import Patrocinio;

public class Institucion {
	private List<Patrocinio> patrocinios;
	
	public existePatrocinio(Edicion e) {
		for (Patrocinio p: patrocinios) {
			if (p.buscarEdicion(e))
				return true;
		}
		
		return false;	
	}
}