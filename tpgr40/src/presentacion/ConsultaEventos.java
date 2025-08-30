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
	private JButton btnConsulta;
	private final JTextField textNombre = new JTextField();
	private JTextField textSigla;
	private JLabel lblFecha;
	private JTextField textFecha;
	private JLabel lblCategoras;
	private JLabel lblEdiciones;
	private JComboBox comboBoxCategorias;
	private JComboBox comboBoxEdiciones;
	private JTextArea textDesc;
	private JLabel lblNombre_1;
	private JTextField textNombreEd;
	private JLabel lblOrganizador;
	private JLabel lblOrganizador_1;
	private JTextField textOrgEd;
	private JLabel lblNombre_2;
	private JLabel lblFechaDeFin;
	private JTextField textFechaIniEd;
	private JTextField textFechaFinEd;
	private JLabel lblOrganizador_3;
	private JTextField textFechaAltaEd;
	private JLabel lblNombre_3;
	private JTextField textCiudadEd;
	private JLabel lblPais;
	private JTextField textPaisEd;
	
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
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        //lblNombre.setBounds(100, 100, 170, 14);
        getContentPane().add(lblNombre);
        textNombre.setEditable(false);
        textNombre.setBounds(359, 42, 255, 37);
        getContentPane().add(textNombre);
        textNombre.setColumns(10);
        
        comboBoxEventos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consulta(e);
            }
        });
        
        JLabel lblSigla = new JLabel("Sigla:");
        lblSigla.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSigla.setBounds(220, 91, 121, 37);
        getContentPane().add(lblSigla);
        
        textSigla = new JTextField();
        textSigla.setEditable(false);
        textSigla.setColumns(10);
        textSigla.setBounds(359, 91, 255, 37);
        getContentPane().add(textSigla);
        
        JLabel lblDescripcin = new JLabel("Descripción:");
        lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescripcin.setBounds(220, 140, 121, 37);
        getContentPane().add(lblDescripcin);
        
        lblFecha = new JLabel("Fecha:");
        lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFecha.setBounds(220, 221, 121, 37);
        getContentPane().add(lblFecha);
        
        textFecha = new JTextField();
        textFecha.setEditable(false);
        textFecha.setColumns(10);
        textFecha.setBounds(359, 221, 255, 37);
        getContentPane().add(textFecha);
        
        lblCategoras = new JLabel("Categorías: ");
        lblCategoras.setHorizontalAlignment(SwingConstants.LEFT);
        lblCategoras.setBounds(27, 261, 121, 37);
        getContentPane().add(lblCategoras);
        
        lblEdiciones = new JLabel("Ediciones: ");
        lblEdiciones.setHorizontalAlignment(SwingConstants.LEFT);
        lblEdiciones.setBounds(342, 261, 121, 37);
        getContentPane().add(lblEdiciones);
        
        comboBoxCategorias = new JComboBox();
        comboBoxCategorias.setBounds(27, 310, 287, 43);
        getContentPane().add(comboBoxCategorias);
        
        comboBoxEdiciones = new JComboBox();
        comboBoxEdiciones.setBounds(342, 310, 287, 43);
        getContentPane().add(comboBoxEdiciones);
        
        textDesc = new JTextArea();
        textDesc.setEditable(false);
        textDesc.setWrapStyleWord(true);
        textDesc.setLineWrap(true);
        textDesc.setBounds(359, 140, 255, 67);
        getContentPane().add(textDesc);
        
        JPanel panel = new JPanel();
        panel.setBounds(27, 382, 812, 157);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        lblNombre_1 = new JLabel("Nombre:");
        lblNombre_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre_1.setBounds(0, 0, 106, 37);
        panel.add(lblNombre_1);
        
        textNombreEd = new JTextField();
        textNombreEd.setEditable(false);
        textNombreEd.setColumns(10);
        textNombreEd.setBounds(113, 0, 147, 37);
        panel.add(textNombreEd);
        
        lblOrganizador = new JLabel("Sigla:");
        lblOrganizador.setHorizontalAlignment(SwingConstants.RIGHT);
        lblOrganizador.setBounds(0, 49, 106, 37);
        panel.add(lblOrganizador);
        
        JTextField textSiglaEd = new JTextField();
        textSiglaEd.setEditable(false);
        textSiglaEd.setColumns(10);
        textSiglaEd.setBounds(113, 49, 147, 37);
        panel.add(textSiglaEd);
        
        lblOrganizador_1 = new JLabel("Organizador:");
        lblOrganizador_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblOrganizador_1.setBounds(0, 96, 106, 37);
        panel.add(lblOrganizador_1);
        
        textOrgEd = new JTextField();
        textOrgEd.setEditable(false);
        textOrgEd.setColumns(10);
        textOrgEd.setBounds(113, 96, 147, 37);
        panel.add(textOrgEd);
        
        lblNombre_2 = new JLabel("Fecha de Inicio:");
        lblNombre_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre_2.setBounds(272, 0, 111, 37);
        panel.add(lblNombre_2);
        
        lblFechaDeFin = new JLabel("Fecha de Fin:");
        lblFechaDeFin.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaDeFin.setBounds(272, 49, 111, 37);
        panel.add(lblFechaDeFin);
        
        textFechaIniEd = new JTextField();
        textFechaIniEd.setEditable(false);
        textFechaIniEd.setColumns(10);
        textFechaIniEd.setBounds(385, 0, 147, 37);
        panel.add(textFechaIniEd);
        
        textFechaFinEd = new JTextField();
        textFechaFinEd.setEditable(false);
        textFechaFinEd.setColumns(10);
        textFechaFinEd.setBounds(385, 49, 147, 37);
        panel.add(textFechaFinEd);
        
        lblOrganizador_3 = new JLabel("Fecha de alta:");
        lblOrganizador_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblOrganizador_3.setBounds(272, 96, 111, 37);
        panel.add(lblOrganizador_3);
        
        textFechaAltaEd = new JTextField();
        textFechaAltaEd.setEditable(false);
        textFechaAltaEd.setColumns(10);
        textFechaAltaEd.setBounds(385, 96, 147, 37);
        panel.add(textFechaAltaEd);
        
        lblNombre_3 = new JLabel("Ciudad:");
        lblNombre_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre_3.setBounds(544, 0, 106, 37);
        panel.add(lblNombre_3);
        
        textCiudadEd = new JTextField();
        textCiudadEd.setEditable(false);
        textCiudadEd.setColumns(10);
        textCiudadEd.setBounds(657, 0, 147, 37);
        panel.add(textCiudadEd);
        
        lblPais = new JLabel("Pais:");
        lblPais.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPais.setBounds(544, 49, 106, 37);
        panel.add(lblPais);
        
        textPaisEd = new JTextField();
        textPaisEd.setEditable(false);
        textPaisEd.setColumns(10);
        textPaisEd.setBounds(657, 49, 147, 37);
        panel.add(textPaisEd);
	}

	protected void consulta(ActionEvent e) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ControladorEvento c = ControladorEvento.getInstance();
		
		DTEvento evento = c.informacionEvento((String) comboBoxEventos.getSelectedItem());
        textNombre.setText(evento.getNombre());
        textSigla.setText(evento.getSigla());
        textDesc.setText(evento.getDescripcion());
        textFecha.setText(evento.getFechaAlta().format(formatter));
        comboBoxCategorias.removeAllItems();
        for (String cat: evento.getCategorias())
        	comboBoxCategorias.addItem(cat);
        comboBoxEdiciones.removeAllItems();
        for (String ed: evento.getEdiciones())
        	comboBoxEdiciones.addItem(ed);
    }
}
