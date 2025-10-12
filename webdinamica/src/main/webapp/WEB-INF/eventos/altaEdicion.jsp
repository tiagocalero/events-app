<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Eventos.uy - Alta de Edición Evento</title>

  <link rel="stylesheet" href="/webdinamica/assets/styles.css"> 
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  
  <link rel="icon" type="image/png" href="/webdinamica/assets/logoChico.png"> 
</head>
<body>
  <%@ include file="/components/header.jsp" %>

  <main>
    <%@ include file="/components/sidebar.jsp" %>
    
    <%
      String errorMessage = (String) request.getAttribute("error");
      
      String nombre = (String) request.getAttribute("name");
      String sigla = (String) request.getAttribute("sigla");
      String ciudad = (String) request.getAttribute("ciudad");
      String pais = (String) request.getAttribute("pais");
      String nombreEvento = (String) request.getAttribute("nombreEvento");
      
      
      String contextPath = request.getContextPath();
      String cancelURL = contextPath + "/eventos"; 
    %>

    <div class="content">
      <h3><strong>Ingrese los datos de la nueva edición para el evento: <%= (nombreEvento != null ? nombreEvento : request.getAttribute("nombreEvento")) %></strong></h3><br/>
      
      <% if (errorMessage != null) { %>
         <p style="color: red; font-weight: bold;">¡Error! <%= errorMessage %></p>
      <% } %>
      
          <form class="content-form-alta" onsubmit="return validarFechas()" action="altaEdicion" method="post">
    
            <input type="hidden" name="evento" value="<%= (nombreEvento != null ? nombreEvento : request.getAttribute("nombreEvento")) %>">
            
            <label for= "name">Nombre:</label>
            <input type = "text" id = "name" name = "name" value="${requestScope.name}" required/><br/>
            
            <label for= "sigla">Sigla:</label>
            <input type = "text" id = "sigla" name = "sigla" value="${requestScope.sigla}"/><br/>
            
            <label for= "ciudad">Ciudad:</label>
            <input type = "text" id = "ciudad" name = "ciudad" value="${requestScope.ciudad}"/><br/>

            <label for= "pais">Pais:</label>
            <input type = "text" id = "pais" name = "pais" value="${requestScope.pais}"/><br/>
            
            <label for="fecha-inicio">Selecciona una fecha de inicio:</label>
            <input type="date" id="fecha-inicio" name="fecha-inicio" value="${requestScope['fecha-inicio']}" required><br/>

            <label for="fecha-fin">Selecciona una fecha de fin:</label>
            <input type="date" id="fecha-fin" name="fecha-fin" value="${requestScope['fecha-fin']}" required><br/>

            <label for="foto">Selecciona una foto:</label>
            <input type="file" id="foto" name="foto" accept="image/*"><br/>

            <input type="reset" value = "Borrar"/>
            <input type="submit" value = "Crear"/>
          </form>
    </div>

  </main>
  
  <% if (errorMessage != null) { %>
  <script>
    const errorMsg = "<%= errorMessage %>";
    
    const confirmar = confirm(errorMsg + "\n\n¿Desea modificar el nombre o cancelar la creación de la edición?");

    if (!confirmar) {
        window.location.href = "<%= cancelURL %>"; 
    }
  </script>
  <% } %>
  
  <script>
    function validarFechas() {
        const fechaInicio = document.getElementById('fecha-inicio').value;
        const fechaFin = document.getElementById('fecha-fin').value;

        if (fechaFin < fechaInicio) {
            alert("Error: La fecha de fin no puede ser anterior a la fecha de inicio.");
            return false; // Evita el envío del formulario
        }
        
        return true; // Permite el envío del formulario
    }
  </script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>