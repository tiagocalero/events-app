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
import logica.DTEdicion;
import logica.DTEvento;
import javax.swing.JPanel;
import java.awt.Font;

public class ConsultaEventos extends JInternalFrame {
	private JInternalFrame frmConsultaEventos;
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
	private JPanel panel;
	private JLabel lblEdNom;
	private JTextField txtEdNom;
	private JLabel lblEdSigla;
	private JTextField txtEdSigla;
	private JLabel lblEdOrg;
	private JTextField txtEdOrg;
	private JLabel lblEdFI;
	private JLabel lblEdFF;
	private JTextField txtEdFI;
	private JTextField txtEdFF;
	private JLabel lblEdFA;
	private JTextField txtEdFA;
	private JLabel lblEdCiudad;
	private JTextField txtEdCiudad;
	private JLabel lblEdPais;
	private JTextField txtEdPais;
	private JButton btnCerrar;
	
	public ConsultaEventos() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar un Evento");
		setBounds(30, 30, 878, 688);
		getContentPane().setLayout(null);

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
        comboBoxEdiciones.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String si = (String) comboBoxEdiciones.getSelectedItem();
        		if ( si != null ) consultaEdicion(e);
        	};
        });
        comboBoxEdiciones.setBounds(342, 310, 287, 43);
        
        getContentPane().add(comboBoxEdiciones);
        
        textDesc = new JTextArea();
        textDesc.setEditable(false);
        textDesc.setWrapStyleWord(true);
        textDesc.setLineWrap(true);
        textDesc.setBounds(359, 140, 255, 67);
        getContentPane().add(textDesc);
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(27, 440, 812, 157);
        getContentPane().add(panel);
        
        lblEdNom = new JLabel("Nombre:");
        lblEdNom.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdNom.setBounds(0, 0, 106, 37);
        panel.add(lblEdNom);
        
        txtEdNom = new JTextField();
        txtEdNom.setEditable(false);
        txtEdNom.setColumns(10);
        txtEdNom.setBounds(113, 0, 147, 37);
        panel.add(txtEdNom);
        
        lblEdSigla = new JLabel("Sigla:");
        lblEdSigla.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdSigla.setBounds(0, 49, 106, 37);
        panel.add(lblEdSigla);
        
        txtEdSigla = new JTextField();
        txtEdSigla.setEditable(false);
        txtEdSigla.setColumns(10);
        txtEdSigla.setBounds(113, 49, 147, 37);
        panel.add(txtEdSigla);
        
        lblEdOrg = new JLabel("Organizador:");
        lblEdOrg.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdOrg.setBounds(0, 96, 106, 37);
        panel.add(lblEdOrg);
        
        txtEdOrg = new JTextField();
        txtEdOrg.setEditable(false);
        txtEdOrg.setColumns(10);
        txtEdOrg.setBounds(113, 96, 147, 37);
        panel.add(txtEdOrg);
        
        lblEdFI = new JLabel("Fecha de Inicio:");
        lblEdFI.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdFI.setBounds(272, 0, 111, 37);
        panel.add(lblEdFI);
        
        lblEdFF = new JLabel("Fecha de Fin:");
        lblEdFF.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdFF.setBounds(272, 49, 111, 37);
        panel.add(lblEdFF);
        
        txtEdFI = new JTextField();
        txtEdFI.setEditable(false);
        txtEdFI.setColumns(10);
        txtEdFI.setBounds(385, 0, 147, 37);
        panel.add(txtEdFI);
        
        txtEdFF = new JTextField();
        txtEdFF.setEditable(false);
        txtEdFF.setColumns(10);
        txtEdFF.setBounds(385, 49, 147, 37);
        panel.add(txtEdFF);
        
        lblEdFA = new JLabel("Fecha de Alta:");
        lblEdFA.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdFA.setBounds(272, 96, 111, 37);
        panel.add(lblEdFA);
        
        txtEdFA = new JTextField();
        txtEdFA.setEditable(false);
        txtEdFA.setColumns(10);
        txtEdFA.setBounds(385, 96, 147, 37);
        panel.add(txtEdFA);
        
        lblEdCiudad = new JLabel("Ciudad:");
        lblEdCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdCiudad.setBounds(544, 0, 106, 37);
        panel.add(lblEdCiudad);
        
        txtEdCiudad = new JTextField();
        txtEdCiudad.setEditable(false);
        txtEdCiudad.setColumns(10);
        txtEdCiudad.setBounds(657, 0, 147, 37);
        panel.add(txtEdCiudad);
        
        lblEdPais = new JLabel("Pais:");
        lblEdPais.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdPais.setBounds(544, 49, 106, 37);
        panel.add(lblEdPais);
        
        txtEdPais = new JTextField();
        txtEdPais.setEditable(false);
        txtEdPais.setColumns(10);
        txtEdPais.setBounds(657, 49, 147, 37);
        panel.add(txtEdPais);
        
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(376, 609, 105, 27);
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	dispose();
            }
        });
        getContentPane().add(btnCerrar);
        
        JLabel lblInformacinDeLa = new JLabel("Información de la edición:");
        lblInformacinDeLa.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblInformacinDeLa.setHorizontalAlignment(SwingConstants.CENTER);
        lblInformacinDeLa.setBounds(27, 391, 812, 37);
        getContentPane().add(lblInformacinDeLa);
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
        for (String cat: evento.getCategorias()) {
        	comboBoxCategorias.addItem(cat);
        }
        comboBoxEdiciones.removeAllItems();
        for (String ed: evento.getEdiciones()) {
        	comboBoxEdiciones.addItem(ed);
        }
    }
	
	protected void consultaEdicion(ActionEvent e) {ControladorEvento c = ControladorEvento.getInstance();
	    DTEdicion ed = c.obtenerEdicion((String) comboBoxEdiciones.getSelectedItem(), (String) comboBoxEventos.getSelectedItem()).getDatos();
		txtEdNom.setText(ed.getNombre());
		txtEdSigla.setText(ed.getSigla());
		txtEdOrg.setText(ed.getOrganizador());
		txtEdFI.setText(ed.getFechaInicio().toString());
		txtEdFF.setText(ed.getFechaFin().toString());
		txtEdFA.setText(ed.getFechaAlta().toString());
		txtEdPais.setText(ed.getPais());
		txtEdCiudad.setText(ed.getCiudad());
	}
}
