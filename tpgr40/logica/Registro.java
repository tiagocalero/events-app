package logica;
import java.time.LocalDate;
import Asistente;


public class Registro{
    private float costo;
    private LocalDate fecha;
    private Asistente asistente;

    public Registro(){};

    public String getApellidoAsistente(){
        return asistente.getApellido();
    }

    public DTRegistro getDatos(){
        return new DTRegistro(costo, fecha);
    }
}
