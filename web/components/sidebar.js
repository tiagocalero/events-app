class Sidebar extends HTMLElement {
  constructor() { super(); }

  connectedCallback() {
    this.innerHTML = `
    <div class="flex-shrink-0 p-3" style="width: 280px">
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

		        <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded"
		        >Tecnología</a></li>
		        <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Innovación</a></li>
		        <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Literatura</a></li>
		        <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Cultura</a></li>
		        <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Música</a></li>
		        <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Deporte</a></li>
		        <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Salud</a></li>
		        <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Entretenimiento</a></li>
		        <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Agro</a></li>
		        <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Negocios</a></li>
		        <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Moda</a></li>
		        <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Investigación</a></li>
              </ul>
            </div>
          </li>
          <li class="border-top my-3"></li>
          <li class="mb-1">
            <button
              class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed"
              data-bs-toggle="collapse"
              data-bs-target="#account-collapse"
              aria-expanded="false"
            >
              Account
            </button>
            <div class="collapse" id="account-collapse">
              <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                <li>
                  <a
                    href="#"
                    class="link-body-emphasis d-inline-flex text-decoration-none rounded"
                    >New...</a
                  >
                </li>
                <li>
                  <a
                    href="#"
                    class="link-body-emphasis d-inline-flex text-decoration-none rounded"
                    >Profile</a
                  >
                </li>
                <li>
                  <a
                    href="#"
                    class="link-body-emphasis d-inline-flex text-decoration-none rounded"
                    >Settings</a
                  >
                </li>
                <li>
                  <a
                    href="#"
                    class="link-body-emphasis d-inline-flex text-decoration-none rounded"
                    >Sign out</a
                  >
                </li>
              </ul>
            </div>
          </li>
        </ul>
    </div>
    `;
  }
}

customElements.define('sidebar-component', Sidebar);
