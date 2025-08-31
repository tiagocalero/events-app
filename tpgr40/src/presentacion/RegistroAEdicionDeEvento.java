package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JComboBox<DTTRegistro> comboBoxTipoRegistro;
	private JComboBox<DTAsistente> comboBoxAsistentes;
	
	

	
	public RegistroAEdicionDeEvento() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar un Evento");
		setBounds(30, 30, 691, 630);
		getContentPane().setLayout(null);
		
		lblEventos = new JLabel("Eventos:");
		lblEventos.setBounds(22, 29, 262, 36);
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblEventos);

        comboBoxEventos = new JComboBox();
        comboBoxEventos.setBounds(22, 76, 262, 46);
        
        ControladorEvento c = ControladorEvento.getInstance();
        
        for (String e: c.listarEventos()) {
        	comboBoxEventos.addItem(e);
        }
        getContentPane().add(comboBoxEventos);
        
        
        lblEdiciones = new JLabel("Ediciones: ");
        lblEdiciones.setHorizontalAlignment(SwingConstants.CENTER);
        lblEdiciones.setBounds(30, 133, 254, 36);
        getContentPane().add(lblEdiciones);
        
        comboBoxEdiciones = new JComboBox();
        comboBoxEdiciones.setBounds(22, 188, 262, 46);
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
        lblTipoRegistro.setBounds(22, 257, 262, 36);
        getContentPane().add(lblTipoRegistro);
        
        comboBoxTipoRegistro = new JComboBox<>();
        comboBoxTipoRegistro.setBounds(22, 304, 262, 46);
        getContentPane().add(comboBoxTipoRegistro);
        
        comboBoxEdiciones.addActionListener(e -> {
        	// Primero vaciamos el combo de ediciones
		    comboBoxTipoRegistro.removeAllItems();

		    // Obtenemos el evento seleccionado
		    String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
		    
		 // Obtenemos la edicion seleccionada
		    String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();

		    // Listamos las ediciones de ese evento y agregamos al combo
		    for (logica.DTTRegistro dtr : c.listarTipoRegistro(eventoSeleccionado, edicionSeleccionada)) {
		        comboBoxTipoRegistro.addItem(dtr);
		    }
        });
        
        lblAsistentes = new JLabel("Asistentes: ");
        lblAsistentes.setHorizontalAlignment(SwingConstants.CENTER);
        lblAsistentes.setBounds(22, 390, 262, 36);
        getContentPane().add(lblAsistentes);
        
        comboBoxAsistentes = new JComboBox<>();
        comboBoxAsistentes.setBounds(22, 437, 262, 46);
        getContentPane().add(comboBoxAsistentes);
        
        ControladorUsuario cu = ControladorUsuario.getInstance();
        
        for (DTAsistente asistente : cu.listarAsistentes()) {
        	comboBoxAsistentes.addItem(asistente);
        }
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(437, 409, 150, 35);
        getContentPane().add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {
            String nickAsistente = ((DTAsistente) comboBoxAsistentes.getSelectedItem()).getNick();
            String nombreTRegistro = ((DTTRegistro) comboBoxTipoRegistro.getSelectedItem()).getNombre();
            String nombreEdicion = (String) comboBoxEdiciones.getSelectedItem();
            String nickEvento = (String) comboBoxEventos.getSelectedItem();

            boolean error = c.darDeAltaRegistro(nickAsistente, nombreEdicion, nickEvento, nombreTRegistro);

            if (error) {
                JOptionPane.showMessageDialog(this, 
                    "No se puede registrar: cupo completo o asistente ya registrado",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Registro realizado con éxito",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
                // Opcional: actualizar el combo de tipo de registro para reflejar el nuevo cupo
            }
        });
	}

}
