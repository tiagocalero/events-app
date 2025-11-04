package main.java.logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTOrganizador extends DTUsuario {
	private String descripcion;
	private String link;
	
	public DTOrganizador(String nick, String nombre, String correo, String img, String desc, String link) {
		super(nick, nombre, correo, img);
		this.descripcion = desc;
		this.link = link;
	}
	
	public String getDescripcion() { return descripcion; }
	public String getLink() { return link; }
}