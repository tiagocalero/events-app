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
	private JTextField textNombre;
	private JTextField textSigla;
	private JTextField textCiudad;
	private JLabel lblNewLabel;
	private JLabel lblDescripcin;
	private JLabel lblSigla;
	private JLabel lblEvento;
	private JTextField textPais;
	private JTextField textFechaIni;
	private JTextField textFechaFin;
	private JComboBox comboBoxEventos;
	private JComboBox comboBoxOrganizadores;
	
	public AltaEdicionEvento() {
		setVisible(true); 
        setSize(699, 420);
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
		
		textNombre = new JTextField();
		textNombre.setBounds(490, 44, 140, 26);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		textSigla = new JTextField();
		textSigla.setBounds(490, 94, 140, 26);
		panel.add(textSigla);
		textSigla.setColumns(10);
		
		textCiudad = new JTextField();
		textCiudad.setBounds(490, 144, 140, 26);
		panel.add(textCiudad);
		textCiudad.setColumns(10);
		
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
		
		comboBoxEventos = new JComboBox();
		comboBoxEventos.setBounds(46, 90, 238, 27);
        for (String e: c.listarEventos()) {
        	comboBoxEventos.addItem(e);
        }
		panel.add(comboBoxEventos);
		
		JLabel lblOrganizador = new JLabel("Organizador:");
		lblOrganizador.setBounds(46, 144, 140, 16);
		panel.add(lblOrganizador);
		
		comboBoxOrganizadores = new JComboBox();
		comboBoxOrganizadores.setBounds(46, 185, 238, 27);
        for (String o: c.listarOrganizadores()) {
        	comboBoxOrganizadores.addItem(o);
        }
		panel.add(comboBoxOrganizadores);
		
		textPais = new JTextField();
		textPais.setColumns(10);
		textPais.setBounds(490, 191, 140, 26);
		panel.add(textPais);
		
		textFechaIni = new JTextField();
		textFechaIni.setColumns(10);
		textFechaIni.setBounds(490, 238, 140, 26);
		panel.add(textFechaIni);
		
		textFechaFin = new JTextField();
		textFechaFin.setColumns(10);
		textFechaFin.setBounds(490, 288, 140, 26);
		panel.add(textFechaFin);
		
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
            public void actionPerformed(ActionEvent e) { crearEdicion(e); }
        });
		panel_1.add(btnNewButton);
	}
	
	protected void crearEdicion(ActionEvent e) {
        String nombre = sacarEspacios(textNombre.getText());
        String sigla = sacarEspacios(textSigla.getText());
        String ciudad = sacarEspacios(textCiudad.getText());
        String pais = sacarEspacios(textPais.getText());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaInicio;
        LocalDate fechaFin;
        // Si las fechas son inválidas, mostrar un error
        try {
	        fechaInicio = LocalDate.parse(textFechaIni.getText(), fmt);
	        fechaFin = LocalDate.parse(textFechaFin.getText(), fmt);
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(this, "El formato de las fechas debe ser dd/mm/aaaa.", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        LocalDate fechaAlta = LocalDate.now();
        String evento = (String) comboBoxEventos.getSelectedItem();
        String organizador = (String) comboBoxOrganizadores.getSelectedItem();
        
        // Chequear que existan organizadores y eventos
        if (evento == "" || evento == null || organizador == "" || organizador == null) {
        	JOptionPane.showMessageDialog(this, "Deben existir Evento y Organizador", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        
        ControladorEvento m = ControladorEvento.getInstance();
        // Chequear que exista el nombre
        if (nombre.isEmpty()) {
        	JOptionPane.showMessageDialog(this, "La edición debe tener un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        } 
        
    	boolean ok = m.altaEdicion(evento, organizador, nombre, sigla, fechaInicio, fechaFin, fechaAlta, ciudad, pais);
        if (ok) {
        	// Si se creó, dar la opción de agregar más ediciones o cerrar la ventana
            Object[] opciones = {"Agregar más ediciones", "Cerrar"};
            int resultado = JOptionPane.showOptionDialog(
                this,
                "Se creó la edición: " + nombre,
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
                	textSigla.setText("");
                	textPais.setText("");
                	textCiudad.setText("");
                	textFechaIni.setText("");
                	textFechaFin.setText("");
	            	break;
                case 1:
                	dispose(); // Cierra la ventana
	            	break;
                default: // El usuario cierra el diálogo
                	dispose();
                	break;
            };
        
		} else {
        	JOptionPane.showMessageDialog(this, "Ya existe una edición con ese nombre.", "Error", JOptionPane.ERROR_MESSAGE);
		}
    }

	// Borrar espacios en blanco al comienzo y final de un String (para usar en campos)
	private static String sacarEspacios(String s) {
	    return s == null ? "" : s.trim();
	}
}