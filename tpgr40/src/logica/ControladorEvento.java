package logica;

import excepciones.PatrocinioSuperaMontoException;
import excepciones.YaExistePatrocinioException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControladorEvento {
	private static volatile ControladorEvento instance;
	private ControladorEvento() {}
	
	public static ControladorEvento getInstance() {
		if (instance == null) {
			synchronized (ControladorEvento.class) {
				if (instance == null) {
					instance = new ControladorEvento();
				}
			}
		}
		return instance;
	}

	public List<String> listarEdiciones(String nombreEvento) {
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		Evento eventoBuscado = manejador.obtenerEvento(nombreEvento);
		Map<String, Edicion> ediciones = eventoBuscado.getEdiciones();
		List<String> res = new ArrayList<>();
		
		for (String entry : ediciones.keySet())
			res.add(entry);
		
		return res;	
	}
	
	public List<String> listarEdicionesIngresadas(String nombreEvento) {
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		Evento eventoBuscado = manejador.obtenerEvento(nombreEvento);
		Map<String, Edicion> ediciones = eventoBuscado.getEdiciones();
		List<String> res = new ArrayList<>();
		
		for (Map.Entry<String, Edicion> entry : ediciones.entrySet()) {
		    if (entry.getValue().getEstado() == TipoEstado.Ingresada) {
		        res.add(entry.getKey());
		    }
		}
		
		return res;	
	}
	
	public List<String> listarEventos(){
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		
		List<String> res = new ArrayList<>();
		Map<String, Evento> eventos = manejador.eventos();
		for (String entry : eventos.keySet())
			res.add(entry);

		return res;
	}
	
	public List<DTEvento> listarDTEventos(){
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		
		Map<String, Evento> eventos = manejador.eventos();

		return eventos.values().stream()
                	  .map(Evento::getDatos)
                	  .collect(Collectors.toList());  
	}

	public List<String> listarPatrocinios(String nomEvento, String nomEdicion) {
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		
		Evento evento = manejador.obtenerEvento(nomEvento);
		Edicion edicion = evento.getEdicion(nomEdicion);
		return edicion.getCodigosPatrocinios();
	}
	
	// Lista las instituciones de una edición
	public List<String> listarInstituciones(String nomEvento, String nomEdicion) {
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		
		Evento evento = manejador.obtenerEvento(nomEvento);
		Edicion edicion = evento.getEdicion(nomEdicion);
		return edicion.getInstitucionesPatrocinios();
	}

	// Lista todas las instituciones del sistema
	public List<String> listarInstituciones() {
		ManejadorInstitucion manejador = ManejadorInstitucion.getInstance();
		
		return new ArrayList<>(manejador.instituciones().keySet());
	}
	
	public List<String> listarOrganizadores() {
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();
		return manejador.organizadores();
	}
	
	public List<String> listarCategorias() {
		ManejadorCategoria manejador = ManejadorCategoria.getInstance();
		
		List<Categoria> categorias = new ArrayList<>(manejador.categorias().values());
		
		return categorias.stream().map(c -> c.getNombre()).collect(Collectors.toList());
	}
	
	public DTEvento informacionEvento(String nombre) {
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		Evento eventoBuscado = manejador.obtenerEvento(nombre);
		DTEvento res = eventoBuscado.getDatos();
		
		return res;
	}
	
	public DTPatrocinio consultaPatrocinioInstitucion(String nomEvento, String nomEdicion, String nomInstitucion) {
		ManejadorEvento manejadorEv = ManejadorEvento.getInstance();
		ManejadorInstitucion manejadorInst = ManejadorInstitucion.getInstance();
		
		Evento evento = manejadorEv.obtenerEvento(nomEvento);
		Edicion edicion = evento.getEdicion(nomEdicion);
		Institucion inst = manejadorInst.obtener(nomInstitucion);
		Patrocinio patrocinio = inst.obtenerPatrocinio(edicion);
		
		if (patrocinio != null)
			return patrocinio.getDatos();
		else
			return null;
	}

	//PRE:: hay un registro en edicion con string nombre
	public DTTRegistro seleccionarTipoRegistro(String nombre, String nomEvento, String nomEdicion) {
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		
		Evento evento = manejador.obtenerEvento(nomEvento);
		Edicion edicion = evento.getEdicion(nomEdicion);
		
		TRegistro tipoRegistro = edicion.getTipoRegistro(nombre);
		
		DTTRegistro dtr = new DTTRegistro(
			tipoRegistro.getCupo(),
			tipoRegistro.getCosto(), 
			tipoRegistro.getNombre(), 
			tipoRegistro.getDescripcion()
		);
		
		return dtr;
	}
	
	public DTEdicion obtenerEdicion(String nombreEdicion, String nombreEvento) {
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		Evento evento = manejador.obtenerEvento(nombreEvento);
		return manejador.obtenerEdicion(nombreEdicion, evento)
				        .getDatos();
	}

	public void crearPatrocinio(DTPatrocinio dtp, String nombreEdicion, String nombreInstitucion, String nombreEvento) throws YaExistePatrocinioException, PatrocinioSuperaMontoException, IllegalArgumentException {
		//Obtengo la Institucion
		ManejadorInstitucion manejadorInst = ManejadorInstitucion.getInstance();		
		Institucion instInstitucion = manejadorInst.obtener(nombreInstitucion);
		
		//Obtengo la Edicion
		ManejadorEvento manejadorEv = ManejadorEvento.getInstance();
		Evento instEvento = manejadorEv.obtenerEvento(nombreEvento);
		Edicion instEdicion = manejadorEv.obtenerEdicion(nombreEdicion, instEvento);
		
		// Obtengo el TipoRegistro y la fecha
		TRegistro tipoRegistro = instEdicion.getTipoRegistro(dtp.getTipoRegistro());

		float costo = tipoRegistro.getCosto();
		int cantRegistros = dtp.getCantRegistros();
		float monto = dtp.getMonto();
        // Chequear valores:
        // cantRegistros y montos no pueden ser negativos
		if (cantRegistros < 0)
			throw new IllegalArgumentException("El cantidad de registros no puede ser negativa");
		if (monto < 0)
			throw new IllegalArgumentException("El aporte económico no puede ser negativo");
		// No se puede exceder el 20% del monto
		if (costo * cantRegistros > 0.2 * monto) 
			throw new PatrocinioSuperaMontoException("El costo de los registros supera el 20% del aporte económico.");
		// No puede existir un patrocinio para la institución
		if (instInstitucion.existePatrocinio(instEdicion)) 
			throw new YaExistePatrocinioException("Ya existe un patrocinio de la institución " + instInstitucion.getNombre() + " para la edición " + instEdicion.getNombre());
		
		Patrocinio patrocinio = new Patrocinio(dtp.getCodigo(), dtp.getMonto(), dtp.getTipo(), dtp.getFechaAlta(), dtp.getCantRegistros(), instEdicion, instInstitucion, tipoRegistro);
		instInstitucion.agregarPatrocinio(patrocinio);
		instEdicion.agregarPatrocinio(patrocinio);
	}
	
	// Crea un evento sin imagen (imagen = null)
	public boolean ingresarDatosEvento(String nombre, String descripcion, String sigla, LocalDate fechaAlta, List<String> categorias) {
		return ingresarDatosEvento(nombre, descripcion, sigla, fechaAlta, categorias, null);	
	}

	// Incluye imagen en los parámetros
	public boolean ingresarDatosEvento(String nombre, String descripcion, String sigla, LocalDate fechaAlta, List<String> categorias, String imagen) {
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		
		// Todo evento debe tener una categoría
		if (categorias.isEmpty())
			return false;
		
		return manejador.crearEvento(nombre, descripcion, sigla, fechaAlta, categorias, imagen);	
	}
	
	public boolean usuarioEnEdicion(String edicion, String usuario, String evento) {
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		return manejador.estaAsistenteEnEdicion(usuario, edicion, evento);
	}
	
	public boolean altaEdicion(String nombreEvento, String nicknameOrganizador, String nombre, String sigla, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAlta, String ciudad, String pais) {
		ManejadorEvento manejadorEv = ManejadorEvento.getInstance();
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();

		Evento evento = manejadorEv.obtenerEvento(nombreEvento);
		Usuario organizador = manejadorU.obtener(nicknameOrganizador);
		
		if (manejadorEv.existeEdicion(nombre) || fechaFin.isBefore(fechaFin))
			return false;

		Edicion edicion = new Edicion(evento, (Organizador) organizador, nombre, sigla, fechaInicio, fechaFin, fechaAlta, ciudad, pais);
		evento.agregarEdicion(edicion);
		
		return true;
	}

	public List<String> listarTipoRegistro(String nombreEvento, String nombreEdicion){
		List<String> resultado = new ArrayList<>();
		
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		Evento evento = manejador.obtenerEvento(nombreEvento);
		Edicion edicion = manejador.obtenerEdicion(nombreEdicion, evento);
		
		for (TRegistro tr : edicion.getTRegistros())
		    resultado.add(tr.getNombre());
		
		return resultado;
	}

	public boolean crearTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTRegistro, String descripcion, float costo, int cupo) {
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		Evento evento = manejador.obtenerEvento(nombreEvento);
		Edicion edicion = manejador.obtenerEdicion(nombreEdicion, evento);
		
		// costo no puede ser negativo
		if (costo < 0)
			throw new IllegalArgumentException("El costo no puede ser negativo");
		// cupo no puede ser negativo
		if (cupo < 0)
			throw new IllegalArgumentException("La cantidad de cupos no puede ser negativa");
		// No debe existir el TRegistro
		if (edicion.getTipoRegistro(nombreTRegistro) != null)
			return false;
		
		TRegistro tipoRegistro = new TRegistro(nombreTRegistro, descripcion, costo, cupo);
		
		edicion.agregarTRegistro(tipoRegistro);
		return true;
	}
	
	public DTTRegistro mostrarTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro){
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		TRegistro tipoRegistro = manejador.encontrarTRegistroDeEdicion(nombreTipoRegistro, nombreEdicion, nombreEvento);
			
		DTTRegistro dtr = new DTTRegistro(
				tipoRegistro.getCupo(),
				tipoRegistro.getCosto(),
				tipoRegistro.getNombre(),
				tipoRegistro.getDescripcion()
	        );			
		return dtr;
	}
	
	//PRE:: asumo que seleccionara un nickTRegistro valido
	public boolean darDeAltaRegistro(String nickAsistente, String nombreEdicion, String nombreTRegistro, String nickEvento, LocalDate fechaAlta) {
		ManejadorEvento manejadorEv = ManejadorEvento.getInstance();
		boolean estaAsistente = manejadorEv.estaAsistenteEnEdicion(nickAsistente, nombreEdicion, nickEvento);
		TRegistro tregistro = manejadorEv.encontrarTRegistroDeEdicion(nombreTRegistro, nombreEdicion, nickEvento);

		if (tregistro.getCuposLibres() == 0 || estaAsistente)
			return true;
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		Usuario usuario = manejadorU.obtener(nickAsistente);
		
		assert usuario instanceof Asistente;
		Asistente asistente = (Asistente) usuario;

		Evento evento = manejadorEv.obtenerEvento(nickEvento);
		Edicion edicion = manejadorEv.obtenerEdicion(nombreEdicion, evento);
				
		float costo = tregistro.getCosto();
		Registro nuevoRegistro = new Registro(costo, fechaAlta, asistente, edicion, tregistro);
		edicion.agregarRegistro(nuevoRegistro);
		asistente.agregarRegistro(nuevoRegistro);
		
		return false;
	}
	
	// Retorna true si existe el evento y la edición para ese evento
	public boolean existeEdicionEvento(String eventoS, String edicionS) {
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		
		if (!manejador.existe(eventoS))
			return false;
		
		Evento evento = manejador.obtenerEvento(eventoS);
		
		return evento.existeEdicion(edicionS);
	}

	public List<DTRegistro> registrosEdicion(String nombreEdicion, String nombreEvento) {
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		
		Evento evento = manejador.obtenerEvento(nombreEvento);
		Edicion edicion = manejador.obtenerEdicion(nombreEdicion, evento);
		
		List<Registro> registros = edicion.getRegistros();
		List<DTRegistro> dtRegistros = new ArrayList<>();
	    
	    for (Registro registro : registros) {
	        DTRegistro dtReg = registro.getDatos();
	        dtRegistros.add(dtReg);
	    }

	    return dtRegistros;
	}

	public void confirmarEdicion(String nombreEvento, String nombreEdicion) {
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		Evento evento = manejador.obtenerEvento(nombreEvento);
		Edicion edicion = manejador.obtenerEdicion(nombreEdicion, evento);
		edicion.confirmar();
	}
	
	public void rechazarEdicion(String nombreEvento, String nombreEdicion) {
		ManejadorEvento manejador = ManejadorEvento.getInstance();
		Evento evento = manejador.obtenerEvento(nombreEvento);
		Edicion edicion = manejador.obtenerEdicion(nombreEdicion, evento);
		edicion.rechazar();
	}

	public List<DTEvento> listarDTEventosCategoria(String categoria){
		ManejadorCategoria manejador = ManejadorCategoria.getInstance();
		Map<String, Evento> eventos = manejador.eventosCategoria(categoria);

		return eventos.values().stream().map(Evento::getDatos).collect(Collectors.toList());  
	}
}
