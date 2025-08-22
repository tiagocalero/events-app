package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Principal extends JFrame {
	public Principal() {
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuSistema = new JMenu("Sistema");
        menuBar.add(menuSistema);
        
        JMenuItem menuSalir = new JMenuItem("Salir");
        menuSistema.add(menuSalir);
        
        
        JMenu menuEventos = new JMenu("Eventos");
        menuBar.add(menuEventos);
        JMenuItem menuConEventos = new JMenuItem("Consulta de Eventos");
        menuEventos.add(menuConEventos);
        JMenuItem menuAltaEventos = new JMenuItem("Alta de Eventos");
        menuEventos.add(menuAltaEventos);
        JMenuItem menuAltaPatrocinio = new JMenuItem("Alta de Patrocinio");
        menuEventos.add(menuAltaPatrocinio);
        
        
        
        
        
		
	}



}