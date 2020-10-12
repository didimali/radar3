package radar.UI.AcuteForecast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;
import radar.Tools.Init;
import radar.UI.Components.JPanelTransparent;
import javax.swing.JButton;

/**
 * 精准预测-雷达维修预测顶部栏
 * @author madi
 *
 */
public class CTop4 extends JPanelTransparent implements Init{

	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JPanel panel;
	private JPanel panel1;
	private JLabel lblNewLabel;
	private JLabel subTitle;
	private String text;
	private String text1;
	private JLabel label;
	private JLabel title3;
		
	public CTop4(String managerName, String radarName) {
		this.text = managerName;
		text1 = radarName;
		setLayout(new MigLayout("", "[grow]", "[grow][4px]"));
		
		initUI();
		Action();
	}

	@Override
	public void initUI() {
		panel = new JPanelTransparent();	
		add(panel,"cell 0 0,grow");
			
		panel.setLayout(new MigLayout("", "[][][][][][40px][][][][][]", "[grow]"));
		title = new JLabel("基本状态");
		title.setToolTipText("返回上一页");
		title.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title, "cell 0 0,growx,aligny center");
		
		lblNewLabel = new JLabel(">>");
		lblNewLabel.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(lblNewLabel, "cell 1 0,grow");
		
		subTitle = new JLabel(text);
		subTitle.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(subTitle, "cell 2 0,grow");
		
		label = new JLabel(">>");
		label.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(label, "cell 3 0");
		
		title3 = new JLabel(text1+"维修预测结果");
		title3.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(title3, "cell 4 0");

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
	public JLabel getSubTitle() {
		return subTitle;
	}

}
