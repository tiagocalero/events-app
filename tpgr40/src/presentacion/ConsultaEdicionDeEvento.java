package presentacion;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logica.ControladorEvento;
import logica.DTEdicion;

public class ConsultaEdicionDeEvento extends JInternalFrame {
	private static final long serialVersionUID = -5008014210806948016L;
	private JLabel lblEventos;
	private JComboBox<String> comboBoxEventos;
	private JComboBox<String> comboBoxEdiciones;
	private JComboBox<String> comboBoxAsistentes;
	private JComboBox<String> comboBoxPatrocinios;
	private JComboBox<String> comboBoxTipoRegistros;
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
	private JDesktopPane desktopPane;
	
	
	/**
	 * @wbp.parser.constructor
	 */
	public ConsultaEdicionDeEvento(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
        initComponents();
	}
	
	public ConsultaEdicionDeEvento(JDesktopPane desktopPane, String evento, String edicion) {
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
		setSize(851, 569);
		
		lblEventos = new JLabel("Eventos:");
		lblEventos.setBounds(34, 52, 107, 36);
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblEventos);

        comboBoxEventos = new JComboBox<String>();
        comboBoxEventos.addItem("Seleccione un Evento");
        comboBoxEventos.setBounds(135, 49, 226, 43);
        
        ControladorEvento controlador = ControladorEvento.getInstance();
        
        for (String e: controlador.listarEventos()) {
        	comboBoxEventos.addItem(e);
        }
        getContentPane().add(comboBoxEventos);
        
        lblPais = new JLabel("Pais:");
        lblPais.setBounds(12, 152, 96, 37);
        lblPais.setHorizontalAlignment(SwingConstants.RIGHT);
        //lblNombre.setBounds(100, 100, 170, 14);
        getContentPane().add(lblPais);
        txtPais.setEditable(false);
        txtPais.setBounds(115, 152, 255, 37);
        getContentPane().add(txtPais);
        txtPais.setColumns(10);
        
        comboBoxEdiciones = new JComboBox<String>();
        comboBoxEdiciones.setBounds(539, 49, 226, 43);
        getContentPane().add(comboBoxEdiciones);
        
        JLabel lblEdicionesDelEvento = new JLabel("Ediciones del Evento:");
        lblEdicionesDelEvento.setHorizontalAlignment(SwingConstants.CENTER);
        lblEdicionesDelEvento.setBounds(386, 52, 158, 36);
        getContentPane().add(lblEdicionesDelEvento);
        
        lblCiudad = new JLabel("Ciudad:");
        lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCiudad.setBounds(12, 201, 96, 37);
        getContentPane().add(lblCiudad);
        
        txtCiudad = new JTextField();
        txtCiudad.setEditable(false);
        txtCiudad.setColumns(10);
        txtCiudad.setBounds(115, 201, 255, 37);
        getContentPane().add(txtCiudad);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre.setBounds(12, 250, 96, 37);
        getContentPane().add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setEditable(false);
        txtNombre.setColumns(10);
        txtNombre.setBounds(115, 250, 255, 37);
        getContentPane().add(txtNombre);
        
        JLabel lblSigla = new JLabel("Sigla:");
        lblSigla.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSigla.setBounds(12, 299, 96, 37);
        getContentPane().add(lblSigla);
        
        txtSigla = new JTextField();
        txtSigla.setEditable(false);
        txtSigla.setColumns(10);
        txtSigla.setBounds(115, 299, 255, 37);
        getContentPane().add(txtSigla);
        
        JLabel lblNombre_1_1_1_1 = new JLabel("Organizador:");
        lblNombre_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre_1_1_1_1.setBounds(12, 348, 96, 37);
        getContentPane().add(lblNombre_1_1_1_1);
        
        txtOrganizador = new JTextField();
        txtOrganizador.setEditable(false);
        txtOrganizador.setColumns(10);
        txtOrganizador.setBounds(115, 348, 255, 37);
        getContentPane().add(txtOrganizador);
        
        comboBoxAsistentes = new JComboBox<String>();
        comboBoxAsistentes.setBounds(115, 395, 255, 43);
        getContentPane().add(comboBoxAsistentes);
        
        comboBoxPatrocinios = new JComboBox<String>();
        comboBoxPatrocinios.setBounds(554, 333, 255, 36);
        getContentPane().add(comboBoxPatrocinios);
        
        JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
        lblFechaInicio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaInicio.setBounds(448, 152, 96, 37);
        getContentPane().add(lblFechaInicio);
        
        JLabel lblFechaFin = new JLabel("Fecha Fin:");
        lblFechaFin.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaFin.setBounds(448, 201, 96, 37);
        getContentPane().add(lblFechaFin);
        
        JLabel lblFechaAlta = new JLabel("Fecha Alta:");
        lblFechaAlta.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaAlta.setBounds(448, 250, 96, 37);
        getContentPane().add(lblFechaAlta);
        
        txtFechaInicio = new JTextField();
        txtFechaInicio.setEditable(false);
        txtFechaInicio.setColumns(10);
        txtFechaInicio.setBounds(554, 152, 255, 37);
        getContentPane().add(txtFechaInicio);
        
        txtFechaFin = new JTextField();
        txtFechaFin.setEditable(false);
        txtFechaFin.setColumns(10);
        txtFechaFin.setBounds(554, 201, 255, 37);
        getContentPane().add(txtFechaFin);
        
        txtFechaAlta = new JTextField();
        txtFechaAlta.setEditable(false);
        txtFechaAlta.setColumns(10);
        txtFechaAlta.setBounds(554, 250, 255, 37);
        getContentPane().add(txtFechaAlta);
        
        comboBoxTipoRegistros = new JComboBox<String>();
        comboBoxTipoRegistros.setBounds(554, 414, 255, 36);
        getContentPane().add(comboBoxTipoRegistros);
        
        lblAsistentes = new JLabel("Asistentes:");
        lblAsistentes.setHorizontalAlignment(SwingConstants.CENTER);
        lblAsistentes.setBounds(34, 395, 96, 37);
        getContentPane().add(lblAsistentes);
        
        lblPatrocinios = new JLabel("Patrocinios de la edición:");
        lblPatrocinios.setHorizontalAlignment(SwingConstants.LEFT);
        lblPatrocinios.setBounds(458, 299, 255, 37);
        getContentPane().add(lblPatrocinios);
        
        lblTipoRegistros = new JLabel("Tipo de Registros de la edición:");
        lblTipoRegistros.setHorizontalAlignment(SwingConstants.LEFT);
        lblTipoRegistros.setBounds(458, 380, 255, 37);
        getContentPane().add(lblTipoRegistros);
        
        lblDatos = new JLabel("Datos de la Edición");
        lblDatos.setFont(new Font("Dialog", Font.BOLD, 18));
        lblDatos.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatos.setBounds(27, 104, 226, 36);
        getContentPane().add(lblDatos);
        
        lblSeleccioneUnEvento = new JLabel("Seleccione un Evento y una Edición");
        lblSeleccioneUnEvento.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeleccioneUnEvento.setFont(new Font("Dialog", Font.BOLD, 18));
        lblSeleccioneUnEvento.setBounds(112, 6, 616, 36);
        getContentPane().add(lblSeleccioneUnEvento);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(353, 489, 139, 29);
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	setVisible(false);
            	dispose();
            }
        });
        getContentPane().add(btnCerrar);
        
        
        comboBoxEventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	borrarCamposEvento();
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
            	borrarCamposEdicion();
            	
                // Obtener los datos seleccionados
            	String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
            	String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
            	
            	if (edicionSeleccionada != null && !edicionSeleccionada.equals("Seleccione una Edicion")) {
           		
            		// Obtener la edicion
            		ControladorEvento c = ControladorEvento.getInstance();
            		DTEdicion DTedicion = c.obtenerEdicion(edicionSeleccionada, eventoSeleccionado);           		
            		
            		DefaultComboBoxModel<String> asistentesModel = new DefaultComboBoxModel<>();
                    asistentesModel.addElement("Seleccione una Asistente");
                    DTedicion.getAsistentes().forEach(asistentesModel::addElement);
                    comboBoxAsistentes.setModel(asistentesModel);
                    
                    DefaultComboBoxModel<String> patrociniosModel = new DefaultComboBoxModel<>();

                    patrociniosModel.addElement("Seleccione una Institución");
                    DTedicion.getInstitucionesPatrocinadoras().forEach(patrociniosModel::addElement);

                    comboBoxPatrocinios.setModel(patrociniosModel);
                    
                    DefaultComboBoxModel<String> tipoRegistrosModel = new DefaultComboBoxModel<>();
                    tipoRegistrosModel.addElement("Seleccione un Tipo Registro");
                    c.listarTipoRegistro(eventoSeleccionado, edicionSeleccionada).forEach(tipoRegistrosModel::addElement);
                    comboBoxTipoRegistros.setModel(tipoRegistrosModel);
        		
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
        			/*
        			comboBoxAsistentes.removeAllItems();
        			for (String as: DTedicion.getAsistentes())
        				comboBoxAsistentes.addItem(as);
                
        			comboBoxPatrocinios.removeAllItems();
        			for (String pat: DTedicion.getPatrocinio())
        				comboBoxPatrocinios.addItem(pat);
        		
        			comboBoxTipoRegistros.removeAllItems();
        			for (String tr: c.listarTipoRegistro(eventoSeleccionado, edicionSeleccionada))
        				comboBoxTipoRegistros.addItem(tr);
        			
        			comboBoxAsistentes.setSelectedItem("Seleccione un Asistente");
        			comboBoxPatrocinios.setSelectedItem("Seleccione un Patrocinio");
        			comboBoxTipoRegistros.setSelectedItem("Seleccione un Tipo Registro");
        			*/
        			
            	}
           }
          
        });
        
        comboBoxPatrocinios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		llamarVentanaPatrocinio(e);           
           }
        });
        
        comboBoxTipoRegistros.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		llamarVentanaTRegistro(e);        
        	}
        });
	}
	
	
	private void cargarDatosIniciales(String evento, String edicion) {
        if (edicion != null) {
        	comboBoxEventos.setSelectedItem(evento);
            comboBoxEdiciones.setSelectedItem(edicion);
        }
    }
	
	public void llamarVentanaTRegistro(ActionEvent e) {           	
		String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
		String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
		String tRegistroSeleccionado = (String) comboBoxTipoRegistros.getSelectedItem();
		if (edicionSeleccionada != null && !edicionSeleccionada.equals("Seleccione una Edicion") 
				&& eventoSeleccionado != null && !edicionSeleccionada.equals("Seleccione un Evento") 
				&& tRegistroSeleccionado != null && !tRegistroSeleccionado.equals("Seleccione un Tipo Registro")) {
			
			JInternalFrame c = new ConsultaTipoRegistro(eventoSeleccionado, edicionSeleccionada, tRegistroSeleccionado);
			desktopPane.add(c);
	        centerInternalFrame(c);
	        c.setVisible(true);
	        c.toFront();
		}
	}
	
	public void llamarVentanaPatrocinio(ActionEvent e) {
		String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
		String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
		String patrocinioSeleecionado = (String) comboBoxPatrocinios.getSelectedItem();
		if (edicionSeleccionada != null && !edicionSeleccionada.equals("Seleccione una Edicion") 
				&& eventoSeleccionado != null && !edicionSeleccionada.equals("Seleccione un Evento") 
				&& patrocinioSeleecionado != null && !patrocinioSeleecionado.equals("Seleccione una Institución")) {
			
			JInternalFrame c = new ConsultaPatrocinio(eventoSeleccionado, edicionSeleccionada, patrocinioSeleecionado);
			desktopPane.add(c);
	        centerInternalFrame(c);
	        c.setVisible(true);
	        c.toFront();
		}
	}
	
	
	private void borrarCamposEvento() {

		txtPais.setText("");
		txtCiudad.setText("");
		txtNombre.setText("");
		txtSigla.setText("");
		txtOrganizador.setText("");
		txtFechaInicio.setText("");
		txtFechaFin.setText("");
		txtFechaAlta.setText("");
		
		comboBoxEdiciones.removeAllItems();;
		comboBoxAsistentes.removeAllItems();
		comboBoxPatrocinios.removeAllItems();
		comboBoxTipoRegistros.removeAllItems();
		
	}
	private void borrarCamposEdicion() {

		txtPais.setText("");
		txtCiudad.setText("");
		txtNombre.setText("");
		txtSigla.setText("");
		txtOrganizador.setText("");
		txtFechaInicio.setText("");
		txtFechaFin.setText("");
		txtFechaAlta.setText("");
		

		comboBoxAsistentes.removeAllItems();
		comboBoxPatrocinios.removeAllItems();
		comboBoxTipoRegistros.removeAllItems();
		
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
