package servlets;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import main.java.publicar.DtAsistente;
import main.java.publicar.DtEdicion;
import main.java.publicar.DtEvento;
import main.java.publicar.DtOrganizador;
import main.java.publicar.DtPatrocinio;
import main.java.publicar.DtUsuario;
import main.java.publicar.DttRegistro;
import main.java.publicar.AltaPatrocinioException_Exception;
import main.java.publicar.PublicadorEvento;
import main.java.publicar.PublicadorEventoService;
import main.java.publicar.PublicadorUsuario;
import main.java.publicar.PublicadorUsuarioService;
import main.java.publicar.RegistroAEdicionException_Exception;
import main.java.publicar.TipoNivelPatrocinio;
import net.java.dev.jaxb.array.StringArray;

/**
 * Servlet implementation class Evento
 */

@WebServlet({"/eventos/*", "/ediciones/*", "/patrocinios/*", "/TipoRegistro/*", "/busqueda"})
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,     // 1 MB
		maxFileSize = 1024 * 1024 * 10,      // 10 MB
		maxRequestSize = 1024 * 1024 * 50    // 50 MB
		)
public class Evento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Evento() {
		super();
		// TODO Auto-generated constructor stub
	}

	// GET /eventos
	protected void listarEventos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession(false);
		DtUsuario usuarioLoggeado = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// No permitir que asistentes o organizadores accedan
		if (usuarioLoggeado == null || !(usuarioLoggeado instanceof DtAsistente)) {
			response.sendRedirect("/webmovil/usuarios/inicio_sesion");
			return;
		}

		// Cargar eventos
        PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();

		String categoria = request.getParameter("categoria");
		List<DtEvento> eventos;
		if (categoria != null && !categoria.trim().isEmpty()) {
			eventos = port.listarDTEventosCategoriaNoFinalizados(categoria).getItem();
		} else {
			eventos = port.listarDTEventosNoFinalizados().getItem();		
		} 
		
		request.setAttribute("eventos", eventos);
		request.setAttribute("categoriaSeleccionada", categoria);

		// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/eventos/index.jsp").forward(request, response);
	}
	
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

	//######  CONSULTA EDICION  ######

	// GET /ediciones/consultaEdicion
	// Carga las categorías existentes en el formulario
	protected void consultaEdicionGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();	
        
		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");

		if (!port.existeEdicionEvento(evento, edicion)) {
			response.sendRedirect("/webmovil");
			return;
		}
		
        System.out.println("DEBUG SERVER: Intentando buscar Evento con nombre: [" + evento + "]");

		DtEdicion datosEdicion = port.obtenerEdicion(edicion, evento);
        
		// 1. Obtener el usuario de la sesión
		HttpSession userSession = request.getSession(false);
		DtUsuario usuario = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		boolean estaRegistrado = false;

		// 2. Verificar si el usuario logueado es un Asistente y si está registrado
		if (usuario == null) {
			response.sendRedirect("/webmovil/usuarios/inicio_sesion");
			return;
		} else if (usuario instanceof DtAsistente) {
			DtAsistente asistente = (DtAsistente) usuario;
			String nickname = asistente.getNickname();
			
			estaRegistrado = port.usuarioEnEdicion(edicion, nickname, evento);
		}

		request.setAttribute("estaRegistrado", estaRegistrado);

		// 5. Establecer los atributos de los datos de la edición
		String pais = datosEdicion.getPais();
		request.setAttribute("pais", pais);

		String sigla = datosEdicion.getSigla();
		request.setAttribute("sigla", sigla);

		String ciudad = datosEdicion.getCiudad();
		request.setAttribute("ciudad", ciudad);
		
		LocalDate fechaIni = LocalDate.parse(datosEdicion.getFechaInicio(), FORMATTER);
		request.setAttribute("fecha-ini", fechaIni);

		LocalDate fechaFin = LocalDate.parse(datosEdicion.getFechaFin(), FORMATTER);
		request.setAttribute("fecha-fin", fechaFin);

		LocalDate fechaAlta = LocalDate.parse(datosEdicion.getFechaAlta(), FORMATTER);
		request.setAttribute("fecha-alta", fechaAlta);

		List<String> tiposRegistros = datosEdicion.getTRegistros();
		request.setAttribute("tiposRegistros", tiposRegistros);

		String organizador = datosEdicion.getOrganizador();
		request.setAttribute("organizador", organizador); // El nickname del organizador de ESTA Edición

		String imagen = datosEdicion.getImagen();
		request.setAttribute("imagen", imagen);
		
		String video = datosEdicion.getVideo();
		request.setAttribute("video", video);

		List<String> instituciones = port.listarInstitucionesEdicionEvento(evento, edicion).getItem();
		request.setAttribute("instituciones", instituciones);

		// No se muestra el botón de crear patrocinio si la edición ya
		// está patrocinada por todas las instituciones
		boolean tieneTodasLasInstituciones = new HashSet<>(port.listarInstituciones().getItem()).equals(new HashSet<>(instituciones));
		request.setAttribute("tieneTodasLasInstituciones", tieneTodasLasInstituciones);

		// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/eventos/consultaEdicion.jsp").forward(request, response);
	}

	//######  REGISTRO A EDICION DE EVENTO  ######

	//GET
	//muestra la pantalla para registrarse
	protected void registroAEdicionEventoGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreEdicion = request.getParameter("edicion");
		String nombreEvento = request.getParameter("evento");

		PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();		
        
		HttpSession userSession = request.getSession(false);
		DtUsuario usuario = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// No permitir que organizadores o visitantes accedan
		if (usuario == null) {
			response.sendRedirect("/webmovil/usuarios/inicio_sesion");
			return;
		} else if (!(usuario instanceof DtAsistente)) {
			response.sendRedirect("/webmovil");
			return;
		}

		if (!port.existeEdicionEvento(nombreEvento, nombreEdicion)) {
			response.sendRedirect("/webmovil");
			return;
		}

		List<String> tiposRegistro = port.listarTipoRegistro(nombreEvento, nombreEdicion).getItem();
		request.setAttribute("tiposRegistro", tiposRegistro);

		request.setAttribute("nombreEvento", nombreEvento);
		request.setAttribute("nombreEdicion", nombreEdicion);

		// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/eventos/registroAEdicionEvento.jsp").forward(request, response);
	}

	//crea el registro al evento
	// POST
	protected void registroAEdicionEventoPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreEdicion = request.getParameter("edicion");
		String nombreTRegistro = request.getParameter("tiposRegistro");
		String evento = request.getParameter("evento");
		

		String codigoPatrocinio = request.getParameter("patrocinio"); // Renombrado para claridad

		HttpSession userSession = request.getSession(false);
		DtUsuario usuarioLoggeado = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// No permitir que organizadores o visitantes accedan
		if (usuarioLoggeado == null) {
			response.sendRedirect("/webmovil/usuarios/inicio_sesion");
			return;
		} else if (!(usuarioLoggeado instanceof DtAsistente)) {
			response.sendRedirect("/webmovil");
			return;
		}
		
		DtAsistente usuario = (DtAsistente) usuarioLoggeado;

		String nickname = usuario.getNickname();
		String nombreInstitucion = usuario.getInstitucion();

		PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();
        
		boolean conPatrocinio = false;
		String mensajeError = null;

		// A) Lógica de Validación de Patrocinio
		if (codigoPatrocinio != null && !codigoPatrocinio.trim().isEmpty()) {
			try {
			    port.patrocinioEdicion(codigoPatrocinio, nombreEdicion, evento, nombreTRegistro, nombreInstitucion);
			} catch (RegistroAEdicionException_Exception exc) {
			    mensajeError = exc.getMessage();
			}
			
			conPatrocinio = true;
		}
	    DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;

		// B) Si no hubo error en el patrocinio (o si no se usó) -> Proceder al alta
		if (mensajeError == null) {
			//le voy a poner en la fecha string como alta patrocinio, capaz hay que cambiar
			if (port.darDeAltaRegistro(nickname, nombreEdicion, nombreTRegistro, evento, LocalDate.now().format(fmt), conPatrocinio)) {
				mensajeError = "No hay cupos para el registro " + nombreTRegistro;
			}
		}

		// C) Manejo de Resultados
		if (mensajeError != null) {
			// Si hay error (validación de patrocinio O cupo/ya registrado):

			// 1. Guardar el mensaje de error en la solicitud (Request Scope)
			request.setAttribute("errorRegistro", mensajeError);

			// 2. Guardar datos para que el usuario pueda reintentar sin perderlos
			request.setAttribute("nombreEdicion", nombreEdicion);
			request.setAttribute("evento", evento);
			StringArray tiposRegistro = port.listarTipoRegistro(evento, nombreEdicion);
			request.setAttribute("tiposRegistro", tiposRegistro);
			request.setAttribute("nombreTRegistro", nombreTRegistro);
			// 3. Redirigir a una página de error o a la misma página de registro para mostrar el mensaje
			// Lo más limpio es redirigir a una página de *feedback*
			registroAEdicionEventoGET(request, response);

		} else {
			// Si fue exitoso:
			request.setAttribute("nombreEdicion", nombreEdicion);
			request.setAttribute("evento", evento);

			// Redirigir al perfil del usuario o a una página de confirmación de registro
			consultaEdicionGET(request, response);
		}
	}

	//######  CONSULTA EVENTO  ######

	// GET /ediciones/consultaEdicion
	// Carga las categorías existentes en el formulario
	protected void consultaEventoGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();


		String evento = request.getParameter("evento");
		
		if (!port.listarEventos().getItem().contains(evento)) {
			response.sendRedirect("/webmovil");
			return;
		}

		//DTEvento datosEvento = c.informacionEvento(evento);
		DtEvento datosEvento = port.informacionEventoEdAceptadas(evento);

		String descripcion = datosEvento.getDescripcion();
		request.setAttribute("descripcion", descripcion);

		String sigla = datosEvento.getSigla();
		request.setAttribute("sigla", sigla);
		
		String fechaAlta = datosEvento.getFechaAlta();
		request.setAttribute("fecha-alta", fechaAlta);

		List<String> categorias = datosEvento.getCategorias();
		request.setAttribute("categorias", categorias);

		List<String> ediciones = datosEvento.getEdiciones();
		request.setAttribute("ediciones", ediciones);

		String imagen = datosEvento.getImagen();
		request.setAttribute("imagen", imagen);

		// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/eventos/consultaEvento.jsp").forward(request, response);
	}

	protected void altaTipoRegistroGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();
        
		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");

		// Si el evento/edición seleccionados no existen o la edición
		// no existe para ese evento, redirigir a página principal
		if (!port.existeEdicionEvento(evento, edicion)) {
			response.sendRedirect("/webmovil");
			return;
		}

		// Mostrar el HTML

		request.getRequestDispatcher("/WEB-INF/eventos/altaTipoRegistro.jsp").forward(request, response);
	}

	protected void consultaTipoRegistro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");
		String tregistro = request.getParameter("tipoRegistro");

		PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();
        
		if (!port.existeEdicionEvento(evento, edicion) || !port.listarTipoRegistro(evento, edicion).getItem().contains(tregistro)) {
			response.sendRedirect("/webmovil");
			return;

		}
		DttRegistro dtr = port.mostrarTipoRegistro(evento, edicion, tregistro);
		request.setAttribute("tipoRegistro", dtr);

		request.getRequestDispatcher("/WEB-INF/eventos/consultaTipoRegistro.jsp").forward(request, response);
	}

	// POST /edicion/alta
	// Crea una edicion con los parámetros pasados
	protected void altaTipoRegistroPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreEvento = request.getParameter("evento");
		String nombreEdicion = request.getParameter("edicion");
		String nombre = request.getParameter("nombre");
		String desc = request.getParameter("descripcion");
		float costo = Float.parseFloat(request.getParameter("costo"));
		int cupo = Integer.parseInt(request.getParameter("cupo"));

		PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();
        
		boolean ok = port.crearTipoRegistro(nombreEvento, nombreEdicion, nombre, desc, costo, cupo);

		if (!ok) {
			request.setAttribute("nombreEvento", nombreEvento);
			request.setAttribute("nombreEdicion", nombreEdicion);

			request.setAttribute("nombre", nombre);
			request.setAttribute("descripcion", desc);
			request.setAttribute("costo", costo);
			request.setAttribute("cupo", cupo);

			request.setAttribute("error", "Ya existe un tipo registro con ese nombre");
			request.getRequestDispatcher("/WEB-INF/eventos/altaTipoRegistro.jsp").forward(request, response);
			return;
		}

		altaTipoRegistroGET(request, response);
	}
	
	protected void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();

        List<DtEvento> eventos = port.buscarEventos(request.getParameter("b")).getItem();
        List<DtEdicion> ediciones = port.buscarEdiciones(request.getParameter("b")).getItem();

        String ordenar = request.getParameter("ordenar");
        
        if (ordenar != null && ordenar.equals("az")) {
        	eventos.sort(Comparator.comparing((DtEvento e) -> e.getNombre()));
        	ediciones.sort(Comparator.comparing((DtEdicion e) -> e.getNombre()));
        } else if (ordenar != null && ordenar.equals("za")) {
        	eventos.sort(Comparator.comparing((DtEvento e) -> e.getNombre()).reversed());
        	ediciones.sort(Comparator.comparing((DtEdicion e) -> e.getNombre()).reversed());        	
        }
        
		request.setAttribute("eventos", eventos);
		request.setAttribute("ediciones", ediciones);

		request.getRequestDispatcher("/WEB-INF/eventos/busqueda.jsp").forward(request, response);
	}

	//############################# 
		
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath(); // Obtiene /evento o /ediciones
		System.out.println("Entrando al servlet Evento...");
		Categoria.listarCategorias(request, response);

		if (servletPath.equals("/eventos")) {

			String path = request.getPathInfo();

			if (path == null || path.equals("/")) {
				listarEventos(request, response);
			} else if (path.equals("/consulta")) {
				response.getWriter().println("Página de consulta");
			} else if (path.equals("/consultaEvento")) {
				consultaEventoGET(request, response);
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().println("404 - Página no encontrada");
			}

		} else if (servletPath.equals("/ediciones")) {

			String path = request.getPathInfo();

			if (path.equals("/consultaEdicion")){

				consultaEdicionGET(request, response);

			} else if (path.equals("/registroAEdicionEvento")) {

				registroAEdicionEventoGET(request, response);
			}
			else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().println("404 - Página no encontrada");
			}

		} else if (servletPath.equals("/patrocinios")) {
			String path = request.getPathInfo();

			if (path == null || path.equals("/")) {
				consultaPatrocinio(request, response);
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().println("404 - Página no encontrada");
			}
		} else if (servletPath.equals("/TipoRegistro")) {

			String path = request.getPathInfo();
			if (path.equals("/altaTipoRegistro")) {
				altaTipoRegistroGET(request, response);
			} else if (path.equals("/consultaTipoRegistro")){
				consultaTipoRegistro(request, response);

			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().println("404 - Página no encontrada");
			}
		} else if (servletPath.equals("/busqueda")) {
			buscar(request, response);
		} else {

			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().println("404 - Página no encontrada");
		}
	}
	
	protected void descargarConstanciaPOST(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    
	    // 1. OBTENER DATOS DEL REGISTRO Y ASISTENTE 
		
		PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();
        
	    // Datos enviados desde el JSP (por POST)
	    String nombreEvento = request.getParameter("evento");
	    String nombreEdicion = request.getParameter("edicion");
	    
        System.out.println("DEBUG SERVER: Intentando buscar Evento con nombre: [" + nombreEvento + "]");

        DtEdicion edicion = port.obtenerEdicion(nombreEdicion, nombreEvento);

	    // Obtener datos del asistente desde la sesión
	    HttpSession userSession = request.getSession(false);
	    DtUsuario dtu = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);
	    
	    
	    // Datos de ejemplo o simulacro (DEBES REEMPLAZAR POR LA LLAMADA A TU PUBLICADOR/WS)
	    // Ejemplo: PublicadorEventoPort port = service.getPublicadorEventoPort();
	    // String ciudad = port.obtenerCiudadEdicion(nombreEvento, nombreEdicion);
	    // String fechaEdicion = port.obtenerFechaEdicion(nombreEvento, nombreEdicion);
                        
	    String nombreAsistente = dtu.getNombre();
	    String ciudad = edicion.getCiudad();  
	    String fechaEdicionStr = edicion.getFechaInicio();

	    // 2. CONFIGURAR LA RESPUESTA HTTP PARA DESCARGA
	    response.setContentType("application/pdf");
	    String nombreArchivo = String.format("Constancia_%s_%s.pdf", 
	                                         nombreEdicion.replaceAll("\\s+", "_"), 
	                                         dtu.getNickname());
	    
	    // La cabecera Content-Disposition fuerza al navegador a descargar el archivo
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

	    // 3. GENERACIÓN DEL PDF CON iText 7
	    try (OutputStream out = response.getOutputStream()) {
	        
	    	// Inicializa el escritor de PDF y el documento
	        PdfWriter writer = new PdfWriter(out);
	        PdfDocument pdf = new PdfDocument(writer);
	        Document document = new Document(pdf);
	        
	        // --- Contenido del Documento ---

	        // Título del documento
	        Paragraph titulo = new Paragraph("CERTIFICADO DE ASISTENCIA")
	                .setBold() // Usar negrita sin cargar la fuente explícitamente
	                .setFontSize(22)	             
	                .setMarginBottom(40);
	        document.add(titulo);
	        
	        // Cuerpo del certificado
	        document.add(new Paragraph("Eventos.uy certifica la participación de:")
	                .setMarginBottom(15));
	        
	        // Información del Asistente
	        document.add(new Paragraph(String.format("Nombre Completo: %s", nombreAsistente))
	                .setBold()
	                .setMarginBottom(5).setFontSize(14));
	        
	        // Separador visual (Se reemplazó por una línea simple para mayor seguridad)
	        document.add(new Paragraph("--------------------------------------------------------------------------------------------------")
	                .setMarginBottom(20));

	        // Información del Evento/Edición
	        document.add(new Paragraph("En el evento y edición:")
	                .setMarginTop(10));
	        
	        document.add(new Paragraph(String.format("Evento: %s", nombreEvento))
	                .setBold());
	        
	        document.add(new Paragraph(String.format("Edición: %s", nombreEdicion))
	                .setBold());
	        
	        document.add(new Paragraph(String.format("Lugar: %s", ciudad)));
	        
	        document.add(new Paragraph(String.format("Fecha de la Edición: %s", fechaEdicionStr))
	                .setMarginBottom(40));
	        
	        // Fecha de Emisión del comprobante
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
	        String fechaActual = LocalDate.now().format(formatter);
	        
	        document.add(new Paragraph(String.format("Comprobante generado dinámicamente el %s.", fechaActual))
	                .setFontSize(10));
	                
	        
	        // Cierra el documento, forzando la escritura al OutputStream
	        document.close();
	        
	    }
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String servletPath = request.getServletPath(); // Obtiene /evento o /ediciones
		Categoria.listarCategorias(request, response);

		if (servletPath.equals("/eventos")) {

			String path = request.getPathInfo();

			if (path == null || path.equals("/")) {
				listarEventos(request, response);
			} else if (path.equals("/consulta")) {
				response.getWriter().println("Página de consulta");
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().println("404 - Página no encontrada");
			}
		} else if (servletPath.equals("/ediciones")) {

			String path = request.getPathInfo();

			if (path.equals("/registroAEdicionEvento")) {
				registroAEdicionEventoPOST(request, response);
			} else if (path != null && path.equals("/descargarConstancia")) {
	            descargarConstanciaPOST(request, response);
			}
			else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().println("404 - Página no encontrada");
			}
		} else if (servletPath.equals("/patrocinios")) {
			String path = request.getPathInfo();

			if (path == null || path.equals("/")) {
				consultaPatrocinio(request, response);
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().println("404 - Página no encontrada");
			}
		} else if (servletPath.equals("/TipoRegistro")) {
			String path = request.getPathInfo();
			if (path.equals("/altaTipoRegistro")) {
				altaTipoRegistroPOST(request, response);
			} else { 
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().println("404 - Página no encontrada");
			}
		} else if (servletPath.equals("/busqueda")) {
			buscar(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().println("404 - Página no encontrada");
		}
	}

}
