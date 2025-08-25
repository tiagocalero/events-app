import Patrocinio;
import TipoRegistro;

public class CantRegistro {
	private int cant;
	
	private Patrocinio patrocinio;
	private TipoRegistro tipoRegistro;

	public CantRegistro(int cant, Patrocinio patrocinio, TipoRegistro tipoRegistro) {
		this.cant = cant;
		this.patrocinio = patrocinio;
		this.tipoRegistro = tipoRegistro;
		
		patrocinio.agregarCantRegistro(this);
		tipoRegistro.agregarCantRegistro(this);
	}
}