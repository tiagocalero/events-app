package logica;
import logica.DTAsistente;
import java.time.LocalDate;
import logica.DTEdicion;
import logica.ManejadorUsuario;
import logica.Asistente;
import logica.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

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
		Set<DTAsistente> res = new HashSet<>();
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		for (Usuario u : m.usuarios().values()) {
			if (u instanceof Asistente) {
				Asistente e = (Asistente) u;
				DTAsistente dts = ((Asistente) e).getDatos();
				res.add(dts);
			}
		}
		return res;
	}
	
	public List<String> listarNicknameAsistentes() {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
        return m.asistentes();
 
	}

    public List<String> listarOrganizadores() {
        ManejadorUsuario m = ManejadorUsuario.getInstance();
        return m.organizadores();
    }

	public List<String> listarUsuarios() {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
        return new ArrayList<>(m.usuarios().keySet());
	}
	

	public DTUsuario elegirUsuario(String nickname) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		Usuario u = m.obtener(nickname);
		usuario = u;
		return u.getDatos();
	}
	
	public Usuario obtenerUsuario(String nick) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		return m.obtener(nick);
	}

	public void modificarDatosUsuario(DTUsuarioModificado datos) {
		usuario.modificar(datos);
		usuario = null;
	}

	// Retorna false si ya existe un usuario con ese nickname o correo,
	// Retorna true si no existe y se creo el usuario correctamente.
	public boolean altaAsistente(String nick, String nom, String c, String ap, LocalDate nac, String ins) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		return m.crearAsistente(nick, nom, c, ap, nac, ins);
	}
	
	public boolean altaAsistente(String nick, String nom, String c, String ap, LocalDate nac) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		return m.crearAsistente(nick, nom, c, ap, nac);
	}

	public boolean altaOrganizador(String nick, String nom, String c, String desc, String link) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		return m.crearOrganizador(nick, nom, c, desc, link);
	}
	
	//RECUERDA USUARIO CON MEMORIA
	public List<DTEdicion> consultaOrganizador() {
		assert usuario instanceof Organizador;
		Organizador o = (Organizador) usuario;
		return o.consulta();
	}
	
	public List<DTRegistro> consultaAsistente() {
		assert usuario instanceof Asistente;
		Asistente a = (Asistente) usuario;
		return a.consulta();
	}
	
	
	//RECIBE PARAMETROS
	public List<DTRegistro> registrosDeAsistente(String nicknameAsistente) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		return ((Asistente) m.obtener(nicknameAsistente)).consulta();
	}
	
	public List<DTEdicion> edicionesDeOrganizador(String nicknameOrganizador) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		return ((Organizador) m.obtener(nicknameOrganizador)).consulta();
	}

	public void liberarMemoria() {
		usuario = null;
	}
}
