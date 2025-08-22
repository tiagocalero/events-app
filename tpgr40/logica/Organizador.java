package logica;
import Usuario;
import edicion;
import DTOrganizador;
import java.util.HashMap;
import java.util.Map;

public class Organizador extends Usuario {
	private String descripcion;
	private String link;
	private map<
	
	public Organizador(String nick, String nom, String c, String desc, String link) {
		super(nick, nom, c);
		this.descripcion = desc;
		this.link = link;
	}
	
	@override
	public DTOrganizador getDatos() {
		DTOrganizador res = new DTOrganizador(getNickname(), getNombre(), getCorreo(), descripcion, link);
		return res;
	}
	
	@override
	public void modificar(DTUsuarioModificado datos) {
		assert datos instanceof DTOrganizadorModificado;
		DTOrganizadorModificado datosO = (DTOrganizadorModificado) datos;
		this.setNombre(datosO.nombre);
		this.descripcion = datosO.descripcion;
		this.link = datosO.link;
	}
}