<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Eventos.uy - Consulta Registro</title>
  
  <link rel="stylesheet" href="/webdinamica/assets/styles.css"> 
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  
  <link rel="icon" type="image/png" href="/webdinamica/assets/logoChico.png"> 
</head>
<body>
  <%@ include file="/components/header.jsp" %>

  <main>
    <div class="content content-consulta">
      
      <form id="modifyUserForm" class="content-form-modificar" action="#" method="post">
        
        <h3><strong>Detalles de su Registro en: ${requestScope.edicion}</strong></h3><br/> 
        

        <label for="fecha-registro"><strong>Fecha de Registro:</strong></label>
        <input type="text" id="fecha-registro" value="${requestScope.fecha}" disabled/><br/> 

        <label for="tipo-registro"><strong>Tipo de Registro:</strong></label>
        <input type="text" id="tipo-registro" value="${requestScope.tipo}" disabled/><br/>
        
   <% if ((boolean) request.getAttribute("patrocinio")) { %>
        <label for="patrocinio"><strong>Usó patrocinio:</strong></label>
        <input type="text" id="patrocinio" value="Sí" disabled/><br/>
        <label for="costo-registro"><strong>Costo:</strong></label>
        <input type="text" id="costo-registro" value="$0" disabled/><br/>
     <% } else { %>
        <label for="patrocinio"><strong>Usó patrocinio:</strong></label>
        <input type="text" id="patrocinio" value="No" disabled/><br/>
        <label for="costo-registro"><strong>Costo:</strong></label>
        <input type="text" id="costo-registro" value="\$${requestScope.costo}" disabled/><br/>
     <% } %>
      </form>
      
      <br>
    <% 
        if ((boolean) request.getAttribute("asistencia")) { %>
        <form action="/webmovil/ediciones/descargarConstancia" method="POST" style="margin-top: 20px;">
            <% 
              String nombreEdicion = (String) request.getAttribute("edicion");
              String nombreEvento = request.getParameter("evento");
            %>
            <input type="hidden" name="evento" value="<%= nombreEvento %>"/>
            <input type="hidden" name="edicion" value="<%= nombreEdicion %>"/>
            
              <button type="submit" class="btn btn-success" style="background-color: #28a745; border-color: #28a745;">
                  Descargar Constancia de Asistencia (PDF)
              </button>
        </form>
      <% } else if ((boolean) request.getAttribute("confirmable")) {%>
        <button type="submit" class="btn btn-primary" style="background-color: #28a745; border-color: #28a745;">
            Confirmar asistencia
        </button>
      <% } %>
      </div>

    <!--
    <div class="flex-shrink-0 p-3 m-3" style="width: 280px">
        <ul class="list-unstyled ps-0">
          <li class="mb-1">
            Edición
            <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
              
              
            String urlConsultaEdicion = "/webdinamica/ediciones/consultaEdicion?evento=" + URLEncoder.encode(nombreEvento, StandardCharsets.UTF_8) + "&edicion=" + URLEncoder.encode(nombreEdicion, StandardCharsets.UTF_8);
              %>
              
              <li>
                 <a href=" urlConsultaEdicion %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
                     nombreEdicion %>
                 </a>
              </li>
              
            </ul>
          </li>
        </ul>
    </div> -->
  </main>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>