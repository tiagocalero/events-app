package logica;
import DTFecha;
import Usuario;
import DTAsistente;

public class Asistente extends Usuario {
	private String apellido;
	private DTFecha nacimiento;
	
	public Asistente(String nick, String nom, String c, String ap, DTFecha fecha) {
		super(nick, nom, c);
		this.apellido = ap;
		this.nacimiento = fecha;
	}
	
	@override
	public DTAsistente getDatos() {
		DTAsistente res = new DTAsistente(getNickname(), getNombre(), getCorreo(), apellido, nacimiento);
		return res;
	}
	
	@override
	public void modificar(DTUsuarioModificado datos) {
		assert datos instanceof DTAsistenteModificado;
		DTAsistenteModificado datosA = (DTAsistenteModificado) datos;
		this.setNombre(datosA.nombre);
		this.apellido = datosA.apellido;
		this.nacimiento = datosA.nacimiento;
	}
}