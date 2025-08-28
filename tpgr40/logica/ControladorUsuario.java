package logica;
import logica.DTAsistente;
import logica.DTFecha;
import logica.DTEdicion;
import logica.ManejadorUsuario;
import logica.Asistente;
import logica.Usuario;
import java.util.List;
import java.util.Set;

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
	public Set<DTAsistente> listarAsistentes() {
		Set<DTAsistente> res;
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		for (Usuario u : m.usuarios()) {
			if (u instanceof Asistente) {
				Asistente e = (Asistente) u;
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

    public List<String> listarOrganizadores() {
        ManejadorUsuario m = ManejadorUsuario.getInstance();
        return m.organizadores();
    }

	public List<String> listarUsuarios() {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
        return m.usuarios();
	}

	public DTUsuario elegirUsuario(String nickname) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		Usuario u = m.obtener(nickname);
		usuario = u;
		return u.getDatos();
	}

	public void modificarDatosUsuario(DTUsuarioModificado datos) {
		usuario.modificar(datos);
		usuario = null;
	}

	// Retorna false si ya existe un usuario con ese nickname o correo,
	// Retorna true si no existe y se creo el usuario correctamente.
	public boolean altaAsistente(String nick, String nom, String c, String ap, DTFecha nac) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		return m.crearAsistente(nick, nom, c, ap, nac);
	}

	public boolean altaOrganizador(String nick, String nom, String c, String desc, String link) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		return m.crearOrganizador(nick, nom, c, desc, link);
	}

	public List<DTEdicion> consultaOrganizador() {
		assert usuario instanceof Organizador;
		return usuario.consulta();
	}

	public List<DTRegistro> consultaAsistente() {
		assert usuario instanceof Asistente;
		return usuario.consulta();
	}

	public void liberarMemoria() {
		usuario = null;
	}
}
