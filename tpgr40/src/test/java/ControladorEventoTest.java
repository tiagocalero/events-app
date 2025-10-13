package test.java;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import excepciones.PatrocinioSuperaMontoException;
import excepciones.YaExistePatrocinioException;
import logica.ControladorCategoria;
import logica.ControladorEvento;
import logica.ControladorInstitucion;
import logica.ControladorUsuario;
import logica.DTEdicion;
import logica.DTEvento;
import logica.DTPatrocinio;
import logica.DTTRegistro;
import logica.ManejadorCategoria;
import logica.ManejadorEvento;
import logica.ManejadorInstitucion;
import logica.ManejadorUsuario;
import logica.TipoNivelPatrocinio;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControladorEventoTest {
	private static ControladorEvento c;
	private static ControladorUsuario cu;
	private static LocalDate fActual;
	
	// Se ejecuta antes que todos los tests
	@BeforeAll
	static void setup() {
		c = ControladorEvento.getInstance();
		
		// Crear categorías de prueba
		ControladorCategoria cg = ControladorCategoria.getInstance();
		cg.altaCategoria("c1");
		cg.altaCategoria("c2");
		cg.altaCategoria("c3");
		
		// Crear organizador de prueba
		ControladorUsuario cu = ControladorUsuario.getInstance();
		cu.altaOrganizador("org", "Organizador 1", "ads", "cont", null, "organiza", "http://");

		// Crear institución de prueba
		ControladorInstitucion ci = ControladorInstitucion.getInstance();
		ci.altaInstitucion("Institución 1", "inst", "http://");
		ci.altaInstitucion("Institución 2", "inst", "http://");
		
		// Guardar la fecha actual
		fActual = LocalDate.now();
	}

	// Se ejecuta después que todos los tests
	@AfterAll
	static void borrado() {
		ManejadorCategoria.borrarInstance();
		ManejadorUsuario.getInstance().liberarMemoria();
		ManejadorInstitucion.borrarInstance();
		ManejadorEvento.borrarInstance();
	}
	
	// Probar crear eventos con categorías que ya existen
	@Test @Order(1)
	void testCrearEventoConCategoriaExistente() {
		assertEquals(true, c.ingresarDatosEvento("Evento 1", "descripcion1", "e1", fActual, List.of("c1", "c2", "c3")));
		
		DTEvento dte = c.informacionEvento("Evento 1");
		// Tomar el dt y asegurarse que cada campo sea correcto
		assertEquals("Evento 1", dte.getNombre());
		assertEquals("descripcion1", dte.getDescripcion());
		assertEquals("e1", dte.getSigla());
		assertEquals(Set.of("c1", "c2", "c3"), new HashSet<>(dte.getCategorias()));
		
		assertEquals(Set.of("Evento 1"), c.listarDTEventosCategoria("c1")
											.stream()
										    .map(DTEvento::getNombre)
										    .collect(Collectors.toSet()));
	}

	// Probar crear eventos con categorías que no existen
	@Test @Order(2)
	void testCrearEventoConCategoriaNoExistente() {
		assertEquals(true, c.ingresarDatosEvento("Evento 2", "descripcion2", "e2", fActual, List.of("c2", "c4")));
		
		DTEvento dte = c.informacionEvento("Evento 2");
		assertEquals("Evento 2", dte.getNombre());
		assertEquals("descripcion2", dte.getDescripcion());
		assertEquals("e2", dte.getSigla());
		// Chequear que se cree la categoría
		assertEquals(List.of("c2", "c4"), dte.getCategorias());

		assertEquals(Set.of("Evento 1", "Evento 2"), c.listarDTEventosCategoria("c2")
											.stream()
										    .map(DTEvento::getNombre)
										    .collect(Collectors.toSet()));
	}

	// Probar crear un evento con un nombre que ya existe
	@Test @Order(3)
	void testCrearEventoExistente() {
		assertEquals(false, c.ingresarDatosEvento("Evento 1", "descripcion2", "e2", fActual, List.of("c4")));

		// Chequear que no se modifique el evento existente
		DTEvento dte = c.informacionEvento("Evento 1");
		assertEquals("Evento 1", dte.getNombre());
		assertEquals("descripcion1", dte.getDescripcion());
		assertEquals("e1", dte.getSigla());
		assertEquals(Set.of("c1", "c2", "c3"), new HashSet<>(dte.getCategorias()));
	}

	// Probar listar eventos del sistema
	@Test @Order(4)
	void testListarEventos() {
		assertEquals(Set.of("Evento 1", "Evento 2"), c.listarEventos().stream().collect(Collectors.toSet()));
	}

	// Probar crear ediciones para un evento, y chequear que se listen en el evento
	@Test @Order(5)
	void testCrearEdicionEvento() {
		LocalDate f = LocalDate.now();
		
		assertEquals(true, c.altaEdicion("Evento 1", "org", "Edición 1", "ed1", f, f, f, "Montevideo", "Uruguay"));
		assertEquals(List.of("Edición 1"), c.listarEdiciones("Evento 1"));
		assertEquals(true, c.altaEdicion("Evento 1", "org", "Edición 2", "ed2", f, f, f, "Salto", "Uruguay"));
		assertEquals(Set.of("Edición 1", "Edición 2"), new HashSet<>(c.listarEdiciones("Evento 1")));
	}
	
	@Test @Order(6)
	void testListarConfirmarEdiciones() {
		assertEquals(Set.of("Edición 1", "Edición 2"), new HashSet<>(c.listarEdicionesIngresadas("Evento 1")));
		assertEquals(Set.of(), new HashSet<>(c.listarEdicionesAceptadas("Evento 1")));
		c.confirmarEdicion("Evento 1", "Edición 1");
		assertEquals(Set.of("Edición 2"), new HashSet<>(c.listarEdicionesIngresadas("Evento 1")));
		assertEquals(Set.of("Edición 1"), new HashSet<>(c.listarEdicionesAceptadas("Evento 1")));
		c.rechazarEdicion("Evento 1", "Edición 2");
		assertEquals(Set.of(), new HashSet<>(c.listarEdicionesIngresadas("Evento 1")));
		assertEquals(Set.of("Edición 1"), new HashSet<>(c.listarEdicionesAceptadas("Evento 1")));
		c.confirmarEdicion("Evento 1", "Edición 2");
		assertEquals(Set.of(), new HashSet<>(c.listarEdicionesIngresadas("Evento 1")));
		assertEquals(Set.of("Edición 1", "Edición 2"), new HashSet<>(c.listarEdicionesAceptadas("Evento 1")));
	}

	// Probar crear ediciones con un mismo nombre para un evento
	@Test @Order(6)
	void testCrearEdicionExistenteEvento() {
		LocalDate f = LocalDate.now();
		
		assertEquals(false, c.altaEdicion("Evento 1", "org", "Edición 1", "dsa", f, f, f, "Montevideo", "Uruguay"));
		assertEquals(false, c.altaEdicion("Evento 2", "org", "Edición 1", "assd", f, f, f, "Montevideo", "Uruguay"));
	}
	
	
	@Test @Order(7)
	//testeo obtener con una que existe
	void testObtenerEdicionExistente() {
		
		DTEdicion e = c.obtenerEdicion("Edición 1", "Evento 1");
		LocalDate f = LocalDate.now();
		assertEquals("Edición 1", e.getNombre());
		assertEquals("ed1", e.getSigla());
		assertEquals("org", e.getOrganizador());
		assertEquals(f, e.getFechaAlta());
		assertEquals(f, e.getFechaFin());
		assertEquals(f, e.getFechaInicio());
		assertEquals("Evento 1", e.getEvento());
		assertEquals("Montevideo", e.getCiudad());
		assertEquals("Uruguay", e.getPais());

	}
	
	@Test @Order(8)
	void testCrearTipoRegistro() {
		c.crearTipoRegistro("Evento 1", "Edición 1", "tp1", "desc1", 100, 1);
	}
	
	@Test @Order(8)
	void testCrearTipoRegistroValoresInvalidos() {
		assertThrows(IllegalArgumentException.class, () -> {
			c.crearTipoRegistro("Evento 1", "Edición 1", "tp10", "desc1", -1, 0);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			c.crearTipoRegistro("Evento 1", "Edición 1", "tp10", "desc1", -1, -1);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			c.crearTipoRegistro("Evento 1", "Edición 1", "tp10", "desc1", 0, -1);
		});
	}

	// Probar crear TipoRegistro con nombre ya existente para una misma edicion y evento.
	// Probar crear TipoRegistro con nombre ya existente, pero para distinta edicion/evento.
	@Test @Order(9)
	void testCrearTipoRegistroExistente() {
		assertEquals(false, c.crearTipoRegistro("Evento 1", "Edición 1", "tp1", "desc2", 101, 12));
		
		// El nombre ya existe, pero para otra edición
		assertEquals(true, c.crearTipoRegistro("Evento 1", "Edición 2", "tp1", "desc2", 101, 12));
	}

	// Probar crear patrocinios con casos borde de costos
	@Test @Order(10)
	void testCrearPatrocinio() {
		DTTRegistro tp = c.seleccionarTipoRegistro("tp1", "Evento 1", "Edición 1");
		float cantRegistrosOtorgados = 10;
		float costo = tp.getCosto();
		
		assertThrows(PatrocinioSuperaMontoException.class, () -> {
			// Caso borde: El costo de los registros supera el 20% del monto
			DTPatrocinio dtp = new DTPatrocinio(
				"pat2",
				TipoNivelPatrocinio.Plata,
				cantRegistrosOtorgados * tp.getCosto() * 4.9f,
				"tp1",
				(int) cantRegistrosOtorgados,
				LocalDate.now(),
				"Institución 1",
				null
			);
			c.crearPatrocinio(dtp, "Edición 1", "Institución 1", "Evento 1");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			// Caso borde: costo negativo
			DTPatrocinio dtp = new DTPatrocinio(
				"pat2",
				TipoNivelPatrocinio.Plata,
				-1,
				"tp1",
				0,
				LocalDate.now(),
				"Institución 1",
				null
			);
			c.crearPatrocinio(dtp, "Edición 1", "Institución 1", "Evento 1");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			// Caso borde: cantRegistros negativo
			DTPatrocinio dtp = new DTPatrocinio(
				"pat2",
				TipoNivelPatrocinio.Plata,
				100000,
				"tp1",
				-1,
				LocalDate.now(),
				"Institución 1",
				null
			);
			c.crearPatrocinio(dtp, "Edición 1", "Institución 1", "Evento 1");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			// Caso borde: cantRegistros y cupos negativos
			DTPatrocinio dtp = new DTPatrocinio(
				"pat2",
				TipoNivelPatrocinio.Plata,
				-100000000,
				"tp1",
				-1,
				LocalDate.now(),
				"Institución 1",
				null
			);
			c.crearPatrocinio(dtp, "Edición 1", "Institución 1", "Evento 1");
		});
		
		assertDoesNotThrow(() -> {
			DTPatrocinio dtp = new DTPatrocinio(
				"pat1",
				TipoNivelPatrocinio.Plata,
				cantRegistrosOtorgados * tp.getCosto() * 5.1f,
				"tp1",
				(int) cantRegistrosOtorgados,
				LocalDate.now(),
				"Institución 1",
				null
			);
			c.crearPatrocinio(dtp, "Edición 1", "Institución 1", "Evento 1");
	    });
		
		assertEquals(List.of("Institución 1"), c.listarInstituciones("Evento 1", "Edición 1"));
		
		assertDoesNotThrow(() -> {
			DTPatrocinio dtp = new DTPatrocinio(
				"pat2",
				TipoNivelPatrocinio.Plata,
				1 * tp.getCosto() * 10f,
				"tp1",
				1,
				LocalDate.now(),
				"Institución 2",
				null
			);
			c.crearPatrocinio(dtp, "Edición 1", "Institución 2", "Evento 1");
	    });
		
		assertThrows(YaExistePatrocinioException.class, () -> {
			// Caso borde: Ya existe patrocinio para la institución
			DTPatrocinio dtp = new DTPatrocinio(
				"pat2",
				TipoNivelPatrocinio.Plata,
				cantRegistrosOtorgados * tp.getCosto() * 5.1f,
				"tp1",
				(int) cantRegistrosOtorgados,
				LocalDate.now(),
				"Institución 1",
				null
			);
			c.crearPatrocinio(dtp, "Edición 1", "Institución 1", "Evento 1");
		});
	}
	
	@Test @Order(15)
	void testConsultaPatrocinio() {
		DTPatrocinio dtp = c.consultaPatrocinioInstitucion("Evento 1", "Edición 1", "Institución 1");
		
		DTTRegistro tp = c.seleccionarTipoRegistro("tp1", "Evento 1", "Edición 1");
		
		assertEquals(dtp.getCodigo(), "pat1");
		assertEquals(dtp.getTipo(), TipoNivelPatrocinio.Plata);
		assertEquals(dtp.getMonto(), 10 * tp.getCosto() * 5.1f);
		assertEquals(dtp.getTipoRegistro(), "tp1");
		assertEquals(dtp.getCantRegistros(), 10);
		assertEquals(dtp.getFechaAlta(), LocalDate.now());
	}
	
	@Test @Order(16)
	//testeo Tipo Registro existente
	void testMostrarTipoRegistro() {
		DTTRegistro dtr = c.mostrarTipoRegistro("Evento 1", "Edición 1", "tp1");
		assertEquals(dtr.getCupo(), 1);
		assertEquals(dtr.getCosto(), 100);
		assertEquals(dtr.getNombre(), "tp1");
		assertEquals(dtr.getDescripcion(), "desc1");

		
	}
	@Test @Order(11)
	void testListarPatrocinios() {
		assertEquals(Set.of("pat1", "pat2"), new HashSet<>(c.listarPatrocinios("Evento 1", "Edición 1")));
	}

	@Test @Order(12)
	void testListarOrganizadores() {
		assertEquals(List.of("org"), c.listarOrganizadores());
	}

	@Test @Order(13)
	void testListarCategorias() {
		assertEquals(Set.of("c1", "c2", "c3", "c4"), new HashSet<>(c.listarCategorias()));
	}
	
	//Dar de alta un registro de un Asistente a una Edicion
	@Test @Order(16)
	void testCrearRegistro() {
		c = ControladorEvento.getInstance();
		cu = ControladorUsuario.getInstance();
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
		assertEquals(true, cu.altaAsistente("as", "Asistente 2", "dsa", "cont", null, "prueba", LocalDate.parse("12/12/2012", fmt)));
		assertEquals(false, c.darDeAltaRegistro("as", "Edición 1", "tp1", "Evento 1", LocalDate.parse("12/12/2025", fmt), false));
		assertEquals(true, cu.altaAsistente("asistente3", "Asistente 3", "dsada", "cont", null, "ff", LocalDate.parse("12/12/2012", fmt)));
		
		// Se supera el cupo
		assertEquals(true, c.darDeAltaRegistro("asistente3", "Edición 1", "tp1", "Evento 1", LocalDate.parse("12/12/2025", fmt), false));
	}
	
	@Test @Order(17)
	void testCrearRegistroConPatrocinio() {
		c = ControladorEvento.getInstance();
		cu = ControladorUsuario.getInstance();
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
		// Código incorrecto
		assertThrows(IllegalArgumentException.class, () -> {
			c.patrocinioEdicion("pat", "Edición 1", "Evento 1", "tp1", "Institución 2");
		});
		// Tipo registro incorrecto
		assertThrows(IllegalArgumentException.class, () -> {
			c.patrocinioEdicion("pat2", "Edición 1", "Evento 1", "tp2", "Institución 2");
		});
		// Correcto
		assertDoesNotThrow(() -> {
			c.patrocinioEdicion("pat2", "Edición 1", "Evento 1", "tp1", "Institución 2");
		});
		// Se supera el cupo
		assertThrows(IllegalStateException.class, () -> {
			c.patrocinioEdicion("pat2", "Edición 1", "Evento 1", "tp1", "Institución 2");
		});
	}
	
	@Test @Order(17)
	void testListarTipoRegistro() {
		List<String> resultadoEsperado = c.listarTipoRegistro("Evento 1", "Edición 1");
		assertEquals(List.of("tp1"), resultadoEsperado);
	}
	
	
}
