package logica;

import java.time.LocalDate;

public class DTPatrocinio {
	private String codigo;
	private TipoNivelPatrocinio tipo;
	private float monto;
	private String tipoRegistro;
	private int cantRegistros;
	private LocalDate fechaAlta;
	
	public DTPatrocinio(String codigo, TipoNivelPatrocinio tipo, float monto, String tipoRegistro, int cantRegistros, LocalDate fechaAlta) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.monto = monto;
		this.tipoRegistro = tipoRegistro;
		this.cantRegistros = cantRegistros;
		this.fechaAlta = fechaAlta;
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
}