package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;



public class Principal extends JFrame {
	public Principal() {
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuSistema = new JMenu("Sistema");
        menuBar.add(menuSistema);
        
        JMenu menuEventos = new JMenu("Eventos");
        menuBar.add(menuEventos);
		
	}



}