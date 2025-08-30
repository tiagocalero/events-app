package logica;
import logica.DTUsuarioModificado;
import java.time.LocalDate;

public class DTAsistenteModificado extends DTUsuarioModificado {
	private String apellido;
	private LocalDate nacimiento;
	
	public DTAsistenteModificado(String nom, String ap, LocalDate fecha) {
		super(nom);
		this.apellido = ap;
		this.nacimiento = fecha;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public LocalDate getNacimiento() {
		return nacimiento;
	}
}
