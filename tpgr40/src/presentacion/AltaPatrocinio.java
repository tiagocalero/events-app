package presentacion;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AltaPatrocinio {
	private JInternalFrame frmConsultaEventos;
	private JLabel lblEventos;
	private JComboBox comboBoxEventos;
	private JLabel lblNombre;
	private JButton btnNewButton;
	private final JTextField txtEjemplo = new JTextField();
	private JComboBox comboBoxEventos_2;
	private JButton btnNewButton_3;
	private JComboBox comboBoxEventos_3;
	private JComboBox comboBoxEventos_4;
	private JTextField textField_3;
	
	public AltaPatrocinio() {
		
		frmConsultaEventos = new JInternalFrame();
		frmConsultaEventos.setResizable(true);
		frmConsultaEventos.setIconifiable(true);
		frmConsultaEventos.setMaximizable(true);
		frmConsultaEventos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmConsultaEventos.setClosable(true);
		frmConsultaEventos.setTitle("Alta de Patrocinio");
		frmConsultaEventos.setBounds(30, 30, 833, 430);
		frmConsultaEventos.getContentPane().setLayout(null);
		
		lblEventos = new JLabel("Datos del Usuario");
		lblEventos.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEventos.setBounds(12, 0, 255, 60);
        lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
        frmConsultaEventos.getContentPane().add(lblEventos);
        
                
                comboBoxEventos = new JComboBox();
                comboBoxEventos.setModel(new DefaultComboBoxModel(new String[] {"Selecciona un Evento", "Evento 1", "Evento 2"}));
                comboBoxEventos.setBounds(27, 53, 226, 43);
                
        comboBoxEventos.addItem("Selecciona un Evento");
        comboBoxEventos.addItem("Evento 1");
        comboBoxEventos.addItem("Evento 2");
        frmConsultaEventos.getContentPane().add(comboBoxEventos);
        
        lblNombre = new JLabel("Aporte Económico:");
        lblNombre.setBounds(386, 114, 139, 37);
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        //lblNombre.setBounds(100, 100, 170, 14);
        frmConsultaEventos.getContentPane().add(lblNombre);
        txtEjemplo.setBounds(536, 114, 226, 37);
        frmConsultaEventos.getContentPane().add(txtEjemplo);
        txtEjemplo.setColumns(10);
        
        btnNewButton = new JButton("Seleccionar");
        btnNewButton.setBounds(47, 114, 188, 37);
        frmConsultaEventos.getContentPane().add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Crear");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnNewButton_1.setBounds(556, 265, 188, 37);
        frmConsultaEventos.getContentPane().add(btnNewButton_1);
        
        JComboBox comboBoxEventos_1 = new JComboBox();
        comboBoxEventos_1.setModel(new DefaultComboBoxModel(new String[] {"Selecciona una edición"}));
        comboBoxEventos_1.setBounds(27, 163, 226, 43);
        frmConsultaEventos.getContentPane().add(comboBoxEventos_1);
        
        JButton btnNewButton_2 = new JButton("Seleccionar");
        btnNewButton_2.setBounds(47, 224, 188, 37);
        frmConsultaEventos.getContentPane().add(btnNewButton_2);
        
        comboBoxEventos_2 = new JComboBox();
        comboBoxEventos_2.setModel(new DefaultComboBoxModel(new String[] {"Selecciona una Institución"}));
        comboBoxEventos_2.setBounds(27, 273, 226, 43);
        frmConsultaEventos.getContentPane().add(comboBoxEventos_2);
        
        btnNewButton_3 = new JButton("Seleccionar");
        btnNewButton_3.setBounds(47, 334, 188, 37);
        frmConsultaEventos.getContentPane().add(btnNewButton_3);
        
        comboBoxEventos_3 = new JComboBox();
        comboBoxEventos_3.setModel(new DefaultComboBoxModel(new String[] {"Selecciona un Tipo de Registro"}));
        comboBoxEventos_3.setBounds(536, 163, 226, 43);
        frmConsultaEventos.getContentPane().add(comboBoxEventos_3);
        
        comboBoxEventos_4 = new JComboBox();
        comboBoxEventos_4.setModel(new DefaultComboBoxModel(new String[] {"Platino", "Oro", "Plata", "Bronce"}));
        comboBoxEventos_4.setBounds(536, 53, 226, 43);
        frmConsultaEventos.getContentPane().add(comboBoxEventos_4);
        
        JLabel lblNivelPatrocinio = new JLabel("Nivel Patrocinio:");
        lblNivelPatrocinio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNivelPatrocinio.setBounds(404, 53, 121, 37);
        frmConsultaEventos.getContentPane().add(lblNivelPatrocinio);
        
        JLabel lblTipoDeRegistros = new JLabel("Tipo de Registros Gratutitos:");
        lblTipoDeRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipoDeRegistros.setBounds(309, 166, 216, 37);
        frmConsultaEventos.getContentPane().add(lblTipoDeRegistros);
        
        JLabel lblNombre_1 = new JLabel("Cantidad de Registros Gratuitos:");
        lblNombre_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre_1.setBounds(291, 215, 234, 37);
        frmConsultaEventos.getContentPane().add(lblNombre_1);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(536, 215, 226, 37);
        frmConsultaEventos.getContentPane().add(textField_3);
        
        JButton btnNewButton_1_1 = new JButton("Cancelar");
        btnNewButton_1_1.setBounds(556, 314, 188, 37);
        frmConsultaEventos.getContentPane().add(btnNewButton_1_1);

		
		
	}
}
