package radar.UI.Left;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;

import java.awt.Color;

public class TestLeftPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7626868893196210106L;

	public TestLeftPanel() {
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new MigLayout("", "[150px]", "[40px][40px][40px][40px][40px][40px]"));
		
		JButton btnNewButton = new Button("测 试 按 钮");
		add(btnNewButton, "cell 0 0,grow");
		
		JButton btnNewButton_6 = new Button("测 试 按 钮1");
		add(btnNewButton_6, "cell 0 1,grow");
		
		JButton btnNewButton_4 = new Button("测 试 按 钮2");
		add(btnNewButton_4, "cell 0 2,grow");
		
		JButton btnNewButton_2 = new Button("测 试 按 钮3");
		add(btnNewButton_2, "cell 0 3,grow");
		
		JButton btnNewButton_3 = new Button("测 试 按 钮4");
		add(btnNewButton_3, "cell 0 4,grow");
		
		JButton btnNewButton_5 = new Button("测 试 按 钮5");
		add(btnNewButton_5, "cell 0 5,grow");
	}
	

}
