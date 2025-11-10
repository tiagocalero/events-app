package main.java.logica;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Usuario {
	private String nickname;
	private String nombre;
	private String correo;
	private String contrasena;
	private String imagen;
	private Set<String> seguidos;
	private Set<String> seguidores;
	
	public Usuario(String nick, String nom, String correo, String contrasena, String img) {
		ControladorUsuario controlador = ControladorUsuario.getInstance();
		this.nickname = nick;
		this.nombre = nom;
		this.correo = correo;
		this.contrasena = controlador.hashearContrasena(contrasena);
		this.imagen = img;
		this.seguidos = new java.util.HashSet<>();
		this.seguidores = new java.util.HashSet<>();
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
	
	public List<String> getSeguidos() {
		return seguidos.stream().collect(Collectors.toList());
	}

	public List<String> getSeguidores() {
		return seguidores.stream().collect(Collectors.toList());
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
	
	public void agregarSeguidor(String nickSeguido) {
		seguidores.add(nickSeguido);
	}
	
	public void agregarSeguido(String nickSeguido) {
		seguidos.add(nickSeguido);
	}
	
	public boolean esSeguido(String nickSeguido) {
		return seguidos.contains(nickSeguido);
	}
	
	public void eliminarSeguidor(String nickSeguido) {
		seguidores.remove(nickSeguido);
	}

	public void eliminarSeguido(String nickSeguido) {
		seguidos.remove(nickSeguido);
	}
	
	public abstract void modificar(DTUsuarioModificado datos);
}
