import DTUsuarioModificado;

public class DTOrganizadorModificado extends DTUsuarioModificado {
	private String descripcion;
	private String link;
	
	public DTOrganizadorModificado(String desc, String link) {
		this.descricpcion = desc;
		this.link = link;
	}
}