package logica;

import java.time.LocalDate;
import java.util.List;

public class DTEdicion {
	
    private String pais;
    private String ciudad;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaAlta;
    private String sigla;
    private String nombre;
    private List<String> institucionesPatrocinadoras;
    private List<String> tRegistros;    
    private List<String> asistentes;
    private String organizador;
    private String evento;
    private String imagen;

    public DTEdicion(String evento, String pais, String ciudad, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAlta, String sigla, String nombre, List<String> institucionesPatrocinadoras, List<String> tRegistros, List<String> asistentes, String organizador, String imagen) {
    	this.pais = pais;
    	this.ciudad = ciudad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaAlta = fechaAlta;
        this.nombre = nombre;
        this.sigla = sigla;
        this.institucionesPatrocinadoras = institucionesPatrocinadoras;
        this.tRegistros = tRegistros;
        this.asistentes = asistentes;
        this.organizador = organizador;
        this.evento = evento;
        this.imagen = imagen;
    }

    // solo getters, no setters si es solo de lectura
    public String getPais() { return pais; }
    public String getCiudad() { return ciudad; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public LocalDate getFechaAlta() { return fechaAlta; }
    public String getSigla() { return sigla; }
    public String getNombre() { return nombre; }
    public String getOrganizador() { return organizador; }
    public List<String> getAsistentes() { return asistentes; }
    public List<String> getInstitucionesPatrocinadoras() { return institucionesPatrocinadoras; }
    public List<String> getTipoRegistros() { return tRegistros; }
    public String getEvento() { return evento; }
    public String getImagen() { return imagen; }    
}
