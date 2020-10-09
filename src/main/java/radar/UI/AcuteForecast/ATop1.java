package radar.UI.AcuteForecast;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import radar.Tools.Init;
import radar.UI.Components.ComboBox;
import radar.UI.Components.JPanelTransparent;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 统计分析-顶部栏一
 */
public class ATop1 extends JPanelTransparent implements Init{

	private static final long serialVersionUID = 1L;

	private JLabel title;
	private JPanel panel;
	private JPanel panel1;
	private JLabel radarT;
	private JLabel locationT;
	private JComboBox radarType;
	private JComboBox locationType;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	
	public ATop1() {
		setLayout(new MigLayout("", "[100%]", "[100%][]"));		
		initUI();
	}

	@Override
	public void initUI() {
		panel = new JPanelTransparent();
		add(panel,"cell 0 0,grow");
					
		panel.setLayout(new MigLayout("", "[][60px,grow][][60px][60px][][60px][60px][][][][]", "[100%]"));
		title = new JLabel("统计分析");
		title.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title, "cell 0 0,growx,aligny center");						
		
		radarT = new JLabel("雷达型号");
		radarT.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(radarT, "cell 2 0,alignx trailing");
		
		radarType = new ComboBox("AcuteForecastServiceImpl", "getRadarType", null);
		radarType.setFont(new Font("仿宋", Font.PLAIN, 15));
		panel.add(radarType, "cell 3 0,growx");
		
		locationT = new JLabel("驻地类型");
		locationT.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(locationT, "cell 5 0,alignx trailing");
		
		locationType = new ComboBox("AcuteForecastServiceImpl", "getLocationType", null);
		locationType.setFont(new Font("仿宋", Font.PLAIN, 15));
		panel.add(locationType, "cell 6 0,growx");
		
		button1 = new JButton("I型雷达备件统计");		
		button1.setFont(new Font("仿宋", Font.PLAIN, 15));
		panel.add(button1, "cell 8 0");
		
		button2 = new JButton("I型雷达器材筹措");
		button2.setFont(new Font("仿宋", Font.PLAIN, 15));
		panel.add(button2, "cell 9 0");
		
		button3 = new JButton("II型雷达备件统计");
		button3.setFont(new Font("仿宋", Font.PLAIN, 15));
		panel.add(button3, "cell 10 0");
		
		button4 = new JButton("II型雷达器材筹措");
		button4.setFont(new Font("仿宋", Font.PLAIN, 15));
		panel.add(button4, "cell 11 0");
		
		panel1 = new JPanelTransparent();	
		add(panel1,"cell 0 1,grow");
		panel1.setLayout(new BorderLayout(0, 0));		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		panel1.add(separator);
		
	}

	@Override
	public void Action() {
		
	}

	public JComboBox getRadarType() {
		return radarType;
	}
	public JComboBox getLocationType() {
		return locationType;
	}
	public JButton getButton1() {
		return button1;
	}
	public JButton getButton3() {
		return button3;
	}
	public JButton getButton2() {
		return button2;
	}
	public JButton getButton4() {
		return button4;
	}
}
