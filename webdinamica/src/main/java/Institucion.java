import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
    	Categoria.listarCategorias(request, response);
    	
    	request.getRequestDispatcher("/WEB-INF/instituciones/altaInstitucion.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		//request.setCharacterEncoding("UTF-8");

        // Get form fields
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("desc");
        String sitioWeb = request.getParameter("sitioweb");
        Part fotoPart = request.getPart("foto");

        String fileName = null;
        if (fotoPart != null && fotoPart.getSize() > 0) {
	        // Generar un nombre aleatorio para la imagen,
	        // guardarla en una carpeta temporal
	        fileName = UUID.randomUUID().toString() + "_" + fotoPart.getSubmittedFileName();
	        String uploadPath = getServletContext().getRealPath("/uploads");
	        File uploadDir = new File(uploadPath);

	        if (!uploadDir.exists()) uploadDir.mkdir();
	
	        fotoPart.write(uploadPath + File.separator + fileName);
        }

        ControladorInstitucion controlador = ControladorInstitucion.getInstance();

        try {
            controlador.altaInstitucion(nombre, descripcion, sitioWeb, fileName);
        	request.setAttribute("mensaje", "Institución creada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al crear la institución: " + e.getMessage());
        }

        //volver a alta Institucion
        doGet(request, response);
    }
}
