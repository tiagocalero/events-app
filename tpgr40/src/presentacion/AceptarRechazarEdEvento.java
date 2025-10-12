package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import logica.ControladorEvento;
import logica.DTPatrocinio;
import logica.DTTRegistro;
import logica.TipoNivelPatrocinio;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logica.ControladorEvento;
import logica.DTEdicion;
import logica.DTPatrocinio;
import logica.Edicion;
import logica.TipoNivelPatrocinio;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;

public class AceptarRechazarEdEvento extends JInternalFrame {

	private JInternalFrame frmConsultaEventos;
	private JLabel lblEventos;
	private JComboBox comboBoxEventos;
	private JComboBox comboBoxEdiciones;
	private JLabel lblSeleccioneUnEvento;
	private JDesktopPane desktopPane;
	private JPanel panel;
	
	
	/**
	 * @wbp.parser.constructor
	 */
	public AceptarRechazarEdEvento(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
        initComponents();
	}
	
	public AceptarRechazarEdEvento(JDesktopPane desktopPane, String evento, String edicion) {
		this.desktopPane = desktopPane;
		initComponents();
        cargarDatosIniciales(evento, edicion);
    }
	
	public void initComponents() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consulta Edicion de Evento");
		setBounds(30, 30, 700, 600);
		getContentPane().setLayout(null);
		setSize(851, 201);
		
		lblEventos = new JLabel("Eventos:");
		lblEventos.setBounds(34, 52, 107, 36);
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblEventos);

        comboBoxEventos = new JComboBox();
        comboBoxEventos.addItem("Seleccione un Evento");
        comboBoxEventos.setBounds(135, 49, 226, 43);
        
        ControladorEvento c = ControladorEvento.getInstance();
        
        for (String e: c.listarEventos()) {
        	comboBoxEventos.addItem(e);
        }
        getContentPane().add(comboBoxEventos);
        
        comboBoxEdiciones = new JComboBox();
        comboBoxEdiciones.setBounds(539, 49, 226, 43);
        getContentPane().add(comboBoxEdiciones);
        
        JLabel lblEdicionesDelEvento = new JLabel("Ediciones del Evento:");
        lblEdicionesDelEvento.setHorizontalAlignment(SwingConstants.CENTER);
        lblEdicionesDelEvento.setBounds(386, 52, 158, 36);
        getContentPane().add(lblEdicionesDelEvento);
        
        lblSeleccioneUnEvento = new JLabel("Seleccione un Evento y una Edición");
        lblSeleccioneUnEvento.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeleccioneUnEvento.setFont(new Font("Dialog", Font.BOLD, 18));
        lblSeleccioneUnEvento.setBounds(112, 6, 616, 36);
        getContentPane().add(lblSeleccioneUnEvento);
        
        panel = new JPanel();
        panel.setBounds(0, 115, 843, 36);
        getContentPane().add(panel);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	dispose();
            }
        });
        panel.add(btnCerrar);
        
        JButton btnAmpliarDetalles = new JButton("Ampliar Detalles");
        btnAmpliarDetalles.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String si = (String) comboBoxEdiciones.getSelectedItem();
        		if ( si != null ) llamarVentanaEdicion(e);
        	};
        });
        panel.add(btnAmpliarDetalles);
        
        JButton btnRechazar = new JButton("Rechazar");
        btnRechazar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String ev = (String) comboBoxEventos.getSelectedItem();
            	String ed = (String) comboBoxEdiciones.getSelectedItem();
            	ControladorEvento c = ControladorEvento.getInstance();

            	if (ev != null && !ev.equals("Seleccione un Evento")
            		&& ed != null && !ed.equals("Seleccione una Edicion")) {
                	c.rechazarEdicion(ev, ed);
                   	DefaultComboBoxModel<String> edicionesModel = new DefaultComboBoxModel<>();
                    edicionesModel.addElement("Seleccione una Edicion");
                    
                    c.listarEdicionesIngresadas(ev).forEach(edicionesModel::addElement);
                    comboBoxEdiciones.setModel(edicionesModel);
                	JOptionPane.showMessageDialog(frmConsultaEventos, ed + " ha sido Rechazada correctamente.");
                } else {
                	JOptionPane.showMessageDialog(AceptarRechazarEdEvento.this, "Debe seleccionar una edición y un evento", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(btnRechazar);
        
        JButton btnConfirmar = new JButton("Aceptar");
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String ev = (String) comboBoxEventos.getSelectedItem();
            	String ed = (String) comboBoxEdiciones.getSelectedItem();
            	ControladorEvento c = ControladorEvento.getInstance();
            	
            	if (ev != null && !ev.equals("Seleccione un Evento")
            		&& ed != null && !ed.equals("Seleccione una Edicion")) {
                	c.confirmarEdicion(ev, ed);
                   	DefaultComboBoxModel<String> edicionesModel = new DefaultComboBoxModel<>();
                    edicionesModel.addElement("Seleccione una Edicion");
                    
                    c.listarEdicionesIngresadas(ev).forEach(edicionesModel::addElement);
                    comboBoxEdiciones.setModel(edicionesModel);
                    JOptionPane.showMessageDialog(frmConsultaEventos, ed + " ha sido Confirmada correctamente.");
                } else {
                	JOptionPane.showMessageDialog(AceptarRechazarEdEvento.this, "Debe seleccionar una edición y un evento", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(btnConfirmar);
        
        
        
        comboBoxEventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Obtener el evento seleccionado
                String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
                
                // Verificar que la selección sea válida
                if (eventoSeleccionado != null && !eventoSeleccionado.equals("Seleccione un Evento")) {
                    // Llamar al método que carga las ediciones
                	DefaultComboBoxModel<String> edicionesModel = new DefaultComboBoxModel<>();
                    edicionesModel.addElement("Seleccione una Edicion");
                    
                    ControladorEvento m = ControladorEvento.getInstance();
                    m.listarEdicionesIngresadas(eventoSeleccionado).forEach(edicionesModel::addElement);
                    comboBoxEdiciones.setModel(edicionesModel);
                } else {
                	DefaultComboBoxModel<String> edicionesModel = new DefaultComboBoxModel<>();
                	comboBoxEdiciones.setModel(edicionesModel);
                }
             
            }
        });
        
        
        
        comboBoxEdiciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	            	
                // Obtener los datos seleccionados
            	String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
            	String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
            	
            	if (edicionSeleccionada != null && !edicionSeleccionada.equals("Seleccione una Edicion")) {
           		
            		// Obtener la edicion
            		ControladorEvento c = ControladorEvento.getInstance();
            		DTEdicion DTedicion = c.obtenerEdicion(edicionSeleccionada, eventoSeleccionado);           		
            		            		        			
            	}
           }
          
        });
	}
	
	
	private void cargarDatosIniciales(String evento, String edicion) {
        if (edicion != null) {
        	comboBoxEventos.setSelectedItem(evento);
            comboBoxEdiciones.setSelectedItem(edicion);
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
		} else {
        	JOptionPane.showMessageDialog(AceptarRechazarEdEvento.this, "Debe seleccionar una edición y un evento", "Error", JOptionPane.ERROR_MESSAGE);
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
