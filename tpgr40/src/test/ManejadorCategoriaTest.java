package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import logica.ManejadorCategoria;

class ManejadorCategoriaTest {
	@Test
	void crear() {
		ManejadorCategoria m = ManejadorCategoria.getInstance();
		m.altaCategoria("Categoría 1");
		assertEquals(true, m.existe("Categoría 1"));
		
	}

}
