package logica;
import logica.DTAsistente;
import java.time.LocalDate;
import logica.DTEdicion;
import logica.ManejadorUsuario;
import logica.Asistente;
import logica.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	
	public List<String> listarAsistentes() {
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
	
	public String hashearContrasena(String contrasena) {
		String hashedcont;
		try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(contrasena.getBytes());
            
            // Convert bytes to hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            hashedcont = hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
		return hashedcont;
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
	public boolean altaAsistente(String nick, String nom, String c, String cont, String ap, LocalDate nac, String ins) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		return m.crearAsistente(nick, nom, c, cont, ap, nac, ins);
	}
	
	public boolean altaAsistente(String nick, String nom, String c, String cont, String ap, LocalDate nac) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		return m.crearAsistente(nick, nom, c, cont, ap, nac);
	}

	public boolean altaOrganizador(String nick, String nom, String c, String cont, String desc, String link) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		return m.crearOrganizador(nick, nom, c, cont, desc, link);
	}
	
	// Con Imagenes.
	public boolean altaAsistente(String nick, String nom, String c, String img, String cont, String ap, LocalDate nac, String ins) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		return m.crearAsistente(nick, nom, c, cont, img, ap, nac, ins);
	}
	
	public boolean altaAsistente(String nick, String nom, String c, String img, String cont, String ap, LocalDate nac) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		return m.crearAsistente(nick, nom, c, cont, img, ap, nac);
	}

	public boolean altaOrganizador(String nick, String nom, String c, String img, String cont, String desc, String link) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();
		return m.crearOrganizador(nick, nom, c, cont, img, desc, link);
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
	
	//Devuelve el Datatype del registro con nombreEdicion del usuario 
	public DTRegistro datosRegistroUsuario (String nombreEdicion, String usuario) {
		ManejadorUsuario m = ManejadorUsuario.getInstance();	
		
		Usuario user = m.obtener(usuario);
		Asistente asistente = (Asistente) user;
		Registro registro = asistente.getRegistro(nombreEdicion);
		
		return registro.getDatos();
	}
}
