
package presentacion;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import logica.ControladorEvento;
import logica.ControladorUsuario;
import logica.DTEdicion;
import logica.DTRegistro;
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

public class ConsultaRegistro extends JInternalFrame {
	private JLabel lblRegistro;
	private JLabel lblFecha;
	private final JTextField textFecha = new JTextField();
	private JTextField textNombre;
	private JTextField textCosto;
	private JComboBox comboBoxUsuarios;
	private JComboBox comboBoxRegistros;
	
	public ConsultaRegistro() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consulta Patrocinio");
		setBounds(30, 30, 833, 349);
		getContentPane().setLayout(null);
		
		
		comboBoxUsuarios = new JComboBox();
		comboBoxUsuarios.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un usuario"}));
		comboBoxUsuarios.setBounds(27, 60, 226, 43);
        getContentPane().add(comboBoxUsuarios);
        
        comboBoxRegistros = new JComboBox();
        comboBoxRegistros.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un registro"}));
        comboBoxRegistros.setBounds(27, 137, 226, 43);
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
        
        
        comboBoxUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el usuario seleccionado
                String usuarioSeleccionado = (String) comboBoxUsuarios.getSelectedItem();
                
                // Verificar que la selección sea válida
                if (usuarioSeleccionado != null && !usuarioSeleccionado.equals("Seleccione un usuario")) {
                    
                	// Llamar al método que carga los registros
                	DefaultComboBoxModel<String> edicionesModel = new DefaultComboBoxModel<>();
                    edicionesModel.addElement("Seleccione una Edicion");
                    
                    ControladorUsuario m = ControladorUsuario.getInstance();
                    for (DTRegistro registro : m.registrosDeAsistente(usuarioSeleccionado)) {
                        edicionesModel.addElement(registro.getEdicion()); // 
                    }
                }
             
            }
        });
        
        comboBoxRegistros.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String usuarioSeleccionado = (String) comboBoxUsuarios.getSelectedItem();
        		String registroSeleccionado = (String) comboBoxRegistros.getSelectedItem();
                
        		ControladorUsuario m = ControladorUsuario.getInstance();
        		DTRegistro dtr = m.datosRegistroUsuario(registroSeleccionado, usuarioSeleccionado);
        		
        		textNombre.setText(dtr.getEdicion());
        		textCosto.setText(String.valueOf(dtr.getCosto()));
        		textFecha.setText(dtr.getFecha().toString());
        		
        	}
        });     
        
	}
	
	private void inicializarCombos() {
        DefaultComboBoxModel<String> eventosModel = new DefaultComboBoxModel<>();
        eventosModel.addElement("Seleccione un usuario");
        
        ControladorUsuario m = ControladorUsuario.getInstance();
        m.listarNicknameAsistentes().forEach(eventosModel::addElement);
        
        comboBoxUsuarios.setModel(eventosModel); 
    }
}