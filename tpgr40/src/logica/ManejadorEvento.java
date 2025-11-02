package logica;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    public static void borrarInstance() {
    	instance = null;
    }
    
	public boolean existe(String nombre) {
		return eventos.containsKey(nombre);
	}
	
	public Evento obtenerEvento(String nombre) {
		return eventos.get(nombre);
	}
	
	// Devuelve false si ya existe un evento con el mismo nombre
	public boolean crearEvento(String nombre, String descripcion, String sigla, LocalDate fechaAlta, List<String> categorias, String imagen, Boolean finalizado) {
		if (existe(nombre))
			return false;
		
		Evento evento = new Evento(
			nombre,
			sigla,
			descripcion,
			fechaAlta,
			categorias,
			imagen,
			finalizado
		);
		eventos.put(nombre, evento);
		
		return true;
	}
	
	public Edicion obtenerEdicion(String nombre, Evento evento) {
		Map<String, Edicion> edicion = evento.getEdiciones();
	
		if (edicion.isEmpty()) return null;
		
		return edicion.get(nombre);
	}
	
	// Devuelve si existe una edición en todo el sistema
	public boolean existeEdicion(String nombre) {
		return eventos.values()
				      .stream()
                      .anyMatch(e -> e.existeEdicion(nombre));
	}
	
	public boolean estaAsistenteEnEdicion(String nickAsistente, String nombreEdicion, String nombreEvento) {
		Evento evento = obtenerEvento(nombreEvento);
		Edicion edicion = obtenerEdicion(nombreEdicion, evento);
		for (Registro registro: edicion.getRegistros()) {
			if (registro.getNicknameAsistente() == nickAsistente) {
				return true;
			}
		}
		return false;
	}
	// Devuelve una copia del map de eventos sobre el que se puede iterar
	public Map<String, Evento> eventos() {
        return Map.copyOf(eventos);
    }
		
	public TRegistro encontrarTRegistroDeEdicion(String nombreTRegistro, String nombreEdicion, String nombreEvento) {
		Evento evento = obtenerEvento(nombreEvento);
		Edicion edicion = obtenerEdicion(nombreEdicion, evento);
		return edicion.getTipoRegistro(nombreTRegistro);
	}
	
	public void finalizarEvento(String evento) {
		Evento ev = obtenerEvento(evento);
		ev.finalizar();
	}
}