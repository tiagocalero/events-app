package logica

public class ControladorEventos{
	
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
	
}