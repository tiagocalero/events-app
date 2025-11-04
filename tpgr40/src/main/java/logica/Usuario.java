package main.java.logica;

public abstract class Usuario {
	private String nickname;
	private String nombre;
	private String correo;
	private String contrasena;
	private String imagen;
	
	public Usuario(String nick, String nom, String correo, String contrasena, String img) {
		ControladorUsuario controlador = ControladorUsuario.getInstance();
		this.nickname = nick;
		this.nombre = nom;
		this.correo = correo;
		this.contrasena = controlador.hashearContrasena(contrasena);
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
		ControladorUsuario controlador = ControladorUsuario.getInstance();
		return contrasena.equals(controlador.hashearContrasena(pass));
	}
	
	public abstract DTUsuario getDatos();
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setImagen(String img) {
		this.imagen = img;
	}
	
	public void setContrasena(String contrasena) {
		ControladorUsuario controlador = ControladorUsuario.getInstance();
		this.contrasena = controlador.hashearContrasena(contrasena);
	}
	
	public abstract void modificar(DTUsuarioModificado datos);
}
