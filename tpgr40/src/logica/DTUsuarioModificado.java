package logica;

public abstract class DTUsuarioModificado {
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
