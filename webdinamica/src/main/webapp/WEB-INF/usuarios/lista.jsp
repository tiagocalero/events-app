<%@ page import="java.util.List" %>
<%@ page import="publicar.DtUsuario" %>
<%@ page import="publicar.DtAsistente" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Eventos.uy - Lista Usuarios</title>
  
  <link rel="stylesheet" href="/webdinamica/assets/styles.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <link rel="icon" type="image/png" href="assets/logoChico.png">
</head>
<body>
  <%@ include file="/components/header.jsp" %>

  <main>
    <%@ include file="/components/sidebar.jsp" %>
  	<div class="container py-3">
	  	<div class="row gy-3">
		
			<div class="list-group m-3 w-50">
				<% publicar.PublicadorUsuarioService service = new publicar.PublicadorUsuarioService();
				   publicar.PublicadorUsuario port = service.getPublicadorUsuarioPort();
				   List<String> lista = (List<String>) request.getAttribute("lista");
				   for (String us : lista) {
					   DtUsuario dtus = port.elegirUsuario(us);
					   boolean esEl = usuarioLoggeado != null ? dtus.getNickname().equals(usuarioLoggeado.getNickname()) : false;
				   %>
				   <a href="/webdinamica/usuarios/<%= esEl ? "" : "consulta" %>?nick=<%= URLEncoder.encode(dtus.getNickname(), StandardCharsets.UTF_8) %>"
				   	  class="list-group-item list-group-item-action <%= esEl ? "active" : "" %>"><%= dtus.getNombre() %> 
				   	  <%= dtus instanceof DtAsistente ? ((DtAsistente) dtus).getApellido() : "" %> (<%= dtus.getNickname() %>)
				   </a>
				<% } %>
			</div>
	    </div>
    </div>
  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
