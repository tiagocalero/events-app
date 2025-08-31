package presentacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logica.Asistente;
import logica.ControladorEvento;
import logica.ControladorUsuario;
import logica.DTAsistente;
import logica.DTEdicion;
import logica.DTOrganizador;
import logica.DTRegistro;
import logica.DTUsuario;
import logica.Organizador;

import javax.swing.JPanel;

public class ConsultaUsuario extends JInternalFrame {
	private JLabel lblUsuarios;
	private JComboBox comboBoxUsuario;
	private JLabel lblNombre;
	private final JTextField txtNick = new JTextField();
	private JTextField txtNom;
	private JLabel lblFecha;
	private JTextField txtAp;
	private JLabel lblRegistros;
	private JLabel lblEdiciones;
	private JComboBox comboBoxRegistro;
	private JComboBox comboBoxEdicion;
	private JTextField txtCorreo;
	private JLabel lblFechaNacimiento;
	private JTextField txtNac;
	private JTextField txtLink;
	private JLabel lblLink;
	private JLabel lblFecha_1;
	private JTextArea Descripcion;
	private DTUsuario u;
	private JTextField txtEdNom;
	private JTextField txtEdSigla;
	private JTextField txtEdOrg;
	private JTextField txtEdFI;
	private JTextField txtEdFF;
	private JTextField txtEdFA;
	private JTextField txtEdCiudad;
	private JTextField txtEdPais;

	private JTextField txtRgEd;
	private JTextField txtRgFecha;
	private JTextField txtRgCosto;
	private JTextField txtRgTipo;
	private String RegEdicion;
	private String RegTipo;
	private String RegFecha;
	private float RegCosto;

	private JButton btnCerrar;
	
	public ConsultaUsuario() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar un Usuario");
		setBounds(30, 30, 854, 798);
		getContentPane().setLayout(null);
		
		lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(27, 12, 188, 36);
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblUsuarios);

        JPanel panelOrg = new JPanel();
        panelOrg.setBounds(233, 201, 421, 190);
        getContentPane().add(panelOrg);
        panelOrg.setLayout(null);
        
        JPanel panelOrg_2 = new JPanel();
        panelOrg_2.setBounds(12, 400, 822, 310);
        getContentPane().add(panelOrg_2);
        panelOrg_2.setLayout(null);
        
        comboBoxUsuario = new JComboBox();
		comboBoxUsuario.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un usuario"}));
        comboBoxUsuario.setBounds(27, 53, 201, 43);
        ControladorUsuario c = ControladorUsuario.getInstance();
        for (String e: c.listarUsuarios()) {
        	comboBoxUsuario.addItem(e);
        }
        getContentPane().add(comboBoxUsuario);
        comboBoxUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String nom = (String) comboBoxUsuario.getSelectedItem();
            	
            	if (u != null && !u.equals("Seleccione un usuario")) {
            		u = c.elegirUsuario(nom);
            		if(u instanceof DTAsistente) {
            			panelOrg.setVisible(false);
            			panelOrg_2.setVisible(false);
            			DTAsistente a = (DTAsistente) u;
            			consultaAsistente(e, a);
            		} else {
            			assert u instanceof DTOrganizador;
            			panelOrg.setVisible(true);
            			panelOrg_2.setVisible(true);
            			DTOrganizador o = (DTOrganizador) u;
            			consultaOrganizador(e, o);
            		}
            	}
            }
        });
        
		
        
        lblNombre = new JLabel("Nickname:");
        lblNombre.setBounds(235, 54, 121, 37);
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        //lblNombre.setBounds(100, 100, 170, 14);
        getContentPane().add(lblNombre);
        txtNick.setEditable(false);
        txtNick.setBounds(374, 54, 255, 37);
        getContentPane().add(txtNick);
        txtNick.setColumns(10);
        
        
        JLabel lblSigla = new JLabel("Nombre:");
        lblSigla.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSigla.setBounds(235, 103, 121, 37);
        getContentPane().add(lblSigla);
        
        txtNom = new JTextField();
        txtNom.setEditable(false);
        txtNom.setColumns(10);
        txtNom.setBounds(374, 103, 255, 37);
        getContentPane().add(txtNom);
        
        JLabel lblDescripcin = new JLabel("Correo:");
        lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescripcin.setBounds(235, 152, 121, 37);
        getContentPane().add(lblDescripcin);
        
        txtAp = new JTextField();
        txtAp.setEditable(false);
        txtAp.setColumns(10);
        txtAp.setBounds(374, 201, 255, 37);
        getContentPane().add(txtAp);
        
        lblFecha = new JLabel("Apellido:");
        lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFecha.setBounds(235, 201, 121, 37);
        getContentPane().add(lblFecha);
        
        Descripcion = new JTextArea();
        Descripcion.setWrapStyleWord(true);
        Descripcion.setText("");
        Descripcion.setLineWrap(true);
        Descripcion.setEditable(false);
        Descripcion.setBounds(141, 49, 255, 129);
        panelOrg.add(Descripcion);
        
        txtLink = new JTextField();
        txtLink.setEditable(false);
        txtLink.setColumns(10);
        txtLink.setBounds(141, 0, 255, 37);
        panelOrg.add(txtLink);
        
        lblLink = new JLabel("Link:");
        lblLink.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLink.setBounds(2, 0, 121, 37);
        panelOrg.add(lblLink);
        
        lblFecha_1 = new JLabel("Descripcion:");
        lblFecha_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFecha_1.setBounds(2, 49, 121, 37);
        panelOrg.add(lblFecha_1);
        
        txtCorreo = new JTextField();
        txtCorreo.setEditable(false);
        txtCorreo.setColumns(10);
        txtCorreo.setBounds(374, 152, 255, 37);
        getContentPane().add(txtCorreo);
        
        lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
        lblFechaNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaNacimiento.setBounds(235, 250, 121, 37);
        getContentPane().add(lblFechaNacimiento);
        
        txtNac = new JTextField();
        txtNac.setEditable(false);
        txtNac.setColumns(10);
        txtNac.setBounds(374, 250, 255, 37);
        getContentPane().add(txtNac);
        
        lblRegistros = new JLabel("Registros: ");
        lblRegistros.setBounds(27, 423, 77, 15);
        getContentPane().add(lblRegistros);
        lblRegistros.setHorizontalAlignment(SwingConstants.LEFT);
        
        comboBoxRegistro = new JComboBox();
        comboBoxRegistro.setBounds(27, 449, 287, 43);
        comboBoxRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	consultaRegistro(e);
            }
        });
        getContentPane().add(comboBoxRegistro);
        
        lblEdiciones = new JLabel("Ediciones: ");
        lblEdiciones.setBounds(15, 12, 121, 37);
        panelOrg_2.add(lblEdiciones);
        lblEdiciones.setHorizontalAlignment(SwingConstants.LEFT);
        
        comboBoxEdicion = new JComboBox();
        comboBoxEdicion.setBounds(15, 49, 287, 43);
        comboBoxEdicion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	consultaEdicion(e);
            }
        });
        panelOrg_2.add(comboBoxEdicion);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 104, 822, 157);
        panelOrg_2.add(panel);
        
        JLabel lblEdNom = new JLabel("Nombre:");
        lblEdNom.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdNom.setBounds(0, 0, 106, 37);
        panel.add(lblEdNom);
        
        txtEdNom = new JTextField();
        txtEdNom.setHorizontalAlignment(SwingConstants.LEFT);
        txtEdNom.setEditable(false);
        txtEdNom.setColumns(10);
        txtEdNom.setBounds(113, 0, 147, 37);
        panel.add(txtEdNom);
        
        JLabel lblEdSigla = new JLabel("Sigla:");
        lblEdSigla.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdSigla.setBounds(0, 49, 106, 37);
        panel.add(lblEdSigla);
        
        txtEdSigla = new JTextField();
        txtEdSigla.setEditable(false);
        txtEdSigla.setColumns(10);
        txtEdSigla.setBounds(113, 49, 147, 37);
        panel.add(txtEdSigla);
        
        JLabel lblEdOrg = new JLabel("Organizador:");
        lblEdOrg.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdOrg.setBounds(0, 96, 106, 37);
        panel.add(lblEdOrg);
        
        txtEdOrg = new JTextField();
        txtEdOrg.setEditable(false);
        txtEdOrg.setColumns(10);
        txtEdOrg.setBounds(113, 96, 147, 37);
        panel.add(txtEdOrg);
        
        JLabel lblEdFI = new JLabel("Fecha de Inicio:");
        lblEdFI.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdFI.setBounds(272, 0, 111, 37);
        panel.add(lblEdFI);
        
        JLabel lblEdFF = new JLabel("Fecha de Fin:");
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
        
        JLabel lblEdFA = new JLabel("Fecha de Alta:");
        lblEdFA.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdFA.setBounds(272, 96, 111, 37);
        panel.add(lblEdFA);
        
        txtEdFA = new JTextField();
        txtEdFA.setEditable(false);
        txtEdFA.setColumns(10);
        txtEdFA.setBounds(385, 96, 147, 37);
        panel.add(txtEdFA);
        
        JLabel lblEdCiudad = new JLabel("Ciudad:");
        lblEdCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdCiudad.setBounds(544, 0, 106, 37);
        panel.add(lblEdCiudad);
        
        txtEdCiudad = new JTextField();
        txtEdCiudad.setEditable(false);
        txtEdCiudad.setColumns(10);
        txtEdCiudad.setBounds(657, 0, 147, 37);
        panel.add(txtEdCiudad);
        
        JLabel lblEdPais = new JLabel("Pais:");
        lblEdPais.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEdPais.setBounds(544, 49, 106, 37);
        panel.add(lblEdPais);
        
        txtEdPais = new JTextField();
        txtEdPais.setEditable(false);
        txtEdPais.setColumns(10);
        txtEdPais.setBounds(657, 49, 147, 37);
        panel.add(txtEdPais);

        JLabel lblRgEd = new JLabel("Edicion:");
        lblRgEd.setHorizontalAlignment(SwingConstants.RIGHT);
        lblRgEd.setBounds(27, 504, 106, 37);
        getContentPane().add(lblRgEd);
        
        JLabel lblRgFecha = new JLabel("Fecha:");
        lblRgFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        lblRgFecha.setBounds(27, 553, 106, 37);
        getContentPane().add(lblRgFecha);
        
        JLabel lblRgCosto = new JLabel("Costo:");
        lblRgCosto.setHorizontalAlignment(SwingConstants.RIGHT);
        lblRgCosto.setBounds(286, 504, 106, 37);
        getContentPane().add(lblRgCosto);
        
        txtRgEd = new JTextField();
        txtRgEd.setEditable(false);
        txtRgEd.setColumns(10);
        txtRgEd.setBounds(140, 504, 147, 37);
        getContentPane().add(txtRgEd);
        
        txtRgFecha = new JTextField();
        txtRgFecha.setEditable(false);
        txtRgFecha.setColumns(10);
        txtRgFecha.setBounds(140, 553, 147, 37);
        getContentPane().add(txtRgFecha);
        
        txtRgCosto = new JTextField();
        txtRgCosto.setEditable(false);
        txtRgCosto.setColumns(10);
        txtRgCosto.setBounds(397, 504, 147, 37);
        getContentPane().add(txtRgCosto);
        
        txtRgTipo = new JTextField();
        txtRgTipo.setEditable(false);
        txtRgTipo.setColumns(10);
        txtRgTipo.setBounds(397, 553, 147, 37);
        getContentPane().add(txtRgTipo);
        
        JLabel lblRgTipo = new JLabel("Tipo:");
        lblRgTipo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblRgTipo.setBounds(286, 553, 106, 37);
        getContentPane().add(lblRgTipo);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(706, 722, 105, 27);
		btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	dispose();
            }
        });
        getContentPane().add(btnCerrar);
        
        
	}

	protected void consultaAsistente(ActionEvent e, DTAsistente a) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ControladorUsuario c = ControladorUsuario.getInstance();

		txtNick.setText(a.getNickname());
        txtNom.setText(a.getNombre());
        txtCorreo.setText(a.getCorreo());
        txtAp.setText(a.getApellido());
	    txtNac.setText(a.getNacimiento().format(formatter));
	    
	    comboBoxRegistro.removeAllItems();
	    for (DTRegistro reg: c.consultaAsistente()) {
	    	RegEdicion = reg.getEdicion();
	    	RegFecha = reg.getFecha().format(formatter);
	    	RegCosto = reg.getCosto();
	    	RegTipo = reg.getTipo();
	    	
	       	comboBoxRegistro.addItem("<html>" + RegEdicion + "<br>" + RegFecha + " $" + RegCosto + "</html>" );
		}   
	}
	
	protected void consultaOrganizador(ActionEvent e, DTOrganizador o) {
		ControladorUsuario c = ControladorUsuario.getInstance();

		txtNick.setText(o.getNickname());
        txtNom.setText(o.getNombre());
        txtCorreo.setText(o.getCorreo());
		txtLink.setText(o.getLink());
	    Descripcion.setText(o.getDescripcion());

	    comboBoxEdicion.removeAllItems();
	    for (DTEdicion ed: c.consultaOrganizador()) {
	       	comboBoxEdicion.addItem(ed.getNombre());
	    }
    }
	
	protected void consultaEdicion(ActionEvent e) {
		ControladorEvento ce = ControladorEvento.getInstance();
		ControladorUsuario cu = ControladorUsuario.getInstance();
		Organizador org = (Organizador) cu.obtenerUsuario(u.getNickname());
		DTEdicion ed = ce.elegirEdicion(org.obtenerEdicion((String) comboBoxEdicion.getSelectedItem()));
		txtEdNom.setText(ed.getNombre());
		txtEdSigla.setText(ed.getSigla());
		txtEdOrg.setText(ed.getOrganizador());
		txtEdFI.setText(ed.getFechaInicio().toString());
		txtEdFF.setText(ed.getFechaFin().toString());
		txtEdFA.setText(ed.getFechaAlta().toString());
		txtEdPais.setText(ed.getPais());
		txtEdCiudad.setText(ed.getCiudad());
	}
	
	protected void consultaRegistro(ActionEvent e) {
		txtRgEd.setText(RegEdicion);
		txtRgFecha.setText(RegFecha);
		txtRgCosto.setText("$" + Float.toString(RegCosto));
		txtRgTipo.setText(RegTipo);
	}
}
