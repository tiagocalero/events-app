class Header extends HTMLElement {
  constructor() { super(); }

  connectedCallback() {
    this.innerHTML = `
    <header>
        <div class="container">
          <div
            class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start"
          >
            <a
              href="index.html"
              class="logo"
            >
              <img class="logo" src="assets/logo.png">
            </a>
            <ul
              class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0"
            >
            </ul>
            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
              <input
                type="search"
                class="form-control form-control-dark text-bg-dark"
                placeholder="Search..."
                aria-label="Search"
              />
            </form>
            <div class="text-end">
              <button type="button" class="btn btn-outline-light me-2"
                onclick="window.location.href='inicio_sesion.html'">
                Iniciar Sesión
              </button>

              <a href="altaUsuario.html" type="button" class="btn btn-warning">Registrarse</a>
              
            </div>

          </div>
        </div>
    </header>
    `;
  }
}

customElements.define('header-component', Header);
