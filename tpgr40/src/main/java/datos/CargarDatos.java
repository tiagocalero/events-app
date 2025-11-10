package main.java.datos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.excepciones.PatrocinioSuperaMontoException;
import main.java.excepciones.YaExistePatrocinioException;
import main.java.logica.ControladorCategoria;
import main.java.logica.ControladorEvento;
import main.java.logica.ControladorInstitucion;
import main.java.logica.ControladorUsuario;
import main.java.logica.TipoNivelPatrocinio;

public class CargarDatos {
	public static void cargaDatos() throws PatrocinioSuperaMontoException, YaExistePatrocinioException, IllegalArgumentException {
		
		//Cargar Categorias
		ControladorCategoria contC = ControladorCategoria.getInstance();
		contC.altaCategoria("Tecnología");
		contC.altaCategoria("Innovación");
		contC.altaCategoria("Literatura");
		contC.altaCategoria("Cultura");
		contC.altaCategoria("Música");
		contC.altaCategoria("Deporte");
		contC.altaCategoria("Salud");
		contC.altaCategoria("Entretenimiento");
		contC.altaCategoria("Agro");
		contC.altaCategoria("Negocios");
		contC.altaCategoria("Moda");
		contC.altaCategoria("Investigación");
		
		//Cargar Instituciones
		ControladorInstitucion contI = ControladorInstitucion.getInstance();
		contI.altaInstitucion("Agencia Nacional de Investigación e Innovación (ANII)", "Fomenta la investigación y la innovación en Uruguay", "https://anii.org.uy");
		contI.altaInstitucion("Facultad de Ingeniería", "Facultad de Ingeniería de la Universidad de la República", "https://www.fing.edu.uy");
		contI.altaInstitucion("ORT Uruguay", "Universidad privada enfocada en tecnología y gestión", "https://ort.edu.uy");
		contI.altaInstitucion("Universidad Católica del Uruguay", "Institución de educación superior privada", "https://ucu.edu.uy");
		contI.altaInstitucion("Antel", "Empresa estatal de telecomunicaciones", "https://antel.com.uy");
		
		//Cargar Eventos
		ControladorEvento contE = ControladorEvento.getInstance();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaAlta;

        fechaAlta = LocalDate.parse("10/01/2025", fmt);
		List<String> cats1 = new ArrayList<>(Arrays.asList("Tecnología", "Innovación"));
		contE.ingresarDatosEvento("Conferencia de Tecnología", "Evento sobre innovación tecnológica", "CONFTEC", fechaAlta, cats1, false);
		contE.setVisitasEvento("Conferencia de Tecnología", 2);

        fechaAlta = LocalDate.parse("01/02/2025", fmt);
		List<String> cats2 = new ArrayList<>(Arrays.asList("Cultura", "Literatura"));
		contE.ingresarDatosEvento("Feria del Libro", "Encuentro anual de literatura", "FERLIB", fechaAlta, cats2, "IMG-EV02.jpeg", false);
		contE.setVisitasEvento("Feria del Libro", 10);

        fechaAlta = LocalDate.parse("15/03/2023", fmt);
		List<String> cats3 = new ArrayList<>(Arrays.asList("Cultura", "Música"));
		contE.ingresarDatosEvento("Montevideo Rock", "Festival de rock con artistas nacionales e internacionales", "MONROCK", fechaAlta, cats3, "IMG-EV03.jpeg", false);
		contE.setVisitasEvento("Montevideo Rock", 25);

        fechaAlta = LocalDate.parse("01/01/2022", fmt);
		List<String> cats4 = new ArrayList<>(Arrays.asList("Deporte", "Salud"));
		contE.ingresarDatosEvento("Maratón de Montevideo", "Competencia deportiva anual en la capital", "MARATON", fechaAlta, cats4, "IMG-EV04.png", false);
		contE.setVisitasEvento("Maratón de Montevideo", 13);

        fechaAlta = LocalDate.parse("10/04/2024", fmt);
		List<String> cats5 = new ArrayList<>(Arrays.asList("Cultura", "Entretenimiento"));
		contE.ingresarDatosEvento("Montevideo Comics", "Convención de historietas, cine y cultura geek", "COMICS", fechaAlta, cats5, "IMG-EV05.png", false);
		contE.setVisitasEvento("Montevideo Comics", 5);

        fechaAlta = LocalDate.parse("12/12/2024", fmt);
		List<String> cats6 = new ArrayList<>(Arrays.asList("Agro", "Negocios"));
		contE.ingresarDatosEvento("Expointer Uruguay", "Exposición internacional agropecuaria y ganadera", "EXPOAGRO", fechaAlta, cats6, "IMG-EV06.png", false);
		contE.setVisitasEvento("Expointer Uruguay", 10);

        fechaAlta = LocalDate.parse("20/07/2025", fmt);
		List<String> cats7 = new ArrayList<>(Arrays.asList("Cultura", "Moda"));
		contE.ingresarDatosEvento("Montevideo Fashion Week", "Pasarela de moda uruguaya e internacional", "MFASHION", fechaAlta, cats7, false);
		contE.setVisitasEvento("Montevideo Fashion Week", 8);		

		fechaAlta = LocalDate.parse("01/01/2025", fmt);
		List<String> cats8 = new ArrayList<>(Arrays.asList("Cultura"));
		// Aviso: El evento se finaliza más adelante para poder crear la edición
		contE.ingresarDatosEvento("Global", "Aventureros en grupo", "GBL", fechaAlta, cats8, false);
		contE.setVisitasEvento("Global", 20);	
		
		//Cargar Organizaciones
		ControladorUsuario contU = ControladorUsuario.getInstance();
		contU.altaOrganizador("miseventos", "MisEventos", "contacto@miseventos.com", "22miseventos", "IMG-US04.jpeg", "Empresa de organización de eventos.", "https://miseventos.com");
		contU.altaOrganizador("techcorp", "Corporación Tecnológica", "info@techcorp.com", "tech25corp", null, "Empresa líder en tecnologías de la información.", "");
		contU.altaOrganizador("imm", "Intendencia de Montevideo", "contacto@imm.gub.uy", "imm2025", "IMG-US06.png", "Gobierno departamental de Montevideo.", "https://montevideo.gub.uy");
		contU.altaOrganizador("udelar", "Universidad de la República", "contacto@udelar.edu.uy", "25udelar", null, "Universidad pública de Uruguay.", "https://udelar.edu.uy");
		contU.altaOrganizador("mec", "Ministerio de Educación y Cultura", "mec@mec.gub.uy", "mec2025ok", "IMG-US11.png", "Institución pública promotora de cultura.", "https://mec.gub.uy");
		
		//Cargar Ediciones
        LocalDate fechaInicio = LocalDate.parse("20/11/2025", fmt);
        LocalDate fechaFin = LocalDate.parse("22/11/2025", fmt);
        fechaAlta = LocalDate.parse("12/03/2025", fmt);
		String video = "https://www.youtube.com/watch?v=YFbRrUX04tU";
		contE.altaEdicion("Montevideo Rock", "imm", "Montevideo Rock 2025", "MONROCK25", fechaInicio, fechaFin, fechaAlta, "Montevideo", "Uruguay", "IMG-EDEV01.jpeg", video);
		contE.confirmarEdicion("Montevideo Rock", "Montevideo Rock 2025");
		
		fechaInicio = LocalDate.parse("14/09/2025", fmt);
        fechaFin = LocalDate.parse("14/09/2025", fmt);
        fechaAlta = LocalDate.parse("05/02/2025", fmt);
		video = "https://www.youtube.com/watch?v=Pg7Jw787MgE";
		contE.altaEdicion("Maratón de Montevideo", "imm", "Maratón de Montevideo 2025", "MARATON25", fechaInicio, fechaFin, fechaAlta, "Montevideo", "Uruguay", "IMG-EDEV02.png", video);
		contE.confirmarEdicion("Maratón de Montevideo", "Maratón de Montevideo 2025");
		
		fechaInicio = LocalDate.parse("14/09/2024", fmt);
        fechaFin = LocalDate.parse("14/09/2024", fmt);
        fechaAlta = LocalDate.parse("21/04/2024", fmt);
		video = "https://www.youtube.com/watch?v=hxDn4EEMank";
        contE.altaEdicion("Maratón de Montevideo", "imm", "Maratón de Montevideo 2024", "MARATON24", fechaInicio, fechaFin, fechaAlta, "Montevideo", "Uruguay", "IMG-EDEV03.jpeg", video);
        contE.confirmarEdicion("Maratón de Montevideo", "Maratón de Montevideo 2024");
        
		fechaInicio = LocalDate.parse("14/09/2022", fmt);
        fechaFin = LocalDate.parse("14/09/2022", fmt);
        fechaAlta = LocalDate.parse("21/05/2022", fmt);
        contE.altaEdicion("Maratón de Montevideo", "imm", "Maratón de Montevideo 2022", "MARATON22", fechaInicio, fechaFin, fechaAlta, "Montevideo", "Uruguay", "IMG-EDEV04.jpeg", "");
        contE.rechazarEdicion("Maratón de Montevideo", "Maratón de Montevideo 2022");
        
		fechaInicio = LocalDate.parse("18/07/2024", fmt);
        fechaFin = LocalDate.parse("21/07/2024", fmt);
        fechaAlta = LocalDate.parse("20/06/2024", fmt);
		video = "https://www.youtube.com/watch?v=4n0itnXxCMg";
		contE.altaEdicion("Montevideo Comics", "miseventos", "Montevideo Comics 2024", "COMICS24", fechaInicio, fechaFin, fechaAlta, "Montevideo", "Uruguay", "IMG-EDEV05.jpeg", video);
		contE.confirmarEdicion("Montevideo Comics", "Montevideo Comics 2024");		
		
        fechaInicio = LocalDate.parse("04/08/2025", fmt);
        fechaFin = LocalDate.parse("06/08/2025", fmt);
        fechaAlta = LocalDate.parse("04/07/2025", fmt);
		video = "https://www.youtube.com/watch?v=jRJt4i7G-SY";
		contE.altaEdicion("Montevideo Comics", "miseventos", "Montevideo Comics 2025", "COMICS25", fechaInicio, fechaFin, fechaAlta, "Montevideo", "Uruguay", "IMG-EDEV06.jpeg", video);
		contE.confirmarEdicion("Montevideo Comics", "Montevideo Comics 2025");
		
		fechaInicio = LocalDate.parse("11/09/2025", fmt);
        fechaFin = LocalDate.parse("17/09/2025", fmt);
        fechaAlta = LocalDate.parse("01/02/2025", fmt);
		video = "https://www.youtube.com/watch?v=NFjb-JujCCY";
		contE.altaEdicion("Expointer Uruguay", "miseventos", "Expointer Uruguay 2025", "EXPOAGRO25", fechaInicio, fechaFin, fechaAlta, "Durazno", "Uruguay", "IMG-EDEV07.jpeg", video);
				
		fechaInicio = LocalDate.parse("06/04/2026", fmt);
        fechaFin = LocalDate.parse("10/04/2026", fmt);
        fechaAlta = LocalDate.parse("01/08/2025", fmt);
		video = "https://www.youtube.com/watch?v=IPukuYb9xWw";
		contE.altaEdicion("Conferencia de Tecnología", "udelar", "Tecnología Punta del Este 2026", "CONFTECH26", fechaInicio, fechaFin, fechaAlta, "Punta del Este", "Uruguay", "IMG-EDEV08.jpeg", video);
		contE.confirmarEdicion("Conferencia de Tecnología", "Tecnología Punta del Este 2026");
		
		fechaInicio = LocalDate.parse("12/12/2025", fmt);
        fechaFin = LocalDate.parse("15/12/2025", fmt);
        fechaAlta = LocalDate.parse("21/08/2025", fmt);
		video = "https://www.youtube.com/watch?v=zNVbgEJfgz8";
		contE.altaEdicion("Conferencia de Tecnología", "techcorp", "Mobile World Congress 2025", "MWC", fechaInicio, fechaFin, fechaAlta, "Barcelona", "España", video);
		contE.confirmarEdicion("Conferencia de Tecnología", "Mobile World Congress 2025");
		
		fechaInicio = LocalDate.parse("13/01/2026", fmt);
        fechaFin = LocalDate.parse("01/02/2026", fmt);
        fechaAlta = LocalDate.parse("04/06/2025", fmt);
		contE.altaEdicion("Conferencia de Tecnología", "techcorp", "Web Summit 2026", "WS26", fechaInicio, fechaFin, fechaAlta, "Lisboa", "Portugal", "");
		contE.confirmarEdicion("Conferencia de Tecnología", "Web Summit 2026");

		fechaInicio = LocalDate.parse("16/02/2026", fmt);
        fechaFin = LocalDate.parse("20/02/2026", fmt);
        fechaAlta = LocalDate.parse("02/10/2025", fmt);
		contE.altaEdicion("Montevideo Fashion Week", "mec", "Montevideo Fashion Week 2026", "MFW26", fechaInicio, fechaFin, fechaAlta, "Nueva York", "Estados Unidos", "IMG-EDEV11.jpeg", "");

		fechaInicio = LocalDate.parse("10/11/2025", fmt);
        fechaFin = LocalDate.parse("30/11/2025", fmt);
        fechaAlta = LocalDate.parse("07/08/2025", fmt);
		video = "https://www.youtube.com/watch?v=cnMa-Sm9H4k";
		contE.altaEdicion("Global", "miseventos", "Descubre la Magia de Machu Picchu", "MAPI25", fechaInicio, fechaFin, fechaAlta, "Cusco", "Perú", "IMG-EDEV12.jpeg", video);
		contE.confirmarEdicion("Global", "Descubre la Magia de Machu Picchu");
		// El evento se puede finalizar recién acá para poder crear la edición
		contE.finalizarEvento("Global");
		
		//Cargar Tipo Registro
		contE.crearTipoRegistro("Montevideo Rock", "Montevideo Rock 2025", "General", "Acceso general a Montevideo Rock (2 días)", 1500, 2000);
		contE.crearTipoRegistro("Montevideo Rock", "Montevideo Rock 2025", "VIP", "Incluye backstage + acceso preferencial", 4000, 200);
		contE.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 42K", "Inscripción a la maratón completa", 1200, 499);
		contE.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 21K", "Inscripción a la media maratón", 800, 700);
		contE.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 10K", "Inscripción a la carrera 10K", 500, 1000);
		contE.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2024", "Corredor 42K", "Inscripción a la maratón completa", 1000, 300);
		contE.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2024", "Corredor 21K", "Inscripción a la media maratón", 500, 500);
		contE.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2022", "Corredor 42K", "Inscripción a la maratón completa", 1100, 450);
		contE.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2022", "Corredor 21K", "Inscripción a la media maratón", 900, 750);
		contE.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 10K", "Inscripción a la carrera 10K", 650, 1400);
		contE.crearTipoRegistro("Montevideo Comics", "Montevideo Comics 2024", "General", "Entrada para los 4 días de Montevideo Comics", 600, 1500);
		contE.crearTipoRegistro("Montevideo Comics", "Montevideo Comics 2024", "Cosplayer", "Entrada especial con acreditación para concurso cosplay", 300, 50);
		contE.crearTipoRegistro("Montevideo Comics", "Montevideo Comics 2025", "General", "Entrada para los 4 días de Montevideo Comics", 800, 1000);
		contE.crearTipoRegistro("Montevideo Comics", "Montevideo Comics 2025", "Cosplayer", "Entrada especial con acreditación para concurso cosplay", 500, 100);
		contE.crearTipoRegistro("Expointer Uruguay", "Expointer Uruguay 2025", "General", "Acceso a la exposición agropecuaria", 300, 5000);
		contE.crearTipoRegistro("Expointer Uruguay", "Expointer Uruguay 2025", "Empresarial", "Acceso para empresas + networking", 2000, 5);
		contE.crearTipoRegistro("Conferencia de Tecnología", "Tecnología Punta del Este 2026", "Full", "Acceso ilimitado + Cena de gala", 1800, 300);
		contE.crearTipoRegistro("Conferencia de Tecnología", "Tecnología Punta del Este 2026", "General", "Acceso general", 1500, 500);
		contE.crearTipoRegistro("Conferencia de Tecnología", "Tecnología Punta del Este 2026", "Estudiante", "Acceso para estudiantes", 1000, 50);
		contE.crearTipoRegistro("Conferencia de Tecnología", "Mobile World Congress 2025", "Full", "Acceso ilimitado + Cena de gala", 750, 550);
		contE.crearTipoRegistro("Conferencia de Tecnología", "Mobile World Congress 2025", "General", "Acceso general", 500, 400);
		contE.crearTipoRegistro("Conferencia de Tecnología", "Mobile World Congress 2025", "Estudiante", "Acceso para estudiantes", 250, 400);
		contE.crearTipoRegistro("Conferencia de Tecnología", "Web Summit 2026", "Full", "Acceso ilimitado + Cena de gala", 900, 30);
		contE.crearTipoRegistro("Conferencia de Tecnología", "Web Summit 2026", "General", "Acceso general", 650, 5);
		contE.crearTipoRegistro("Conferencia de Tecnología", "Web Summit 2026", "Estudiante", "Acceso para estudiantes", 300, 1);
		contE.crearTipoRegistro("Montevideo Fashion Week", "Montevideo Fashion Week 2026", "Full", "Acceso a todos los eventos de la semana", 450, 50);
		contE.crearTipoRegistro("Montevideo Fashion Week", "Montevideo Fashion Week 2026", "Visitante", "Acceso parcial a los eventos de la semana", 150, 25);
		contE.crearTipoRegistro("Global", "Descubre la Magia de Machu Picchu", "plus50", "Viaje para personas con más de 50 años", 250, 10);
		contE.crearTipoRegistro("Global", "Descubre la Magia de Machu Picchu", "Mayores", "Viaje para personas mayores de 18 años", 300, 20);
		
		//Cargar Patrocinios
		contE.crearPatrocinio("TECHUDELAR", TipoNivelPatrocinio.Oro, "Estudiante", 4, 20000, LocalDate.parse("21/08/2025", fmt), "Tecnología Punta del Este 2026", "Facultad de Ingeniería", "Conferencia de Tecnología");
		contE.crearPatrocinio("TECHANII", TipoNivelPatrocinio.Plata, "General", 1, 10000, LocalDate.parse("20/08/2025", fmt), "Tecnología Punta del Este 2026", "Agencia Nacional de Investigación e Innovación (ANII)", "Conferencia de Tecnología");
		contE.crearPatrocinio("CORREANTEL", TipoNivelPatrocinio.Platino, "Corredor 10K", 10, 25000, LocalDate.parse("04/03/2025", fmt), "Maratón de Montevideo 2025", "Antel", "Maratón de Montevideo");
		contE.crearPatrocinio("EXPOCAT", TipoNivelPatrocinio.Bronce, "General", 10, 15000, LocalDate.parse("05/05/2025", fmt), "Expointer Uruguay 2025", "Universidad Católica del Uruguay", "Expointer Uruguay");
		
		//Cargar Asistentes
		contU.altaAsistente("atorres", "Ana", "atorres@gmail.com", "123.torres", "IMG-US01.jpg", "Torres", LocalDate.parse("12/05/1990", fmt), "Facultad de Ingeniería");
		contU.altaAsistente("msilva", "Martin", "martin.silva@fing.edu.uy", "msilva2025", null, "Silva", LocalDate.parse("21/08/1987", fmt), "Facultad de Ingeniería");
		contU.altaAsistente("sofirod", "Sofia", "srodriguez@outlook.com", "srod.abc1", "IMG-US03.jpeg", "Rodriguez", LocalDate.parse("03/02/1995", fmt), "Universidad Católica del Uruguay");
		contU.altaAsistente("vale23", "Valentina", "valentina.costa@mail.com", "valen11c", "IMG-US07.jpeg", "Costa", LocalDate.parse("01/12/1992", fmt));
		contU.altaAsistente("luciag", "Lucía", "lucia.garcia@mail.com", "garcia.22l", "IMG-US08.jpeg", "García", LocalDate.parse("09/11/1993", fmt));
		contU.altaAsistente("andrearod", "Andrea", "andrea.rod@mail.com", "rod77and", "IMG-US09.jpeg", "Rodríguez", LocalDate.parse("10/06/2000", fmt), "Agencia Nacional de Investigación e Innovación (ANII)");
		contU.altaAsistente("AnaG", "Ana", "ana.gomez@hotmail.com", "gomez88a", "IMG-US12.png", "Gómez", LocalDate.parse("15/03/1998", fmt));
		contU.altaAsistente("JaviL", "Javier", "javier.lopez@outlook.com", "jl99lopez", "IMG-US13.jpeg", "López", LocalDate.parse("22/07/1995", fmt));
		contU.altaAsistente("MariR", "María", "maria.rodriguez@gmail.com", "maria55r", "IMG-US14.jpeg", "Rodríguez", LocalDate.parse("10/11/2000", fmt));
		contU.altaAsistente("SofiM", "Sofía", "sofia.martinez@yahoo.com", "smarti99z", "IMG-US15.jpeg", "Martínez", LocalDate.parse("05/02/1997", fmt));
	
		//Cargar Registros
        fechaAlta = LocalDate.parse("14/05/2025", fmt);
		contE.darDeAltaRegistro("sofirod", "Montevideo Rock 2025", "VIP", "Montevideo Rock", fechaAlta, false);
		//contU.registrarAsistencia("sofirod", "Montevideo Rock 2025");

        fechaAlta = LocalDate.parse("30/07/2024", fmt);
		contE.darDeAltaRegistro("sofirod", "Maratón de Montevideo 2024", "Corredor 21K", "Maratón de Montevideo", fechaAlta, false);
		
        fechaAlta = LocalDate.parse("21/08/2025", fmt);
		contE.darDeAltaRegistro("andrearod", "Web Summit 2026", "Estudiante", "Conferencia de Tecnología", fechaAlta, false);
		
        fechaAlta = LocalDate.parse("03/03/2025", fmt);
		contE.darDeAltaRegistro("sofirod", "Maratón de Montevideo 2025", "Corredor 42K", "Maratón de Montevideo", fechaAlta, false);
		contU.registrarAsistencia("sofirod", "Maratón de Montevideo 2025");
		
        fechaAlta = LocalDate.parse("22/08/2025", fmt);
		contE.darDeAltaRegistro("vale23", "Mobile World Congress 2025", "Full", "Conferencia de Tecnología", fechaAlta, false);
		
        fechaAlta = LocalDate.parse("09/04/2025", fmt);
		contE.darDeAltaRegistro("AnaG", "Maratón de Montevideo 2025", "Corredor 10K", "Maratón de Montevideo", fechaAlta, false);
		contU.registrarAsistencia("AnaG", "Maratón de Montevideo 2025");
		
        fechaAlta = LocalDate.parse("10/04/2025", fmt);
		contE.darDeAltaRegistro("JaviL", "Maratón de Montevideo 2025", "Corredor 21K", "Maratón de Montevideo", fechaAlta, false);
		
        fechaAlta = LocalDate.parse("03/08/2025", fmt);
		contE.darDeAltaRegistro("MariR", "Montevideo Comics 2025", "Cosplayer", "Montevideo Comics", fechaAlta, false);
		
        fechaAlta = LocalDate.parse("16/07/2024", fmt);
		contE.darDeAltaRegistro("SofiM", "Montevideo Comics 2024", "General", "Montevideo Comics", fechaAlta, false);
		contU.registrarAsistencia("SofiM", "Montevideo Comics 2024");
		//
		fechaAlta = LocalDate.parse("01/10/2025", fmt);
		contE.patrocinioEdicion("TECHUDELAR", "Tecnología Punta del Este 2026", "Conferencia de Tecnología", "Estudiante", "Facultad de Ingeniería");
		contE.darDeAltaRegistro("msilva", "Tecnología Punta del Este 2026", "Estudiante", "Conferencia de Tecnología", fechaAlta, true);
		
		fechaAlta = LocalDate.parse("06/10/2025", fmt);
		contE.patrocinioEdicion("TECHANII", "Tecnología Punta del Este 2026", "Conferencia de Tecnología", "General", "Agencia Nacional de Investigación e Innovación (ANII)");
		contE.darDeAltaRegistro("andrearod", "Tecnología Punta del Este 2026", "General", "Conferencia de Tecnología", fechaAlta, true);
		
		fechaAlta = LocalDate.parse("10/10/2025", fmt);
		contE.darDeAltaRegistro("MariR", "Tecnología Punta del Este 2026", "General", "Conferencia de Tecnología", fechaAlta, false);
		//
		fechaAlta = LocalDate.parse("07/11/2025", fmt);
		contE.darDeAltaRegistro("atorres", "Descubre la Magia de Machu Picchu", "Mayores", "Global", fechaAlta, false);
		contU.registrarAsistencia("atorres", "Descubre la Magia de Machu Picchu");
		
		fechaAlta = LocalDate.parse("10/08/2025", fmt);
		contE.darDeAltaRegistro("msilva", "Descubre la Magia de Machu Picchu", "Mayores", "Global", fechaAlta, false);

		fechaAlta = LocalDate.parse("30/09/2025", fmt);
		contE.darDeAltaRegistro("AnaG", "Descubre la Magia de Machu Picchu", "plus50", "Global", fechaAlta, false);
		contU.registrarAsistencia("AnaG", "Descubre la Magia de Machu Picchu");

		//Carga Seguidos y Seguidores
		contU.seguirUsuario("atorres", "sofirod");
		contU.seguirUsuario("atorres", "imm");
		contU.seguirUsuario("sofirod", "imm");
		contU.seguirUsuario("sofirod", "atorres");
		contU.seguirUsuario("udelar", "techcorp");
		contU.seguirUsuario("udelar", "mec");
		contU.seguirUsuario("techcorp", "sofirod");

	}
}
