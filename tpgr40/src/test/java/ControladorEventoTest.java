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
	
	@Test @Order(1)
	void testCrearEventoConCategoriaExistente() {
		assertEquals(true, c.ingresarDatosEvento("Evento 1", "descripcion1", "e1", List.of("c1", "c2", "c3")));
		
		DTEvento dte = c.informacionEvento("Evento 1");
		assertEquals("Evento 1", dte.getNombre());
		assertEquals("descripcion1", dte.getDescripcion());
		assertEquals("e1", dte.getSigla());
		assertEquals(Set.of("c1", "c2", "c3"), new HashSet<>(dte.getCategorias()));
	}
	
	@Test @Order(2)
	void testCrearEventoConCategoriaNoExistente() {
		assertEquals(true, c.ingresarDatosEvento("Evento 2", "descripcion2", "e2", List.of("c4")));
		
		DTEvento dte = c.informacionEvento("Evento 2");
		assertEquals("Evento 2", dte.getNombre());
		assertEquals("descripcion2", dte.getDescripcion());
		assertEquals("e2", dte.getSigla());
		assertEquals(List.of("c4"), dte.getCategorias());
	}

	@Test @Order(3)
	void testCrearEventoExistente() {
		assertEquals(false, c.ingresarDatosEvento("Evento 1", "descripcion2", "e2", List.of("c4")));
		
		DTEvento dte = c.informacionEvento("Evento 1");
		assertEquals("Evento 1", dte.getNombre());
		assertEquals("descripcion1", dte.getDescripcion());
		assertEquals("e1", dte.getSigla());
		assertEquals(Set.of("c1", "c2", "c3"), new HashSet<>(dte.getCategorias()));
	}
	
	@Test @Order(4)
	void testListarEventos() {
		assertEquals(Set.of("Evento 1", "Evento 2"), c.listarEventos().stream().collect(Collectors.toSet()));
	}
	
	@Test @Order(5)
	void testCrearEdicionEvento() {
		assertEquals(true, c.altaEdicion("Evento 1", "org", "Edición 1", "ed1", "Montevideo", "Uruguay"));
		assertEquals(List.of("Edición 1"), c.listarEdiciones("Evento 1"));
	}

	@Test @Order(6)
	void testCrearTipoRegistro() {
		c.elegirEvento("Evento 1");
		c.elegirEdicion("Edición 1");
		c.crearTipoRegistro("tp1", "desc1", 100, 1);
	}
	
	@Test @Order(7)
	void testCrearPatrocinio() {
		c.elegirEdicion("Edición 1");
		c.elegirInstitucion("Institución 1");
		
		DTPatrocinio dtp = new DTPatrocinio("pat1", TipoNivelPatrocinio.Plata, 100, "tp1", 0);
		assertEquals(true, c.crearPatrocinio(dtp));
	}
}
