import Patrocinio;

public class Institucion {
	private String nombre;
	private String desc;
	private String url;
	private List<Patrocinio> patrocinios;
	
	public Institucion (String nombre, String desc, String url) {
		this.nombre = nombre;
		this.desc = desc;
		this.url = url;
	}	
	
	public existePatrocinio(Edicion e) {
		for (Patrocinio p: patrocinios) {
			if (p.buscarEdicion(e))
				return true;
		}
		
		return false;	
	}
}