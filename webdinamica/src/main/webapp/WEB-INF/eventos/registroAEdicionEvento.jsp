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
  <header-component></header-component>

  <main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class = "content">

        <%-- Título dinámico usando los atributos pasados por el Servlet --%>
        <h3>
            <strong>Registro para la edición:</strong> 
            <strong>${nombreEdicion}</strong> del evento <strong>${nombreEvento}</strong>
        </h3><br/>

        <%-- 
          NOTA: Se ha quitado el formulario anidado que no es válido en HTML.
          La acción "info_tipo_registro" es la acción que manejará el POST final.
        --%>
        <form class="content-form-alta" onsubmit="return validarSeleccionRegistroEdicion()" action="info_tipo_registro" method="post">

            <%-- Campos ocultos CRUCIALES para enviar Evento y Edición en el POST final --%>
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
                <input type="radio" name="tipoRegistro" value="general" required>
                General
              </label><br>

              <label>
                <input type="radio" name="tipoRegistro" value="patrocinio" required>
                Con código de patrocinio
              </label><br><br>

              <div id="campoCodigo" style="display:none;">
                <label>Código de patrocinio:
                  <input type="text" name="codigoPatrocinio">
                </label>
              </div>

              <br>
              <input type="submit" value="Registrarme">
        </form>
    </div>

  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>