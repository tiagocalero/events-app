
package presentacion;

import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import logica.ControladorEvento;
import logica.DTPatrocinio;
import logica.TipoNivelPatrocinio;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Font;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConsultaPatrocinio extends JInternalFrame {
	private JLabel lblEventos;
	private JComboBox comboBoxEventos;
	private JComboBox comboBoxEdiciones;
	private JLabel lblNombre;
	private final JTextField textAporteEconomico = new JTextField();
	private JComboBox comboBoxInstituciones;
	private JTextField textCantRegistros;
	private JTextField textNivelPatrocinio;
	private JTextField textTRegistros;
	private JTextField textFechaAlta;
	private JButton btnCerrar;
	private JDesktopPane desktopPane;
	private JLabel libCodigo;
	private JTextField textCodigo;

	
	public ConsultaPatrocinio(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
        initComponents();
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public ConsultaPatrocinio(String evento, String edicion, String patrocinio) {
        initComponents();
        cargarDatosIniciales(evento, edicion, patrocinio);
    }
	
	public void initComponents() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar un Patrocinio");
		setBounds(30, 30, 814, 454);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
        comboBoxEventos = new JComboBox();
        comboBoxEventos.setBounds(27, 53, 226, 43);
        panel.add(comboBoxEventos);
        
                comboBoxEventos.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { eventoSeleccionado(); }
                });
		
		comboBoxEdiciones = new JComboBox();
		comboBoxEdiciones.setBounds(27, 108, 226, 43);
		panel.add(comboBoxEdiciones);
		
		        comboBoxEdiciones.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) { edicionSeleccionada(); }
		        });
		
		comboBoxInstituciones = new JComboBox();
		comboBoxInstituciones.setBounds(27, 163, 226, 43);
		panel.add(comboBoxInstituciones);
		
		        comboBoxInstituciones.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) { institucionSeleccionada(); }
		        });
		
		lblEventos = new JLabel("Datos del Patrocinio");
		lblEventos.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEventos.setBounds(309, 6, 453, 60);
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblEventos);
        inicializarCombos();
        
        JLabel lblNivelPatrocinio = new JLabel("Nivel Patrocinio:");
        lblNivelPatrocinio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNivelPatrocinio.setBounds(403, 108, 121, 37);
        panel.add(lblNivelPatrocinio);
        
        textNivelPatrocinio = new JTextField();
        textNivelPatrocinio.setEditable(false);
        textNivelPatrocinio.setColumns(10);
        textNivelPatrocinio.setBounds(535, 108, 226, 37);
        panel.add(textNivelPatrocinio);
        
        lblNombre = new JLabel("Aporte Económico:");
        lblNombre.setBounds(385, 158, 139, 37);
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        //lblNombre.setBounds(100, 100, 170, 14);
        panel.add(lblNombre);
        textAporteEconomico.setEditable(false);
        textAporteEconomico.setBounds(535, 158, 226, 37);
        panel.add(textAporteEconomico);
        textAporteEconomico.setColumns(10);
        
        JLabel lblTipoDeRegistros = new JLabel("Tipo de Registro Gratutito:");
        lblTipoDeRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipoDeRegistros.setBounds(308, 208, 216, 37);
        panel.add(lblTipoDeRegistros);
        
        textTRegistros = new JTextField();
        textTRegistros.setEditable(false);
        textTRegistros.setColumns(10);
        textTRegistros.setBounds(535, 208, 226, 37);
        panel.add(textTRegistros);
        
        JLabel lblNombre_1 = new JLabel("Cantidad de Registros Gratuitos:");
        lblNombre_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre_1.setBounds(290, 258, 234, 37);
        panel.add(lblNombre_1);
        
        textCantRegistros = new JTextField();
        textCantRegistros.setEditable(false);
        textCantRegistros.setColumns(10);
        textCantRegistros.setBounds(535, 258, 226, 37);
        panel.add(textCantRegistros);
        
        JLabel lblNombre_1_1 = new JLabel("Fecha de Alta:");
        lblNombre_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre_1_1.setBounds(291, 308, 234, 37);
        panel.add(lblNombre_1_1);
        
        textFechaAlta = new JTextField();
        textFechaAlta.setEditable(false);
        textFechaAlta.setColumns(10);
        textFechaAlta.setBounds(536, 308, 226, 37);
        panel.add(textFechaAlta);
        
        libCodigo = new JLabel("Código:");
        libCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
        libCodigo.setBounds(403, 59, 121, 37);
        panel.add(libCodigo);
        
        textCodigo = new JTextField();
        textCodigo.setEditable(false);
        textCodigo.setColumns(10);
        textCodigo.setBounds(535, 59, 226, 37);
        panel.add(textCodigo);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
        
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(352, 346, 105, 27);
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	dispose();
            }
        });
        panel_1.add(btnCerrar);
	}
	
	private void inicializarCombos() {
        DefaultComboBoxModel<String> eventosModel = new DefaultComboBoxModel<>();
        eventosModel.addElement("Seleccione un Evento");
        
        ControladorEvento m = ControladorEvento.getInstance();
        m.listarEventos().forEach(eventosModel::addElement);
        comboBoxEventos.setModel(eventosModel);
    }
	
	private void eventoSeleccionado() {
        // Obtener el evento seleccionado
        String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
        
        // Verificar que la selección sea válida
        if (eventoSeleccionado != null && !eventoSeleccionado.equals("Seleccione un Evento")) {
            // Llamar al método que carga las ediciones
        	DefaultComboBoxModel<String> edicionesModel = new DefaultComboBoxModel<>();
            edicionesModel.addElement("Seleccione una Edición");
            
            ControladorEvento m = ControladorEvento.getInstance();
            m.listarEdiciones(eventoSeleccionado).forEach(edicionesModel::addElement);
            comboBoxEdiciones.setModel(edicionesModel);
        } else {
        	// Si se selecciono "Seleccione un Evento", no mostrar ediciones
        	comboBoxEdiciones.setModel(new DefaultComboBoxModel<>());
        }
        // Vaciar patrocinios y campos (si ya se habían seleccionado de antes)
        comboBoxInstituciones.setModel(new DefaultComboBoxModel<>());
        borrarCampos();
	}
	private void edicionSeleccionada() {
        // Obtener la edicion seleccionada
        String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
        String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
        
        // Verificar que la selección sea válida
        if (edicionSeleccionada != null && !edicionSeleccionada.equals("Seleccione una Edición")) {
        	DefaultComboBoxModel<String> patrociniosModel = new DefaultComboBoxModel<>();
        	patrociniosModel.addElement("Seleccione una Institución");

        	// Agregar patrocinios
            ControladorEvento m = ControladorEvento.getInstance();
            m.listarInstituciones(eventoSeleccionado, edicionSeleccionada).forEach(patrociniosModel::addElement);
            comboBoxInstituciones.setModel(patrociniosModel);
        } else {
        	// Si se selecciono "Seleccione una Edicion", no mostrar patrocinios
            comboBoxInstituciones.setModel(new DefaultComboBoxModel<>());
        }
    	borrarCampos();
	}
	
	private void institucionSeleccionada() {
        // Obtener el evento seleccionado
        String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
        String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
        String institucionSeleccionada = (String) comboBoxInstituciones.getSelectedItem();
        
        // Verificar que la selección sea válida
        if (institucionSeleccionada != null && !institucionSeleccionada.equals("Seleccione una Institución")) {
        	// Mostrar datos de patrocinio
            ControladorEvento m = ControladorEvento.getInstance();
            DTPatrocinio dtp = m.consultaPatrocinioInstitucion(eventoSeleccionado, edicionSeleccionada, institucionSeleccionada);
            
            textCodigo.setText(dtp.getCodigo());
            textNivelPatrocinio.setText(dtp.getTipo().toString());
            textAporteEconomico.setText("$" + String.valueOf(dtp.getMonto()));
            textTRegistros.setText(dtp.getTipoRegistro());
            textCantRegistros.setText(String.valueOf(dtp.getCantRegistros()));
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            textFechaAlta.setText(dtp.getFechaAlta().format(fmt));
        } else {
        	// Si se selecciono "Seleccione un Patrocinio", borrar campos
        	borrarCampos();
        }
	}
	
	private void borrarCampos() {
		textCodigo.setText("");
		textAporteEconomico.setText("");
		textAporteEconomico.setText("");
		textCantRegistros.setText("");
		textFechaAlta.setText("");
		textNivelPatrocinio.setText("");
		textTRegistros.setText("");
	}
	
	private void cargarDatosIniciales(String evento, String edicion, String patrocinio) {
        if (edicion != null) {
        	comboBoxEventos.setSelectedItem(evento);
            comboBoxEdiciones.setSelectedItem(edicion);
            comboBoxInstituciones.setSelectedItem(patrocinio);
        }
    }
}