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

public class ConsultaEventos extends JInternalFrame {
	private JInternalFrame frmConsultaEventos;
	private JComboBox comboEventos;
	private JLabel lblNombre;
	private JButton btnConsultar;
	private final JTextField textNombre = new JTextField();
	private JTextField textSigla;
	private JLabel lblFecha;
	private JTextField textFecha;
	private JLabel lblCategoras;
	private JLabel lblEdiciones;
	private JComboBox comboCategorias;
	private JComboBox comboEdiciones;
	private JTextArea textDesc;
	
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

        comboEventos = new JComboBox();
        comboEventos.setBounds(27, 53, 218, 43);
        
        ControladorEvento c = ControladorEvento.getInstance();
        
        for (String e: c.listarEventos()) {
        	comboEventos.addItem(e);
        }
        getContentPane().add(comboEventos);
        
        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(235, 54, 121, 37);
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblNombre);
        textNombre.setEditable(false);
        textNombre.setBounds(374, 54, 255, 37);
        getContentPane().add(textNombre);
        textNombre.setColumns(10);
        
        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(27, 114, 218, 37);
		btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { consultar(e); }
        });
        getContentPane().add(btnConsultar);
        
        JLabel lblSigla = new JLabel("Sigla:");
        lblSigla.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSigla.setBounds(235, 103, 121, 37);
        getContentPane().add(lblSigla);
        
        textSigla = new JTextField();
        textSigla.setEditable(false);
        textSigla.setColumns(10);
        textSigla.setBounds(374, 103, 255, 37);
        getContentPane().add(textSigla);
        
        JLabel lblDescripcin = new JLabel("Descripción:");
        lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescripcin.setBounds(235, 152, 121, 37);
        getContentPane().add(lblDescripcin);
        
        lblFecha = new JLabel("Fecha:");
        lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFecha.setBounds(235, 293, 121, 37);
        getContentPane().add(lblFecha);
        
        textFecha = new JTextField();
        textFecha.setEditable(false);
        textFecha.setColumns(10);
        textFecha.setBounds(374, 293, 255, 37);
        getContentPane().add(textFecha);
        
        lblCategoras = new JLabel("Categorías: ");
        lblCategoras.setHorizontalAlignment(SwingConstants.LEFT);
        lblCategoras.setBounds(27, 361, 121, 37);
        getContentPane().add(lblCategoras);
        
        lblEdiciones = new JLabel("Ediciones: ");
        lblEdiciones.setHorizontalAlignment(SwingConstants.LEFT);
        lblEdiciones.setBounds(342, 361, 121, 37);
        getContentPane().add(lblEdiciones);
        
        comboCategorias = new JComboBox();
        comboCategorias.setBounds(27, 410, 287, 27);
        getContentPane().add(comboCategorias);
        
        comboEdiciones = new JComboBox();
        comboEdiciones.setBounds(342, 410, 287, 27);
        getContentPane().add(comboEdiciones);
        
        textDesc = new JTextArea();
        textDesc.setEditable(false);
        textDesc.setWrapStyleWord(true);
        textDesc.setLineWrap(true);
        textDesc.setBounds(374, 152, 255, 129);
        getContentPane().add(textDesc);
	}

	protected void consultar(ActionEvent e) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ControladorEvento c = ControladorEvento.getInstance();
		
		DTEvento evento = c.informacionEvento((String) comboEventos.getSelectedItem());
        textNombre.setText(evento.getNombre());
        textSigla.setText(evento.getSigla());
        textDesc.setText(evento.getDescripcion());
        textFecha.setText(evento.getFechaAlta().format(formatter));
        comboCategorias.removeAllItems();
        for (String cat: evento.getCategorias())
        	comboCategorias.addItem(cat);
        comboEdiciones.removeAllItems();
        for (String ed: evento.getEdiciones())
        	comboEdiciones.addItem(ed);
    }
}
