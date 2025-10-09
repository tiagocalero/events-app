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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
	    if (servletPath.equals("/usuarios")) {
		
				String path = request.getPathInfo();
		
		        if (path == null || path.equals("/")) {
		            response.getWriter().println("Página de consulta");
		        } else if (path.equals("/alta")) {
		        	altaUsuarioGET(request, response);
		        } else {
		            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		            response.getWriter().println("404 - Página no encontrada");
		        }
	    
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String servletPath = request.getServletPath(); // Obtiene /evento o /ediciones
        
	    if (servletPath.equals("/usuarios")) {
		
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
		        
		} else {
		        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		        response.getWriter().println("404 - Página no encontrada");
		}

	}

}
