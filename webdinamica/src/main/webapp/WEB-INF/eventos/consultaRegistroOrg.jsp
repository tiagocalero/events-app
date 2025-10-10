<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Eventos.uy - Consulta Registro</title>
  
  <link rel="stylesheet" href="/webdinamica/assets/styles.css"> 
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  
  <script src="/webdinamica/components/headerLoggedIn.js" type="text/javascript" defer></script>
  <script src="/webdinamica/components/sidebar.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="/webdinamica/assets/logoChico.png"> 
<body>
  <header-component></header-component>

  <main>
    <sidebar-component></sidebar-component>

    <div class="content">
      <h3><strong>Consulte los Registros de: ${requestScope.nombreEdicion}</strong></h3><br/>
      
      <form class="content-form-alta" action="#" method="post">
        
        <label for="usuarioSelect">Seleccione un Asistente:</label>
        <select id="usuarioSelect" name="asistente" onchange="mostrarDetalles(this.value)">
          <option value="">Seleccione un Asistente</option>
          
          <c:forEach var="registro" items="${requestScope.registros}">
             <option value="${registro.nombreAsistente}">${registro.nombreAsistente}</option>
          </c:forEach>
          
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

  </main>

  <script>
    // 3.1. TRASPASAR LOS DATOS DE JAVA/JSP A JAVASCRIPT
    // Se recomienda usar JSON para pasar la lista de objetos de manera limpia
    
    // Convertimos la lista de registros a una cadena JSON
    const registrosJSON = '${fn:escapeXml(registrosJSON)}';
    
    // Si usas un Servlet con librerías como Jackson/Gson para generar JSON en el backend,
    // este es el código que usarías en el JSP:
    const registrosData = ${registrosJSON}; 

    // Si NO usas librerías de JSON en el backend, puedes generar un array JS manualmente
    // **ASUMIENDO que el Servlet ha agregado los datos en el request como 'registros'**
    const registrosJS = [];
    <c:forEach var="reg" items="${requestScope.registros}">
        registrosJS.push({
            nombreAsistente: '${reg.nombreAsistente}',
            fecha: '${reg.fecha}',
            tipo: '${reg.tipo}',
            costo: '${reg.costo}'
        });
    </c:forEach>
    
    // Usaremos 'registrosJS' para la función 'mostrarDetalles'
    
    // 3.2. FUNCIÓN JAVASCRIPT PARA ACTUALIZAR LOS CAMPOS
    function mostrarDetalles(nombreAsistente) {
        // Encontrar el registro en el array JS
        const registroSeleccionado = registrosJS.find(registro => registro.nombreAsistente === nombreAsistente);

        if (registroSeleccionado) {
            // Actualizar los campos del formulario
            document.getElementById('fecha-registro').value = registroSeleccionado.fecha;
            document.getElementById('tipo-registro').value = registroSeleccionado.tipo;
            document.getElementById('costo-registro').value = `\$${registroSeleccionado.costo}`;
            document.getElementById('usuarioHeader').innerHTML = `Los datos del Registro de <strong>${nombreAsistente}</strong> son los siguientes:`;
        } else {
            // Limpiar los campos si se selecciona la opción "Seleccione un Asistente"
            document.getElementById('fecha-registro').value = '';
            document.getElementById('tipo-registro').value = '';
            document.getElementById('costo-registro').value = '';
            document.getElementById('usuarioHeader').innerHTML = `Los datos del Registro del Asistente son los siguientes:`;
        }
    }
    
    // Si tu `DTRegistro` tiene un campo para "Uso codigo de Patrocinio", 
    // puedes incluirlo también en los objetos y en la función.

  </script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>    
</body>
</html>
