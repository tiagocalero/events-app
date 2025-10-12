package logica;

public class DTOrganizadorModificado extends DTUsuarioModificado {
	private String descripcion;
	private String link;
	
	public DTOrganizadorModificado(String nom, String img, String desc, String link) {
		super(nom, img);
		this.descripcion = desc;
		this.link = link;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getLink() {
		return link;
	}
}
