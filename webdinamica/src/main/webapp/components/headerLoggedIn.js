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
              class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none"
            >
              <img class="logo" src="/webdinamica/assets/logo.png">
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
            
            <div class="dropdown text-end">
              <a
                href="#"
                class="d-block link-body-emphasis text-decoration-none"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <img
                  src="https://github.com/mdo.png"
                  alt="mdo"
                  width="40"
                  height="40"
                  class="rounded-circle"
                />
              </a>
              <ul class="dropdown-menu text-small">
                <li><a class="dropdown-item" href="#">Mi Perfil</a></li>
                <li><hr class="dropdown-divider" /></li>
                <li><a class="dropdown-item" href="sesionFinalizada.html">Cerrar Sesión</a></li>
              </ul>
            </div>
          </div>
        </div>
    </header>
    `;
  }
}

customElements.define('header-component', Header);
