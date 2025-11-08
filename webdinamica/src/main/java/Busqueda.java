import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.publicar.DtUsuario;

import java.io.IOException;
import java.text.Normalizer;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Suggest nicknames from an in-memory cache of listarUsuarios().
 *
 * GET /userSuggest?q=te&limit=8&mode=prefix|contains
 * Response:
 *   { "results": ["testuser","teo","teamlead"] }
 */
@WebServlet(name = "Busqueda", urlPatterns = "/userSuggest")
public class Busqueda extends HttpServlet {

    private static final long CACHE_TTL_MS = 60_000; // 1 minute
    private static final int DEFAULT_LIMIT = 8;
    private static final int MAX_LIMIT = 25;

    private final Object cacheLock = new Object();
    private volatile List<String> cachedNicknames = Collections.emptyList();
    private final AtomicLong cacheLoadedAt = new AtomicLong(0);

    public Busqueda() {
		super();
		refreshCacheIfStale(true);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        String q = opt(req.getParameter("q")).trim();
        int limit = parseLimit(req.getParameter("limit"));

        if (q.length() < 2) {
            resp.getWriter().write("{\"results\":[]}");
            return;
        }

        refreshCacheIfStale(false);

        final String normQ = norm(q);
        List<String> snapshot = cachedNicknames;

        List<String> matches = snapshot.stream()
                .filter(nick -> norm(nick).startsWith(normQ))
                .sorted(Comparator
                        .comparing((String nick) -> norm(nick).startsWith(normQ)).reversed()
                        .thenComparing(String::toString, String.CASE_INSENSITIVE_ORDER))
                .limit(limit)
                .collect(Collectors.toList());

        // Build JSON: { "results": ["a","b"] }
        StringBuilder sb = new StringBuilder();
        sb.append("{\"results\":[");
        for (int i = 0; i < matches.size(); i++) {
            if (i > 0) sb.append(',');
            sb.append('"').append(esc(matches.get(i))).append('"');
        }
        sb.append("]}");
        resp.getWriter().write(sb.toString());
    }

    private void refreshCacheIfStale(boolean force) {
    	main.java.publicar.PublicadorUsuarioService service = new main.java.publicar.PublicadorUsuarioService();
    	main.java.publicar.PublicadorUsuario port = service.getPublicadorUsuarioPort();
        long now = System.currentTimeMillis();
        if (force || now - cacheLoadedAt.get() > CACHE_TTL_MS) {
            synchronized (cacheLock) {
                if (force || now - cacheLoadedAt.get() > CACHE_TTL_MS) {
                    List<String> fresh = port.listarUsuarios().getItem();
                    cachedNicknames = fresh != null ? fresh : Collections.emptyList();
                    cacheLoadedAt.set(System.currentTimeMillis());
                }
            }
        }
    }

    private static String norm(String s) {
        if (s == null) return "";
        String lower = s.toLowerCase(Locale.ROOT);
        String nfd = Normalizer.normalize(lower, Normalizer.Form.NFD);
        return nfd.replaceAll("\\p{M}+", ""); // strip accents
    }

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    private static String opt(String s) { return s == null ? "" : s; }

    private static int parseLimit(String s) {
        try {
            int n = Integer.parseInt(s);
            if (n < 1) return DEFAULT_LIMIT;
            return Math.min(n, MAX_LIMIT);
        } catch (Exception e) {
            return DEFAULT_LIMIT;
        }
    }
}