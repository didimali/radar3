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
import radar.UI.Components.BackupPartsBox;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Components.ManagerCombox;
/**
 * 备件消耗列表-顶部栏一
 */
public class ConsumeListTop extends JPanelTransparent implements Init{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel consumeInfo;
	private JLabel managerLable;
	private ManagerCombox managerBox;
	private JLabel partLable;
	private BackupPartsBox partBox;
	private JButton addConsume;
	private JPanel panel;
	private JPanel panel1;
	public ConsumeListTop() {
		setLayout(new MigLayout("", "[100%]", "[grow][]"));
		initUI();
		Action();
	}
	@Override
	public void initUI() {
		panel = new JPanelTransparent();
		add(panel,"cell 0 0,grow");
					
		panel.setLayout(new MigLayout("", "[][30%][][120px][120px,grow][][120px][30%][120px]", "[100%]"));
		consumeInfo = new JLabel("备件消耗信息");
		consumeInfo.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(consumeInfo, "cell 0 0,alignx trailing,aligny center");
				
		managerLable = new JLabel("部队");
		managerLable.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(managerLable, "cell 2 0,growx,aligny center");
		
		managerBox =  new ManagerCombox("ManagerServiceImpl", "getDataForManagerComboBox");
		managerBox.setFont(new Font("仿宋", Font.PLAIN, 15));
		panel.add(managerBox, "cell 3 0,growx,aligny center");
		
		partLable = new JLabel("备件");
		partLable.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(partLable, "cell 5 0,growx,aligny center");
		
		partBox =  new BackupPartsBox("PartsServiceImpl", "getDataForPartsComboBox");
		partBox.setFont(new Font("仿宋", Font.PLAIN, 15));
		panel.add(partBox, "cell 6 0,growx,aligny center");
		
		
		addConsume =new JButton("添加备件消耗");
		addConsume.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(addConsume, "cell 8 0,growx,aligny center");
		
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
	public ManagerCombox getManagerBox() {
		return managerBox;
	}
	public BackupPartsBox getPartBox() {
		return partBox;
	}
	public JButton getAddConsume() {
		return addConsume;
	}
}
