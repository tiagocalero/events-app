<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
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
  <header-component></header-component>

  <main>
    <%@ include file="/components/sidebar.jsp" %>
    
    <% HttpSession user = request.getSession(false); %>

    <div class="content content-consulta">
      <form id="modifyUserForm" class="content-form-modificar" action="alta_usuario" method="post">

        <div class="event-image">
          <img id="profile-pic" src="<%= user.getAttribute("imgPerfil") %>" alt="atorres.jpg">
          <input id="file-input" type="file" accept="image/*" style="display: none"/>
        </div>

        <label for="nombre"><strong>Nombre:</strong></label>
        <input type="text" id="nombre" value="<%= user.getAttribute("name") != null ? user.getAttribute("name") : "" %>"/><br/>

		<% if (user.getAttribute("tipoUsuario") == "Asistente") { %>
        <label for="apellido"><strong>Apellido:</strong></label>
        <input type="text" id="apellido" value="<%= user.getAttribute("apellido") != null ? user.getAttribute("apellido") : "" %>"/><br/>
        <% } %>

        <label for="nick"><strong>Nickname:</strong></label>
        <input type="text" id="nick" value="<%= user.getAttribute("nick") != null ? user.getAttribute("nick") : "" %>" disabled /><br/>

        <label for="correo"><strong>Email:</strong></label>
        <input type="text" id="correo" value="<%= user.getAttribute("correo") != null ? user.getAttribute("correo") : "" %>" disabled /><br/>

        <label for="pass"><strong>Contraseña:</strong></label>
        <input type="password" id="pass" value="<%= user.getAttribute("pass") != null ? user.getAttribute("pass") : "" %>"/>
        <button type="button" id="passbtn" onclick="togglePassword()"><i class="nf nf-cod-eye" style="vertical-align: center"></i></button><br/>

		<% if (user.getAttribute("tipoUsuario") == "Asistente") { %>
        	<label for="nac" class="nacimiento"><strong>Fecha de Nacimiento:</strong></label>
        	<input type="date" id="nacimiento"  value="<%= user.getAttribute("nacimiento") != null ? user.getAttribute("nacimiento") : "" %>"/><br/>

        	<label for="institucion"><strong>Institución:</strong></label>
        	<select id="institucion" name="institucion">
          		<% List<String> instituciones = (List<String>) request.getAttribute("instituciones");
    	    		for (String inst : instituciones) { %>
          			<option value="<%= inst %>"
          		    <%= inst.equals(user.getAttribute("inst")) ? "selected" : "" %>>
						<%= inst %>
					</option>
          		<% } %>
        	</select><br/>
        <% } else if (user.getAttribute("tipoUsuario") == "Organizador") { %>
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
          	<% if (user.getAttribute("tipoUsuario") == "Asistente") { %>
            	Registros
            	<ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
              		<% List<String> registros = (List<String>) request.getAttribute("registros");
    	      			for (String reg : registros) { %>
              			<li><a href="/webdinamica/registros?nomReg=<%= reg %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded"><%= reg %></a></li>
              		<% } %>
           		</ul>
            <% } else if (user.getAttribute("tipoUsuario") == "Organizador") { %>
            	Ediciones
            	<ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
            		<% List<String> registros = (List<String>) request.getAttribute("registros");
    	      			for (String reg : registros) { %>
            			<li><a href="/webdinamica/ediciones?nomEd=<%= ed %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded"><%= ed %></a></li>
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
