package main.java.logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
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
