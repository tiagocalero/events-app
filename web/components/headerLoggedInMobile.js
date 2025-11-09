class Header extends HTMLElement {
  constructor() { super(); }

  connectedCallback() {
  	const imgPath = this.getAttribute("imgPath");
    this.innerHTML = `
    <header class="position-relative bg-dark py-4">
        <link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
        />
        <div class="container d-flex align-items-center justify-content-end">
        
            <!-- 🟢 Botón hamburguesa a la izquierda -->
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
                <a href="/web">
                <img class="logo-responsive-pos" src="/assets/logo.png">
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
                  src=/assets/IMG-US01.jpg
                  alt="mdo"
                  width="40"
                  height="40"
                  class="rounded-circle"
                />
              </a>
              <ul class="dropdown-menu text-small">
                <li><a class="dropdown-item" href="/webdinamica/usuarios">Mi Perfil</a></li>
                <li><hr class="dropdown-divider" /></li>
                <li><a class="dropdown-item" href="sesionFinalizada.html">Cerrar Sesión</a></li>
              </ul>
            </div>
            <!-- 🟢 Menú lateral derecho (offcanvas) -->
            <div class="offcanvas offcanvas-start bg-light" tabindex="-1" id="menuLateral" aria-labelledby="menuLateralLabel" style="background-color: #f2f2f2;">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="menuLateralLabel">Menú</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Cerrar"></button>
            </div>
            <div class="offcanvas-body d-flex flex-column align-items-center text-center" style="background-color: #f2f2f2;">
                <ul class="navbar-nav list-unstyled">
                    <li class="nav-item"><a class="nav-link" href="#">Consulta Edición</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Consulta Registro</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Asistencia</a></li>
               
                </ul> 
                <hr class="w-50 my-3">
                <h5 class="offcanvas-title" id="bienvenida">Bienvenido!!</h5>
                <img class="logo-responsive-pos" src="/assets/logo.png">
            </div>
          </div>
    </header>
    `;
  }
}

customElements.define('header-component', Header);