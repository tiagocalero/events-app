package logica;
import logica.DTUsuarioModificado;

public class DTAsistenteModificado extends DTUsuarioModificado {
	private String apellido;
	private DTFecha nacimiento;
	
	public DTAsistenteModificado(String nom, String ap, DTFecha fecha) {
		super(nom);
		this.apellido = ap;
		this.nacimiento = fecha;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public DTFecha getNacimiento() {
		return nacimiento;
	}
}
