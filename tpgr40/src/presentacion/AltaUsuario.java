package presentacion;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import logica.ControladorInstitucion;
import logica.ControladorUsuario;

import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AltaUsuario extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private JLabel lblDescripcin;
	private JLabel lblSigla;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextArea textArea;
	
	public AltaUsuario() {
		setVisible(true); 
        setSize(524, 456);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta de Usuario");
		//setBounds(30, 30, 402, 376);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(193, 24, 140, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(193, 62, 140, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(193, 100, 140, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		lblNewLabel = new JLabel("Nickname:");
		lblNewLabel.setBounds(43, 24, 140, 16);
		panel.add(lblNewLabel);
		
		lblDescripcin = new JLabel("Nombre:");
		lblDescripcin.setBounds(43, 62, 140, 16);
		panel.add(lblDescripcin);
		
		lblSigla = new JLabel("Correo:");
		lblSigla.setBounds(43, 104, 140, 16);
		panel.add(lblSigla);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(32, 186, 472, 189);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblLink = new JLabel("Link:");
		lblLink.setBounds(43, 198, 140, 17);
		panel.add(lblLink);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(193, 198, 140, 26);
		panel.add(textField_6);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(43, 236, 93, 17);
		panel.add(lblDescripcion);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(12, 12, 93, 17);
		panel_2.add(lblApellido);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(161, 12, 140, 26);
		panel_2.add(textField_3);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
		lblFechaNacimiento.setBounds(12, 50, 140, 17);
		panel_2.add(lblFechaNacimiento);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(161, 50, 140, 26);
		panel_2.add(textField_4);
		
		JLabel lblInstitiucion = new JLabel("Institiucion:");
		lblInstitiucion.setBounds(12, 88, 140, 17);
		panel_2.add(lblInstitiucion);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(161, 88, 140, 26);
		ControladorInstitucion c = ControladorInstitucion.getInstance();
		for(String i: c.listarInstituciones()) {
			comboBox.addItem(i);
		}
		panel_2.add(comboBox);
	
		
		JRadioButton rdbtnOrganizador = new JRadioButton("Organizador");
		JRadioButton rdbtnAsistente = new JRadioButton("Asistente");
		rdbtnAsistente.setSelected(true);
		rdbtnAsistente.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				rdbtnAsistente.setSelected(true);
				rdbtnOrganizador.setSelected(false);
				panel_2.setVisible(true);
			}
		});
		rdbtnAsistente.setBounds(43, 153, 130, 25);
		panel.add(rdbtnAsistente);
		
		rdbtnOrganizador.setBounds(193, 153, 153, 25);
		rdbtnOrganizador.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				rdbtnAsistente.setSelected(false);
				rdbtnOrganizador.setSelected(true);
				panel_2.setVisible(false);
			}
		});
		panel.add(rdbtnOrganizador);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(193, 236, 311, 139);
		panel.add(textArea);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		if(rdbtnAsistente.isSelected()) {
                    cmdCrearAsistenteActionPerformed(e);
        		} else if(rdbtnOrganizador.isSelected()) {
        				cmdCrearOrganizadorActionPerformed(e);
            	}
            }
        });
		panel_1.add(btnNewButton);
	}
	
	protected void cmdCrearAsistenteActionPerformed(ActionEvent e) {
        String nickname = textField.getText();
        String nombre = textField_1.getText();
        String correo = textField_2.getText();
        String apellido = textField_3.getText();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate nacimiento = LocalDate.parse(textField_4.getText(), fmt);
        
        ControladorUsuario c = ControladorUsuario.getInstance();
        c.altaAsistente(nickname, nombre, correo, apellido, nacimiento);
        JOptionPane.showMessageDialog(AltaUsuario.this, "Se creó el usuario: " + nickname);
    }
	protected void cmdCrearOrganizadorActionPerformed(ActionEvent e) {
        String nickname = textField.getText();
        String nombre = textField_1.getText();
        String correo = textField_2.getText();
        String desc = textArea.getText();
        String link = textField_6.getText();
        
        ControladorUsuario c = ControladorUsuario.getInstance();
        c.altaOrganizador(nickname, nombre, correo, desc, link);
        JOptionPane.showMessageDialog(AltaUsuario.this, "Se creó el usuario: " + nickname);
    }
}