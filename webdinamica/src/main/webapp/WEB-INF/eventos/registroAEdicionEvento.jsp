<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Eventos.uy - Registro a Edicion de Evento</title>

  <link rel="stylesheet" href="/webdinamica/assets/styles.css"> 
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  
  <script src="/webdinamica/components/headerLoggedIn.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="/webdinamica/assets/logoChico.png"> 
  </head>
<body>
  <%@ include file="/components/header.jsp" %>

  <main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class = "content">

        <%-- Título dinámico usando los atributos pasados por el Servlet --%>
        <h3>
            <strong>Registro para la edición:</strong> 
            <strong>${nombreEdicion}</strong> del evento <strong>${nombreEvento}</strong>
        </h3><br/>

        <%-- NOTA: Asumimos que validarSeleccionRegistroEdicion() ya está implementada en tu headerLoggedIn.js --%>
        <form class="content-form-alta" onsubmit="return validarSeleccionRegistroEdicion()" action="registroAEdicionEvento" method="post">

            <input type="hidden" name="evento" value="${nombreEvento}">
            <input type="hidden" name="edicion" value="${nombreEdicion}">
            
          <label for="tiposRegistro" style="vertical-align: top ">Seleccione un tipo de Registro:</label>
            <select id="tiposRegistro" name="tiposRegistro" multiple size="3" style="margin-bottom: 20px;">
                <%-- Itera dinámicamente sobre la lista de tiposRegistro pasada por el Servlet --%>
                <% 
                    List<String> tipos = (List<String>) request.getAttribute("tiposRegistro");
                    if (tipos != null) {
                        for (String tipo : tipos) { 
                %>
                        <option value="<%= tipo %>"><%= tipo %></option>
                <% 
                        }
                    } 
                %>
            </select><br/>

            <%-- Campos de la Forma de Registro, integrados en el formulario principal --%>
              <h5>Seleccione la forma de registro:</h5>

              <label>
                <input type="radio" name="tipoRegistro" value="general" required onclick="toggleCodigoPatrocinio()">
                General
              </label><br>

              <label>
                <input type="radio" name="tipoRegistro" value="patrocinio" required onclick="toggleCodigoPatrocinio()">
                Con código de patrocinio
              </label><br><br>

              <div id="campoCodigo" style="display:none;">
                <label>Código de patrocinio:
                  <input type="text" id="codigoPatrocinio" name="codigoPatrocinio">
                </label>
              </div>

              <br>
              <input type="submit" value="Registrarme">
        </form>
    </div>

  </main>

  <script>
    
    function toggleCodigoPatrocinio() {
        const campoCodigoDiv = document.getElementById('campoCodigo');
        const codigoInput = document.getElementById('codigoPatrocinio');
        
        // Obtener el valor del radio button seleccionado
        const tipoRegistro = document.querySelector('input[name="tipoRegistro"]:checked');
        
        if (tipoRegistro && tipoRegistro.value === 'patrocinio') {
            // Si es patrocinio, mostrar el campo y hacerlo requerido
            campoCodigoDiv.style.display = 'block';
            codigoInput.required = true;
        } else {
            // Si es general (o ninguno), ocultar el campo y no requerirlo
            campoCodigoDiv.style.display = 'none';
            codigoInput.required = false;
            codigoInput.value = ''; // Opcional: limpiar el valor si se cambia a General
        }
    }

    
  </script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>