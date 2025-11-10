package main.java.publicar;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import main.java.logica.ControladorCategoria;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorCategoria {
    private String ip;
    private String puerto;

    private Endpoint endpoint = null;
    private ControladorCategoria contCategoria = null;
    //Constructor
    public PublicadorCategoria(String ip, String puerto) {
    	contCategoria = ControladorCategoria.getInstance();
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
    public boolean altaCategoria(String nombreCategoria) {
		return contCategoria.altaCategoria(nombreCategoria);
	}
    
    @WebMethod
	public String[] categorias() {
		return contCategoria.categorias().toArray(new String[0]);
	}
    
}

