<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Eventos.uy - Consulta de Evento</title>
  
  <link rel="stylesheet" href="/webdinamica/assets/styles.css"> 
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  
  <script src="/webdinamica/components/headerLoggedIn.js" type="text/javascript" defer></script>
  <script src="/webdinamica/components/sidebar.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="/webdinamica/assets/logoChico.png"> 
</head>
<body>
  <header-component></header-component>

  <main>
    <sidebar-component></sidebar-component>

    <div class="content">
    <form class="content-form-consulta" method="get">
      <div class="event-detail">
        
        <%-- Nombre del Evento: Lo obtenemos del parámetro de la URL. --%>
        <h2>${param.evento}</h2> 
        
        <%-- Descripción: Se accede al atributo "descripcion". --%>
        <p><strong>Descripción:</strong> ${descripcion}</p>
        
        <%-- Sigla: Se accede al atributo "sigla". --%>
        <p><strong>Sigla:</strong> ${sigla}</p>
        
        <%-- Categorías: Accedemos a la lista y la formateamos con un Scriptlet. --%>
        <p><strong>Categorías:</strong> 
          <% 
            List<String> categorias = (List<String>) request.getAttribute("categorias");
            if (categorias != null && !categorias.isEmpty()) {
              out.print(String.join(", ", categorias));
            } else {
              out.print("Sin categorías definidas");
            }
          %>
        </p>
        
        <%-- Fecha Alta: Usamos la notación de corchetes para el guion (-). --%>
        <p><strong>Fecha Alta:</strong> ${requestScope['fecha-alta']}</p>
        
      </div>
      <div class="event-image">
        <img src="assets/IMG-EV04.png" alt="Imagen del evento">
      </div>

    </form>
    </div>

    <%--- Ediciones Dinámicas ---%>
    <div class="flex-shrink-0 p-3 m-3" style="width: 280px">
        <ul class="list-unstyled ps-0">
          <li class="mb-1">
            Ediciones
            
            <% 
              String nombreEvento = request.getParameter("evento");
              
              String urlAltaEdicion = "/webdinamica/ediciones/altaEdicion?evento=" + nombreEvento;
            %>
            
            <%-- Enlace para alta de edición (estático) --%>
            <a href="<%= urlAltaEdicion %>" style="margin-left: 1em;height: 2em;" class="btn btn-warning">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16" style="vertical-align: 0em;">
                  <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"></path>
                </svg>
            </a>
            
            <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
              <%-- Iteración de ediciones usando Scriptlet --%>
              <% 
                List<String> ediciones = (List<String>) request.getAttribute("ediciones");
                
                if (ediciones != null && !ediciones.isEmpty()) {
                    for (String nombreEdicion : ediciones) {
                        // Generamos el enlace dinámico para consultar la edición
              %>
                      <li>
                        <a href="/webdinamica/ediciones/consultaEdicion?evento=<%= nombreEvento %>&edicion=<%= nombreEdicion %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
                          <%= nombreEdicion %>
                        </a>
                      </li>
              <%
                    }
                } else {
              %>
                    <li>No hay ediciones disponibles.</li>
              <%
                }
              %>
            </ul>
          </li>
        </ul>
    </div>
    </div>
  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>