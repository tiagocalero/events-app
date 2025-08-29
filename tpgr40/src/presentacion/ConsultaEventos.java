package presentacion;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.DropMode;
import logica.ControladorEvento;
import logica.DTEvento;

public class ConsultaEventos extends JInternalFrame {
	private JInternalFrame frmConsultaEventos;
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
	private JTextArea txtrEstaEsLa;
	
	public ConsultaEventos() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar un Evento");
		setBounds(30, 30, 700, 600);
		getContentPane().setLayout(null);
		
		lblEventos = new JLabel("Eventos");
		lblEventos.setBounds(0, 0, 492, 60);
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblEventos);

        comboBoxEventos = new JComboBox();
        comboBoxEventos.setBounds(27, 53, 188, 43);
        
        ControladorEvento c = ControladorEvento.getInstance();
        comboBoxEventos.addItem("Selecciona un Evento");
        
        for (String e: c.listarEventos()) {
        	comboBoxEventos.addItem(e);
        }
        getContentPane().add(comboBoxEventos);
        
        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(235, 54, 121, 37);
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        //lblNombre.setBounds(100, 100, 170, 14);
        getContentPane().add(lblNombre);
        txtEjemplo.setEditable(false);
        txtEjemplo.setText("Congreso Internacional de Cardiología");
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
        
        JLabel lblSigla = new JLabel("Sigla:");
        lblSigla.setHorizontalAlignment(SwingConstants.CENTER);
        lblSigla.setBounds(235, 103, 121, 37);
        getContentPane().add(lblSigla);
        
        txtCc = new JTextField();
        txtCc.setEditable(false);
        txtCc.setText("CIC");
        txtCc.setColumns(10);
        txtCc.setBounds(374, 103, 255, 37);
        getContentPane().add(txtCc);
        
        JLabel lblDescripcin = new JLabel("Descripción:");
        lblDescripcin.setHorizontalAlignment(SwingConstants.CENTER);
        lblDescripcin.setBounds(235, 152, 121, 37);
        getContentPane().add(lblDescripcin);
        
        lblFecha = new JLabel("Fecha:");
        lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
        lblFecha.setBounds(235, 293, 121, 37);
        getContentPane().add(lblFecha);
        
        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setText("26/08/2025");
        textField_2.setColumns(10);
        textField_2.setBounds(374, 293, 255, 37);
        getContentPane().add(textField_2);
        
        lblCategoras = new JLabel("Categorías: ");
        lblCategoras.setHorizontalAlignment(SwingConstants.LEFT);
        lblCategoras.setBounds(27, 361, 121, 37);
        getContentPane().add(lblCategoras);
        
        lblEdiciones = new JLabel("Ediciones: ");
        lblEdiciones.setHorizontalAlignment(SwingConstants.LEFT);
        lblEdiciones.setBounds(342, 361, 121, 37);
        getContentPane().add(lblEdiciones);
        
        comboBoxEventos_2 = new JComboBox();
        comboBoxEventos_2.setBounds(27, 410, 287, 146);
        comboBoxEventos_2.addItem("Medicina");
        comboBoxEventos_2.addItem("Biología");
        getContentPane().add(comboBoxEventos_2);
        
        comboBoxEventos_3 = new JComboBox();
        comboBoxEventos_3.setBounds(342, 410, 287, 146);
        comboBoxEventos_3.addItem("Edición 2024");
        comboBoxEventos_3.addItem("Edición 2025");
        getContentPane().add(comboBoxEventos_3);
        
        txtrEstaEsLa = new JTextArea();
        txtrEstaEsLa.setEditable(false);
        txtrEstaEsLa.setWrapStyleWord(true);
        txtrEstaEsLa.setText("Esta es la descripción del Congreso Internaciol de Cardiología");
        txtrEstaEsLa.setLineWrap(true);
        txtrEstaEsLa.setBounds(374, 152, 255, 129);
        getContentPane().add(txtrEstaEsLa);
	}

	protected void cmdConsultar(ActionEvent e) {
		ControladorEvento c = ControladorEvento.getInstance();
		DTEvento evento = c.informacionEvento((String) comboBoxEventos.getSelectedItem());
        txtEjemplo.setText(evento.getNombre());
        txtCc.setText(evento.getSigla());
        txtrEstaEsLa.setText(evento.getDescripcion());
        comboBoxEventos_2.removeAllItems();
        for (String cat: evento.getCategorias())
        	comboBoxEventos_2.addItem(cat);
    }
}
