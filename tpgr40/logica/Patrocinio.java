import java.time.LocalDate;

import DTPatrocinio;
import Edicion;
import Institucion;
import CantRegistro;

public class Patrocinio {
	private String codigo;
	private float monto;
	private TipoNivelPatrocinio tipo;
	private LocalDate fecha;
	private int cantRegistros;
	
	private Edicion edicion;
	private Institucion institucion;
	private TipoRegistro tp;
	
	public Patrocinio(String codigo, float monto, TipoNivelPatrocinio tipo, localDate fecha, int cantRegistros, Edicion e, Institucion i, TipoRegistro tp) {
		edicion = e;
		institucion = i;
		
		this.codigo = codgio;
		this.monto = monto;
		this.tipo = tipo;
		this.fecha = fecha;
		this.cantRegistros = cantRegistros;

		tp.agregarPatrocinio(this);
		i.agfregarPatrocinio(this);
		e.agregarPatrocinio(this);
	}
	
	public DTPatrocinio getDatos() {
		return new DTPatrocinio(codigo, tipo, monto, tp.getNombre(), cantRegistros);
	}

	public String getCodigo() {
		return codigo;
	}
	
	public boolean buscarEdicion(Edicion e) {
		return e == edicion;
	}
	
	public void setCantRegistro(CantRegistro cr) {
		cantRegistro = cr;
	}
}