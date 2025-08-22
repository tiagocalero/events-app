package logica;
import DTUsuario.java;
import DTUsuarioModificado.java;


public class Usuario {
	private String nickname;
	private String nombre;
	private String correo;
	
	public Usuario(String nick, String nom, String c) {
		this.nickname = nick;
		this.nombre = nom;
		this.correo = c;
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
	
	public abstract DTUsuario getDatos() {}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public abstract void modificar(DTUsuarioModificado datos) {}
	}
}