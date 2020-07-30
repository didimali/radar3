package radar.UI.AcuteForecast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.ServiceImpl.AnalysisServiceImpl;
import radar.Tools.Init;
import radar.UI.Components.Button;
import radar.UI.Components.JPanelTransparent;

import javax.swing.JRadioButton;

/**
 * 统计分析-顶部栏二
 *
 */
public class ATop2 extends JPanelTransparent implements Init{
	AnalysisServiceImpl analysisServiceImpl = (AnalysisServiceImpl) SpringUtil.getBean("AnalysisServiceImpl");
	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JPanel panel;
	private JPanel panel1;
	private JLabel lblNewLabel;
	private JLabel subTitle;
	private String typeName;
	private String locationName;
	private String text;
	
	private ButtonGroup group;
	private JButton radioButton;
	private JButton radioButton_1;
	
	public ATop2(int typeid, int location) {
		String[] data=analysisServiceImpl.titleName(typeid,location);
		
		this.typeName = data[0];
		this.locationName = data[1];
		this.text = typeName+"-"+locationName+"适应性分析";
		setLayout(new MigLayout("", "[grow]", "[grow][4px]"));
		
		initUI();
		Action();
	}

	@Override
	public void initUI() {
		panel = new JPanelTransparent();	
		add(panel,"cell 0 0,grow");
			
		panel.setLayout(new MigLayout("", "[][][][grow][][40px][][]", "[grow]"));
		title = new JLabel("统计分析");
		title.setToolTipText("返回上一页");
		title.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title, "cell 0 0,growx,aligny center");
		
		lblNewLabel = new JLabel(">>");
		lblNewLabel.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(lblNewLabel, "cell 1 0,grow");
		
		subTitle = new JLabel(text);
		subTitle.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(subTitle, "cell 2 0,grow");
		
		radioButton = new JButton("适应性分析");
		radioButton.setBackground(new Color(0, 204, 255));
		radioButton.setFont(new Font("仿宋", Font.PLAIN, 18));
		panel.add(radioButton, "cell 4 0,growx,aligny center");
		
		radioButton_1 = new JButton("故障分析");
		radioButton_1.setBackground(new Color(192,192,192));
		radioButton_1.setFont(new Font("仿宋", Font.PLAIN, 18));
		panel.add(radioButton_1, "cell 6 0,growx,aligny center");
		
		group = new ButtonGroup();
		group.add(radioButton);
		group.add(radioButton_1);
		
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

	public JLabel getTitle() {
		return title;
	}
	
	public ButtonGroup getButtonGroup() {
		return group;
	}
	public JButton getRadioButton() {
		return radioButton;
	}
	public JButton getRadioButton_1() {
		return radioButton_1;
	}

	public String gettypeName() {
		return typeName;
	}

}
