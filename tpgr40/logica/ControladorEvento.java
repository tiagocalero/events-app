package logica

import Evento;
import Edicion;
import ManejadorEvento;
import ManejadorInstitucion;
import DTPatrocinio;
import DTEdicion;
import java.time.LocalDate;

public class ControladorEvento{
	private Evento evento;
	private Edicion edicion;
	private Institucion institucion;
	
	private static volatile ControladorEvento instance;
	private ControladorEvento();
	
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
		for (Map.Entry<String,Evento> entry : eventos.entrySet()) {
			res.put(entry.nombre);
		}
		
	}
	
	public boolean elegirInstitucion(String nombre) {
		ManejadorInstitucion m = ManejadorInstitucion.getInstance();
		
		Institucion i = m.obtener(nombre);
		
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
		new Patrocinio(dtp.codigo, dtp.monto, dtp.tipo, fechaActual, dtp.cantRegistros, edicion, institucion, tp)
		
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
	
	public boolean ingresarDatosEvento(String nombre, String descripción, String siglas, List<String> categorias) {
		ManejadorEvento m = ManejadorEvento.getInstance();
		
        LocalDate fechaActual = LocalDate.now();
		
		return m.crearEvento(nombre, descripcion, sigla, fechaActual, categorias);	
	}

	public DTEdicion elegirEdicion(Edicion edicion) {
		return edicion.getDatos();
	}

}
