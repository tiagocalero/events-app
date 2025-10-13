package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Edicion {
	private String pais;
	private String ciudad;
	private LocalDate fechaAlta;
	private LocalDate fechaFinal;
	private LocalDate fechaInicio;
	private String sigla;
    private String nombre;
    private TipoEstado estado;
    private List<Patrocinio> patrocinios;
    private Map<String, TRegistro> tRegistros;
    private Organizador organizador;
    private Evento evento;
    private List<Registro> registros;
    private String imagen;

    public Edicion(
			Evento evento,
			Organizador organizador,
			String nombre,
			String sigla,
			LocalDate fechaInicio,
			LocalDate fechaFin,
			LocalDate fechaAlta,
			String ciudad,
			String pais,
			String imagen
	) {
        this.evento = evento;
        this.organizador = organizador;
        this.nombre = nombre;
        this.sigla = sigla;
        this.ciudad = ciudad;
        this.pais = pais;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFin;
        this.fechaAlta = fechaAlta;
        this.estado = TipoEstado.Ingresada;
        this.patrocinios = new ArrayList<Patrocinio>();
        this.tRegistros = new HashMap<String, TRegistro>();
        this.registros = new ArrayList<>();
        this.imagen = imagen;
        
        organizador.agregarEdicion(this);
        evento.agregarEdicion(this);
    }

	public DTEdicion getDatos() {
		List<String> institucionesPatrocinadoras = getInstitucionesPatrocinios();
		List<String> nombreTRegistros = new ArrayList<>(tRegistros.keySet());
        List<String> nickAsistentes =
        		registros.stream()
        		         .map(r -> r.getNicknameAsistente())
        		         .collect(Collectors.toList());
        

		return new DTEdicion(evento.getNombre(), pais, ciudad, fechaInicio, fechaFinal, fechaAlta, sigla, nombre, institucionesPatrocinadoras, nombreTRegistros, nickAsistentes, organizador.getNickname(), imagen);
	}
	
    public String getNombre() {
        return this.nombre;
    }

    public String getSigla() {
        return this.sigla;
    }

    public LocalDate getFechaAlta() {
        return this.fechaAlta;
    }
    
    public LocalDate getFechaFinal() {
        return this.fechaFinal;
    }

    public LocalDate getFechaInicio() {
        return this.fechaInicio;
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
    
    public TipoEstado getEstado() {
        return this.estado;
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
	
    public void agregarPatrocinio(Patrocinio patrocinio) {
    	patrocinios.add(patrocinio);
    }
    
    public List<String> getCodigosPatrocinios() {
    	return new ArrayList<>(
    			patrocinios.stream()
    					   .map(p -> p.getCodigo())
    					   .collect(Collectors.toList())
    		);
    }
    
    public List<String> getInstitucionesPatrocinios() {
    	return new ArrayList<>(
			patrocinios.stream()
					   .map(p -> p.getNombreInstitucion())
					   .collect(Collectors.toList())
		);
    }
    
    public void agregarTRegistro(TRegistro tipoRegistro) {
    	tRegistros.put(tipoRegistro.getNombre(), tipoRegistro);
    }

    public TRegistro getTipoRegistro(String nombre) {
        return tRegistros.get(nombre);
    }
    
    public void agregarRegistro(Registro registro) {
    	registros.add(registro);
    }
    
    public void confirmar() {
    	estado = TipoEstado.Aceptada;
    }
    
    public void rechazar() {
    	estado = TipoEstado.Rechazada;
    }
}
