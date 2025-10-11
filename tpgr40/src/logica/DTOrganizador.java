package logica;

public class DTOrganizador extends DTUsuario {
	private String descripcion;
	private String link;
	
	public DTOrganizador(String nick, String nom, String c, String img, String desc, String link) {
		super(nick, nom, c, img);
		this.descripcion = desc;
		this.link = link;
	}
	
	public String getDescripcion() { return descripcion; }
	public String getLink() { return link; }
}