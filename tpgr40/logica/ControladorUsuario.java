
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
	//PRE:: asumo que seleccionara un nickASistente y nickTRegistro valido
	public bool darDeAltaRegistro(String nickAsistente, String nombreEdicion, String nombreTRegistro) {
		ManejadorUsuario me = ManejadorEvento::getInstance();
		//falta hacer
		bool estaAsistente = me.estaAsistenteEnEdicion(nickAsistente, nombreEdicion);
		TRegistro t = me.encontrarRegistroDeEdicion(nombreTRegistro, nombreEdicion);
		
		
		if (t.getGupo() == 0 || estaAsistente) {
			return false;
		}
		//falta hacer
		int costo = t.getCosto();
		Registro nuevoRegistro = new Registro(asistente, edicion, t, costo);
		
		
	}

    public List<String> listarOrganizadores(){
        ManejadorUsuario m = ManejadorUsuario::getInstance();

        return m.organizadores();
    }

	public List<String> listarUsuarios(){
		ManejadorUsuario m = ManejadorUsuario.getInstance();

        return m.usuarios();
	}

}  