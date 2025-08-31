package presentacion;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import logica.ControladorEvento;
import logica.ControladorInstitucion;
import logica.DTPatrocinio;
import logica.Patrocinio;
import logica.TipoNivelPatrocinio;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AltaPatrocinio extends JInternalFrame{
	
	private final JTextField txtAporte = new JTextField();
	private JComboBox comboBoxEdiciones;
	private JComboBox comboBoxInstituciones;
	private JComboBox comboBoxEventos;
	private JButton botonCancelar;
	private JButton botonCrear;
	private JTextField textCantRegistros;
	private JLabel labelCantRegistros;
	private JLabel labelTipoRegistros;
	private JLabel labelPatrocinio;
	private JComboBox comboBoxNivelPatrocinio;
	private JComboBox comboBoxTipoRegistro;
	private JLabel labelAporte;
	private JLabel labelDatosDelUsuario;
	private JTextField textField;
	private JLabel labelCodigo;
	private JTextField textCodigo;
	
	public AltaPatrocinio() {
		
		setVisible(true);
		setSize(300, 200);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta de Patrocinio");
		setBounds(30, 30, 833, 430);
		getContentPane().setLayout(null);
		
		//DATOS DE LA IZQ
		
		labelDatosDelUsuario = new JLabel("Datos del Patrocinio");
		labelDatosDelUsuario.setFont(new Font("Dialog", Font.BOLD, 16));
		labelDatosDelUsuario.setBounds(12, 0, 271, 80);
		labelDatosDelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(labelDatosDelUsuario);
        
		comboBoxEventos = new JComboBox();
        comboBoxEventos.setModel(new DefaultComboBoxModel(new String[] {"Selecciona un Evento", "Evento 1", "Evento 2"}));
        comboBoxEventos.setBounds(27, 92, 226, 43);
        getContentPane().add(comboBoxEventos);        
        inicializarCombos();
        
        comboBoxEdiciones = new JComboBox();
        comboBoxEdiciones.setModel(new DefaultComboBoxModel(new String[] {"Selecciona una edición"}));
        comboBoxEdiciones.setBounds(27, 155, 226, 43);
        getContentPane().add(comboBoxEdiciones);
        
        comboBoxInstituciones = new JComboBox();
        comboBoxInstituciones.setModel(new DefaultComboBoxModel(new String[] {"Selecciona una Institución"}));
        comboBoxInstituciones.setBounds(27, 221, 226, 43);
        getContentPane().add(comboBoxInstituciones);
		
        
        //DATOS DE LA DERECHA
  
        labelAporte = new JLabel("Aporte Económico:");
        labelAporte.setBounds(386, 74, 139, 37);
        labelAporte.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(labelAporte);
        
        txtAporte.setBounds(536, 74, 226, 37);
        getContentPane().add(txtAporte);
        txtAporte.setColumns(10);
                           
        comboBoxTipoRegistro = new JComboBox();
        comboBoxTipoRegistro.setModel(new DefaultComboBoxModel(new String[] {"Selecciona un Tipo de Registro"}));
        comboBoxTipoRegistro.setBounds(536, 172, 226, 43);
        getContentPane().add(comboBoxTipoRegistro);
        
        comboBoxNivelPatrocinio = new JComboBox();
        comboBoxNivelPatrocinio.setModel(new DefaultComboBoxModel(new String[] {"Platino", "Oro", "Plata", "Bronce"}));
        comboBoxNivelPatrocinio.setBounds(536, 22, 226, 43);
        getContentPane().add(comboBoxNivelPatrocinio);
        
        labelPatrocinio = new JLabel("Nivel Patrocinio:");
        labelPatrocinio.setHorizontalAlignment(SwingConstants.RIGHT);
        labelPatrocinio.setBounds(397, 25, 121, 37);
        getContentPane().add(labelPatrocinio);
        
        labelTipoRegistros = new JLabel("Tipo de Registros Gratutitos:");
        labelTipoRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
        labelTipoRegistros.setBounds(309, 175, 216, 37);
        getContentPane().add(labelTipoRegistros);
        
        labelCantRegistros = new JLabel("Cantidad de Registros Gratuitos:");
        labelCantRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
        labelCantRegistros.setBounds(291, 123, 234, 37);
        getContentPane().add(labelCantRegistros);
        
        textCantRegistros = new JTextField();
        textCantRegistros.setColumns(10);
        textCantRegistros.setBounds(536, 123, 226, 37);
        getContentPane().add(textCantRegistros);
        
        botonCrear = new JButton("Crear");
        botonCrear.setBounds(336, 314, 188, 37);
        getContentPane().add(botonCrear);
        
        botonCancelar = new JButton("Cancelar");
        botonCancelar.setBounds(556, 314, 188, 37);
        getContentPane().add(botonCancelar);
        
        labelCodigo = new JLabel("Codigo:");
        labelCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
        labelCodigo.setBounds(309, 224, 216, 37);
        getContentPane().add(labelCodigo);
        
        textCodigo = new JTextField();
        textCodigo.setColumns(10);
        textCodigo.setBounds(536, 227, 226, 37);
        getContentPane().add(textCodigo);
		
        
        //FUNCIONES
        
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
                    m.listarEdiciones(eventoSeleccionado).forEach(edicionesModel::addElement);
                    comboBoxEdiciones.setModel(edicionesModel);
                }
             
            }
        });
        
        comboBoxEdiciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la edicion seleccionada
                String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
                
                // Verificar que la selección sea válida
                if (edicionSeleccionada != null && !edicionSeleccionada.equals("Seleccione un Evento")) {
                    // Llamar al método que carga las instituciones
                	DefaultComboBoxModel<String> institucionesModel = new DefaultComboBoxModel<>();
                    institucionesModel.addElement("Seleccione una Institucion");
                    
                    ControladorInstitucion m = ControladorInstitucion.getInstance();
                    m.listarInstituciones().forEach(institucionesModel::addElement);
                    comboBoxInstituciones.setModel(institucionesModel);
                }
             
            }
        });
        
        botonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos seleccionados
                String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
                String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
                String institucionSeleccionada = (String) comboBoxInstituciones.getSelectedItem();
                String tipoRegistroSeleccionado = (String) comboBoxTipoRegistro.getSelectedItem();
                String nivelPatrocinioSeleccionado = (String) comboBoxNivelPatrocinio.getSelectedItem();
                String aporteEconomicoSeleccionado = (String) txtAporte.getText();
                String cantRegistrosSeleccionados = (String) textCantRegistros.getText();
                String codigoSeleccionado = (String) textCodigo.getText();
                
                // Transformar los datos seleccionados
                TipoNivelPatrocinio tipoNivel = TipoNivelPatrocinio.valueOf(nivelPatrocinioSeleccionado);
                float montoAporte = Float.parseFloat(aporteEconomicoSeleccionado);
                int cantidadRegistros = Integer.parseInt(cantRegistrosSeleccionados);
                
                
                // Crear el DTPatrocinio
                DTPatrocinio dtp = new DTPatrocinio(codigoSeleccionado, tipoNivel, montoAporte, tipoRegistroSeleccionado, cantidadRegistros, LocalDate.now());
                
                // Crear el Patrocinio
                ControladorEvento me = ControladorEvento.getInstance();
                me.crearPatrocinio(dtp, edicionSeleccionada, institucionSeleccionada, eventoSeleccionado);
                
                //Mostrar mensaje 
                JOptionPane.showMessageDialog(AltaPatrocinio.this, "Se creó el patrocinio: " + codigoSeleccionado);
           }
          
        });
 
	}
	
	
	private void inicializarCombos() {
        DefaultComboBoxModel<String> eventosModel = new DefaultComboBoxModel<>();
        eventosModel.addElement("Seleccione un Evento");
        
        ControladorEvento m = ControladorEvento.getInstance();
        m.listarEventos().forEach(eventosModel::addElement);
        comboBoxEventos.setModel(eventosModel);
    }
}
