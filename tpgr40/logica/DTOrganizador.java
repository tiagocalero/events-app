package logica;

public class DTOrganizador extends DTUsuario {
	private String descripcion;
	private String link;
	
	public DTOrganizador(String nick, String nom, String c, String desc, String link) {
		super(nick, nom, c);
		this.descripcion = desc;
		this.link = link;
	}
	
}