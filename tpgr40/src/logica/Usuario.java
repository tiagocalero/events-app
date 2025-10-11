package logica;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import logica.DTUsuario;
import logica.DTUsuarioModificado;

public abstract class Usuario {
	private String nickname;
	private String nombre;
	private String correo;
	private String contrasena;
	private String imagen;
	
	public Usuario(String nick, String nom, String c, String contrasena, String img) {
		ControladorUsuario cu = ControladorUsuario.getInstance();
		this.nickname = nick;
		this.nombre = nom;
		this.correo = c;
		this.contrasena = cu.hashearContrasena(contrasena);
		this.imagen = img;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public String getImagen() {
		return imagen;
	}

	public boolean chequearContrasena(String pass) {
		ControladorUsuario cu = ControladorUsuario.getInstance();
		return contrasena.equals(cu.hashearContrasena(pass));
	}
	
	public abstract DTUsuario getDatos();
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setImagen(String img) {
		this.imagen = img;
	}
	
	public abstract void modificar(DTUsuarioModificado datos);
}
