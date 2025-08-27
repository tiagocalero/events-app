package logica;
import logica.DTUsuarioModificado;

public class DTOrganizadorModificado extends DTUsuarioModificado {
	private String descripcion;
	private String link;
	
	public DTOrganizadorModificado(String nom, String desc, String link) {
		super(nom);
		this.descricpcion = desc;
		this.link = link;
	}
}
