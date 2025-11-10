<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="main.java.publicar.DtUsuario" %>
<%@ page import="main.java.publicar.DtAsistente" %>
<%@ page import="main.java.publicar.DtOrganizador" %>
<%@ page import="main.java.publicar.DtEdicion" %>
<%@ page import="main.java.publicar.DtRegistro" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Eventos.uy - Consulta de Usuario</title>
  <link rel="stylesheet" href="/webmovil/assets/styles.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <link href="https://www.nerdfonts.com/assets/css/webfont.css" rel="stylesheet">
  <script src="/webmovil/js/consultaUsuario.js" type="text/javascript" defer></script>
  <script src="/webmovil/js/altaUsuario.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="assets/logoChico.png">
</head>
<body>
  <%@ include file="/components/header.jsp" %>
  <% main.java.publicar.PublicadorUsuarioService service = new main.java.publicar.PublicadorUsuarioService();
     main.java.publicar.PublicadorUsuario port = service.getPublicadorUsuarioPort();
  	 DtUsuario usuario = (DtUsuario) request.getAttribute("datos");
  	 DtAsistente asistente = null;
	 DtOrganizador organizador = null; %>

  <main>
    <div class="container p-3">
      <div class="flex-shrink-0 p-3 m-0" style="width: 100%">
          <ul class="list-unstyled ps-0">
            <li class="mb-1">
            	<% if (usuario instanceof DtAsistente) { %>
              	Registros
              	<ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
                		<% List<DtRegistro> registros = (List<DtRegistro>) request.getAttribute("registros");
      	      			for (DtRegistro reg : registros) { %>
                			<li><a href="/webmovil/ediciones/consultaEdicion?edicion=<%= URLEncoder.encode(reg.getNombreEdicion(), StandardCharsets.UTF_8) %>&evento=<%= URLEncoder.encode(reg.getNombreEvento(), StandardCharsets.UTF_8) %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded"><%= reg.getNombreEdicion() %></a></li>
                		<% } %>
             		</ul>
              <% } %>
            </li>
          </ul>
      </div>
    </div>
  </main>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
