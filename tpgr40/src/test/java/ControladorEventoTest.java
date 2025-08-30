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
import java.util.List;

import logica.ControladorEvento;
import logica.ControladorCategoria;
import logica.DTEvento;
import logica.ManejadorCategoria;
import logica.ManejadorEvento;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControladorEventoTest {
	@BeforeAll
	static void crearCategorias() {
		ControladorCategoria cg = ControladorCategoria.getInstance();
		cg.altaCategoria("c1");
		cg.altaCategoria("c2");
		cg.altaCategoria("c3");
	}
	
	@AfterAll
	static void borrado() {
		ManejadorCategoria.borrarInstance();
	}
	
	@Test @Order(1)
	void deberiaCrearEventos() {
		ControladorEvento c = ControladorEvento.getInstance();

		c.ingresarDatosEvento("Evento 1", "descripcion1", "e1", List.of("c1", "c2", "c3"));
		c.ingresarDatosEvento("Evento 2", "descripcion2", "e2", List.of("c1"));
		c.ingresarDatosEvento("Evento 3", "descripcion3", "e3", new ArrayList<>());
		c.ingresarDatosEvento("Evento 4", "descripcion4", "e4", new ArrayList<>());
		assertEquals(false, c.ingresarDatosEvento("Evento 4", "", "", new ArrayList<>()));
	}
	
	@Test @Order(2)
	void deberiaListarEventos() {
		ControladorEvento c = ControladorEvento.getInstance();

		assertEquals(Set.of("Evento 1", "Evento 2", "Evento 3", "Evento 4"), c.listarEventos().stream().collect(Collectors.toSet()));
	}
	
	@Test @Order(3)
	void deberiaDarInfoDeEventos() {
		ControladorEvento c = ControladorEvento.getInstance();

		DTEvento dte = c.informacionEvento("Evento 1");
		assertEquals("Evento 1", dte.getNombre());
		assertEquals("descripcion1", dte.getDescripcion());
		assertEquals("e1", dte.getSigla());
		assertEquals(List.of("c1", "c2", "c3"), dte.getCategorias());
	}
	
	

}
