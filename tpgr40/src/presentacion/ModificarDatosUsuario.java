package presentacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logica.ControladorUsuario;
import logica.DTUsuario;
import logica.DTAsistente;
import logica.DTOrganizador;
import logica.DTUsuarioModificado;
import logica.DTAsistenteModificado;
import logica.DTOrganizadorModificado;
import logica.DTRegistro;
import logica.DTEdicion;

import javax.swing.JPanel;

public class ModificarDatosUsuario extends JInternalFrame {
	private JLabel lblUsuarios;
	private JComboBox comboBoxUsuario;
	private JLabel lblNombre;
	private final JTextField txtNick = new JTextField();
	private JTextField txtNom;
	private JLabel lblFecha;
	private JTextField txtAp;
	private JTextField txtCorreo;
	private JLabel lblFechaNacimiento;
	private JTextField txtNac;
	private JTextField txtLink;
	private JLabel lblLink;
	private JLabel lblFecha_1;
	private JTextArea Descripcion;
	private DTUsuario u;
	private JPanel panel;
	private JButton btnNewButton_1;
	
	public ModificarDatosUsuario() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar un Usuario");
		setBounds(30, 30, 698, 483);
		getContentPane().setLayout(null);
		
		lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(27, 12, 188, 36);
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblUsuarios);

        JPanel panelOrg = new JPanel();
        panelOrg.setBounds(233, 201, 421, 190);
        getContentPane().add(panelOrg);
        panelOrg.setLayout(null);
        panelOrg.setVisible(false);

        
        comboBoxUsuario = new JComboBox();
        comboBoxUsuario.setBounds(27, 53, 188, 43);
        ControladorUsuario c = ControladorUsuario.getInstance();
        
        for (String e: c.listarUsuarios()) {
        	comboBoxUsuario.addItem(e);
        }
        getContentPane().add(comboBoxUsuario);
        comboBoxUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	u = c.elegirUsuario((String) comboBoxUsuario.getSelectedItem());
            	if(u instanceof DTAsistente) {
            		panelOrg.setVisible(false);
            		DTAsistente a = (DTAsistente) u;
            		consultaAsistente(e, a);
            	} else {
            		assert u instanceof DTOrganizador;
            		panelOrg.setVisible(true);
        			DTOrganizador o = (DTOrganizador) u;
            		consultaOrganizador(e, o);
            	}
            }
        });
        
        
        lblNombre = new JLabel("Nickname:");
        lblNombre.setBounds(235, 54, 121, 37);
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        //lblNombre.setBounds(100, 100, 170, 14);
        getContentPane().add(lblNombre);
        txtNick.setEditable(false);
        txtNick.setBounds(374, 54, 255, 37);
        getContentPane().add(txtNick);
        txtNick.setColumns(10);
        
        
        JLabel lblSigla = new JLabel("Nombre:");
        lblSigla.setHorizontalAlignment(SwingConstants.CENTER);
        lblSigla.setBounds(235, 103, 121, 37);
        getContentPane().add(lblSigla);
        
        txtNom = new JTextField();
        txtNom.setColumns(10);
        txtNom.setBounds(374, 103, 255, 37);
        getContentPane().add(txtNom);
        
        JLabel lblDescripcin = new JLabel("Correo:");
        lblDescripcin.setHorizontalAlignment(SwingConstants.CENTER);
        lblDescripcin.setBounds(235, 152, 121, 37);
        getContentPane().add(lblDescripcin);
        
        txtAp = new JTextField();
        txtAp.setColumns(10);
        txtAp.setBounds(374, 201, 255, 37);
        getContentPane().add(txtAp);
        
        lblFecha = new JLabel("Apellido:");
        lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
        lblFecha.setBounds(235, 201, 121, 37);
        getContentPane().add(lblFecha);
        
        Descripcion = new JTextArea();
        Descripcion.setWrapStyleWord(true);
        Descripcion.setText("");
        Descripcion.setLineWrap(true);
        Descripcion.setBounds(141, 49, 255, 129);
        panelOrg.add(Descripcion);
        
        txtLink = new JTextField();
        txtLink.setColumns(10);
        txtLink.setBounds(141, 0, 255, 37);
        panelOrg.add(txtLink);
        
        lblLink = new JLabel("Link:");
        lblLink.setHorizontalAlignment(SwingConstants.CENTER);
        lblLink.setBounds(2, 0, 121, 37);
        panelOrg.add(lblLink);
        
        lblFecha_1 = new JLabel("Descripcion:");
        lblFecha_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblFecha_1.setBounds(2, 49, 121, 37);
        panelOrg.add(lblFecha_1);
        
        txtCorreo = new JTextField();
        txtCorreo.setEditable(false);
        txtCorreo.setColumns(10);
        txtCorreo.setBounds(374, 152, 255, 37);
        getContentPane().add(txtCorreo);
        
        lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
        lblFechaNacimiento.setHorizontalAlignment(SwingConstants.CENTER);
        lblFechaNacimiento.setBounds(235, 250, 121, 37);
        getContentPane().add(lblFechaNacimiento);
        
        txtNac = new JTextField();
        txtNac.setColumns(10);
        txtNac.setBounds(374, 250, 255, 37);
        getContentPane().add(txtNac);
        
        panel = new JPanel();
        panel.setBounds(0, 418, 690, 35);
        getContentPane().add(panel);
        
        JButton btnNewButton = new JButton("Modificar");
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarUsuario(e);
            }
        });
		panel.add(btnNewButton);
        
        
	}

	protected void consultaAsistente(ActionEvent e, DTAsistente a) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		txtNick.setText(a.getNickname());
        txtNom.setText(a.getNombre());
        txtCorreo.setText(a.getCorreo());
        txtAp.setText(a.getApellido());
	    txtNac.setText(a.getNacimiento().format(formatter));
	}
	
	protected void consultaOrganizador(ActionEvent e, DTOrganizador o) {
		txtNick.setText(o.getNickname());
        txtNom.setText(o.getNombre());
        txtCorreo.setText(o.getCorreo());
		txtLink.setText(o.getLink());
	    Descripcion.setText(o.getDescripcion());
    }
	
	protected void modificarUsuario(ActionEvent e) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ControladorUsuario c = ControladorUsuario.getInstance();
		DTUsuarioModificado um = null;
		if (u instanceof DTAsistente) {
			DTAsistenteModificado am = new DTAsistenteModificado(txtNom.getText(), txtAp.getText(), LocalDate.parse(txtNac.getText(), formatter));
			um = am;
		} else {
			assert u instanceof DTOrganizador;
			DTOrganizadorModificado om = new DTOrganizadorModificado(txtNom.getText(), Descripcion.getText(), txtLink.getText());
			um = om;			
		}
		
		assert um != null;
		c.modificarDatosUsuario(um);
		
		Object[] opciones = {"Modificar otro usuario", "Cerrar"};
        int resultado = JOptionPane.showOptionDialog(
            this,
            "Se modificó el usuario: " + txtNick.getText(),
            "",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,        
            opciones,    
            opciones[0] // opción por defecto
        );

        // Ver qué eligió el usuario
        switch (resultado) {
            case 0:
            	break;
            case 1:
            	dispose(); // Cierra la ventana
            	break;
            default: // El usuario cierra el diálogo
            	dispose();
            	break;
        };
	}
}
