import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import logica.ControladorEvento;
import logica.ControladorCategoria;
import logica.DTEvento;
import logica.DTEdicion;
import logica.DTPatrocinio;
import logica.TipoNivelPatrocinio;

/**
 * Servlet implementation class Evento
 */
@WebServlet({"/eventos/*", "/ediciones/*", "/patrocinios/*"}) 
public class Evento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Evento() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // GET /eventos
    protected void listarEventos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControladorEvento c = ControladorEvento.getInstance();
        
        // Cargar eventos
        List<DTEvento> eventos = c.listarDTEventos();
        request.setAttribute("eventos", eventos);
        
        // Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/eventos/index.jsp").forward(request, response);
    }

    // GET /patrocinios/alta
    protected void altaPatrocinioGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// FALTA cargar eventos, ediciones, instituciones
    	ControladorEvento c = ControladorEvento.getInstance();

    	String evento = request.getParameter("evento");
    	String edicion = request.getParameter("edicion");
    	
    	// Si el evento/edición seleccionados no existen o la edición
    	// no existe para ese evento, redirigir a página principal
    	if (!c.existeEdicionEvento(evento, edicion)) {
        	response.sendRedirect("/webdinamica");
    		return;
    	}

    	List<String> instituciones = c.listarInstituciones();
        request.setAttribute("instituciones", instituciones);
        
        List<String> tipoRegistros = c.listarTipoRegistro(evento, edicion);
        request.setAttribute("tipoRegistros", tipoRegistros);
    	
    	// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/eventos/altaPatrocinio.jsp").forward(request, response);
    }
    
    // POST /patrocinios/alta
    // Crea un patrocinio con los parámetros pasados
    protected void altaPatrocinioPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String evento = request.getParameter("evento");
    	String edicion = request.getParameter("edicion");
    	String institucion = request.getParameter("institucion");
    	TipoNivelPatrocinio nivel = TipoNivelPatrocinio.valueOf(request.getParameter("nivel"));
    	float monto = Float.parseFloat(request.getParameter("monto"));
    	int cantRegistros = Integer.parseInt(request.getParameter("cantRegistros"));
    	String tipoRegistro = request.getParameter("tipoRegistro");
    	String codigo = request.getParameter("codigo");
    	
    	DTPatrocinio dtp = new DTPatrocinio(codigo, nivel, monto, tipoRegistro, cantRegistros, LocalDate.now(), institucion);
    	
    	ControladorEvento c = ControladorEvento.getInstance();
    	try {
    		c.crearPatrocinio(dtp, edicion, institucion, evento);
    	} catch (Exception ex) {
    		request.setAttribute("error", ex.getMessage());
    		
    		// Se incluyen todos los atributos para que se vuelvan a cargar y
    		// que el usuario no tenga que llenarlos de vuelta
    		request.setAttribute("institucion", institucion);
    		request.setAttribute("nivel", nivel.toString());
    		request.setAttribute("monto", monto);
    		request.setAttribute("cantRegistros", cantRegistros);
    		request.setAttribute("tipoRegistro", tipoRegistro);
    		request.setAttribute("codigo", codigo);
    		altaPatrocinioGET(request, response);
    	}
    	
    	// Volver a AltaPatrocinio
		altaPatrocinioGET(request, response);
    }
    
    // GET /eventos/alta
    // Carga las categorías existentes en el formulario
    protected void altaEventoGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ControladorEvento c = ControladorEvento.getInstance();
    	
    	List<String> categorias = c.listarCategorias();
        
        request.setAttribute("categorias", categorias);
    	
    	// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/eventos/alta.jsp").forward(request, response);
    }
    
    // POST /eventos/alta
    // Crea un evento con los parámetros pasados
    protected void altaEventoPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nombre = request.getParameter("nombre");
    	String descripcion = request.getParameter("desc");
    	String sigla = request.getParameter("sigla");
    	String[] categoriasA = request.getParameterValues("categorias");
    	List<String> categorias = (categoriasA != null)
    								? Arrays.asList(categoriasA)
    								: List.of();
    	
    	ControladorEvento c = ControladorEvento.getInstance();
    	boolean ok = c.ingresarDatosEvento(nombre, descripcion, sigla, LocalDate.now(), categorias);
    	
    	if (!ok) {
    		// Se incluyen todos los atributos para que se vuelvan a cargar y que el
    		// usuario no tenga que llenarlos de vuelta
    		request.setAttribute("nombre", nombre);
    		request.setAttribute("descripcion", descripcion);
    		request.setAttribute("sigla", sigla);
    		request.setAttribute("categoriasA", categoriasA);
    		
    		request.setAttribute("error", "Ya existe un evento con ese nombre");
    	}
    	// Volver a AltaEvento
		altaEventoGET(request, response);
    }
    
    
    
    
    
     //######  ALTA EDICION  ######
    
			 // GET /ediciones/altaEdicion
			 // Llama a la ventana de altaEdicion
			 protected void altaEdicionGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String nombreEvento = request.getParameter("evento");
				 
				request.setAttribute("nombreEvento", nombreEvento);
				 
				 // Mostrar el HTML
				request.getRequestDispatcher("/WEB-INF/eventos/altaEdicion.jsp").forward(request, response);
			 }
			 
			 
		     // POST /ediciones/altaEdicion
		     // Crea una edicion con los parámetros pasados
		     protected void altaEdicionPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    	String nombre = request.getParameter("name");
		    	String ciudad = request.getParameter("ciudad");
		    	String sigla = request.getParameter("sigla");
		    	String pais = request.getParameter("pais");
		    	LocalDate fechaInicio = LocalDate.parse(request.getParameter("fecha-inicio"));
		    	LocalDate fechaFin = LocalDate.parse(request.getParameter("fecha-fin"));
		    	String evento = request.getParameter("evento");
		    	String edicion = request.getParameter("edicion");
		    	LocalDate fechaAlta = LocalDate.now();
		    	//Hay que agregar el nickname del organizador desde la sesion
		    	
		    	ControladorEvento c = ControladorEvento.getInstance();
		    	//boolean ok = c.altaEdicion(altaEdicion(evento, nicknameOrganizador, nombre, sigla, fechaInicio, fechaFin, fechaAlta, ciudad, pais));
		    	
		        /*if (!ok) {
		        	// Se incluyen todos los atributos para que se vuelvan a cargar y que el
		        	// usuario no tenga que llenarlos de vuelta
		        	request.setAttribute("name", nombre);
		        	request.setAttribute("ciudad", ciudad);
		        	request.setAttribute("sigla", sigla);
		        	request.setAttribute("pais", pais);
		        	request.setAttribute("fecha-inicio", fechaInicio);
		        	request.setAttribute("fecha-fin", fechaFin);
		        		
		        	request.setAttribute("error", "Ya existe una edicion con ese nombre");
		        }*/
		        
		        // Volver a ConsultaEvento
		    		//consultaEventoGET(request, response);
		      }
    	
     
     
     
     
     
     //######  CONSULTA EDICION  ######
     
			 // GET /ediciones/consultaEdicion
		     // Carga las categorías existentes en el formulario
		     protected void consultaEdicionGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				    	ControladorEvento c = ControladorEvento.getInstance();
		
				    	String evento = request.getParameter("evento");
				    	String edicion = request.getParameter("edicion");
				    	
				    	DTEdicion datosEdicion = c.obtenerEdicion(edicion, evento);
				    	
				    	String pais = datosEdicion.getPais();
				    	request.setAttribute("pais", pais);
				    	
				    	String sigla = datosEdicion.getSigla();
				    	request.setAttribute("sigla", sigla);
				    	
				    	String ciudad = datosEdicion.getCiudad();
				    	request.setAttribute("ciudad", ciudad);
				    	
				    	LocalDate fechaIni = datosEdicion.getFechaInicio();
				    	request.setAttribute("fecha-ini", fechaIni);
				    	
				    	LocalDate fechaFin = datosEdicion.getFechaFin();
				    	request.setAttribute("fecha-fin", fechaFin);
				    	
				    	LocalDate fechaAlta = datosEdicion.getFechaAlta();
				    	request.setAttribute("fecha-alta", fechaAlta);
				    	
				    	List<String> tiposRegistros = datosEdicion.getTipoRegistros();
				    	request.setAttribute("tiposRegistros", tiposRegistros);
				    	
				    	String organizador = datosEdicion.getOrganizador();
				    	request.setAttribute("organizador", organizador);
				    	
				    	if (!c.existeEdicionEvento(evento, edicion)) {
				        	response.sendRedirect("/webdinamica");
				    		return;
				    	}
				    
				    	// Mostrar el HTML
						request.getRequestDispatcher("/WEB-INF/eventos/consultaEdicion.jsp").forward(request, response);
				    }
     
     
     
     
     
     //######  REGISTRO A EDICION DE EVENTO  ######
     
		     //GET
		     //muestra la pantalla para registrarse
		     protected void registroAEdicionEventoGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    	 String nombreEdicion = request.getParameter("edicion");
		    	 String nombreEvento = request.getParameter("evento");
		    	 
		    	 ControladorEvento c = ControladorEvento.getInstance();
		    	 
		    	 List<String> tiposRegistro = c.listarTipoRegistro(nombreEvento, nombreEdicion);
		 		request.setAttribute("tiposRegistro", tiposRegistro);
		 		
		 		request.setAttribute("nombreEvento", nombreEvento);
		 	    request.setAttribute("nombreEdicion", nombreEdicion);
		 	    
		    	// Mostrar el HTML
		 		request.getRequestDispatcher("/WEB-INF/eventos/registroAEdicionEvento.jsp").forward(request, response);
		 	 }
		     
		     
		     //POST
		     //crea el registro al evento
		     protected void registroAEdicionEventoPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    	String nombreEdicion = request.getParameter("edicion");
		     	String nombreTRegistro = request.getParameter("nombreTRegistro");
		     	String evento = request.getParameter("evento");
		     	LocalDate fechaAlta = LocalDate.now();
		     	//Hay que agregar el nickname del asistente desde la sesion
		     	
		     	ControladorEvento c = ControladorEvento.getInstance();
		    	 
		    	//boolean ok = c.darDeAltaRegistro(nickAsistente, nombreEdicion, String nombreTRegistro, String nickEvento, LocalDate fechaAlta)
		    	
		     	/*if (!ok) {
		    	// Se incluyen todos los atributos para que se vuelvan a cargar y que el
		    	// usuario no tenga que llenarlos de vuelta
		    		request.setAttribute("tiposRegistro", tiposRegistro);
		    		
		    	request.setAttribute("error", "Ya existe una edicion con ese nombre");
		    }*/
		    
		    // Volver a ConsultaEdicion
				//consultaEdicionGET(request, response); 
		  	 }
     
     
     
     
   //######  CONSULTA EVENTO  ######
     
	   	 // GET /ediciones/consultaEdicion
	        // Carga las categorías existentes en el formulario
	        protected void consultaEventoGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	   		    	ControladorEvento c = ControladorEvento.getInstance();
	
	   		    	String evento = request.getParameter("evento");
	   		    	
	   		    	DTEvento datosEvento = c.informacionEvento(evento);
	   		    	
	   		    	String descripcion = datosEvento.getDescripcion();
	   		    	request.setAttribute("descripcion", descripcion);
	   		    	
	   		    	String sigla = datosEvento.getSigla();
	   		    	request.setAttribute("sigla", sigla);
	   		    	
	   		    	LocalDate fechaAlta = datosEvento.getFechaAlta();
	   		    	request.setAttribute("fecha-alta", fechaAlta);
	   		    	
	   		    	List<String> categorias = datosEvento.getCategorias();
	   		    	request.setAttribute("categorias", categorias);
	   		    	
	   		    	List<String> ediciones = datosEvento.getEdiciones();
	   		    	request.setAttribute("ediciones", ediciones);
	   		    
	   		    	// Mostrar el HTML
	   				request.getRequestDispatcher("/WEB-INF/eventos/consultaEvento.jsp").forward(request, response);
	   		    }
    
    
    
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String servletPath = request.getServletPath(); // Obtiene /evento o /ediciones
	    if (servletPath.equals("/eventos")) {
		
				String path = request.getPathInfo();
		
		        if (path == null || path.equals("/")) {
		            listarEventos(request, response);
		        } else if (path.equals("/consulta")) {
		            response.getWriter().println("Página de consulta");
		        } else if (path.equals("/alta")) {
		        	altaEventoGET(request, response);
		        } else if (path.equals("/consultaEvento")) {
		        	consultaEventoGET(request, response);
		        }
		        else {
		            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		            response.getWriter().println("404 - Página no encontrada");
		        }
	    
		} else if (servletPath.equals("/ediciones")) {
				
				String path = request.getPathInfo();
				
				if (path.equals("/altaEdicion")) {
					
					altaEdicionGET(request, response);
					
				} else if (path.equals("/consultaEdicion")){
					
					consultaEdicionGET(request, response);
					
				} else if (path.equals("/registroAEdicionEvento")) {
					
					registroAEdicionEventoGET(request, response);
				}
				else {
		            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		            response.getWriter().println("404 - Página no encontrada");
		        }
				
		} else if (servletPath.equals("/patrocinios")) {
			String path = request.getPathInfo();
        	if (path.equals("/alta")) {
        		altaPatrocinioGET(request, response);
        	} else {
	            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	            response.getWriter().println("404 - Página no encontrada");
	        }
		} else {
		        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		        response.getWriter().println("404 - Página no encontrada");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String servletPath = request.getServletPath(); // Obtiene /evento o /ediciones
        
	    if (servletPath.equals("/eventos")) {
		
		    	String path = request.getPathInfo();
	
		        if (path == null || path.equals("/")) {
		            listarEventos(request, response);
		        } else if (path.equals("/consulta")) {
		            response.getWriter().println("Página de consulta");
		        } else if (path.equals("/alta")) {
		        	altaEventoPOST(request, response);
		        } else {
		            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		            response.getWriter().println("404 - Página no encontrada");
		        }
	    
		} else if (servletPath.equals("/ediciones")) {
				
			String path = request.getPathInfo();
			
		        if (path.equals("/altaEdicion")) {
		        	altaEdicionPOST(request, response);
		        	
		        } else if (path.equals("/registroAEdicionEvento")) {
					
					registroAEdicionEventoPOST(request, response);
				} 
		        else {
		            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		            response.getWriter().println("404 - Página no encontrada");
		        }

		} else if (servletPath.equals("/patrocinios")) {
			String path = request.getPathInfo();
        	if (path.equals("/alta")) {
        		altaPatrocinioPOST(request, response);
        	} else {
	            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	            response.getWriter().println("404 - Página no encontrada");
	        }
		} else {
		        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		        response.getWriter().println("404 - Página no encontrada");
		}
	}

}

