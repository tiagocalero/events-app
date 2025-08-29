package presentacion;

import java.awt.BorderLayout;
import javax.swing.*;

import logica.ControladorEvento;

import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.List;
import java.utils.List;

public class AltaEvento {
	private JInternalFrame frmAltaEvento;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private JLabel lblDescripcin;
	private JLabel lblSigla;
	
	public AltaEvento() {
		
		frmAltaEvento = new JInternalFrame();
		frmAltaEvento.setResizable(true);
		frmAltaEvento.setIconifiable(true);
		frmAltaEvento.setMaximizable(true);
		frmAltaEvento.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmAltaEvento.setClosable(true);
		frmAltaEvento.setTitle("Alta de Evento");
		frmAltaEvento.setBounds(30, 30, 402, 376);
		frmAltaEvento.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmAltaEvento.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(193, 24, 140, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(193, 74, 140, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(193, 124, 140, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(43, 24, 140, 16);
		panel.add(lblNewLabel);
		
		lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setBounds(43, 74, 140, 16);
		panel.add(lblDescripcin);
		
		lblSigla = new JLabel("Sigla:");
		lblSigla.setBounds(43, 124, 140, 16);
		panel.add(lblSigla);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Categoría 1");
		chckbxNewCheckBox.setBounds(193, 174, 140, 23);
		panel.add(chckbxNewCheckBox);
		
		JLabel lblCategoras = new JLabel("Categorías:");
		lblCategoras.setBounds(43, 174, 140, 16);
		panel.add(lblCategoras);
		
		JCheckBox chckbxCategora_1 = new JCheckBox("Categoría 2");
		chckbxCategora_1.setBounds(193, 209, 140, 23);
		panel.add(chckbxCategora_1);
		
		JCheckBox chckbxCategora = new JCheckBox("Categoría 3");
		chckbxCategora.setBounds(193, 244, 140, 23);
		panel.add(chckbxCategora);
		
		JPanel panel_1 = new JPanel();
		frmAltaEvento.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdCrearEventoActionPerformed(e);
            }
        });
		panel_1.add(btnNewButton);
	}
	
	protected void cmdCrearEventoActionPerformed(ActionEvent e) {
        String nombre = lblNewLabel.getText();
        String desc = lblDescripcin.getText();
        String sigla = lblSigla.getText();
        List<String> categorias;
        
        ControladorEvento m = ControladorEvento.getInstance();
        
    }
}