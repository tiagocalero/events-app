package logica;
import java.time.LocalDate;

public class DTAsistente extends DTUsuario {
	private String apellido;
	private LocalDate nacimiento;
	
	public DTAsistente(String nick, String nom, String c, String ap, LocalDate fecha) {
		super(nick, nom, c);
		this.apellido = ap;
		this.nacimiento = fecha;
	}
	public String getApellido() { return apellido; }
	public LocalDate getNacimiento() { return nacimiento; }
}