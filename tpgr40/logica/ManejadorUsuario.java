package logica;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;
import Usuario;


public class ManejadorUsuario {
	private Map<String, Usuario> usuarios;
    private Map<String, Usuario> emails;
    private static volatile ManejadorUsuario instance;

    private ManejadorUsuario(){
        usuarios = new HashMap<String, Usuario>();
        emails = new HashMap<String, Usuario>();
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
        return this.usuarios.values().stream().filter(usuario -> usuario instanceof Organizador).map(usuario->usuario.getNickname()).collect(Collectors.tolist());
    }

    public List<String> usuarios() {
        return this.usuarios.values().stream().map(usuario->usuario.getNickname()).collect(Collectors.tolist());
    }

    public bool crearAsistente(String nick, String nom, String c, String ap, DTFecha nac) {
        if(!(usuarios.containsKey(nick) || emails.containsKey(c))){
            Asistente a = new Asistente(nick, nom, c, ap, nac);
            usuarios.put(nick, a);
            emails.put(c, a);
            return true;
        }
        return false;
    }

	public bool crearOrganizador(String nick, String nom, String c, String desc, String link) {
        if(!(usuarios.containsKey(nick) || emails.containsKey(c))){
            Organizador o = new Organizador(nick, nom, c, desc, link);
            usuarios.put(nick, o);
            emails.put(c, o);
            return true;
        }
        return false;
    }

    public void liberarMemoria() {
        usuarios.clear();
        emails.clear();
    }
}
