package logica;

import logica.Evento;
import logica.Edicion;
import logica.ManejadorEvento;
import logica.ManejadorInstitucion;
import logica.ManejadorCategoria;
import logica.DTPatrocinio;
import logica.DTEdicion;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

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
		for (String entry : eventos.keySet()) {
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
		Map<Edicion> ediciones = eventoBuscado.getEdiciones();
		List<String> res;
		
		for (String entry : eventos.keySet())
			res.put(entry.nombre);
		
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
		return edicion.getInstituciones();
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
		TipoRegistro tp = edicion.obtenerTipoRegistro(dtp.getTipoRegistro());
		float costo = tp.getCosto();
		if (costo * dtp.getCantRegistros() > 0.2 * dtp.getMonto())
			return false;
		
        LocalDate fechaActual = LocalDate.now();
		new Patrocinio(dtp.getCodigo(), dtp.getMonto(), dtp.getTipo(), fechaActual, dtp.getCantRegistros(), edicion, institucion, tp);
		
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
		
		List<Categoria> categorias = new ArrayList<>(m.categorias().values());
		
		return categorias.stream().map(c -> c.getNombre()).collect(Collectors.toList());
	}
	
	public boolean ingresarDatosEvento(String nombre, String descripcion, String sigla, List<String> categorias) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		
        LocalDate fechaActual = LocalDate.now();
		
		return m.crearEvento(nombre, descripcion, sigla, fechaActual, categorias);	
	}

	public DTEdicion elegirEdicion(Edicion edicion) {
		return edicion.getDatos();
	}

	//Asumo que se hizo el chequeo de que el nombre no exista
	public void altaEdicion(String nombreEvento, String nickanameOrganizador, String nombre, String sigla, String ciudad, String pais) {
		ManejadorEvento m = ManejadorEvento.getInstance();

		Evento evento = m.obtener(nombreEvento);

		ManejadorUsuario mu = ManejadorUsuario.getInstance();

		Usuario organizador = mu.obtener(nickanameOrganizador);

		new Edicion(evento, (Organizador) organizador, nombre, sigla, ciudad, pais);

	}

}
