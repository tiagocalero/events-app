package logica;

import logica.Registro;
import logica.Edicion;
import logica.Evento;
import logica.DTEdicion;
import logica.DTTRegistro;


//hay una coleccion (map) de tipos de registro globales llamada TRegistrosGlobales
public class TRegistro {
	
	private String nombre; 
	private String descripcion; 
	private float costo;
	private int cupo;
	private set<Registro> registro;
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
	public set<DTEdicion> seleccionarEvento(Evento e){
		
		Set<DTEdicion> resultado = new HashSet<>();
		
		for (Edicion ed : e.obtenerEdiciones.values()) {
			DTEdicion dte = new DTEdicion(
					
				 ed.getPais(),
		         ed.getCiudad(),
		         ed.getFechaInicio(),
		         ed.getFechaFin(),
		         ed.getFechaAlta(),
		         ed.getSigla(),
		         ed.getNombre()
		        );
		    resultado.add(dte);
			
        }
		return resultado;
	
	}
	
	public boolean crearTipoRegistro(Edicion ed, String nombre, String descripcion, float costo, int cupo) {
		if (ed.contains(nombre))
			return false;
		
		TRegistro tr = new TipoRegistro(nombre, descripcion, costo, cupo);
		
		ed.add(tr);
		return true;
	}
	
	public DTTRegistro seleccionarTipoRegistro(String nombre){
			
		TRegistro tr = TRegistrosGlobales.get(nombre);
		
		DTTRegistro dtr = new DTTRegistro(
			tr.getCupo(), 
			tr.getCosto(), 
			tr.getNombre(), 
			tr.getDesc()
		);
		
		return dtr;
		

	}
	
	public set<DTTRegistro> seleccionarTipoRegistro(Edicion ed){
		
		Set<DTEdicion> resultado = new HashSet<>();
		
		for (TipoRegistro tr : ed.obtenerEdiciones.values()) {
			DTEdicion dtr = new DTTRegistro(
					
				dtr.getCupo(),
				dtr.getCosto(),
				dtr.getNombre(),
				dtr.getDescripcion()
		        );
		    resultado.add(dtr);
			
        }
		return resultado;
	
	}
}

	