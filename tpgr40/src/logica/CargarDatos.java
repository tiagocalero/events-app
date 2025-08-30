package logica;

import java.util.Arrays;
import java.util.List;
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
		
		List<String> cats1 = new ArrayList<>(Arrays.asList("Tecnología", "Innovación"));
		boolean ev = e.altaEventoCargaDatos("Conferencia de Tecnología", "Evento sobre innovación tecnológica", "CONFTEC", "10/01/2025", cats1);
		
		List<String> cats2 = new ArrayList<>(Arrays.asList("Innovación", "Literatura"));
		ev = e.altaEventoCargaDatos("Feria del Libro", "Encuentro anual de literatura", "FERLIB", "01/02/2025", cats2);
		
		List<String> cats3 = new ArrayList<>(Arrays.asList("Cultura", "Música"));
		ev = e.altaEventoCargaDatos("Montevideo Rock", "Festival de rock con artistas nacionales e internacionales", "MONROCK", "15/03/2023", cats3);
		
		List<String> cats4 = new ArrayList<>(Arrays.asList("Deporte", "Salud"));
		ev = e.altaEventoCargaDatos("Maratón de Montevideo", "Competencia deportiva anual en la capital", "MARATON", "01/01/2022", cats4);
		
		List<String> cats5 = new ArrayList<>(Arrays.asList("Cultura", "Entretenimiento"));
		ev = e.altaEventoCargaDatos("Montevideo Comics", "Convención de historietas, cine y cultura geek", "COMICS", "10/04/2024", cats5);
		
		List<String> cats6 = new ArrayList<>(Arrays.asList("Agro", "Negocios"));
		ev = e.altaEventoCargaDatos("Expointer Uruguay", "Exposición internacional agropecuaria y ganadera", "EXPOAGRO", "12/12/2024", cats6);
		
		List<String> cats7 = new ArrayList<>(Arrays.asList("Cultura", "Moda"));
		ev = e.altaEventoCargaDatos("Montevideo Fashion Week", "Pasarela de moda uruguaya e internacional", "MFASHION", "20/07/2025", cats7);
		
		
		//Cargar Organizaciones
		ControladorUsuario u = ControladorUsuario.getInstance();
		boolean  org = u.altaOrganizador("miseventos", "MisEventos", "contacto@miseventos.com", "Empresa de organización de eventos.", "https://miseventos.com");
		org = u.altaOrganizador("techcorp", "Corporación Tecnológica", "info@techcorp.com", "Empresa líder en tecnologías de la información.", "");
		org = u.altaOrganizador("imm", "Intendencia de Montevideo", "contacto@imm.gub.uy", "Gobierno departamental de Montevideo.", "https://montevideo.gub.uy");
		org = u.altaOrganizador("udelar", "Universidad de la República", "contacto@udelar.edu.uy", "Universidad pública de Uruguay.", "https://udelar.edu.uy");
		org = u.altaOrganizador("mec", "Ministerio de Educación y Cultura", "mec@mec.gub.uy", "Institución pública promotora de cultura.", "https://mec.gub.uy");
		
		//Cargar Ediciones
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
        LocalDate fechaInicio = LocalDate.parse("20/11/2025", fmt);
        LocalDate fechaFin = LocalDate.parse("22/11/2025", fmt);
        LocalDate fechaAlta = LocalDate.parse("12/03/2025", fmt);
		boolean  ed = e.altaEdicion("Montevideo Rock", "", "Montevideo Rock 2025", "MONROCK25", fechaInicio, fechaFin, fechaAlta, "Montevideo", "Uruguay");
		
		fechaInicio = LocalDate.parse("", fmt);
        fechaFin = LocalDate.parse("", fmt);
        fechaAlta = LocalDate.parse("", fmt);
		ed = e.altaEdicion("", "", "", "", fechaInicio, fechaFin, fechaAlta, "", "");
		
	
	
	
	}
}
