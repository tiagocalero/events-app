package logica;

import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import logica.Usuario;
import java.time.LocalDate;

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


    public Usuario obtener(String nick_o_correo) {
        Usuario n = usuariosNick.get(nick_o_correo);
        if (n != null) return n;
        return usuariosCorreo.get(nick_o_correo);
    }

    public List<String> organizadores() {
        return this.usuariosNick.values().stream().filter(usuario -> usuario instanceof Organizador).map(usuario->usuario.getNickname()).collect(Collectors.toList());
    }

    public List<String> asistentes() {
        return this.usuariosNick.values().stream().filter(usuario -> usuario instanceof Asistente).map(usuario->usuario.getNickname()).collect(Collectors.toList());
    }
    

	public boolean crearAsistente(String nick, String nom, String c, String cont, String ap, LocalDate nac) {
    	for(Usuario u: usuariosNick.values()) {
    		if (nick.equals(u.getNickname()) || c.equals(u.getCorreo())) return false;
    	}		
        Asistente a = new Asistente(nick, nom, c, cont, ap, nac);
        usuariosNick.put(nick, a);
        usuariosCorreo.put(c, a);
        return true;
    }
    
    public boolean crearAsistente(String nick, String nom, String c, String cont, String ap, LocalDate nac, String ins) {
    	for(Usuario u: usuariosNick.values()) {
    		if (nick.equals(u.getNickname()) || c.equals(u.getCorreo())) return false;
    	}
    	ControladorInstitucion ci = ControladorInstitucion.getInstance();
    	Institucion institucion = ci.obtenerInstitucion(ins);
        Asistente a = new Asistente(nick, nom, c, cont, ap, nac, institucion);
        usuariosNick.put(nick, a);
        usuariosCorreo.put(c, a);
        return true;
    }

	public boolean crearOrganizador(String nick, String nom, String c, String cont, String desc, String link) {
    	for(Usuario u: usuariosNick.values()) {
    		if (nick.equals(u.getNickname()) || c.equals(u.getCorreo())) return false;
    	}		
        Organizador o = new Organizador(nick, nom, c, cont, desc, link);
        usuariosNick.put(nick, o);
        usuariosCorreo.put(c, o);
        return true;
    }
	
	// Con imagenes.
	public boolean crearAsistente(String nick, String nom, String c, String cont, String img, String ap, LocalDate nac) {
    	for(Usuario u: usuariosNick.values()) {
    		if (nick.equals(u.getNickname()) || c.equals(u.getCorreo())) return false;
    	}		
        Asistente a = new Asistente(nick, nom, c, cont, img, ap, nac);
        usuariosNick.put(nick, a);
        usuariosCorreo.put(c, a);
        return true;
    }
    
    public boolean crearAsistente(String nick, String nom, String c, String img, String cont, String ap, LocalDate nac, String ins) {
    	for(Usuario u: usuariosNick.values()) {
    		if (nick.equals(u.getNickname()) || c.equals(u.getCorreo())) return false;
    	}
    	ControladorInstitucion ci = ControladorInstitucion.getInstance();
    	Institucion institucion = ci.obtenerInstitucion(ins);
        Asistente a = new Asistente(nick, nom, c, cont, img, ap, nac, institucion);
        usuariosNick.put(nick, a);
        usuariosCorreo.put(c, a);
        return true;
    }

	public boolean crearOrganizador(String nick, String nom, String c, String img, String cont, String desc, String link) {
    	for(Usuario u: usuariosNick.values()) {
    		if (nick.equals(u.getNickname()) || c.equals(u.getCorreo())) return false;
    	}		
        Organizador o = new Organizador(nick, nom, c, cont, img, desc, link);
        usuariosNick.put(nick, o);
        usuariosCorreo.put(c, o);
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
