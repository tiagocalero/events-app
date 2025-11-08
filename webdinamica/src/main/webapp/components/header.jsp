<%@ page import="main.java.publicar.DtUsuario" %>
<header>
	<div class="container">
<% HttpSession userSession = request.getSession(false);
   DtUsuario usuarioLoggeado = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);
   if (usuarioLoggeado != null) { %>
      <div
        class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start"
      >
        <a
          href="/webdinamica"
          class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none"
        >
          <img class="logo" src="/webdinamica/assets/logo.png">
        </a>
        <ul
          class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0"
        >
        </ul>
        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search" action="/webdinamica/busqueda">
          <input
            type="search"
            class="form-control form-control-dark text-bg-dark"
            placeholder="Buscar..."
            aria-label="Buscar"
            name="b"
            value="<%= request.getParameter("b") != null ? request.getParameter("b") : "" %>"
          />
        </form>
        
        <div class="dropdown text-end">
          <a
            href="#"
            class="d-block link-body-emphasis text-decoration-none"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            <img
              src=<%= (usuarioLoggeado.getImagen() != null)
			  			? "/webdinamica/uploads/" + usuarioLoggeado.getImagen()
						: "/webdinamica/assets/default_profile.jpg"
					%>
              alt="foto de perfil"
              style="object-fit: cover;"
              width="40"
              height="40"
              class="rounded-circle"
            />
          </a>
          <ul class="dropdown-menu text-small">
            <li><a class="dropdown-item" href="/webdinamica/usuarios/consulta?nick=<%= usuarioLoggeado.getNickname() %>">Mi Perfil</a></li>
            <li><hr class="dropdown-divider" /></li>
            <li><a class="dropdown-item" href="/webdinamica/usuarios/fin_sesion">Cerrar Sesión</a></li>
          </ul>
        </div>
      </div>
	<% } else { %>
      <div
        class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start"
      >
        <a
          href="/webdinamica"
          class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none"
        >
          <img class="logo" src="/webdinamica/assets/logo.png">
        </a>
        <ul
          class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0"
        >
        </ul>
        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search" action="/webdinamica/busqueda">
          <input
            type="search"
            class="form-control form-control-dark text-bg-dark"
            placeholder="Buscar..."
            aria-label="Buscar"
            name="b"
            value="<%= request.getParameter("b") != null ? request.getParameter("b") : "" %>"
          />
        </form>
        <div class="text-end">
          <button type="button" class="btn btn-outline-light me-2"
            onclick="window.location.href='/webdinamica/usuarios/inicio_sesion'">
            Iniciar Sesión
          </button>

          <a href="/webdinamica/usuarios/alta" type="button" class="btn btn-warning">Registrarse</a>
          
        </div>

      </div>
	<% }%>
  </div>
</header>