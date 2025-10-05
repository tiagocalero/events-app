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
    
    // GET /evento
    protected void listarEventos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControladorEvento c = ControladorEvento.getInstance();
        
        // Cargar eventos
        List<DTEvento> eventos = c.listarDTEventos();
        request.setAttribute("eventos", eventos);
        
        // Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/eventos/index.jsp").forward(request, response);
    }
    
    // GET /evento/alta
    protected void altaEventoGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ControladorEvento c = ControladorEvento.getInstance();
    	
    	List<String> categorias = c.listarCategorias();
        
        if (categorias.isEmpty())
        	// BORRAR!! Para usar mientras no haya CargaDatos
            request.setAttribute("categorias", List.of("Categoría 1"));
        else
            request.setAttribute("categorias", categorias);
    	
    	// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/eventos/alta.jsp").forward(request, response);
    }
    
    // POST /evento/alta
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	}

}
