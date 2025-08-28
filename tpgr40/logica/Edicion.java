package logica;

import java.time.LocalDate;
import java.util.Map;
import java.util.List;
import java.util.stream.Stream;
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
    private List<Patrocinio> patrocinios;
    private List<TRegistro> tRegistros;
    private Organizador organizador;
    private Evento evento;
    private List<Registro> registros;

    public Edicion(Evento evento, Organizador organizaddor, String nombre, String sigla, String ciudad, String pais) {
        this.evento = evento;
        this.orgnaizador = organizador;
        this.nombre = nombre;
        this.sigla = sigla;
        this.ciudad = ciudad;
        this.pais = pais;
    }

	public DTEdicion getDatos() {
		List<String> codigoPatrocinios = patrocinios.stream().map(p -> p.getCodigo()).collect(Collectors.toList());

        List<String> nombreTRegistros = tRegistros.stream().map(tr -> tr.getNombre()).collect(Collectors.toList());

        List<String> apellidosAsistentes = registros.stream().map(r -> r.getApellidoAsistente()).collect(Collectors.toList());
		
		return new DTEdicion(pais, ciudad, fecha_inicio, fecha_final, fecha_alta, sigla, nombre, codigoPatrocinios, nombreTRegistros, apellidosAsistentes, organizador.getNombre());
	}
	
	public List<String> getInstituciones() {
		return patrocinios.stream().map(p -> p.getInstitucion().getNombre()).collect(Collectors.toList());
	}

    public String getNombre() {
        return this.nombre;
    }
    public List<Registro> getRegistros() {
        return List.copyOf(registros);
    }
    
    //necesito que tRegistros sea map
    /*public TRegistro obtenerTRegistro(String nombre) {
    	return 
    }*/
    		
}