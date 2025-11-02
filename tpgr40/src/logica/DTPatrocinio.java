package logica;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import publicar.LocalDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTPatrocinio {
	private String codigo;
	private TipoNivelPatrocinio tipo;
	private float monto;
	private String tipoRegistro;
	private int cantRegistros;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate fechaAlta;
	private String institucion;
	private String imagenInstitucion;
	
	public DTPatrocinio() {}
	
	public DTPatrocinio(String codigo, TipoNivelPatrocinio tipo, float monto, String tipoRegistro, int cantRegistros, LocalDate fechaAlta, String institucion, String imagenInstitucion) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.monto = monto;
		this.tipoRegistro = tipoRegistro;
		this.cantRegistros = cantRegistros;
		this.fechaAlta = fechaAlta;
		this.institucion = institucion;
		this.imagenInstitucion = imagenInstitucion;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public TipoNivelPatrocinio getTipo() {
		return tipo;
	}
	
	public float getMonto() {
		return monto;
	}
	
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	
	public int getCantRegistros() {
		return cantRegistros;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	
	public String getInstitucion() {
		return institucion;
	}

	public String getImagenInstitucion() {
		return imagenInstitucion;
	}
}