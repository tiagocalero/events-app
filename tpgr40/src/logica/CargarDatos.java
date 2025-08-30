package logica;

public class CargarDatos {
	public static void cargaDatos() {
		
		//Cargar Categorias
		ControladorCategoria c = ControladorCategoria.getInstance();
		boolean cat = c.altaCategoria("Tecnología");
		cat = c.altaCategoria("Innovación");
		cat = c.altaCategoria("Literatura");
		cat = c.altaCategoria("Cultura");
		cat = c.altaCategoria("Música");
		cat = c.altaCategoria("Deporte");
		cat = c.altaCategoria("Salud");
		cat = c.altaCategoria("Entretenimiento");
		cat = c.altaCategoria("Agro");
		cat = c.altaCategoria("Negocios");
		cat = c.altaCategoria("Moda");
		cat = c.altaCategoria("Investigación");
		
		//Cargar Instituciones
		ControladorInstitucion i = ControladorInstitucion.getInstance();
		boolean inst = i.altaInstitucion("Agencia Nacional de Investigación e Innovación (ANII)", "Fomenta la investigación y la innovación en Uruguay", "https://anii.org.uy");
		inst = i.altaInstitucion("Facultad de Ingeniería", "Facultad de Ingeniería de la Universidad de la República", "https://www.fing.edu.uy");
		inst = i.altaInstitucion("ORT Uruguay", "Universidad privada enfocada en tecnología y gestión", "https://ort.edu.uy");
		inst = i.altaInstitucion("Universidad Católica del Uruguay", "Institución de educación superior privada", "https://ucu.edu.uy");
		inst = i.altaInstitucion("Antel", "Empresa estatal de telecomunicaciones", "https://antel.com.uy");
		
	
	
	
	
	}
}
