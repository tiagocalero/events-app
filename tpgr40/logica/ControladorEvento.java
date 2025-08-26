package logica

import Evento;
import Edicion;
import ManejadorEvento;
import DTPatrocinio;
import java.time.LocalDate;

public class ControladorEventos{
	private Evento evento;
	private Edicion edicion;
	
	private static volatile ControladorEvento instance;
	private ControladorEvento();
	
	public static ControladorEvento getInstance() {
		if (instance == null) {
			synchronized (ControladorEvento.class) {
				if (instance == null) {
					instance = new ControladorUsuario();
				}
			}
		}
		return instance;
	}
	
	public List<String> listarEventos(){
		ManejadorEvento m = ManejadorEvento.getInstance();
		
		List<String> res = new ArrayList();
		Map<Evento> eventos = m.eventos();
		for (Map.Entry<String,Evento> entry : eventos.entrySet()) {
			res.put(entry.nombre);
		}
		
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
	
	public boolean ingresarDatosEvento(String nombre, String descripción, String siglas, List<String> categorias) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		
        LocalDate fechaActual = LocalDate.now();
		
		return m.crearEvento(nombre, descripcion, sigla, fechaActual, categorias);	
	}
}
