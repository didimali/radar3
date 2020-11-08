package radar.UI.Settings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;
import radar.Tools.Init;
import radar.UI.Components.ComboBox;
import radar.UI.Components.JPanelTransparent;

public class UserListTop extends JPanelTransparent implements Init{
	private JLabel usersInfo;
	private JPanel panel;
	private JPanel panel1;
	public UserListTop() {
		setLayout(new MigLayout("", "[100%]", "[grow][]"));
		
		initUI();
		Action();
	}
	@Override
	public void initUI() {
		panel = new JPanelTransparent();
		add(panel,"cell 0 0,grow");
					
		panel.setLayout(new MigLayout("", "[][160px,grow][][120px][260px][][120px][100px]", "[100%]"));
		usersInfo = new JLabel("用户信息");
		usersInfo.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(usersInfo, "cell 0 0,alignx trailing,aligny center");
				
		
		
		panel1 = new JPanelTransparent();
		panel1.setBackground(Color.WHITE);
		
		add(panel1,"cell 0 1,grow");
		panel1.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		panel1.add(separator);
	}
	@Override
	public void Action() {
	
	}
	
	
	
}
