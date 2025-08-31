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

import java.awt.Font;

public class ConsultaEdicionDeEvento extends JInternalFrame {

	private JInternalFrame frmConsultaEventos;
	private JLabel lblEventos;
	private JComboBox comboBoxEventos;
	private JLabel lblPais;
	private final JTextField txtPais = new JTextField();
	private JLabel lblCiudad;
	private JTextField txtCiudad;
	private JTextField txtNombre;
	private JTextField txtSigla;
	private JTextField txtOrganizador;
	private JTextField txtFechaInicio;
	private JTextField txtFechaFin;
	private JTextField txtFechaAlta;
	private JLabel lblAsistentes;
	private JLabel lblPatrocinios;
	private JLabel lblTipoRegistros;
	private JLabel lblDatos;
	private JLabel lblSeleccioneUnEvento;
	private JLabel lblAporte;
	private JTextField textAporte;
	private JLabel lblNivelPatrocinio;
	private JLabel lblTipoDeRegistros;
	private JTextField textCantRegistros;
	private JTextField textNivelPatrocinio;
	private JTextField textTipoRegistro;
	private JLabel lblDatosDelPatrocinio;
	private JLabel lblCantRegistros;
	private JLabel lblDatosDelTipo;
	private JTextField textNombreRegistro;
	private JLabel lblNombre_1;
	private JLabel lblDescripcion;
	private JTextField textDescRegistro;
	private JTextField textCupoRegistro;
	private JLabel lblCupo_1;
	private JTextField textCostoRegistro;
	private JLabel lblCupo;
	
	public ConsultaEdicionDeEvento() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consulta Edicion de Evento");
		setBounds(30, 30, 700, 600);
		getContentPane().setLayout(null);
		setSize(1130, 900);
		
		lblEventos = new JLabel("Eventos:");
		lblEventos.setBounds(12, 56, 107, 36);
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblEventos);

        comboBoxEventos = new JComboBox();
        comboBoxEventos.addItem("Seleccione un Evento");
        comboBoxEventos.setBounds(100, 53, 188, 43);
        
        ControladorEvento c = ControladorEvento.getInstance();
        
        for (String e: c.listarEventos()) {
        	comboBoxEventos.addItem(e);
        }
        getContentPane().add(comboBoxEventos);
        
        lblPais = new JLabel("Pais:");
        lblPais.setBounds(12, 152, 96, 37);
        lblPais.setHorizontalAlignment(SwingConstants.CENTER);
        //lblNombre.setBounds(100, 100, 170, 14);
        getContentPane().add(lblPais);
        txtPais.setEditable(false);
        txtPais.setBounds(115, 152, 255, 37);
        getContentPane().add(txtPais);
        txtPais.setColumns(10);
        
        JComboBox comboBoxEdiciones = new JComboBox();
        comboBoxEdiciones.setBounds(461, 53, 188, 43);
        getContentPane().add(comboBoxEdiciones);
        
        JLabel lblEdicionesDelEvento = new JLabel("Ediciones del Evento:");
        lblEdicionesDelEvento.setHorizontalAlignment(SwingConstants.CENTER);
        lblEdicionesDelEvento.setBounds(313, 56, 158, 36);
        getContentPane().add(lblEdicionesDelEvento);
        
        lblCiudad = new JLabel("Ciudad:");
        lblCiudad.setHorizontalAlignment(SwingConstants.CENTER);
        lblCiudad.setBounds(12, 201, 96, 37);
        getContentPane().add(lblCiudad);
        
        txtCiudad = new JTextField();
        txtCiudad.setEditable(false);
        txtCiudad.setColumns(10);
        txtCiudad.setBounds(115, 201, 255, 37);
        getContentPane().add(txtCiudad);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre.setBounds(12, 250, 96, 37);
        getContentPane().add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setEditable(false);
        txtNombre.setColumns(10);
        txtNombre.setBounds(115, 250, 255, 37);
        getContentPane().add(txtNombre);
        
        JLabel lblSigla = new JLabel("Sigla:");
        lblSigla.setHorizontalAlignment(SwingConstants.CENTER);
        lblSigla.setBounds(12, 299, 96, 37);
        getContentPane().add(lblSigla);
        
        txtSigla = new JTextField();
        txtSigla.setEditable(false);
        txtSigla.setColumns(10);
        txtSigla.setBounds(115, 299, 255, 37);
        getContentPane().add(txtSigla);
        
        JLabel lblNombre_1_1_1_1 = new JLabel("Organizador:");
        lblNombre_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre_1_1_1_1.setBounds(12, 348, 96, 37);
        getContentPane().add(lblNombre_1_1_1_1);
        
        txtOrganizador = new JTextField();
        txtOrganizador.setEditable(false);
        txtOrganizador.setColumns(10);
        txtOrganizador.setBounds(115, 348, 255, 37);
        getContentPane().add(txtOrganizador);
        
        JComboBox comboBoxAsistentes = new JComboBox();
        comboBoxAsistentes.setBounds(415, 198, 188, 43);
        getContentPane().add(comboBoxAsistentes);
        
        JComboBox comboBoxPatrocinios = new JComboBox();
        comboBoxPatrocinios.setBounds(415, 296, 188, 43);
        getContentPane().add(comboBoxPatrocinios);
        
        JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
        lblFechaInicio.setHorizontalAlignment(SwingConstants.CENTER);
        lblFechaInicio.setBounds(12, 397, 96, 37);
        getContentPane().add(lblFechaInicio);
        
        JLabel lblFechaFin = new JLabel("Fecha Fin:");
        lblFechaFin.setHorizontalAlignment(SwingConstants.CENTER);
        lblFechaFin.setBounds(12, 446, 96, 37);
        getContentPane().add(lblFechaFin);
        
        JLabel lblFechaAlta = new JLabel("Fecha Alta:");
        lblFechaAlta.setHorizontalAlignment(SwingConstants.CENTER);
        lblFechaAlta.setBounds(12, 495, 96, 37);
        getContentPane().add(lblFechaAlta);
        
        txtFechaInicio = new JTextField();
        txtFechaInicio.setEditable(false);
        txtFechaInicio.setColumns(10);
        txtFechaInicio.setBounds(115, 397, 255, 37);
        getContentPane().add(txtFechaInicio);
        
        txtFechaFin = new JTextField();
        txtFechaFin.setEditable(false);
        txtFechaFin.setColumns(10);
        txtFechaFin.setBounds(115, 446, 255, 37);
        getContentPane().add(txtFechaFin);
        
        txtFechaAlta = new JTextField();
        txtFechaAlta.setEditable(false);
        txtFechaAlta.setColumns(10);
        txtFechaAlta.setBounds(115, 495, 255, 37);
        getContentPane().add(txtFechaAlta);
        
        JComboBox comboBoxTipoRegistros = new JComboBox();
        comboBoxTipoRegistros.setBounds(415, 589, 188, 43);
        getContentPane().add(comboBoxTipoRegistros);
        
        lblAsistentes = new JLabel("Asistentes:");
        lblAsistentes.setHorizontalAlignment(SwingConstants.CENTER);
        lblAsistentes.setBounds(415, 152, 96, 37);
        getContentPane().add(lblAsistentes);
        
        lblPatrocinios = new JLabel("Patrocinios:");
        lblPatrocinios.setHorizontalAlignment(SwingConstants.CENTER);
        lblPatrocinios.setBounds(415, 250, 96, 37);
        getContentPane().add(lblPatrocinios);
        
        lblTipoRegistros = new JLabel("Tipo de Registros:");
        lblTipoRegistros.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipoRegistros.setBounds(415, 543, 121, 37);
        getContentPane().add(lblTipoRegistros);
        
        lblDatos = new JLabel("Datos de la Edicion");
        lblDatos.setFont(new Font("Dialog", Font.BOLD, 18));
        lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
        lblDatos.setBounds(27, 104, 188, 36);
        getContentPane().add(lblDatos);
        
        lblSeleccioneUnEvento = new JLabel("Seleccione un Evento y una Edicion");
        lblSeleccioneUnEvento.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeleccioneUnEvento.setFont(new Font("Dialog", Font.BOLD, 18));
        lblSeleccioneUnEvento.setBounds(27, 8, 343, 36);
        getContentPane().add(lblSeleccioneUnEvento);
        
        lblAporte = new JLabel("Aporte Económico:");
        lblAporte.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAporte.setBounds(722, 395, 139, 37);
        getContentPane().add(lblAporte);
        
        textAporte = new JTextField();
        textAporte.setEditable(false);
        textAporte.setColumns(10);
        textAporte.setBounds(872, 395, 226, 37);
        getContentPane().add(textAporte);
        
        lblNivelPatrocinio = new JLabel("Nivel Patrocinio:");
        lblNivelPatrocinio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNivelPatrocinio.setBounds(740, 345, 121, 37);
        getContentPane().add(lblNivelPatrocinio);
        
        lblTipoDeRegistros = new JLabel("Tipo de Registro Gratutito:");
        lblTipoDeRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipoDeRegistros.setBounds(645, 446, 216, 37);
        getContentPane().add(lblTipoDeRegistros);
        
        textCantRegistros = new JTextField();
        textCantRegistros.setEditable(false);
        textCantRegistros.setColumns(10);
        textCantRegistros.setBounds(872, 495, 226, 37);
        getContentPane().add(textCantRegistros);
        
        textNivelPatrocinio = new JTextField();
        textNivelPatrocinio.setEditable(false);
        textNivelPatrocinio.setColumns(10);
        textNivelPatrocinio.setBounds(872, 345, 226, 37);
        getContentPane().add(textNivelPatrocinio);
        
        textTipoRegistro = new JTextField();
        textTipoRegistro.setEditable(false);
        textTipoRegistro.setColumns(10);
        textTipoRegistro.setBounds(872, 445, 226, 37);
        getContentPane().add(textTipoRegistro);
        
        lblDatosDelPatrocinio = new JLabel("Datos del Patrocinio");
        lblDatosDelPatrocinio.setHorizontalAlignment(SwingConstants.CENTER);
        lblDatosDelPatrocinio.setFont(new Font("Dialog", Font.BOLD, 18));
        lblDatosDelPatrocinio.setBounds(740, 300, 188, 36);
        getContentPane().add(lblDatosDelPatrocinio);
        
        lblCantRegistros = new JLabel("Cantidad de Registros Gratuitos:");
        lblCantRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCantRegistros.setBounds(627, 495, 234, 37);
        getContentPane().add(lblCantRegistros);
        
        lblDatosDelTipo = new JLabel("Datos del Tipo de Registro");
        lblDatosDelTipo.setHorizontalAlignment(SwingConstants.CENTER);
        lblDatosDelTipo.setFont(new Font("Dialog", Font.BOLD, 18));
        lblDatosDelTipo.setBounds(722, 590, 255, 36);
        getContentPane().add(lblDatosDelTipo);
        
        textNombreRegistro = new JTextField();
        textNombreRegistro.setEditable(false);
        textNombreRegistro.setColumns(10);
        textNombreRegistro.setBounds(872, 638, 226, 37);
        getContentPane().add(textNombreRegistro);
        
        lblNombre_1 = new JLabel("Nombre:");
        lblNombre_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre_1.setBounds(627, 638, 234, 37);
        getContentPane().add(lblNombre_1);
        
        lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescripcion.setBounds(627, 687, 234, 37);
        getContentPane().add(lblDescripcion);
        
        textDescRegistro = new JTextField();
        textDescRegistro.setEditable(false);
        textDescRegistro.setColumns(10);
        textDescRegistro.setBounds(872, 687, 226, 37);
        getContentPane().add(textDescRegistro);
        
        textCupoRegistro = new JTextField();
        textCupoRegistro.setEditable(false);
        textCupoRegistro.setColumns(10);
        textCupoRegistro.setBounds(872, 736, 226, 37);
        getContentPane().add(textCupoRegistro);
        
        lblCupo_1 = new JLabel("Cupo:");
        lblCupo_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCupo_1.setBounds(627, 736, 234, 37);
        getContentPane().add(lblCupo_1);
        
        textCostoRegistro = new JTextField();
        textCostoRegistro.setEditable(false);
        textCostoRegistro.setColumns(10);
        textCostoRegistro.setBounds(872, 785, 226, 37);
        getContentPane().add(textCostoRegistro);
        
        lblCupo = new JLabel("Costo:");
        lblCupo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCupo.setBounds(627, 785, 234, 37);
        getContentPane().add(lblCupo);
        
        
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
                // Obtener los datos seleccionados
            	String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
            	String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
            	
            	// Obtener la edicion
            	ControladorEvento c = ControladorEvento.getInstance();       	
            	Edicion instEdicion = c.obtenerEdicion(edicionSeleccionada, eventoSeleccionado);
            	
        		//Crear el datatype
        		DTEdicion DTedicion = c.elegirEdicion(instEdicion);  
        		
        		//Actualizar los datos en el GUI
        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        		
        		txtPais.setText(DTedicion.getPais());
        		txtCiudad.setText(DTedicion.getCiudad());
        		txtNombre.setText(DTedicion.getNombre());
        		txtSigla.setText(DTedicion.getSigla());
        		txtOrganizador.setText(DTedicion.getOrganizador());
        		txtFechaInicio.setText(DTedicion.getFechaInicio().format(formatter));
        		txtFechaFin.setText(DTedicion.getFechaFin().format(formatter));
        		txtFechaAlta.setText(DTedicion.getFechaAlta().format(formatter));
        		
                comboBoxAsistentes.removeAllItems();
                for (String as: DTedicion.getAsistentes())
                	comboBoxAsistentes.addItem(as);
                
                comboBoxPatrocinios.removeAllItems();
                for (String pat: DTedicion.getPatrocinio())
                	comboBoxPatrocinios.addItem(pat);
                
                comboBoxTipoRegistros.removeAllItems();
                //for (String tr: DTedicion.getTipoRegistros())
                //	comboBoxTipoRegistros.addItem(tr);
                for (String tr: c.listarTipoRegistro(eventoSeleccionado, edicionSeleccionada))
                	comboBoxTipoRegistros.addItem(tr);
           }
          
        });
        
        comboBoxPatrocinios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Obtener el evento seleccionado
            	String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
            	String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
                String patrocinioSeleccionado = (String) comboBoxPatrocinios.getSelectedItem();
                
                // Verificar que la selección sea válida
                if (patrocinioSeleccionado != null && !patrocinioSeleccionado.equals("Seleccione un Patrocinio")) {
                    ControladorEvento m = ControladorEvento.getInstance();
                    DTPatrocinio dtp = m.consultaPatrocinio(eventoSeleccionado, edicionSeleccionada, patrocinioSeleccionado);
                    
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
                    
                    textAporte.setText(String.valueOf(dtp.getMonto()));
                    textTipoRegistro.setText(dtp.getTipoRegistro());
                    textCantRegistros.setText(String.valueOf(dtp.getCantRegistros()));
                }    
           }
        });
        
        comboBoxTipoRegistros.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        			String registroSeleccionado = (String) comboBoxTipoRegistros.getSelectedItem();
        			String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
        			String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
        			
        			ControladorEvento m = ControladorEvento.getInstance();
        			DTTRegistro registro = m.seleccionarTipoRegistro(registroSeleccionado, eventoSeleccionado, edicionSeleccionada);
            
        			// Mostramos los datos en los campos de texto
        			textNombreRegistro.setText(registro.getNombre());
        			textDescRegistro.setText(registro.getDescripcion());
        			textCostoRegistro.setText("$" + String.valueOf(registro.getCosto()));
        			textCupoRegistro.setText(String.valueOf(registro.getCupo()));
           
        	}
        }
        );
	}
}
