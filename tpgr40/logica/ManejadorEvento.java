import java.time.LocalDate;
import java.util.stream.Stream;
import categoria;
import DTEvento;
import Evento;

public class ManejadorEvento {
	private Map<String, Evento> eventos;
    private static ManejadorEvento instance;

    private ManejadorEventos() {}

    public static ManejadorEvento getInstance() {
        if (instance == null) {
            instance = new ManejadorEvento();
        }
        return instance;
    }
    
	public existe(String nombre) {
		return eventos.containsKey(nombre);
	}
	
	public obtener(String nombre) {
		return eventos.get(nombre);
	}
	
	public crearEvento(String nombre, String sigla, String descripcion, LocalDate fechaAlta, List<String> categorias) {
		Evento e = Evento(nombre, sigla, descripcion, fechaAlta, categorias);
		eventos.put(nombre, e)
	}

	// Devuelve una copia del map de eventos sobre el que se puede iterar
	public Map<String, Evento> eventos() {
        return Map.copyOf(eventos);
    }
}