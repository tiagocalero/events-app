package logica;
import logica.Usuario;
import java.time.LocalDate;
import logica.DTAsistente;
import logica.Institucion;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import logica.Registro;

public class Asistente extends Usuario {
	private String apellido;
	private LocalDate nacimiento;
	private Institucion institucion;
	private Set<Registro> registros;
	
	public Asistente(String nick, String nom, String c, String ap, LocalDate fecha) {
		super(nick, nom, c);
		this.apellido = ap;
		this.nacimiento = fecha;
		this.institucion = null;
		this.registros = new HashSet<>();
	}
	
	@Override
	public DTAsistente getDatos() {
		DTAsistente res = new DTAsistente(getNickname(), getNombre(), getCorreo(), apellido, nacimiento);
		return res;
	}
	
	@Override
	public void modificar(DTUsuarioModificado datos) {
		assert datos instanceof DTAsistenteModificado;
		DTAsistenteModificado datosA = (DTAsistenteModificado) datos;
		this.setNombre(datosA.getNombre());
		this.apellido = datosA.getApellido();
		this.nacimiento = datosA.getNacimiento();
	}

	public String getApellido() {
		return apellido;
	}

	public List<DTRegistro> consulta() {
		return registros.Stream().map(registro -> registro.getDatos()).collect(Collectors.toList()); //?? chequear
	}
}
