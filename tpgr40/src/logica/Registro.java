package logica;

import java.time.LocalDate;

public class Registro{
    private float costo;
    private LocalDate fecha;
    private Asistente asistente;
    private Edicion edicion;
    private TRegistro tipo;

    public Registro(float costo, LocalDate fecha, Asistente asistente, Edicion edicion, TRegistro tipo) {
        this.costo = costo;
        this.fecha = fecha;
        this.asistente = asistente;
        this.edicion = edicion;
        this.tipo = tipo;
        tipo.agregarRegistro(this);
    };

    public String getNicknameAsistente(){
        return asistente.getNickname();
    }
    
    public Asistente getAsistente() { return asistente; }

    public DTRegistro getDatos(){
        return new DTRegistro(costo, fecha, edicion.getNombre(), tipo.getNombre(), asistente.getNickname());
    }
    
    public Edicion getEdicion( ) {
    	return edicion;
    }
}
