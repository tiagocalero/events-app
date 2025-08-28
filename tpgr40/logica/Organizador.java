package logica;

import logica.Usuario;
import logica.Edicion;
import logica.DTOrganizador;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class Organizador extends Usuario {
	private String descripcion;
	private String link;
	private Map<String, Edicion> ediciones;
	
	public Organizador(String nick, String nom, String c, String desc, String link) {
		super(nick, nom, c);
		this.descripcion = desc;
		this.link = link;
		this.ediciones = new HashMap<String, Edicion>();
	}
	
	@Override
	public DTOrganizador getDatos() {
		DTOrganizador res = new DTOrganizador(getNickname(), getNombre(), getCorreo(), descripcion, link);
		return res;
	}
	
	@Override
	public void modificar(DTUsuarioModificado datos) {
		assert datos instanceof DTOrganizadorModificado;
		DTOrganizadorModificado datosO = (DTOrganizadorModificado) datos;
		this.setNombre(datosO.nombre);
		this.descripcion = datosO.descripcion;
		this.link = datosO.link;
	}

	@Override
	public List<DTEdicion> consulta(){
		return ediciones.values().stream().map(ediciones->ediciones.getDatos()).collect(Collectors.tolist()); //?? chequear
	}
}
