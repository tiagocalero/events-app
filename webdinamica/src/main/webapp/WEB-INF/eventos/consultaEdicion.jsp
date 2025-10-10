<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Eventos.uy - Consulta de Edición Evento</title>
  
  <link rel="stylesheet" href="/webdinamica/assets/styles.css"> 
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  
  <script src="/webdinamica/components/headerLoggedIn.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="/webdinamica/assets/logoChico.png"> 
  </head>
<body>
  <header-component></header-component>

  <main>
    <%@ include file="/components/sidebar.jsp" %>
	
	<%
    // Código Java: ¡Requiere punto y coma!
    String nombreEvento = request.getParameter("evento");
    String nombreEdicion = request.getParameter("edicion"); 
    
    // Construimos la URL de destino con los parámetros
    String urlRegistro = "/webdinamica/ediciones/registroAEdicionEvento?evento=" + nombreEvento + "&edicion=" + nombreEdicion;
	%>
	
    <div class="content">
      <form class="content-form-consulta", method="get", action="registroAEdicionDeEvento.html">
        <div class="edition-detail">
          
          <h2><%= request.getParameter("edicion") %></h2> 
          
          <p><strong>Ciudad:</strong> <%= request.getAttribute("ciudad") %></p>
          <p><strong>País:</strong> <%= request.getAttribute("pais") %></p>
          <p><strong>Sigla:</strong> <%= request.getAttribute("sigla") %></p>
          
          <p><strong>Fecha Inicio:</strong> <%= ((LocalDate) request.getAttribute("fecha-ini")).toString() %></p>
          <p><strong>Fecha Fin:</strong> <%= ((LocalDate) request.getAttribute("fecha-fin")).toString() %></p>
          <p><strong>Fecha Alta:</strong> <%= ((LocalDate) request.getAttribute("fecha-alta")).toString() %></p>
          
          <a href="<%= urlRegistro %>" class="btn btn-primary mt-3">
              Registrarme a esta edición
          </a>
        
        </div>
        <div class="flex-shrink-0 p-3 m-3" style="width: 280px">
            <ul class="list-unstyled ps-0">
              <li class="mb-1">
                Organizador
                <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
                <li><a href="consultaUsuarioOrg.html" class="link-body-emphasis d-inline-flex text-decoration-none rounded"><%= request.getAttribute("organizador") %></a></li>
                </ul>
              </li>
            </ul>
            
            <ul class="list-unstyled ps-0">
              <li class="mb-1">
                Tipos de Registro
                <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
                <% 
                    List<String> tipos = (List<String>) request.getAttribute("tiposRegistros");
                    if (tipos != null) {
                        for (String tipo : tipos) { 
                %>
                        <li><a href="consultaTipoRegistro.html" class="link-body-emphasis d-inline-flex text-decoration-none rounded"><%= tipo %></a></li>
                <% 
                        }
                    } 
                %>
                </ul>
              </li>
            </ul>
            
            </div>

      </form>
    </div>


    <div class="flex-shrink-0 p-3 m-3" style="width: 280px">
        </div>
  </main>
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>