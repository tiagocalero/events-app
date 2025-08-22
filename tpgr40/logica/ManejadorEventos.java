import java.time.LocalDate;
import java.util.stream.Stream;
import categoria;
import DTEvento;
import Evento;

public class ManejadorEventos {
	private Map<String, Evento> eventos;
    private static ManejadorEventos instance;

    private ManejadorEventos() {}

    public static ManejadorEventos getInstance() {
        if (instance == null) {
            instance = new ManejadorEventos();
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
}