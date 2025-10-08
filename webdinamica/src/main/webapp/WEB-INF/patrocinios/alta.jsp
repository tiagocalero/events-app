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
  <script src="/webdinamica/js/altaPatrocinio.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="/webdinamica/assets/logoChico.png">
</head>
<body>
  <header-component></header-component>

  <main>
    <%@ include file="/components/sidebar.jsp" %>

    <div class="content">
      <h3><strong>Ingrese los datos del nuevo patrocinio:</strong></h3><br/>
      <form class="content-form-alta" action="alta" method="post">
        <label for="evento">Evento:</label>
      	<input name="evento" id="evento" value="<%= request.getParameter("evento") %>" readonly><br/>
      	
        <label for="edicion">Edición:</label>
      	<input name="edicion" id="edicion" value="<%= request.getParameter("edicion") %>" readonly><br/>
        
        <label for="institucion">Institución:</label>
        <select id="institucion" name="institucion">
          	<% List<String> instituciones = (List<String>) request.getAttribute("instituciones");
    	       for (String institucion : instituciones) { %>
          		<option value="<%= institucion %>"
          				<%= institucion.equals(request.getAttribute("institucion")) ? "selected" : "" %>>
					<%= institucion %>
				</option>
          	<% } %>
        </select><br/>
        
        <label for="nivel">Nivel patrocinio:</label>
        <select id="nivel" name="nivel">
          <option value="Platino" <%= request.getAttribute("nivel") == "Platino" ? "selected" : "" %>>Platino</option>
          <option value="Oro" <%= request.getAttribute("nivel") == "Oro" ? "selected" : "" %>>Oro</option>
          <option value="Plata" <%= request.getAttribute("nivel") == "Plata" ? "selected" : "" %>>Plata</option>
          <option value="Bronce" <%= request.getAttribute("nivel") == "Bronce" ? "selected" : "" %>>Bronce</option>
        </select><br/>
        
        <label for = "monto">Aporte económico:</label>
        <input type = "number" id = "monto" name = "monto" min="0" step="0.01" class="no-spinner" required
        	   value="<%= request.getAttribute("monto")%>"/><br/>
        
        <label for = "cantRegistros">Cantidad de Registros Gratuitos:</label>
        <input type = "number" id = "cantRegistros" name = "cantRegistros" min="0" step="any" class="no-spinner" required
        	   value="<%= request.getAttribute("cantRegistros")%>"/><br/>
        
        <label for="tipoRegistro">Tipo de registro gratuito:</label>
        <select id="tipoRegistro" name="tipoRegistro">
          	<% List<String> tipoRegistros = (List<String>) request.getAttribute("tipoRegistros");
    	       for (String tipoRegistro : tipoRegistros) { %>
          		<option value="<%= tipoRegistro %>"
          				<%= tipoRegistro.equals(request.getAttribute("tipoRegistro")) ? "selected" : "" %>>
					<%= tipoRegistro %>
				</option>
          	<% } %>
        </select><br/>
        
        <label for="codigo">Código:</label>
        <input type="text" id = "codigo" name = "codigo" required
        	   value="<%= request.getAttribute("codigo") == null ? "" : request.getAttribute("codigo") %>"/><br/>
        
        <% if (request.getAttribute("error") != null) { %>
		    <p style="color:red;"><%= request.getAttribute("error") %></p>
		<% } %>
        
        <input type="reset" value = "Borrar"/>
        <input type="submit" value = "Crear"/>
      </form>
    </div>

  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
