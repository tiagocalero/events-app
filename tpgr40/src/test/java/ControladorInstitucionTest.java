package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;

import logica.ControladorInstitucion;
import logica.ManejadorCategoria;


import excepciones.PatrocinioSuperaMontoException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControladorInstitucionTest {
	private static ControladorInstitucion ci;
	
	// Se ejecuta antes que todos los tests
	@BeforeAll
	static void setup() {
		
		// Crear instituciones de prueba
		ControladorInstitucion ci = ControladorInstitucion.getInstance();
		ci.altaInstitucion("Institucion 1", "inst", "http://");
		ci.altaInstitucion("Institucion 2", "desc", "ttp://");
		
	}

	// Se ejecuta después que todos los tests
	@AfterAll
	static void borrado() {
		ManejadorCategoria.borrarInstance();
	}
	
	// Probar crear instituciones con nombre que ya existen
	@Test @Order(1)
	void testCrearInstitucionConNombreExistente() {
		ControladorInstitucion ci = ControladorInstitucion.getInstance();
		assertEquals(false, ci.altaInstitucion("Institucion 1", "insttt", "http://"));
	}
	
	//Probar listar las instituciones
	@Test @Order(2)
	void testListarInstituciones() {
		ControladorInstitucion ci = ControladorInstitucion.getInstance();
		assertEquals(Set.of("Institucion 1", "Institucion 2"), ci.listarInstituciones().stream().collect(Collectors.toSet()));
	}
	
	//Probar obtener una institucion
	@Test @Order(3)
	void testObtenerInstitucion() {
		ControladorInstitucion ci = ControladorInstitucion.getInstance();
		assertEquals(ci.obtenerInstitucion("Institucion 1"), ci.obtenerInstitucion("Institucion 1"));
	}
		
}
