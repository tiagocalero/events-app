
package presentacion;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import logica.ControladorEvento;
import logica.ControladorUsuario;
import logica.DTAsistente;
import logica.DTOrganizador;
import logica.DTEdicion;
import logica.DTRegistro;
import logica.DTUsuario;
import logica.TipoNivelPatrocinio;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ConsultaRegistro extends JInternalFrame {
	private JLabel lblRegistro;
	private JLabel lblFecha;
	private final JTextField textFecha = new JTextField();
	private JTextField textNombre;
	private JTextField textCosto;
	private JComboBox comboBoxUsuarios;
	private JComboBox comboBoxRegistros;
	private JButton btnCerrar;

	private String RegEdicion;
	private String RegTipo;
	private String RegFecha;
	private float RegCosto;
	private JTextField textTipo;
	private JLabel lblTipo;
	
	public ConsultaRegistro() {
		initComponents();
	}
	
	public ConsultaRegistro(String usuario, String registro) {
        initComponents();
        cargarDatosIniciales(usuario, registro);
    }
	
	public void initComponents() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar un Registro");
		setBounds(30, 30, 833, 349);
		getContentPane().setLayout(null);
		
		
		comboBoxUsuarios = new JComboBox();
		comboBoxUsuarios.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un usuario"}));
		comboBoxUsuarios.setBounds(27, 60, 263, 43);
        ControladorUsuario c = ControladorUsuario.getInstance();
        for (String e: c.listarUsuarios()) {
        	comboBoxUsuarios.addItem(e);
        }
        getContentPane().add(comboBoxUsuarios);
        
        comboBoxRegistros = new JComboBox();
        comboBoxRegistros.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un registro"}));
        comboBoxRegistros.setBounds(27, 137, 263, 43);
        getContentPane().add(comboBoxRegistros);
        
		lblRegistro = new JLabel("Datos del Registro");
		lblRegistro.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRegistro.setBounds(309, 6, 453, 60);
        lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblRegistro);
        inicializarCombos();
        
        lblFecha = new JLabel("Fecha del Registro:");
        lblFecha.setBounds(385, 113, 139, 37);
        lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        //lblNombre.setBounds(100, 100, 170, 14);
        getContentPane().add(lblFecha);
        textFecha.setEditable(false);
        textFecha.setBounds(535, 113, 226, 37);
        getContentPane().add(textFecha);
        textFecha.setColumns(10);
        
        JLabel lblNombreEdicion = new JLabel("Nombre de la Edicion:");
        lblNombreEdicion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombreEdicion.setBounds(339, 63, 185, 37);
        getContentPane().add(lblNombreEdicion);
        
        JLabel lblCosto = new JLabel("Costo del Registro:");
        lblCosto.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCosto.setBounds(308, 163, 216, 37);
        getContentPane().add(lblCosto);
        
        textNombre = new JTextField();
        textNombre.setEditable(false);
        textNombre.setColumns(10);
        textNombre.setBounds(535, 63, 226, 37);
        getContentPane().add(textNombre);
        
        textCosto = new JTextField();
        textCosto.setEditable(false);
        textCosto.setColumns(10);
        textCosto.setBounds(535, 163, 226, 37);
        getContentPane().add(textCosto);
        
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(358, 270, 105, 27);
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	dispose();
            }
        });
        getContentPane().add(btnCerrar);
        
        textTipo = new JTextField();
        textTipo.setEditable(false);
        textTipo.setColumns(10);
        textTipo.setBounds(536, 212, 226, 37);
        getContentPane().add(textTipo);
        
        lblTipo = new JLabel("Tipo del Registro:");
        lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipo.setBounds(308, 212, 216, 37);
        getContentPane().add(lblTipo);
        
        
        comboBoxUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                // Obtener el usuario seleccionado
                String usuarioSeleccionado = (String) comboBoxUsuarios.getSelectedItem();
                
                // Verificar que la selección sea válida
                if (usuarioSeleccionado != null && !usuarioSeleccionado.equals("Seleccione un usuario")) {
                	
                	// Llamar al método que carga los registros
                	DefaultComboBoxModel<String> registrosModel = new DefaultComboBoxModel<>();
                    registrosModel.addElement("Seleccione un registro");
                    
                    ControladorUsuario m = ControladorUsuario.getInstance();
                    for (DTRegistro reg : m.registrosDeAsistente(usuarioSeleccionado)) {
            	    	/*RegEdicion = reg.getEdicion();
            	    	RegFecha = reg.getFecha().format(fmt);
            	    	RegCosto = reg.getCosto();
            	    	RegTipo = reg.getTipo();
            	    	
                        registrosModel.addElement("<html>" + RegEdicion + "<br>" + RegFecha + " $" + RegCosto + "</html>"); */
                    	registrosModel.addElement(reg.getEdicion());
                    }
                    comboBoxRegistros.setModel(registrosModel);
                }
             
            }
        });
        
        comboBoxRegistros.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String usuarioSeleccionado = (String) comboBoxUsuarios.getSelectedItem();
        		String registroSeleccionado = (String) comboBoxRegistros.getSelectedItem();
        		if (usuarioSeleccionado != null && !usuarioSeleccionado.equals("Seleccione un usuario")) {
        			if (registroSeleccionado != null && !registroSeleccionado.equals("Seleccione un registro")) {
                        ControladorUsuario c = ControladorUsuario.getInstance();
                        DTRegistro dtr = c.datosRegistroUsuario(registroSeleccionado, usuarioSeleccionado);
                        if (dtr != null) {
                        	textNombre.setText(dtr.getEdicion());
                        	textCosto.setText("$" + Float.toString(dtr.getCosto()));
                        	textFecha.setText(String.valueOf(dtr.getFecha()));
                    		textTipo.setText(dtr.getTipo());
                        }
        			}
        		}
        	}
        });
        
	}
	
	private void cargarDatosIniciales(String usuario, String registro) {
        if (usuario != null) {
        	comboBoxUsuarios.setSelectedItem(usuario);
            comboBoxRegistros.setSelectedItem(registro);
        }
    }
	
	private void inicializarCombos() {
        DefaultComboBoxModel<String> eventosModel = new DefaultComboBoxModel<>();
        eventosModel.addElement("Seleccione un usuario");
        
        ControladorUsuario m = ControladorUsuario.getInstance();
        m.listarNicknameAsistentes().forEach(eventosModel::addElement);
        
        comboBoxUsuarios.setModel(eventosModel); 
    }
}