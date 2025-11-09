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
  
  <link rel="stylesheet" href="/webmovil/assets/styles.css"> 
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  
  <link rel="icon" type="image/png" href="/webmovil/assets/logoChico.png"> 
  </head>
<body>
  <%@ include file="/components/header.jsp" %>

  <main>
    <%-- El sidebar lateral /components/sidebar.jsp se ha eliminado para la versión móvil. --%>
	
	<%
    // Lógica Java para preparar variables y chequear roles/registro
    String nombreEvento = request.getParameter("evento");
    String nombreEdicion = request.getParameter("edicion"); 
    String videoUrl = (String) request.getAttribute("video");
    
    // URL de consulta de registro para el asistente
    String urlConsultaAsistente = "/webmovil/eventos/consultaRegistroAsis?evento=" + URLEncoder.encode(nombreEvento, StandardCharsets.UTF_8) + "&edicion=" + URLEncoder.encode(nombreEdicion, StandardCharsets.UTF_8);
    
    // Chequeos de rol y registro
    // Se asume que 'usuarioLoggeado' está disponible desde el header/session.
    boolean esAsistente = (usuarioLoggeado != null) && (usuarioLoggeado instanceof DtAsistente);
    Boolean estaRegistrado = (Boolean) request.getAttribute("estaRegistrado");
    if (estaRegistrado == null) {
        estaRegistrado = false;
    }
                                           
	%>
	
    <div class="content">
      <div class="edition-detail">
        
        <h3><%= request.getParameter("edicion") %></h3>
        
        <%-- 1. IMAGEN DEL EVENTO (Arriba, como en el diseño) --%>
        <div class="event-image">
	      	<% if (request.getAttribute("imagen") != null && request.getAttribute("imagen") != "") { %>
	       		<img src="/webmovil/uploads/<%= request.getAttribute("imagen") %>" alt="<%= request.getParameter("edicion") %>">
	    	<% } else {%>
	      		<img src="data:image/png;base64,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" alt="${param.evento}">
	  		<% } %>
	    </div>

        <%-- 2. DETALLES DEL TEXTO (Contenido mínimo del diseño) --%>
        <h2><%= request.getParameter("edicion") %></h2> 
          
          <p><strong>Ciudad:</strong> <%= request.getAttribute("ciudad") %></p>
          <p><strong>País:</strong> <%= request.getAttribute("pais") %></p>
          <p><strong>Sigla:</strong> <%= request.getAttribute("sigla") %></p>
          
          <p><strong>Fecha Inicio:</strong> <%= ((LocalDate) request.getAttribute("fecha-ini")).toString() %></p>
          <p><strong>Fecha Fin:</strong> <%= ((LocalDate) request.getAttribute("fecha-fin")).toString() %></p>
          <p><strong>Fecha Alta:</strong> <%= ((LocalDate) request.getAttribute("fecha-alta")).toString() %></p>
       	  
       	  <ul class="list-unstyled ps-0">
	         <li class="mb-1">
	           Organizador
	           <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
	           <% 
	             String organizadorNicknameConsulta = (String) request.getAttribute("organizador");
	             String urlConsultaUsuario = "/webmovil/usuarios/consulta?nick=" + organizadorNicknameConsulta;
	           %>
	           <li>
	             <%-- Enlace dinámico al organizador --%>
	             <a>
	               <%= organizadorNicknameConsulta %>
	             </a>
	           </li>
	           </ul>
	         </li>
       	  </ul>
       
       <ul class="list-unstyled ps-0">
         <li class="mb-1">
           Tipos de Registro                      
           <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
	           <% 
	               List<String> tipos = (List<String>) request.getAttribute("tiposRegistros");
	               if (tipos != null && !tipos.isEmpty()) {
	                   for (String tipo : tipos) { 
	           %>
	           		<li>
	           		
						<a>
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
           <ul style="margin-top: 1em;" class="btn-toggle-nav list-unstyled fw-normal small">
	           <% 
	               List<String> instituciones = (List<String>) request.getAttribute("instituciones");
	               if (instituciones != null && !instituciones.isEmpty()) {
	                   for (String institucion : instituciones) { 
	           %>
	                   <li><a><%= institucion %></a></li>
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
	    
	    
        <%-- 3. BOTÓN CONDICIONAL: SOLO Ver Registro (si es Asistente y está registrado) --%>
        <% 
            if (esAsistente && estaRegistrado) { 
        %>
            <a href="<%= urlConsultaAsistente %>" class="btn btn-success mt-3">
              Ver Detalles de mi Registro
            </a>
        <% 
            }
        %>
        
        <%-- 4. BLOQUE DEL VIDEO (Debajo del enlace de registro) --%>
        <%
            String videoId = null;
            // Lógica para extraer el ID del video (sin cambios)
            if (videoUrl != null && !videoUrl.trim().isEmpty()) {
                if (videoUrl.contains("v=")) {
                    int startIndex = videoUrl.indexOf("v=") + 2;
                    int endIndex = videoUrl.indexOf("&", startIndex);
                    if (endIndex == -1) {
		                videoId = videoUrl.substring(startIndex);
		            } else {
		                videoId = videoUrl.substring(startIndex, endIndex);
		            }                
                }                 
                else if (videoUrl.contains("youtu.be/")) {
                    int startIndex = videoUrl.indexOf("youtu.be/") + 9;
                    videoId = videoUrl.substring(startIndex);
                }
            }
        
            if (videoId != null) {
        %>
            <div class="video-container mt-4">
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
        
        <%-- Mensaje de error (se mantiene) --%>
        <% if (request.getAttribute("error") != null) { %>
            <br/>
            <p style="color:red;" id="error_nombre_repetido"><%= request.getAttribute("error") %></p>
        <% } %>
        
      </div>
    </div>
    
    <%-- Se eliminó el bloque lateral derecho que contenía el Organizador, Tipos de Registro y Patrocinios. --%>
    
  </main>
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>