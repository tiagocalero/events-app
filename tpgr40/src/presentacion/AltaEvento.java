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
	private JTextField textNombre;
	private JTextField textDesc;
	private JTextField textSigla;
	private JLabel lblNewLabel;
	private JLabel lblDescripcin;
	private JLabel lblSigla;
	private JList<String> listaCategorias;
	private JButton btnCancelar;
	
	public AltaEvento() {
		setVisible(true); 
        setSize(410, 367);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta de Evento");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textNombre = new JTextField();
		textNombre.setBounds(193, 24, 140, 26);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		textDesc = new JTextField();
		textDesc.setBounds(193, 74, 140, 26);
		panel.add(textDesc);
		textDesc.setColumns(10);
		
		textSigla = new JTextField();
		textSigla.setBounds(193, 124, 140, 26);
		panel.add(textSigla);
		textSigla.setColumns(10);
		
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
		
		listaCategorias = new JList<String>(m.listarCategorias().toArray(new String[0]));
		
        JScrollPane scrollPane = new JScrollPane(listaCategorias);
		scrollPane.setBounds(193, 176, 140, 81);
		panel.add(scrollPane);
		listaCategorias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		JLabel lblCategoras = new JLabel("Categorías:");
		lblCategoras.setBounds(43, 176, 140, 16);
		panel.add(lblCategoras);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { crearEvento(e); }
        });

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { dispose(); }
        });
		
		panel_1.add(btnCancelar);
		panel_1.add(btnCrear);
	}
	
	private void crearEvento(ActionEvent e) {
        String nombre = sacarEspacios(textNombre.getText());
        String desc = sacarEspacios(textDesc.getText());
        String sigla = sacarEspacios(textSigla.getText());
        List<String> categorias = listaCategorias.getSelectedValuesList();
        
        ControladorEvento m = ControladorEvento.getInstance();
        // Chequear que exista el nombre
        if (nombre.isEmpty()) {
        	JOptionPane.showMessageDialog(AltaEvento.this, "El evento debe tener un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        // Chequear que exista al menos una categoria
        if (categorias.isEmpty()) {
        	JOptionPane.showMessageDialog(AltaEvento.this, "El evento debe tener al menos una categoria.", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }

        // Si la operación genera un error, mostrárselo al usuario
        boolean ok;
        try {
        	ok = m.ingresarDatosEvento(nombre, desc, sigla, categorias);
		} catch (Exception ex) {
	    	JOptionPane.showMessageDialog(this, "Error inesperado:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
        if (ok) {
        	// Si se creó, dar la opción de agregar más eventos o cerrar la ventana
            Object[] opciones = {"Agregar más eventos", "Cerrar"};
            int resultado = JOptionPane.showOptionDialog(
                this,
                "Se creó el evento: " + nombre,
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
                	// Borrar campos
	            	textNombre.setText("");
	            	textDesc.setText("");
	            	textSigla.setText("");
	            	break;
                case 1:
                	dispose(); // Cierra la ventana
	            	break;
                default: // El usuario cierra el diálogo
                	dispose();
                	break;
            };
		} else {
        	JOptionPane.showMessageDialog(AltaEvento.this, "Ya existe un evento con ese nombre.", "Error", JOptionPane.ERROR_MESSAGE);
		}
    }
	
	// Borrar espacios en blanco al comienzo y final de un String (para usar en campos)
	private static String sacarEspacios(String s) {
	    return s == null ? "" : s.trim();
	}
}