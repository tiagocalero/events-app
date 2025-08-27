package logica;
import logica.DTFecha;

public class DTTRegistro {
	
    private int cupo;
    private float costo;
    private String nombre;
    private String descripcion;
    

    public DTTRegistro(int cupo, float costo, String nombre, String descripcion) {
        this.cupo = cupo;
        this.costo = costo;
        this.nombre = nombre;
        this.descripcion = descripcion;

    }

    // solo getters, no setters si es solo de lectura
    public int getCupo() { return cupo; }
    public float getCosto() { return costo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }

}
