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
  <% HttpSession user = request.getSession(false);
  	 DTUsuario usuario = (DTUsuario) user.getAttribute("usuario");
	 DTAsistente asistente = null;
	 DTOrganizador organizador = null;
  	 if (usuario.getImagen() != null) { %>
  	 	<header-component imgPath="/webdinamica/uploads/<%= usuario.getImagen() %>"></header-component>
  	 <% } else { %>
  		<header-component 
  		imgPath="data:image/png;base64,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"
  		></header-component>
  	 <% } %>

  <main>
    <%@ include file="/components/sidebar.jsp" %>

    <div class="content content-consulta">
      <form id="modifyUserForm" class="content-form-modificar" action="alta_usuario" method="post">

        <div class="event-image">
          <img id="profile-pic" src="/webdinamica/uploads/<%= usuario.getImagen() %>" alt="atorres.jpg">
          <input id="file-input" type="file" accept="image/*" style="display: none"/>
        </div>

        <label for="nombre"><strong>Nombre:</strong></label>
        <input type="text" id="nombre" value="<%= usuario.getNombre() != null ? usuario.getNombre() : "" %>"/><br/>

		<% if (usuario instanceof DTAsistente) { 
			asistente = (DTAsistente) usuario; %>
        	<label for="apellido"><strong>Apellido:</strong></label>
        	<input type="text" id="apellido" value="<%= asistente.getApellido() != null ? asistente.getApellido() : "" %>"/><br/>
        <% } %>

        <label for="nick"><strong>Nickname:</strong></label>
        <input type="text" id="nick" value="<%= usuario.getNickname() != null ? usuario.getNickname() : "" %>" disabled /><br/>

        <label for="correo"><strong>Email:</strong></label>
        <input type="text" id="correo" value="<%= usuario.getCorreo() != null ? usuario.getCorreo() : "" %>" disabled /><br/>

        <label for="pass"><strong>Contraseña:</strong></label>
        <input type="password" id="pass" value="<%= user.getAttribute("pass") != null ? user.getAttribute("pass") : "" %>"/>
        <button type="button" id="passbtn" onclick="togglePassword()"><i class="nf nf-cod-eye" style="vertical-align: center"></i></button><br/>

		<% if (usuario instanceof DTAsistente) { %>
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
        <% } else if (usuario instanceof DTOrganizador) { 
        	organizador = (DTOrganizador) organizador;%>
        	<label for="link"><strong>Sitio Web:</strong></label>
        	<input type="text" id="link" value="https://miseventos.com" /><br/>

        	<label for="desc" class="lblDesc"><strong>Descripción:</strong></label>
        	<textarea type="text" id="desc">Empresa de organizacion de eventos.</textarea><br/>
        <% } %>

        <button id="modifyBtn" type="submit" class="btn_login" disabled>Modificar</button>
      </form>
    </div>

    <div class="flex-shrink-0 p-3 m-3" style="width: 280px">
        <ul class="list-unstyled ps-0">
          <li class="mb-1">
          	<% if (usuario instanceof DTAsistente) { %>
            	Registros
            	<ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
              		<% List<DTRegistro> registros = (List<DTRegistro>) request.getAttribute("registros");
    	      			for (DTRegistro reg : registros) { %>
              			<li><a href="/webdinamica/registros/consultaRegistro?registro=<%= reg.getEdicion() %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded"><%= reg.getEdicion() %></a></li>
              		<% } %>
           		</ul>
            <% } else if (usuario instanceof DTOrganizador) { %>
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
