package servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.publicar.PublicadorUsuario;
import main.java.publicar.PublicadorUsuarioService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@WebServlet(name = "Ajax", urlPatterns = "/check-nickname")
public class Ajax extends HttpServlet {

    private static final Pattern NICK_PATTERN = Pattern.compile("^[A-Za-z0-9_]{3,20}$");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PublicadorUsuarioService service = new PublicadorUsuarioService();
        PublicadorUsuario port = service.getPublicadorUsuarioPort();
    	
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json; charset=UTF-8");
        // Evitar cachear respuestas
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        resp.setHeader("Pragma", "no-cache");

        String nick = req.getParameter("nickname");
        try (PrintWriter out = resp.getWriter()) {
            if (nick == null || nick.isBlank()) {
                out.printf("{\"available\":false,\"message\":\"El nickname no puede estar vacío.\"}");
                return;
            }

            nick = nick.trim();

            if (!NICK_PATTERN.matcher(nick).matches()) {
                out.printf("{\"available\":false,\"message\":\"Formato inválido. Usa 3-20 caracteres: letras, números o _.\"}");
                return;
            }

            boolean exists;
            try {
                exists = port.existeUsuario(nick);

            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.printf("{\"available\":false,\"message\":\"Error al verificar disponibilidad.\"}");
                return;
            }

            if (exists) {
                out.printf("{\"available\":false,\"message\":\"No disponible. Elige otro.\"}");
            } else {
                out.printf("{\"available\":true,\"message\":\"Disponible.\"}");
            }
        }
    }
}