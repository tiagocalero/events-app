package main.java.presentacion;

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
import javax.swing.JDesktopPane;
import java.awt.Dimension;

import main.java.logica.Asistente;
import main.java.logica.ControladorEvento;
import main.java.logica.ControladorUsuario;
import main.java.logica.DTAsistente;
import main.java.logica.DTEdicion;
import main.java.logica.DTOrganizador;
import main.java.logica.DTRegistro;
import main.java.logica.DTUsuario;
import main.java.logica.Organizador;

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
	private String RegEdicion;
	private String RegTipo;
	private String RegFecha;
	private float RegCosto;

	private JButton btnCerrar;
	private JTextField textInst;
	private JLabel lblInst;
	
    // Añadir una referencia al JDesktopPane
    private JDesktopPane desktopPane;

    public ConsultaUsuario(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
        initComponents();
    }
	
    public ConsultaUsuario() {
        // Constructor por defecto, en caso de que sea usado en algún otro lugar sin pasar el desktopPane
        initComponents();
    }

    private void initComponents() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar un Usuario");
		setBounds(30, 30, 685, 576);
		getContentPane().setLayout(null);
		
		lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(27, 12, 188, 36);
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblUsuarios);

        JPanel panelOrg = new JPanel();
        panelOrg.setBounds(209, 201, 447, 190);
        getContentPane().add(panelOrg);
        panelOrg.setLayout(null);
        
        JPanel panelOrg_2 = new JPanel();
        panelOrg_2.setBounds(276, 403, 447, 65);
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
            	borrarCampos();
            	if (nom != null && !nom.equals("Seleccione un usuario")) {
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
        Descripcion.setBounds(165, 49, 255, 129);
        panelOrg.add(Descripcion);
        
        txtLink = new JTextField();
        txtLink.setEditable(false);
        txtLink.setColumns(10);
        txtLink.setBounds(165, 0, 255, 37);
        panelOrg.add(txtLink);
        
        lblLink = new JLabel("Link:");
        lblLink.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLink.setBounds(26, 0, 121, 37);
        panelOrg.add(lblLink);
        
        lblFecha_1 = new JLabel("Descripcion:");
        lblFecha_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFecha_1.setBounds(26, 49, 121, 37);
        panelOrg.add(lblFecha_1);
        
        txtCorreo = new JTextField();
        txtCorreo.setEditable(false);
        txtCorreo.setColumns(10);
        txtCorreo.setBounds(374, 152, 255, 37);
        getContentPane().add(txtCorreo);
        
        lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
        lblFechaNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaNacimiento.setBounds(199, 250, 157, 37);
        getContentPane().add(lblFechaNacimiento);
        
        txtNac = new JTextField();
        txtNac.setEditable(false);
        txtNac.setColumns(10);
        txtNac.setBounds(374, 250, 255, 37);
        getContentPane().add(txtNac);
        
        lblRegistros = new JLabel("Registros: ");
        lblRegistros.setBounds(299, 423, 77, 15);
        getContentPane().add(lblRegistros);
        lblRegistros.setHorizontalAlignment(SwingConstants.LEFT);
        
        comboBoxRegistro = new JComboBox();
        comboBoxRegistro.setBounds(369, 409, 287, 43);
        comboBoxRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	llamarVentanaRegistro(e);
            }
        });
        getContentPane().add(comboBoxRegistro);
        
        lblEdiciones = new JLabel("Ediciones: ");
        lblEdiciones.setBounds(15, 12, 121, 37);
        panelOrg_2.add(lblEdiciones);
        lblEdiciones.setHorizontalAlignment(SwingConstants.LEFT);
        
        comboBoxEdicion = new JComboBox();
        comboBoxEdicion.setBounds(91, 9, 287, 43);
        comboBoxEdicion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	llamarVentanaEdicion(e);
            }
        });
        panelOrg_2.add(comboBoxEdicion);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(287, 497, 105, 27);
		btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	dispose();
            }
        });
        getContentPane().add(btnCerrar);
        
        textInst = new JTextField();
        textInst.setEditable(false);
        textInst.setColumns(10);
        textInst.setBounds(374, 299, 255, 37);
        getContentPane().add(textInst);
        
        lblInst = new JLabel("Institucion:");
        lblInst.setHorizontalAlignment(SwingConstants.RIGHT);
        lblInst.setBounds(235, 299, 121, 37);
        getContentPane().add(lblInst);
   
    }
    
    //Abre la ventana para consultar la Edicion
    public void llamarVentanaEdicion(ActionEvent e) {           	
		ControladorUsuario cu = ControladorUsuario.getInstance();
		String edicionSeleccionada = (String) comboBoxEdicion.getSelectedItem();
		if (edicionSeleccionada != null && !edicionSeleccionada.equals("Seleccione una Edicion")) {
			DTEdicion ed = cu.consultaOrganizador().stream()
				    .filter(edicion -> edicion.getNombre().equals(edicionSeleccionada))
				    .findFirst()
				    .orElse(null);
			String evento = (ed.getEvento());
			JInternalFrame c = new ConsultaEdicionDeEvento(desktopPane, evento, edicionSeleccionada);
			desktopPane.add(c);
	        centerInternalFrame(c);
	        c.setVisible(true);
	        c.toFront();
		}
	}
    
    //Abre la ventana para consultar el Registro
	public void llamarVentanaRegistro(ActionEvent e) {           
		String usuarioSeleccionado = (String) comboBoxUsuario.getSelectedItem();
		String registroSeleccionado = (String) comboBoxRegistro.getSelectedItem();
		if (usuarioSeleccionado != null && !usuarioSeleccionado.equals("Seleccione un Usuario") 
				&& registroSeleccionado != null && !registroSeleccionado.equals("Seleccione un Registro")) {
			
			JInternalFrame c = new ConsultaRegistro(usuarioSeleccionado, registroSeleccionado);
			desktopPane.add(c);
		    centerInternalFrame(c);
		    c.setVisible(true);
		    c.toFront();
		}
	}
    
	protected void consultaAsistente(ActionEvent e, DTAsistente a) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ControladorUsuario c = ControladorUsuario.getInstance();

		txtNick.setText(a.getNickname());
        txtNom.setText(a.getNombre());
        txtCorreo.setText(a.getCorreo());
        txtAp.setText(a.getApellido());
	    txtNac.setText(a.getNacimiento().format(formatter));
	    String inst = a.getInstitucion();
	    if (inst == null) inst = "Ninguna";
	    textInst.setText(inst);
	    
	    comboBoxRegistro.removeAllItems();
	    comboBoxRegistro.addItem("Seleccione un Registro");
	    
	    for (DTRegistro reg: c.consultaAsistente()) {	    	
	       	comboBoxRegistro.addItem(reg.getEdicion());
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
	    comboBoxEdicion.addItem("Seleccione una Edicion");
	    
	    for (DTEdicion ed: c.consultaOrganizador()) {
	       	comboBoxEdicion.addItem(ed.getNombre());
	    }
    }
	
	private void borrarCampos() {
		txtNick.setText("");
		txtNom.setText("");
    	txtCorreo.setText("");
    	txtAp.setText("");
    	txtNac.setText("");
    	txtLink.setText("");
    	Descripcion.setText("");
	}
    
    // Método para centrar las ventanas internas
    private void centerInternalFrame(JInternalFrame frame) {
        if (desktopPane != null) {
            Dimension desktopSize = desktopPane.getSize();
            Dimension frameSize = frame.getSize();
            if (frameSize.getWidth() > 0 && frameSize.getHeight() > 0) {
                int x = (desktopSize.width - frameSize.width) / 2;
                int y = (desktopSize.height - frameSize.height) / 2;
                frame.setLocation(x, y);
            }
        }
    }
}