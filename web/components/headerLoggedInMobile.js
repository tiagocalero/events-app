class Header extends HTMLElement {
  constructor() { super(); }

  connectedCallback() {
  	const imgPath = this.getAttribute("imgPath");
    this.innerHTML = `
    <header class="position-relative bg-dark py-4">
        <div class="container d-flex align-items-center justify-content-end">
        
            <div class="position-absolute top-50 start-0 translate-middle-y ms-3">
                <a href="/web">
                    <img src="/assets/icono-izquierdo.png" alt="Inicio" width="40" height="40">
                </a>
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
          </div>
        </div>
    </header>
    `;
  }
}

customElements.define('header-component', Header);