package publicar;

import java.util.ArrayList;
import java.util.List;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

import logica.ControladorInstitucion;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorCategoria {

    private Endpoint endpoint = null;
    private ControladorInstitucion contInstitucion = null;
    //Constructor
    public PublicadorCategoria() {
    	contInstitucion = ControladorInstitucion.getInstance();
    }

    @WebMethod(exclude = true)
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:9131/webservices", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    @WebMethod
    public boolean altaInstitucion(String nombre, String desc, String url) {
    	return contInstitucion.altaInstitucion(nombre, desc, url);
    }
    
    @WebMethod
    public boolean altaInstitucion(String nombre, String desc, String url, String imagen) {
    	return contInstitucion.altaInstitucion(nombre, desc, url, imagen);
    }
    
    @WebMethod
    public ArrayList<String> listarInstituciones() {
    	return new ArrayList<String>(contInstitucion.listarInstituciones());
    }
    
}

