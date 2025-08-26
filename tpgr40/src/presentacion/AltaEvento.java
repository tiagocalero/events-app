package presentacion;

import java.awt.BorderLayout;
import javax.swing.*;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.List;

public class AltaEvento {
	private JInternalFrame frmAltaEvento;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
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
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(43, 24, 140, 16);
		panel.add(lblNewLabel);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setBounds(43, 74, 140, 16);
		panel.add(lblDescripcin);
		
		JLabel lblSigla = new JLabel("Sigla:");
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
		
		List listm = new DefaultListModel();
		listm.setBounds(28, 209, 138, 76);
		listm.addElement("asd");
		list = new JList(listm);
		panel.add(list);
		
		
		JPanel panel_1 = new JPanel();
		frmAltaEvento.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Crear");
		panel_1.add(btnNewButton);
	}
}