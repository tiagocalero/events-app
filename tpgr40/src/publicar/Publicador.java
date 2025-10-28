package publicar;

import java.util.ArrayList;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

import logica.ControladorEvento;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class Publicador {

    private Endpoint endpoint = null;
    private ControladorEvento contEvento = null;
    //Constructor
    public Publicador() {
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
    public boolean usuarioEnEdicion(String edicion, String usuario, String evento) {
    	return contEvento.usuarioEnEdicion(edicion, usuario, evento);
    }
}

