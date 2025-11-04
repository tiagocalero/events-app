package main.java.presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.java.logica.ControladorInstitucion;

public class AltaInstitucion extends JInternalFrame{
	private static final long serialVersionUID = -8173060715607020830L;
	private JTextField textNombre;
	private JTextField textWeb;
	private JTextArea textDesc;
	
	
	public AltaInstitucion() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta de Institución");
		setBounds(30, 30, 402, 298);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(29, 22, 117, 17);
		panel.add(lblNombre);
		
		JLabel lblSitioWeb = new JLabel("Sitio Web: ");
		lblSitioWeb.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSitioWeb.setBounds(29, 61, 118, 17);
		panel.add(lblSitioWeb);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcin.setBounds(29, 103, 117, 17);
		panel.add(lblDescripcin);
		
		textNombre = new JTextField();
		textNombre.setBounds(158, 19, 178, 21);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		textWeb = new JTextField();
		textWeb.setBounds(158, 58, 178, 21);
		panel.add(textWeb);
		textWeb.setColumns(10);
		
		textDesc = new JTextArea();
		textDesc.setWrapStyleWord(true);
		textDesc.setLineWrap(true);
		textDesc.setBounds(158, 103, 178, 88);
		panel.add(textDesc);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	textNombre.setText("");
            	textWeb.setText("");
            	textDesc.setText(""); 
            }
        });
		panel_1.add(btnLimpiar);
		
		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	setVisible(false);
            	dispose();
            }
        });
		panel_1.add(btnNewButton_2);

		JButton btnNewButton_1 = new JButton("Crear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) { crearInstitucion(event); }
		});
		panel_1.add(btnNewButton_1);
	}
	
	protected void crearInstitucion(ActionEvent event) {
        String nombre = sacarEspacios(textNombre.getText());
        String url = sacarEspacios(textWeb.getText());
        String desc= sacarEspacios(textDesc.getText());
        
        if (nombre.isEmpty()) {
        	JOptionPane.showMessageDialog(AltaInstitucion.this, "La institución debe tener un nombre", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
                
        ControladorInstitucion controlador = ControladorInstitucion.getInstance();
        // Si la operación genera un error, mostrárselo al usuario
        boolean exito;
        try {
        	exito = controlador.altaInstitucion(nombre, desc, url);
		} catch (Exception exc) {
	    	JOptionPane.showMessageDialog(this, "Error inesperado:\n" + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
        if (!exito) {
        	JOptionPane.showMessageDialog(AltaInstitucion.this, "La institución: " + nombre + " ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
        	// Si se creó, dar la opción de agregar más instituciones o cerrar la ventana
            Object[] opciones = {"Agregar más instituciones", "Cerrar"};
            int resultado = JOptionPane.showOptionDialog(
                this,
                "Se creó la institución: " + nombre,
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
	            	textWeb.setText("");
	            	break;
                case 1:
                	dispose(); // Cierra la ventana
	            	break;
                default: // El usuario cierra el diálogo
                	dispose();
                	break;
            };
        }
	}

	// Borrar espacios en blanco al comienzo y final de un String (para usar en campos)
	private static String sacarEspacios(String str) {
	    return str == null ? "" : str.trim();
	}
}
