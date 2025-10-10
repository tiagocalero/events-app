<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Eventos.uy - Consulta Registro</title>
  
  <link rel="stylesheet" href="/webdinamica/assets/styles.css"> 
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  
  <script src="/webdinamica/components/headerLoggedIn.js" type="text/javascript" defer></script>
  <script src="/webdinamica/components/sidebar.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="/webdinamica/assets/logoChico.png"> 
<body>

<body>
  <header-component></header-component>

  <main>
    <sidebar-component></sidebar-component>

    <div class="content content-consulta">
      
      <form id="modifyUserForm" class="content-form-modificar" action="#" method="post">
        
        <h3><strong>Detalles de su Registro en: ${requestScope.nombreEdicion}</strong></h3><br/>

        <c:set var="reg" value="${requestScope.registro}"/>

        <label for="fecha-registro"><strong>Fecha de Registro:</strong></label>
        <input type="text" id="fecha-registro" value="${reg.fecha}" readonly/><br/>

        <label for="tipo-registro"><strong>Tipo de Registro:</strong></label>
        <input type="text" id="tipo-registro" value="${reg.tipo}" readonly/><br/>

        <label for="costo-registro"><strong>Costo del Registro:</strong></label>
        <input type="text" id="costo-registro" value="\$${reg.costo}" readonly/><br/>
        
        </form>
      
    </div>

    
    <div class="flex-shrink-0 p-3 m-3" style="width: 280px">
        <ul class="list-unstyled ps-0">
          <li class="mb-1">
            Registros
            <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
              </ul>
          </li>
        </ul>
    </div>
  </main>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>