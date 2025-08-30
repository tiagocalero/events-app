package logica;

import logica.Registro;
import logica.Edicion;
import logica.Evento;
import logica.DTEdicion;
import logica.DTTRegistro;
import java.util.List;
import java.util.ArrayList;

public class TRegistro {
	
	private String nombre; 
	private String descripcion; 
	private float costo;
	private int cupo;
	private List<Registro> registro;
	private List<Edicion> edicion;
	private List<Patrocinio> patrocinios;
	
	public TRegistro(String nom, String desc, float costo, int cupo) {
		this.nombre = nom;
		this.descripcion = desc;
		this.costo = costo;
		this.cupo = cupo;
		patrocinios = new ArrayList<>();
		edicion = new ArrayList<>();
		patrocinios = new ArrayList<>();
	}
	
	/* public void agregarCantRegistro(CantRegistro cr) {
		cantReg.add(cr);
		
	} */
	public String getNombre() {
		return this.nombre;
	}
	public String getDescripcion() {
		return this.descripcion;
	}
	public float getCosto() {
		return this.costo;
	}
	public int getCupo() {
		return this.cupo;
	}

    public void agregarPatrocinio(Patrocinio p) {
    	patrocinios.add(p);
    }
}

	