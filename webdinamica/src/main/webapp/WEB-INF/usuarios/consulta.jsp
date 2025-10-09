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
  <script src="/webdinamica/components/sidebar.js" type="text/javascript" defer></script>
  <script src="/webdinamica/js/consultaUsuario.js" type="text/javascript" defer></script>
  <script src="/webdinamica/js/altaUsuario.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="assets/logoChico.png">
</head>
<body>
  <header-component></header-component>

  <main>
    <sidebar-component></sidebar-component>

    <div class="content content-consulta">
      <form id="modifyUserForm" class="content-form-modificar" action="alta_usuario" method="post">

        <div class="event-image">
          <img id="profile-pic" src="assets/IMG-US01.jpg" alt="atorres.jpg">
          <input id="file-input" type="file" accept="image/*" style="display: none"/>
        </div>

        <label for="nombre"><strong>Nombre:</strong></label>
        <input type="text" id="nombre" value="Ana"/><br/>

        <label for="apellido"><strong>Apellido:</strong></label>
        <input type="text" id="apellido" value="Torres" /><br/>

        <label for="nick"><strong>Nickname:</strong></label>
        <input type="text" id="nick" value="atorres" disabled /><br/>

        <label for="correo"><strong>Email:</strong></label>
        <input type="text" id="correo" value="atorres@gmail.com" disabled /><br/>

        <label for="pass"><strong>Contraseña:</strong></label>
        <input type="password" id="pass" value="123.torres"/>
        <button type="button" id="passbtn" onclick="togglePassword()"><i class="nf nf-cod-eye" style="vertical-align: center"></i></button><br/>

        <label for="nac" class="nacimiento"><strong>Fecha de Nacimiento:</strong></label>
        <input type="date" id="nacimiento" value="1990-12-05"/><br/>

        <label for="institucion"><strong>Institución:</strong></label>
        <select id="institucion" name="institucion">
          <option value="fing">Facultad de Ingeniería</option>
          <option value="none">Sin Institución</option>
        </select><br/>

        <button id="modifyBtn" type="submit" class="btn_login" disabled>Modificar</button>
      </form>
    </div>

    
    <div class="flex-shrink-0 p-3 m-3" style="width: 280px">
        <ul class="list-unstyled ps-0">
          <li class="mb-1">
            Registros
            <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
              <li><a href="consultaRegistroRE02.html" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Maratón de Montevideo 2024<br/>30/07/2024 $500</a></li>
            <li><a href="consultaRegistroRE02.html" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Web Summit 2026<br/>21/08/2025 $300</a></li>
       
            </ul>
          </li>
        </ul>
    </div>
  </main>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
