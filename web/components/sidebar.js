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
				<li><a href="index.html" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Tecnología</a></li>
				<li><a href="index.html" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Innovación</a></li>
		        <li><a href="index.html" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Deporte</a></li>
		        <li><a href="index.html" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Salud</a></li>
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
              Acciones
            </button>
            <div class="collapse" id="account-collapse">
              <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
				<li>
				  <a
				    href="altaEvento.html"
				    class="link-body-emphasis d-inline-flex text-decoration-none rounded"
				    >Crear Evento</a
				  >
				</li>
                <li>
                  <a
                    href="altaEdicion.html"
                    class="link-body-emphasis d-inline-flex text-decoration-none rounded"
                    >Crear Edición</a
                  >
                </li>
				  <li>
				    <a
				      href="altaTipoRegistro.html"
				      class="link-body-emphasis d-inline-flex text-decoration-none rounded"
				      >Crear Tipo Registro</a
				    >
				  </li>
				<li>
				  <a
				    href="altaPatrocinio.html"
				    class="link-body-emphasis d-inline-flex text-decoration-none rounded"
				    >Crear Patrocinio</a
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
