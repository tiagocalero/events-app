class Header extends HTMLElement {
  constructor() { super(); }

  connectedCallback() {
    this.innerHTML = `

    <nav class="navbar navbar-light bg-light px-3">
    <div class="container-fluid position-relative">

        <!-- Botón hamburguesa -->
        <button class="navbar-toggler border-0" type="button" data-bs-toggle="offcanvas" data-bs-target="#menuLateral" aria-controls="menuLateral">
        <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Logo centrado -->
        <span class="navbar-brand mx-auto fw-bold">EventosUy</span>

        <!-- Icono usuario -->
        <a href="#" class="d-inline-block">
        <i class="bi bi-person-circle fs-3"></i>
        </a>
    </div>
    </nav>

    <!-- 🔹 Menú lateral derecho -->
    <div class="offcanvas offcanvas-start" tabindex="-1" id="menuLateral" aria-labelledby="menuLateralLabel">
    <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="menuLateralLabel">Menú</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Cerrar"></button>
    </div>
    <div class="offcanvas-body">
        <ul class="navbar-nav">
        <li class="nav-item"><a class="nav-link" href="#">Consulta Edición</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Consulta Registro</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Asistencia</a></li>
        </ul>
    </div>
    </div>
    `;
  }
}

customElements.define('header-component', Header);