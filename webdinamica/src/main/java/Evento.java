import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import excepciones.PatrocinioSuperaMontoException;
import excepciones.YaExistePatrocinioException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import logica.ControladorEvento;
import logica.ControladorUsuario;
import logica.DTAsistente;
import logica.DTEdicion;
import logica.DTEvento;
import logica.DTOrganizador;
import logica.DTPatrocinio;
import logica.DTTRegistro;
import logica.DTUsuario;
import logica.TipoNivelPatrocinio;

/**
 * Servlet implementation class Evento
 */

@WebServlet({"/eventos/*", "/ediciones/*", "/patrocinios/*", "/TipoRegistro/*"})
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
		ControladorEvento c = ControladorEvento.getInstance();

		// Cargar eventos

		String categoria = request.getParameter("categoria");
		List<DTEvento> eventos;
		if (categoria != null && !categoria.trim().isEmpty()) {
			eventos = c.listarDTEventosCategoriaNoFinalizados(categoria);
		} else {
			eventos = c.listarDTEventosNoFinalizados();
		}
		
		request.setAttribute("eventos", eventos);
		request.setAttribute("categoriaSeleccionada", categoria); 

		// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/eventos/index.jsp").forward(request, response);
	}

	// GET /patrocinios/alta
	protected void altaPatrocinioGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorEvento c = ControladorEvento.getInstance();

		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");

		// Si el evento/edición seleccionados no existen o la edición
		// no existe para ese evento, redirigir a página principal
		if (!c.existeEdicionEvento(evento, edicion)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		DTEdicion datosEdicion = c.obtenerEdicion(edicion, evento);
		HttpSession userSession = request.getSession(false);
		DTUsuario usuarioLoggeado = (DTUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// No permitir que asistentes o visitantes accedan, ni organizadores
		// de una edición distinta a la actual.
		if (usuarioLoggeado == null) {
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		} else if (usuarioLoggeado.getNickname() != datosEdicion.getOrganizador() || !(usuarioLoggeado instanceof DTOrganizador)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		List<String> instituciones = 
				c.listarInstituciones()
				.stream()
				.filter(e -> !c.listarInstituciones(evento, edicion).contains(e))
				.collect(Collectors.toList());
		request.setAttribute("instituciones", instituciones);

		// Redirigir a ConsultaEdición si la edición ya
		// está patrocinada por todas las instituciones
		if (instituciones.equals(List.of())) {
			request.setAttribute("error", "La edición ya está patrocinada por todas las instituciones en el sistema.");
			consultaEdicionGET(request, response);
			return;
		}

		List<String> tipoRegistros = c.listarTipoRegistro(evento, edicion);
		request.setAttribute("tipoRegistros", tipoRegistros);

		// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/patrocinios/alta.jsp").forward(request, response);
	}

	// POST /patrocinios/alta
	// Crea un patrocinio con los parámetros pasados
	protected void altaPatrocinioPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorEvento c = ControladorEvento.getInstance();

		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");
		String institucion = request.getParameter("institucion");
		TipoNivelPatrocinio nivel = TipoNivelPatrocinio.valueOf(request.getParameter("nivel"));
		float monto = Float.parseFloat(request.getParameter("monto"));
		int cantRegistros = Integer.parseInt(request.getParameter("cantRegistros"));
		String tipoRegistro = request.getParameter("tipoRegistro");
		String codigo = request.getParameter("codigo");

		DTEdicion datosEdicion = c.obtenerEdicion(edicion, evento);
		HttpSession userSession = request.getSession(false);
		DTUsuario usuarioLoggeado = (DTUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// Si el evento/edición seleccionados no existen o la edición
		// no existe para ese evento, redirigir a página principal
		if (!c.existeEdicionEvento(evento, edicion)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		// No permitir que asistentes o visitantes accedan, ni organizadores
		// de una edición distinta a la actual.
		if (usuarioLoggeado == null) {
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		} else if (usuarioLoggeado.getNickname() != datosEdicion.getOrganizador() || !(usuarioLoggeado instanceof DTOrganizador)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		DTPatrocinio dtp = new DTPatrocinio(codigo, nivel, monto, tipoRegistro, cantRegistros, LocalDate.now(), institucion, null);

		try {
			c.crearPatrocinio(dtp, edicion, institucion, evento);
		} catch(PatrocinioSuperaMontoException
				| YaExistePatrocinioException
				| IllegalArgumentException ex) {
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
		ControladorEvento c = ControladorEvento.getInstance();

		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");
		String institucion = request.getParameter("institucion");

		// Si el evento/edición seleccionados no existen o la edición
		// no existe para ese evento, o la institución no existe
		// para la edición, redirigir a página principal
		if (!c.existeEdicionEvento(evento, edicion)
				|| !c.listarInstituciones(evento, edicion).contains(institucion)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		DTPatrocinio dtp = c.consultaPatrocinioInstitucion(evento, edicion, institucion);
		request.setAttribute("dtp", dtp);

		// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/patrocinios/consulta.jsp").forward(request, response);
	}

	// GET /eventos/alta
	// Carga las categorías existentes en el formulario
	protected void altaEventoGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorEvento c = ControladorEvento.getInstance();

		HttpSession userSession = request.getSession(false);
		DTUsuario usuarioLoggeado = (DTUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// No permitir que asistentes o visitantes accedan
		if (usuarioLoggeado == null) {
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		} else if (!(usuarioLoggeado instanceof DTOrganizador)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		List<String> categorias = c.listarCategorias();
		request.setAttribute("categorias", categorias);

		// Mostrar el HTML
		request.getRequestDispatcher("/WEB-INF/eventos/alta.jsp").forward(request, response);
	}

	// POST /eventos/alta
	// Crea un evento con los parámetros pasados
	protected void altaEventoPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession(false);
		DTUsuario usuarioLoggeado = (DTUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// No permitir que asistentes o visitantes accedan
		if (usuarioLoggeado == null) {
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		} else if (!(usuarioLoggeado instanceof DTOrganizador)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("desc");
		String sigla = request.getParameter("sigla");
		String[] categoriasA = request.getParameterValues("categorias");
		List<String> categorias = (categoriasA != null)
				? Arrays.asList(categoriasA)
						: List.of();
		// Obtener imagen
		Part filePart = request.getPart("imagen");
		boolean subioImagen = filePart != null && filePart.getSize() > 0;

		ControladorEvento c = ControladorEvento.getInstance();
		boolean ok;

		if (subioImagen) {
			// Generar un nombre aleatorio para la imagen,
			// guardarla en una carpeta temporal
			String fileName = UUID.randomUUID().toString() + "_" + filePart.getSubmittedFileName();
			String uploadPath = getServletContext().getRealPath("/uploads");
			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists()) uploadDir.mkdir();

			filePart.write(uploadPath + File.separator + fileName);

			ok = c.ingresarDatosEvento(nombre, descripcion, sigla, LocalDate.now(), categorias, fileName, false);
		} else {
			ok = c.ingresarDatosEvento(nombre, descripcion, sigla, LocalDate.now(), categorias, false);
		}

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

		LocalDate fechaAlta = LocalDate.now();

		HttpSession user = request.getSession(false);
		DTUsuario usuario = (DTUsuario) user.getAttribute("usuario");
		String nicknameOrganizador = usuario.getNickname();

		// Obtener imagen
		Part filePart = request.getPart("imagen");
		boolean subioImagen = filePart != null && filePart.getSize() > 0;

		String fileName = null;

		if (subioImagen) {
			// Generar un nombre aleatorio para la imagen,
			// guardarla en una carpeta temporal
			fileName = UUID.randomUUID().toString() + "_" + filePart.getSubmittedFileName();
			String uploadPath = getServletContext().getRealPath("/uploads");
			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists()) uploadDir.mkdir();

			filePart.write(uploadPath + File.separator + fileName);
		}

		ControladorEvento c = ControladorEvento.getInstance();
		boolean ok = c.altaEdicion(nombreEvento, nicknameOrganizador, nombre, sigla, fechaInicio, fechaFin, fechaAlta, ciudad, pais, fileName);

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

		ControladorEvento c = ControladorEvento.getInstance();

		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");

		if (!c.existeEdicionEvento(evento, edicion)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		DTEdicion datosEdicion = c.obtenerEdicion(edicion, evento);

		// 1. Obtener el usuario de la sesión
		HttpSession session = request.getSession(false);
		Object usuario = (session != null) ? session.getAttribute("usuario") : null;

		boolean estaRegistrado = false;

		// 2. Verificar si el usuario logueado es un Asistente y si está registrado
		if (usuario instanceof DTAsistente) {
			DTAsistente asistente = (DTAsistente) usuario;
			String nickname = asistente.getNickname();

			estaRegistrado = c.usuarioEnEdicion(edicion, nickname, evento);
		}

		request.setAttribute("estaRegistrado", estaRegistrado);

		// 5. Establecer los atributos de los datos de la edición
		String pais = datosEdicion.getPais();
		request.setAttribute("pais", pais);

		String sigla = datosEdicion.getSigla();
		request.setAttribute("sigla", sigla);

		String ciudad = datosEdicion.getCiudad();
		request.setAttribute("ciudad", ciudad);

		LocalDate fechaIni = datosEdicion.getFechaInicio();
		request.setAttribute("fecha-ini", fechaIni);

		LocalDate fechaFin = datosEdicion.getFechaFin();
		request.setAttribute("fecha-fin", fechaFin);

		LocalDate fechaAlta = datosEdicion.getFechaAlta();
		request.setAttribute("fecha-alta", fechaAlta);

		List<String> tiposRegistros = datosEdicion.getTipoRegistros();
		request.setAttribute("tiposRegistros", tiposRegistros);

		String organizador = datosEdicion.getOrganizador();
		request.setAttribute("organizador", organizador); // El nickname del organizador de ESTA Edición

		String imagen = datosEdicion.getImagen();
		request.setAttribute("imagen", imagen);

		List<String> instituciones = c.listarInstituciones(evento, edicion);
		request.setAttribute("instituciones", instituciones);

		// No se muestra el botón de crear patrocinio si la edición ya
		// está patrocinada por todas las instituciones
		boolean tieneTodasLasInstituciones = new HashSet<>(c.listarInstituciones()).equals(new HashSet<>(instituciones));
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

		ControladorEvento c = ControladorEvento.getInstance();
		
		HttpSession userSession = request.getSession(false);
		DTUsuario usuario = (DTUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// No permitir que organizadores o visitantes accedan
		if (usuario == null) {
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		} else if (!(usuario instanceof DTAsistente)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		if (!c.existeEdicionEvento(nombreEvento, nombreEdicion)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		List<String> tiposRegistro = c.listarTipoRegistro(nombreEvento, nombreEdicion);
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
		LocalDate fechaAlta = LocalDate.now();
		String codigoPatrocinio = request.getParameter("patrocinio"); // Renombrado para claridad

		HttpSession userSession = request.getSession(false);
		DTUsuario usuarioLoggeado = (DTUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);

		// No permitir que organizadores o visitantes accedan
		if (usuarioLoggeado == null) {
			response.sendRedirect("/webdinamica/usuarios/inicio_sesion");
			return;
		} else if (!(usuarioLoggeado instanceof DTAsistente)) {
			response.sendRedirect("/webdinamica");
			return;
		}
		
		DTAsistente usuario = (DTAsistente) usuarioLoggeado;

		String nickname = usuario.getNickname();
		String nombreInstitucion = usuario.getInstitucion();

		ControladorEvento c = ControladorEvento.getInstance();

		boolean conPatrocinio = false;
		String mensajeError = null;

		// A) Lógica de Validación de Patrocinio
		if (codigoPatrocinio != null && !codigoPatrocinio.trim().isEmpty()) {
			try {
			    c.patrocinioEdicion(codigoPatrocinio, nombreEdicion, evento, nombreTRegistro, nombreInstitucion);
			} catch (IllegalArgumentException | NoSuchElementException | IllegalStateException exc) {
			    mensajeError = exc.getMessage();
			}
			
			conPatrocinio = true;
		}

		// B) Si no hubo error en el patrocinio (o si no se usó) -> Proceder al alta
		if (mensajeError == null) {
			if (c.darDeAltaRegistro(nickname, nombreEdicion, nombreTRegistro, evento, fechaAlta, conPatrocinio)) {
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
			List<String> tiposRegistro = c.listarTipoRegistro(evento, nombreEdicion);
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

		ControladorEvento c = ControladorEvento.getInstance();

		String evento = request.getParameter("evento");

		if (!c.listarEventos().contains(evento)) {
			response.sendRedirect("/webdinamica");
			return;
		}

		//DTEvento datosEvento = c.informacionEvento(evento);
		DTEvento datosEvento = c.informacionEventoEdAceptadas(evento);

		String descripcion = datosEvento.getDescripcion();
		request.setAttribute("descripcion", descripcion);

		String sigla = datosEvento.getSigla();
		request.setAttribute("sigla", sigla);

		LocalDate fechaAlta = datosEvento.getFechaAlta();
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
		ControladorEvento c = ControladorEvento.getInstance();

		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");

		// Si el evento/edición seleccionados no existen o la edición
		// no existe para ese evento, redirigir a página principal
		if (!c.existeEdicionEvento(evento, edicion)) {
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

		ControladorEvento c = ControladorEvento.getInstance();
		if (!c.existeEdicionEvento(evento, edicion) || !c.listarTipoRegistro(evento, edicion).contains(tregistro)) {
			response.sendRedirect("/webdinamica");
			return;

		}
		DTTRegistro dtr = c.mostrarTipoRegistro(evento, edicion, tregistro);
		request.setAttribute("tipoRegistro", dtr);

		request.getRequestDispatcher("/WEB-INF/eventos/consultaTipoRegistro.jsp").forward(request, response);
	}

	// POST /edicion/alta
	// Crea una edicion con los parámetros pasados
	protected void altaTipoRegistroPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreEvento = request.getParameter("nombreEvento");
		String nombreEdicion = request.getParameter("nombreEdicion");
		String nombre = request.getParameter("nombre");
		String desc = request.getParameter("descripcion");
		float costo = Float.parseFloat(request.getParameter("costo"));
		int cupo = Integer.parseInt(request.getParameter("cupo"));

		ControladorEvento c = ControladorEvento.getInstance();

		boolean ok = c.crearTipoRegistro(nombreEvento, nombreEdicion, nombre, desc, costo, cupo);

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
		
	
	//######  FINALIZAR EVENTO  ######

		// GET /eventos/finalizarEvento
		// Permite finalizar un evento
		protected void finalizarEventoGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    // 1. Obtener el controlador
		    ControladorUsuario c = ControladorUsuario.getInstance();
		    ControladorEvento ce = ControladorEvento.getInstance();
	
		    // 2. Obtener la sesión y el usuario (se mantiene la lógica original)
		    HttpSession userSession = request.getSession(false);
		    DTUsuario usuario = (DTUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);
		    
		    // 3. Obtener todas las ediciones del Organizador
		    List<DTEdicion> ediciones = c.consultaOrganizador();
		    
		    // 4. Crear un Set para almacenar nombres de eventos únicos
		    Set<String> eventosUnicos = new HashSet<>();
		    
		    // 5. Recorrer las ediciones para extraer los nombres de eventos únicos
		    for (DTEdicion edicion : ediciones) {
		    	DTEvento ev = ce.informacionEvento(edicion.getEvento());
		    	if (ev.getFinalizado() == false) {
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
			ControladorEvento c = ControladorEvento.getInstance();

		    // 1. Obtener el parámetro del formulario (nombreEvento)
		    String nombreEvento = request.getParameter("nombreEvento");
		    
		    c.finalizarEvento(nombreEvento);
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
		} else {

			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().println("404 - Página no encontrada");
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
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().println("404 - Página no encontrada");
		}
	}

}

