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
	
	public DTUsuario getDatos() {
		DTUsuario res = new DTUsuario(nickname, nombre, correo);
		return res;
	}
	
	public void modificar(DTUsuarioModificado datos) {
		this.nombre = datos.nombre;
	}
}