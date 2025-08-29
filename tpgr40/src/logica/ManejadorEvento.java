package logica;

import java.time.LocalDate;
import java.util.stream.Stream;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import logica.Categoria;
import logica.DTEvento;
import logica.Evento;

public class ManejadorEvento {
	private Map<String, Evento> eventos;
    private static ManejadorEvento instance;

    private ManejadorEvento() {
    	eventos = new HashMap<>();
    }

    public static ManejadorEvento getInstance() {
        if (instance == null) {
            instance = new ManejadorEvento();
        }
        return instance;
    }
    
    
	public boolean existe(String nombre) {
		return eventos.containsKey(nombre);
	}
	
	public Evento obtenerEvento(String nombre) {
		return eventos.get(nombre);
	}
	
	public boolean crearEvento(String nombre, String descripcion, String sigla, LocalDate fechaAlta, List<String> categorias) {
		if (existe(nombre))
			return false;
		
		Evento e = new Evento(nombre, sigla, descripcion, fechaAlta, categorias);
		eventos.put(nombre, e);
		
		return true;
	}
	
	public Edicion obtenerEdicion(String nombre, Evento ev) {
		return ev.getEdiciones().get(nombre);
	}
	
	public boolean estaAsistenteEnEdicion(String nickAsistente, String nombreEdicion, String nickEvento) {
		
		Evento ev = obtenerEvento(nickEvento);
		Edicion ed = obtenerEdicion(nombreEdicion, ev);
		for (Registro r :ed.getRegistros()) {
			if (r.getNicknameAsistente() == nickAsistente) {
				return true;
			}
		}
		return false;
		
	}
	// Devuelve una copia del map de eventos sobre el que se puede iterar
	public Map<String, Evento> eventos() {
        return Map.copyOf(eventos);
    }
	
	public TRegistro encontrarTRegistroDeEdicion(nombreTRegistro, nombreEdicion, nickEvento) {
		Evento ev = obtenerEvento(nickEvento);
		Edicion ed = obtenerEdicion(nombreEdicion, ev);
		return ed.getTipoRegistro(nombreTRegistro);
	}
	
}