package logica;

import java.time.LocalDate;
import logica.DTPatrocinio;
import logica.Edicion;
import logica.Institucion;
import logica.TipoNivelPatrocinio;

public class Patrocinio {
	private String codigo;
	private float monto;
	private TipoNivelPatrocinio tipo;
	private LocalDate fecha;
	private int cantRegistros;
	
	private Edicion edicion;
	private Institucion institucion;
	private TRegistro tp;
	
	public Patrocinio(String codigo, float monto, TipoNivelPatrocinio tipo, LocalDate fecha, int cantRegistros, Edicion e, Institucion i, TRegistro tp) {
		edicion = e;
		institucion = i;
		
		this.codigo = codigo;
		this.monto = monto;
		this.tipo = tipo;
		this.fecha = fecha;
		this.cantRegistros = cantRegistros;
		this.tp = tp;

		i.agregarPatrocinio(this);
		e.agregarPatrocinio(this);
	}
	
	public DTPatrocinio getDatos() {
		return new DTPatrocinio(codigo, tipo, monto, tp.getNombre(), cantRegistros, fecha);
	}

	public String getCodigo() {
		return codigo;
	}
	
	public boolean buscarEdicion(Edicion e) {
		return e == edicion;
	}
	
	public void setCantRegistro(int cr) {
		cantRegistros = cr;
	}
}