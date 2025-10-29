package publicar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import excepciones.PatrocinioSuperaMontoException;
import excepciones.YaExistePatrocinioException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.ws.Endpoint;

import logica.ControladorEvento;
import logica.DTEdicion;
import logica.DTEvento;
import logica.DTPatrocinio;
import logica.DTRegistro;
import logica.DTTRegistro;
import logica.TipoNivelPatrocinio;

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
    public String[] listarEdiciones(String nombreEvento) {
    	return contEvento.listarEdiciones(nombreEvento).toArray(new String[0]);
    }
    
    @WebMethod
    public String[] listarEdicionesIngresadas(String nombreEvento) {
    	return contEvento.listarEdicionesIngresadas(nombreEvento).toArray(new String[0]);
    }
    
    @WebMethod
    public String[] listarEdicionesAceptadas(String nombreEvento) {
    	return contEvento.listarEdicionesAceptadas(nombreEvento).toArray(new String[0]);
    }

    @WebMethod
    public String[] listarEventos() {
		return contEvento.listarEventos().toArray(new String[0]);
	}

    @WebMethod
    public DTEvento[] listarDTEventos() {
    	return contEvento.listarDTEventos().toArray(new DTEvento[0]);
    }
    
    @WebMethod
    public ArrayList<String> listarPatrocinios(String nomEvento, String nomEdicion) {
    	return new ArrayList<>(contEvento.listarPatrocinios(nomEvento, nomEdicion));
    }
    
    @WebMethod
    public String[] listarInstitucionesEdicionEvento(String nomEvento, String nomEdicion) {
    	return contEvento.listarInstituciones(nomEvento, nomEdicion).toArray(new String[0]);
    }
    
    @WebMethod
    public String[] listarInstituciones() {
    	return contEvento.listarInstituciones().toArray(new String[0]);
    }
    
    @WebMethod
    public String[] listarOrganizadores() {
    	return contEvento.listarOrganizadores().toArray(new String[0]);
    }
    
    @WebMethod
    public String[] listarCategorias() {
    	return contEvento.listarCategorias().toArray(new String[0]);
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
	public void crearPatrocinio(String codigo, TipoNivelPatrocinio tipo, String nombreTipoRegistro, int cantRegistros, float monto, @XmlJavaTypeAdapter(LocalDateAdapter.class) LocalDate fechaAlta, String nombreEdicion, String nombreInstitucion, String nombreEvento)
		throws YaExistePatrocinioException, PatrocinioSuperaMontoException, IllegalArgumentException {
		contEvento.crearPatrocinio(codigo, tipo, nombreTipoRegistro, cantRegistros, monto, fechaAlta, nombreEdicion, nombreInstitucion, nombreEvento);
	}
    
    @WebMethod
    public boolean ingresarDatosEvento(String nombre, String descripcion, String sigla, String fechaAlta,
			String[] categorias, String imagen) {
    	DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
    	return contEvento.ingresarDatosEvento(nombre, descripcion, sigla, LocalDate.parse(fechaAlta, fmt), Arrays.asList(categorias), imagen);
    }
    
    @WebMethod
    public boolean usuarioEnEdicion(String edicion, String usuario, String evento) {
    	return contEvento.usuarioEnEdicion(edicion, usuario, evento);
    }
    
    @WebMethod
    public boolean altaEdicion(String nombreEvento, String nicknameOrganizador, String nombre, String sigla,
		LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAlta, String ciudad, String pais, String imagen) {
    	return contEvento.altaEdicion(nombreEvento, nicknameOrganizador, nombre, sigla, fechaInicio, fechaFin, fechaAlta, ciudad, pais, imagen);
    }
    
    @WebMethod
    public String[] listarTipoRegistro(String nombreEvento, String nombreEdicion) {
    	return contEvento.listarTipoRegistro(nombreEvento, nombreEdicion).toArray(new String[0]);
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
    public DTRegistro[] registrosEdicion(String nombreEdicion, String nombreEvento) {
    	return contEvento.registrosEdicion(nombreEdicion, nombreEvento).toArray(new DTRegistro[0]);
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
    public DTEvento[] listarDTEventosCategoria(String categoria) {
    	return contEvento.listarDTEventosCategoria(categoria).toArray(new DTEvento[0]);
    }
    
    @WebMethod
    public void patrocinioEdicion(String codigoPatrocinio, String nombreEdicion, String nombreEvento,
		String nombreTRegistroSeleccionado, String nombreInstitucion)
			throws NoSuchElementException, IllegalArgumentException {
    	contEvento.patrocinioEdicion(codigoPatrocinio, nombreEdicion, nombreEvento, nombreTRegistroSeleccionado, nombreInstitucion);
    }
    
}