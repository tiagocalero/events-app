package presentacion;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import logica.ControladorEvento;

import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AltaTipoRegistro extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private JLabel lblDescripcin;
	private JLabel lblSigla;
	private JLabel lblEvento;
	private JTextField textField_3;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JButton btnCancelar;
	
	public AltaTipoRegistro() {
		setVisible(true); 
        setSize(699, 405);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta de Tipo de Registro");
		//setBounds(30, 30, 402, 376);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(490, 39, 140, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(490, 89, 140, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(490, 139, 140, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(340, 44, 140, 16);
		panel.add(lblNewLabel);
		
		lblDescripcin = new JLabel("Descripcion:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcin.setBounds(340, 94, 140, 16);
		panel.add(lblDescripcin);
		
		lblSigla = new JLabel("Costo:");
		lblSigla.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSigla.setBounds(340, 144, 140, 16);
		panel.add(lblSigla);
		
		JLabel lblCategoras = new JLabel("Cupo:");
		lblCategoras.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCategoras.setBounds(340, 196, 140, 16);
		panel.add(lblCategoras);
		
		ControladorEvento c = ControladorEvento.getInstance();
		
		JLabel lblEvento = new JLabel("Evento:");
		lblEvento.setBounds(46, 62, 140, 16);
		panel.add(lblEvento);
		
		comboBox = new JComboBox();
		comboBox.setBounds(46, 89, 238, 27);
        for (String e: c.listarEventos()) {
        	comboBox.addItem(e);
        }
		panel.add(comboBox);
		
		JLabel lblEdicion = new JLabel("Edicion:");
		lblEdicion.setBounds(46, 144, 140, 16);
		panel.add(lblEdicion);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(46, 185, 238, 27);
		panel.add(comboBox_1);
		
		
		comboBox.addActionListener(e -> {
		    // Primero vaciamos el combo de ediciones
		    comboBox_1.removeAllItems();

		    // Obtenemos el evento seleccionado
		    String eventoSeleccionado = (String) comboBox.getSelectedItem();

		    // Listamos las ediciones de ese evento y agregamos al combo
		    for (String ed : c.listarEdiciones(eventoSeleccionado)) {
		        comboBox_1.addItem(ed);
		    }
		});
		
	
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(490, 191, 140, 26);
		panel.add(textField_3);
		
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { dispose(); }
        });
		panel_1.add(btnCancelar);
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(e -> {
			
		    // Obtenemos los datos de los campos de texto
			String nombreEvento = (String) comboBox.getSelectedItem();
			String nombreEdicion = (String) comboBox_1.getSelectedItem();
		    String nombre = textField.getText().trim();
		    String descripcion = textField_1.getText().trim();
		    float costo = 0;
		    int cupo = 0;

		    // Validación simple de costo y cupo
		    try {
		        costo = Float.parseFloat(textField_2.getText().trim());
		        cupo = Integer.parseInt(textField_3.getText().trim());
		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(panel, "Costo o cupo inválido", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }
		    boolean exito = c.crearTipoRegistro(nombreEvento, nombreEdicion, nombre, descripcion, costo, cupo);
		    
		    if (exito) {
	            JOptionPane.showMessageDialog(null, "Tipo de registro creado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            textField.setText("");
	            textField_1.setText("");
	            textField_2.setText("");
	            textField_3.setText("");
	        } else {
	            JOptionPane.showMessageDialog(null, "Ya existe un tipo de registro con ese nombre en esta edición", "Error", JOptionPane.WARNING_MESSAGE);
	            textField.requestFocus();
	        }

            
        });
		panel_1.add(btnNewButton);
	}

    
}