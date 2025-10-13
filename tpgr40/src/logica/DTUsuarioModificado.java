package logica;
//saque el abstract pero nose si quieren que lo haga
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
