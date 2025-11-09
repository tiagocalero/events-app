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
            
            <div class="foto-perfil-responsive">
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
              <nav class="navbar navbar-expand-lg bg-body-tertiary">
              <div class="container-fluid">
                <a class="navbar-brand" href="#">Navbar</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Dropdown
                      </a>
                      <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                      </ul>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                    </li>
                  </ul>
                  <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>
                    <button class="btn btn-outline-success" type="submit">Search</button>
                  </form>
                </div>
              </div>
            </nav>
            </div>
          </div>
        </div>
    </header>
    `;
  }
}

customElements.define('header-component', Header);
