package main.java.presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.java.logica.ControladorEvento;

public class AltaEdicionEvento extends JInternalFrame {
	private static final long serialVersionUID = 7318556931431081324L;
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
	private JComboBox<String> comboBoxEventos;
	private JComboBox<String> comboBoxOrganizadores;
	private JButton btnCancelar;
	private JTextField textFechaAlta;
	private JLabel libFechaAlta;
	
	public AltaEdicionEvento() {
		setVisible(true); 
        setSize(699, 456);
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
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(340, 44, 140, 16);
		panel.add(lblNewLabel);
		
		lblDescripcin = new JLabel("Sigla:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcin.setBounds(340, 94, 140, 16);
		panel.add(lblDescripcin);
		
		lblSigla = new JLabel("Ciudad:");
		lblSigla.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSigla.setBounds(340, 144, 140, 16);
		panel.add(lblSigla);
		
		JLabel lblCategoras = new JLabel("País:");
		lblCategoras.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategoras.setBounds(340, 196, 140, 16);
		panel.add(lblCategoras);
		
		lblEvento = new JLabel("Evento:");
		lblEvento.setBounds(46, 49, 140, 16);
		panel.add(lblEvento);
		
		ControladorEvento controlador = ControladorEvento.getInstance();
		
		comboBoxEventos = new JComboBox<String>();
		comboBoxEventos.setBounds(46, 90, 238, 27);
		comboBoxEventos.addItem("Selecciona un Evento");
        for (String evento: controlador.listarEventos()) {
        	comboBoxEventos.addItem(evento);
        }
		panel.add(comboBoxEventos);
		
		JLabel lblOrganizador = new JLabel("Organizador:");
		lblOrganizador.setBounds(46, 144, 140, 16);
		panel.add(lblOrganizador);
		
		comboBoxOrganizadores = new JComboBox<String>();
		comboBoxOrganizadores.setBounds(46, 185, 238, 27);
		comboBoxOrganizadores.addItem("Selecciona un Organizador");
        for (String org: controlador.listarOrganizadores()) {
        	comboBoxOrganizadores.addItem(org);
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
		lblDescripcin_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcin_1.setBounds(340, 243, 140, 16);
		panel.add(lblDescripcin_1);
		
		JLabel lblSigla_1 = new JLabel("Fecha Fin:");
		lblSigla_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSigla_1.setBounds(340, 288, 140, 16);
		panel.add(lblSigla_1);
		
		textFechaAlta = new JTextField();
		textFechaAlta.setColumns(10);
		textFechaAlta.setBounds(490, 327, 140, 26);
		panel.add(textFechaAlta);
		
		libFechaAlta = new JLabel("Fecha Alta:");
		libFechaAlta.setHorizontalAlignment(SwingConstants.RIGHT);
		libFechaAlta.setBounds(340, 327, 140, 16);
		panel.add(libFechaAlta);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) { crearEdicion(event); }
        });
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	setVisible(false);
            	dispose();
            }
        });
		panel_1.add(btnCancelar);
		panel_1.add(btnNewButton);
	}
	
	protected void crearEdicion(ActionEvent event) {
        String nombre = sacarEspacios(textNombre.getText());
        String sigla = sacarEspacios(textSigla.getText());
        String ciudad = sacarEspacios(textCiudad.getText());
        String pais = sacarEspacios(textPais.getText());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaInicio;
        LocalDate fechaFin;
        LocalDate fechaAlta;
        // Si las fechas son inválidas, mostrar un error
        try {
	        fechaInicio = LocalDate.parse(textFechaIni.getText(), fmt);
	        fechaFin = LocalDate.parse(textFechaFin.getText(), fmt);
	        fechaAlta = LocalDate.parse(textFechaAlta.getText(), fmt);
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(this, "El formato de las fechas debe ser dd/mm/aaaa.", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        String evento = (String) comboBoxEventos.getSelectedItem();
        String organizador = (String) comboBoxOrganizadores.getSelectedItem();
        
        // Chequear que existan organizadores y eventos
        if (evento == "" || evento == null || evento == "Selecciona un Evento") {
        	JOptionPane.showMessageDialog(this, "Debe existir un evento", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        if (organizador == "Selecciona un Organizador" || organizador == "" || organizador == null) {
        	JOptionPane.showMessageDialog(this, "Debe existir un organizador", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        
        // Chequear que exista el nombre
        if (nombre.isEmpty()) {
        	JOptionPane.showMessageDialog(this, "La edición debe tener un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        } 
        if (fechaFin.isBefore(fechaInicio)) {
        	JOptionPane.showMessageDialog(this, "La fecha de fin no puede ser mayor que la de inicio", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        } 

        ControladorEvento controlador = ControladorEvento.getInstance();
        // Si la operación genera un error, mostrárselo al usuario
        boolean exito;
        try {
        	exito = controlador.altaEdicion(evento, organizador, nombre, sigla, fechaInicio, fechaFin, fechaAlta, ciudad, pais, null, null);
        } catch (Exception exc) {
        	JOptionPane.showMessageDialog(this, "Error inesperado:\n" + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
    	if (exito) {
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
                	textFechaAlta.setText("");
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
	private static String sacarEspacios(String str) {
	    return str == null ? "" : str.trim();
	}
}