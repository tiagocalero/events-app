package logica;
import DTAsistente;
import ManejadorUsuario;
import Asistente;
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
	public set<DTAsistente> listarAsistentes(){
		set<DTAsistente> res = new set<DTAsistente>;
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		for (Usuario u : m.usuarios()) {
			if (u instanceof Asistente e) {
				// DTAsistente dts = new DTAsistente(
				// 		e.getNick(),
				// 		e.getNom(),
				// 		e.getC(),
				// 		e.getAp(),
				// 		e.getFecha()
				// 		);
				DTAsistente dts = ((Asistente) e).getDatos();
				res.add(dts);
			}
		}
		return res;
	}

    public List<String> listarOrganizadores(){
        ManejadorUsuario m = ManejadorUsuario.getInstance();
        return m.organizadores();
    }

	public List<String> listarUsuarios(){
		ManejadorUsuario m = ManejadorUsuario.getInstance();
        return m.usuarios();
	}

	public DTUsuario elegirUsuario(String nickname){
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		Usuario u = m.obtener(nickname);
		usuario = u;
		return u.getDatos();
	}

	public void modificarDatosUsuario(DTUsuarioModificado datos) {
		usuario.modificar(datos);
		usuario = NULL;
	}

}
