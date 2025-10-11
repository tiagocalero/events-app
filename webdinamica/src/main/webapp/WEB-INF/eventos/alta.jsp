<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="logica.DTUsuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Eventos.uy - Alta de Evento</title>
  
  <link rel="stylesheet" href="/webdinamica/assets/styles.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <script src="/webdinamica/components/headerLoggedIn.js" type="text/javascript" defer></script>
  <script src="/webdinamica/js/altaEvento.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="/webdinamica/assets/logoChico.png">
</head>
<body>
  <% HttpSession user = request.getSession(false);
  	 DTUsuario u = (DTUsuario) user.getAttribute("usuario"); %>
  <header-component imgPath="/webdinamica/uploads/<%= u.getImagen() %>"></header-component>

  <main>
    <%@ include file="/components/sidebar.jsp" %>

    <div class="content">
      <h3><strong>Ingrese los datos del nuevo evento:</strong></h3><br/>
      <form class="content-form-alta" onsubmit="return validarSeleccionAltaEvento()" action="alta" method="post" enctype="multipart/form-data">
        <!-- En cada atributo, se carga el valor pasado desde el servlet si existe -->
        
        <label for= "nombre">Nombre:</label>
        <input type = "text" id = "nombre" name = "nombre" 
        	   value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>"/><br/>
        
        <!-- Mostrar mensaje de error si existe -->
        <% if (request.getAttribute("error") != null) { %>
		    <p style="color:red;" id="error_nombre_repetido"><%= request.getAttribute("error") %></p>
		<% } %>
		<p style="color:red; display: none;" id="error_nombre_vacio">No puede estar vacío.</p>
        
        <label for= "desc">Descripción:</label>
        <input type = "text" id = "desc" name = "desc"
        	   value="<%= request.getAttribute("descripcion") != null ? request.getAttribute("descripcion") : "" %>"/><br/>
        
        <label for= "sigla">Sigla:</label>
        <input type = "text" id = "sigla" name = "sigla"
               value="<%= request.getAttribute("sigla") != null ? request.getAttribute("sigla") : "" %>"/><br/>
        
		<label for="imagen">Selecciona una imagen:</label>
		<input type="file" name="imagen" id="imagen" accept="image/*"> <br/>
        
        <!-- Se marcan como seleccionadas las categorías en categoriasA -->
        <%
    		String[] categoriasA = (String[]) request.getAttribute("categoriasA");
    		List<String> seleccionadas = categoriasA != null ? java.util.Arrays.asList(categoriasA) : List.of();
		%>
        
        <label for="categorias" style="vertical-align: top">Selecciona una o varias categorías:</label>
        <select id="categorias" name="categorias" multiple size="3">
            <% List<String> categorias = (List<String>) request.getAttribute("categorias");
    	       for (String categoria : categorias) { %>
          		<option value="<%= categoria %>"
          		        <%= seleccionadas.contains(categoria) ? "selected" : "" %>>
					<%= categoria %>
				</option>
          	<% } %>
        </select><br/>
		<p id="error_categorias" class="error" style="display: none;">Debe seleccionar al menos una categoría.</p>
        
        <input type="reset" value = "Borrar"/>
        <input type="submit" value = "Crear"/>
      </form>
    </div>

  </main>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
