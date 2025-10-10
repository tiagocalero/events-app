package logica;

import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import logica.Usuario;
import java.time.LocalDate;

public class ManejadorUsuario {
	private Map<String, Usuario> usuarios;
    private static volatile ManejadorUsuario instance;

    private ManejadorUsuario(){
        usuarios = new HashMap<String, Usuario>();
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


    public Usuario obtener(String nickname) {
        return usuarios.get(nickname);
    }

    public List<String> organizadores() {
        return this.usuarios.values().stream().filter(usuario -> usuario instanceof Organizador).map(usuario->usuario.getNickname()).collect(Collectors.toList());
    }

    public List<String> asistentes() {
        return this.usuarios.values().stream().filter(usuario -> usuario instanceof Asistente).map(usuario->usuario.getNickname()).collect(Collectors.toList());
    }
    
    public boolean crearAsistente(String nick, String nom, String c, String contrasena, String ap, LocalDate nac) {
    	for(Usuario u: usuarios.values()) {
    		if (nick.equals(u.getNickname()) || c.equals(u.getCorreo())) return false;
    	}		
        Asistente a = new Asistente(nick, nom, c, contrasena, ap, nac);
        usuarios.put(nick, a);
        return true;
    }
    
    public boolean crearAsistente(String nick, String nom, String c, String contrasena, String ap, LocalDate nac, String ins) {
    	for(Usuario u: usuarios.values()) {
    		if (nick.equals(u.getNickname()) || c.equals(u.getCorreo())) return false;
    	}
    	ControladorInstitucion ci = ControladorInstitucion.getInstance();
    	Institucion institucion = ci.obtenerInstitucion(ins);
        Asistente a = new Asistente(nick, nom, c, contrasena, ap, nac, institucion);
        usuarios.put(nick, a);
        return true;
    }

	public boolean crearOrganizador(String nick, String nom, String c, String contrasena, String desc, String link) {
    	for(Usuario u: usuarios.values()) {
    		if (nick.equals(u.getNickname()) || c.equals(u.getCorreo())) return false;
    	}		
        Organizador o = new Organizador(nick, nom, c, contrasena, desc, link);
        usuarios.put(nick, o);
        return true;
    }

	// Devuelve una copia del map de usuarios sobre el que se puede iterar
	public Map<String, Usuario> usuarios() {
        return Map.copyOf(usuarios);
    }

    public void liberarMemoria() {
        usuarios.clear();
    }
}
