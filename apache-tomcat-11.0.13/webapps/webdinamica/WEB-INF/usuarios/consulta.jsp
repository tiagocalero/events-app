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
  <link rel="stylesheet" href="/webdinamica/assets/styles.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <link href="https://www.nerdfonts.com/assets/css/webfont.css" rel="stylesheet">
  <script src="/webdinamica/js/consultaUsuario.js" type="text/javascript" defer></script>
  <script src="/webdinamica/js/altaUsuario.js" type="text/javascript" defer></script>
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
    <%@ include file="/components/sidebar.jsp" %>

    <div class="content content-consulta">
      <form id="modifyUserForm" class="content-form-modificar" method="post">

        <div class="event-image">
          <img id="profile-pic" src="/webdinamica/uploads/<%= usuario.getImagen() %>"  onerror="this.onerror=null; this.src='/webdinamica/assets/default_profile.jpg';"
          	alt="Foto Perfil de <%= usuario.getNickname() %>">
        </div>

        <a href="/webdinamica/usuarios/seguidores?nick=<%= URLEncoder.encode(usuario.getNickname(), StandardCharsets.UTF_8) %>" class="btn">
          Seguidores <span class="badge text-bg-secondary"><%= request.getAttribute("seguidos") %></span>
        </a>

        <a href="/webdinamica/usuarios/seguidos?nick=<%= URLEncoder.encode(usuario.getNickname(), StandardCharsets.UTF_8) %>" class="btn">
          Seguidos <span class="badge text-bg-secondary"><%= request.getAttribute("seguidos") %></span>
        </a>

        <br><br>

        <label for="nombre"><strong>Nombre:</strong></label>
        <input type="text" id="nombre" value="<%= usuario.getNombre() != null ? usuario.getNombre() : "" %>" disabled/><br/>

		<% if (usuario instanceof DtAsistente) { 
			asistente = (DtAsistente) usuario; %>
        	<label for="apellido"><strong>Apellido:</strong></label>
        	<input type="text" id="apellido" value="<%= asistente.getApellido() != null ? asistente.getApellido() : "" %>" disabled/><br/>
        <% } %>

        <label for="nick"><strong>Nickname:</strong></label>
        <input type="text" id="nick" value="<%= usuario.getNickname() != null ? usuario.getNickname() : "" %>" disabled /><br/>

        <label for="correo"><strong>Email:</strong></label>
        <input type="text" id="correo" value="<%= usuario.getCorreo() != null ? usuario.getCorreo() : "" %>" disabled /><br/>

		    <% if (usuario instanceof DtAsistente) { %>
        	<label for="nac" class="nacimiento"><strong>Fecha de Nacimiento:</strong></label>
        	<input type="date" id="nacimiento"  value="<%= asistente.getNacimiento() != null ? asistente.getNacimiento() : "" %>" disabled/><br/>

        	<label for="institucion"><strong>Institución:</strong></label>
        	<input type="text" id="institucion" name="institucion" value="<%= asistente.getInstitucion() != null ? asistente.getInstitucion() : "Sin Institucion" %>" disabled/><br/>
        <% } else if (usuario instanceof DtOrganizador) { 
        	organizador = (DtOrganizador) usuario;%>
        	<label for="link"><strong>Sitio Web:</strong></label>
        	<input type="text" id="link" value="<%= organizador.getLink() != null ? organizador.getLink() : "" %>" disabled/><br/>

        	<label for="desc" class="lblDesc"><strong>Descripción:</strong></label>
        	<textarea type="text" id="desc" disabled><%= organizador.getDescripcion() != null ? organizador.getDescripcion() : "" %></textarea><br/>
        <% } %>

        <% if (usuarioLoggeado != null) {
        	   if (usuarioLoggeado.getNickname().equals(usuario.getNickname())) { %>
				    <button id="modifyBtn" type="submit" name="accion" value="editar" class="btn_login">Editar Perfil</button>
          <% } else if (port.esSeguido(usuarioLoggeado.getNickname(), usuario.getNickname())) { %>
    			  <button type="submit" name="accion" value="dejar" class="btn btn-danger">Dejar de Seguir</button>
    		  <% } else { %>
        		<button type="submit" name="accion" value="seguir" class="btn btn-primary">Seguir</button>
          <% }
           } %>
      </form>
    </div>
    <div class="flex-shrink-0 p-3 m-3" style="width: 280px">
        <ul class="list-unstyled ps-0">
          <li class="mb-1">
          	<% if (usuario instanceof DtOrganizador) { %>
            	Ediciones
            	<ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
            		<% List<DtEdicion> edicionesAceptadas = port.consultaOrganizadorAceptadas(usuario.getNickname()).getItem();
    	      			for (DtEdicion ed : edicionesAceptadas) { %>
            			<li><a href="/webdinamica/ediciones/consultaEdicion?evento=<%= URLEncoder.encode(ed.getEvento(), StandardCharsets.UTF_8) %>&edicion=<%= URLEncoder.encode(ed.getNombre(), StandardCharsets.UTF_8) %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded"><%= ed.getNombre() %></a></li>
            		<% } %>
            	</ul>
            <% } else if (usuario instanceof DtAsistente) { %>
            	Registros
            	<ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
              		<% List<DtRegistro> registros = (List<DtRegistro>) request.getAttribute("registros");
    	      			for (DtRegistro reg : registros) { %>
              			<li><a href="/webdinamica/ediciones/consultaEdicion?edicion=<%= URLEncoder.encode(reg.getNombreEdicion(), StandardCharsets.UTF_8) %>&evento=<%= URLEncoder.encode(reg.getNombreEvento(), StandardCharsets.UTF_8) %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded"><%= reg.getNombreEdicion() %></a></li>
              		<% } %>
           		</ul>
            <% } %>
          </li>
        </ul>
    </div>
  </main>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
