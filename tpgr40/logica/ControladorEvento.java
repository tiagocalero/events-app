package logica;

import Evento;
import Edicion;
import ManejadorEvento;
import ManejadorInstitucion;
import ManejadorCategoria;
import DTPatrocinio;
import DTEdicion;
import java.time.LocalDate;

public class ControladorEvento{
	private Evento evento;
	private Edicion edicion;
	private Institucion institucion;
	
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
	
	public List<String> listarEventos(){
		ManejadorEvento m = ManejadorEvento.getInstance();
		
		List<String> res = new ArrayList();
		Map<Evento> eventos = m.eventos();
		for (Map.Entry<String,Evento> entry : eventos.keySet()) {
			res.put(entry.nombre);
		}
		return res;
	}
	
	public DTEvento informacionEvento (String nombre) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		Evento eventoBuscado = m.obtener(nombre);
		DTEvento res = eventoBuscado.getDatos();
		return res;
	}
	
	public List<String> listarEdiciones(String nombreEvento) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		Evento eventoBuscado = m.obtener(nombre);
		Map<Edcion> ediciones = eventoBuscado.getEdiciones();
		List<String> res = new ArrayList();
		for (Map.Entry<String,Evento> entry : eventos.keySet()) {
			res.put(entry.nombre);
		}
		return res;
		
	}
	
	// Devuelve una lista con todas las ediciones del evento
	public List<String> elegirEvento(String nombreEvento) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		
		evento = m.obtener(nombreEvento);
		return new ArrayList<>(evento.getEdiciones().keySet());
	}
	
	// Devuelve una lista con todas las instituciones de la edición
	public List<String> elegirEdicion(String nombreEdicion) {
		edicion = evento.getEdicion(nombreEdicion);
		return edicion.getInstituciones().stream().map(i -> i.getNombre());
	}
	
	public boolean elegirInstitucion(String nombreInstitucion) {
		ManejadorInstitucion m = ManejadorInstitucion.getInstance();
		
		Institucion i = m.obtener(nombreInstitucion);
		
		if (!i.existePatrocinio(edicion))
			return false;
	
		institucion = i;
		return true;
	}
	
	public boolean crearPatrocinio(DTPatrocinio dtp) {
		TipoRegistro tp = edicion.obtenerTipoRegistro(dtp.tipoRegistro);
		float costo = tp.getCosto();
		if (costo * dtp.cantRegistros > 0.2 * dtp.monto)
			return false;
		
        LocalDate fechaActual = LocalDate.now();
		new Patrocinio(dtp.codigo, dtp.monto, dtp.tipo, fechaActual, dtp.cantRegistros, edicion, institucion, tp);
		
		return true;
	}
	
	public List<String> listarPatrocinios() {
		return edicion.getNombresPatrocinios();
	}
	
	public DTPatrocinio consultaPatrocinio(String nomPatrocinio) {
		DTPatrocinio dtp = edicion.getPatrocinio(nomPatrocinio).getDatos();
		
		evento = null;
		edicion = null;
		
		return dtp;
	}
	
	public List<String> listarCategorias() {
		ManejadorCategoria m = ManejadorCategoria.getInstance();
		
		return m.categorias().stream().map(c -> c.getNombre()).collect(Collectors.toList())
	}
	
	public boolean ingresarDatosEvento(String nombre, String descripción, String siglas, List<String> categorias) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		
        LocalDate fechaActual = LocalDate.now();
		
		return m.crearEvento(nombre, descripcion, sigla, fechaActual, categorias);	
	}

	public DTEdicion elegirEdicion(Edicion edicion) {
		return edicion.getDatos();
	}

	//Asumo que se hizo el chequeo de que el nombre no exista
	public void altaEdicion(String nombreEvento, Organizador nickanameOrganizador, String nombre, String sigla, String ciudad, String pais) {
		ManejadorEvento m = ManejadorEvento.getInstance();

		Evento evento = m.obtener(nombreEvento);

		ManejadorUsuario mu = ManejadorUsuario.getInstance();

		Usuario organizador = mu.obtener(nickanameOrganizador);

		new Edicion(evento, organizador, nombre, sigla, ciudad, pais);

	}

}
