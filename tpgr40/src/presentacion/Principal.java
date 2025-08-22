package presentacion;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;

public class Principal extends JFrame{
	public Principal() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JComboBox comboBox = new JComboBox();
		getContentPane().add(comboBox, BorderLayout.NORTH);
	}

}
