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
          href="/webmovil"
          class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none"
        >
        
          <img class="logo" src="/webmovil/assets/logo.png">
        </a>
        <a
        <div class="dropdown text-end">
        
          <a
            href="#"
            class="boton-izq-hdr"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
          	<svg xmlns="http://www.w3.org/2000/svg" width="16" style="color:white;" height="16" fill="#FFFFFF" class="bi bi-list" viewBox="0 0 16 16">
  				<path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5"/>
			</svg>
          </a>
          <ul class="dropdown-menu text-small">
            <li><a class="dropdown-item" href="/webmovil">Consulta Edición</a></li>
            <li><a class="dropdown-item" href="/webmovil/fin_sesion">Consulta Registro</a></li>
            <li><a class="dropdown-item" href="/webmovil/fin_sesion">Confirmar Asistencia</a></li>
          </ul>
          <a
            href="#"
            class="d-block link-body-emphasis text-decoration-none"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            <img
              src=<%= (usuarioLoggeado.getImagen() != null)
			  			? "/webmovil/uploads/" + usuarioLoggeado.getImagen()
						: "/webmovil/assets/default_profile.jpg"
					%>
              alt="foto de perfil"
              style="object-fit: cover;"
              width="40"
              height="40"
              class="rounded-circle"
            />
          </a>
          <ul class="dropdown-menu text-small">
            <li><a class="dropdown-item" href="/webmovil/usuarios/">Mi Perfil</a></li>
            <li><hr class="dropdown-divider" /></li>
            <li><a class="dropdown-item" href="/webdinamica/movil/fin_sesion">Cerrar Sesión</a></li>
          </ul>
        </div>
        	
        >
        </a>
        
      </div>
	<% } else { %>
      <div
        class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start"
      >
        <a
          href="/webdinamica"
          class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none"
        >
          <img class="logo" src="/webmovil/assets/logo.png">
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
        </div>

      </div>
	<% }%>
  </div>
</header>