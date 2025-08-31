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

import logica.ControladorEvento;
import logica.DTTRegistro;
import logica.DTEvento;
import javax.swing.JPanel;

public class ConsultaTipoRegistro extends JInternalFrame {
	private JInternalFrame frmConsultaEventos;
	private JLabel lblEventos;
	private JLabel lblDesc;
	private JLabel lblCupo;
	private JLabel lblCosto;
	private JComboBox<String> comboBoxEventos;
	private JComboBox<String> comboBoxEdiciones;
	private JComboBox<String> comboBoxTipoRegistro;
	private JLabel lblNombre;
	private JLabel lblEdiciones;
	private JLabel lblTipoRegistro;
	private JTextField textFieldNombre;
	private JTextField textFieldDesc;
	private JTextField textFieldCupo;
	private JTextField textFieldCosto;



	
	public ConsultaTipoRegistro() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);

		setTitle("Consultar un Tipo de Registro");
		setBounds(30, 30, 734, 445);
		getContentPane().setLayout(null);
		
		lblEventos = new JLabel("Eventos:");
		lblEventos.setBounds(22, 29, 262, 36);
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblEventos);

        comboBoxEventos = new JComboBox();
        comboBoxEventos.setBounds(22, 76, 262, 36);
        
        ControladorEvento c = ControladorEvento.getInstance();
        
        for (String e: c.listarEventos()) {
        	comboBoxEventos.addItem(e);
        }
        getContentPane().add(comboBoxEventos);
        
        
        lblEdiciones = new JLabel("Ediciones: ");
        lblEdiciones.setHorizontalAlignment(SwingConstants.CENTER);
        lblEdiciones.setBounds(22, 134, 262, 36);
        getContentPane().add(lblEdiciones);
        
        comboBoxEdiciones = new JComboBox();
        comboBoxEdiciones.setBounds(22, 188, 262, 36);
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
        lblTipoRegistro.setBounds(12, 238, 270, 36);
        getContentPane().add(lblTipoRegistro);
        
        comboBoxTipoRegistro = new JComboBox<>();
        comboBoxTipoRegistro.setBounds(22, 285, 262, 36);
        getContentPane().add(comboBoxTipoRegistro);
        
        comboBoxEdiciones.addActionListener(e -> {
        	// Primero vaciamos el combo de ediciones
		    comboBoxTipoRegistro.removeAllItems();

		    // Obtenemos el evento seleccionado
		    String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
		    
		 // Obtenemos la edicion seleccionada
		    String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();

		    // Listamos las ediciones de ese evento y agregamos al combo
		    for (String nombreTRegistro : c.listarTipoRegistro(eventoSeleccionado, edicionSeleccionada)) {
		        comboBoxTipoRegistro.addItem(nombreTRegistro);
		    }
        });
        
        lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(441, 58, 100, 21);
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblNombre);

        
        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(441, 82, 249, 25);
        textFieldNombre.setEnabled(false);
        getContentPane().add(textFieldNombre);

        textFieldNombre.setText("Nombre del registro");
        
        lblDesc = new JLabel("Descripcion:");
		lblDesc.setBounds(433, 118, 123, 21);
        lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblDesc);
        
        textFieldDesc = new JTextField();
        textFieldDesc.setBounds(441, 140, 249, 65);
        textFieldDesc.setEnabled(false);
        getContentPane().add(textFieldDesc);

        textFieldDesc.setText("Descripcion del registro");
        
        lblCupo = new JLabel("Cupo:");
		lblCupo.setBounds(433, 216, 109, 22);
        lblCupo.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblCupo);
        
        textFieldCupo = new JTextField();
        textFieldCupo.setBounds(441, 249, 249, 25);
        textFieldCupo.setEnabled(false);
        getContentPane().add(textFieldCupo);

        textFieldCupo.setText("Cupo del registro");
        
        lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(433, 293, 115, 21);
        lblCosto.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblCosto);
        
        textFieldCosto = new JTextField();
        textFieldCosto.setBounds(441, 325, 249, 25);
        textFieldCosto.setEnabled(false);
        getContentPane().add(textFieldCosto);

        textFieldCosto.setText("Costo del registro");
        
        
        /*comboBoxTipoRegistro.addActionListener(e -> {
        	
        	String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
            String edicionSeleccionada = (String) comboBoxEdiciones.getSelectedItem();
            String tipoRegistroSeleccionado = (String) comboBoxTipoRegistro.getSelectedItem();

            DTTRegistro seleccionado = c.seleccionarTipoRegistro(tipoRegistroSeleccionado, eventoSeleccionado, edicionSeleccionada);
            
           // Mostramos los datos en los campos de texto
           textFieldNombre.setText(seleccionado.getNombre());
           textFieldDesc.setText(seleccionado.getDescripcion());
           textFieldCosto.setText(String.valueOf(seleccionado.getCosto()));
           textFieldCupo.setText(String.valueOf(seleccionado.getCupo()));
            
        });*/
                
        JButton btnConsulta = new JButton("Consultar");
        btnConsulta.setBounds(76, 359, 150, 35);
		btnConsulta.addActionListener(e -> {
			String nombreEvento = (String) comboBoxEventos.getSelectedItem();
			String nombreEdicion = (String) comboBoxEdiciones.getSelectedItem();
			String nombreTipoRegistro = (String) comboBoxTipoRegistro.getSelectedItem();
			
			DTTRegistro tr = c.mostrarTipoRegistro(nombreEvento, nombreEdicion, nombreTipoRegistro);
			
			textFieldNombre.setText(tr.getNombre());
		    textFieldDesc.setText(tr.getDescripcion());
		    textFieldCosto.setText(String.valueOf(tr.getCosto()));
		    textFieldCupo.setText(String.valueOf(tr.getCupo())); 
			
		});
		
		getContentPane().add(btnConsulta);
	}

}
