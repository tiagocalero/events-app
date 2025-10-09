<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Eventos.uy - Alta de Institución</title>
  
  <link rel="stylesheet" href="/webdinamica/assets/styles.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous"><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <script src="/webdinamica/components/headerLoggedIn.js" type="text/javascript" defer></script>
  <script src="/webdinamica/js/altaEvento.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="/webdinamica/assets/logoChico.png">
</head>
<body>
  <header-component></header-component>

  <main>
    <%@ include file="/components/sidebar.jsp" %>

    <div class="content">
      <h3><strong>Ingrese los datos de la nueva Institución:</strong></h3><br/>
      <form class="content-form-alta" action="altaInstitucion" method="post">
        <label for="nombre">Nombre*:</label>
        <input type = "text" id = "nombre" name = "nombre" required
        	value=" <%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>"/><br/>
        
        <!-- Mostrar mensaje de error si existe -->
        <% if (request.getAttribute("error") != null) { %>
		    <p style="color:red;" id="error_nombre_repetido"><%= request.getAttribute("error") %></p>
		<% } %>
		<p style="color:red; display: none;" id="error_nombre_vacio">No puede estar vacío.</p>
        
        <label for="descripcion">Descripción*:</label>
        <textarea id="desc" name="desc" rows="4">
    <%= request.getAttribute("desc") != null ? request.getAttribute("desc") : "" %>
</textarea><br/>
        
        <label for="url">Sitio Web*:</label>
        <input type = "text" id = "sitioweb" name = "sitioweb"
        	value="<%= request.getAttribute("sitioweb") != null ? request.getAttribute("sitioweb") : "" %>"/><br/>
        
        <label for="foto">Seleccione una Imágen:</label>
        <input type="file" id="foto" name="foto" accept="image/*"><br/>
        
        <input type="reset" value = "Borrar"/>
        <input type="submit" value = "Crear"/>
      </form>
    </div>

  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
