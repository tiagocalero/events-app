<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="styles.css">
  <title>Eventos.uy - Registrarse</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <link href="https://www.nerdfonts.com/assets/css/webfont.css" rel="stylesheet">
  <script src="webmovil/components/header.js" type="text/javascript" defer></script>
  <script src="webmovil/components/sidebar.js" type="text/javascript" defer></script>
  <script src="webmovil/altaUsuario.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="assets/logoChico.png">
</head>
<body>
  <header-component></header-component>

  <main>
    <sidebar-component></sidebar-component>

    <div class="content">
      <h3><strong>Registrar un Usuario:</strong></h3><br/>

          <form class="content-form-alta" onsubmit="return validarSeleccionAltaUsuario()" action="alta_usuario" method="post">

            <label for="tipoUsuario">Tipo de Usuario:</label>
            <button type="button" id="tipoUsuario" onclick="toggleTipoUsuario()">Asistente</button><br/>

            <label for="nickname">Nickname:</label>
            <input type="text" id="nickname" name="nickname" 
            	value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>" required/><br/>

            <label for="name">Nombre:</label>
            <input type="text" id="name" name="name"/><br/>

            <div id="datosAs">
              <label for="apellido">Apellido:</label>
              <input type="text" id="apellido" name="apellido"/><br/>

              <label for="nacimiento">Fecha Nacimiento:</label>
              <input type="date" id="nacimiento" name="nacimiento" required/><br/>

              <label for="institucion">Institución:</label>
              <select id="institucion" name="institucion">
                <option value="none">Sin Institución</option>
                <option value="fing">Facultad de Ingeniería</option>
              </select><br/>
            </div>

            <div>
              <label for="correo">Email:</label>
              <input type="text" id="correo" name="correo" required/><br/>
            </div>

            <div id="datosOrg" style="display:none">
              <label for="link">Sitio Web:</label>
              <input type="text" id="link" name="link"/><br/>

              <label for="desc" id="desclbl" class="lblDesc">Descripción:</label>

              <textarea id="desc" name="desc" rows="5" cols="50" placeholder="Escriba su descripción..."></textarea><br/>
            </div>

            <div>
              <label for="pass">Contraseña:</label>
              <input type="password" id="pass" name="pass" required/>
              <button type="button" id="passbtn" onclick="togglePassword()"><i class="nf nf-cod-eye" style="vertical-align: center"></i></button><br/>

              <label for="passconf">Confirma la Contraseña:</label>
              <input type="password" id="passconf" name="passconf"/><br/>

              <label for="foto">Foto de perfil:</label>
              <input type="file" id="foto" name="foto" accept="image/*"/><br/>
            </div>

            <div id="btns">
              <input type="reset" value="Borrar"/>
              <input type="submit" value="Registrarse"/>
            </div>
          </form>
    </div>

  </main>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>