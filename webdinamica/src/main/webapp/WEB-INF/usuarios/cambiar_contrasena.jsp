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
  <title>Eventos.uy - Cambiar Contraseña</title>
  <link rel="stylesheet" href="/webdinamica/assets/styles.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <link href="https://www.nerdfonts.com/assets/css/webfont.css" rel="stylesheet">
  <script src="/webdinamica/js/cambiar_contrasena.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="assets/logoChico.png">
</head>
<body>
  <%@ include file="/components/header.jsp" %>

  <main>
    <%@ include file="/components/sidebar.jsp" %>

    <div class="content content-consulta">
      <form id="modificarContrasena" class="content-form-alta" action="cambiar_contrasena" method="post">

        <label for="pass" style="width:300px;"><strong>Contraseña Actual:</strong></label>
        <input type="password" id="pass" name="pass" required/>
		<button type="button" id="passbtn" onclick="togglePassword()"><i class="nf nf-cod-eye" style="vertical-align: center"></i></button>
        <!-- Mostrar mensaje de error si existe -->
       	<% if (request.getAttribute("error") != null) { %>
			<p style="color:red;"><%= request.getAttribute("error") %></p>
		<% } %>
        <br/>

        <label for="newPass" style="width:300px;"><strong>Contraseña Nueva:</strong></label>
        <input type="password" id="newPass" name="newPass" required/>
		<button type="button" id="passbtn2" onclick="toggleNewPassword()"><i class="nf nf-cod-eye" style="vertical-align: center"></i></button><br/>

        <label for="confNewPass" style="width:300px;"><strong>Confirmar Contraseña Nueva:</strong></label>
        <input type="password" id="confNewPass" name="confNewPass" required/>
		<!-- Mostrar mensaje de error si existe -->
       	<% if (request.getAttribute("error3") != null) { %>
			<p style="color:red;"><%= request.getAttribute("error3") %></p>
		<% } %>
		<br/>

        <button id="modifyBtn" type="submit" class="btn_login">Cambiar</button>
        <!-- Mostrar mensaje de error si existe -->
       	<% if (request.getAttribute("error2") != null) { %>
			<p style="color:red;"><%= request.getAttribute("error2") %></p>
		<% } %>
      </form>
    </div>

  </main>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
