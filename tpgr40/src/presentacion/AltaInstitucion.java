package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

import logica.ControladorEvento;
import logica.ControladorInstitucion;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AltaInstitucion extends JInternalFrame{
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
		setTitle("Alta Institución");
		setBounds(30, 30, 315, 422);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(40, 54, 60, 17);
		getContentPane().add(lblNombre);
		
		JLabel lblSitioWeb = new JLabel("Sitio Web: ");
		lblSitioWeb.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSitioWeb.setBounds(-18, 84, 118, 17);
		getContentPane().add(lblSitioWeb);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcin.setBounds(11, 114, 89, 17);
		getContentPane().add(lblDescripcin);
		
		JLabel lblDatosDeLa = new JLabel("Datos de la Institución:");
		lblDatosDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDeLa.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDatosDeLa.setBounds(11, 2, 247, 40);
		getContentPane().add(lblDatosDeLa);
		
		textNombre = new JTextField();
		textNombre.setBounds(107, 52, 151, 21);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		textWeb = new JTextField();
		textWeb.setBounds(107, 82, 151, 21);
		getContentPane().add(textWeb);
		textWeb.setColumns(10);
		
		textDesc = new JTextArea();
		textDesc.setWrapStyleWord(true);
		textDesc.setLineWrap(true);
		textDesc.setBounds(107, 114, 151, 88);
		getContentPane().add(textDesc);
		
		
		JButton btnNewButton_1 = new JButton("Crear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { crearInstitucion(e); }
		});
		btnNewButton_1.setBounds(55, 214, 188, 37);
		getContentPane().add(btnNewButton_1);
				
		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.setBounds(54, 312, 188, 37);
		btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	dispose();
            }
        });
		getContentPane().add(btnNewButton_2);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(54, 263, 188, 37);
		btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	textNombre.setText("");
            	textWeb.setText("");
            	textDesc.setText(""); 
            }
        });
		getContentPane().add(btnLimpiar);
	}
	
	protected void crearInstitucion(ActionEvent e) {
        String nombre = sacarEspacios(textNombre.getText());
        String url = sacarEspacios(textWeb.getText());
        String desc= sacarEspacios(textDesc.getText());
        
        if (nombre.isEmpty()) {
        	JOptionPane.showMessageDialog(AltaInstitucion.this, "La institución debe tener un nombre", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
                
        ControladorInstitucion c = ControladorInstitucion.getInstance();
        boolean exito = c.altaInstitucion(nombre, desc, url);
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
	private static String sacarEspacios(String s) {
	    return s == null ? "" : s.trim();
	}
}
