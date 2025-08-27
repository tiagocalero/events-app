package logica;
import java.util.stream.Collectors;
import Usuario;


public class ManejadorUsuario {
	private Map<String, Usuario> usuarios;
    private static ManejadorUsuario instance;

    private ManejadorUsuario() {}

    public static ManejadorUsuario getInstance() {
        if (instance == null) {
            instance = new ManejadorUsuario();
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
}