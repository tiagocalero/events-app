package main.java.logica;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ManejadorUsuario {
	private Map<String, Usuario> usuariosNick;
	private Map<String, Usuario> usuariosCorreo;
    private static volatile ManejadorUsuario instance;

    private ManejadorUsuario(){
        usuariosNick = new HashMap<String, Usuario>();
        usuariosCorreo = new HashMap<String, Usuario>();
    }

    public static ManejadorUsuario getInstance() {
        if (instance == null) {
            synchronized (ManejadorUsuario.class) {
                if (instance == null) {
                    instance = new ManejadorUsuario();
                }
            }
        }
        return instance;
    }

    public Usuario obtener(String nickOCorreo) {
        Usuario usuario = usuariosNick.get(nickOCorreo);
        
        if (usuario != null)
        	return usuario;
        
        return usuariosCorreo.get(nickOCorreo);
    }

    public List<String> organizadores() {
        return this.usuariosNick
        		   .values()
        		   .stream()
        		   .filter(usuario -> usuario instanceof Organizador)
        		   .map(usuario -> usuario.getNickname()).collect(Collectors.toList());
    }

    public List<String> asistentes() {
        return this.usuariosNick
        		   .values()
        		   .stream()
        		   .filter(usuario -> usuario instanceof Asistente)
        		   .map(usuario -> usuario.getNickname()).collect(Collectors.toList());
    }
	
	// Con imagenes.
	public boolean crearAsistente(String nick, String nom, String correo, String contrasena, String img, String apellido, LocalDate fechaNacimiento) {
		if (usuariosNick.containsKey(nick) || usuariosCorreo.containsKey(correo))
    		return false;
		
        Asistente asistente = new Asistente(nick, nom, correo, contrasena, img, apellido, fechaNacimiento);
        usuariosNick.put(nick, asistente);
        usuariosCorreo.put(correo, asistente);
        return true;
    }
    
    public boolean crearAsistente(String nickname, String nombre, String correo, String contasena, String img, String apellido, LocalDate fechaNacimiento, String nombreInstitucion) {
    	if (usuariosNick.containsKey(nickname) || usuariosCorreo.containsKey(correo))
    		return false;
    	
    	ManejadorInstitucion manejador = ManejadorInstitucion.getInstance();
    	Institucion institucion = manejador.obtener(nombreInstitucion);
        Asistente asistente = new Asistente(nickname, nombre, correo, contasena, img, apellido, fechaNacimiento, institucion);
        usuariosNick.put(nickname, asistente);
        usuariosCorreo.put(correo, asistente);
        return true;
    }

	public boolean crearOrganizador(String nickname, String nombre, String correo, String contrasena, String img, String desc, String link) {
    	if (usuariosNick.containsKey(nickname) || usuariosCorreo.containsKey(correo))
    		return false;
				
        Organizador organizador = new Organizador(nickname, nombre, correo, contrasena, img, desc, link);
        usuariosNick.put(nickname, organizador);
        usuariosCorreo.put(correo, organizador);
        return true;
    }

	// Devuelve una copia del map de usuarios sobre el que se puede iterar
	public Map<String, Usuario> usuarios() {
        return Map.copyOf(usuariosNick);
    }

    public void liberarMemoria() {
        usuariosNick.clear();
        usuariosCorreo.clear();
    }
}
