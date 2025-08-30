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
	private JLabel lblNombre_1;
	private JTextField textField;
	private JLabel lblOrganizador;
	private JTextField textField_1;
	private JLabel lblOrganizador_1;
	private JTextField textField_3;
	private JLabel lblNombre_2;
	private JLabel lblFechaDeFin;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblOrganizador_3;
	private JTextField textField_6;
	private JLabel lblNombre_3;
	private JTextField textField_7;
	private JLabel lblPais;
	private JTextField textField_8;
	
	public ConsultaEventos() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar un Evento");
		setBounds(30, 30, 1007, 594);
		getContentPane().setLayout(null);
		
		lblEventos = new JLabel("Eventos");
		lblEventos.setBounds(12, 0, 188, 36);
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblEventos);

        comboBoxEventos = new JComboBox();
        comboBoxEventos.setBounds(12, 41, 188, 43);
        
        ControladorEvento c = ControladorEvento.getInstance();
        
        for (String e: c.listarEventos()) {
        	comboBoxEventos.addItem(e);
        }
        getContentPane().add(comboBoxEventos);
        
        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(220, 42, 121, 37);
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        //lblNombre.setBounds(100, 100, 170, 14);
        getContentPane().add(lblNombre);
        txtEjemplo.setEditable(false);
        txtEjemplo.setBounds(359, 42, 255, 37);
        getContentPane().add(txtEjemplo);
        txtEjemplo.setColumns(10);
        
        btnNewButton = new JButton("Consultar");
        btnNewButton.setBounds(12, 102, 188, 37);
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdConsultar(e);
            }
        });
        getContentPane().add(btnNewButton);
        
        JLabel lblSigla = new JLabel("Sigla:");
        lblSigla.setHorizontalAlignment(SwingConstants.CENTER);
        lblSigla.setBounds(220, 91, 121, 37);
        getContentPane().add(lblSigla);
        
        txtCc = new JTextField();
        txtCc.setEditable(false);
        txtCc.setColumns(10);
        txtCc.setBounds(359, 91, 255, 37);
        getContentPane().add(txtCc);
        
        JLabel lblDescripcin = new JLabel("Descripción:");
        lblDescripcin.setHorizontalAlignment(SwingConstants.CENTER);
        lblDescripcin.setBounds(220, 140, 121, 37);
        getContentPane().add(lblDescripcin);
        
        lblFecha = new JLabel("Fecha:");
        lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
        lblFecha.setBounds(220, 221, 121, 37);
        getContentPane().add(lblFecha);
        
        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setColumns(10);
        textField_2.setBounds(359, 221, 255, 37);
        getContentPane().add(textField_2);
        
        lblCategoras = new JLabel("Categorías: ");
        lblCategoras.setHorizontalAlignment(SwingConstants.LEFT);
        lblCategoras.setBounds(27, 261, 121, 37);
        getContentPane().add(lblCategoras);
        
        lblEdiciones = new JLabel("Ediciones: ");
        lblEdiciones.setHorizontalAlignment(SwingConstants.LEFT);
        lblEdiciones.setBounds(342, 261, 121, 37);
        getContentPane().add(lblEdiciones);
        
        comboBoxEventos_2 = new JComboBox();
        comboBoxEventos_2.setBounds(27, 310, 287, 43);
        getContentPane().add(comboBoxEventos_2);
        
        comboBoxEventos_3 = new JComboBox();
        comboBoxEventos_3.setBounds(342, 310, 287, 43);
        getContentPane().add(comboBoxEventos_3);
        
        txtrEstaEsLa = new JTextArea();
        txtrEstaEsLa.setEditable(false);
        txtrEstaEsLa.setWrapStyleWord(true);
        txtrEstaEsLa.setLineWrap(true);
        txtrEstaEsLa.setBounds(359, 140, 255, 67);
        getContentPane().add(txtrEstaEsLa);
        
        JPanel panel = new JPanel();
        panel.setBounds(27, 382, 812, 157);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        lblNombre_1 = new JLabel("Nombre:");
        lblNombre_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre_1.setBounds(0, 0, 121, 37);
        panel.add(lblNombre_1);
        
        textField = new JTextField();
        textField.setEditable(false);
        textField.setColumns(10);
        textField.setBounds(113, 0, 147, 37);
        panel.add(textField);
        
        lblOrganizador = new JLabel("Sigla:");
        lblOrganizador.setHorizontalAlignment(SwingConstants.CENTER);
        lblOrganizador.setBounds(0, 49, 121, 37);
        panel.add(lblOrganizador);
        
        textField_1 = new JTextField();
        textField_1.setEditable(false);
        textField_1.setColumns(10);
        textField_1.setBounds(113, 49, 147, 37);
        panel.add(textField_1);
        
        lblOrganizador_1 = new JLabel("Organizador:");
        lblOrganizador_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblOrganizador_1.setBounds(0, 96, 121, 37);
        panel.add(lblOrganizador_1);
        
        textField_3 = new JTextField();
        textField_3.setEditable(false);
        textField_3.setColumns(10);
        textField_3.setBounds(113, 96, 147, 37);
        panel.add(textField_3);
        
        lblNombre_2 = new JLabel("Fecha de Inicio:");
        lblNombre_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre_2.setBounds(272, 0, 121, 37);
        panel.add(lblNombre_2);
        
        lblFechaDeFin = new JLabel("Fecha de Fin:");
        lblFechaDeFin.setHorizontalAlignment(SwingConstants.CENTER);
        lblFechaDeFin.setBounds(272, 49, 121, 37);
        panel.add(lblFechaDeFin);
        
        textField_4 = new JTextField();
        textField_4.setEditable(false);
        textField_4.setColumns(10);
        textField_4.setBounds(385, 0, 147, 37);
        panel.add(textField_4);
        
        textField_5 = new JTextField();
        textField_5.setEditable(false);
        textField_5.setColumns(10);
        textField_5.setBounds(385, 49, 147, 37);
        panel.add(textField_5);
        
        lblOrganizador_3 = new JLabel("Fecha de alta:");
        lblOrganizador_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblOrganizador_3.setBounds(272, 96, 121, 37);
        panel.add(lblOrganizador_3);
        
        textField_6 = new JTextField();
        textField_6.setEditable(false);
        textField_6.setColumns(10);
        textField_6.setBounds(385, 96, 147, 37);
        panel.add(textField_6);
        
        lblNombre_3 = new JLabel("Ciudad:");
        lblNombre_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre_3.setBounds(544, 0, 121, 37);
        panel.add(lblNombre_3);
        
        textField_7 = new JTextField();
        textField_7.setEditable(false);
        textField_7.setColumns(10);
        textField_7.setBounds(657, 0, 147, 37);
        panel.add(textField_7);
        
        lblPais = new JLabel("Pais:");
        lblPais.setHorizontalAlignment(SwingConstants.CENTER);
        lblPais.setBounds(544, 49, 121, 37);
        panel.add(lblPais);
        
        textField_8 = new JTextField();
        textField_8.setEditable(false);
        textField_8.setColumns(10);
        textField_8.setBounds(657, 49, 147, 37);
        panel.add(textField_8);
	}

	protected void cmdConsultar(ActionEvent e) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ControladorEvento c = ControladorEvento.getInstance();
		
		DTEvento evento = c.informacionEvento((String) comboBoxEventos.getSelectedItem());
        txtEjemplo.setText(evento.getNombre());
        txtCc.setText(evento.getSigla());
        txtrEstaEsLa.setText(evento.getDescripcion());
        textField_2.setText(evento.getFechaAlta().format(formatter));
        comboBoxEventos_2.removeAllItems();
        for (String cat: evento.getCategorias())
        	comboBoxEventos_2.addItem(cat);
        comboBoxEventos_3.removeAllItems();
        for (String ed: evento.getEdiciones())
        	comboBoxEventos_3.addItem(ed);
    }
}
