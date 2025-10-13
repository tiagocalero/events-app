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
        
        <%-- LÓGICA DE MANEJO DE ERRORES CON SCRIPTLET (Reemplazo de JSTL) --%>
        <%
            // Obtener el mensaje de error del request
            String errorRegistro = (String) request.getAttribute("errorRegistro");
            
            if (errorRegistro != null && !errorRegistro.isEmpty()) {
        %>
            <div class="alert alert-danger" role="alert">
                <h4>❌ Error en el Registro</h4>
                <p><strong>Motivo:</strong> <%= errorRegistro %></p>
                
                <%-- Opciones de Reintentar o Cancelar --%>
                <hr>
                <p class="mb-0">
                    Puedes corregir el error y **reintentar**, o **cancelar** la operación.
                </p>
                
                <%-- Botón de Cancelar: Vuelve a la consulta de la edición (como solicitaste) --%>
                <%-- Usamos las expresiones EL (${...}) ya que tu JSP las usa para nombreEdicion/nombreEvento --%>
                <a href="consultaEdicion?edicion=${nombreEdicion}&evento=${nombreEvento}" class="btn btn-secondary mt-2">
                    Cancelar y Volver a la Edición
                </a>
                
            </div>
        <%
            }
        %>
        
        <%-- Formulario de Registro --%>
        <form class="content-form-alta" onsubmit="return validarSeleccionRegistroEdicion()" action="registroAEdicionEvento" method="post">

            <input type="hidden" name="evento" value="${nombreEvento}">
            <input type="hidden" name="edicion" value="${nombreEdicion}">
            
            <label for="tiposRegistro" style="vertical-align: top ">Seleccione un tipo de Registro:</label>
            <%-- Quitamos el 'multiple size="3"' para forzar la selección única --%>
            <select id="tiposRegistro" name="tiposRegistro" required style="margin-bottom: 20px;">
                <option value="" disabled selected>-- Seleccione un tipo --</option>
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

            <%-- Campos de la Forma de Registro: Se usa 'formaRegistro' para los radios --%>
            <h5>Seleccione la forma de registro:</h5>

            <label>
                <input type="radio" name="formaRegistro" value="general" required onclick="toggleCodigoPatrocinio()">
                General
            </label><br>

            <label>
                <input type="radio" name="formaRegistro" value="patrocinio" required onclick="toggleCodigoPatrocinio()">
                Con código de patrocinio
            </label><br><br>

            <div id="campoCodigo" style="display:none;">
                <label for="patrocinio">Código de patrocinio:</label>
                <%-- name="patrocinio" para que el Servlet lo lea con request.getParameter("patrocinio") --%>
                <input type="text" id="patrocinio" name="patrocinio">
            </div>

            <br>
            <input type="submit" value="Registrarme">
        </form>
    </div>

  </main>

  <script>
    
    function toggleCodigoPatrocinio() {
        const campoCodigoDiv = document.getElementById('campoCodigo');
        const codigoInput = document.getElementById('patrocinio');
        
        // Obtener el valor del radio button seleccionado, el nombre del grupo es 'formaRegistro'
        const tipoRegistro = document.querySelector('input[name="formaRegistro"]:checked');
        
        if (tipoRegistro && tipoRegistro.value === 'patrocinio') {
            // Mostrar el campo y hacerlo requerido
            campoCodigoDiv.style.display = 'block';
            codigoInput.required = true;
        } else {
            // Ocultar, no requerir y limpiar el valor
            campoCodigoDiv.style.display = 'none';
            codigoInput.required = false;
            codigoInput.value = '';
        }
    }

  </script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>