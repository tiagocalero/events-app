<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="logica.DTPatrocinio" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Eventos.uy - Consulta de Patrocinio</title>
  
  <link rel="stylesheet" href="/webmovil/assets/styles.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <script src="/webmovil/components/header.js" type="text/javascript" defer></script>
  <script src="/webmovil/components/sidebar.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="assets/logoChico.png">
</head>
<body>
  <header-component></header-component>

  <main>
    <sidebar-component></sidebar-component>
    
    <% DTPatrocinio dtp = (DTPatrocinio) request.getAttribute("dtp"); %>
    <div class="content">
    <form class="content-form-consulta", method="get">
      <div class="event-detail">
        <h2>Patrocinio de <b><%= dtp.getInstitucion() %></b> a <b><%= request.getParameter("edicion") %></b></h2>
        
        <p><strong>Código:</strong> <%= dtp.getCodigo() %></p>
        <p><strong>Aporte económico:</strong> $<%= dtp.getMonto() %></p>
        <p><strong>Cantidad de registros gratuitos:</strong> <%= dtp.getCantRegistros() %></p>
        <p><strong>Institución:</strong><%= dtp.getInstitucion() %></p>
	    <p><strong>Fecha Alta:</strong> <%= dtp.getFechaAlta() %></p>
      </div>
    </form>
    </div>

    <div class="flex-shrink-0 p-3 m-3" style="width: 280px">
        <ul class="list-unstyled ps-0">
          <li class="mb-1">
            Evento
            <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
		        <li><a href="consultaEvento2.html" class="link-body-emphasis d-inline-flex text-decoration-none rounded"><%= request.getParameter("evento") %></a></li>
		    </ul>
          </li>
        </ul>
        <ul class="list-unstyled ps-0">
          <li class="mb-1">
            Edición
            <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
		        <li><a href="consultaEdicionEDEV08.html" class="link-body-emphasis d-inline-flex text-decoration-none rounded"><%= request.getParameter("edicion") %></a></li>
            </ul>
          </li>
        </ul>
        <ul class="list-unstyled ps-0">
          <li class="mb-1">
            Tipo de Registro
            <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
		        <li><a href="consultaTipoRegistroTR19.html" class="link-body-emphasis d-inline-flex text-decoration-none rounded"><%= dtp.getTipoRegistro() %></a></li>
            </ul>
          </li>
        </ul>
    </div>
  </main>
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
