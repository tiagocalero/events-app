package presentacion;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class ConsultaEventos {
	private JInternalFrame frmConsultaEventos;
	private JLabel lblEventos;
	private JComboBox comboBoxEventos;
	private JLabel lblNombre;
	private JButton btnNewButton;
	private final JTextField txtEjemplo = new JTextField();
	private JTextField txtCc;
	private JTextField txtEstaEsLa;
	private JLabel lblFecha;
	private JTextField textField_2;
	private JLabel lblCategoras;
	private JLabel lblEdiciones;
	private JComboBox comboBoxEventos_2;
	private JComboBox comboBoxEventos_3;
	
	public ConsultaEventos() {
		
		frmConsultaEventos = new JInternalFrame();
		frmConsultaEventos.setResizable(true);
		frmConsultaEventos.setIconifiable(true);
		frmConsultaEventos.setMaximizable(true);
		frmConsultaEventos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmConsultaEventos.setClosable(true);
		frmConsultaEventos.setTitle("Consultar un Evento");
		frmConsultaEventos.setBounds(30, 30, 700, 600);
		frmConsultaEventos.getContentPane().setLayout(null);
		
		lblEventos = new JLabel("Eventos");
		lblEventos.setBounds(0, 0, 492, 60);
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        frmConsultaEventos.getContentPane().add(lblEventos);
        
                
                comboBoxEventos = new JComboBox();
                comboBoxEventos.setBounds(27, 53, 188, 146);
                
        comboBoxEventos.addItem("Selecciona un Evento");
        comboBoxEventos.addItem("Evento 1");
        comboBoxEventos.addItem("Evento 2");
        frmConsultaEventos.getContentPane().add(comboBoxEventos);
        
        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(235, 54, 121, 37);
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        //lblNombre.setBounds(100, 100, 170, 14);
        frmConsultaEventos.getContentPane().add(lblNombre);
        txtEjemplo.setEditable(false);
        txtEjemplo.setText("Congreso Internacional de Cardiología");
        txtEjemplo.setBounds(374, 54, 255, 37);
        frmConsultaEventos.getContentPane().add(txtEjemplo);
        txtEjemplo.setColumns(10);
        
        btnNewButton = new JButton("Consultar");
        btnNewButton.setBounds(27, 234, 188, 37);
        frmConsultaEventos.getContentPane().add(btnNewButton);
        
        JLabel lblSigla = new JLabel("Sigla:");
        lblSigla.setHorizontalAlignment(SwingConstants.CENTER);
        lblSigla.setBounds(235, 103, 121, 37);
        frmConsultaEventos.getContentPane().add(lblSigla);
        
        txtCc = new JTextField();
        txtCc.setEditable(false);
        txtCc.setText("CC");
        txtCc.setColumns(10);
        txtCc.setBounds(374, 103, 255, 37);
        frmConsultaEventos.getContentPane().add(txtCc);
        
        JLabel lblDescripcin = new JLabel("Descripción:");
        lblDescripcin.setHorizontalAlignment(SwingConstants.CENTER);
        lblDescripcin.setBounds(235, 152, 121, 37);
        frmConsultaEventos.getContentPane().add(lblDescripcin);
        
        txtEstaEsLa = new JTextField();
        txtEstaEsLa.setEditable(false);
        txtEstaEsLa.setText("Esta es la descripción del Congreso Internacional de Cardiología");
        txtEstaEsLa.setColumns(10);
        txtEstaEsLa.setBounds(374, 152, 255, 129);
        frmConsultaEventos.getContentPane().add(txtEstaEsLa);
        
        lblFecha = new JLabel("Fecha:");
        lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
        lblFecha.setBounds(235, 293, 121, 37);
        frmConsultaEventos.getContentPane().add(lblFecha);
        
        textField_2 = new JTextField();
        textField_2.setText("Ejemplo");
        textField_2.setColumns(10);
        textField_2.setBounds(374, 293, 255, 37);
        frmConsultaEventos.getContentPane().add(textField_2);
        
        lblCategoras = new JLabel("Categorías: ");
        lblCategoras.setHorizontalAlignment(SwingConstants.LEFT);
        lblCategoras.setBounds(27, 361, 121, 37);
        frmConsultaEventos.getContentPane().add(lblCategoras);
        
        lblEdiciones = new JLabel("Ediciones: ");
        lblEdiciones.setHorizontalAlignment(SwingConstants.LEFT);
        lblEdiciones.setBounds(342, 361, 121, 37);
        frmConsultaEventos.getContentPane().add(lblEdiciones);
        
        comboBoxEventos_2 = new JComboBox();
        comboBoxEventos_2.setBounds(27, 410, 287, 146);
        frmConsultaEventos.getContentPane().add(comboBoxEventos_2);
        
        comboBoxEventos_3 = new JComboBox();
        comboBoxEventos_3.setBounds(342, 410, 287, 146);
        frmConsultaEventos.getContentPane().add(comboBoxEventos_3);

		
		
	}
}
