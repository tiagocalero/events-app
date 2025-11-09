<%@ page import="main.java.publicar.DtUsuario" %>
<%
  HttpSession userSession = request.getSession(false);
  DtUsuario usuarioLoggeado = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);
%>

<header class="position-relative bg-dark py-4">
    <div class="container d-flex align-items-center justify-content-end">
    
        <div class="position-absolute top-50 start-0 translate-middle-y ms-3">
            <a href="/webmovil" class="text-white text-decoration-none">
              <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5"/>
</svg>
            </a>
        </div>

        <div class="position-absolute top-50 start-50 translate-middle">
            <a
              href="/webmovil"
              class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none"
            >
              <img class="logo" src="/webdinamica/assets/logo.png">
            </a>
        </div>
        
        <div class="dropdown text-end">
          <a
            href="#"
            class="foto-perfil-responsive d-inline-block"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            <img
              src="<%= (usuarioLoggeado.getImagen() != null)
              ? "/webmovil/uploads/" + usuarioLoggeado.getImagen()
            : "/webmovil/assets/default_profile.jpg" %>"
              alt="foto de perfil"
              style="object-fit: cover;"
              width="40"
              height="40"
              class="rounded-circle"
            />
          </a>
          <ul class="dropdown-menu text-small">
            <li><a class="dropdown-item" href="/webmovil/usuarios/consulta?nick=<%= usuarioLoggeado.getNickname() %>">Mi Perfil</a></li>
            <li><hr class="dropdown-divider" /></li>
            <li><a class="dropdown-item" href="/webmovil/usuarios/fin_sesion">Cerrar Sesión</a></li>
          </ul>
        </div>
      </div>
    </div>
</header>