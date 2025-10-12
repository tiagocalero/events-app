<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Eventos.uy - Alta de Tipo Registro</title>
  
  <link rel="stylesheet" href="/webdinamica/assets/styles.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <script src="/webdinamica/components/headerLoggedIn.js" type="text/javascript" defer></script>
  <script src="/webdinamica/js/altaTipoRegistro.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="/webdinamica/assets/logoChico.png">
</head>
<body>
	<%@ include file="/components/header.jsp" %>
	
  <main>
  	<% String errorMessage = (String) request.getAttribute("error"); %>
  	
    <%@ include file="/components/sidebar.jsp" %>



    <div class="content">
      <h3><strong>Ingrese los datos del nuevo tipo de registro:</strong></h3>
      
      <form class = "content-form-alta "action="altaTipoRegistro" method="post">
      
		<input type="hidden" name="nombreEvento" value="${param.evento}" />
  		<input type="hidden" name="nombreEdicion" value="${param.edicion}" />
  		<label for= "Nombre">Nombre:</label>
        <input type = "text" id = "name" name = "nombre" value="${nombre}" required/><br/>
        <label for= "Descripción">Descripción:</label>
        <input type = "text" id = "Descripción" name = "descripcion" value="${descripcion}"/><br/>
        <label for= "c">Costo:</label>
        <input type = "number" id = "c" name = "costo" min="0" class="no-spinner" value="${costo}" required/><br/>
        <label for= "Cupo">Cupo:</label>
        <input type = "number" id = "Cupo" name = "cupo" min="0" class="no-spinner" value="${cupo}" required/><br/><br/>
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
        window.location.href = "<%= request.getContextPath() + "/eventos"  %>"; 
    }
  </script>
  <% } %>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

</body>
</html>
