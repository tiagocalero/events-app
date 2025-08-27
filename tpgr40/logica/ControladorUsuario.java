import DTAsistente;
import ManejadorUsuario;
import Asistente;

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
	public set<DTAsistente> listarAsistentes(){
		
		set<DTAsistente> res = new set<DTAsistente>;
		
		ManejadorUsuario m = getInstance();
		for (Usuario u : m.getUsuarios()) {
			if (u instanceof Asistente e) {
				DTAsistente dts = new DTAsistente(
						e.getNick(),
						e.getNom(),
						e.getC(),
						e.getAp(),
						e.getFecha()

						);
				res.add(dts);
						
			}
		}
		return res;
	}

    