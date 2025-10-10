package logica;

import java.time.LocalDate;

public class DTRegistro {
    private float costo;
    private LocalDate fecha;
    private String nombreEdicion;
    private String tipo;
    private String nickname;


    public DTRegistro(float costo, LocalDate fecha, String nombreEdicion, String tipo, String nickname) {
        this.costo = costo;
        this.fecha = fecha;
        this.nombreEdicion = nombreEdicion;
        this.tipo = tipo;
        this.nickname = nickname;
    }

    public float getCosto() { return costo; }
    public LocalDate getFecha() { return fecha; }
    public String getEdicion() { return nombreEdicion; }
    public String getTipo() { return tipo; }
    public String getNickname() { return nickname; }
}