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
 * 备件列表-顶部栏一
 */
public class PartsListTop extends JPanelTransparent implements Init{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel partsInfo;
	private JLabel radarTypeLable;
	private ComboBox radarComboxTypeBox;
	private JButton addParts;
	private JPanel panel;
	private JPanel panel1;
	public PartsListTop() {
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
		partsInfo = new JLabel("备件种类信息");
		partsInfo.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(partsInfo, "cell 0 0,alignx trailing,aligny center");
				
		radarTypeLable = new JLabel("雷达型号");
		radarTypeLable.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(radarTypeLable, "cell 2 0,growx,aligny center");
		
		radarComboxTypeBox = new ComboBox("AcuteForecastServiceImpl", "getRadarType", null);
		radarComboxTypeBox.setFont(new Font("仿宋", Font.PLAIN, 15));
		panel.add(radarComboxTypeBox, "cell 3 0,growx,aligny center");
		
		addParts =new JButton("添加备件种类");
		addParts.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(addParts, "cell 6 0,growx,aligny center");
		
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
	
	
	public JButton getAddParts() {
		return addParts;
	}
	public ComboBox getRadarComboxTypeBox() {
		return radarComboxTypeBox;
	}
}
