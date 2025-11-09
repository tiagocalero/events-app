package main.java.logica;
import java.time.LocalDate;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;
import java.util.List;

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
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();
        return manejador.asistentes();
 
	}

    public List<String> listarOrganizadores() {
        ManejadorUsuario manejador = ManejadorUsuario.getInstance();
        return manejador.organizadores();
    }

	public List<String> listarUsuarios() {
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();
        return new ArrayList<>(manejador.usuarios().keySet());
	}

	public boolean existeUsuario(String nickname) {
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();
		
		return manejador.obtener(nickname) != null;
	}

	public DTUsuario elegirUsuario(String nickname) {
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();
		Usuario usu = manejador.obtener(nickname);
		usuario = usu;
		return usu == null ? null : usu.getDatos();
	}
	
	public String hashearContrasena(String contrasena) {
		String hashedcont;
		try {
            MessageDigest mensaje = MessageDigest.getInstance("SHA-256");
            byte[] hash = mensaje.digest(contrasena.getBytes());
            
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

	public void modificarDatosUsuario(DTUsuarioModificado datos) {
		usuario.modificar(datos);
		usuario = null;
	}
	
	// Con Imagenes.
	public boolean altaAsistente(String nick, String nombre, String correo, String cont, String img, String apellido, LocalDate nac, String ins) {
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();
		return manejador.crearAsistente(nick, nombre, correo, cont, img, apellido, nac, ins);
	}
	
	public boolean altaAsistente(String nick, String nombre, String correo, String cont, String img, String apellido, LocalDate nac) {
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();
		return manejador.crearAsistente(nick, nombre, correo, cont, img, apellido, nac);
	}

	public boolean altaOrganizador(String nick, String nombre, String correo, String cont, String img, String desc, String link) {
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();
		return manejador.crearOrganizador(nick, nombre, correo, cont, img, desc, link);
	}
	
	public List<DTEdicion> consultaOrganizadorAceptadas(String nickname) {
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();
		Organizador organizador = (Organizador) manejador.obtener(nickname);
		return organizador.consultaAceptadas();
	}
	
	//RECUERDA USUARIO CON MEMORIA
	public List<DTEdicion> consultaOrganizador() {
		assert usuario instanceof Organizador;
		Organizador organiza = (Organizador) usuario;
		return organiza.consulta();
	}
	
	public List<DTRegistro> consultaAsistente() {
		assert usuario instanceof Asistente;
		Asistente asis = (Asistente) usuario;
		return asis.consulta();
	}
	
	//Devuelve el Datatype del registro con nombreEdicion del usuario 
	public DTRegistro datosRegistroUsuario(String nombreEdicion, String usuario) {
		
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();	
		
		Usuario user = manejador.obtener(usuario);
		Asistente asistente = (Asistente) user;
		Registro registro = asistente.getRegistro(nombreEdicion);
		
		return registro.getDatos();
	}
	
	public boolean chequearContrasena(String nickOCorreo, String cont) {
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();	
		
		Usuario usuario = manejador.obtener(nickOCorreo);
		return usuario != null && usuario.chequearContrasena(cont);
	}
	
	public void setContrasena(String nickOCorreo, String nueva) {
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();	
		
		Usuario usuario = manejador.obtener(nickOCorreo);
		usuario.setContrasena(nueva);
	}
	
	public void registrarAsistencia(String asistente, String edicion) {
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();		
		assert usuario instanceof Asistente;
		Asistente usuario = (Asistente) manejador.obtener(asistente);
		usuario.registrarAsistencia(edicion);
	}

	public List<String> registrosNoConfirmados(String nickname){
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();		
		assert usuario instanceof Asistente;
		Asistente usuario = (Asistente) manejador.obtener(nickname);
		return usuario.registrosNoConfirmados();
	}

	
	public void seguirUsuario(String seguidor, String seguido) {
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();		
		Usuario userSeguidor = manejador.obtener(seguidor);
		userSeguidor.agregarSeguido(seguido);
	}
	
	public void dejarDeSeguirUsuario(String seguidor, String seguido) {
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();		
		Usuario userSeguidor = manejador.obtener(seguidor);
		userSeguidor.eliminarSeguido(seguido);
	}
	
	public boolean esSeguido(String seguidor, String seguido) {
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();		
		Usuario userSeguidor = manejador.obtener(seguidor);
		return userSeguidor.esSeguido(seguido);
	}
	
	public List<String> obtenerSeguidos(String nickname) {
		ManejadorUsuario manejador = ManejadorUsuario.getInstance();		
		Usuario user = manejador.obtener(nickname);
		return user.getSeguidos();
	}
}
