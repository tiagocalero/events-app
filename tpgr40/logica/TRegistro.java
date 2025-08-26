
import Registro;
import Edicion;
import Evento;
import DTEdicion;
import DTTRegistro;


//hay una coleccion (map) de tipos de registro globales llamada TRegistrosGlobales
public class TRegistro {
	
	private String nombre; 
	private String descripcion; 
	private float costo;
	private int cupo;
	private set<Registro> registro;
	private Edicion edicion;
	private set<cantRegistro> cantReg;
	
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
	
	public bool crearTipoRegistro(Edicion ed, String nombre, String descripcion, float costo, int cupo) {
		if ed.contains(nombre){
			return false;
		}
		TRegistro tr = new TipoRegistro(nombre, descripcion, costo, cupo);
		
		ed.add(tr);
		
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
}

	