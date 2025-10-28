package publicar;

import java.util.ArrayList;
import java.util.List;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

import logica.ControladorCategoria;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorInstitucion {

    private Endpoint endpoint = null;
    private ControladorCategoria contCategoria = null;
    //Constructor
    public PublicadorInstitucion() {
    	contCategoria = ControladorCategoria.getInstance();
    }

    @WebMethod(exclude = true)
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:9130/webservices", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    @WebMethod
    public boolean altaCategoria(String nombreCategoria) {
		return contCategoria.altaCategoria(nombreCategoria);
	}
    
    @WebMethod
	public ArrayList<String> categorias() {
		return new ArrayList<>(contCategoria.categorias());
	}
    
}