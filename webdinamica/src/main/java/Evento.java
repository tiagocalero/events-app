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
import logica.DTEvento;

/**
 * Servlet implementation class Evento
 */
@WebServlet("/evento/*")
public class Evento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Evento() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // POST /evento/alta
    // Crea un evento con los parámetros pasados
    protected void altaEvento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nombre = request.getParameter("nombre");
    	String descripcion = request.getParameter("desc");
    	String sigla = request.getParameter("sigla");
    	String[] categoriasA = request.getParameterValues("categorias");
    	List<String> categorias = (categoriasA != null)
    								? Arrays.asList(categoriasA)
    								: List.of();
    	
    	ControladorEvento c = ControladorEvento.getInstance();
    	c.ingresarDatosEvento(nombre, descripcion, sigla, LocalDate.now(), categorias);
    	
    	// Volver a AltaEvento
		request.getRequestDispatcher("/WEB-INF/eventos/alta.html").forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();

        if (path == null || path.equals("/")) {
            ControladorEvento c = ControladorEvento.getInstance();
            List<String> eventos = c.listarEventos();
            request.setAttribute("eventos", eventos);
    		request.getRequestDispatcher("/WEB-INF/eventos/index.jsp").forward(request, response);
        } else if (path.equals("/consulta")) {
            response.getWriter().println("Página de consulta");
        } else if (path.equals("/alta")) {
    		request.getRequestDispatcher("/WEB-INF/eventos/alta.html").forward(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("404 - Página no encontrada");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();

        if (path == null || path.equals("/")) {
            response.getWriter().println("Página principal");
        } else if (path.equals("/consulta")) {
            response.getWriter().println("Página de consulta");
        } else if (path.equals("/alta")) {
        	altaEvento(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("404 - Página no encontrada");
        }
	}

}
