package logica;
import java.time.LocalDate;

public class DTAsistenteModificado extends DTUsuarioModificado {
	private String apellido;
	private LocalDate nacimiento;
	private String institucion;
	
	public DTAsistenteModificado(String nom, String ap, LocalDate fecha) {
		super(nom);
		this.apellido = ap;
		this.nacimiento = fecha;
		this.institucion = null;
	}
	
	public DTAsistenteModificado(String nom, String ap, LocalDate fecha, String institucion) {
		super(nom);
		this.apellido = ap;
		this.nacimiento = fecha;
		this.institucion = institucion;
	}
	
	public String getApellido() { return apellido; }
	public LocalDate getNacimiento() { return nacimiento; }
	public String getInstitucion() { return institucion; }
}
