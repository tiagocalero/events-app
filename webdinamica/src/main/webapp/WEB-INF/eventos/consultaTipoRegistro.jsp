<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Eventos.uy - Consulta de Tipo de Registro</title>
  
  <link rel="stylesheet" href="/webdinamica/assets/styles.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <script src="components/headerLoggedIn.js" type="text/javascript" defer></script>
  <script src="components/sidebar.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="/webdinamica/assets/logoChico.png">
</head>
<body>

  <main>
    <%@ include file="/components/sidebar.jsp" %>

    <div class="content">

      <form class="content-form-consulta" method="get">
        <div class="tipo-registro-detalles">
        
        
          <h2>${param.tipoRegistro}</h2>
          <p><strong>Nombre:</strong> ${tipoRegistro.nombre}</p>
          <p><strong>Descripción:</strong> ${tipoRegistro.descripcion}</p>
          <p><strong>Cupo:</strong> ${tipoRegistro.cupo}</p>
          <p><strong>Costo:</strong> ${tipoRegistro.costo}</p>
        </div>
       
      </form>
    </div>

	  <%--- volver al evento ---%>
	 <div class="flex-shrink-0 p-3 m-3" style="width: 280px">
        <ul class="list-unstyled ps-0">
        	<li class="mb-1">
	          	Volver al evento
	        </li>
	        
	    </ul>
	    
	    <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
	        <li>
	        	<a href="/webdinamica/eventos/consultaEvento?evento=${param.evento}" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
	        		            ${param.evento}
	        	</a>
	        </li>
        </ul>
        
        <ul class="list-unstyled ps-0">
        	<li class="mb-1">
	          	Volver a la edicion
	        </li>
	        
	    </ul>
	    
	    <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
	        <li>
	        	<a href="/webdinamica/ediciones/consultaEdicion?evento=${param.evento}&edicion=${param.edicion}" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
	       				${param.edicion}
	        	</a>
	        </li>
        </ul>
       
      
    

  </div>
  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>