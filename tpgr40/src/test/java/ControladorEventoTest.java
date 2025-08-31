package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import logica.ControladorEvento;
import logica.ControladorCategoria;
import logica.ControladorInstitucion;
import logica.ControladorUsuario;
import logica.DTEvento;
import logica.DTPatrocinio;
import logica.DTTRegistro;
import logica.TipoNivelPatrocinio;
import logica.ManejadorCategoria;
import logica.ManejadorEvento;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControladorEventoTest {
	private static ControladorEvento c;
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
		cu.altaOrganizador("org", "Organizador 1", "ads", "organiza", "http://");

		// Crear institución de prueba
		ControladorInstitucion ci = ControladorInstitucion.getInstance();
		ci.altaInstitucion("Institución 1", "inst", "http://");
	}
	
	@AfterAll
	static void borrado() {
		ManejadorCategoria.borrarInstance();
	}
	
	// Probar crear eventos con categorias que ya existen
	@Test @Order(1)
	void testCrearEventoConCategoriaExistente() {
		assertEquals(true, c.ingresarDatosEvento("Evento 1", "descripcion1", "e1", List.of("c1", "c2", "c3")));
		
		DTEvento dte = c.informacionEvento("Evento 1");
		assertEquals("Evento 1", dte.getNombre());
		assertEquals("descripcion1", dte.getDescripcion());
		assertEquals("e1", dte.getSigla());
		assertEquals(Set.of("c1", "c2", "c3"), new HashSet<>(dte.getCategorias()));
	}

	// Probar crear eventos con categorias que no existen
	@Test @Order(2)
	void testCrearEventoConCategoriaNoExistente() {
		assertEquals(true, c.ingresarDatosEvento("Evento 2", "descripcion2", "e2", List.of("c4")));
		
		DTEvento dte = c.informacionEvento("Evento 2");
		assertEquals("Evento 2", dte.getNombre());
		assertEquals("descripcion2", dte.getDescripcion());
		assertEquals("e2", dte.getSigla());
		assertEquals(List.of("c4"), dte.getCategorias());
	}

	// Probar crear un evento con un nombre que ya existe
	@Test @Order(3)
	void testCrearEventoExistente() {
		assertEquals(false, c.ingresarDatosEvento("Evento 1", "descripcion2", "e2", List.of("c4")));
		
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

	// Probar crear ediciones con un mismo nombre para un evento
	@Test @Order(6)
	void testCrearEdicionExistenteEvento() {
		LocalDate f = LocalDate.now();
		
		assertEquals(false, c.altaEdicion("Evento 1", "org", "Edición 1", "dsa", f, f, f, "Montevideo", "Uruguay"));
	}

	@Test @Order(7)
	void testCrearTipoRegistro() {
		c.crearTipoRegistro("Evento 1", "Edición 1", "tp1", "desc1", 100, 1);
	}

	// Probar crear TipoRegistro con nombre ya existente para una misma edicion y evento.
	// Probar crear TipoRegistro con nombre ya existente, pero para distinta edicion/evento.
	@Test @Order(8)
	void testCrearTipoRegistroExistente() {
		assertEquals(false, c.crearTipoRegistro("Evento 1", "Edición 1", "tp1", "desc2", 101, 12));
		assertEquals(true, c.crearTipoRegistro("Evento 1", "Edición 2", "tp1", "desc2", 101, 12));
	}

	// Probar crear patrocinios con casos borde de costos
	@Test @Order(9)
	void testCrearPatrocinio() {
		DTTRegistro tp = c.seleccionarTipoRegistro("tp1", "Evento 1", "Edición 1");
		float cantRegistrosOtorgados = 10;
		float costo = tp.getCosto();
		
		// Caso borde: El costo de los registros supera el 20% del monto
		DTPatrocinio dtp = new DTPatrocinio(
			"pat2",
			TipoNivelPatrocinio.Plata,
			cantRegistrosOtorgados * tp.getCosto() * 4.9f,
			"tp1",
			(int) cantRegistrosOtorgados,
			LocalDate.now()
		);
		assertEquals(false, c.crearPatrocinio(dtp, "Edición 1", "Institución 1", "Evento 1"));
		
		// Caso borde: El costo de los registros no supera el 20% del monto (por poco)
		dtp = new DTPatrocinio(
			"pat1",
			TipoNivelPatrocinio.Plata,
			cantRegistrosOtorgados * tp.getCosto() * 5.1f,
			"tp1",
			(int) cantRegistrosOtorgados,
			LocalDate.now()
		);
		assertEquals(true, c.crearPatrocinio(dtp, "Edición 1", "Institución 1", "Evento 1"));
	}

	@Test @Order(10)
	void testListarPatrocinios() {
		assertEquals(List.of("pat1"), c.listarPatrocinios("Evento 1", "Edición 1"));
	}

	@Test @Order(11)
	void testListarOrganizadores() {
		assertEquals(List.of("org"), c.listarOrganizadores());
	}

	@Test @Order(12)
	void testListarCategorias() {
		assertEquals(Set.of("c1", "c2", "c3", "c4"), new HashSet<>(c.listarCategorias()));
	}
}
