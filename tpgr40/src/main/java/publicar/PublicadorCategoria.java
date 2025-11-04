package main.java.publicar;

import java.util.ArrayList;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import main.java.logica.ControladorCategoria;
import main.java.logica.ControladorInstitucion;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorCategoria {

    private Endpoint endpoint = null;
    private ControladorCategoria contCategoria = null;
    //Constructor
    public PublicadorCategoria() {
    	contCategoria = ControladorCategoria.getInstance();
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
    public boolean altaCategoria(String nombreCategoria) {
		return contCategoria.altaCategoria(nombreCategoria);
	}
    
    @WebMethod
	public String[] categorias() {
		return contCategoria.categorias().toArray(new String[0]);
	}
    
}

