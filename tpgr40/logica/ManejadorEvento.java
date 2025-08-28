package logica;

import java.time.LocalDate;
import java.util.stream.Stream;
import java.util.Map;
import java.util.List;
import logica.Categoria;
import logica.DTEvento;
import logica.Evento;

public class ManejadorEvento {
	private Map<String, Evento> eventos;
    private static ManejadorEvento instance;

    private ManejadorEvento() {}

    public static ManejadorEvento getInstance() {
        if (instance == null) {
            instance = new ManejadorEvento();
        }
        return instance;
    }
    
    
	public boolean existe(String nombre) {
		return eventos.containsKey(nombre);
	}
	
	public Evento obtener(String nombre) {
		return eventos.get(nombre);
	}
	
	public boolean crearEvento(String nombre, String descripcion, String sigla, LocalDate fechaAlta, List<String> categorias) {
		if (existe(nombre))
			return false;
		
		Evento e = Evento(nombre, sigla, descripcion, fechaAlta, categorias);
		eventos.put(nombre, e);
		
		return true;
	}

	// Devuelve una copia del map de eventos sobre el que se puede iterar
	public Map<String, Evento> eventos() {
        return Map.copyOf(eventos);
    }
	
	/*
	public String buscarEvento(
	public boolean estaAsistenteEnEdicion(String nickAsistente, String nickEdicion, String nickEvento) {
		
		Evento evento = eventos.get(nickEvento);
		Edicion edicion = evento.obtenerEdicion(nickEdicion);
		return edicion.getRegistros().stream()
	            .anyMatch(r -> r.getAsistente().getNick().equals(nickAsistente));
	}

	*/

	/*falta esto
	public TRegistro encontrarTRegistroDeEdicion(nombreTRegistro, nombreEdicion) {
		
	}
	*/
}