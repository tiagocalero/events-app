package main.java.logica;

import java.time.LocalDate;

public class Registro {
    private float costo;
    private LocalDate fecha;
    private Asistente asistente;
    private Edicion edicion;
    private TRegistro tipo;
    private boolean patrocinio;
    private boolean asistencia;

    public Registro(float costo, LocalDate fecha, Asistente asistente, Edicion edicion, TRegistro tipo, Boolean patrocinio) {
        this.costo = costo;
        this.fecha = fecha;
        this.asistente = asistente;
        this.edicion = edicion;
        this.tipo = tipo;
        tipo.agregarRegistro(this);
        this.patrocinio = patrocinio;
        this.asistencia = false;
    };

    public String getNicknameAsistente(){
        return asistente.getNickname();
    }
    
    public Asistente getAsistente() { return asistente; }

    public DTRegistro getDatos(){
        boolean esConfirmable = edicion.getFechaInicio().isBefore(LocalDate.now())
                                || edicion.getFechaInicio().equals(LocalDate.now());
        return new DTRegistro(costo, fecha, edicion.getEvento(), edicion.getNombre(), tipo.getNombre(), asistente.getNickname(), patrocinio, asistencia, esConfirmable);
    }
    
    public Edicion getEdicion( ) {
    	return edicion;
    }
    
    public boolean getPatrocinio() {
    	return patrocinio;
    }

    public boolean getAsistencia() {
    	return asistencia;
    }
    
    public void registrarAsistencia() {
    	this.asistencia = true;
    }
    
    
}
