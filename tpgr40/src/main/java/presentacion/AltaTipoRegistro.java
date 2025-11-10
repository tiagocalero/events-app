package main.java.presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class AltaTipoRegistro extends JInternalFrame {
	private static final long serialVersionUID = -5815021613805971585L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private JLabel lblDescripcin;
	private JLabel lblSigla;
	private JTextField textField_3;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JButton btnCancelar;
	
	public AltaTipoRegistro() {
		setVisible(true); 
        setSize(699, 349);
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
		
		ControladorEvento controlador = ControladorEvento.getInstance();
		
		JLabel lblEvento = new JLabel("Evento:");
		lblEvento.setBounds(46, 62, 140, 16);
		panel.add(lblEvento);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(46, 89, 238, 27);
        for (String e: controlador.listarEventos()) {
        	comboBox.addItem(e);
        }
		panel.add(comboBox);
		
		JLabel lblEdicion = new JLabel("Edicion:");
		lblEdicion.setBounds(46, 144, 140, 16);
		panel.add(lblEdicion);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(46, 185, 238, 27);
		panel.add(comboBox_1);
		
		
		comboBox.addActionListener(e -> {
		    // Primero vaciamos el combo de ediciones
		    comboBox_1.removeAllItems();

		    // Obtenemos el evento seleccionado
		    String eventoSeleccionado = (String) comboBox.getSelectedItem();

		    // Listamos las ediciones de ese evento y agregamos al combo
		    for (String ed : controlador.listarEdiciones(eventoSeleccionado)) {
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
            public void actionPerformed(ActionEvent event) { dispose(); }
        });
		panel_1.add(btnCancelar);
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(event -> {
		    // Obtenemos los datos de los campos de texto
			String nombreEvento = (String) comboBox.getSelectedItem();
			String nombreEdicion = (String) comboBox_1.getSelectedItem();
		    String nombre = sacarEspacios(textField.getText());
		    String descripcion =  sacarEspacios(textField_1.getText().trim());
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
		    
		    // Validar evento y edicion
		    if (nombreEvento == null || nombreEvento == "") {
		    	JOptionPane.showMessageDialog(panel, "Debe existir un evento", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }
		    if (nombreEdicion == null || nombreEdicion == "") {
		    	JOptionPane.showMessageDialog(panel, "Debe existir un evento", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }
		    
		    // Validar nombre
		    if (nombre.isEmpty()) {
		    	JOptionPane.showMessageDialog(panel, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

            // Si la operación genera un error, mostrárselo al usuario
		    boolean exito;
		    try {
		    	exito = controlador.crearTipoRegistro(nombreEvento, nombreEdicion, nombre, descripcion, costo, cupo);
			} catch(IllegalArgumentException ex) {
	        	JOptionPane.showMessageDialog(AltaTipoRegistro.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        	return;
	        } catch (Exception ex) {
		    	JOptionPane.showMessageDialog(this, "Error inesperado:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    	return;
		    }
		    if (exito) {
	        	// Si se creó, dar la opción de agregar más tipos de registro o cerrar la ventana
	            Object[] opciones = {"Agregar más tipos de registro", "Cerrar"};
	            int resultado = JOptionPane.showOptionDialog(
	                this,
	                "Se creó el tipo de registro: " + nombre,
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
	                	textField.setText("");
	                	textField_1.setText("");
	                	textField_2.setText("");
	                	textField_3.setText("");
		            	break;
	                case 1:
	                	dispose(); // Cierra la ventana
		            	break;
	                default: // El usuario cierra el diálogo
	                	dispose();
	                	break;
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Ya existe un tipo de registro con ese nombre en esta edición", "Error", JOptionPane.WARNING_MESSAGE);
	            textField.requestFocus();
	        }

            
        });
		panel_1.add(btnNewButton);
	}


	// Borrar espacios en blanco al comienzo y final de un String (para usar en campos)
	private static String sacarEspacios(String str) {
	    return str == null ? "" : str.trim();
	}
    
}