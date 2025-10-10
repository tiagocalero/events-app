package logica;

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
		this.contrasena = contrasena;
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
