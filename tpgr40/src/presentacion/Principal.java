package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class Principal{
	
	private JFrame frmPrincipal;
	
	public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            
                try {
                    Principal window = new Principal();
                    window.frmPrincipal.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        });
    }
	
	
	public Principal() {
		inicializar();
	}
	
	private void inicializar() {
		frmPrincipal = new JFrame();
		frmPrincipal.setTitle("Eventos.uy");
		
		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);
		
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
        
        JMenu menUsuario = new JMenu("Usuarios");
        menuBar.add(menUsuario);
        JMenuItem menuModDatosUsuario = new JMenuItem("Modificar Datos de Usuario");
        menUsuario.add(menuModDatosUsuario);
	}




}