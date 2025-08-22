package logica;
import Usuario;

public class Organizador extends Usuario {
	private String descripcion;
	private String link;
	
	public Organizador(String nick, String nom, String c, String desc, String link) {
		super(nick, nom, c);
		this.descripcion = desc;
		this.link = link;
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