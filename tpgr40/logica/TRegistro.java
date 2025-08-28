package logica;

import logica.Registro;
import logica.Edicion;
import logica.Evento;
import logica.DTEdicion;
import logica.DTTRegistro;
import java.util.List;

public class TRegistro {
	
	private String nombre; 
	private String descripcion; 
	private float costo;
	private int cupo;
	private List<Registro> registro;
	private List<Edicion> edicion;
	
	public TRegistro(String nom, String desc, float costo, int cupo) {
		this.nombre = nom;
		this.descripcion = desc;
		this.costo = costo;
		this.cupo = cupo;
		
	}
	
	public void agregarCantRegistro(CantRegistro cr) {
		cantReg.add(cr);
		
	}
	public float getNombre() {
		return this.nombre;
	}
	public float getDesc() {
		return this.descripcion;
	}
	public float getCosto() {
		return this.costo;
	}
	public float getCupo() {
		return this.cupo;
	}

}

	