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
/**
 * 故障列表-顶部栏一
 */
@SuppressWarnings("serial")
public class FaultListTop extends JPanelTransparent implements Init{
	private JLabel title;
	private JLabel fType;
	private ComboBox faultT;
	private JButton addRadarFault;
	private JPanel panel;
	private JPanel panel1;
	public FaultListTop() {
		setLayout(new MigLayout("", "[100%]", "[grow][]"));
		
		initUI();
		Action();
	}
	@Override
	public void initUI() {
		panel = new JPanelTransparent();
//		panel.setBackground(Color.WHITE);
//		panel.setBackground(null);
		add(panel,"cell 0 0,grow");
					
		panel.setLayout(new MigLayout("", "[][160px,grow][][120px][260px][][120px][100px]", "[100%]"));
		title = new JLabel("故障列表");
		title.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title, "cell 0 0,alignx trailing,aligny center");
				
		fType = new JLabel("故障类型");
		fType.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(fType, "cell 2 0,growx,aligny center");
		
		faultT = new ComboBox("BasicInfoSettingServiceImpl", "getFaultType", null);
		faultT.setFont(new Font("仿宋", Font.PLAIN, 15));
		panel.add(faultT, "cell 3 0,growx,aligny center");
		
		addRadarFault =new JButton("添加记录");
		addRadarFault.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(addRadarFault, "cell 6 0,growx,aligny center");
		
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
	
	public JButton getAddRadarFault() {
		return addRadarFault;
	}
	public ComboBox getFaultT() {
		return faultT;
	}
}
