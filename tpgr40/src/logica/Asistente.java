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
	
	public Asistente(String nick, String nom, String c, String contrasena, String ap, LocalDate fecha) {
		super(nick, nom, c, contrasena);
		this.apellido = ap;
		this.nacimiento = fecha;
		this.institucion = null;
		this.registros = new HashSet<>();
	}
	
	public Asistente(String nick, String nom, String c, String contrasena, String ap, LocalDate fecha, Institucion ins) {
		super(nick, nom, c, contrasena);
		this.apellido = ap;
		this.nacimiento = fecha;
		this.institucion = ins;
		this.registros = new HashSet<>();
	}
	
	public void agregarRegistro(Registro reg) {
		registros.add(reg);
	}
	
	@Override
	public DTAsistente getDatos() {
		DTAsistente res;
		if (institucion != null) res = new DTAsistente(getNickname(), getNombre(), getCorreo(), apellido, nacimiento, institucion.getNombre());
		else res = new DTAsistente(getNickname(), getNombre(), getCorreo(), apellido, nacimiento);
		return res;
	}
	
	@Override
	public void modificar(DTUsuarioModificado datos) {
		ControladorInstitucion c = ControladorInstitucion.getInstance();
		
		assert datos instanceof DTAsistenteModificado;
		DTAsistenteModificado datosA = (DTAsistenteModificado) datos;
		this.setNombre(datosA.getNombre());
		this.apellido = datosA.getApellido();
		this.nacimiento = datosA.getNacimiento();
		String ins = datosA.getInstitucion();
		if (ins != null) this.institucion = c.obtenerInstitucion(ins);
	}

	//Retorna el registro con el nombre de la edicion
	public Registro getRegistro(String nombreEdicion) {
		Registro res = null;
		for (Registro registro : registros) {
	        if (registro.getEdicion().getNombre().equals(nombreEdicion)) {
	            res = registro;
	        }
		}
		return res;
	}

	public List<DTRegistro> consulta() {
		return registros.stream().map(registro -> registro.getDatos()).collect(Collectors.toList());
	}
}
