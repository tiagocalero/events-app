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
import logica.DTEvento;
import javax.swing.JPanel;

public class ConsultaUsuario extends JInternalFrame {
	private JInternalFrame frmConsultaUsuario;
	private JLabel lblEventos;
	private JComboBox comboBoxEventos;
	private JLabel lblNombre;
	private JButton btnNewButton;
	private final JTextField txtEjemplo = new JTextField();
	private JTextField txtCc;
	private JLabel lblFecha;
	private JTextField textField_2;
	private JLabel lblCategoras;
	private JLabel lblEdiciones;
	private JComboBox comboBoxEventos_2;
	private JComboBox comboBoxEventos_3;
	private JTextField textField;
	private JLabel lblFechaNacimiento;
	private JTextField textField_1;
	private JTextField textField_3;
	private JLabel lblLink;
	private JLabel lblFecha_1;
	private JTextArea Descripcion;
	
	public ConsultaUsuario() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar un Usuario");
		setBounds(30, 30, 831, 752);
		getContentPane().setLayout(null);
		
		lblEventos = new JLabel("Usuarios");
		lblEventos.setBounds(27, 12, 188, 36);
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblEventos);

        comboBoxEventos = new JComboBox();
        comboBoxEventos.setBounds(27, 53, 188, 43);
        
        ControladorEvento c = ControladorEvento.getInstance();
        
        for (String e: c.listarEventos()) {
        	comboBoxEventos.addItem(e);
        }
        getContentPane().add(comboBoxEventos);
        
        lblNombre = new JLabel("Nickname:");
        lblNombre.setBounds(235, 54, 121, 37);
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        //lblNombre.setBounds(100, 100, 170, 14);
        getContentPane().add(lblNombre);
        txtEjemplo.setEditable(false);
        txtEjemplo.setBounds(374, 54, 255, 37);
        getContentPane().add(txtEjemplo);
        txtEjemplo.setColumns(10);
        
        btnNewButton = new JButton("Consultar");
        btnNewButton.setBounds(27, 114, 188, 37);
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdConsultar(e);
            }
        });
        getContentPane().add(btnNewButton);
        
        JLabel lblSigla = new JLabel("Nombre:");
        lblSigla.setHorizontalAlignment(SwingConstants.CENTER);
        lblSigla.setBounds(235, 103, 121, 37);
        getContentPane().add(lblSigla);
        
        txtCc = new JTextField();
        txtCc.setEditable(false);
        txtCc.setColumns(10);
        txtCc.setBounds(374, 103, 255, 37);
        getContentPane().add(txtCc);
        
        JLabel lblDescripcin = new JLabel("Correo:");
        lblDescripcin.setHorizontalAlignment(SwingConstants.CENTER);
        lblDescripcin.setBounds(235, 152, 121, 37);
        getContentPane().add(lblDescripcin);
        
        lblFecha = new JLabel("Apellido:");
        lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
        lblFecha.setBounds(235, 201, 121, 37);
        getContentPane().add(lblFecha);
        
        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setColumns(10);
        textField_2.setBounds(374, 201, 255, 37);
        getContentPane().add(textField_2);
        
        lblCategoras = new JLabel("Categorías: ");
        lblCategoras.setHorizontalAlignment(SwingConstants.LEFT);
        lblCategoras.setBounds(27, 513, 121, 37);
        getContentPane().add(lblCategoras);
        
        lblEdiciones = new JLabel("Ediciones: ");
        lblEdiciones.setHorizontalAlignment(SwingConstants.LEFT);
        lblEdiciones.setBounds(342, 513, 121, 37);
        getContentPane().add(lblEdiciones);
        
        comboBoxEventos_2 = new JComboBox();
        comboBoxEventos_2.setBounds(27, 562, 287, 146);
        getContentPane().add(comboBoxEventos_2);
        
        comboBoxEventos_3 = new JComboBox();
        comboBoxEventos_3.setBounds(342, 562, 287, 146);
        getContentPane().add(comboBoxEventos_3);
        
        textField = new JTextField();
        textField.setEditable(false);
        textField.setColumns(10);
        textField.setBounds(374, 152, 255, 37);
        getContentPane().add(textField);
        
        lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
        lblFechaNacimiento.setHorizontalAlignment(SwingConstants.CENTER);
        lblFechaNacimiento.setBounds(235, 250, 121, 37);
        getContentPane().add(lblFechaNacimiento);
        
        textField_1 = new JTextField();
        textField_1.setEditable(false);
        textField_1.setColumns(10);
        textField_1.setBounds(374, 250, 255, 37);
        getContentPane().add(textField_1);
        
        JPanel panel = new JPanel();
        panel.setBounds(233, 201, 421, 190);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        Descripcion = new JTextArea();
        Descripcion.setWrapStyleWord(true);
        Descripcion.setText("");
        Descripcion.setLineWrap(true);
        Descripcion.setEditable(false);
        Descripcion.setBounds(141, 49, 255, 129);
        panel.add(Descripcion);
        
        textField_3 = new JTextField();
        textField_3.setEditable(false);
        textField_3.setColumns(10);
        textField_3.setBounds(141, 0, 255, 37);
        panel.add(textField_3);
        
        lblLink = new JLabel("Link:");
        lblLink.setHorizontalAlignment(SwingConstants.CENTER);
        lblLink.setBounds(2, 0, 121, 37);
        panel.add(lblLink);
        
        lblFecha_1 = new JLabel("Descripcion:");
        lblFecha_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblFecha_1.setBounds(2, 49, 121, 37);
        panel.add(lblFecha_1);
	}

	protected void cmdConsultar(ActionEvent e) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ControladorEvento c = ControladorEvento.getInstance();
		
		DTEvento evento = c.informacionEvento((String) comboBoxEventos.getSelectedItem());
        txtEjemplo.setText(evento.getNombre());
        txtCc.setText(evento.getSigla());
        Descripcion.setText(evento.getDescripcion());
        textField_2.setText(evento.getFechaAlta().format(formatter));
        comboBoxEventos_2.removeAllItems();
        for (String cat: evento.getCategorias())
        	comboBoxEventos_2.addItem(cat);
        comboBoxEventos_3.removeAllItems();
        for (String ed: evento.getEdiciones())
        	comboBoxEventos_3.addItem(ed);
    }
}
