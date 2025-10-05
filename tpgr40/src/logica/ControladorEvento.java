package logica;

import excepciones.YaExistePatrocinioException;
import excepciones.PatrocinioSuperaMontoException;
import logica.DTEdicion;
import logica.Evento;
import logica.Edicion;
import logica.ManejadorEvento;
import logica.ManejadorInstitucion;
import logica.ManejadorCategoria;
import logica.DTPatrocinio;
import logica.DTEdicion;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Stream;
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
		ManejadorEvento m = ManejadorEvento.getInstance();
		Evento eventoBuscado = m.obtenerEvento(nombreEvento);
		Map<String, Edicion> ediciones = eventoBuscado.getEdiciones();
		List<String> res = new ArrayList<>();
		
		for (String entry : ediciones.keySet())
			res.add(entry);
		
		return res;	
	}
	
	public List<String> listarEventos(){
		ManejadorEvento m = ManejadorEvento.getInstance();
		
		List<String> res = new ArrayList<>();
		Map<String, Evento> eventos = m.eventos();
		for (String entry : eventos.keySet())
			res.add(entry);

		return res;
	}
	
	public List<DTEvento> listarDTEventos(){
		ManejadorEvento m = ManejadorEvento.getInstance();
		
		Map<String, Evento> eventos = m.eventos();

		return eventos.values().stream()
                	  .map(Evento::getDatos)
                	  .collect(Collectors.toList());  
	}

	public List<String> listarPatrocinios(String nomEvento, String nomEdicion) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		
		Evento evento = m.obtenerEvento(nomEvento);
		Edicion edicion = evento.getEdicion(nomEdicion);
		return edicion.getCodigosPatrocinios();
	}
	
	public List<String> listarInstituciones(String nomEvento, String nomEdicion) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		
		Evento evento = m.obtenerEvento(nomEvento);
		Edicion edicion = evento.getEdicion(nomEdicion);
		return edicion.getInstitucionesPatrocinios();
	}
	
	public List<String> listarOrganizadores() {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		return m.organizadores();
	}
	
	public List<String> listarCategorias() {
		ManejadorCategoria m = ManejadorCategoria.getInstance();
		
		List<Categoria> categorias = new ArrayList<>(m.categorias().values());
		
		return categorias.stream().map(c -> c.getNombre()).collect(Collectors.toList());
	}
	
	public DTEvento informacionEvento(String nombre) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		Evento eventoBuscado = m.obtenerEvento(nombre);
		DTEvento res = eventoBuscado.getDatos();
		
		return res;
	}
	
	public DTPatrocinio consultaPatrocinioInstitucion(String nomEvento, String nomEdicion, String nomInstitucion) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		ControladorInstitucion i = ControladorInstitucion.getInstance();
		
		Evento evento = m.obtenerEvento(nomEvento);
		Edicion edicion = evento.getEdicion(nomEdicion);
		Institucion inst = i.obtenerInstitucion(nomInstitucion);
		Patrocinio p = inst.obtenerPatrocinio(edicion);
		DTPatrocinio dtp = null;
		if (p != null) dtp = p.getDatos();
		
		return dtp;
	}

	//PRE:: hay un registro en edicion con string nombre
	public DTTRegistro seleccionarTipoRegistro(String nombre, String nomEvento, String nomEdicion) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		
		Evento evento = m.obtenerEvento(nomEvento);
		Edicion edicion = evento.getEdicion(nomEdicion);
		
		TRegistro tr = edicion.getTipoRegistro(nombre);
		
		DTTRegistro dtr = new DTTRegistro(
			tr.getCupo(),
			tr.getCosto(), 
			tr.getNombre(), 
			tr.getDescripcion()
		);
		
		return dtr;
	}
	
	public DTEdicion obtenerEdicion(String nombre, String ev) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		Evento evento = m.obtenerEvento(ev);
		return m.obtenerEdicion(nombre, evento).getDatos();
	}

	public void crearPatrocinio(DTPatrocinio dtp, String nombreEdicion, String nombreInstitucion, String nombreEvento) throws YaExistePatrocinioException, PatrocinioSuperaMontoException, IllegalArgumentException {
		//Obtengo la Institucion
		ManejadorInstitucion mi = ManejadorInstitucion.getInstance();		
		Institucion instInstitucion = mi.obtener(nombreInstitucion);
		
		//Obtengo la Edicion
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento instEvento = me.obtenerEvento(nombreEvento);
		Edicion instEdicion = me.obtenerEdicion(nombreEdicion, instEvento);
		
		// Obtengo el TipoRegistro y la fecha
		TRegistro tp = instEdicion.getTipoRegistro(dtp.getTipoRegistro());

		float costo = tp.getCosto();
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
		
		Patrocinio p = new Patrocinio(dtp.getCodigo(), dtp.getMonto(), dtp.getTipo(), dtp.getFechaAlta(), dtp.getCantRegistros(), instEdicion, instInstitucion, tp);
		instInstitucion.agregarPatrocinio(p);
		instEdicion.agregarPatrocinio(p);
	}
	
	public boolean ingresarDatosEvento(String nombre, String descripcion, String sigla, LocalDate fechaAlta, List<String> categorias) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		
		if (categorias.isEmpty())
			return false;
		
		return m.crearEvento(nombre, descripcion, sigla, fechaAlta, categorias);	
	}
	
	public boolean altaEdicion(String nombreEvento, String nicknameOrganizador, String nombre, String sigla, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAlta, String ciudad, String pais) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		ManejadorUsuario mu = ManejadorUsuario.getInstance();

		Evento evento = m.obtenerEvento(nombreEvento);
		Usuario organizador = mu.obtener(nicknameOrganizador);
		
		if (m.existeEdicion(nombre) || fechaFin.isBefore(fechaFin))
			return false;

		Edicion e = new Edicion(evento, (Organizador) organizador, nombre, sigla, fechaInicio, fechaFin, fechaAlta, ciudad, pais);
		//hay que asociar?
		evento.agregarEdicion(e);
		
		return true;
	}

	public List<String> listarTipoRegistro(String nombreEvento, String nombreEdicion){
		List<String> resultado = new ArrayList<>();
		
		ManejadorEvento m = ManejadorEvento.getInstance();
		Evento ev = m.obtenerEvento(nombreEvento);
		Edicion ed = m.obtenerEdicion(nombreEdicion, ev);
		
		for (TRegistro tr : ed.getTRegistros())
		    resultado.add(tr.getNombre());
		
		return resultado;
	}

	public boolean crearTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTRegistro, String descripcion, float costo, int cupo) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		Evento e = m.obtenerEvento(nombreEvento);
		Edicion ed = m.obtenerEdicion(nombreEdicion, e);
		
		// costo no puede ser negativo
		if (costo < 0)
			throw new IllegalArgumentException("El costo no puede ser negativo");
		// cupo no puede ser negativo
		if (cupo < 0)
			throw new IllegalArgumentException("La cantidad de cupos no puede ser negativa");
		// No debe existir el TRegistro
		if (ed.getTipoRegistro(nombreTRegistro) != null)
			return false;
		
		TRegistro tr = new TRegistro(nombreTRegistro, descripcion, costo, cupo);
		
		ed.agregarTRegistro(tr);
		return true;
	}
	
	public DTTRegistro mostrarTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro){
		ManejadorEvento m = ManejadorEvento.getInstance();
		TRegistro tr = m.encontrarTRegistroDeEdicion(nombreTipoRegistro, nombreEdicion, nombreEvento);
			
		DTTRegistro dtr = new DTTRegistro(
				tr.getCupo(),
				tr.getCosto(),
				tr.getNombre(),
				tr.getDescripcion()
	        );			
		return dtr;
	}
	
	//PRE:: asumo que seleccionara un nickTRegistro valido
	public boolean darDeAltaRegistro(String nickAsistente, String nombreEdicion, String nombreTRegistro, String nickEvento, LocalDate fechaAlta) {
		ManejadorEvento me = ManejadorEvento.getInstance();
		boolean estaAsistente = me.estaAsistenteEnEdicion(nickAsistente, nombreEdicion, nickEvento);
		TRegistro tregistro = me.encontrarTRegistroDeEdicion(nombreTRegistro, nombreEdicion, nickEvento);

		if (tregistro.getCuposLibres() == 0 || estaAsistente)
			return true;
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Usuario usuario = mu.obtener(nickAsistente);
		
		assert usuario instanceof Asistente;
		Asistente asistente = (Asistente) usuario;

		ManejadorEvento m = ManejadorEvento.getInstance();
		Evento e = m.obtenerEvento(nickEvento);
		Edicion edicion = m.obtenerEdicion(nombreEdicion, e);
				
		float costo = tregistro.getCosto();
		Registro nuevoRegistro = new Registro(costo, fechaAlta, asistente, edicion, tregistro);
		edicion.agregarRegistro(nuevoRegistro);
		asistente.agregarRegistro(nuevoRegistro);
		
		return false;
	}
}
