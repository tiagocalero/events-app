package main.java.publicar;

import java.time.LocalDate;
import java.util.ArrayList;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.ws.Endpoint;

import main.java.logica.ControladorUsuario;
import main.java.logica.DTEdicion;
import main.java.logica.DTRegistro;
import main.java.logica.DTUsuario;
import main.java.logica.DTUsuarioModificado;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorUsuario {
    private Endpoint endpoint = null;
    private ControladorUsuario contUsuario = null;
    private String ip;
    private String puerto;

    //Constructor
    public PublicadorUsuario(String ip, String puerto) {
    	contUsuario = ControladorUsuario.getInstance();
        this.ip = ip;
        this.puerto = puerto;
    }

    @WebMethod(exclude = true)
    public void publicar(){
         endpoint = Endpoint.publish("http://" + ip + ":" + puerto + "/webservices", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }

    @WebMethod
    public String[] listarAsistentes() {
    	return contUsuario.listarAsistentes().toArray(new String[0]);
    }

    @WebMethod
    public String[] listarOrganizadores() {
    	return contUsuario.listarOrganizadores().toArray(new String[0]);
    }
    
    @WebMethod
    public String[] listarUsuarios() {
    	return contUsuario.listarUsuarios().toArray(new String[0]);
    }
    
    @WebMethod
    public boolean existeUsuario(String nickname) {
        return contUsuario.existeUsuario(nickname);
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
    public boolean altaAsistenteInstitucion(String nick, String nombre, String correo, String cont, String img, String apellido, @XmlJavaTypeAdapter(LocalDateAdapter.class) LocalDate nac, String ins) {
    	if (img == "")
            img = null;
        return contUsuario.altaAsistente(nick, nombre, correo, cont, img, apellido, nac, ins);
    }
    
    @WebMethod
    public boolean altaAsistente(String nick, String nombre, String correo, String cont, String img, String apellido, @XmlJavaTypeAdapter(LocalDateAdapter.class) LocalDate nac) {
    	if (img == "")
            img = null;
        return contUsuario.altaAsistente(nick, nombre, correo, cont, img, apellido, nac);
    }
    
    @WebMethod
    public boolean altaOrganizador(String nick, String nombre, String correo, String cont, String img, String desc, String link) {
    	if (img == "")
            img = null;
        return contUsuario.altaOrganizador(nick, nombre, correo, cont, img, desc, link);
    }
    
    @WebMethod
    public DTEdicion[] consultaOrganizadorAceptadas(String nickname) {
    	return contUsuario.consultaOrganizadorAceptadas(nickname).toArray(new DTEdicion[0]);
    }
    
    @WebMethod
    public DTEdicion[] consultaOrganizador() {
		return contUsuario.consultaOrganizador().toArray(new DTEdicion[0]);
	}
    
    @WebMethod
    public DTRegistro[] consultaAsistente() {
    	return contUsuario.consultaAsistente().toArray(new DTRegistro[0]);
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
    
    
    @WebMethod
    public void registrarAsistencia(String asistente, String edicion) {
    	contUsuario.registrarAsistencia(asistente, edicion);
    }
    
    @WebMethod
    public String[] obtenerSeguidos(String nickname) {
    	return contUsuario.obtenerSeguidos(nickname).toArray(new String[0]);
    }
    
    @WebMethod
    public void agregarSeguido(String nickSeguidor, String nickSeguido) {
    	contUsuario.seguirUsuario(nickSeguidor, nickSeguido);
    }
    
    @WebMethod
    public void eliminarSeguido(String nickSeguidor, String nickSeguido) {
    	contUsuario.dejarDeSeguirUsuario(nickSeguidor, nickSeguido);
    }
    
    @WebMethod
    public boolean esSeguido(String nickSeguidor, String nickSeguido) {
    	return contUsuario.esSeguido(nickSeguidor, nickSeguido);
    }

    @WebMethod
    public DTRegistro[] registrosNoConfirmados(String nickname){
        return contUsuario.registrosNoConfirmados(nickname).toArray(new DTRegistro[0]);
    }
    
}