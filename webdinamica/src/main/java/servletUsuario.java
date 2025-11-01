import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import logica.ControladorEvento;
import logica.ControladorInstitucion;
import logica.ControladorUsuario;
import logica.DTAsistente;
import logica.DTAsistenteModificado;
import logica.DTEdicion;
import logica.DTOrganizador;
import logica.DTOrganizadorModificado;
import logica.DTRegistro;
import logica.DTUsuario;
import publicar.DtUsuario;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/usuarios/*")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,     // 1 MB
	    maxFileSize = 1024 * 1024 * 10,      // 10 MB
	    maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
public class servletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // GET /usuarios/alta
    protected void altaUsuarioGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Cargar eventos
        publicar.PublicadorInstitucionService service = new publicar.PublicadorInstitucionService();
        publicar.PublicadorInstitucion port = service.getPublicadorInstitucionPort();
    	
    	List<String> instituciones = port.listarInstituciones();
        request.setAttribute("instituciones", instituciones);
        
    	// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/usuarios/alta.jsp").forward(request, response);
    }
    
    protected void perfilUsuarioGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ControladorUsuario c = ControladorUsuario.getInstance();
    	ControladorInstitucion i = ControladorInstitucion.getInstance();
    	
    	List<String> instituciones = i.listarInstituciones();
        
        request.setAttribute("instituciones", instituciones);
        HttpSession userSession = request.getSession(false);
		DTUsuario usuario = (DTUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// No permitir que visitantes accedan
		if (usuario == null) {
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		}
		
    	usuario = c.elegirUsuario(usuario.getNickname());
    	
    	if (usuario instanceof DTAsistente) {
    		List<DTRegistro> registros = c.consultaAsistente();
    		request.setAttribute("registros", registros);
    	} else if (usuario instanceof DTOrganizador) {
    		List<DTEdicion> ediciones = c.consultaOrganizador();
    		request.setAttribute("ediciones", ediciones);
    	}
    	
    	request.getRequestDispatcher("/WEB-INF/usuarios/perfil.jsp").forward(request, response);
    }
    
    protected void consultaUsuarioGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ControladorUsuario c = ControladorUsuario.getInstance();
     	String nick = (String) request.getParameter("nick");
    	if (nick != null && c.elegirUsuario(nick) != null) {
    		DTUsuario usuario = c.elegirUsuario(nick);
        	
        	if (usuario instanceof DTAsistente) {
        		List<DTRegistro> registros = c.consultaAsistente();
        		request.setAttribute("registros", registros);
        	}
        	
    		request.setAttribute("datos", usuario);
        	request.getRequestDispatcher("/WEB-INF/usuarios/consulta.jsp").forward(request, response);
    	} else {
    		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		response.getWriter().println("404 - Página no encontrada");
    	}
    }
    
	// POST /usuarios/alta
    // Crea una usuario con los parámetros pasados
    protected void altaUsuarioPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String tipo = request.getParameter("tipo");
    	String nombre = request.getParameter("name");
    	String nickname = request.getParameter("nickname");
    	String apellido = request.getParameter("apellido");
    	LocalDate nacimiento = null;
    	if (!request.getParameter("nacimiento").equals("")) {
    		nacimiento = LocalDate.parse(request.getParameter("nacimiento"));
    	}
    	String email = request.getParameter("correo");
    	String inst = request.getParameter("institucion");
    	String desc = request.getParameter("desc");
    	String link = request.getParameter("link");
    	String passwd = request.getParameter("pass");
    	
    	Part imgPart = request.getPart("foto");
    	String imgName = null;
    	String uniqueFileName = null;
    	if (imgPart != null && imgPart.getSize() > 0) {
    		imgName = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();

    		// Define where to store it (for example inside /uploads)
    		String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
    		File uploadDir = new File(uploadPath);
    		if (!uploadDir.exists()) uploadDir.mkdir();

    		// Create unique filename to avoid conflicts
    		uniqueFileName = System.currentTimeMillis() + "_" + imgName;
    		imgPart.write(uploadPath + File.separator + uniqueFileName);
    	}
    		
    	boolean ok = false;
    	ControladorUsuario c = ControladorUsuario.getInstance();
    	if (tipo.equals("Asistente")) {
    		if (inst != "none") {
    			ok = c.altaAsistente(nickname, nombre, email, passwd, uniqueFileName, apellido, nacimiento, inst);
    		} else {
    			ok = c.altaAsistente(nickname, nombre, email, passwd, uniqueFileName, apellido, nacimiento);
    		}
    	} else if (tipo.equals("Organizador")) {
    		ok = c.altaOrganizador(nickname, nombre, email, passwd, uniqueFileName, desc, link);
    	}
    				
    	if (!ok) {
    		// Se incluyen todos los atributos para que se vuelvan a cargar y que el
    		// usuario no tenga que llenarlos de vuelta
    		request.setAttribute("tipo", tipo);
    		request.setAttribute("name", nombre);
    		request.setAttribute("nickname", nickname);
    		request.setAttribute("apellido", apellido);
    		request.setAttribute("nacimiento", nacimiento);
    		request.setAttribute("correo", email);
    		request.setAttribute("institucion", inst);
    		request.setAttribute("link", link);
    		request.setAttribute("desc", desc);
    		
    		request.setAttribute("error", "Ya existe un usuario con ese nickname o correo");

        	// Volver a AltaUsuario
    		altaUsuarioGET(request, response);
    	} else {
    		request.getRequestDispatcher("/WEB-INF/usuarios/inicio_sesion.jsp").forward(request, response);
    	}
    }
    
    protected void modificarUsuarioPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ControladorUsuario c = ControladorUsuario.getInstance();
    	HttpSession userSession = request.getSession(false);
		DTUsuario dtu = (DTUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// No permitir que visitantes accedan
		if (dtu == null) {
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		}
		
    	String nick = dtu.getNickname();
    	
    	String nombre = request.getParameter("name");

    	Part imgPart = request.getPart("imagenPerfil");
    	String imgName = null;
    	String uniqueFileName = null;
    	if (imgPart != null && imgPart.getSize() > 0) {
    		imgName = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();

    		// Define where to store it (for example inside /uploads)
    		String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
    		File uploadDir = new File(uploadPath);
    		if (!uploadDir.exists()) uploadDir.mkdir();

    		// Create unique filename to avoid conflicts
    		uniqueFileName = System.currentTimeMillis() + "_" + imgName;
    		imgPart.write(uploadPath + File.separator + uniqueFileName);
    	}

    	dtu = c.elegirUsuario(nick);
    	
    	if (dtu instanceof DTAsistente) {
    		String apellido = request.getParameter("apellido");
        	String inst = request.getParameter("institucion");
        	LocalDate nacimiento = LocalDate.parse(request.getParameter("nacimiento"));
    		
    		DTAsistenteModificado dtam = new DTAsistenteModificado(nombre, uniqueFileName, apellido, nacimiento, inst);
    		c.modificarDatosUsuario(dtam);
    	} else if (dtu instanceof DTOrganizador) {
    		String desc = request.getParameter("desc");
        	String link = request.getParameter("link");
        	
    		DTOrganizadorModificado dtom = new DTOrganizadorModificado(nombre, uniqueFileName, desc, link);
    		c.modificarDatosUsuario(dtom);
    	}
    	
		dtu = c.elegirUsuario(nick);
		userSession.setAttribute("usuario", dtu);
    	response.sendRedirect("/webdinamica/usuarios/");
    }

    protected void inicio_sesionGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/usuarios/inicio_sesion.jsp").forward(request, response);
    }
    
    protected void inicio_sesionPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nick_o_correo = request.getParameter("nick_o_correo");
    	String passwd = request.getParameter("passwd");
    	
    	publicar.PublicadorUsuarioService service = new publicar.PublicadorUsuarioService();
        publicar.PublicadorUsuario port = service.getPublicadorUsuarioPort();	
        
    	DtUsuario u = port.elegirUsuario(nick_o_correo);

    	if (u != null && port.chequearContrasena(nick_o_correo, passwd)) {
    		HttpSession session = request.getSession(true);
    		session.setAttribute("usuario", u);
        	response.sendRedirect("/webdinamica");
    	} else {
    		request.setAttribute("error", "El usuario o contraseña son incorrectos");
    		request.setAttribute("nick_o_correo", nick_o_correo);
    		request.setAttribute("passwd", passwd);
    	    request.getRequestDispatcher("/WEB-INF/usuarios/inicio_sesion.jsp").forward(request, response);
    	}
    }
    
    protected void cambiarContraGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession userSession = request.getSession(false);
		DTUsuario usuario = (DTUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// No permitir que visitantes accedan
		if (usuario == null) {
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/usuarios/cambiar_contrasena.jsp").forward(request, response);
    }
    
    protected void cambiarContraPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String actual = request.getParameter("pass");
 		String nueva = request.getParameter("newPass");
 		String confNueva = request.getParameter("confNewPass");
 		
 		ControladorUsuario cu = ControladorUsuario.getInstance();HttpSession userSession = request.getSession(false);
		DTUsuario dtu = (DTUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// No permitir que visitantes accedan
		if (dtu == null) {
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		}
		
 		if (cu.chequearContrasena(dtu.getNickname(), actual)) {
 			if (actual.equals(nueva)) {
 				request.setAttribute("error2", "La nueva contraseña no puede ser igual a la actual.");
 	    	    request.getRequestDispatcher("/WEB-INF/usuarios/cambiar_contrasena.jsp").forward(request, response);
 			} else if (!nueva.equals(confNueva)) {
 				request.setAttribute("error3", "Las contraseñas no coniciden.");
 	    	    request.getRequestDispatcher("/WEB-INF/usuarios/cambiar_contrasena.jsp").forward(request, response);
 			} else {
 				cu.setContrasena(dtu.getNickname(), nueva);
 				response.sendRedirect("/webdinamica/usuarios/fin_sesion");
 			}
 		} else {
 			request.setAttribute("error", "Contraseña incorrecta.");
    	    request.getRequestDispatcher("/WEB-INF/usuarios/cambiar_contrasena.jsp").forward(request, response);
 		}
    }
    
    // ######  CONSULTA REGISTRO DOGET  ###### 

 	protected void consultaRegistroGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		ControladorUsuario cu = ControladorUsuario.getInstance();
    		    	
 		String evento = request.getParameter("evento");
 		String edicion = request.getParameter("edicion");
    		    	
 		HttpSession user = request.getSession(false);
 		DTUsuario usuario = (DTUsuario) user.getAttribute("usuario");
    		     	 
 		if (usuario instanceof DTOrganizador) {
    		    		
 			ControladorEvento ce = ControladorEvento.getInstance();
    	    		
    		List<DTRegistro> registros = ce.registrosEdicion(edicion, evento);
    		request.setAttribute("registros", registros);
    		request.setAttribute("edicion", edicion);

    		    		
    		request.getRequestDispatcher("/WEB-INF/eventos/consultaRegistroOrg.jsp").forward(request, response);
    		    	
    	} else if (usuario instanceof DTAsistente) {
    		DTRegistro registro = cu.datosRegistroUsuario(edicion, usuario.getNickname());
    		
    		request.setAttribute("patrocinio", registro.getPatrocinio());
    		request.setAttribute("costo", registro.getCosto());
    		request.setAttribute("fecha", registro.getFecha());
    		request.setAttribute("tipo", registro.getTipo());
    		request.setAttribute("edicion", edicion);
    		    		
    		request.getRequestDispatcher("/WEB-INF/eventos/consultaRegistroAsis.jsp").forward(request, response);
    	} else {
    		// Error: Usuario no logueado o tipo desconocido
    		response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
    	}	    	
    }
 	
	protected void listaGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorUsuario cu = ControladorUsuario.getInstance();
		List<String> usuarios = cu.listarUsuarios();
		request.setAttribute("lista", usuarios);
		
    	request.getRequestDispatcher("/WEB-INF/usuarios/lista.jsp").forward(request, response);
    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Categoria.listarCategorias(request, response);
		String servletPath = request.getServletPath();
	    if (servletPath.equals("/usuarios")) {
				String path = request.getPathInfo();
		        if (path == null || path.equals("/")) {
		            perfilUsuarioGET(request, response);
		        } else if (path.equals("/alta")) {
		        	altaUsuarioGET(request, response);
		        } else if (path.equals("/consulta")) {
		        	consultaUsuarioGET(request, response);
		        } else if (path.equals("/lista")) {
		        	listaGET(request, response);
	    		} else if (path.equals("/inicio_sesion")) {
					inicio_sesionGET(request, response);
		        } else if (path.equals("/cambiar_contrasena")) {
		        	cambiarContraGET(request, response);
		        } else if (path.equals("/consultaRegistroAsis")) {
		        	consultaRegistroGET(request, response);
		        } else if (path.equals("/consultaRegistroOrg")) {
		        	consultaRegistroGET(request, response);
		        } else if (path.equals("/fin_sesion")) {
		        	HttpSession session = request.getSession(false);
		            if (session != null) {
		                session.invalidate();
		            }
		            response.sendRedirect("/webdinamica/");
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
		String servletPath = request.getServletPath();
		Categoria.listarCategorias(request, response);
	    
	    if (servletPath.equals("/usuarios")) {
		    	String path = request.getPathInfo();
		        if (path == null || path.equals("/modificar")) {
		        	modificarUsuarioPOST(request, response);
		        } else if (path.equals("/alta")) {
		        	altaUsuarioPOST(request, response);
		        } else if(path.equals("/inicio_sesion")) {
					inicio_sesionPOST(request, response);
				} else if (path.equals("/cambiar_contrasena")) {
		        	cambiarContraPOST(request, response);
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
