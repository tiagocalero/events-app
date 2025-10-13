package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import logica.Asistente;
import logica.ControladorCategoria;
import logica.ControladorEvento;
import logica.ControladorInstitucion;
import logica.ControladorUsuario;
import logica.DTAsistente;
import logica.DTAsistenteModificado;
import logica.DTEdicion;
import logica.DTOrganizador;
import logica.DTOrganizadorModificado;
import logica.DTRegistro;
import logica.ManejadorCategoria;
import logica.ManejadorInstitucion;
import logica.ManejadorUsuario;
import logica.Organizador;
import logica.Usuario;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControladorUsuarioTest {
	private static ControladorUsuario c;
	private static ManejadorUsuario m;
	private static DateTimeFormatter fmt;
	
	// Se ejecuta antes que todos los tests
	@BeforeAll
	static void setup() {
		c = ControladorUsuario.getInstance();
		m = ManejadorUsuario.getInstance();
        fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		

		// Crear asistente de prueba
		c.altaAsistente("as", "Asistente 1", "a", "cont", null, "prueba", LocalDate.parse("12/12/2012", fmt));
		
		// Crear organizador de prueba
		c.altaOrganizador("org", "Organizador 1", "o", "cont", null, "organiza", "http://");
		
		// Crear instituciónes de prueba
		ControladorInstitucion ci = ControladorInstitucion.getInstance();
		ci.altaInstitucion("Institución 1", "inst", "http://");
		ci.altaInstitucion("Institución 2", "inst2", "http://");
	}
	
	// Se ejecuta después que todos los tests
	@AfterAll
	static void borrado() {
		ManejadorCategoria.borrarInstance();
		ManejadorUsuario.getInstance().liberarMemoria();
		ManejadorInstitucion.borrarInstance();
	}
	
	// Probar crear un usuario con nickname ya existente
	@Test @Order(1)
	void testCrearUsuario() {
		// chequeo asistente
		assertEquals(true, c.altaAsistente("as2", "Asistente 2", "a2", "cont", null, "prueba2", LocalDate.parse("12/12/2012", fmt), "Institución 1"));
				
		DTAsistente dta = (DTAsistente) c.elegirUsuario("as2");
		// Tomar el dt y asegurarse que cada campo sea correcto
		assertEquals("as2", dta.getNickname());
		assertEquals("Asistente 2", dta.getNombre());
		assertEquals("a2", dta.getCorreo());
		assertEquals("prueba2", dta.getApellido());
		assertEquals(LocalDate.parse("12/12/2012", fmt), dta.getNacimiento());
		assertEquals("Institución 1", dta.getInstitucion());
		
		// cheqeueo organizador
		assertEquals(true, c.altaOrganizador("org2", "Organizador 2", "o2", "cont", null, "organiza2", "http://"));
				
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
		assertEquals(false, c.altaAsistente("as", "Asistente 2", "a2", "cont", null, "prueba", LocalDate.parse("12/12/2012", fmt)));
		DTAsistente dtu = (DTAsistente) c.elegirUsuario("as");
		// Tomar el dt y asegurarse que cada campo sea correcto
		assertEquals("Asistente 1", dtu.getNombre());
		assertEquals("a", dtu.getCorreo());
		assertEquals("prueba", dtu.getApellido());
		assertEquals(LocalDate.parse("12/12/2012", fmt), dtu.getNacimiento());
		assertEquals(null, dtu.getInstitucion());
		
		// chequeo correo
		assertEquals(false, c.altaAsistente("as3", "Asistente 2", "a", "cont", null, "prueba", LocalDate.parse("12/12/2012", fmt)));
		dtu = (DTAsistente) c.elegirUsuario("as");
		// Tomar el dt y asegurarse que cada campo sea correcto
		assertEquals("Asistente 1", dtu.getNombre());
		assertEquals("a", dtu.getCorreo());
		assertEquals("prueba", dtu.getApellido());
		assertEquals(LocalDate.parse("12/12/2012", fmt), dtu.getNacimiento());
		assertEquals(null, dtu.getInstitucion());
		
		assertEquals(true, c.chequearContrasena("as", "cont"));
		assertEquals(false, c.chequearContrasena("as", "contr"));
		c.setContrasena("as", "contr");
		assertEquals(false, c.chequearContrasena("as", "cont"));
		assertEquals(true, c.chequearContrasena("as", "contr"));
	}
	
	@Test @Order(3)
	void testModificarDatosUsuario() {
		// chequeo asistente
		DTAsistenteModificado dtam = new DTAsistenteModificado("Asistente Modificado", null, "apm", LocalDate.parse("01/01/2000", fmt), "Institución 2");
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
		DTOrganizadorModificado dtom = new DTOrganizadorModificado("Organizador Modificado", null, "organiza modificado", "http:// modificado");
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
	
	void compararDTEdiciones(DTEdicion dte1, DTEdicion dte2) {
		assertEquals(dte1.getPais(), dte2.getPais());
		assertEquals(dte1.getCiudad(), dte2.getCiudad());
		assertEquals(dte1.getFechaInicio(), dte2.getFechaInicio());
		assertEquals(dte1.getFechaFin(), dte2.getFechaFin());
		assertEquals(dte1.getFechaAlta(), dte2.getFechaAlta());
		assertEquals(dte1.getSigla(), dte2.getSigla());
		assertEquals(dte1.getNombre(), dte2.getNombre());
	}
	
	@Test @Order(4)
	void testConsultaUsuario() {
		ControladorEvento ce = ControladorEvento.getInstance();
		LocalDate f = LocalDate.now();
		ControladorCategoria cg = ControladorCategoria.getInstance();
		cg.altaCategoria("c1");
		cg.altaCategoria("c2");
		cg.altaCategoria("c3");
		
		ce.ingresarDatosEvento("Evento test", "descripcion1", "e1", f, List.of("c1", "c2", "c3"));
		assertEquals(true, ce.altaEdicion("Evento test", "org", "Edición test 1", "ed1", f, f, f, "Montevideo", "Uruguay"));
		assertEquals(true, ce.altaEdicion("Evento test", "org", "Edición test 2", "ed2", f, f, f, "Salto", "Uruguay"));
		DTEdicion dte1 = new DTEdicion("Evento test", "Uruguay", "Montevideo", f, f, f, "ed1", "Edición test 1", List.of(), List.of(), List.of(), "org", null);
		DTEdicion dte2 = new DTEdicion("Evento test", "Uruguay", "Salto", f, f, f, "ed2", "Edición test 2", List.of(), List.of(), List.of(), "org", null);
		
		ce.crearTipoRegistro("Evento test", "Edición test 1", "tp5", "desc5", 101, 12);
		ce.crearTipoRegistro("Evento test", "Edición test 2", "tp5", "desc5", 101, 12);
		ce.darDeAltaRegistro("as", "Edición test 1", "tp5", "Evento test", f, false);
		ce.darDeAltaRegistro("as", "Edición test 2", "tp5", "Evento test", f, false);
		DTRegistro dtr1 = new DTRegistro(101, f, "Edición test 1", "tp5", "as", false);
		DTRegistro dtr2 = new DTRegistro(101, f, "Edición test 2", "tp5", "as", false);
		
		Asistente a = (Asistente) m.obtener("as");
		// chequeo asistente
		DTRegistro aget0 = a.consulta().get(0);
		DTRegistro aget1 = a.consulta().get(1);
		if (aget0.getEdicion() == dtr1.getEdicion()) {
			compararDTRegistros(dtr1, aget0);
			compararDTRegistros(dtr2, aget1);
		} else {
			compararDTRegistros(dtr1, aget1);
			compararDTRegistros(dtr2, aget0);
		}
		
		c.elegirUsuario("as");
		DTRegistro cona0 = c.consultaAsistente().get(0);
		DTRegistro cona1 = c.consultaAsistente().get(1);
		if (aget0.getEdicion() == cona0.getEdicion()) {
			compararDTRegistros(aget0, cona0);
			compararDTRegistros(aget1, cona1);
		} else {
			compararDTRegistros(aget0, cona1);
			compararDTRegistros(aget1, cona0);
		}
		
		Organizador o = (Organizador) m.obtener("org");
		// chequeo organizador
		
		DTEdicion oget0 = o.consulta().get(0);
		DTEdicion oget1 = o.consulta().get(1);
		if (oget0.getNombre() == dte1.getNombre()) {
			compararDTEdiciones(dte1, oget0);
			compararDTEdiciones(dte2, oget1);
		} else {
			compararDTEdiciones(dte1, oget1);
			compararDTEdiciones(dte2, oget0);
		}
		
		c.elegirUsuario("org");
		DTEdicion cono0 = c.consultaOrganizador().get(0);
		DTEdicion cono1 = c.consultaOrganizador().get(1);
		if (oget0.getNombre() == cono0.getNombre()) {
			compararDTEdiciones(oget0, cono0);
			compararDTEdiciones(oget1, cono1);
		} else {
			compararDTEdiciones(oget0, cono1);
			compararDTEdiciones(oget1, cono0);
		}
	}

	@Test @Order(5)
	void testRegistroUsuario() {
		DTRegistro dt = c.datosRegistroUsuario("Edición test 1", "as");
		assertEquals(101, dt.getCosto());
		assertEquals("Edición test 1", dt.getEdicion());
		assertEquals("tp5", dt.getTipo());
	}


	@Test @Order(6)
	void testListarUsuarios() {
		assertEquals(Set.of("as", "as2", "org", "org2"), c.listarUsuarios().stream().collect(Collectors.toSet()));
		assertEquals(Set.of("as", "as2"), c.listarAsistentes().stream().collect(Collectors.toSet()));
		assertEquals(Set.of("org", "org2"), c.listarOrganizadores().stream().collect(Collectors.toSet()));
	}
	
}