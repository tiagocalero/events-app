package logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTUsuarioModificado {
	private String nombre;
	private String imagen;
	
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
