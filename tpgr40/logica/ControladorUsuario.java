public class ControladorEvento{
	private Evento evento;
	private Edicion edicion;
	
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