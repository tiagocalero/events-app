<%@ page import="main.java.publicar.DtUsuario" %>
<%
  HttpSession userSession = request.getSession(false);
  DtUsuario usuarioLoggeado = (DtUsuario) ((userSession != null) ? userSession.getAttribute("usuario") : null);
%>
<link
  rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
  />

<header class="position-relative bg-dark py-4">
  <div class="container d-flex align-items-center justify-content-end">
  
      <div class="position-absolute top-50 start-0 translate-middle-y ms-3">
        <button class="navbar-toggler border-0" type="button"
                data-bs-toggle="offcanvas"
                data-bs-target="#menuLateral"
                aria-controls="menuLateral"
                style="color: white; font-size: 1.8rem;">
        <i class="bi bi-list"></i>
        </button>
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
    <div class="offcanvas offcanvas-start bg-light" tabindex="-1" id="menuLateral" aria-labelledby="menuLateralLabel" style="background-color: #f2f2f2;">
      <div class="offcanvas-header">
          <h5 class="offcanvas-title" id="menuLateralLabel">Menú</h5>
          <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Cerrar"></button>
      </div>
      <div class="offcanvas-body d-flex flex-column align-items-center text-center">
          <ul class="list-unstyled ps-0">
            <li class="mb-1">
              <button
                  class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed"
                  data-bs-toggle="collapse"
                  data-bs-target="#home-collapse"
                  aria-expanded="true"
                >
                Categorías
              </button>
              <div class="collapse show" id="home-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <%            
                        List<String> categoriasListado = (List<String>) request.getAttribute("categoriasListado");
                        if (categoriasListado != null && !categoriasListado.isEmpty()) {
                            for (String entry : categoriasListado) {
                      %>
                                <li>
                                    <a href="/webmovil/eventos<%= entry.equals(request.getParameter("categoria")) ? "" : "?categoria=" + entry %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded <%= entry.equals(request.getParameter("categoria")) ? "active bg-warning text-black" : "" %>">
                                        <%= entry %>
                                    </a>
                                </li>
                      <%
                              }
                          }
                      %>
                </ul>
              </div>
            </li>
              
            <% 
               HttpSession userSessionSidebar = request.getSession(false);
               DtUsuario usuarioLoggeadoSidebar = (DtUsuario) ((userSessionSidebar != null) ? userSessionSidebar.getAttribute("usuario") : null);
            %>
            <li class="border-top my-3"></li>
            <li class="mb-1">
              <button
                class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed"
                data-bs-toggle="collapse"
                data-bs-target="#account-collapse"
                aria-expanded="false"
              >
                Acciones
              </button>
              <div class="collapse" id="account-collapse">

                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                  <li>
                    <a href="/webmovil/usuarios/lista" class="link-body-emphasis d-inline-flex text-decoration-none rounded"
                        >Usuarios</a>
                  </li>
                </ul>
              </div>
            </li>
          </ul>
          <hr class="w-50 my-3">
          <img class="logo-responsive-pos" style="height: 60px;" src="/webmovil/assets/logoNegro.png">
      </div>
    </div>
  </div>
</header>