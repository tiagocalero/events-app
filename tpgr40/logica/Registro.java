package logica;
import java.time.LocalDate;
import Asistente;


public class Registro{
    private float costo;
    private LocalDate fecha;
    private Asistente asistente;


    public String getApellidoAsistente(){
        return asistente.getApellido();
    }
}