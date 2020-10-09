package radar.UI.AcuteForecast;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.ServiceImpl.AnalysisServiceImpl;
import radar.Tools.Init;
import radar.UI.Components.ComboBox;
import radar.UI.Components.JPanelTransparent;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JButton;

/**
 * 统计分析-低部栏一
 */
public class AFoot extends JPanelTransparent implements Init{
	AnalysisServiceImpl analysisServiceImpl = (AnalysisServiceImpl) SpringUtil.getBean("AnalysisServiceImpl");
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JPanel panel1;
	private JPanel panel2;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label6;
	private JLabel label8;
	private JLabel label9;
	private JLabel label10;
	private JLabel label5;
	private JLabel label11;
	private JLabel label12;
	private JLabel label13;
	private JLabel label14;
	private JLabel label7;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	public AFoot() {
		setLayout(new MigLayout("", "[100%]", "[][100%][]"));		
		initUI();
	}

	@Override
	public void initUI() {
		String [] number=analysisServiceImpl.countNum();

		panel = new JPanelTransparent();
		add(panel,"cell 0 1,grow");
					
		panel.setLayout(new MigLayout("", "[15%][5%][3%][5%][3%][5%][3%][10%][15%][5%][3%][5%][3%][5%][3%][10%][20%]", "[100%,grow]"));
		
		label1 = new JLabel("I型雷达总计：");
		label1.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label1, "cell 0 0");
		
		label2 = new JLabel("绿");
		label2.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label2, "cell 1 0,alignx right");
		
		label3 = new JLabel(number[0]);
		label3.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label3, "cell 2 0,alignx left");
		
		label4 = new JLabel("黄");
		label4.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label4, "cell 3 0,alignx right");
		
		label5 = new JLabel(number[1]);
		label5.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label5, "cell 4 0,alignx left");
		
		label6 = new JLabel("红");
		label6.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label6, "cell 5 0,alignx right");
		
		label7 = new JLabel(number[2]);
		label7.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label7, "cell 6 0,alignx left");
		
		
		label8 = new JLabel("II型雷达总计：");
		label8.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label8, "cell 8 0");
		
		label9 = new JLabel("绿");
		label9.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label9, "cell 9 0,alignx right");
		
		label10 = new JLabel(number[3]);
		label10.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label10, "cell 10 0,alignx left");
		
		label11 = new JLabel("黄");
		label11.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label11, "cell 11 0,alignx right");
		
		label12 = new JLabel(number[4]);
		label12.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label12, "cell 12 0,alignx left");
		
		label13 = new JLabel("红");
		label13.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label13, "cell 13 0,alignx right");
		
		label14 = new JLabel(number[5]);
		label14.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label14, "cell 14 0,alignx left");
		
		panel2=new JPanelTransparent();
		panel2.setLayout(new MigLayout("", "[50%][50%]", "[50%][50%]"));
		panel.add(panel2,"cell 16 0,grow");
		
//		button1 = new JButton(" I型雷达备件消耗统计");
//		button1.setFont(new Font("仿宋", Font.PLAIN, 15));
//	
//		panel2.add(button1, "cell 0 0");
//		
//		button2 = new JButton(" I型雷达器材筹措");
//		button2.setFont(new Font("仿宋", Font.PLAIN, 15));
//		panel2.add(button2, "cell 2 0");
//		
//		button3 = new JButton("II型雷达备件消耗统计");
//		button3.setFont(new Font("仿宋", Font.PLAIN, 15));
//		panel2.add(button3, "cell 0 1");
//		
//		button4 = new JButton("II型雷达器材筹措");
//		button4.setFont(new Font("仿宋", Font.PLAIN, 15));
//		panel2.add(button4, "cell 2 1");
	}

	@Override
	public void Action() {
	}
	
	public JButton getButton1() {
		return button1;
	}
	public JButton getButton2() {
		return button2;
	}
	public JButton getButton3() {
		return button3;
	}
	public JButton getButton4() {
		return button4;
	}
}
