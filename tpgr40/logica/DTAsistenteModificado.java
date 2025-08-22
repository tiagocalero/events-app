import DTUsuarioModificado;

public class DTAsistenteModificado extends DTUsuarioModificado {
	private String apellido;
	private DTFecha nacimiento;
	
	public DTAsistenteModificado(String ap, DTFecha fecha) {
		this.apellido = ap;
		this.nacimiento = fecha;
	}
}