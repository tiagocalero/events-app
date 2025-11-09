package main.java.logica;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTRegistro {
    private float costo;
    private LocalDate fecha;
    private String nombreEvento;
    private String nombreEdicion;
    private String tipo;
    private String nicknameUsuario;
    private boolean patrocinio;
    private boolean asistencia;
    private boolean confirmable;

    public DTRegistro() {}

    public DTRegistro(float costo, LocalDate fecha, String nombreEvento, String nombreEdicion, String tipo, String nicknameUsuario, boolean patrocinio, boolean asistencia, boolean confirmable) {
        this.costo = costo;
        this.fecha = fecha;
        this.nombreEvento = nombreEvento;
        this.nombreEdicion = nombreEdicion;
        this.tipo = tipo;
        this.nicknameUsuario = nicknameUsuario;
        this.patrocinio = patrocinio;
        this.asistencia = asistencia;
        this.confirmable = confirmable;
    }

    public float getCosto() { return costo; }
    public LocalDate getFecha() { return fecha; }
    public String getEvento() { return nombreEvento; }
    public String getEdicion() { return nombreEdicion; }
    public String getTipo() { return tipo; }
    public String getNombre() { return nicknameUsuario; }
    public boolean getPatrocinio( ) {return patrocinio;}
    public boolean getAsistencia() { return asistencia;}
    public boolean getConfirmable() { return confirmable;}
}