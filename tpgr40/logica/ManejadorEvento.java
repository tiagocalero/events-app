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
    
	public bool existe(String nombre) {
		return eventos.containsKey(nombre);
	}
	
	public String obtener(String nombre) {
		return eventos.get(nombre);
	}
	
	public bool crearEvento(String nombre, String sigla, String descripcion, LocalDate fechaAlta, List<String> categorias) {
		if (existe(nombre))
			return false;
		
		Evento e = Evento(nombre, sigla, descripcion, fechaAlta, categorias);
		eventos.put(nombre, e)
		
		return true;
	}

	// Devuelve una copia del map de eventos sobre el que se puede iterar
	public Map<String, Evento> eventos() {
        return Map.copyOf(eventos);
    }
}