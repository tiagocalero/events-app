package logica;
import logica.DTFecha;
import logica.Usuario;
import logica.DTAsistente;
import logica.Institucion;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;
import logica.Registro;

public class Asistente extends Usuario {
	private String apellido;
	private DTFecha nacimiento;
	private Institucion institucion;
	private set<Registro> registros;
	
	public Asistente(String nick, String nom, String c, String ap, DTFecha fecha) {
		super(nick, nom, c);
		this.apellido = ap;
		this.nacimiento = fecha;
		this.institucion = NULL;
		this.registros = new HashMap<Registro>();
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
		this.setNombre(datosA.nombre);
		this.apellido = datosA.apellido;
		this.nacimiento = datosA.nacimiento;
	}

	public String getApellido() {
		return apellido;
	}

	@Override
	public List<DTRegistro> consulta() {
		return registros.stream().map(registros->registros.getDatos()).collect(Collectors.tolist()); //?? chequear
	}
}
