package logica;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import logica.Categoria;
import logica.TRegistro;
import logica.Organizador;
import logica.Registro;
import logica.Patrocinio;
import logica.DTEdicion;
import logica.Evento;

public class Edicion {
	private String pais;
	private String ciudad;
	private LocalDate fecha_alta;
	private LocalDate fecha_final;
	private LocalDate fecha_inicio;
	private String sigla;
    private String nombre;
    private Map<String, Patrocinio> patrocinios;
    private Map<String, TRegistro> tRegistros;
    private Organizador organizador;
    private Evento evento;
    private List<Registro> registros;

    public Edicion(Evento evento, Organizador organizador, String nombre, String sigla, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAlta, String ciudad, String pais) {
        this.evento = evento;
        this.organizador = organizador;
        this.nombre = nombre;
        this.sigla = sigla;
        this.ciudad = ciudad;
        this.pais = pais;
        this.fecha_inicio = fechaInicio;
        this.fecha_final = fechaFin;
        this.fecha_alta = fechaAlta;
        this.patrocinios = new HashMap<String, Patrocinio>();
        this.tRegistros = new HashMap<String, TRegistro>();
        this.registros = new ArrayList<>();
        
        organizador.agregarEdicion(this);
        evento.agregarEdicion(this);
    }

	public DTEdicion getDatos() {
		List<String> codigoPatrocinios = new ArrayList<>(patrocinios.keySet());
		List<String> nombreTRegistros = new ArrayList<>(patrocinios.keySet());
        List<String> nickAsistentes = registros.stream().map(r -> r.getNicknameAsistente()).collect(Collectors.toList());

		return new DTEdicion(pais, ciudad, fecha_inicio, fecha_final, fecha_alta, sigla, nombre, codigoPatrocinios, nombreTRegistros, nickAsistentes, organizador.getNickname());
	}
	
	public List<String> getInstituciones() {
		return patrocinios.values().stream().map(p -> p.getInstitucion().getNombre()).collect(Collectors.toList());
	}

    public String getNombre() {
        return this.nombre;
    }

    public String getSigla() {
        return this.sigla;
    }

    public LocalDate getFechaAlta() {
        return this.fecha_alta;
    }
    
    public LocalDate getFechaFinal() {
        return this.fecha_final;
    }

    public LocalDate getFechaInicio() {
        return this.fecha_inicio;
    }
    
    public String getPais() {
        return this.pais;
    }

    public String getCiudad() {
        return this.ciudad;
    }
    public String getEvento() {
        String nombreEv = evento.getNombre();
        return nombreEv;
    }
    
    public Organizador getOrganizador() {
        return this.organizador;
    }
    
    public List<Registro> getRegistros() {
        return new ArrayList<>(registros);
    }
    
	public List<TRegistro> getTRegistros() {
	    return new ArrayList<>(tRegistros.values());
	}
	
    public void agregarPatrocinio(Patrocinio p) {
    	patrocinios.put(p.getCodigo(), p);
    }
    
    public List<String> getCodigosPatrocinios() {
    	return new ArrayList<>(patrocinios.keySet());
    }
    
    public void agregarTRegistro(TRegistro tr) {
    	tRegistros.put(tr.getNombre(), tr);
    }
    		
    		
    public Patrocinio getPatrocinio(String codigo) {
    	return patrocinios.get(codigo);
    }

    public TRegistro getTipoRegistro(String nombre) {
        return tRegistros.get(nombre);
    }
    
    public void agregarRegistro(Registro r) {
    	registros.add(r);
    }
}
