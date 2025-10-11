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
      <form id="modifyUserForm" class="content-form-modificar" action="alta_usuario" method="post">

        <div class="event-image">
          <img id="profile-pic" src="/webdinamica/uploads/<%= usuarioLoggeado.getImagen() %>"  onerror="this.onerror=null; this.src='/webdinamica/assets/default_profile.jpg';"
          	alt="Foto Perfil de <%= usuarioLoggeado.getNickname() %>">
        </div>

        <label for="nombre"><strong>Nombre:</strong></label>
        <input type="text" id="nombre" value="<%= usuarioLoggeado.getNombre() != null ? usuarioLoggeado.getNombre() : "" %>" disabled/><br/>

		<% if (usuarioLoggeado instanceof DTAsistente) { 
			asistente = (DTAsistente) usuarioLoggeado; %>
        	<label for="apellido"><strong>Apellido:</strong></label>
        	<input type="text" id="apellido" value="<%= asistente.getApellido() != null ? asistente.getApellido() : "" %>" disabled/><br/>
        <% } %>

        <label for="nick"><strong>Nickname:</strong></label>
        <input type="text" id="nick" value="<%= usuarioLoggeado.getNickname() != null ? usuarioLoggeado.getNickname() : "" %>" disabled /><br/>

        <label for="correo"><strong>Email:</strong></label>
        <input type="text" id="correo" value="<%= usuarioLoggeado.getCorreo() != null ? usuarioLoggeado.getCorreo() : "" %>" disabled /><br/>

		<% if (usuarioLoggeado instanceof DTAsistente) { %>
        	<label for="nac" class="nacimiento"><strong>Fecha de Nacimiento:</strong></label>
        	<input type="date" id="nacimiento"  value="<%= asistente.getNacimiento() != null ? asistente.getNacimiento() : "" %>" disabled/><br/>

        	<label for="institucion"><strong>Institución:</strong></label>
        	<input type="text" id="institucion" name="institucion" value="<%= asistente.getNacimiento() != null ? asistente.getNacimiento() : "Sin Institucion" %>" disabled/><br/>
        <% } else if (usuarioLoggeado instanceof DTOrganizador) { 
        	organizador = (DTOrganizador) usuarioLoggeado;%>
        	<label for="link"><strong>Sitio Web:</strong></label>
        	<input type="text" id="link" value="<%= organizador.getLink() != null ? organizador.getLink() : "" %>" disabled/><br/>

        	<label for="desc" class="lblDesc"><strong>Descripción:</strong></label>
        	<textarea type="text" id="desc" disabled><%= organizador.getDescripcion() != null ? organizador.getDescripcion() : "" %></textarea><br/>
        <% } %>
      </form>
    </div>
  </main>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
