import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Stream;
import categoria;
import TRegistro;
import Organizador;
import Registro;
import Patrocinio;
import DTEdicion;


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
    private List<Registro> registros;


	public DTEdicion getDatos() {
		List<String> codigoPatrocinios = patrocinios.stream().map(p -> p.getCodigo()).collect(Collectors.toList());

        List<String> nombreTRegistros = tRegistros.stream().map(tr -> tr.getNombre()).collect(Collectors.toList());

        List<String> apellidosAsistentes = registros.stream().map(r -> r.getApellidoAsistente()).collect(Collectors.toList());
		
		return new DTEdicion(pais, ciudad, fecha_inicio, fecha_final, fecha_alta, sigla, nombre, codigoPatrocinios, nombreTRegistros, apellidosAsistentes, organizador.getNombre());
	}    

}