package logica;
import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTAsistente extends DTUsuario {
	private String apellido;
	private LocalDate nacimiento;
	private String institucion;

	public DTAsistente(String nick, String nombre, String correo, String img, String apellido, LocalDate fecha) {
		super(nick, nombre, correo, img);
		this.apellido = apellido;
		this.nacimiento = fecha;
		this.institucion = null;
	}
	
	public DTAsistente(String nick, String nombre, String correo, String img, String apellido, LocalDate fecha, String institucion) {
		super(nick, nombre, correo, img);
		this.apellido = apellido;
		this.nacimiento = fecha;
		this.institucion = institucion;
	}
	
	public String getApellido() { return apellido; }
	public LocalDate getNacimiento() { return nacimiento; }
	public String getInstitucion() { return institucion; }
}