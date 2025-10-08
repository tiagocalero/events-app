import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import logica.ControladorEvento;
import logica.ControladorCategoria;
import logica.DTEvento;
import logica.DTPatrocinio;
import logica.TipoNivelPatrocinio;

/**
 * Servlet implementation class Evento
 */
@WebServlet({"/eventos/*", "/ediciones/*", "/patrocinios/*"}) 
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,     // 1 MB
	    maxFileSize = 1024 * 1024 * 10,      // 10 MB
	    maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
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
    
    // GET /patrocinios
    // POST /patrocinios
    protected void consultaPatrocinio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ControladorEvento c = ControladorEvento.getInstance();

    	String evento = request.getParameter("evento");
    	String edicion = request.getParameter("edicion");
    	String institucion = request.getParameter("institucion");
    	
    	// Si el evento/edición seleccionados no existen o la edición
    	// no existe para ese evento, o la institución no existe
    	// para la edición, redirigir a página principal
    	if (!c.existeEdicionEvento(evento, edicion)
    		|| !c.listarInstituciones(evento, edicion).contains(institucion)) {
        	response.sendRedirect("/webdinamica");
    		return;
    	}
  
    	DTPatrocinio dtp = c.consultaPatrocinioInstitucion(evento, edicion, institucion);
	    request.setAttribute("dtp", dtp);
    	
    	// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/patrocinios/consulta.jsp").forward(request, response);
    }

    // GET /patrocinios/alta
    protected void altaPatrocinioGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		request.getRequestDispatcher("/WEB-INF/patrocinios/alta.jsp").forward(request, response);
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
    	// Obtener imagen
        Part filePart = request.getPart("imagen");
    	boolean subioImagen = filePart != null && filePart.getSize() > 0;

    	ControladorEvento c = ControladorEvento.getInstance();
    	boolean ok;
    	
    	if (subioImagen) {
	        // Generar un nombre aleatorio para la imagen,
	        // guardarla en una carpeta temporal
	        String fileName = UUID.randomUUID().toString() + "_" + filePart.getSubmittedFileName();
	        String uploadPath = getServletContext().getRealPath("/uploads");
	        File uploadDir = new File(uploadPath);
	        
	        if (!uploadDir.exists()) uploadDir.mkdir();
	
	        filePart.write(uploadPath + File.separator + fileName);
	    	
	    	ok = c.ingresarDatosEvento(nombre, descripcion, sigla, LocalDate.now(), categorias, fileName);
    	} else {
    		ok = c.ingresarDatosEvento(nombre, descripcion, sigla, LocalDate.now(), categorias);
    	}
    	
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
    
    // POST /edicion/alta
    // Crea una edicion con los parámetros pasados
    protected void altaEdicionPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nombre = request.getParameter("name");
    	String ciudad = request.getParameter("ciudad");
    	String sigla = request.getParameter("sigla");
    	String pais = request.getParameter("pais");
    	LocalDate fechaInicio = LocalDate.parse(request.getParameter("fecha-inicio"));
    	LocalDate fechaFin = LocalDate.parse(request.getParameter("fecha-fin"));
    	
    	
    	ControladorEvento c = ControladorEvento.getInstance();
    	boolean ok = c.altaEdicion(altaEdicion(nombreEvento, nicknameOrganizador, nombre, sigla, fechaInicio, fechaFin, fechaAlta, ciudad, pais));
    	
    	if (!ok) {
    		// Se incluyen todos los atributos para que se vuelvan a cargar y que el
    		// usuario no tenga que llenarlos de vuelta
    		request.setAttribute("nombre", nombre);
    		request.setAttribute("ciudad", ciudad);
    		request.setAttribute("sigla", sigla);
    		request.setAttribute("pais", pais);
    		
    		request.setAttribute("error", "Ya existe un evento con ese nombre");
    	}
    	// Volver a AltaEvento
		altaEventoGET(request, response);
    }

    
    protected void altaTipoRegistroPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nombre = request.getParameter("nombre");
    	String desc = request.getParameter("descripcion");
    	float costo = request.getParameter(costo);
    	int cupo = request.getParameter(cupo);
    	
    	ControladorEvento c = ControladorEvento.getInstance();
    	boolean ok = c.crearTipoRegistro(c.nombre, nombre, nombre, desc, costo, cupo);
    	
    	if (!ok) {
    		request.setAttribute("nombre", nombre);
    		request.setAttribute("descripcion", desc);
    		request.setAttribute("costo", costo);
    		request.setAttribute("cupo", cupo);
    		
    	}
    	
    	
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
		        } else {
		            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		            response.getWriter().println("404 - Página no encontrada");
		        }
	    
		} else if (servletPath.equals("/ediciones")) {

		} else if (servletPath.equals("/patrocinios")) {
			String path = request.getPathInfo();
			if (path == null || path.equals("/")) {
				consultaPatrocinio(request, response);
			} else if (path.equals("/alta")) {
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
								
		        if (path.equals("/alta")) {
		        	altaEdicionPOST(request, response);
		        } else {
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
        	
		} else if (servletPath.equals("/TipoRegistro")) {
			String path = request.getPathInfo();
			if (path.equals("/alta")) {
				altaTipoRegistroPOST(request, response);
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
