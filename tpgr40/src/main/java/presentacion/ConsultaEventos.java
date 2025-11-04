package main.java.presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.java.logica.ControladorEvento;
import main.java.logica.ControladorUsuario;
import main.java.logica.DTEdicion;
import main.java.logica.DTEvento;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;

public class ConsultaEventos extends JInternalFrame {
	private JInternalFrame frmConsultaEventos;
	private JComboBox comboBoxEventos;
	private JLabel lblNombre;
	private JButton btnConsulta;
	private final JTextField textNombre = new JTextField();
	private JTextField textSigla;
	private JLabel lblFecha;
	private JTextField textFecha;
	private JLabel lblCategoras;
	private JLabel lblEdiciones;
	private JComboBox comboBoxCategorias;
	private JComboBox comboBoxEdiciones;
	private JTextArea textDesc;
	private JButton btnCerrar;
	private JDesktopPane desktopPane;
	
	
	public ConsultaEventos(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
        initComponents();
    }
	
	public void initComponents() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar un Evento");
		setBounds(30, 30, 657, 474);
		getContentPane().setLayout(null);

        comboBoxEventos = new JComboBox();
        comboBoxEventos.setBounds(12, 41, 188, 43);
        
        ControladorEvento c = ControladorEvento.getInstance();
        
        for (String e: c.listarEventos()) {
        	comboBoxEventos.addItem(e);
        }
        getContentPane().add(comboBoxEventos);
        
        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(220, 42, 121, 37);
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        //lblNombre.setBounds(100, 100, 170, 14);
        getContentPane().add(lblNombre);
        textNombre.setEditable(false);
        textNombre.setBounds(359, 42, 255, 37);
        getContentPane().add(textNombre);
        textNombre.setColumns(10);
        
        comboBoxEventos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consulta(e);
            }
        });
        
        JLabel lblSigla = new JLabel("Sigla:");
        lblSigla.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSigla.setBounds(220, 91, 121, 37);
        getContentPane().add(lblSigla);
        
        textSigla = new JTextField();
        textSigla.setEditable(false);
        textSigla.setColumns(10);
        textSigla.setBounds(359, 91, 255, 37);
        getContentPane().add(textSigla);
        
        JLabel lblDescripcin = new JLabel("Descripción:");
        lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescripcin.setBounds(220, 140, 121, 37);
        getContentPane().add(lblDescripcin);
        
        lblFecha = new JLabel("Fecha:");
        lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFecha.setBounds(220, 221, 121, 37);
        getContentPane().add(lblFecha);
        
        textFecha = new JTextField();
        textFecha.setEditable(false);
        textFecha.setColumns(10);
        textFecha.setBounds(359, 221, 255, 37);
        getContentPane().add(textFecha);
        
        lblCategoras = new JLabel("Categorías: ");
        lblCategoras.setHorizontalAlignment(SwingConstants.LEFT);
        lblCategoras.setBounds(27, 261, 121, 37);
        getContentPane().add(lblCategoras);
        
        lblEdiciones = new JLabel("Ediciones: ");
        lblEdiciones.setHorizontalAlignment(SwingConstants.LEFT);
        lblEdiciones.setBounds(342, 261, 121, 37);
        getContentPane().add(lblEdiciones);
        
        comboBoxCategorias = new JComboBox();
        comboBoxCategorias.setBounds(27, 310, 287, 43);
        getContentPane().add(comboBoxCategorias);
        
        comboBoxEdiciones = new JComboBox();
        comboBoxEdiciones.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String si = (String) comboBoxEdiciones.getSelectedItem();
        		if ( si != null ) llamarVentanaEdicion(e);
        	};
        });
        comboBoxEdiciones.setBounds(342, 310, 287, 43);
        
        getContentPane().add(comboBoxEdiciones);
        
        textDesc = new JTextArea();
        textDesc.setEditable(false);
        textDesc.setWrapStyleWord(true);
        textDesc.setLineWrap(true);
        textDesc.setBounds(359, 140, 255, 67);
        getContentPane().add(textDesc);
        
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(268, 394, 105, 27);
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	dispose();
            }
        });
        getContentPane().add(btnCerrar);
	}

	protected void consulta(ActionEvent e) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ControladorEvento c = ControladorEvento.getInstance();
		
		DTEvento evento = c.informacionEvento((String) comboBoxEventos.getSelectedItem());
        textNombre.setText(evento.getNombre());
        textSigla.setText(evento.getSigla());
        textDesc.setText(evento.getDescripcion());
        textFecha.setText(evento.getFechaAlta().format(formatter));
        comboBoxCategorias.removeAllItems();
        for (String cat: evento.getCategorias()) {
        	comboBoxCategorias.addItem(cat);
        }
        comboBoxEdiciones.removeAllItems();
        comboBoxEdiciones.addItem("Seleccione una Edicion");
        for (String ed: evento.getEdiciones()) {
        	comboBoxEdiciones.addItem(ed);
        }
    }
	
	
	
	public void llamarVentanaEdicion(ActionEvent e) {           	
		String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
		String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
		if (edicionSeleccionada != null && !edicionSeleccionada.equals("Seleccione una Edicion") 
				&& eventoSeleccionado != null && !edicionSeleccionada.equals("Seleccione un Evento")) {
			
			JInternalFrame c = new ConsultaEdicionDeEvento(desktopPane, eventoSeleccionado, edicionSeleccionada);
			desktopPane.add(c);
	        centerInternalFrame(c);
	        c.setVisible(true);
	        c.toFront();
		}
	}
	
	// Método para centrar las ventanas internas
    private void centerInternalFrame(JInternalFrame frame) {
        if (desktopPane != null) {
            Dimension desktopSize = desktopPane.getSize();
            Dimension frameSize = frame.getSize();
            if (frameSize.getWidth() > 0 && frameSize.getHeight() > 0) {
                int x = (desktopSize.width - frameSize.width) / 2;
                int y = (desktopSize.height - frameSize.height) / 2;
                frame.setLocation(x, y);
            }
        }
    }
}
