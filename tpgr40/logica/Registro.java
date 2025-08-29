package logica;

import java.time.LocalDate;
import logica.Asistente;
import logica.Edicion;

public class Registro{
    private float costo;
    private LocalDate fecha;
    private Asistente asistente;
    private Edicion edicion;

    public Registro(){};

    public String getNicknameAsistente(){
        return asistente.getNickname();
    }
    public Asistente getAsistente() { return asistente; }

    public DTRegistro getDatos(){
        return new DTRegistro(costo, fecha, edicion.getNombre());
    }
}
