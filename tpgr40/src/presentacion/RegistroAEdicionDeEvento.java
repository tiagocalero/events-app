package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import logica.ControladorEvento;
import logica.ControladorUsuario;
import logica.DTTRegistro;
import logica.DTEvento;
import logica.DTAsistente;
import javax.swing.JPanel;

public class RegistroAEdicionDeEvento extends JInternalFrame {
	private JInternalFrame frmConsultaEventos;
	private JLabel lblEventos;
	private JLabel lblEdiciones;
	private JLabel lblTipoRegistro;
	private JLabel lblAsistentes;
	private JComboBox comboBoxEventos;
	private JComboBox comboBoxEdiciones;
	//private JComboBox comboBoxTipoRegistro;
	private JComboBox<String> comboBoxTipoRegistro;
	private JComboBox<String> comboBoxAsistentes;
	private JTextField textFecha;
	private JLabel lblFecha;
	
	

	
	public RegistroAEdicionDeEvento() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Registro a Edicion de Evento");
		setBounds(30, 30, 709, 441);
		getContentPane().setLayout(null);
		
		lblEventos = new JLabel("Eventos:");
		lblEventos.setBounds(22, 40, 309, 36);
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblEventos);

        comboBoxEventos = new JComboBox();
        comboBoxEventos.setBounds(22, 75, 309, 46);
        
        ControladorEvento c = ControladorEvento.getInstance();
        
        for (String e: c.listarEventos()) {
        	comboBoxEventos.addItem(e);
        }
        getContentPane().add(comboBoxEventos);
        
        
        lblEdiciones = new JLabel("Ediciones: ");
        lblEdiciones.setHorizontalAlignment(SwingConstants.CENTER);
        lblEdiciones.setBounds(22, 152, 309, 36);
        getContentPane().add(lblEdiciones);
        
        comboBoxEdiciones = new JComboBox();
        comboBoxEdiciones.setBounds(22, 188, 309, 46);
        getContentPane().add(comboBoxEdiciones);
        
        comboBoxEventos.addActionListener(e -> {
        	// Primero vaciamos el combo de ediciones
		    comboBoxEdiciones.removeAllItems();

		    // Obtenemos el evento seleccionado
		    String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();

		    // Listamos las ediciones de ese evento y agregamos al combo
		    for (String ed : c.listarEdiciones(eventoSeleccionado)) {
		        comboBoxEdiciones.addItem(ed);
		    }
        });
        
        lblTipoRegistro = new JLabel("Tipos de Registro: ");
        lblTipoRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipoRegistro.setBounds(343, 40, 309, 36);
        getContentPane().add(lblTipoRegistro);
        
        comboBoxTipoRegistro = new JComboBox<>();
        comboBoxTipoRegistro.setBounds(343, 75, 309, 46);
        getContentPane().add(comboBoxTipoRegistro);
        
        comboBoxEdiciones.addActionListener(e -> {
        	// Primero vaciamos el combo de ediciones
		    comboBoxTipoRegistro.removeAllItems();

		    // Obtenemos el evento seleccionado
		    String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
		    
		    // Obtenemos la edicion seleccionada
		    String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();

		    
		    if (eventoSeleccionado != null && edicionSeleccionada != null) {
		    	// Listamos las ediciones de ese evento y agregamos al combo
		    	for (String dtr : c.listarTipoRegistro(eventoSeleccionado, edicionSeleccionada)) {
		    		comboBoxTipoRegistro.addItem(dtr);
		    	}
		    }
        });
        
        lblAsistentes = new JLabel("Asistentes: ");
        lblAsistentes.setHorizontalAlignment(SwingConstants.CENTER);
        lblAsistentes.setBounds(343, 152, 309, 36);
        getContentPane().add(lblAsistentes);
        
        comboBoxAsistentes = new JComboBox<>();
        comboBoxAsistentes.setBounds(343, 188, 309, 46);
        getContentPane().add(comboBoxAsistentes);
        
        ControladorUsuario cu = ControladorUsuario.getInstance();
        
        for (DTAsistente asistente : cu.listarAsistentes()) {
        	comboBoxAsistentes.addItem(asistente.getNickname());
        }
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(343, 353, 132, 29);
        btnRegistrar.addActionListener(e -> {
            String nickAsistente = (String) comboBoxAsistentes.getSelectedItem();
            String nombreTRegistro = (String) comboBoxTipoRegistro.getSelectedItem();
            String nombreEdicion = (String) comboBoxEdiciones.getSelectedItem();
            String nickEvento = (String) comboBoxEventos.getSelectedItem();
            
            if (nickEvento == null) {
                JOptionPane.showMessageDialog(this, 
                        "Debe existir un evento",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (nombreEdicion == null) {
                JOptionPane.showMessageDialog(this, 
                        "Debe existir una edición",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (nombreTRegistro == null) {
                JOptionPane.showMessageDialog(this, 
                        "Debe existir un tipo de registro",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (nickAsistente == null) {
                JOptionPane.showMessageDialog(this, 
                        "Debe existir un asistente",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaAlta;
            // Si la fecha es inválida, mostrar un error
            try {
            	fechaAlta = LocalDate.parse(textFecha.getText(), fmt);
            } catch (Exception ex) {
            	JOptionPane.showMessageDialog(this, "El formato de las fechas debe ser dd/mm/aaaa.", "Error", JOptionPane.ERROR_MESSAGE);
            	return;
            }

            // Si la operación genera un error, mostrárselo al usuario
            boolean error;
            try {
            	error = c.darDeAltaRegistro(nickAsistente, nombreEdicion, nombreTRegistro, nickEvento, fechaAlta);
	        } catch (Exception ex) {
		    	JOptionPane.showMessageDialog(this, "Error inesperado:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    	return;
		    }
            if (error) {
                JOptionPane.showMessageDialog(this, 
                    "No se puede registrar: cupo completo o asistente ya registrado",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Registro realizado con éxito",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        getContentPane().add(btnRegistrar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(199, 353, 132, 29);
        getContentPane().add(btnCancelar);
        
        textFecha = new JTextField();
        textFecha.setBounds(343, 287, 155, 26);
        getContentPane().add(textFecha);
        textFecha.setColumns(10);
        
        lblFecha = new JLabel("Fecha Alta");
        lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFecha.setBounds(199, 292, 132, 16);
        getContentPane().add(lblFecha);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	dispose();
            }
        });
	}
}
