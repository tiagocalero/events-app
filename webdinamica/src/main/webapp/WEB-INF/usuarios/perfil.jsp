<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="logica.DTUsuario" %>
<%@ page import="logica.DTAsistente" %>
<%@ page import="logica.DTOrganizador" %>
<%@ page import="logica.DTEdicion" %>
<%@ page import="logica.DTRegistro" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Eventos.uy - Consulta de Usuario</title>
  <link rel="stylesheet" href="/webdinamica/assets/styles.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <link href="https://www.nerdfonts.com/assets/css/webfont.css" rel="stylesheet">
  <script src="/webdinamica/components/headerLoggedIn.js" type="text/javascript" defer></script>
  <script src="/webdinamica/js/consultaUsuario.js" type="text/javascript" defer></script>
  <script src="/webdinamica/js/altaUsuario.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="assets/logoChico.png">
</head>
<body>
  <%@ include file="/components/header.jsp" %>

  <main>
    <%@ include file="/components/sidebar.jsp" %>
    <% DTAsistente asistente = null;
       DTOrganizador organizador = null; %>

    <div class="content content-consulta">
      <form id="modifyUserForm" class="content-form-modificar" action="modificar" method="post"  enctype="multipart/form-data">

        <div class="event-image">
          <img id="profile-pic" src="/webdinamica/uploads/<%= usuarioLoggeado.getImagen() %>"  onerror="this.onerror=null; this.src='/webdinamica/assets/default_profile.jpg';"
          	alt="Foto Perfil de <%= usuarioLoggeado.getNickname() %>">
          <input id="file-input" type="file" name="imagenPerfil" accept="image/*" style="display: none"/>
        </div>

        <label for="nombre"><strong>Nombre:</strong></label>
        <input type="text" id="nombre" value="<%= usuarioLoggeado.getNombre() != null ? usuarioLoggeado.getNombre() : "" %>"/><br/>

		<% if (usuarioLoggeado instanceof DTAsistente) { 
			asistente = (DTAsistente) usuarioLoggeado; %>
        	<label for="apellido"><strong>Apellido:</strong></label>
        	<input type="text" id="apellido" value="<%= asistente.getApellido() != null ? asistente.getApellido() : "" %>"/><br/>
        <% } %>

        <label for="nick"><strong>Nickname:</strong></label>
        <input type="text" id="nick" value="<%= usuarioLoggeado.getNickname() != null ? usuarioLoggeado.getNickname() : "" %>" disabled /><br/>

        <label for="correo"><strong>Email:</strong></label>
        <input type="text" id="correo" value="<%= usuarioLoggeado.getCorreo() != null ? usuarioLoggeado.getCorreo() : "" %>" disabled /><br/>

        <label for="pass"><strong>Contraseña:</strong></label>
        <%-- <input type="password" id="pass" value="<%= user.getAttribute("pass") != null ? user.getAttribute("pass") : "" %>"/> --%>
        <a href="#" >cambiar</a></br>

		<% if (usuarioLoggeado instanceof DTAsistente) { %>
        	<label for="nac" class="nacimiento"><strong>Fecha de Nacimiento:</strong></label>
        	<input type="date" id="nacimiento"  value="<%= asistente.getNacimiento() != null ? asistente.getNacimiento() : "" %>"/><br/>

        	<label for="institucion"><strong>Institución:</strong></label>
        	<select id="institucion" name="institucion">
          		<% List<String> instituciones = (List<String>) request.getAttribute("instituciones");
    	    		for (String inst : instituciones) { %>
          			<option value="<%= inst %>"
          		    <%= inst.equals(asistente.getInstitucion()) ? "selected" : "" %>>
						<%= inst %>
					</option>
          		<% } %>
        	</select><br/>
        <% } else if (usuarioLoggeado instanceof DTOrganizador) { 
        	organizador = (DTOrganizador) organizador;%>
        	<label for="link"><strong>Sitio Web:</strong></label>
        	<input type="text" id="link" value="<%= organizador.getLink() != null ? organizador.getLink() : "" %>" /><br/>

        	<label for="desc" class="lblDesc"><strong>Descripción:</strong></label>
        	<textarea type="text" id="desc"><%= organizador.getDescripcion() != null ? organizador.getDescripcion() : "" %></textarea><br/>
        <% } %>

        <button id="modifyBtn" type="submit" class="btn_login" disabled>Modificar</button>
      </form>
    </div>

    <div class="flex-shrink-0 p-3 m-3" style="width: 280px">
        <ul class="list-unstyled ps-0">
          <li class="mb-1">
          	<% if (usuarioLoggeado instanceof DTAsistente) { %>
            	Registros
            	<ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
              		<% List<DTRegistro> registros = (List<DTRegistro>) request.getAttribute("registros");
    	      			for (DTRegistro reg : registros) { %>
              			<li><a href="/webdinamica/registros/consultaRegistro?registro=<%= reg.getEdicion() %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded"><%= reg.getEdicion() %></a></li>
              		<% } %>
           		</ul>
            <% } else if (usuarioLoggeado instanceof DTOrganizador) { %>
            	Ediciones
            	<ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
            		<% List<DTEdicion> ediciones = (List<DTEdicion>) request.getAttribute("ediciones");
    	      			for (DTEdicion ed : ediciones) { %>
            			<li><a href="/webdinamica/ediciones/consultaEdicion?evento=<%= ed.getEvento() %>&edicion=<%= ed.getNombre() %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded"><%= ed.getNombre() %></a></li>
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
