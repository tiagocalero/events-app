<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="main.java.publicar.DtUsuario" %>
<%@ page import="main.java.publicar.DtAsistente" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Eventos.uy - Lista de Usuario</title>

  <link rel="stylesheet" href="/webdinamica/assets/styles.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <link href="https://www.nerdfonts.com/assets/css/webfont.css" rel="stylesheet">
  <link rel="icon" type="image/png" href="assets/logoChico.png">
</head>
<body>
  <%@ include file="/components/header.jsp" %>

  <main>
    <%@ include file="/components/sidebar.jsp" %>
  	<div class="container py-3">
	  	<div class="row gy-3">
		
				<div class="list-group m-3 w-50">
			 <% main.java.publicar.PublicadorUsuarioService service = new main.java.publicar.PublicadorUsuarioService();
					main.java.publicar.PublicadorUsuario port = service.getPublicadorUsuarioPort();
					List<String> lista = (List<String>) request.getAttribute("lista");
					for (String us : lista) {
						DtUsuario dtus = port.elegirUsuario(us);
						boolean esEl = usuarioLoggeado != null ? dtus.getNickname().equals(usuarioLoggeado.getNickname()) : false; %>
						<div class="list-group-item list-group-item-action align-items-center item-usuario <%= esEl ? "active" : "" %>" style="display:flex;">
			    		<a class="<%= esEl ? "text-white" : "text-black" %> text-decoration-none" href="/webdinamica/usuarios/consulta?nick=<%= URLEncoder.encode(dtus.getNickname(), StandardCharsets.UTF_8) %>" style="width:400px;">
			    			<%= dtus.getNombre() %> 
							  <%= dtus instanceof DtAsistente ? ((DtAsistente) dtus).getApellido() : "" %> (<%= dtus.getNickname() %>)
							</a>
							<% if (usuarioLoggeado != null && !esEl) { %>
								<form id="seguirUsuario" method="post">
									<input type="hidden" name="usuarioASeguir" value="<%= dtus.getNickname() %>">
	        						<% if (port.esSeguido(usuarioLoggeado.getNickname(), dtus.getNickname())) { %>
	    						  		<button type="submit" name="accion" value="dejar" class="btn btn-danger">Dejar de Seguir</button>
	    					  		<% } else { %>
	        							<button type="submit" name="accion" value="seguir" class="btn btn-primary">Seguir</button>
	        						<% } %>
	        					</form>
	         				<% } %>
		  				</div>
				<% } %>
				</div>
	    </div>
    </div>
  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
