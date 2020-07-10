package radar.UI.AcuteForecast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;
import radar.Tools.Init;
import radar.UI.Components.ComboBox;
import radar.UI.Components.JPanelTransparent;

import javax.swing.JRadioButton;

/**
 * 精准预测-顶部栏二
 * @author madi
 *
 */
public class CTop2 extends JPanelTransparent implements Init{

	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JPanel panel;
	private JPanel panel1;
	private JLabel lblNewLabel;
	private JLabel subTitle;
	private String managerName;
	private String radarType;
	private String text;
	
	private ButtonGroup group;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	
	public CTop2(String managerName, String radarType) {
		this.managerName = managerName;
		this.radarType = radarType;
		this.text = managerName+"(雷达详情)"+"-"+radarType;
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[grow][4px]"));
		
		initUI();
		Action();
	}

	@Override
	public void initUI() {
		panel = new JPanelTransparent();
		add(panel,"cell 0 0,grow");
			
		panel.setLayout(new MigLayout("", "[][][][grow][][40px][][]", "[grow]"));
		title = new JLabel("基本状态");
		title.setToolTipText("返回上一页");
		title.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title, "cell 0 0,growx,aligny center");
		
		lblNewLabel = new JLabel(">>");
		lblNewLabel.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(lblNewLabel, "cell 1 0,grow");
		
		subTitle = new JLabel(text);
		subTitle.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(subTitle, "cell 2 0,grow");
		
		radioButton = new JRadioButton("维修预测",true);
		radioButton.setBackground(Color.WHITE);
		radioButton.setFont(new Font("仿宋", Font.PLAIN, 18));
		panel.add(radioButton, "cell 4 0,growx,aligny center");
		
		radioButton_1 = new JRadioButton("器材筹措");
		radioButton_1.setBackground(Color.WHITE);
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
	public JRadioButton getRadioButton() {
		return radioButton;
	}
	public JRadioButton getRadioButton_1() {
		return radioButton_1;
	}

	public String getManagerName() {
		return managerName;
	}

}
