
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import logica.ControladorEvento;	
import logica.ManejadorEvento;
import logica.Evento; 




@WebFilter("/eventos/consulta") // Se aplica a las URLs de consultar eventos
public class ContadorFiltroEventos implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String nombreEvento = req.getParameter("evento");
        
        publicar.PublicadorEventoService service = new publicar.PublicadorEventoService();
        publicar.PublicadorEvento port = service.getPublicadorEventoPort();
        
        port.incrementarVisitas(nombreEvento);

        chain.doFilter(request, response);
    }

}