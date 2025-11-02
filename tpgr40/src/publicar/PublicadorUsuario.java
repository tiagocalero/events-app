package publicar;

import java.time.LocalDate;
import java.util.ArrayList;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

import logica.ControladorUsuario;
import logica.DTEdicion;
import logica.DTRegistro;
import logica.DTUsuario;
import logica.DTUsuarioModificado;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorUsuario {

    private Endpoint endpoint = null;
    private ControladorUsuario contUsuario = null;
    //Constructor
    public PublicadorUsuario() {
    	contUsuario = ControladorUsuario.getInstance();
    }

    @WebMethod(exclude = true)
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:9129/webservices", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }

    @WebMethod
    public ArrayList<String> listarAsistentes() {
    	return new ArrayList<>(contUsuario.listarAsistentes());
    }

    @WebMethod
    public ArrayList<String> listarOrganizadores() {
    	return new ArrayList<>(contUsuario.listarOrganizadores());
    }
    
    @WebMethod
    public ArrayList<String> listarUsuarios() {
    	return new ArrayList<>(contUsuario.listarUsuarios());
    }
    
    @WebMethod
    public DTUsuario elegirUsuario(String nickname) {
    	return contUsuario.elegirUsuario(nickname);
    }
    
    @WebMethod
    public String hashearContrasena(String contrasena) {
    	return contUsuario.hashearContrasena(contrasena);
    }
    
    @WebMethod
    public void modificarDatosUsuario(DTUsuarioModificado datos) {
    	contUsuario.modificarDatosUsuario(datos);
    }
    
    @WebMethod
    public boolean altaAsistenteInstitucion(String nick, String nombre, String correo, String cont, String img, String apellido, LocalDate nac, String ins) {
    	return contUsuario.altaAsistente(nick, nombre, correo, cont, img, apellido, nac, ins);
    }
    
    @WebMethod
    public boolean altaAsistente(String nick, String nombre, String correo, String cont, String img, String apellido, LocalDate nac) {
    	return contUsuario.altaAsistente(nick, nombre, correo, cont, img, apellido, nac);
    }
    
    @WebMethod
    public boolean altaOrganizador(String nick, String nombre, String correo, String cont, String img, String desc, String link) {
    	return contUsuario.altaOrganizador(nick, nombre, correo, cont, img, desc, link);
    }
    
    @WebMethod
    public ArrayList<DTEdicion> consultaOrganizadorAceptadas(String nickname) {
    	return new ArrayList<>(contUsuario.consultaOrganizadorAceptadas(nickname));
    }
    
    @WebMethod
    public ArrayList<DTEdicion> consultaOrganizador() {
		return new ArrayList<>(contUsuario.consultaOrganizador());
	}
    
    @WebMethod
    public ArrayList<DTRegistro> consultaAsistente() {
    	return new ArrayList<>(contUsuario.consultaAsistente());
    }
    
    @WebMethod
    public DTRegistro datosRegistroUsuario(String nombreEdicion, String usuario) {
    	return contUsuario.datosRegistroUsuario(nombreEdicion, usuario);
    }
    
    @WebMethod
    public boolean chequearContrasena(String nickOCorreo, String cont) {
		return contUsuario.chequearContrasena(nickOCorreo, cont);
    }
    
    @WebMethod
    public void setContrasena(String nickOCorreo, String nueva) {
    	contUsuario.setContrasena(nickOCorreo, nueva);
    }
    
}