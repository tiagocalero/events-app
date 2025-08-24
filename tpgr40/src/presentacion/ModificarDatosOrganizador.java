package presentacion;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ModificarDatosOrganizador {
	private JInternalFrame frmConsultaEventos;
	private JLabel lblEventos;
	private JComboBox comboBoxEventos;
	private JLabel lblNombre;
	private JButton btnNewButton;
	private final JTextField txtEjemplo = new JTextField();
	private JTextField txtCc;
	private JLabel lblFecha;
	private JTextField textField;
	private JTextField textField_1;
	
	public ModificarDatosOrganizador() {
		
		frmConsultaEventos = new JInternalFrame();
		frmConsultaEventos.setResizable(true);
		frmConsultaEventos.setIconifiable(true);
		frmConsultaEventos.setMaximizable(true);
		frmConsultaEventos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmConsultaEventos.setClosable(true);
		frmConsultaEventos.setTitle("Modificar Datos de Usuario");
		frmConsultaEventos.setBounds(30, 30, 700, 653);
		frmConsultaEventos.getContentPane().setLayout(null);
		
		lblEventos = new JLabel("Datos del Usuario");
		lblEventos.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEventos.setBounds(374, 0, 255, 60);
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        frmConsultaEventos.getContentPane().add(lblEventos);
        
                
                comboBoxEventos = new JComboBox();
                comboBoxEventos.setModel(new DefaultComboBoxModel(new String[] {"Selecciona un Usuario", "Usuario 1", "Usuario 2"}));
                comboBoxEventos.setBounds(27, 53, 188, 43);
                
        comboBoxEventos.addItem("Selecciona un Evento");
        comboBoxEventos.addItem("Evento 1");
        comboBoxEventos.addItem("Evento 2");
        frmConsultaEventos.getContentPane().add(comboBoxEventos);
        
        lblNombre = new JLabel("Nickname:");
        lblNombre.setBounds(235, 54, 121, 37);
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        //lblNombre.setBounds(100, 100, 170, 14);
        frmConsultaEventos.getContentPane().add(lblNombre);
        txtEjemplo.setEditable(false);
        txtEjemplo.setBounds(374, 54, 255, 37);
        frmConsultaEventos.getContentPane().add(txtEjemplo);
        txtEjemplo.setColumns(10);
        
        btnNewButton = new JButton("Seleccionar");
        btnNewButton.setBounds(27, 114, 188, 37);
        frmConsultaEventos.getContentPane().add(btnNewButton);
        
        JLabel lblSigla = new JLabel("Nombre:");
        lblSigla.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSigla.setBounds(235, 103, 121, 37);
        frmConsultaEventos.getContentPane().add(lblSigla);
        
        txtCc = new JTextField();
        txtCc.setColumns(10);
        txtCc.setBounds(374, 103, 255, 37);
        frmConsultaEventos.getContentPane().add(txtCc);
        
        JLabel lblDescripcin = new JLabel("Correo:");
        lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescripcin.setBounds(235, 152, 121, 37);
        frmConsultaEventos.getContentPane().add(lblDescripcin);
        
        lblFecha = new JLabel("Descripción General:");
        lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFecha.setBounds(168, 250, 188, 37);
        frmConsultaEventos.getContentPane().add(lblFecha);
        
        textField = new JTextField();
        textField.setEditable(false);
        textField.setColumns(10);
        textField.setBounds(374, 152, 255, 37);
        frmConsultaEventos.getContentPane().add(textField);
        
        JLabel lblApellido = new JLabel("Sitio Web:");
        lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        lblApellido.setBounds(235, 201, 121, 37);
        frmConsultaEventos.getContentPane().add(lblApellido);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(374, 201, 255, 37);
        frmConsultaEventos.getContentPane().add(textField_1);
        
        JButton btnNewButton_1 = new JButton("Modificar");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnNewButton_1.setBounds(414, 391, 188, 37);
        frmConsultaEventos.getContentPane().add(btnNewButton_1);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(374, 250, 255, 119);
        frmConsultaEventos.getContentPane().add(textArea);

		
		
	}
}
