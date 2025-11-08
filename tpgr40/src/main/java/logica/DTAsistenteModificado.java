package main.java.logica;
import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import main.java.publicar.LocalDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTAsistenteModificado extends DTUsuarioModificado {
	private String apellido;
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate nacimiento;
	private String institucion;

	public DTAsistenteModificado() {}

	public DTAsistenteModificado(String nom, String img, String apellido, LocalDate fecha) {
		super(nom, img);
		this.apellido = apellido;
		this.nacimiento = fecha;
	}
	
	public DTAsistenteModificado(String nom, String img, String apellido, LocalDate fecha, String institucion) {
		super(nom, img);
		this.apellido = apellido;
		this.nacimiento = fecha;
		this.institucion = institucion;
	}
	
	public String getApellido() { return apellido; }
	public LocalDate getNacimiento() { return nacimiento; }
	public String getInstitucion() { return institucion; }
}
