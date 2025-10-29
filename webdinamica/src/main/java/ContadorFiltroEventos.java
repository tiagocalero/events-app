package filtros;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import jakarta.servlet.ServletRequest;
import logica.ControladorEvento;	
import logica.Evento; 


@WebFilter("/eventos/consulta") // Se aplica a las URLs de consultar eventos
public class ContadorFiltro implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String nombreEvento = req.getParameter("evento");
        
		ControladorEvento c = ControladorEvento.getInstance();
        Evento e = c.obtenerEvento(nombreEvento);
        e.incrementarVisitas();

        chain.doFilter(request, response);
    }

}