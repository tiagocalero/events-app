package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

import logica.ControladorEvento;
import logica.ControladorInstitucion;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AltaInstitucion extends JInternalFrame{
	private JInternalFrame frmAltaInstitucion;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	
	
	public AltaInstitucion() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta Institución");
		setBounds(30, 30, 315, 365);
		getContentPane().setLayout(null);
		
			
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(40, 54, 60, 17);
		getContentPane().add(lblNombre);
		
		JLabel lblSitioWeb = new JLabel("Sitio Web: ");
		lblSitioWeb.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSitioWeb.setBounds(-18, 84, 118, 17);
		getContentPane().add(lblSitioWeb);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcin.setBounds(11, 114, 89, 17);
		getContentPane().add(lblDescripcin);
		
		JLabel lblDatosDeLa = new JLabel("Datos de la Institución:");
		lblDatosDeLa.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDatosDeLa.setBounds(55, 2, 187, 40);
		getContentPane().add(lblDatosDeLa);
		
		textField = new JTextField();
		textField.setBounds(107, 52, 151, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(107, 82, 151, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(107, 114, 151, 88);
		getContentPane().add(textArea);
		
		
		JButton btnNewButton_1 = new JButton("Crear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdCrearInstActionPerformed(e);
			}
		});
		btnNewButton_1.setBounds(55, 214, 188, 37);
		getContentPane().add(btnNewButton_1);
				
		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.setBounds(55, 263, 188, 37);
		getContentPane().add(btnNewButton_2);
	}
	
	protected void cmdCrearInstActionPerformed(ActionEvent e) {
        String nombre = textField.getText();
        String url = textField_1.getText();
        String desc= textArea.getText();
        	        
        ControladorInstitucion c = ControladorInstitucion.getInstance();
        c.altaInstitucion(nombre, desc, url);
        JOptionPane.showMessageDialog(AltaInstitucion.this, "Se creó la institución: " + nombre);
	}
}
