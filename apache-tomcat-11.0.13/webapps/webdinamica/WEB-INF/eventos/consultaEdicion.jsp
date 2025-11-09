<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="main.java.publicar.DtOrganizador" %>
<%@ page import="main.java.publicar.DtAsistente" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Eventos.uy - Consulta de Edición Evento</title>
  
  <link rel="stylesheet" href="/webdinamica/assets/styles.css"> 
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  
  <link rel="icon" type="image/png" href="/webdinamica/assets/logoChico.png"> 
  </head>
<body>
  <%@ include file="/components/header.jsp" %>

  <main>
    <%@ include file="/components/sidebar.jsp" %>
	
	<%
    // Código Java: ¡Requiere punto y coma!
    String nombreEvento = request.getParameter("evento");
    String nombreEdicion = request.getParameter("edicion"); 
    String videoUrl = (String) request.getAttribute("video");
    // URL base para el registro y consulta
    String urlRegistroAsistente = "/webdinamica/ediciones/registroAEdicionEvento?evento=" + URLEncoder.encode(nombreEvento, StandardCharsets.UTF_8) + "&edicion=" + URLEncoder.encode(nombreEdicion, StandardCharsets.UTF_8);
    String urlConsultaAsistente = "/webdinamica/usuarios/consultaRegistroAsis?evento=" + URLEncoder.encode(nombreEvento, StandardCharsets.UTF_8) + "&edicion=" + URLEncoder.encode(nombreEdicion, StandardCharsets.UTF_8);
    String urlConsultaOrganizador = "/webdinamica/usuarios/consultaRegistroOrg?evento=" + URLEncoder.encode(nombreEvento, StandardCharsets.UTF_8) + "&edicion=" + URLEncoder.encode(nombreEdicion, StandardCharsets.UTF_8);

    // --- LÓGICA DE VISIBILIDAD DE BOTONES (Organizador/Asistente) ---
    // Obtener datos del organizador de la edición
    String organizadorNickname = (String) request.getAttribute("organizador");
    
    // Chequeos de rol y registro
    boolean esOrganizador = (usuarioLoggeado != null) && (usuarioLoggeado instanceof DtOrganizador);
    boolean esAsistente = (usuarioLoggeado != null) && (usuarioLoggeado instanceof DtAsistente);
    
    // NOTA: 'estaRegistrado' DEBE ser enviado por el Servlet (true/false)
    Boolean estaRegistrado = (Boolean) request.getAttribute("estaRegistrado");
    if (estaRegistrado == null) {
        estaRegistrado = false; // Valor por defecto seguro
    }
    
    // Chequeo si el usuario logueado es el organizador de ESTA edición
    boolean esElOrganizadorDeEstaEdicion = esOrganizador && 
                                           ((DtOrganizador) usuarioLoggeado).getNickname().equals(organizadorNickname);
                                           
	%>
	
    <div class="content">
      <form class="content-form-consulta" method="get" action="registroAEdicionDeEvento.html">
        <div class="edition-detail">
          
          <h2><%= request.getParameter("edicion") %></h2> 
          
          <p><strong>Ciudad:</strong> <%= request.getAttribute("ciudad") %></p>
          <p><strong>País:</strong> <%= request.getAttribute("pais") %></p>
          <p><strong>Sigla:</strong> <%= request.getAttribute("sigla") %></p>
          
          <p><strong>Fecha Inicio:</strong> <%= ((LocalDate) request.getAttribute("fecha-ini")).toString() %></p>
          <p><strong>Fecha Fin:</strong> <%= ((LocalDate) request.getAttribute("fecha-fin")).toString() %></p>
          <p><strong>Fecha Alta:</strong> <%= ((LocalDate) request.getAttribute("fecha-alta")).toString() %></p>
          
		   <%
			    // Obtener la URL del video del atributo enviado por el Servlet
			    String videoId = null;
			
			    // Verificar si la URL existe y no está vacía
			    if (videoUrl != null && !videoUrl.trim().isEmpty()) {
			        
			        // --- Lógica para extraer el ID del Video ---
			        
			        // 1. Caso: URL larga (ej: https://www.youtube.com/watch?v=VIDEO_ID)
			        if (videoUrl.contains("v=")) {
			            int startIndex = videoUrl.indexOf("v=") + 2;
			            int endIndex = videoUrl.indexOf("&", startIndex); // Maneja parámetros adicionales
			
			            if (endIndex == -1) {
			                videoId = videoUrl.substring(startIndex);
			            } else {
			                videoId = videoUrl.substring(startIndex, endIndex);
			            }
			        } 
			        // 2. Caso: URL corta (ej: https://youtu.be/VIDEO_ID)
			        else if (videoUrl.contains("youtu.be/")) {
			            int startIndex = videoUrl.indexOf("youtu.be/") + 9;
			            videoId = videoUrl.substring(startIndex);
			        }
			    }
			
			    // Si el ID se extrajo correctamente, mostrar el iframe
			    if (videoId != null) {
		   %>
			  
			    <div class="video-container mt-4">
			        <h3>Video</h3>
			        <%-- Incrustar el video en el iframe usando el ID extraído --%>
			        <iframe width="100%" height="315" 
			                src="https://www.youtube.com/embed/<%= videoId %>" 
			                frameborder="0" 
			                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
			                allowfullscreen>
			        </iframe>
			    </div>
		   <%
			    }
		   %>

          <%-- Lógica de Botones Condicionales --%>
          <% 
            if (esElOrganizadorDeEstaEdicion) { 
              // 1. Organizador de esta Edición: Ver listado de registros
          %>
            <a href="<%= urlConsultaOrganizador %>" class="btn btn-warning mt-3">
              Ver Listado de Registros
            </a>
          <% 
            } else if (esAsistente) {
              if (estaRegistrado) { 
                // 2. Asistente Registrado: Ver detalles de su registro
          %>
                <a href="<%= urlConsultaAsistente %>" class="btn btn-success mt-3">
                  Ver Detalles de mi Registro
                </a>
          <% 
              } else { 
                // 3. Asistente NO Registrado: Botón para registrarse
          %>
                <a href="<%= urlRegistroAsistente %>" class="btn btn-primary mt-3">
                  Registrarme a esta edición
                </a>
          <% 
              }
            } 
            // Si es un Organizador que NO es el de esta edición, o no está logueado, no se muestra ningún botón.
          %>
          
        <!-- Mensaje de error cuando se intenta acceder a AltaPatrocinio incorrectamente -->
       	<% if (request.getAttribute("error") != null) { %>
       		<br/>
       		<br/>
		    <p style="color:red;" id="error_nombre_repetido"><%= request.getAttribute("error") %></p>
		<% } %>
        
        </div>
        <div class="event-image">
	      	<% if (request.getAttribute("imagen") != null && request.getAttribute("imagen") != "") { %>
	       		<img src="/webdinamica/uploads/<%= request.getAttribute("imagen") %>" alt="<%= request.getParameter("edicion") %>">
	    	<% } else {%>
	      		<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMwAAADACAMAAAB/Pny7AAAAKlBMVEXMzMzy8vL19fXS0tLh4eHZ2dnr6+vv7+/JycnPz8/k5OTc3NzV1dXo6Og1EEG5AAAFxklEQVR4nO2b2XajMAxAjXfZ5v9/d7wRjAMpBCKSOboPnbbpFN/IktcyRhAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRBfj43c3YaLsEwE78Pv61gLyrvRcK7F3W05RexaIokMnA8R95OhgfQhhUQ+RBL67na9ibVCamOGBSbc3azDpMLlY0SakEz4H+tnOSSzR/6Xxy9L0tzdut2kRAE/dgHhg3EKgi5JA3c3cicglDO8NYmfmlGKNFxaltPHqB/oZyBC7lu8E1FsGvety6/5e9v5B7GtQqV07yPivGBNGKz/9qSxScTptnPFz4x2PgDrOhTkV791EmAhNIP7ZBJFlOhF8o8bnpMGv6F/EFM6ZvtyKInRSUkitupVThrDvyxpYkoHWUX45BFnkS6LbFYrq9IPc/c9xTmFJI4ki3EkhcS1EQHIk4A+Zyz/pqSJE8fgSv1t81371L626Ra8NqNfxgBsrnj8O5Im1FkKr9U357MLdSRpWm597oG8n1bKHBqJ2eaOPEcBkWcpi7JldHzvV5fCcvqhzkZkmfHOpIGgnOaLaUoSkWEr18Nj3t83vPzfu5ImTrdkWu+2MTGpAK+HpCDnnx0WCWJL5Wi/hzRTs0mkmziWd3aU3ssXmMZ8bF/wI++/h1ANLIvrXaeHoReZq3EZW1Z5KtzdC+23EGbRabq1JXIxn94VSF17OQX+JB+WgYWJGc306XbPOszjAeazLlbUR8VHjnFwF3rS0pdhImXs/axMnFeVsX0Yy0y+yHBnrwVKaftwZOpwwDWUQlMjc3nVyWPr52XqXL1+WWT05TL5935cplSAx1SkPvSMzOpxRsCRsaUD1DlUicwJmbxRoETnY7Fkcm5Oc6jTMsKZNDXt18tI3YyVpJEwP/R9GatqEe7PM7Bk6q7QJTLwGH65XPwOrALAdF5Iivmhb8tYyecxeLG6wYpMXeCq+aHvyzQnNMvlP5pMKMU5r5nPycDQyCyyBk1GlC378zLA2hmyv0WGufxOisdD35dp153qHhlfivM80Xw7Z3wz3b+nADBVivN5GfYIDY+BaW3wZPKTDL9AxoZp0Ox2//BkWD7nykmzIbP/Jkw6rx2Gsd+IQZTxw5Q0qzIgpNt/pg8hPJ90IMqotD2TJ/6rMnEuemibaCWOiDJCp8MXDrAqY3PgBnlmkYMoU5MmvvlrMnEBn2ZvcY7wPogythTncVUmnVmUaqvfvwyDKRPqjGZNJvXBOniUA9eS3cfOKjC7GZQt+2BXZHy7yylbB+V3hwpTpp5MyBUZ0V68iokjptfS4sXInQHClCkFKybNs4zsNvpNvXtp88rBjPuCgxqZugyAJxlhur10bspmRT1MisHZc4iEKsPywwbVyYCV/bkAL1diagFMH+aetw2qjHU1v7vIiGGNMdjQppL/c+6GK6NKK7vI1E215+CkvfbmpfGrZBjk3NAqt/4hI9ZcJqHFa3nqtl3acGXYmC+OyTYyYM3zLdINtXxN5ltkrMzv9NjK1L6308aoF2MOskzIjSp3k6dupvfFZfJx2zcYkLsZa8pTlfEHVIY8E9086UeWaQtXkYll+uDhc1z6w3pdw5ZRnYyV263eDs64viLF7mbQycChjHnYmNXgYMvYuVMlGavMi0ZvuaQPeuXPgNBlxqXMG71s4vnKHLYMC48RMncz9/7Vk+e7megybBGZMzLD0815dBmrr5NJS9J7ZfxV3az8kvZCLX43C0uZcy6RZk8XXwamYnxJZOYV9i0ybCrOWUaZC26aueU1FkwZ38qw8Oqi6U78fTKh7WYJOMn0iy26DFio+0o/e0WrBaaNpR2bR4eom4yo1YzVZQAfFIgr8QZdprnZf76QdWXtBhmYZ85X3nCeztNxZfpt8mvBljm4h3FQ5voq+RJwl6fLDPpfOkLw6lME+z1/HEgQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEH8D/wDnXg4+PJhj2oAAAAASUVORK5CYII=" alt="${param.evento}">
	  		<% } %>
	    </div>

      </form>
    </div>


    
   <div class="flex-shrink-0 p-3 m-3" style="width: 280px">
       <ul class="list-unstyled ps-0">
         <li class="mb-1">
           Organizador
           <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
           <% 
             String organizadorNicknameConsulta = (String) request.getAttribute("organizador");
             String urlConsultaUsuario = "/webdinamica/usuarios/consulta?nick=" + organizadorNicknameConsulta;
           %>
           <li>
             <%-- Enlace dinámico al organizador --%>
             <a href="<%= urlConsultaUsuario %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
               <%= organizadorNicknameConsulta %>
             </a>
           </li>
           </ul>
         </li>
       </ul>
       
       <ul class="list-unstyled ps-0">
         <li class="mb-1">
           Tipos de Registro
           
           <% if (esOrganizador && esElOrganizadorDeEstaEdicion) { %>
           <a href="/webdinamica/TipoRegistro/altaTipoRegistro?evento=<%= URLEncoder.encode(nombreEvento, StandardCharsets.UTF_8) %>&edicion=<%= URLEncoder.encode(nombreEdicion, StandardCharsets.UTF_8) %>" style="margin-left: 1em;height: 2em;" class="btn btn-warning">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16" style="vertical-align: 0em;">
		          <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"></path>
		   		</svg>
           </a>
           <% } %>
           <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
	           <% 
	               List<String> tipos = (List<String>) request.getAttribute("tiposRegistros");
	               if (tipos != null && !tipos.isEmpty()) {
	                   for (String tipo : tipos) { 
	           %>
	           		<li>
	           		
						<a href="/webdinamica/TipoRegistro/consultaTipoRegistro?evento=<%= URLEncoder.encode(nombreEvento, StandardCharsets.UTF_8) %>&edicion=<%= URLEncoder.encode(nombreEdicion, StandardCharsets.UTF_8) %>&tipoRegistro=<%= URLEncoder.encode(tipo, StandardCharsets.UTF_8) %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
   						<%= tipo %>
						</a>
					</li>
				<%
	                   }
	               } else {
                %>
                      <li>No hay tipos de registro disponibles.</li>
                <%
                  }
                %>
	       </ul>
	     </li>
	    </ul>
       
       	
       <ul class="list-unstyled ps-0">
         <li class="mb-1">
           Patrocinios
           <%-- Bloque Condicional para el botón de Patrocinios (solo para el Organizador de la edición) --%>
           <% // No se muestra el botón de crear patrocinio si la edición ya
		   	  // está patrocinada por todas las instituciones
		   	  boolean tieneTodasLasInstituciones = (boolean) request.getAttribute("tieneTodasLasInstituciones");
              if (esOrganizador && esElOrganizadorDeEstaEdicion && !tieneTodasLasInstituciones) { %>
           <a href="/webdinamica/patrocinios/alta?evento=<%= URLEncoder.encode(nombreEvento, StandardCharsets.UTF_8) %>&edicion=<%= URLEncoder.encode(nombreEdicion, StandardCharsets.UTF_8) %>" style="margin-left: 1em;height: 2em;" class="btn btn-warning">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16" style="vertical-align: 0em;">
		          <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"></path>
		   		</svg>
           </a>
           <% } %>
           
           <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
	           <% 
	               List<String> instituciones = (List<String>) request.getAttribute("instituciones");
	               if (instituciones != null && !instituciones.isEmpty()) {
	                   for (String institucion : instituciones) { 
	           %>
	                   <li><a href="/webdinamica/patrocinios?evento=<%= URLEncoder.encode(nombreEvento, StandardCharsets.UTF_8) %>&edicion=<%= URLEncoder.encode(nombreEdicion, StandardCharsets.UTF_8) %>&institucion=<%= URLEncoder.encode(institucion, StandardCharsets.UTF_8) %>" class="link-body-emphasis d-inline-flex text-decoration-none rounded"><%= institucion %></a></li>
	           <% 
	                   }
	               } else {
               %>
                     <li>No hay patrocinios disponibles.</li>
               <%
                 }
               %>
	       </ul>
	     </li>
	    </ul>
       </div>
  </main>
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>