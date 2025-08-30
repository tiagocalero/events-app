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

public class AltaEdicionEvento extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private JLabel lblDescripcin;
	private JLabel lblSigla;
	private JLabel lblEvento;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	
	public AltaEdicionEvento() {
		setVisible(true); 
        setSize(699, 405);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta de Edición de Evento");
		//setBounds(30, 30, 402, 376);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(490, 44, 140, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(490, 94, 140, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(490, 144, 140, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(340, 44, 140, 16);
		panel.add(lblNewLabel);
		
		lblDescripcin = new JLabel("Sigla:");
		lblDescripcin.setBounds(340, 94, 140, 16);
		panel.add(lblDescripcin);
		
		lblSigla = new JLabel("Ciudad:");
		lblSigla.setBounds(340, 144, 140, 16);
		panel.add(lblSigla);
		
		JLabel lblCategoras = new JLabel("País:");
		lblCategoras.setBounds(340, 196, 140, 16);
		panel.add(lblCategoras);
		
		lblEvento = new JLabel("Evento:");
		lblEvento.setBounds(46, 49, 140, 16);
		panel.add(lblEvento);
		
		ControladorEvento c = ControladorEvento.getInstance();
		
		comboBox = new JComboBox();
		comboBox.setBounds(46, 90, 238, 27);
        for (String e: c.listarEventos()) {
        	comboBox.addItem(e);
        }
		panel.add(comboBox);
		
		JLabel lblOrganizador = new JLabel("Organizador:");
		lblOrganizador.setBounds(46, 144, 140, 16);
		panel.add(lblOrganizador);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(46, 185, 238, 27);
        for (String o: c.listarOrganizadores()) {
        	comboBox_1.addItem(o);
        }
		panel.add(comboBox_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(490, 191, 140, 26);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(490, 238, 140, 26);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(490, 288, 140, 26);
		panel.add(textField_5);
		
		JLabel lblDescripcin_1 = new JLabel("Fecha Inicio:");
		lblDescripcin_1.setBounds(340, 243, 140, 16);
		panel.add(lblDescripcin_1);
		
		JLabel lblSigla_1 = new JLabel("Fecha Fin:");
		lblSigla_1.setBounds(340, 288, 140, 16);
		panel.add(lblSigla_1);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdCrearEventoActionPerformed(e);
            }
        });
		panel_1.add(btnNewButton);
	}
	
	protected void cmdCrearEventoActionPerformed(ActionEvent e) {
        String nombre = textField.getText();
        String sigla = textField_1.getText();
        String ciudad = textField_2.getText();
        String pais = textField_3.getText();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaInicio = LocalDate.parse(textField_4.getText(), fmt);
        LocalDate fechaFin = LocalDate.parse(textField_5.getText(), fmt);
        LocalDate fechaAlta = LocalDate.now();
        String evento = (String) comboBox.getSelectedItem();
        String organizador = (String) comboBox.getSelectedItem();
        
        ControladorEvento m = ControladorEvento.getInstance();
        m.altaEdicion(evento, organizador, nombre, sigla, fechaInicio, fechaFin, fechaAlta, ciudad, pais);
        JOptionPane.showMessageDialog(AltaEdicionEvento.this, "Se creó la edición: " + nombre + ", para el evento: " + evento);
    }
}