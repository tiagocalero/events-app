package logica;
import java.time.LocalDate;

public class DTAsistente extends DTUsuario {
	private String apellido;
	private LocalDate nacimiento;
	private String institucion;
	
	public DTAsistente(String nick, String nom, String c, String ap, LocalDate fecha) {
		super(nick, nom, c);
		this.apellido = ap;
		this.nacimiento = fecha;
		this.institucion = null;
	}
	
	public DTAsistente(String nick, String nom, String c, String ap, LocalDate fecha, String institucion) {
		super(nick, nom, c);
		this.apellido = ap;
		this.nacimiento = fecha;
		this.institucion = institucion;
	}
	
	public DTAsistente(String nick, String nom, String c, String img, String ap, LocalDate fecha) {
		super(nick, nom, c, img);
		this.apellido = ap;
		this.nacimiento = fecha;
		this.institucion = null;
	}
	
	public DTAsistente(String nick, String nom, String c, String img, String ap, LocalDate fecha, String institucion) {
		super(nick, nom, c, img);
		this.apellido = ap;
		this.nacimiento = fecha;
		this.institucion = institucion;
	}
	
	public String getApellido() { return apellido; }
	public LocalDate getNacimiento() { return nacimiento; }
	public String getInstitucion() { return institucion; }
}