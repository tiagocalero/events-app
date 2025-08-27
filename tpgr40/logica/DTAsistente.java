package logica;
import logica.DTFecha;

public class DTAsistente extends DTUsuario {
	private String apellido;
	private DTFecha nacimiento;
	
	public DTAsistente(String nick, String nom, String c, String ap, DTFecha fecha) {
		super(nick, nom, c);
		this.apellido = ap;
		this.nacimiento = fecha;
	}
	
	public String getApellido( ) { return apellido; }
	public DTFecha getNacimiento( ) { return nacimiento; }
}