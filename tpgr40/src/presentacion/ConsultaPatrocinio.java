
package presentacion;

import javax.swing.JComboBox;
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
	private JComboBox comboBoxPatrocinios;
	private JTextField textCantRegistros;
	private JTextField textNivelPatrocinio;
	private JTextField textTRegistros;
	private JTextField textFechaAlta;
	private JButton btnCerrar;
	
	public ConsultaPatrocinio() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar un Patrocinio");
		setBounds(30, 30, 814, 425);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblEventos = new JLabel("Datos del Patrocinio");
		lblEventos.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEventos.setBounds(309, 6, 453, 60);
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblEventos);
                
        comboBoxEventos = new JComboBox();
        comboBoxEventos.setBounds(27, 53, 226, 43);
        inicializarCombos();
        panel.add(comboBoxEventos);
        
        lblNombre = new JLabel("Aporte Económico:");
        lblNombre.setBounds(385, 113, 139, 37);
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        //lblNombre.setBounds(100, 100, 170, 14);
        panel.add(lblNombre);
        textAporteEconomico.setEditable(false);
        textAporteEconomico.setBounds(535, 113, 226, 37);
        panel.add(textAporteEconomico);
        textAporteEconomico.setColumns(10);
        
        comboBoxEdiciones = new JComboBox();
        comboBoxEdiciones.setBounds(27, 108, 226, 43);
        panel.add(comboBoxEdiciones);
        
        comboBoxPatrocinios = new JComboBox();
        comboBoxPatrocinios.setBounds(27, 163, 226, 43);
        panel.add(comboBoxPatrocinios);
        
        JLabel lblNivelPatrocinio = new JLabel("Nivel Patrocinio:");
        lblNivelPatrocinio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNivelPatrocinio.setBounds(403, 63, 121, 37);
        panel.add(lblNivelPatrocinio);
        
        JLabel lblTipoDeRegistros = new JLabel("Tipo de Registro Gratutito:");
        lblTipoDeRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipoDeRegistros.setBounds(308, 163, 216, 37);
        panel.add(lblTipoDeRegistros);
        
        JLabel lblNombre_1 = new JLabel("Cantidad de Registros Gratuitos:");
        lblNombre_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre_1.setBounds(290, 213, 234, 37);
        panel.add(lblNombre_1);
        
        textCantRegistros = new JTextField();
        textCantRegistros.setEditable(false);
        textCantRegistros.setColumns(10);
        textCantRegistros.setBounds(535, 213, 226, 37);
        panel.add(textCantRegistros);
        
        textNivelPatrocinio = new JTextField();
        textNivelPatrocinio.setEditable(false);
        textNivelPatrocinio.setColumns(10);
        textNivelPatrocinio.setBounds(535, 63, 226, 37);
        panel.add(textNivelPatrocinio);
        
        textTRegistros = new JTextField();
        textTRegistros.setEditable(false);
        textTRegistros.setColumns(10);
        textTRegistros.setBounds(535, 163, 226, 37);
        panel.add(textTRegistros);
        
        JLabel lblNombre_1_1 = new JLabel("Fecha de Alta:");
        lblNombre_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre_1_1.setBounds(291, 263, 234, 37);
        panel.add(lblNombre_1_1);
        
        textFechaAlta = new JTextField();
        textFechaAlta.setEditable(false);
        textFechaAlta.setColumns(10);
        textFechaAlta.setBounds(536, 263, 226, 37);
        panel.add(textFechaAlta);

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

        comboBoxEventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { eventoSeleccionado(); }
        });

        comboBoxEdiciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { edicionSeleccionada(); }
        });

        comboBoxPatrocinios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { patrocinioSeleccionado(); }
        });
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
            edicionesModel.addElement("Seleccione una Edicion");
            
            ControladorEvento m = ControladorEvento.getInstance();
            m.listarEdiciones(eventoSeleccionado).forEach(edicionesModel::addElement);
            comboBoxEdiciones.setModel(edicionesModel);
        } else {
        	// Si se selecciono "Seleccione un Evento", no mostrar ediciones
        	comboBoxEdiciones.setModel(new DefaultComboBoxModel<>());
        }
        // Vaciar patrocinios y campos (si ya se habían seleccionado de antes)
        comboBoxPatrocinios.setModel(new DefaultComboBoxModel<>());
        borrarCampos();
	}
	private void edicionSeleccionada() {
        // Obtener la edicion seleccionada
        String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
        String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
        
        // Verificar que la selección sea válida
        if (edicionSeleccionada != null && !edicionSeleccionada.equals("Seleccione una Edicion")) {
        	DefaultComboBoxModel<String> patrociniosModel = new DefaultComboBoxModel<>();
        	patrociniosModel.addElement("Seleccione un Patrocinio");

        	// Agregar patrocinios
            ControladorEvento m = ControladorEvento.getInstance();
            m.listarPatrocinios(eventoSeleccionado, edicionSeleccionada).forEach(patrociniosModel::addElement);
            comboBoxPatrocinios.setModel(patrociniosModel);
        } else {
        	// Si se selecciono "Seleccione una Edicion", no mostrar patrocinios
            comboBoxPatrocinios.setModel(new DefaultComboBoxModel<>());
        }
    	borrarCampos();
	}
	
	private void patrocinioSeleccionado() {
        // Obtener el evento seleccionado
        String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
        String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
        String patrocinioSeleccionado = (String) comboBoxPatrocinios.getSelectedItem();
        
        // Verificar que la selección sea válida
        if (patrocinioSeleccionado != null && !patrocinioSeleccionado.equals("Seleccione un Patrocinio")) {
        	// Mostrar datos de patrocinio
            ControladorEvento m = ControladorEvento.getInstance();
            DTPatrocinio dtp = m.consultaPatrocinio(eventoSeleccionado, edicionSeleccionada, patrocinioSeleccionado);
            
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
		textAporteEconomico.setText("");
		textAporteEconomico.setText("");
		textCantRegistros.setText("");
		textFechaAlta.setText("");
		textNivelPatrocinio.setText("");
		textTRegistros.setText("");
	}
}