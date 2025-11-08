package main.java.logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ DTAsistenteModificado.class, DTOrganizadorModificado.class })
public class DTUsuarioModificado {
	private String nombre;
	private String imagen;

	public DTUsuarioModificado() {}
	
	public DTUsuarioModificado(String nom, String img) {
		this.nombre = nom;
		this.imagen = img;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getImagen() {
		return imagen;
	}
}
