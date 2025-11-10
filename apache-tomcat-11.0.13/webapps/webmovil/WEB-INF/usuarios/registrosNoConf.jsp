<%@ page import="java.util.List" %>
<%@ page import="main.java.publicar.DtUsuario" %>
<%@ page import="main.java.publicar.DtAsistente" %>
<%@ page import="main.java.publicar.DtRegistro" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Eventos.uy - Registros No Confirmados</title>
  
  
  <link rel="stylesheet" href="/webmovil/assets/styles.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <link rel="icon" type="image/png" href="assets/logoChico.png">
</head>
<body>
  <%@ include file="/components/header.jsp" %>

  <main>
    <div class="container py-3">
      <div class="row gy-3">
        <div class="list-group m-3 w-50">
          <% 
            main.java.publicar.PublicadorUsuarioService service = new main.java.publicar.PublicadorUsuarioService();
            main.java.publicar.PublicadorUsuario port = service.getPublicadorUsuarioPort();
            List<DtRegistro> lista = (List<DtRegistro>) request.getAttribute("regnc");
            for (DtRegistro reg : lista) {
              if (!reg.isAsistencia()){
                String nombreEdicion = reg.getNombreEdicion(); 
                String nombreEvento = reg.getNombreEvento();             
                String redirectUrl = request.getContextPath() +  "/usuarios/consultaRegistroAsis?evento=" + nombreEvento + "&edicion=" + nombreEdicion;   
          %>
            <div class="list-group-item list-group-item-action align-items-center item-usuario" style="display:flex;">
              <a class="text-black text-decoration-none" href="<%= redirectUrl %>" style="width:400px;">
                <%= nombreEdicion %>
              </a>
            </div>
          <%  }
            } %>
        </div>
      </div>
    </div>
  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
