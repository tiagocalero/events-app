package test.java;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import logica.ManejadorCategoria;
import logica.ControladorCategoria;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControladorCategoriaTest {
	static ControladorCategoria c;
	static ManejadorCategoria m;
	
	@BeforeAll
	static void setup() {
		c = ControladorCategoria.getInstance();
		m = ManejadorCategoria.getInstance();

		c.altaCategoria("Categoría 1");
	}
	
	@AfterAll
	static void borrado() {
		ManejadorCategoria.borrarInstance();
	}
	
	@Test @Order(1)
	void testCrear() {
		assertEquals(true, m.existe("Categoría 1"));
		assertEquals(false, m.existe("Categoría 2"));
		assertEquals(true, c.altaCategoria("Categoría 2"));
		assertEquals(true, m.existe("Categoría 2"));
	}
	@Test @Order(2)
	void testCrearExistente() {
		assertEquals(false, c.altaCategoria("Categoría 1"));
	}

	@Test @Order(3)
	void deberiaObtener() {
		c.altaCategoria("Categoría 3");
		c.altaCategoria("Categoría 4");
		c.altaCategoria("Categoría 5");
		assertEquals(Set.of("Categoría 1", "Categoría 2", "Categoría 3", "Categoría 4", "Categoría 5"), m.categorias().keySet());
	}
}
