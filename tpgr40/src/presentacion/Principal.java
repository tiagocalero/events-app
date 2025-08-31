package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;

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

		// Maximiza la ventana principal para que ocupe toda la pantalla
		frmPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);

		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);

		// Crea e inicializa el JDesktopPane y lo establece como el panel de contenido
		desktopPane = new JDesktopPane();
		frmPrincipal.setContentPane(desktopPane);

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
        });
        menuSistema.add(menuCargarDatos);

        JMenu menuEventos = new JMenu("Eventos");
        menuBar.add(menuEventos);
        JMenuItem menuConEventos = new JMenuItem("Consulta de Eventos");
        menuConEventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JInternalFrame c = new ConsultaEventos();
                desktopPane.add(c);
                centerInternalFrame(c);
                c.setVisible(true);
            }
        });
        menuEventos.add(menuConEventos);

        JMenuItem menuConPatrocinios = new JMenuItem("Consulta de Patrocinio");
        menuConPatrocinios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JInternalFrame c = new ConsultaPatrocinio();
                desktopPane.add(c);
                centerInternalFrame(c);
                c.setVisible(true);
            }
        });
        menuEventos.add(menuConPatrocinios);

        JMenuItem menuAltaEventos = new JMenuItem("Alta de Eventos");
        menuAltaEventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame a = new AltaEvento();
                desktopPane.add(a);
                centerInternalFrame(a);
                a.setVisible(true);
            }
        });
        menuEventos.add(menuAltaEventos);

        JMenuItem menuAltaEdicion = new JMenuItem("Alta de Edición de Evento");
        menuAltaEdicion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame a = new AltaEdicionEvento();
                desktopPane.add(a);
                centerInternalFrame(a);
                a.setVisible(true);
            }
        });
        menuEventos.add(menuAltaEdicion);

        JMenuItem menuConsultaEdicion = new JMenuItem("Consulta de Edición de Evento");
        menuConsultaEdicion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame a = new ConsultaEdicionDeEvento();
                desktopPane.add(a);
                centerInternalFrame(a);
                a.setVisible(true);
            }
        });
        menuEventos.add(menuConsultaEdicion);

        JMenuItem menuAltaPatrocinio = new JMenuItem("Alta de Patrocinio");
        menuAltaPatrocinio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame a = new AltaPatrocinio();
                desktopPane.add(a);
                centerInternalFrame(a);
                a.setVisible(true);
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
                desktopPane.add(a);
                centerInternalFrame(a);
                a.setVisible(true);
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
            	desktopPane.add(c);
                centerInternalFrame(c);
                c.setVisible(true);
            }
        });
        menuInstitucion.add(menuAltaInst);
	}

    // Método para centrar una JInternalFrame dentro del JDesktopPane
    private void centerInternalFrame(JInternalFrame frame) {
        Dimension desktopSize = desktopPane.getSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.getWidth() > 0 && frameSize.getHeight() > 0) {
            int x = (desktopSize.width - frameSize.width) / 2;
            int y = (desktopSize.height - frameSize.height) / 2;
            frame.setLocation(x, y);
        }
    }
}