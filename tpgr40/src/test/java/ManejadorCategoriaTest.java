package test.java;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Set;

import logica.ManejadorCategoria;

class ManejadorCategoriaTest {
	@BeforeAll
	static void setup() {
		ManejadorCategoria m = ManejadorCategoria.getInstance();

		m.altaCategoria("Categoría 1");
	}
	
	@Test
	void deberiaCrear() {
		ManejadorCategoria m = ManejadorCategoria.getInstance();

		m.altaCategoria("Categoría 1");
		assertEquals(true, m.existe("Categoría 1"));
		assertEquals(false, m.existe("Categoría 2"));
		m.altaCategoria("Categoría 2");
		assertEquals(true, m.existe("Categoría 2"));
	}
	
	@Test
	void deberiaObtener() {
		ManejadorCategoria m = ManejadorCategoria.getInstance();

		m.altaCategoria("Categoría 3");
		m.altaCategoria("Categoría 4");
		m.altaCategoria("Categoría 5");
		if (m.existe("Categoría 2"))
			assertEquals(Set.of("Categoría 1", "Categoría 2", "Categoría 3", "Categoría 4", "Categoría 5"), m.categorias().keySet());
		else
			assertEquals(Set.of("Categoría 1", "Categoría 3", "Categoría 4", "Categoría 5"), m.categorias().keySet());
	}
}
