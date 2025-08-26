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
	
	private Edicion edicion;
	private Institucion institucion;
	private List<CantRegistro> cantRegistros;
	
	public Patrocinio(String codigo, float monto, TipoNivelPatrocinio tipo, localDate fecha, Edicion e, Institucion i) {
		edicion = e;
		institucion = i;
		
		this.codigo = codgio;
		this.monto = monto;
		this.tipo = tipo;
		this.fecha = fecha;

		i.agregarPatrocinio(this);
		e.agregarPatrocinio(this);
	}
	
	public void agregarCantRegistro(CantRegistro cr) {
		cantRegistros.add(cr)
	}
}