package datos;

import java.util.Arrays;
import java.util.List;

import excepciones.PatrocinioSuperaMontoException;
import excepciones.YaExistePatrocinioException;
import logica.ControladorCategoria;
import logica.ControladorEvento;
import logica.ControladorInstitucion;
import logica.ControladorUsuario;
import logica.DTPatrocinio;
import logica.TipoNivelPatrocinio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CargarDatos {
	public static void cargaDatos() {
		
		//Cargar Categorias
		ControladorCategoria c = ControladorCategoria.getInstance();
		boolean cat = c.altaCategoria("Tecnología");
		cat = c.altaCategoria("Innovación");
		cat = c.altaCategoria("Literatura");
		cat = c.altaCategoria("Cultura");
		cat = c.altaCategoria("Música");
		cat = c.altaCategoria("Deporte");
		cat = c.altaCategoria("Salud");
		cat = c.altaCategoria("Entretenimiento");
		cat = c.altaCategoria("Agro");
		cat = c.altaCategoria("Negocios");
		cat = c.altaCategoria("Moda");
		cat = c.altaCategoria("Investigación");
		
		//Cargar Instituciones
		ControladorInstitucion i = ControladorInstitucion.getInstance();
		boolean inst = i.altaInstitucion("Agencia Nacional de Investigación e Innovación (ANII)", "Fomenta la investigación y la innovación en Uruguay", "https://anii.org.uy");
		inst = i.altaInstitucion("Facultad de Ingeniería", "Facultad de Ingeniería de la Universidad de la República", "https://www.fing.edu.uy");
		inst = i.altaInstitucion("ORT Uruguay", "Universidad privada enfocada en tecnología y gestión", "https://ort.edu.uy");
		inst = i.altaInstitucion("Universidad Católica del Uruguay", "Institución de educación superior privada", "https://ucu.edu.uy");
		inst = i.altaInstitucion("Antel", "Empresa estatal de telecomunicaciones", "https://antel.com.uy");
		
		//Cargar Eventos
		ControladorEvento e = ControladorEvento.getInstance();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaAlta;

        fechaAlta = LocalDate.parse("10/01/2025", fmt);
		List<String> cats1 = new ArrayList<>(Arrays.asList("Tecnología", "Innovación"));
		boolean ev = e.ingresarDatosEvento("Conferencia de Tecnología", "Evento sobre innovación tecnológica", "CONFTEC", fechaAlta, cats1);

        fechaAlta = LocalDate.parse("01/02/2025", fmt);
		List<String> cats2 = new ArrayList<>(Arrays.asList("Innovación", "Literatura"));
		ev = e.ingresarDatosEvento("Feria del Libro", "Encuentro anual de literatura", "FERLIB", fechaAlta, cats2, "IMG-EV02.jpeg");

        fechaAlta = LocalDate.parse("15/03/2023", fmt);
		List<String> cats3 = new ArrayList<>(Arrays.asList("Cultura", "Música"));
		ev = e.ingresarDatosEvento("Montevideo Rock", "Festival de rock con artistas nacionales e internacionales", "MONROCK", fechaAlta, cats3, "IMG-EV03.jpeg");

        fechaAlta = LocalDate.parse("01/01/2022", fmt);
		List<String> cats4 = new ArrayList<>(Arrays.asList("Deporte", "Salud"));
		ev = e.ingresarDatosEvento("Maratón de Montevideo", "Competencia deportiva anual en la capital", "MARATON", fechaAlta, cats4, "IMG-EV04.png");

        fechaAlta = LocalDate.parse("10/04/2024", fmt);
		List<String> cats5 = new ArrayList<>(Arrays.asList("Cultura", "Entretenimiento"));
		ev = e.ingresarDatosEvento("Montevideo Comics", "Convención de historietas, cine y cultura geek", "COMICS", fechaAlta, cats5, "IMG-EV05.png");

        fechaAlta = LocalDate.parse("12/12/2024", fmt);
		List<String> cats6 = new ArrayList<>(Arrays.asList("Agro", "Negocios"));
		ev = e.ingresarDatosEvento("Expointer Uruguay", "Exposición internacional agropecuaria y ganadera", "EXPOAGRO", fechaAlta, cats6, "IMG-EV06.png");

        fechaAlta = LocalDate.parse("20/07/2025", fmt);
		List<String> cats7 = new ArrayList<>(Arrays.asList("Cultura", "Moda"));
		ev = e.ingresarDatosEvento("Montevideo Fashion Week", "Pasarela de moda uruguaya e internacional", "MFASHION", fechaAlta, cats7);
		
		
		//Cargar Organizaciones
		ControladorUsuario u = ControladorUsuario.getInstance();
		boolean  org = u.altaOrganizador("miseventos", "MisEventos", "contacto@miseventos.com", "Empresa de organización de eventos.", "https://miseventos.com");
		org = u.altaOrganizador("techcorp", "Corporación Tecnológica", "info@techcorp.com", "Empresa líder en tecnologías de la información.", "");
		org = u.altaOrganizador("imm", "Intendencia de Montevideo", "contacto@imm.gub.uy", "Gobierno departamental de Montevideo.", "https://montevideo.gub.uy");
		org = u.altaOrganizador("udelar", "Universidad de la República", "contacto@udelar.edu.uy", "Universidad pública de Uruguay.", "https://udelar.edu.uy");
		org = u.altaOrganizador("mec", "Ministerio de Educación y Cultura", "mec@mec.gub.uy", "Institución pública promotora de cultura.", "https://mec.gub.uy");
		
		//Cargar Ediciones
        LocalDate fechaInicio = LocalDate.parse("20/11/2025", fmt);
        LocalDate fechaFin = LocalDate.parse("22/11/2025", fmt);
        fechaAlta = LocalDate.parse("12/03/2025", fmt);
		boolean  ed = e.altaEdicion("Montevideo Rock", "imm", "Montevideo Rock 2025", "MONROCK25", fechaInicio, fechaFin, fechaAlta, "Montevideo", "Uruguay");
		
		fechaInicio = LocalDate.parse("14/09/2025", fmt);
        fechaFin = LocalDate.parse("14/09/2025", fmt);
        fechaAlta = LocalDate.parse("05/02/2025", fmt);
		ed = e.altaEdicion("Maratón de Montevideo", "imm", "Maratón de Montevideo 2025", "MARATON25", fechaInicio, fechaFin, fechaAlta, "Montevideo", "Uruguay");
		
		fechaInicio = LocalDate.parse("14/09/2024", fmt);
        fechaFin = LocalDate.parse("14/09/2024", fmt);
        fechaAlta = LocalDate.parse("21/04/2024", fmt);
        ed = e.altaEdicion("Maratón de Montevideo", "imm", "Maratón de Montevideo 2024", "MARATON24", fechaInicio, fechaFin, fechaAlta, "Montevideo", "Uruguay");
		
		fechaInicio = LocalDate.parse("14/09/2022", fmt);
        fechaFin = LocalDate.parse("14/09/2022", fmt);
        fechaAlta = LocalDate.parse("21/05/2022", fmt);
        ed = e.altaEdicion("Maratón de Montevideo", "imm", "Maratón de Montevideo 2022", "MARATON22", fechaInicio, fechaFin, fechaAlta, "Montevideo", "Uruguay");
		
		fechaInicio = LocalDate.parse("18/07/2024", fmt);
        fechaFin = LocalDate.parse("21/07/2024", fmt);
        fechaAlta = LocalDate.parse("20/06/2024", fmt);
		ed = e.altaEdicion("Montevideo Comics", "miseventos", "Montevideo Comics 2024", "COMICS24", fechaInicio, fechaFin, fechaAlta, "Montevideo", "Uruguay");
		
        fechaInicio = LocalDate.parse("04/08/2025", fmt);
        fechaFin = LocalDate.parse("06/08/2025", fmt);
        fechaAlta = LocalDate.parse("04/07/2025", fmt);
		ed = e.altaEdicion("Montevideo Comics", "miseventos", "Montevideo Comics 2025", "COMICS25", fechaInicio, fechaFin, fechaAlta, "Montevideo", "Uruguay");
		
		fechaInicio = LocalDate.parse("11/09/2025", fmt);
        fechaFin = LocalDate.parse("17/09/2025", fmt);
        fechaAlta = LocalDate.parse("01/02/2025", fmt);
		ed = e.altaEdicion("Expointer Uruguay", "miseventos", "Expointer Uruguay 2025", "EXPOAGRO25", fechaInicio, fechaFin, fechaAlta, "Durazno", "Uruguay");
		
		fechaInicio = LocalDate.parse("06/04/2026", fmt);
        fechaFin = LocalDate.parse("10/04/2026", fmt);
        fechaAlta = LocalDate.parse("01/08/2025", fmt);
		ed = e.altaEdicion("Conferencia de Tecnología", "udelar", "Tecnología Punta del Este 2026", "CONFTECH26", fechaInicio, fechaFin, fechaAlta, "Punta del Este", "Uruguay");
		
		fechaInicio = LocalDate.parse("12/12/2025", fmt);
        fechaFin = LocalDate.parse("15/12/2025", fmt);
        fechaAlta = LocalDate.parse("21/08/2025", fmt);
		ed = e.altaEdicion("Conferencia de Tecnología", "techcorp", "Mobile World Congress 2025", "MWC", fechaInicio, fechaFin, fechaAlta, "Barcelona", "España");
		
		fechaInicio = LocalDate.parse("13/01/2026", fmt);
        fechaFin = LocalDate.parse("01/02/2026", fmt);
        fechaAlta = LocalDate.parse("04/06/2025", fmt);
		ed = e.altaEdicion("Conferencia de Tecnología", "techcorp", "Web Summit 2026", "WS26", fechaInicio, fechaFin, fechaAlta, "Lisboa", "Portugal");
		
		//Cargar Tipo Registro
		boolean tp = e.crearTipoRegistro("Montevideo Rock", "Montevideo Rock 2025", "General", "Acceso general a Montevideo Rock (2 días)", 1500, 2000);
		tp = e.crearTipoRegistro("Montevideo Rock", "Montevideo Rock 2025", "VIP", "Incluye backstage + acceso preferencial", 4000, 200);
		tp = e.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 42K", "Inscripción a la maratón completa", 1200, 499);
		tp = e.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 21K", "Inscripción a la media maratón", 800, 700);
		tp = e.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 10K", "Inscripción a la carrera 10K", 500, 1000);
		tp = e.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2024", "Corredor 42K", "Inscripción a la maratón completa", 1000, 300);
		tp = e.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2024", "Corredor 21K", "Inscripción a la media maratón", 500, 500);
		tp = e.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2022", "Corredor 42K", "Inscripción a la maratón completa", 1100, 450);
		tp = e.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2022", "Corredor 21K", "Inscripción a la media maratón", 900, 750);
		tp = e.crearTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 10K", "Inscripción a la carrera 10K", 650, 1400);
		tp = e.crearTipoRegistro("Montevideo Comics", "Montevideo Comics 2024", "General", "Entrada para los 4 días de Montevideo Comics", 600, 1500);
		tp = e.crearTipoRegistro("Montevideo Comics", "Montevideo Comics 2024", "Cosplayer", "Entrada especial con acreditación para concurso cosplay", 300, 50);
		tp = e.crearTipoRegistro("Montevideo Comics", "Montevideo Comics 2025", "General", "Entrada para los 4 días de Montevideo Comics", 800, 1000);
		tp = e.crearTipoRegistro("Montevideo Comics", "Montevideo Comics 2025", "Cosplayer", "Entrada especial con acreditación para concurso cosplay", 500, 100);
		tp = e.crearTipoRegistro("Expointer Uruguay", "Expointer Uruguay 2025", "General", "Acceso a la exposición agropecuaria", 300, 5000);
		tp = e.crearTipoRegistro("Expointer Uruguay", "Expointer Uruguay 2025", "Empresarial", "Acceso para empresas + networking", 2000, 5);
		tp = e.crearTipoRegistro("Conferencia de Tecnología", "Tecnología Punta del Este 2026", "Full", "Acceso ilimitado + Cena de gala", 1800, 300);
		tp = e.crearTipoRegistro("Conferencia de Tecnología", "Tecnología Punta del Este 2026", "General", "Acceso general", 1500, 500);
		tp = e.crearTipoRegistro("Conferencia de Tecnología", "Tecnología Punta del Este 2026", "Estudiante", "Acceso para estudiantes", 1000, 50);
		tp = e.crearTipoRegistro("Conferencia de Tecnología", "Mobile World Congress 2025", "Full", "Acceso ilimitado + Cena de gala", 750, 550);
		tp = e.crearTipoRegistro("Conferencia de Tecnología", "Mobile World Congress 2025", "General", "Acceso general", 500, 400);
		tp = e.crearTipoRegistro("Conferencia de Tecnología", "Mobile World Congress 2025", "Estudiante", "Acceso para estudiantes", 250, 400);
		tp = e.crearTipoRegistro("Conferencia de Tecnología", "Web Summit 2026", "Full", "Acceso ilimitado + Cena de gala", 900, 30);
		tp = e.crearTipoRegistro("Conferencia de Tecnología", "Web Summit 2026", "General", "Acceso general", 650, 5);
		tp = e.crearTipoRegistro("Conferencia de Tecnología", "Web Summit 2026", "Estudiante", "Acceso para estudiantes", 300, 1);
		
		//Cargar Patrocinios
		// El try es necesario para que no tire que hay errores sin manejar
		try {
			DTPatrocinio dtp1 = new DTPatrocinio("TECHFING", TipoNivelPatrocinio.Oro, 20000, "Estudiante", 4, LocalDate.parse("21/08/2025", fmt), "Facultad de Ingeniería");
			e.crearPatrocinio(dtp1, "Tecnología Punta del Este 2026", "Facultad de Ingeniería", "Conferencia de Tecnología");
			DTPatrocinio dtp2 = new DTPatrocinio("TECHANII", TipoNivelPatrocinio.Plata, 10000, "General", 1, LocalDate.parse("20/08/2025", fmt), "Agencia Nacional de Investigación e Innovación (ANII)");
			e.crearPatrocinio(dtp2, "Tecnología Punta del Este 2026", "Agencia Nacional de Investigación e Innovación (ANII)", "Conferencia de Tecnología");
			DTPatrocinio dtp3 = new DTPatrocinio("CORREANTEL", TipoNivelPatrocinio.Platino, 25000, "Corredor 10K", 10, LocalDate.parse("04/03/2025", fmt), "Antel");
			e.crearPatrocinio(dtp3, "Maratón de Montevideo 2025", "Antel", "Maratón de Montevideo");
			DTPatrocinio dtp4 = new DTPatrocinio("EXPOCAT", TipoNivelPatrocinio.Bronce, 15000, "General", 10, LocalDate.parse("05/05/2025", fmt), "Universidad Católica del Uruguay");
			e.crearPatrocinio(dtp4, "Expointer Uruguay 2025", "Universidad Católica del Uruguay", "Expointer Uruguay");
		} catch (Exception e1) {}
		
		//Cargar Asistentes
		boolean as = u.altaAsistente("atorres", "Ana", "atorres@gmail.com", "Torres", LocalDate.parse("12/05/1990", fmt), "Facultad de Ingenierı́a");
		as = u.altaAsistente("msilva", "Martin", "martin.silva@fing.edu.uy", "Silva", LocalDate.parse("21/08/1987", fmt), "Facultad de Ingenierı́a");
		as = u.altaAsistente("sofirod", "Sofia", "srodriguez@outlook.com", "Rodriguez", LocalDate.parse("03/02/1995", fmt), "Universidad Católica del Uruguay");
		as = u.altaAsistente("vale23", "Valentina", "valentina.costa@mail.com", "Costa", LocalDate.parse("01/12/1992", fmt));
		as = u.altaAsistente("luciag", "Lucía", "lucia.garcia@mail.com", "García", LocalDate.parse("09/11/1993", fmt));
		as = u.altaAsistente("andrearod", "Andrea", "andrea.rod@mail.com", "Rodríguez", LocalDate.parse("10/06/2000", fmt), "Agencia Nacional de Investigación e Innovación (ANII)");
		as = u.altaAsistente("AnaG", "Ana", "ana.gomez@hotmail.com", "Gómez", LocalDate.parse("15/03/1998", fmt));
		as = u.altaAsistente("JaviL", "Javier", "javier.lopez@outlook.com", "López", LocalDate.parse("22/07/1995", fmt));
		as = u.altaAsistente("MariR", "María", "maria.rodriguez@gmail.com", "Rodríguez", LocalDate.parse("10/11/2000", fmt));
		as = u.altaAsistente("SofiM", "Sofía", "sofia.martinez@yahoo.com", "Martínez", LocalDate.parse("05/02/1997", fmt));
	
		//Cargar Registros
        fechaAlta = LocalDate.parse("14/05/2025", fmt);
		e.darDeAltaRegistro("sofirod", "Montevideo Rock 2025", "VIP", "Montevideo Rock", fechaAlta);
        fechaAlta = LocalDate.parse("30/07/2024", fmt);
		e.darDeAltaRegistro("sofirod", "Maratón de Montevideo 2024", "Corredor 21K", "Maratón de Montevideo", fechaAlta);
        fechaAlta = LocalDate.parse("21/08/2025", fmt);
		e.darDeAltaRegistro("andrearod", "Web Summit 2026", "Estudiante", "Conferencia de Tecnología", fechaAlta);
        fechaAlta = LocalDate.parse("03/03/2025", fmt);
		e.darDeAltaRegistro("sofirod", "Maratón de Montevideo 2025", "Corredor 42K", "Maratón de Montevideo", fechaAlta);
        fechaAlta = LocalDate.parse("22/08/2025", fmt);
		e.darDeAltaRegistro("vale23", "Mobile World Congress 2025", "Full", "Conferencia de Tecnología", fechaAlta);
        fechaAlta = LocalDate.parse("09/04/2025", fmt);
		e.darDeAltaRegistro("AnaG", "Maratón de Montevideo 2025", "Corredor 10K", "Maratón de Montevideo", fechaAlta);
        fechaAlta = LocalDate.parse("10/04/2025", fmt);
		e.darDeAltaRegistro("JaviL", "Maratón de Montevideo 2025", "Corredor 21K", "Maratón de Montevideo", fechaAlta);
        fechaAlta = LocalDate.parse("03/08/2025", fmt);
		e.darDeAltaRegistro("MariR", "Montevideo Comics 2025", "Cosplayer", "Montevideo Comics", fechaAlta);
        fechaAlta = LocalDate.parse("16/07/2024", fmt);
		e.darDeAltaRegistro("SofiM", "Montevideo Comics 2024", "General", "Montevideo Comics", fechaAlta);
		
	
	}
}
