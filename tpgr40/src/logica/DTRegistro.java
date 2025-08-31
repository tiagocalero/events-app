package logica;

import java.time.LocalDate;

public class DTRegistro {
    private float costo;
    private LocalDate fecha;
    private String nombreEdicion;


    public DTRegistro(float costo, LocalDate fecha, String nombreEdicion) {
        this.costo = costo;
        this.fecha = fecha;
        this.nombreEdicion = nombreEdicion;
    }

    public float getCosto() { return costo; }
    public LocalDate getFecha() { return fecha; }
    public String getEdicion() { return nombreEdicion; }
    
}
