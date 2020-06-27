package radar.UI.AcuteForecast;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import radar.Tools.Init;
import radar.UI.Components.ComboBox;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JSeparator;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * 精准预测内容-顶部栏一
 */
public class CTop1 extends JPanel implements Init{

	private static final long serialVersionUID = 1L;

	private JLabel title;
	private JLabel lType;
	private ComboBox locationT;
	private JLabel rType;
	private ComboBox radarT;
	private JPanel panel;
	private JPanel panel1;
	private JComboBox comboBox;
	
	public CTop1() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[grow][4px]"));
		
		initUI();
		Action();
	}

	@Override
	public void initUI() {
		panel = new JPanel();
		panel.setBackground(Color.WHITE);		
		add(panel,"cell 0 0,grow");
					
		panel.setLayout(new MigLayout("", "[grow][160px,grow][][120px][260px][][120px][100px]", "[grow][grow]"));
		title = new JLabel("基本状态");
		title.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title, "cell 0 0,alignx trailing,aligny center");
				
		lType = new JLabel("驻地类型");
		lType.setFont(new Font("仿宋", Font.PLAIN, 14));
		panel.add(lType, "cell 2 0,growx,aligny center");
		
		locationT = new ComboBox("AcuteForecastServiceImpl", "getLocationType", null);
		locationT.setFont(new Font("仿宋", Font.PLAIN, 13));
		locationT.setMaximumRowCount(4);
		panel.add(locationT, "cell 3 0,growx,aligny center");
		
		rType = new JLabel("雷达型号");
		rType.setFont(new Font("仿宋", Font.PLAIN, 14));
		panel.add(rType, "cell 5 0,growx,aligny center");
		
		radarT = new ComboBox("AcuteForecastServiceImpl", "getRadarType", null);
		radarT.setFont(new Font("仿宋", Font.PLAIN, 13));
		radarT.setMaximumRowCount(4);		
		panel.add(radarT, "cell 6 0,growx,aligny center");
		
		panel1 = new JPanel();
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

	/**
	 * 获取CTop1的驻地类型下拉框
	 * @return
	 */
	public ComboBox getLocationT() {
		return locationT;
	}
	
	/**
	 * 获取CTop1的雷达型号下拉框
	 * @return
	 */
	public ComboBox getRadarT() {
		return radarT;
	}
}
