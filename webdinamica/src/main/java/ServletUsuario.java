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

import logica.Asistente;
import logica.Organizador;
import logica.ControladorEvento;
import logica.ControladorUsuario;
import logica.DTUsuario;
import logica.Usuario;
import logica.DTAsistente;
import logica.DTOrganizador;

/**
 * Servlet implementation class Usuario
 */
@WebServlet({"/usuarios/*, /inicio_sesion/*"})
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void inicio_sesionGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/eventos/inicio_sesion.jsp").forward(request, response);
    }
    
    protected void inicio_sesionPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nick_o_name = request.getParameter("nick_o_name");
    	
    	ControladorUsuario c = ControladorUsuario.getInstance();
    	Usuario u = c.obtenerUsuario(nick_o_name);
    	
    	if (u != null) {
    		if (u instanceof Asistente) {
    	    	request.getRequestDispatcher("/WEB-INF/eventos/index.jsp").forward(request, response);
    	    	//distingo que partes del index?
    		} else if (u instanceof Organizador) {
    	    	request.getRequestDispatcher("/WEB-INF/eventos/index.jsp").forward(request, response);
    		}
    	} else {
    		request.setAttribute("error", "No existe un usuario con ese nickname");
    	    request.getRequestDispatcher("/WEB-INF/eventos/inicio_sesion.jsp").forward(request, response);
    	    return;
    	}
    	
    	
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		
		if (servletPath.equals("/inicio_sesion")) {
			inicio_sesionGET(request, response);
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
		
		if (servletPath.equals("/inicio_sesion")) {
			inicio_sesionPOST(request, response);
		} else {
	        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        response.getWriter().println("404 - Página no encontrada");
		}
	}

}

