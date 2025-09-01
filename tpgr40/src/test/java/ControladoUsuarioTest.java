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
import logica.ControladorInstitucion;
import logica.ControladorUsuario;
import logica.DTAsistente;
import logica.DTEvento;
import logica.DTOrganizador;
import logica.DTUsuario;
import logica.ManejadorCategoria;

import org.junit.jupiter.api.MethodOrderer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControladorUsuarioTest {
	private static ControladorUsuario c;
	
	// Se ejecuta antes que todos los tests
	@BeforeAll
	static void setup() {
		c = ControladorUsuario.getInstance();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		

		// Crear asistente de prueba
		c.altaAsistente("as", "Asistente 1", "ads", "prueba", LocalDate.parse("12/12/2012", fmt));
		
		// Crear organizador de prueba
		c.altaOrganizador("org", "Organizador 1", "ads", "organiza", "http://");
		
		// Crear institución de prueba
		ControladorInstitucion ci = ControladorInstitucion.getInstance();
		ci.altaInstitucion("Institución 1", "inst", "http://");
	}
	
	// Se ejecuta después que todos los tests
	@AfterAll
	static void borrado() {
	}
	
	// Probar crear un usuario con nickname ya existente
	@Test @Order(1)
	void testCrearUsuario() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        
		assertEquals(true, c.altaAsistente("as2", "Asistente 2", "ads2", "prueba2", LocalDate.parse("12/12/2012", fmt)), "Institución 1");
				
		DTAsistente dta = (DTAsistente) c.elegirUsuario("as");
		// Tomar el dt y asegurarse que cada campo sea correcto
		assertEquals("as2", dta.getNickname());
		assertEquals("Asistente 2", dta.getNombre());
		assertEquals("ads2", dta.getCorreo());
		assertEquals("prueba2", dta.getApellido());
		assertEquals(LocalDate.parse("12/12/2012", fmt), dta.getNacimiento());
		assertEquals("Institución 1", dta.getInstitucion());
		        
		assertEquals(true, c.altaOrganizador("Org2", "Organizador 2", "ads2", "organiza2", "http://"));
				
		DTOrganizador dtu = (DTOrganizador) c.elegirUsuario("as2");
		// Tomar el dt y asegurarse que cada campo sea correcto
		assertEquals("as2", dtu.getNickname());
		assertEquals("Asistente 2", dtu.getNombre());
		assertEquals("ads2", dtu.getCorreo());
		assertEquals("organiza2", dtu.getDescripcion());
		assertEquals("http://", dtu.getLink());
	}
	
	// Probar crear un usuario con nickname ya existente
	@Test @Order(2)
	void testCrearUsuarioConNickExistente() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        
		assertEquals(true, c.altaAsistente("as", "Asistente 2", "dsa", "prueba", LocalDate.parse("12/12/2012", fmt)));
			
		DTAsistente dtu = (DTAsistente) c.elegirUsuario("as");
		// Tomar el dt y asegurarse que cada campo sea correcto
		assertEquals("Asistente 1", dtu.getNombre());
		assertEquals("ads", dtu.getCorreo());
		assertEquals("prueba", dtu.getApellido());
		assertEquals(LocalDate.parse("12/12/2012", fmt), dtu.getNacimiento());
		assertEquals(null, dtu.getInstitucion());
			
			
	}

	
}