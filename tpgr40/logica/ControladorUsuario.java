import Usuario;
import ManejadorUsuario;


public class ControladorUsuario{
	private Usuario usuario;

	private static volatile ControladorUsuario instance;
	private ControladorUsuario(){};
	
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

    public List<String> listarOrganizadores(){
        ManejadorUsuario m = ManejadorUsuario.getInstance();

        return m.organizadores();
    }

}  