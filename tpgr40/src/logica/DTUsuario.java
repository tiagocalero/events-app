package logica;

public class DTUsuario {
	private String nickname;
	private String nombre;
	private String correo;
	private String imagen;
	
	public DTUsuario(String nick, String nombre, String correo, String img) {
		this.nickname = nick;
		this.nombre = nombre;
		this.correo = correo;
		this.imagen = img;
	}
	
	public String getNickname() { return nickname; }
	public String getNombre() { return nombre; }
	public String getCorreo() { return correo; }
	public String getImagen() { return imagen; }
}