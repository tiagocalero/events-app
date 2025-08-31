package logica;

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

public class ControladorEvento{
	private static volatile ControladorEvento instance;
	private ControladorEvento(){};
	
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

	public List<String> listarPatrocinios(String nomEvento, String nomEdicion) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		
		Evento evento = m.obtenerEvento(nomEvento);
		Edicion edicion = evento.getEdicion(nomEdicion);
		return edicion.getCodigosPatrocinios();
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

	public DTPatrocinio consultaPatrocinio(String nomEvento, String nomEdicion, String nomPatrocinio) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		
		Evento evento = m.obtenerEvento(nomEvento);
		Edicion edicion = evento.getEdicion(nomEdicion);
		DTPatrocinio dtp = edicion.getPatrocinio(nomPatrocinio).getDatos();
		
		return dtp;
	}

	public DTEdicion elegirEdicion(Edicion edicion) {
		return edicion.getDatos();
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

	public boolean crearPatrocinio(DTPatrocinio dtp, String nombreEdicion, String nombreInstitucion, String nombreEvento) {
		//Obtengo la Institucion
		ManejadorInstitucion mi = ManejadorInstitucion.getInstance();		
		Institucion instInstitucion = mi.obtener(nombreInstitucion);
		
		//Obtengo la Edicion
		ManejadorEvento me = ManejadorEvento.getInstance();
		Evento instEvento = me.obtenerEvento(nombreEvento);
		Edicion instEdicion = me.obtenerEdicion(nombreEdicion, instEvento);
		
		//Obtengo el TipoRegistro y la fecha
		TRegistro tp = instEdicion.getTipoRegistro(dtp.getTipoRegistro());
        LocalDate fechaActual = LocalDate.now();
		
        //Obtengo el Costo y lo calculo para ver si falla
		float costo = tp.getCosto();
		if (costo * dtp.getCantRegistros() > 0.2 * dtp.getMonto())
			return false;
		
		new Patrocinio(dtp.getCodigo(), dtp.getMonto(), dtp.getTipo(), fechaActual, dtp.getCantRegistros(), instEdicion, instInstitucion, tp);
		
		return true;
	}
	
	
	public boolean ingresarDatosEvento(String nombre, String descripcion, String sigla, List<String> categorias) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		
        LocalDate fechaActual = LocalDate.now();
		
		return m.crearEvento(nombre, descripcion, sigla, fechaActual, categorias);	
	}
	
	public boolean altaEventoCargaDatos(String nombre, String descripcion, String sigla, String fecha, List<String> categorias) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaAlta = LocalDate.parse(fecha, fmt);
		
		return m.crearEvento(nombre, descripcion, sigla, fechaAlta, categorias);	
	}

	
	public boolean altaEdicion(String nombreEvento, String nickanameOrganizador, String nombre, String sigla, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAlta, String ciudad, String pais) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		ManejadorUsuario mu = ManejadorUsuario.getInstance();

		Evento evento = m.obtenerEvento(nombreEvento);
		Usuario organizador = mu.obtener(nickanameOrganizador);
		
		if (evento.existeEdicion(nombre))
			return false;

		Edicion e = new Edicion(evento, (Organizador) organizador, nombre, sigla, fechaInicio, fechaFin, fechaAlta, ciudad, pais);
		evento.agregarEdicion(e);
		
		return true;
	}
	//seria el evento del controlador?
	public List<DTEdicion> seleccionarEvento(Evento e){
		List<DTEdicion> resultado = new ArrayList<>();
		
		for (Edicion ed : e.getEdiciones().values()) {
			DTEdicion dte = new DTEdicion(
					
				 ed.getPais(),
		         ed.getCiudad(),
		         ed.getFechaInicio(),
		         ed.getFechaFinal(),
		         ed.getFechaAlta(),
		         ed.getSigla(),
		         ed.getNombre()
		        );
		    resultado.add(dte);
			
        }
		return resultado;
	}

	public List<DTTRegistro> listarTipoRegistro(String nombreEvento, String nombreEdicion){
			
			List<DTTRegistro> resultado = new ArrayList<>();
			
			ManejadorEvento m = ManejadorEvento.getInstance();
			Evento ev = m.obtenerEvento(nombreEvento);
			Edicion ed = m.obtenerEdicion(nombreEdicion, ev);
			
			for (TRegistro tr : ed.getTRegistros()) {
				
				DTTRegistro dtr = new DTTRegistro(
						tr.getCupo(),
						tr.getCosto(),
						tr.getNombre(),
						tr.getDescripcion()
			        );
			    resultado.add(dtr);
				
	        }
			return resultado;
		
	}


	public boolean crearTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTRegistro, String descripcion, float costo, int cupo) {
		
		ManejadorEvento m = ManejadorEvento.getInstance();
		Evento e = m.obtenerEvento(nombreEvento);
		Edicion ed = m.obtenerEdicion(nombreEdicion, e);
		
		if (ed.getTipoRegistro(nombreTRegistro) != null)
			return false;
		
		TRegistro tr = new TRegistro(nombreTRegistro, descripcion, costo, cupo);
		
		ed.agregarTRegistro(tr);
		return true;
	}
	
	//PRE:: asumo que seleccionara un nickASistente y nickTRegistro valido
	public boolean darDeAltaRegistro(String nickAsistente, String nombreEdicion, String nombreTRegistro, String nickEvento, String nomEdicion) {
		
		ManejadorEvento me = ManejadorEvento.getInstance();
		boolean estaAsistente = me.estaAsistenteEnEdicion(nickAsistente, nombreEdicion, nickEvento);
		TRegistro t = me.encontrarTRegistroDeEdicion(nombreTRegistro, nombreEdicion, nickEvento);

		if (t.getCupo() == 0 || estaAsistente)
			return true;
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		//tener cuidado si no me pasan un asistente, no funciona
		Asistente asistente = (Asistente) mu.obtener(nickAsistente);

		ManejadorEvento m = ManejadorEvento.getInstance();
		Evento e = m.obtenerEvento(nickEvento);
		Edicion edicion = m.obtenerEdicion(nombreEdicion, e);
		
		float costo = t.getCosto();
		Registro nuevoRegistro = new Registro(costo, LocalDate.now(), asistente, edicion);
		return false;
	}
}
