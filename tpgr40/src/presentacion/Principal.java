package presentacion;

import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class Principal extends JFrame {
	
	private JFrame frmPrincipal;
	private AltaEvento altEventoInternalFrame;
	private AltaPatrocinio altPatInternalFrame;
    private ModificarDatosAsistente modDatosAsInternalFrame;
    private ModificarDatosOrganizador modDatosOrgInternalFrame;
    private JDesktopPane desktopPane;
	
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
	    frmPrincipal.setSize(800, 600);
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
        menuAltaEventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir nueva ventana AltaEvento
                JInternalFrame a = new AltaEvento();
                a.setSize(400, 300);
                a.setVisible(true); 
                frmPrincipal.getContentPane().add(a);
            }
        });
        menuEventos.add(menuAltaEventos);
        
        
        JMenuItem menuAltaPatrocinio = new JMenuItem("Alta de Patrocinio");
        menuAltaPatrocinio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir nueva ventana AltaPatrocinio
                JInternalFrame a = new AltaPatrocinio();
                a.setSize(400, 300);
                a.setVisible(true); 
                frmPrincipal.getContentPane().add(a);
            }
        });
        menuEventos.add(menuAltaPatrocinio);
        
        JMenu menUsuario = new JMenu("Usuarios");
        menuBar.add(menUsuario);
        JMenuItem menuModDatosUsuario = new JMenuItem("Modificar Datos de Usuario");
        menUsuario.add(menuModDatosUsuario);
	}




}