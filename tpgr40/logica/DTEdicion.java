package logica;
import logica.DTFecha;

public class DTEdicion {
	
    private String pais;
    private String ciudad;
    private DTFecha fechaInicio;
    private DTFecha fechaFin;
    private DTFecha fechaAlta;
    private String sigla;
    private String nombre;
    private List<String> patrocinios;
    private List<String> tRegistros;    
    private List<String> asistentes;
    private String organizador;

    public DTEdicion(String pais, String ciudad, DTFecha fechaInicio, DTFecha fechaFin, DTFecha fechaAlta, String sigla, String nombre) {
    	
    	this.pais = pais;
    	this.ciudad = ciudad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombre = nombre;
        this.sigla = sigla;
        this.nombre = nombre;
    }

    // solo getters, no setters si es solo de lectura
    public String getPais() { return pais; }
    public String getCiudad() { return ciudad; }
    public String getFechaInicio() { return fechaInicio; }
    public String getFechaFin() { return fechaFin; }
    public String getFechaAlta() { return fechaAlta; }
    public DTFecha getSigla() { return sigla; }
    public DTFecha getNombre() { return nombre; }
}