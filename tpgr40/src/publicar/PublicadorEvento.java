package publicar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import excepciones.PatrocinioSuperaMontoException;
import excepciones.YaExistePatrocinioException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

import logica.ControladorEvento;
import logica.DTEdicion;
import logica.DTEvento;
import logica.DTPatrocinio;
import logica.DTRegistro;
import logica.DTTRegistro;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorEvento {

    private Endpoint endpoint = null;
    private ControladorEvento contEvento = null;
    //Constructor
    public PublicadorEvento() {
    	contEvento = ControladorEvento.getInstance();
    }

    @WebMethod(exclude = true)
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:9128/webservices", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }

    @WebMethod
    public ArrayList<String> listarEdiciones(String nombreEvento) {
    	return new ArrayList<>(contEvento.listarEdiciones(nombreEvento));
    }
    
    @WebMethod
    public ArrayList<String> listarEdicionesIngresadas(String nombreEvento) {
    	return new ArrayList<>(contEvento.listarEdicionesIngresadas(nombreEvento));
    }
    
    @WebMethod
    public ArrayList<String> listarEdicionesAceptadas(String nombreEvento) {
    	return new ArrayList<>(contEvento.listarEdicionesAceptadas(nombreEvento));
    }

    @WebMethod
    public ArrayList<String> listarEventos() {
		return new ArrayList<>(contEvento.listarEventos());
	}
    
    @WebMethod
    public List<DTEvento> listarDTEventos() {
    	return contEvento.listarDTEventos();
    }
    
    @WebMethod
    public ArrayList<String> listarPatrocinios(String nomEvento, String nomEdicion) {
    	return new ArrayList<>(contEvento.listarPatrocinios(nomEvento, nomEdicion));
    }
    
    @WebMethod
    public ArrayList<String> listarInstituciones(String nomEvento, String nomEdicion) {
    	return new ArrayList<>(contEvento.listarInstituciones(nomEvento, nomEdicion));
    }
    
    @WebMethod
    public ArrayList<String> listarInstituciones() {
    	return new ArrayList<>(contEvento.listarInstituciones());
    }
    
    @WebMethod
    public ArrayList<String> listarOrganizadores() {
    	return new ArrayList<>(contEvento.listarOrganizadores());
    }
    
    @WebMethod
    public ArrayList<String> listarCategorias() {
    	return new ArrayList<>(contEvento.listarCategorias());
    }
    
    @WebMethod
	public DTEvento informacionEvento(String nombre) {
    	return contEvento.informacionEvento(nombre);
    }
    
    @WebMethod
    public DTEvento informacionEventoEdAceptadas(String nombre) {
    	return contEvento.informacionEventoEdAceptadas(nombre);
    }
    
    @WebMethod
    public DTPatrocinio consultaPatrocinioInstitucion(String nomEvento, String nomEdicion, String nomInstitucion) {
    	return contEvento.consultaPatrocinioInstitucion(nomEvento, nomEdicion, nomInstitucion);
    }
    
    @WebMethod
    public DTTRegistro seleccionarTipoRegistro(String nombre, String nomEvento, String nomEdicion) {
    	return contEvento.seleccionarTipoRegistro(nombre, nomEvento, nomEdicion);
    }
    
    @WebMethod
    public DTEdicion obtenerEdicion(String nombreEdicion, String nombreEvento) {
    	return contEvento.obtenerEdicion(nombreEdicion, nombreEvento);
    }
    
    @WebMethod
    public void crearPatrocinio(DTPatrocinio dtp, String nombreEdicion, String nombreInstitucion, String nombreEvento)
		throws YaExistePatrocinioException, PatrocinioSuperaMontoException, IllegalArgumentException {
		contEvento.crearPatrocinio(dtp, nombreEdicion, nombreInstitucion, nombreEvento);
	}
    
    @WebMethod
    public boolean ingresarDatosEvento(String nombre, String descripcion, String sigla, LocalDate fechaAlta,
			ArrayList<String> categorias) {
    	return contEvento.ingresarDatosEvento(nombre, descripcion, sigla, fechaAlta, categorias);
    }
    
    @WebMethod
    public boolean ingresarDatosEvento(String nombre, String descripcion, String sigla, LocalDate fechaAlta,
			ArrayList<String> categorias, String imagen) {
    	return contEvento.ingresarDatosEvento(nombre, descripcion, sigla, fechaAlta, categorias, imagen);
    }
    
    @WebMethod
    public boolean usuarioEnEdicion(String edicion, String usuario, String evento) {
    	return contEvento.usuarioEnEdicion(edicion, usuario, evento);
    }
    
    @WebMethod
    public boolean altaEdicion(String nombreEvento, String nicknameOrganizador, String nombre, String sigla,
		LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAlta, String ciudad, String pais) {
		return contEvento.altaEdicion(nombreEvento, nicknameOrganizador, nombre, sigla, fechaInicio, fechaFin, fechaAlta, ciudad, pais);
	}
    
    @WebMethod
    public boolean altaEdicion(String nombreEvento, String nicknameOrganizador, String nombre, String sigla,
		LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAlta, String ciudad, String pais, String imagen) {
    	return contEvento.altaEdicion(nombreEvento, nicknameOrganizador, nombre, sigla, fechaInicio, fechaFin, fechaAlta, ciudad, pais, imagen);
    }
    
    @WebMethod
    public ArrayList<String> listarTipoRegistro(String nombreEvento, String nombreEdicion) {
    	return new ArrayList<>(contEvento.listarTipoRegistro(nombreEvento, nombreEdicion));
    }
    
    @WebMethod
    public boolean crearTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTRegistro,
			String descripcion, float costo, int cupo) {
    	return contEvento.crearTipoRegistro(nombreEvento, nombreEdicion, nombreTRegistro, descripcion, costo, cupo);
    }
    
    @WebMethod
    public DTTRegistro mostrarTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro) {
    	return contEvento.mostrarTipoRegistro(nombreEvento, nombreEdicion, nombreTipoRegistro);
    }
    
    @WebMethod
    public boolean darDeAltaRegistro(String nickAsistente, String nombreEdicion, String nombreTRegistro,
		String nickEvento, LocalDate fechaAlta, Boolean patrocinio) {
    	return contEvento.darDeAltaRegistro(nickAsistente, nombreEdicion, nombreTRegistro, nickEvento, fechaAlta, patrocinio);
    }
    
    @WebMethod
    public boolean existeEdicionEvento(String eventoS, String edicionS) {
    	return contEvento.existeEdicionEvento(eventoS, edicionS);
    }
    
    @WebMethod
    public List<DTRegistro> registrosEdicion(String nombreEdicion, String nombreEvento) {
    	return contEvento.registrosEdicion(nombreEdicion, nombreEvento);
    }
    
    @WebMethod
    public void confirmarEdicion(String nombreEvento, String nombreEdicion) {
		contEvento.confirmarEdicion(nombreEvento, nombreEdicion);
	}
    
    @WebMethod
    public void rechazarEdicion(String nombreEvento, String nombreEdicion) {
		contEvento.rechazarEdicion(nombreEvento, nombreEdicion);
	}
    
    @WebMethod
    public List<DTEvento> listarDTEventosCategoria(String categoria) {
    	return contEvento.listarDTEventosCategoria(categoria);
    }
    
    @WebMethod
    public void patrocinioEdicion(String codigoPatrocinio, String nombreEdicion, String nombreEvento,
		String nombreTRegistroSeleccionado, String nombreInstitucion)
			throws NoSuchElementException, IllegalArgumentException {
    	contEvento.patrocinioEdicion(codigoPatrocinio, nombreEdicion, nombreEvento, nombreTRegistroSeleccionado, nombreInstitucion);
    }
    
}