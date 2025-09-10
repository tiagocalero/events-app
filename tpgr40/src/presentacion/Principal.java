package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;

import datos.CargarDatos;

import java.awt.Color;

import logica.ControladorCategoria;

public class Principal extends JFrame {

	private JFrame frmPrincipal;
	private AltaEvento altEventoInternalFrame;
	private AltaPatrocinio altPatInternalFrame;
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
		frmPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		frmPrincipal.setContentPane(desktopPane);

		JMenu menuSistema = new JMenu("Sistema");
        menuBar.add(menuSistema);
        

        JMenuItem menuMinimizar = new JMenuItem("Minimizar");
        menuMinimizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frmPrincipal.setState(JFrame.ICONIFIED);
            }
        });
        menuSistema.add(menuMinimizar);
        
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
                JOptionPane.showMessageDialog(frmPrincipal, "Datos cargados correctamente.");
                menuCargarDatos.setEnabled(false);
        	}
        });
        menuSistema.add(menuCargarDatos);

        JMenu menuEventos = new JMenu("Eventos");
        menuBar.add(menuEventos);
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

        JMenu menuEdiciones = new JMenu("Ediciones");
        menuBar.add(menuEdiciones);
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
        menuEdiciones.add(menuAltaEdicion);
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
        menuEdiciones.add(menuConsultaEdicion);

        JMenu menuPatrocinios = new JMenu("Patrocinios");
        menuBar.add(menuPatrocinios);
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
        menuPatrocinios.add(menuAltaPatrocinio);
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
        menuPatrocinios.add(menuConPatrocinios);

        JMenu menuTipoRegistro = new JMenu("Tipo de Registros");
        menuBar.add(menuTipoRegistro);
        JMenuItem menuAltaTipoRegistro = new JMenuItem("Alta de Tipo de Registro");
        menuAltaTipoRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame a = new AltaTipoRegistro();
                desktopPane.add(a);
                centerInternalFrame(a);
                a.setVisible(true);
            }
        });
        menuTipoRegistro.add(menuAltaTipoRegistro);
        JMenuItem menuConsultaTipoRegistro = new JMenuItem("Consulta de Tipo de Registro");
        menuConsultaTipoRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame c = new ConsultaTipoRegistro();
                desktopPane.add(c);
                centerInternalFrame(c);
                c.setVisible(true);
            }
        });
        menuTipoRegistro.add(menuConsultaTipoRegistro);

        JMenu menuRegistros = new JMenu("Registros");
        menuBar.add(menuRegistros);
        JMenuItem menuRegistroAEdicionDeEvento = new JMenuItem("Registro a Edicion de Evento");
        menuRegistroAEdicionDeEvento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame c = new RegistroAEdicionDeEvento();
                desktopPane.add(c);
                centerInternalFrame(c);
                c.setVisible(true);
            }
        });
        menuRegistros.add(menuRegistroAEdicionDeEvento);
        JMenuItem menuConRegistro = new JMenuItem("Consulta Registro");
        menuConRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JInternalFrame c = new ConsultaRegistro();
                desktopPane.add(c);
                centerInternalFrame(c);
                c.setVisible(true);
            }
        });
        menuRegistros.add(menuConRegistro);

        JMenu menUsuario = new JMenu("Usuarios");
        menuBar.add(menUsuario);
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
        
        JMenuItem menuConsultaUsuario = new JMenuItem("Consulta de Usuario");
        menuConsultaUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame a = new ConsultaUsuario(desktopPane);
                desktopPane.add(a);
                centerInternalFrame(a);
                a.setVisible(true);
            }
        });
        menUsuario.add(menuConsultaUsuario);
        

        JMenuItem menuModDatosUsuario = new JMenuItem("Modificar Datos de Usuario");
        menuModDatosUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame a = new ModificarDatosUsuario();
                desktopPane.add(a);
                centerInternalFrame(a);
                a.setVisible(true);
            }
        });
        menUsuario.add(menuModDatosUsuario);

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