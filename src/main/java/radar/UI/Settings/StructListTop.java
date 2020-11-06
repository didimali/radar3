package radar.UI.Settings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import net.miginfocom.swing.MigLayout;
import radar.Tools.Init;
import radar.UI.Components.ComboBox;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Components.TableWithScrollBar;

/**
 * 备件列表-顶部栏一
 */
public class StructListTop extends JPanelTransparent implements Init{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JPanel panel1;
	private JLabel title;
	private JLabel label1;
	private JComboBox radarType;
	private JSeparator separator;
	private JScrollPane scrollPane;
	private TableWithScrollBar table;
	private JButton addStruct;
	public StructListTop() {
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
		title = new JLabel("产品结构");
		title.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title, "cell 0 0,growx,aligny center");
		
		label1 = new JLabel("雷达型号");
		label1.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label1, "cell 2 0,growx,aligny center");
		
		radarType = new ComboBox("AcuteForecastServiceImpl", "getRadarType", null);
		radarType.setFont(new Font("仿宋", Font.PLAIN, 15));
		radarType.setMaximumRowCount(4);	
		panel.add(radarType, "cell 3 0,growx,aligny center");
		
		addStruct =new JButton("编辑产品结构信息");
		addStruct.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(addStruct, "cell 6 0,growx,aligny center");
		
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
	
	public JButton getAddStruct() {
		return addStruct;
	}
	public JComboBox getRadarComboxTypeBox() {
		return radarType;
	}
}
