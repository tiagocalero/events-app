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
		
		textArea = new JTextArea();
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
        String nickname = sacarEspacios(textField.getText());
        String nombre = sacarEspacios(textField_1.getText());
        String correo = sacarEspacios(textField_2.getText());
        String apellido = sacarEspacios(textField_3.getText());
        LocalDate nacimiento;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Validar fecha
        try {
        	nacimiento = LocalDate.parse(textField_4.getText(), fmt);
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(this, "El formato de las fechas debe ser dd/mm/aaaa.", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        
        if (nickname.isEmpty()) {
        	JOptionPane.showMessageDialog(this, "El nickname no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        
        if (correo.isEmpty()) {
        	JOptionPane.showMessageDialog(this, "El correo no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        
        ControladorUsuario c = ControladorUsuario.getInstance();
        if (c.altaAsistente(nickname, nombre, correo, apellido, nacimiento)) {    
        	// Si se creó, dar la opción de agregar más usuarios o cerrar la ventana
            Object[] opciones = {"Agregar más usuarios", "Cerrar"};
            int resultado = JOptionPane.showOptionDialog(
                this,
                "Se creó el asistente: " + nickname,
                "",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,        
                opciones,    
                opciones[0] // opción por defecto
            );

            // Ver qué eligió el usuario
            switch (resultado) {
                case 0:
	            	borrarCampos();
	            	break;
                case 1:
                	dispose(); // Cierra la ventana
	            	break;
                default: // El usuario cierra el diálogo
                	dispose();
                	break;
            };
        } else {
        	JOptionPane.showMessageDialog(AltaUsuario.this, "Ya existe un usuario con el mismo nickname o correo electrónico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	protected void cmdCrearOrganizadorActionPerformed(ActionEvent e) {
        String nickname = sacarEspacios(textField.getText());
        String nombre = sacarEspacios(textField_1.getText());
        String correo = sacarEspacios(textField_2.getText());
        String desc = sacarEspacios(textArea.getText());
        String link = sacarEspacios(textField_6.getText());

        if (nickname.isEmpty()) {
        	JOptionPane.showMessageDialog(this, "El nickname no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        
        if (correo.isEmpty()) {
        	JOptionPane.showMessageDialog(this, "El correo no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        
        ControladorUsuario c = ControladorUsuario.getInstance();
        if(c.altaOrganizador(nickname, nombre, correo, desc, link)) {    
        	// Si se creó, dar la opción de agregar más usuarios o cerrar la ventana
            Object[] opciones = {"Agregar más usuarios", "Cerrar"};
            int resultado = JOptionPane.showOptionDialog(
                this,
                "Se creó el organizador: " + nickname,
                "",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,        
                opciones,    
                opciones[0] // opción por defecto
            );

            // Ver qué eligió el usuario
            switch (resultado) {
                case 0:
	            	borrarCampos();
	            	break;
                case 1:
                	dispose(); // Cierra la ventana
	            	break;
                default: // El usuario cierra el diálogo
                	dispose();
                	break;
            };
        } else {
        	JOptionPane.showMessageDialog(AltaUsuario.this, "Ya existe un usuario con el mismo nickname o correo electrónico", "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	// Limpia todos los campos
	private void borrarCampos() {
    	textField.setText("");
    	textField_1.setText("");
    	textField_2.setText("");
    	textField_3.setText("");
    	textField_4.setText("");
    	textField_6.setText("");
    	textArea.setText("");
	}

	// Borrar espacios en blanco al comienzo y final de un String (para usar en campos)
	private static String sacarEspacios(String s) {
	    return s == null ? "" : s.trim();
	}
}