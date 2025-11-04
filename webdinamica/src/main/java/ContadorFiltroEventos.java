
import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest; 

@WebFilter("/eventos/consulta") // Se aplica a las URLs de consultar eventos
public class ContadorFiltroEventos implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String nombreEvento = req.getParameter("evento");
        
        main.java.publicar.PublicadorEventoService service = new main.java.publicar.PublicadorEventoService();
        main.java.publicar.PublicadorEvento port = service.getPublicadorEventoPort();
        
        port.incrementarVisitas(nombreEvento);

        chain.doFilter(request, response);
    }

}