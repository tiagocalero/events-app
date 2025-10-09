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

import logica.ControladorUsuario;
import logica.ControladorEvento;
import logica.ControladorInstitucion;
import logica.DTUsuario;
import logica.DTAsistente;
import logica.DTOrganizador;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/usuarios/*")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // GET /usuarios/alta
    protected void altaUsuarioGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ControladorInstitucion c = ControladorInstitucion.getInstance();
    	
    	List<String> instituciones = c.listarInstituciones();
        
        request.setAttribute("instituciones", instituciones);
    	
    	// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/usuarios/alta.jsp").forward(request, response);
    }
    
    protected void consultaUsuarioGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/usuarios/consulta.jsp").forward(request, response);
    }
    
	// POST /usuarios/alta
    // Crea una usuario con los parámetros pasados
    protected void altaUsuarioPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String tipo = request.getParameter("tipoUsuario");
    	String nombre = request.getParameter("name");
    	String nickname = request.getParameter("nickname");
    	String apellido = request.getParameter("apellido");
    	LocalDate nacimiento = LocalDate.parse(request.getParameter("nacimiento"));
    	String email = request.getParameter("correo");
    	String inst = request.getParameter("institucion");
    	String desc = request.getParameter("desc");
    	String link = request.getParameter("link");
    	String passwd = request.getParameter("pass");
    	
    	boolean ok;
    	ControladorUsuario c = ControladorUsuario.getInstance();
    	if (tipo == "Asistente") {
    		if (inst != "none") {
    			ok = c.altaAsistente(nickname, nombre, email, passwd, apellido, nacimiento, inst);
    		} else {
    			ok = c.altaAsistente(nickname, nombre, email, passwd, apellido, nacimiento);
    		}
    	} else if (tipo == "Organizador") {
    		ok = c.altaOrganizador(nickname, nombre, email, passwd, desc, link);
    	}
    				
    	if (!ok) {
    		// Se incluyen todos los atributos para que se vuelvan a cargar y que el
    		// usuario no tenga que llenarlos de vuelta
    		request.setAttribute("tipoUsuario", tipo);
    		request.setAttribute("nombre", nombre);
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
    		request.getRequestDispatcher("/WEB-INF/eventos").forward(request, response);
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
	    if (servletPath.equals("/usuarios")) {
				String path = request.getPathInfo();
		        if (path == null || path.equals("/")) {
		            consultaUsuarioGET(request, response);
		        } else if (path.equals("/alta")) {
		        	altaUsuarioGET(request, response);
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
        
	    if (servletPath.equals("/usuarios")) {
		    	String path = request.getPathInfo();
		        if (path == null || path.equals("/")) {
		            response.getWriter().println("Página de consulta");
		        } else if (path.equals("/alta")) {
		        	altaUsuarioPOST(request, response);
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
