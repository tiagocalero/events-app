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

    <div class="content">
      <%-- TÍTULO DINÁMICO: Muestra el nombre del evento --%>
      <h3><strong>Ingrese los datos de la nueva edición para el evento: ${nombreEvento}</strong></h3><br/>
      
          <form class="content-form-alta" action="altaEdicion" method="post">
    
            <%-- CAMPO OCULTO: Envía el nombre del evento en el POST --%>
            <input type="hidden" name="evento" value="${nombreEvento}">
            
            <label for= "name">Nombre:</label>
            <input type = "text" id = "name" name = "name" required/><br/>
            
            <label for= "sigla">Sigla:</label>
            <input type = "text" id = "sigla" name = "sigla"/><br/>
            
            <label for= "ciudad">Ciudad:</label>
            <input type = "text" id = "ciudad" name = "ciudad"/><br/>

            <label for= "pais">Pais:</label>
            <input type = "text" id = "pais" name = "pais"/><br/>
            
            <label for="fecha-inicio">Selecciona una fecha de inicio:</label>
            <input type="date" id="fecha-inicio" name="fecha-inicio" required><br/>

            <label for="fecha-fin">Selecciona una fecha de fin:</label>
            <input type="date" id="fecha-fin" name="fecha-fin" required><br/>

            <label for="foto">Selecciona una foto:</label>
            <input type="file" id="foto" name="foto" accept="image/*"><br/>

            <input type="reset" value = "Borrar"/>
            <input type="submit" value = "Crear"/>
          </form>
    </div>

  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>