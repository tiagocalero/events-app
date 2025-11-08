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

	// GET /patrocinios/alta
	protected void altaPatrocinioGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();

		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");

		// Si el evento/edición seleccionados no existen o la edición
		// no existe para ese evento, redirigir a página principal
		if (evento == null || edicion == null || !port.existeEdicionEvento(evento, edicion)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		DtEdicion datosEdicion = port.obtenerEdicion(edicion, evento);
		HttpSession userSession = request.getSession(false);
		DtUsuario usuarioLoggeado = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// No permitir que asistentes o visitantes accedan, ni organizadores
		// de una edición distinta a la actual.
		if (usuarioLoggeado == null) {
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		} else if (!usuarioLoggeado.getNickname().equals(datosEdicion.getOrganizador())) {
			response.sendRedirect("/webdinamica/");
			return;
		}

		List<String> instituciones = 
				port.listarInstituciones()
				.getItem()
				.stream()
				.filter(e -> !port.listarInstitucionesEdicionEvento(evento, edicion).getItem().contains(e))
				.collect(Collectors.toList());
		request.setAttribute("instituciones", instituciones);

		// Redirigir a ConsultaEdición si la edición ya
		// está patrocinada por todas las instituciones
		if (instituciones.equals(List.of())) {
			request.setAttribute("error", "La edición ya está patrocinada por todas las instituciones en el sistema.");
			consultaEdicionGET(request, response);
			return;
		}

		List<String> tipoRegistros = port.listarTipoRegistro(evento, edicion).getItem();
		request.setAttribute("tipoRegistros", tipoRegistros);

		// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/patrocinios/alta.jsp").forward(request, response);
	}

	// POST /patrocinios/alta
	// Crea un patrocinio con los parámetros pasados
	protected void altaPatrocinioPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();

		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");
		String institucion = request.getParameter("institucion");
		TipoNivelPatrocinio nivel = TipoNivelPatrocinio.valueOf(request.getParameter("nivel").toUpperCase());
		float monto = Float.parseFloat(request.getParameter("monto"));
		int cantRegistros = Integer.parseInt(request.getParameter("cantRegistros"));
		String tipoRegistro = request.getParameter("tipoRegistro");
		String codigo = request.getParameter("codigo");

		DtEdicion datosEdicion = port.obtenerEdicion(edicion, evento);
		HttpSession userSession = request.getSession(false);
		DtUsuario usuarioLoggeado = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// Si el evento/edición seleccionados no existen o la edición
		// no existe para ese evento, redirigir a página principal
		if (evento == null || edicion == null || !port.existeEdicionEvento(evento, edicion)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		// No permitir que asistentes o visitantes accedan, ni organizadores
		// de una edición distinta a la actual.
		if (usuarioLoggeado == null) {
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		} else if (!usuarioLoggeado.getNickname().equals(datosEdicion.getOrganizador())) {
			response.sendRedirect("/webdinamica");
			return;
		}
		
	    DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;

		try {
			port.crearPatrocinio(codigo, nivel, tipoRegistro, cantRegistros, monto, LocalDate.now().format(fmt), edicion, institucion, evento);
		} catch(AltaPatrocinioException_Exception ex) {
			request.setAttribute("error", ex.getMessage());

			// Se incluyen todos los atributos para que se vuelvan a cargar y
			// que el usuario no tenga que llenarlos de vuelta
			request.setAttribute("institucion", institucion);
			request.setAttribute("nivel", nivel.toString());
			request.setAttribute("monto", monto);
			request.setAttribute("cantRegistros", cantRegistros);
			request.setAttribute("tipoRegistro", tipoRegistro);
			request.setAttribute("codigo", codigo);
		}

		// Volver a AltaPatrocinio
		altaPatrocinioGET(request, response);
	}

	// GET /patrocinios
	// POST /patrocinios
	protected void consultaPatrocinio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();

		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");
		String institucion = request.getParameter("institucion");

		// Si el evento/edición seleccionados no existen o la edición
		// no existe para ese evento, o la institución no existe
		// para la edición, redirigir a página principal
		if (evento == null || edicion == null 
				|| !port.existeEdicionEvento(evento, edicion)
				|| !port.listarInstitucionesEdicionEvento(evento, edicion).getItem().contains(institucion)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		DtPatrocinio dtp = port.consultaPatrocinioInstitucion(evento, edicion, institucion);
		request.setAttribute("dtp", dtp);

		// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/patrocinios/consulta.jsp").forward(request, response);
	}

	// GET /eventos/alta
	// Carga las categorías existentes en el formulario
	protected void altaEventoGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();

		HttpSession userSession = request.getSession(false);
		DtUsuario usuarioLoggeado = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// No permitir que asistentes o visitantes accedan
		if (usuarioLoggeado == null) {
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		} else if (!(usuarioLoggeado instanceof DtOrganizador)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		List<String> categorias = port.listarCategorias().getItem();
		request.setAttribute("categorias", categorias);

		// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/eventos/alta.jsp").forward(request, response);
	}

	// POST /eventos/alta
	// Crea un evento con los parámetros pasados
	protected void altaEventoPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession(false);
		DtUsuario usuarioLoggeado = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// No permitir que asistentes o visitantes accedan
		if (usuarioLoggeado == null) {
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		} else if (!(usuarioLoggeado instanceof DtOrganizador)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("desc");
		String sigla = request.getParameter("sigla");
		String[] categoriasA = request.getParameterValues("categorias");
		StringArray categorias = new StringArray();
		categorias.getItem().addAll(Arrays.asList(categoriasA));
		
		// Obtener imagen
		Part filePart = request.getPart("imagen");
		boolean subioImagen = filePart != null && filePart.getSize() > 0;
    	String fileName = "";
    	
		if (subioImagen) {
			// Generar un nombre aleatorio para la imagen,
			// guardarla en una carpeta temporal
			fileName = UUID.randomUUID().toString() + "_" + filePart.getSubmittedFileName();
			String uploadPath = getServletContext().getRealPath("/uploads");
			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists()) uploadDir.mkdir();

			filePart.write(uploadPath + File.separator + fileName);
		}

    	DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
		PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();
        
		boolean ok = port.ingresarDatosEvento(nombre, descripcion, sigla, LocalDate.now().format(fmt), categorias, fileName);

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

	//######  ALTA EDICION  ######

	// GET /ediciones/altaEdicion
	// Llama a la ventana de altaEdicion
	protected void altaEdicionGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreEvento = request.getParameter("evento");

		request.setAttribute("nombreEvento", nombreEvento);

		// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/eventos/altaEdicion.jsp").forward(request, response);
	}

	// POST /ediciones/altaEdicion
	// Crea una edicion con los parámetros pasados
	protected void altaEdicionPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("name");
		String ciudad = request.getParameter("ciudad");
		String sigla = request.getParameter("sigla");
		String pais = request.getParameter("pais");
		LocalDate fechaInicio = LocalDate.parse(request.getParameter("fecha-inicio"));
		LocalDate fechaFin = LocalDate.parse(request.getParameter("fecha-fin"));
		String nombreEvento = request.getParameter("evento");
		String video = request.getParameter("video-url");
		LocalDate fechaAlta = LocalDate.now();

		HttpSession user = request.getSession(false);
		DtUsuario usuario = (DtUsuario) user.getAttribute("usuario");
		String nicknameOrganizador = usuario.getNickname();

		// Obtener imagen
		Part filePart = request.getPart("imagen");
		boolean subioImagen = filePart != null && filePart.getSize() > 0;

		String fileName = "";

		if (subioImagen) {
			// Generar un nombre aleatorio para la imagen,
			// guardarla en una carpeta temporal
			fileName = UUID.randomUUID().toString() + "_" + filePart.getSubmittedFileName();
			String uploadPath = getServletContext().getRealPath("/uploads");
			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists()) uploadDir.mkdir();

			filePart.write(uploadPath + File.separator + fileName);
		}

		PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();
        
        boolean ok = port.altaEdicion(nombreEvento, nicknameOrganizador, nombre, sigla, fechaInicio.format(FORMATTER), fechaFin.format(FORMATTER), fechaAlta.format(FORMATTER), ciudad, pais, fileName, video);

		if (!ok) {
			request.setAttribute("name", nombre);
			request.setAttribute("ciudad", ciudad);
			request.setAttribute("sigla", sigla);
			request.setAttribute("pais", pais); 
			request.setAttribute("fecha-inicio", fechaInicio);
			request.setAttribute("fecha-fin", fechaFin);

			request.setAttribute("nombreEvento", nombreEvento); 

			request.setAttribute("error", "El nombre de la edición '" + nombre + "' ya existe.");

			request.getRequestDispatcher("/WEB-INF/eventos/altaEdicion.jsp").forward(request, response);
		} else {
			consultaEventoGET(request, response);
		}
	}

	//######  CONSULTA EDICION  ######

	// GET /ediciones/consultaEdicion
	// Carga las categorías existentes en el formulario
	protected void consultaEdicionGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublicadorEventoService service = new PublicadorEventoService();
        PublicadorEvento port = service.getPublicadorEventoPort();	
        
		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");

		if (!port.existeEdicionEvento(evento, edicion)) {
			response.sendRedirect("/webdinamica");
			return;
		}
		
        System.out.println("DEBUG SERVER: Intentando buscar Evento con nombre: [" + evento + "]");

		DtEdicion datosEdicion = port.obtenerEdicion(edicion, evento);
        
		// 1. Obtener el usuario de la sesión
		HttpSession user = request.getSession(false);
		DtUsuario usuario = (DtUsuario) user.getAttribute("usuario");

		boolean estaRegistrado = false;

		// 2. Verificar si el usuario logueado es un Asistente y si está registrado
		if (usuario instanceof DtAsistente) {
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
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		} else if (!(usuario instanceof DtAsistente)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		if (!port.existeEdicionEvento(nombreEvento, nombreEdicion)) {
			response.sendRedirect("/webdinamica");
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
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		} else if (!(usuarioLoggeado instanceof DtAsistente)) {
			response.sendRedirect("/webdinamica");
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
			response.sendRedirect("/webdinamica");
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
			response.sendRedirect("/webdinamica");
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
			response.sendRedirect("/webdinamica");
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

		// GET /eventos/finalizarEvento
		// Permite finalizar un evento
		protected void finalizarEventoGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PublicadorEventoService service = new PublicadorEventoService();
	        PublicadorEvento portE = service.getPublicadorEventoPort();
	        
	        PublicadorUsuarioService serviceU = new PublicadorUsuarioService();
	        PublicadorUsuario portU = serviceU.getPublicadorUsuarioPort();
	
		    // 2. Obtener la sesión y el usuario (se mantiene la lógica original)
		    HttpSession userSession = request.getSession(false);
		    DtUsuario usuario = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);
		    
		    // 3. Obtener todas las ediciones del Organizador
		    List<DtEdicion> ediciones = portU.consultaOrganizador().getItem();
		    
		    // 4. Crear un Set para almacenar nombres de eventos únicos
		    Set<String> eventosUnicos = new HashSet<>();
		    
		    // 5. Recorrer las ediciones para extraer los nombres de eventos únicos
		    for (DtEdicion edicion : ediciones) {
		    	DtEvento ev = portE.informacionEvento(edicion.getEvento());
		    	if (ev.isFinalizado() == false) {
		    		eventosUnicos.add(edicion.getEvento()); 
		    	}
		    }
		    
		    // 6. Convertir el Set (único) a una List para enviarlo al JSP
		    List<String> eventos = new ArrayList<>(eventosUnicos);
		    
		    // 7. Establecer los atributos para el JSP
		    // Se envía la lista de ediciones por si el JSP la necesita (e.g., para el sidebar).
		    request.setAttribute("ediciones", ediciones);
		    
		    // Se envía la nueva lista de nombres de eventos únicos.
		    request.setAttribute("eventosCreados", eventos);
		    
		    String nombreEvento = request.getParameter("nombreEvento");
		    request.setAttribute("evento", nombreEvento);
		    
		    // 8. Hacer el forward al JSP
		    request.getRequestDispatcher("/WEB-INF/eventos/finalizarEvento.jsp").forward(request, response);
		}
		
		protected void finalizarEventoPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PublicadorEventoService service = new PublicadorEventoService();
	        PublicadorEvento portE = service.getPublicadorEventoPort();

		    // 1. Obtener el parámetro del formulario (nombreEvento)
		    String nombreEvento = request.getParameter("nombreEvento");
		    
		    portE.finalizarEvento(nombreEvento);
		    finalizarEventoGET(request, response);
		}


	//############################# 
		
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath(); // Obtiene /evento o /ediciones
		Categoria.listarCategorias(request, response);

		if (servletPath.equals("/eventos")) {

			String path = request.getPathInfo();

			if (path == null || path.equals("/")) {
				listarEventos(request, response);
			} else if (path.equals("/consulta")) {
				response.getWriter().println("Página de consulta");
			} else if (path.equals("/alta")) {
				altaEventoGET(request, response);
			} else if (path.equals("/consultaEvento")) {
				consultaEventoGET(request, response);
			} else if (path.equals("/finalizarEvento")) {
				finalizarEventoGET(request, response);
			}
			else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().println("404 - Página no encontrada");
			}

		} else if (servletPath.equals("/ediciones")) {

			String path = request.getPathInfo();

			if (path.equals("/altaEdicion")) {

				altaEdicionGET(request, response);

			} else if (path.equals("/consultaEdicion")){

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
			} else if (path.equals("/alta")) {
				altaPatrocinioGET(request, response);
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
			} else if (path.equals("/alta")) {
				altaEventoPOST(request, response);
			} else if (path.equals("/finalizarEvento")) {
				finalizarEventoPOST(request, response);
			}else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().println("404 - Página no encontrada");
			}
		} else if (servletPath.equals("/ediciones")) {

			String path = request.getPathInfo();

			if (path.equals("/altaEdicion")) {
				altaEdicionPOST(request, response);

			} else if (path.equals("/registroAEdicionEvento")) {

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
			} else if (path.equals("/alta")) {
				altaPatrocinioPOST(request, response);
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
