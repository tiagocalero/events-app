<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="main.java.publicar.DtRegistro" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Eventos.uy - Consulta Registros</title>
  
  <link rel="stylesheet" href="/webdinamica/assets/styles.css"> 
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  
  <link rel="icon" type="image/png" href="/webdinamica/assets/logoChico.png"> 
</head>
<body>
  <%@ include file="/components/header.jsp" %>

  <main>
    <%@ include file="/components/sidebar.jsp" %>
    
    <%
      // Recuperar la lista y la edición del Request
      List<main.java.publicar.DtRegistro> registros = (List<main.java.publicar.DtRegistro>) request.getAttribute("registros");
      String nombreEdicion = (String) request.getAttribute("edicion"); 
    %>

    <div class="content">
      <%-- Uso de Scriptlet para mostrar la edición --%>
      <h3><strong>Consulte los Registros de: <%= nombreEdicion %></strong></h3><br/>
      
      <form class="content-form-alta" action="#" method="post">
        
        <label for="usuarioSelect">Seleccione un Asistente:</label>
        <select id="usuarioSelect" name="asistente" onchange="mostrarDetalles(this.value)">
          <option value="">Seleccione un Asistente</option>
          
          <%//es nombre edicion?
          
            if (registros != null) {
                for (DtRegistro registro : registros) {
          %> 
                    <option value="<%= registro.getNicknameUsuario() %>"><%= registro.getNicknameUsuario() %></option> 
          <%
                }
            }
          %>
          
        </select><br/><br/>
        
        <h5 id="usuarioHeader">Los datos del Registro del Asistente son los siguientes:</h5>

        <label for="fecha-registro">Fecha de Registro:</label>
        <input type="text" id="fecha-registro" value="" readonly/><br/>

        <label for="tipo-registro">Tipo de Registro:</label>
        <input type="text" id="tipo-registro" value="" readonly/><br/>

        <label for="costo-registro">Costo del Registro:</label>
        <input type="text" id="costo-registro" value="" readonly/><br/>

      </form>
    </div>
    
    <div class="flex-shrink-0 p-3 m-3" style="width: 280px">
        <ul class="list-unstyled ps-0">
          <li class="mb-1">
            Edición
            <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
              
              <% 
           
                String nombreEvento = request.getParameter("evento");
                String urlConsultaEdicion = "/webdinamica/ediciones/consultaEdicion?evento=" + URLEncoder.encode(nombreEvento, StandardCharsets.UTF_8) + "&edicion=" + URLEncoder.encode(nombreEdicion, StandardCharsets.UTF_8);
              %>
              
              <li>
                 <a href="<%= urlConsultaEdicion %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
                    <%= nombreEdicion %>
                 </a>
              </li>
              
            </ul>
          </li>
        </ul>
    </div>

  </main>

  <script>
    const registrosJS = [];
    <%
      if (registros != null) {
          for (DtRegistro registro : registros) {
              // Creamos el objeto JS directamente con los datos de Java
    %>
              registrosJS.push({
                  nicknameAsistente: '<%= registro.getNicknameUsuario() %>',
                  fecha: '<%= registro.getFecha() %>',
                  tipo: '<%= registro.getTipo() %>',
                  costo: '<%= registro.getCosto() %>'
              });
    <%
          }
      }
    %>
    
    function mostrarDetalles(nombreAsistente) {
        const registroSeleccionado = registrosJS.find(registro => registro.nicknameAsistente === nombreAsistente);

        if (registroSeleccionado) {
            document.getElementById('fecha-registro').value = registroSeleccionado.fecha;
            document.getElementById('tipo-registro').value = registroSeleccionado.tipo;
            document.getElementById('costo-registro').value = registroSeleccionado.costo;
            document.getElementById('usuarioHeader').innerHTML = `Los datos del Registro de <strong>${nombreAsistente}</strong> son los siguientes:`;
        } else {
            document.getElementById('fecha-registro').value = '';
            document.getElementById('tipo-registro').value = '';
            document.getElementById('costo-registro').value = '';
            document.getElementById('usuarioHeader').innerHTML = `Los datos del Registro del Asistente son los siguientes:`;
        }
    }
  </script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>    
</body>
</html>