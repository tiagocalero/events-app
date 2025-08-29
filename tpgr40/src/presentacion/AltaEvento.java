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

public class AltaEvento extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private JLabel lblDescripcin;
	private JLabel lblSigla;
	private JList<String> listaCategorias;
	
	public AltaEvento() {
		setVisible(true); 
        setSize(300, 200);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta de Evento");
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
		textField_1.setBounds(193, 74, 140, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(193, 124, 140, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(43, 24, 140, 16);
		panel.add(lblNewLabel);
		
		lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setBounds(43, 74, 140, 16);
		panel.add(lblDescripcin);
		
		lblSigla = new JLabel("Sigla:");
		lblSigla.setBounds(43, 124, 140, 16);
		panel.add(lblSigla);
		
		ControladorEvento m = ControladorEvento.getInstance();
		
		listaCategorias = new JList<>(m.listarCategorias().toArray(new String[0]));
		listaCategorias.setBounds(193, 176, 140, 81);
		panel.add(listaCategorias);
		listaCategorias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		JLabel lblCategoras = new JLabel("Categorías:");
		lblCategoras.setBounds(43, 176, 140, 16);
		panel.add(lblCategoras);
		
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
        String desc = textField_1.getText();
        String sigla = textField_2.getText();
        List<String> categorias = listaCategorias.getSelectedValuesList();
        
        ControladorEvento m = ControladorEvento.getInstance();
        m.ingresarDatosEvento(nombre, desc, sigla, categorias);
        JOptionPane.showMessageDialog(AltaEvento.this, "Se creó el evento: " + nombre);
    }
}