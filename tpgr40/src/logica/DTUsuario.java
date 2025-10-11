package logica;

public class DTUsuario {
	private String nickname;
	private String nombre;
	private String correo;
	private String imagen;
	
	public DTUsuario(String nick, String nom, String c) {
		this.nickname = nick;
		this.nombre = nom;
		this.correo = c;
		this.imagen = null;
	}
	
	public DTUsuario(String nick, String nom, String c, String img) {
		this.nickname = nick;
		this.nombre = nom;
		this.correo = c;
		this.imagen = img;
	}
	
	public String getNickname() { return nickname; }
	public String getNombre() { return nombre; }
	public String getCorreo() { return correo; }
	public String getImagen() { return imagen; }
}