
package presentacion;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
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
import java.awt.Font;

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
	
	public ConsultaPatrocinio() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consulta Patrocinio");
		setBounds(30, 30, 833, 349);
		getContentPane().setLayout(null);
		
		lblEventos = new JLabel("Datos del Patrocinio");
		lblEventos.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEventos.setBounds(309, 6, 453, 60);
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblEventos);
                
        comboBoxEventos = new JComboBox();
        comboBoxEventos.setBounds(27, 53, 226, 43);
        inicializarCombos();
        getContentPane().add(comboBoxEventos);
        
        lblNombre = new JLabel("Aporte Económico:");
        lblNombre.setBounds(385, 113, 139, 37);
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        //lblNombre.setBounds(100, 100, 170, 14);
        getContentPane().add(lblNombre);
        textAporteEconomico.setEditable(false);
        textAporteEconomico.setBounds(535, 113, 226, 37);
        getContentPane().add(textAporteEconomico);
        textAporteEconomico.setColumns(10);
        
        comboBoxEdiciones = new JComboBox();
        comboBoxEdiciones.setBounds(27, 108, 226, 43);
        getContentPane().add(comboBoxEdiciones);
        
        comboBoxPatrocinios = new JComboBox();
        comboBoxPatrocinios.setBounds(27, 163, 226, 43);
        getContentPane().add(comboBoxPatrocinios);
        
        JLabel lblNivelPatrocinio = new JLabel("Nivel Patrocinio:");
        lblNivelPatrocinio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNivelPatrocinio.setBounds(403, 63, 121, 37);
        getContentPane().add(lblNivelPatrocinio);
        
        JLabel lblTipoDeRegistros = new JLabel("Tipo de Registro Gratutito:");
        lblTipoDeRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipoDeRegistros.setBounds(308, 163, 216, 37);
        getContentPane().add(lblTipoDeRegistros);
        
        JLabel lblNombre_1 = new JLabel("Cantidad de Registros Gratuitos:");
        lblNombre_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre_1.setBounds(290, 213, 234, 37);
        getContentPane().add(lblNombre_1);
        
        textCantRegistros = new JTextField();
        textCantRegistros.setEditable(false);
        textCantRegistros.setColumns(10);
        textCantRegistros.setBounds(535, 213, 226, 37);
        getContentPane().add(textCantRegistros);
        
        textNivelPatrocinio = new JTextField();
        textNivelPatrocinio.setEditable(false);
        textNivelPatrocinio.setColumns(10);
        textNivelPatrocinio.setBounds(535, 63, 226, 37);
        getContentPane().add(textNivelPatrocinio);
        
        textTRegistros = new JTextField();
        textTRegistros.setEditable(false);
        textTRegistros.setColumns(10);
        textTRegistros.setBounds(535, 163, 226, 37);
        getContentPane().add(textTRegistros);

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
            m.elegirEvento(eventoSeleccionado).forEach(edicionesModel::addElement);
            comboBoxEdiciones.setModel(edicionesModel);
        }
	}
	private void edicionSeleccionada() {
        // Obtener la edicion seleccionada
        String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
        
        // Verificar que la selección sea válida
        if (edicionSeleccionada != null && !edicionSeleccionada.equals("Seleccione una Edicion")) {
        	DefaultComboBoxModel<String> patrociniosModel = new DefaultComboBoxModel<>();
        	patrociniosModel.addElement("Seleccione un Patrocinio");

        	// Agregar patrocinios
            ControladorEvento m = ControladorEvento.getInstance();
            m.elegirEdicion(edicionSeleccionada);
            m.listarPatrocinios().forEach(patrociniosModel::addElement);
            comboBoxPatrocinios.setModel(patrociniosModel);
        }
	}
	
	private void patrocinioSeleccionado() {
        // Obtener el evento seleccionado
        String patrocinioSeleccionado = (String) comboBoxPatrocinios.getSelectedItem();
        
        // Verificar que la selección sea válida
        if (patrocinioSeleccionado != null && !patrocinioSeleccionado.equals("Seleccione un Patrocinio")) {
        	// Mostrar datos de patrocinio
            ControladorEvento m = ControladorEvento.getInstance();
            DTPatrocinio dtp = m.consultaPatrocinio(patrocinioSeleccionado);
            
            // Convertir TipoNivelPatrocinio a String
            switch (dtp.getTipo()) {
            case Plata:
            	textNivelPatrocinio.setText("Plata");
            	break;
            case Platino:
            	textNivelPatrocinio.setText("Platino");
            	break;
            case Oro:
            	textNivelPatrocinio.setText("Oro");
            	break;
            case Bronce:
            	textNivelPatrocinio.setText("Bronce");
            	break;
            }
            
            textAporteEconomico.setText(String.valueOf(dtp.getMonto()));
            textTRegistros.setText(dtp.getTipoRegistro());
            textCantRegistros.setText(String.valueOf(dtp.getCantRegistros()));
        }
	}
}