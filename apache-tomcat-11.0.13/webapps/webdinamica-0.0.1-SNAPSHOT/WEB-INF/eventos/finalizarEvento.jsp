<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="logica.DTUsuario" %>
<%@ page import="logica.DTEvento" %>
<%@ page import="main.java.publicar.DtAsistente" %>
<%@ page import="main.java.publicar.DtOrganizador" %>
<%@ page import="logica.DTEdicion" %>
<%@ page import="logica.DTRegistro" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Eventos.uy - Perfil</title>
  <link rel="stylesheet" href="/webdinamica/assets/styles.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <link href="https://www.nerdfonts.com/assets/css/webfont.css" rel="stylesheet">
  <script src="/webdinamica/js/consultaUsuario.js" type="text/javascript" defer></script>
  <script src="/webdinamica/js/altaUsuario.js" type="text/javascript" defer></script>
  <link rel="icon" type="image/png" href="assets/logoChico.png">
</head>
<body>
  <%@ include file="/components/header.jsp" %>

  <main>
    <%@ include file="/components/sidebar.jsp" %>
    <% DtAsistente asistente = null;
       DtOrganizador organizador = null; %>

    <div class="content content-consulta">
        
        <h2 class="mb-4">Finalizar un Evento</h2>
        
        <ul class="list-unstyled ps-0">
          <li class="mb-1">
          	<% if (usuarioLoggeado instanceof DtAsistente) { %>
                Registros
            	<ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
              		<% List<DTRegistro> registros = (List<DTRegistro>) request.getAttribute("registros");
    	      			for (DTRegistro reg : registros) { %>
              			<li><a href="/webdinamica/usuarios/consultaRegistroAsis?edicion=<%= URLEncoder.encode(reg.getEdicion(), StandardCharsets.UTF_8) %>&evento=<%= URLEncoder.encode(reg.getEvento(), StandardCharsets.UTF_8) %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded"><%= reg.getEdicion() %></a></li>
              		<% } %>
           		</ul>
            <% } else if (usuarioLoggeado instanceof DtOrganizador) { %>
            	
            	<p class="mb-4">Lista de eventos para gestionar la finalización:</p>
            	
            	<ul style="margin-top: 1em;" class="list-unstyled fw-normal small">
            		<% 
                    List<String> eventos = (List<String>) request.getAttribute("eventosCreados");
    	      			if (eventos != null && !eventos.isEmpty()) {
							for (String nombreEvento : eventos) { 
                                // Generamos un ID único para la sección de confirmación de cada evento
                                String confirmId = "confirm-" + nombreEvento.replaceAll("[^a-zA-Z0-9]", "");
                            %>
            			<li class="d-flex flex-column mb-3 p-2 border rounded">
                            <div class="d-flex justify-content-between align-items-center mb-2">
                                
                                <%-- Enlace para consultar el Evento --%>
                                <a href="/webdinamica/eventos/consultaEvento?nombre=<%= URLEncoder.encode(nombreEvento, StandardCharsets.UTF_8) %>" 
                                   class="link-body-emphasis text-decoration-none rounded me-2 fs-5 fw-bold">
                                    <%= nombreEvento %>
                                </a>
                                
                                <div class="d-flex align-items-center">
                                    <%-- 1. Botón Inicial para Activar Confirmación --%>
                                    <button type="button" 
                                            class="btn btn-danger btn-sm" 
                                            id="btn-finalizar-<%= confirmId %>"
                                            onclick="
                                                // Oculta todos los cuadros de confirmación abiertos
                                                document.querySelectorAll('.confirm-box').forEach(box => box.style.display = 'none');
                                                document.querySelectorAll('[id^=btn-finalizar-]').forEach(btn => btn.style.display = 'block');
                                                // Muestra el cuadro de confirmación de este evento
                                                document.getElementById('<%= confirmId %>').style.display = 'flex';
                                                // Oculta el botón de Finalizar para evitar doble clic
                                                this.style.display = 'none';">
                                        Finalizar
                                    </button>
                                </div>
                            </div>

                            <%-- 2. Área de Confirmación (Oculta por defecto) --%>
                            <div id="<%= confirmId %>" class="confirm-box alert alert-warning p-2 m-0" style="display: none; align-items: center; justify-content: space-between;">
                                <span class="text-dark small me-3">⚠️ ¿Desea confirmar la finalización de este evento? Esta acción es irreversible.</span>
                                
                                <div class="d-flex">
                                    <%-- Formulario de Envío (Botón de Confirmar) --%>
                                    <form action="/webdinamica/eventos/finalizarEvento" method="post" class="d-inline me-2">
                                        <input type="hidden" name="nombreEvento" value="<%= nombreEvento %>">
                                        <button type="submit" class="btn btn-danger btn-sm">
                                            Confirmar
                                        </button>
                                    </form>
                                    
                                    <%-- Botón de Cancelar --%>
                                    <button type="button" 
                                            class="btn btn-secondary btn-sm"
                                            onclick="
                                                // Oculta el cuadro de confirmación
                                                document.getElementById('<%= confirmId %>').style.display = 'none';
                                                // Muestra el botón inicial de Finalizar
                                                document.getElementById('btn-finalizar-<%= confirmId %>').style.display = 'block';">
                                        Cancelar
                                    </button>
                                </div>
                            </div>
            			</li>
            		<% 	} 
					   } else { %>
					   	<li class="list-group-item text-center text-muted">No hay eventos creados o pendientes de finalizar.</li>
					<% } %>
            	</ul>
                <% } %>
            
          </li>
        </ul>
    </div>

    <div class="flex-shrink-0 p-3 m-3" style="width: 280px">
        
    </div>
  </main>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>