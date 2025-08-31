package logica;

public class DTUsuario {
	private String nickname;
	private String nombre;
	private String correo;
	
	public DTUsuario(String nick, String nom, String c) {
		this.nickname = nick;
		this.nombre = nom;
		this.correo = c;
	}
	public String getNickname() { return nickname; }
	public String getNombre() { return nombre; }
	public String getCorreo() { return correo; }	
}