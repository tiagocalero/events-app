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
            <li><a href="index.html" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Tecnologí­a</a></li>
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
		      href="/webdinamica/eventos/alta"
		      class="link-body-emphasis d-inline-flex text-decoration-none rounded"
		      >Crear Evento</a>
		  </li>
		  <li>
		    <a
		      href="/webdinamica/instituciones/altaInstitucion"
		      class="link-body-emphasis d-inline-flex text-decoration-none rounded"
		      >Crear Institución</a>
		  </li>
		  <% boolean datosCargados = false;
		     if (getServletContext().getAttribute("datosCargados") != null)
		      	datosCargados = (boolean) getServletContext().getAttribute("datosCargados");
		      	
	      	 // No mostrar el botón de cargar datos si ya se cargaron los datos
	      	 if (!datosCargados) {
	  	  %>
			  <li>
			    <a
			      href="#"
			      class="link-body-emphasis d-inline-flex text-decoration-none rounded"
				  id="carga-datos"
			      >Cargar Datos</a>
			  </li>
	
			  <script>
			    document.getElementById("carga-datos").href = 
			    			"/webdinamica/CargarDatos?redirect=" + 
			    				encodeURIComponent(window.location.href);
			  </script>
		  <% } %>
		</ul>

        </div>
      </li>
    </ul>
</div>