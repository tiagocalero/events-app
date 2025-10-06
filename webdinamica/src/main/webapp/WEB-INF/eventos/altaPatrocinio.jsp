<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Eventos.uy - Alta de Patrocinio</title>
  
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
      <h3><strong>Ingrese los datos del nuevo patrocinio:</strong></h3><br/>
      <form class="content-form-alta" action="altaPatrocinio" method="post">
      	<input type="hidden" name="evento" value="<%= request.getParameter("evento") %>">
      	<input type="hidden" name="edicion" value="<%= request.getParameter("edicion") %>">
        
        <label for="institucion">Institución:</label>
        <select id="institucion" name="institucion">
          	<% List<String> instituciones = (List<String>) request.getAttribute("instituciones");
    	       for (String institucion : instituciones) { %>
          		<option value="<%= institucion %>">
					<%= institucion %>
				</option>
          	<% } %>
        </select><br/>
        
        <label for="nivel">Nivel patrocinio:</label>
        <select id="nivel" name="nivel">
          <option value="Platino">Platino</option>
          <option value="Oro">Oro</option>
          <option value="Plata">Plata</option>
          <option value="Bronce">Bronce</option>
        </select><br/>
        
        <label for = "monto">Aporte económico:</label>
        <input type = "number" id = "monto" name = "monto" min="0" step="0.01" class="no-spinner" required/><br/>
        
        <label for = "cantRegistros">Cantidad de Registros Gratuitos:</label>
        <input type = "number" id = "cantRegistros" name = "cantRegistros" min="0" step="any" class="no-spinner" required/><br/>
        
        <label for="tipoRegistro">Tipo de registro gratuito:</label>
        <select id="tipoRegistro" name="tipoRegistro">
          	<% List<String> tipoRegistros = (List<String>) request.getAttribute("tipoRegistros");
    	       for (String tipoRegistro : tipoRegistros) { %>
          		<option value="<%= tipoRegistro %>">
					<%= tipoRegistro %>
				</option>
          	<% } %>
        </select><br/>
        
        <label for="codigo">Código:</label>
        <input type="text" id = "codigo" name = "codigo" required/><br/>
        
        <input type="reset" value = "Borrar"/>
        <input type="submit" value = "Crear"/>
      </form>
    </div>

  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
