import java.time.LocalDate;

import DTPatrocinio;
import Edicion;
import Institucion;
import CantRegistro;

public class Patrocinio {
	private String codigo;
	private float monto;
	private LocalDate fecha;
	
	private Edicion edicion;
	private Institucion institucion;
	private List<CantRegistro> cantRegistros;
	
	public Patrocinio(DTPatrocinio d, Edicion e, Institucion i, localDate fecha) {
		edicion = e;
		institucion = i;
		
		codigo = d.codgio;
		monto = d.monto;
		this.fecha = fecha;

		i.agregarPatrocinio(this);
		e.agregarPatrocinio(this);
	}
	
	public void agregarCantRegistro(CantRegistro cr) {
		cantRegistros.add(cr)
	}
}