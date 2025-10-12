<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>

<!doctype html>
<html lang="en" data-bs-theme="auto">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="" />
    <meta
      name="author"
      content="Mark Otto, Jacob Thornton, and Bootstrap contributors"
    />
    <meta name="generator" content="Astro v5.13.2" />
    <title>Eventos.uy - Inicio de Sesión</title>
    <link
      rel="canonical"
      href="https://getbootstrap.com/docs/5.3/examples/sign-in/"
    />
    <meta name="theme-color" content="#712cf9" />
    <link href="/webdinamica/assets/styles.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  	<link rel="icon" type="image/png" href="/webdinamica/assets/logoChico.png">
  </head>
  <body class="d-flex align-items-center py-4 bg-body-tertiary">
    <main class="form-signin w-100 m-auto">
      <form action="/webdinamica/usuarios/inicio_sesion" method="post">
        <a href="/webdinamica"><img class="logo" src="/webdinamica/assets/logoNegro.png"> </a>
        <h1 class="h3 mb-3 fw-normal">Inicie sesión</h1>
        <div class="form-floating">
          <input
            class="form-control"
            id="floatingInput"
            name="nick_o_correo"
            placeholder="name@example.com"
          />
          <label for="floatingInput">nickname / mail </label>
        </div>
        <div class="form-floating">
          <input
            type="password"
            class="form-control"
            id="floatingPassword"
            name="passwd"
            placeholder="Password"
          />
          <label for="floatingPassword">contraseña</label>
        </div>
        <div class="form-check text-start my-3">
          <input
            class="form-check-input"
            type="checkbox"
            value="remember-me"
            id="checkDefault"
          />
          <label class="form-check-label" for="checkDefault">
            Remember me
          </label>
        </div>
        
        <% if (request.getAttribute("error") != null) { %>
		    <p style="color:red;"><%= request.getAttribute("error") %></p>
		<% } %>
        <button class="btn_login" type="submit">
          Iniciar sesión
        </button>
        
      </form>
    </main>
    <script
      src="../assets/dist/js/bootstrap.bundle.min.js"
      class="astro-vvvwv3sm"
    ></script>
  </body>
</html>
