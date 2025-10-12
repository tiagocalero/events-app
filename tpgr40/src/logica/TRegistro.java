package logica;

import java.util.ArrayList;
import java.util.List;

public class TRegistro {
	private String nombre; 
	private String descripcion; 
	private float costo;
	private int cupo;
	private List<Registro> registro;
	
	public TRegistro(String nom, String desc, float costo, int cupo) {
		this.nombre = nom;
		this.descripcion = desc;
		this.costo = costo;
		this.cupo = cupo;
		registro = new ArrayList<>();
	}

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
	
	public int getCuposLibres() {
		return cupo - registro.size();
	}

    public void agregarRegistro(Registro nuevoRegistro) {
    	registro.add(nuevoRegistro);
    }
}

	