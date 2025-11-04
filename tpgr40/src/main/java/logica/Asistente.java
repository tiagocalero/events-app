package main.java.logica;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

public class Asistente extends Usuario {
	private String apellido;
	private LocalDate nacimiento;
	private Institucion institucion;
	private Set<Registro> registros;
	
	public Asistente(String nick, String nombre, String correo, String contrasena, String img, String apellido, LocalDate fecha) {
		super(nick, nombre, correo, contrasena, img);
		this.apellido = apellido;
		this.nacimiento = fecha;
		this.institucion = null;
		this.registros = new HashSet<>();
	}
	
	
	public Asistente(String nick, String nombre, String correo, String contrasena, String img, String apellido, LocalDate fecha, Institucion ins) {
		super(nick, nombre, correo, contrasena, img);
		this.apellido = apellido;
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
		if (institucion != null) res = new DTAsistente(getNickname(), getNombre(), getCorreo(), getImagen(), apellido, nacimiento, institucion.getNombre());
		else res = new DTAsistente(getNickname(), getNombre(), getCorreo(), getImagen(), apellido, nacimiento);
		return res;
	}
	
	@Override
	public void modificar(DTUsuarioModificado datos) {
		ManejadorInstitucion manejador = ManejadorInstitucion.getInstance();
		
		assert datos instanceof DTAsistenteModificado;
		DTAsistenteModificado datosA = (DTAsistenteModificado) datos;
		this.setNombre(datosA.getNombre());
		this.apellido = datosA.getApellido();
		this.nacimiento = datosA.getNacimiento();
		String ins = datosA.getInstitucion();
		if (ins != null) this.institucion = manejador.obtener(ins);
		String img = datosA.getImagen();
		if (img != null) this.setImagen(img); 
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
	
	public void registrarAsistencia(String edicion) {
		Registro reg = getRegistro(edicion);
		reg.registrarAsistencia();
	}
}
