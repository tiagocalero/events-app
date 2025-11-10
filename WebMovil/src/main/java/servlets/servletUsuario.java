package servlets;

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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import main.java.publicar.DtAsistenteModificado;
import main.java.publicar.DtOrganizadorModificado;
import main.java.publicar.DtUsuario;
import main.java.publicar.DtAsistente;
import main.java.publicar.DtEdicion;
import main.java.publicar.DtOrganizador;
import main.java.publicar.DtEdicion;
import main.java.publicar.DtRegistro;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import java.time.format.DateTimeFormatter;
import java.io.OutputStream;

import main.java.publicar.PublicadorEventoService;
import main.java.publicar.PublicadorEvento;
import main.java.publicar.PublicadorUsuarioService;
import main.java.publicar.PublicadorUsuario;
import main.java.publicar.PublicadorInstitucionService;
import main.java.publicar.PublicadorInstitucion;

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
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void inicioSesionGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/usuarios/inicio_sesion.jsp").forward(request, response);
    }
    
    protected void inicioSesionPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nick_o_correo = request.getParameter("nick_o_correo");
    	String passwd = request.getParameter("passwd");
    	
    	PublicadorUsuarioService service = new PublicadorUsuarioService();
        PublicadorUsuario port = service.getPublicadorUsuarioPort();	

    	if (port.chequearContrasena(nick_o_correo, passwd)) {
        	DtUsuario u = port.elegirUsuario(nick_o_correo);
    		HttpSession session = request.getSession(true);
    		session.setAttribute("usuario", u);
        	response.sendRedirect("/webmovil/eventos");
    	} else {
    		request.setAttribute("error", "El usuario o contraseña son incorrectos");
    		request.setAttribute("nick_o_correo", nick_o_correo);
    		request.setAttribute("passwd", passwd);
    	    request.getRequestDispatcher("/WEB-INF/usuarios/inicio_sesion.jsp").forward(request, response);
    	}
    }
    
    // ######  CONSULTA REGISTRO DOGET  ###### 

 	protected void consultaRegistroGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		PublicadorUsuarioService service = new PublicadorUsuarioService();
        PublicadorUsuario port = service.getPublicadorUsuarioPort();
    		    	
 		String evento = request.getParameter("evento");
 		String edicion = request.getParameter("edicion");
    		    	
 		HttpSession userSession = request.getSession(false);
 		DtUsuario usuario = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);
    		     	 
 		if (usuario instanceof DtAsistente) {
    		DtRegistro registro = port.datosRegistroUsuario(edicion, usuario.getNickname());
    		
    		request.setAttribute("patrocinio", registro.isPatrocinio());
    		request.setAttribute("costo", registro.getCosto());
    		request.setAttribute("fecha", registro.getFecha());
    		request.setAttribute("tipo", registro.getTipo());
    		request.setAttribute("edicion", edicion);
    		request.setAttribute("asistencia", registro.isAsistencia());
    		request.setAttribute("confirmable", registro.isConfirmable());
    		    		
    		request.getRequestDispatcher("/WEB-INF/eventos/consultaRegistroAsis.jsp").forward(request, response);
    	} else {
    		// Error: Usuario no logueado o tipo desconocido
    		response.sendRedirect("/webmovil/usuarios/inicio_sesion");
    	}	    	
    }
	
	protected void regNCGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession userSession = request.getSession(false);
 		DtUsuario usuario = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);
		
		PublicadorUsuarioService service = new PublicadorUsuarioService();
        PublicadorUsuario port = service.getPublicadorUsuarioPort();
		if (usuario instanceof DtAsistente) {
        		List<DtRegistro> registrosNC = port.consultaAsistente().getItem();
        		request.setAttribute("regnc", registrosNC);
        }
		
    	request.getRequestDispatcher("/WEB-INF/usuarios/registrosNoConf.jsp").forward(request, response);
    }
	
	protected void confirmarAsistenciaPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession userSession = request.getSession(false);
 		DtUsuario usuario = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);
		
		PublicadorUsuarioService service = new PublicadorUsuarioService();
        PublicadorUsuario port = service.getPublicadorUsuarioPort();
		String edicion = request.getParameter("edicion");
		String evento = request.getParameter("evento");
		if (usuario instanceof DtAsistente) {
        		port.registrarAsistencia(usuario.getNickname(),edicion);
        }
		
    	response.sendRedirect(request.getContextPath() + "/usuarios/consultaRegistroAsis?evento=" + URLEncoder.encode(evento, StandardCharsets.UTF_8) + "&edicion=" + URLEncoder.encode(edicion, StandardCharsets.UTF_8));

    }

    protected void consultaUsuarioGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicadorUsuarioService service = new PublicadorUsuarioService();
        PublicadorUsuario port = service.getPublicadorUsuarioPort();
     	String nick = (String) request.getParameter("nick");
    	if (nick != null && port.existeUsuario(nick)) {
    		DtUsuario usuario = port.elegirUsuario(nick);
        	
        	if (usuario instanceof DtAsistente) {
        		List<DtRegistro> registros = port.consultaAsistente().getItem();
        		request.setAttribute("registros", registros);
        	}
        	
    		request.setAttribute("datos", usuario);
        	request.getRequestDispatcher("/WEB-INF/usuarios/consulta.jsp").forward(request, response);
    	} else {
    		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		response.getWriter().println("404 - Página no encontrada");
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Categoria.listarCategorias(request, response);
		String servletPath = request.getServletPath();
	    if (servletPath.equals("/usuarios")) {
				String path = request.getPathInfo();
		        if (path.equals("/registrosNoConf")) {
		        	regNCGET(request, response);
	    		} else if (path.equals("/inicio_sesion")) {
					inicioSesionGET(request, response);
	    		} else if (path.equals("/consulta")) {
					consultaUsuarioGET(request, response);
		        } else if (path.equals("/consultaRegistroAsis")) {
		        	consultaRegistroGET(request, response);
		        } else if (path.equals("/fin_sesion")) {
		        	HttpSession session = request.getSession(false);
		            if (session != null) {
		                session.invalidate();
		            }
		            response.sendRedirect("/webmovil/");
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
		        if (path.equals("/inicio_sesion")) {
					inicioSesionPOST(request, response);
		        } else if (path.equals("/registrarAsistencia")) {
					confirmarAsistenciaPOST(request, response);
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
