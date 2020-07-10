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

/**
 * 统计分析-顶部栏一
 */
public class ATop1 extends JPanelTransparent implements Init{

	private static final long serialVersionUID = 1L;

	private JLabel title;
	private JPanel panel;
	private JPanel panel1;
	private JLabel radarT;
	private JLabel lblNewLabel;
	private JComboBox radarType;
	private JComboBox locationType;
	
	public ATop1() {
		setLayout(new MigLayout("", "[100%]", "[100%][]"));		
		initUI();
	}

	@Override
	public void initUI() {
		panel = new JPanelTransparent();
		add(panel,"cell 0 0,grow");
					
		panel.setLayout(new MigLayout("", "[][160px,grow][][120px][260px][][120px][100px]", "[100%]"));
		title = new JLabel("统计分析");
		title.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title, "cell 0 0,growx,aligny center");						
		
		radarT = new JLabel("雷达型号");
		radarT.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(radarT, "cell 2 0,alignx trailing");
		
		radarType = new ComboBox("AcuteForecastServiceImpl", "getRadarType", null);
		radarType.setFont(new Font("仿宋", Font.PLAIN, 15));
		panel.add(radarType, "cell 3 0,growx");
		
		lblNewLabel = new JLabel("驻地类型");
		lblNewLabel.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(lblNewLabel, "cell 5 0,alignx trailing");
		
		locationType = new ComboBox("AcuteForecastServiceImpl", "getLocationType", null);
		locationType.setFont(new Font("仿宋", Font.PLAIN, 15));
		panel.add(locationType, "cell 6 0,growx");
		
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
}
