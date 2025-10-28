import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CargaDatos
 */
@WebServlet("/CargarDatos")
public class CargarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarDatos() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void cargaDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	boolean datosCargados = false;
    	
    	if (getServletContext().getAttribute("datosCargados") != null)
    		datosCargados = (boolean) getServletContext().getAttribute("datosCargados");
    	
    	// No cargar los datos dos veces
    	if (!datosCargados) {
	    	try {
		    	datos.CargarDatos.cargaDatos();
    		} catch (Exception ex) {}
	    	
	    	// Guardar que los datos fueron cargados
	    	getServletContext().setAttribute("datosCargados", true);
    	}

    	
    	// En el link que accede a CargarDatos se incluye el parámetro
    	// redirect para poder redirigir al usuario a la URL en la que
    	// estaba cuando tocó el botón
    	String redirect = request.getParameter("redirect");
    	if (redirect != null && !redirect.isEmpty()) {
    	    response.sendRedirect(redirect);
    	} else {
        	response.sendRedirect("/webdinamica");
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		cargaDatos(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		cargaDatos(request, response);
	}

}
