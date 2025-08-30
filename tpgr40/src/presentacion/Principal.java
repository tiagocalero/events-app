package presentacion;

import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logica.ControladorCategoria;
import logica.CargarDatos;


public class Principal extends JFrame {
	
	private JFrame frmPrincipal;
	private AltaEvento altEventoInternalFrame;
	private AltaPatrocinio altPatInternalFrame;
    private ModificarDatosAsistente modDatosAsInternalFrame;
    private ModificarDatosOrganizador modDatosOrgInternalFrame;
    private AltaInstitucion altaInstInternalFrame;
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
        menuSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frmPrincipal.setVisible(false);
                frmPrincipal.dispose();
            }
        });
        menuSistema.add(menuSalir);
        JMenuItem menuCargarDatos = new JMenuItem("Cargar Datos");
        menuCargarDatos.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                CargarDatos.cargaDatos();
            }
        	setLocationRelativeTo(null);
        });
        menuSistema.add(menuCargarDatos);
        
        JMenu menuEventos = new JMenu("Eventos");
        menuBar.add(menuEventos);
        JMenuItem menuConEventos = new JMenuItem("Consulta de Eventos");
        menuConEventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JInternalFrame c = new ConsultaEventos();
                frmPrincipal.getContentPane().add(c);
            }
        });
        menuEventos.add(menuConEventos);

        JMenuItem menuConPatrocinios = new JMenuItem("Consulta de Patrocinio");
        menuConPatrocinios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JInternalFrame c = new ConsultaPatrocinio();
                frmPrincipal.getContentPane().add(c);
            }
        });
        menuEventos.add(menuConPatrocinios);
        
        JMenuItem menuAltaEventos = new JMenuItem("Alta de Eventos");
        menuAltaEventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame a = new AltaEvento();
                frmPrincipal.getContentPane().add(a);
            }
        });
        menuEventos.add(menuAltaEventos);

        JMenuItem menuAltaEdicion = new JMenuItem("Alta de Edición de Evento");
        menuAltaEdicion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame a = new AltaEdicionEvento();
                frmPrincipal.getContentPane().add(a);
            }
        });
        menuEventos.add(menuAltaEdicion);
              
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
        
        JMenuItem menuAltaUsuario = new JMenuItem("Alta de Usuario");
        menuAltaUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame a = new AltaUsuario();
                frmPrincipal.getContentPane().add(a);
            }
        });
        menUsuario.add(menuAltaUsuario);
        
        JMenu menuInstitucion = new JMenu("Instituciones");
        menuBar.add(menuInstitucion);
        JMenuItem menuAltaInst = new JMenuItem("Alta de Institución");
        menuAltaInst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JInternalFrame c = new AltaInstitucion();
            	frmPrincipal.getContentPane().add(c);

            }
        });
        
        menuInstitucion.add(menuAltaInst);        
        
        JMenuItem menuAltaTipoRegistro = new JMenuItem("Alta de Tipo Registro");
        menuAltaTipoRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame a = new AltaTipoRegistro();
                frmPrincipal.getContentPane().add(a);
            }
        });
        menuEventos.add(menuAltaTipoRegistro);
	}
	
}
