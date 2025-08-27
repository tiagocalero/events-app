public class ControladorUsuario{
	
	private static volatile ControladorUsuario instance;
	private Controladorusuario();
	
	public static ControladorUsuario getInstance() {
		if (instance == null) {
			synchronized (ControladorUsuario.class) {
				if (instance == null) {
					instance = new ControladorUsuario();
				}
			}
		}
		return instance;
	}

    