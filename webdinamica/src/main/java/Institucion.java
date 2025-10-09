import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import logica.ControladorInstitucion;


/**
 * Servlet implementation class Institucion
 */
@WebServlet("/instituciones/*")
@MultipartConfig(maxFileSize = 10 * 1024 * 1024)
public class Institucion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Institucion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	request.getRequestDispatcher("/WEB-INF/instituciones/altaInstitucion.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");

        // Get form fields
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("desc");
        String sitioWeb = request.getParameter("sitioweb");
        Part fotoPart = request.getPart("foto");

        /*byte[] fotoBytes = null;
        if (fotoPart != null && fotoPart.getSize() > 0) {
            fotoBytes = fotoPart.getInputStream().readAllBytes();
        }
        */

        // Use your existing controller
        ControladorInstitucion controlador = ControladorInstitucion.getInstance();

        try {
            //controlador.altaInstitucion(nombre, descripcion, sitioWeb, fotoBytes);
        	controlador.altaInstitucion(nombre, descripcion, sitioWeb);
        	request.setAttribute("mensaje", "Institución creada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al crear la institución: " + e.getMessage());
        }

        // Forward to JSP for feedback
        request.getRequestDispatcher("resultadoInstitucion.jsp").forward(request, response);
    }
}
