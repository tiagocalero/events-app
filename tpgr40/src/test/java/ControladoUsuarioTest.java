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

import logica.Asistente;
import logica.ControladorCategoria;
import logica.ControladorEvento;
import logica.ControladorInstitucion;
import logica.ControladorUsuario;
import logica.DTAsistente;
import logica.DTAsistenteModificado;
import logica.DTEvento;
import logica.DTOrganizador;
import logica.DTOrganizadorModificado;
import logica.DTUsuario;
import logica.DTRegistro;
import logica.ManejadorCategoria;
import logica.ManejadorInstitucion;
import logica.ManejadorUsuario;

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
	private static DateTimeFormatter fmt;
	
	// Se ejecuta antes que todos los tests
	@BeforeAll
	static void setup() {
		c = ControladorUsuario.getInstance();
        fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		

		// Crear asistente de prueba
		c.altaAsistente("as", "Asistente 1", "a", "prueba", LocalDate.parse("12/12/2012", fmt));
		
		// Crear organizador de prueba
		c.altaOrganizador("org", "Organizador 1", "o", "organiza", "http://");
		
		// Crear instituciónes de prueba
		ControladorInstitucion ci = ControladorInstitucion.getInstance();
		ci.altaInstitucion("Institución 1", "inst", "http://");
		ci.altaInstitucion("Institución 2", "inst2", "http://");
	}
	
	// Se ejecuta después que todos los tests
	@AfterAll
	static void borrado() {
		ManejadorUsuario.getInstance().liberarMemoria();
		ManejadorInstitucion.borrarInstance();
	}
	
	// Probar crear un usuario con nickname ya existente
	@Test @Order(1)
	void testCrearUsuario() {
		// chequeo asistente
		assertEquals(true, c.altaAsistente("as2", "Asistente 2", "a2", "prueba2", LocalDate.parse("12/12/2012", fmt), "Institución 1"));
				
		DTAsistente dta = (DTAsistente) c.elegirUsuario("as2");
		// Tomar el dt y asegurarse que cada campo sea correcto
		assertEquals("as2", dta.getNickname());
		assertEquals("Asistente 2", dta.getNombre());
		assertEquals("a2", dta.getCorreo());
		assertEquals("prueba2", dta.getApellido());
		assertEquals(LocalDate.parse("12/12/2012", fmt), dta.getNacimiento());
		assertEquals("Institución 1", dta.getInstitucion());
		
		// cheqeueo organizador
		assertEquals(true, c.altaOrganizador("org2", "Organizador 2", "o2", "organiza2", "http://"));
				
		DTOrganizador dtu = (DTOrganizador) c.elegirUsuario("org2");
		// Tomar el dt y asegurarse que cada campo sea correcto
		assertEquals("org2", dtu.getNickname());
		assertEquals("Organizador 2", dtu.getNombre());
		assertEquals("o2", dtu.getCorreo());
		assertEquals("organiza2", dtu.getDescripcion());
		assertEquals("http://", dtu.getLink());
	}
	
	// Probar crear un usuario con nickname ya existente
	@Test @Order(2)
	void testCrearUsuarioConNickOCorreoExistente() {	    
		// chequeo nickname
		assertEquals(false, c.altaAsistente("as", "Asistente 2", "a2", "prueba", LocalDate.parse("12/12/2012", fmt)));
		DTAsistente dtu = (DTAsistente) c.elegirUsuario("as");
		// Tomar el dt y asegurarse que cada campo sea correcto
		assertEquals("Asistente 1", dtu.getNombre());
		assertEquals("a", dtu.getCorreo());
		assertEquals("prueba", dtu.getApellido());
		assertEquals(LocalDate.parse("12/12/2012", fmt), dtu.getNacimiento());
		assertEquals(null, dtu.getInstitucion());
		
		// chequeo correo
		assertEquals(false, c.altaAsistente("as3", "Asistente 2", "a", "prueba", LocalDate.parse("12/12/2012", fmt)));
		dtu = (DTAsistente) c.elegirUsuario("as");
		// Tomar el dt y asegurarse que cada campo sea correcto
		assertEquals("Asistente 1", dtu.getNombre());
		assertEquals("a", dtu.getCorreo());
		assertEquals("prueba", dtu.getApellido());
		assertEquals(LocalDate.parse("12/12/2012", fmt), dtu.getNacimiento());
		assertEquals(null, dtu.getInstitucion());
	}
	
	@Test @Order(3)
	void testModificarDatosUsuario() {
		// chequeo asistente
		DTAsistenteModificado dtam = new DTAsistenteModificado("Asistente Modificado", "apm", LocalDate.parse("01/01/2000", fmt), "Institución 2");
		c.elegirUsuario("as");
		c.modificarDatosUsuario(dtam);
		DTAsistente dta = (DTAsistente) c.elegirUsuario("as");
		// Tomar el dt y asegurarse que cada campo sea correcto
		assertEquals("Asistente Modificado", dta.getNombre());
		assertEquals("a", dta.getCorreo());
		assertEquals("apm", dta.getApellido());
		assertEquals(LocalDate.parse("01/01/2000", fmt), dta.getNacimiento());
		assertEquals("Institución 2", dta.getInstitucion());
		
		// chequeo organizador
		DTOrganizadorModificado dtom = new DTOrganizadorModificado("Organizador Modificado", "organiza modificado", "http:// modificado");
		c.elegirUsuario("org");
		c.modificarDatosUsuario(dtom);
		DTOrganizador dto = (DTOrganizador) c.elegirUsuario("org");
		// Tomar el dt y asegurarse que cada campo sea correcto
		assertEquals("Organizador Modificado", dto.getNombre());
		assertEquals("o", dto.getCorreo());
		assertEquals("organiza modificado", dto.getDescripcion());
		assertEquals("http:// modificado", dto.getLink());

	}
	
	void compararDTRegistros(DTRegistro dtr1, DTRegistro dtr2) {
		assertEquals(dtr1.getCosto(), dtr2.getCosto());
		assertEquals(dtr1.getFecha(), dtr2.getFecha());
		assertEquals(dtr1.getEdicion(), dtr2.getEdicion());
		assertEquals(dtr1.getTipo(), dtr2.getTipo());
	}
	
	@Test @Order(4)
	void testConsultaUsuario() {
		ControladorEvento ce = ControladorEvento.getInstance();
		LocalDate f = LocalDate.now();
		ControladorCategoria cg = ControladorCategoria.getInstance();
		cg.altaCategoria("c1");
		cg.altaCategoria("c2");
		cg.altaCategoria("c3");
		
		ce.ingresarDatosEvento("Evento test", "descripcion1", "e1", List.of("c1", "c2", "c3"));
		assertEquals(true, ce.altaEdicion("Evento test", "org", "Edición test 1", "ed1", f, f, f, "Montevideo", "Uruguay"));
		assertEquals(true, ce.altaEdicion("Evento test", "org", "Edición test 2", "ed2", f, f, f, "Salto", "Uruguay"));
		
		ce.crearTipoRegistro("Evento test", "Edición test 1", "tp5", "desc5", 101, 12);
		ce.crearTipoRegistro("Evento test", "Edición test 2", "tp5", "desc5", 101, 12);
		ce.darDeAltaRegistroCD("as", "Edición test 1", "tp5", "Evento test", f.format(fmt), 101);
		ce.darDeAltaRegistroCD("as", "Edición test 2", "tp5", "Evento test", f.format(fmt), 101);
		DTRegistro dtr1 = new DTRegistro(101, f, "Edición test 1", "tp5");
		DTRegistro dtr2 = new DTRegistro(101, f, "Edición test 2", "tp5");
		
		Asistente a = (Asistente) c.obtenerUsuario("as");
		// chequeo asistente
		compararDTRegistros(dtr1, a.consulta().get(0));
		compararDTRegistros(dtr2, a.consulta().get(1));
		
		c.elegirUsuario("as");
		compararDTRegistros(a.consulta().get(1), c.consultaAsistente().get(1));
		compararDTRegistros(a.consulta().get(0), c.consultaAsistente().get(0));
		
		
		// chequeo organizador
		
		
	}

	
}