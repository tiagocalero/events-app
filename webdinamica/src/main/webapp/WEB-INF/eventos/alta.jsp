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
  	 DTUsuario usuario = (DTUsuario) user.getAttribute("usuario");
  	 if (usuario.getImagen() != null) { %>
  	 	<header-component-logeado imgPath="/webdinamica/uploads/<%= usuario.getImagen() %>"></header-component-logeado>
  	 <% } else { %>
  		<header-component-logeado
  		imgPath="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMwAAADACAMAAAB/Pny7AAAAKlBMVEXMzMzy8vL19fXS0tLh4eHZ2dnr6+vv7+/JycnPz8/k5OTc3NzV1dXo6Og1EEG5AAAFxklEQVR4nO2b2XajMAxAjXfZ5v9/d7wRjAMpBCKSOboPnbbpFN/IktcyRhAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRBfj43c3YaLsEwE78Pv61gLyrvRcK7F3W05RexaIokMnA8R95OhgfQhhUQ+RBL67na9ibVCamOGBSbc3azDpMLlY0SakEz4H+tnOSSzR/6Xxy9L0tzdut2kRAE/dgHhg3EKgi5JA3c3cicglDO8NYmfmlGKNFxaltPHqB/oZyBC7lu8E1FsGvety6/5e9v5B7GtQqV07yPivGBNGKz/9qSxScTptnPFz4x2PgDrOhTkV791EmAhNIP7ZBJFlOhF8o8bnpMGv6F/EFM6ZvtyKInRSUkitupVThrDvyxpYkoHWUX45BFnkS6LbFYrq9IPc/c9xTmFJI4ki3EkhcS1EQHIk4A+Zyz/pqSJE8fgSv1t81371L626Ra8NqNfxgBsrnj8O5Im1FkKr9U357MLdSRpWm597oG8n1bKHBqJ2eaOPEcBkWcpi7JldHzvV5fCcvqhzkZkmfHOpIGgnOaLaUoSkWEr18Nj3t83vPzfu5ImTrdkWu+2MTGpAK+HpCDnnx0WCWJL5Wi/hzRTs0mkmziWd3aU3ssXmMZ8bF/wI++/h1ANLIvrXaeHoReZq3EZW1Z5KtzdC+23EGbRabq1JXIxn94VSF17OQX+JB+WgYWJGc306XbPOszjAeazLlbUR8VHjnFwF3rS0pdhImXs/axMnFeVsX0Yy0y+yHBnrwVKaftwZOpwwDWUQlMjc3nVyWPr52XqXL1+WWT05TL5935cplSAx1SkPvSMzOpxRsCRsaUD1DlUicwJmbxRoETnY7Fkcm5Oc6jTMsKZNDXt18tI3YyVpJEwP/R9GatqEe7PM7Bk6q7QJTLwGH65XPwOrALAdF5Iivmhb8tYyecxeLG6wYpMXeCq+aHvyzQnNMvlP5pMKMU5r5nPycDQyCyyBk1GlC378zLA2hmyv0WGufxOisdD35dp153qHhlfivM80Xw7Z3wz3b+nADBVivN5GfYIDY+BaW3wZPKTDL9AxoZp0Ox2//BkWD7nykmzIbP/Jkw6rx2Gsd+IQZTxw5Q0qzIgpNt/pg8hPJ90IMqotD2TJ/6rMnEuemibaCWOiDJCp8MXDrAqY3PgBnlmkYMoU5MmvvlrMnEBn2ZvcY7wPogythTncVUmnVmUaqvfvwyDKRPqjGZNJvXBOniUA9eS3cfOKjC7GZQt+2BXZHy7yylbB+V3hwpTpp5MyBUZ0V68iokjptfS4sXInQHClCkFKybNs4zsNvpNvXtp88rBjPuCgxqZugyAJxlhur10bspmRT1MisHZc4iEKsPywwbVyYCV/bkAL1diagFMH+aetw2qjHU1v7vIiGGNMdjQppL/c+6GK6NKK7vI1E215+CkvfbmpfGrZBjk3NAqt/4hI9ZcJqHFa3nqtl3acGXYmC+OyTYyYM3zLdINtXxN5ltkrMzv9NjK1L6308aoF2MOskzIjSp3k6dupvfFZfJx2zcYkLsZa8pTlfEHVIY8E9086UeWaQtXkYll+uDhc1z6w3pdw5ZRnYyV263eDs64viLF7mbQycChjHnYmNXgYMvYuVMlGavMi0ZvuaQPeuXPgNBlxqXMG71s4vnKHLYMC48RMncz9/7Vk+e7megybBGZMzLD0815dBmrr5NJS9J7ZfxV3az8kvZCLX43C0uZcy6RZk8XXwamYnxJZOYV9i0ybCrOWUaZC26aueU1FkwZ38qw8Oqi6U78fTKh7WYJOMn0iy26DFio+0o/e0WrBaaNpR2bR4eom4yo1YzVZQAfFIgr8QZdprnZf76QdWXtBhmYZ85X3nCeztNxZfpt8mvBljm4h3FQ5voq+RJwl6fLDPpfOkLw6lME+z1/HEgQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEH8D/wDnXg4+PJhj2oAAAAASUVORK5CYII="
  		></header-component-logeado>
  	 <% } %>

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
