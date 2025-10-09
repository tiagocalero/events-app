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
	
	/*public Usuario(String nick, String nom, String c) {
		this.nickname = nick;
		this.nombre = nom;
		this.correo = c;
	}
	*/
	
	//Se puede fusionar con la de arriba
	public Usuario(String nick, String nom, String c, String contrasena) {
		this.nickname = nick;
		this.nombre = nom;
		this.correo = c;
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
		this.contrasena = hashedcont;
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
	
	public abstract DTUsuario getDatos();
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public abstract void modificar(DTUsuarioModificado datos);
}
