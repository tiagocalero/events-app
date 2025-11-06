import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Categoria
 */
@WebServlet("/Categoria")
public class Categoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Categoria() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected static void listarCategorias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	main.java.publicar.PublicadorCategoriaService service = new main.java.publicar.PublicadorCategoriaService();
    	main.java.publicar.PublicadorCategoria port = service.getPublicadorCategoriaPort();
    	
        List<String> categoriasListado = port.categorias().getItem();
        request.setAttribute("categoriasListado", categoriasListado);
    }

    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	listarCategorias(request, response);
    }
}

