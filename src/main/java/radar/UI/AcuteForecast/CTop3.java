package radar.UI.AcuteForecast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;
import radar.Tools.Init;
import radar.UI.Components.Chooser;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Top.TopPanel;

import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 精准预测-顶部栏三
 */
public class CTop3 extends JPanelTransparent implements Init{

	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JPanel panel;
	private JPanel panel1;
	private JLabel lblNewLabel;
	private JLabel subTitle;
	private JLabel label;
	private JTextField sDate;
	private JTextField eDate;
	private JLabel lblNewLabel_1;
	private JLabel label_1;
	
	private String text;
	
	public CTop3(String managerName, String radarType) {
		text = managerName+"(备件分析)"+"-"+radarType;
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[grow][4px]"));
		
		initUI();
		Action();
	}

	@Override
	public void initUI() {
		panel = new JPanelTransparent();	
		add(panel,"cell 0 0,grow");
			
		panel.setLayout(new MigLayout("", "[][][][grow][][grow 64][][grow 64][10%][]", "[grow][grow]"));
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
		
		label = new JLabel("起始-截止时间");
		label.setFont(new Font("仿宋", Font.PLAIN, 14));
		panel.add(label, "cell 4 0,growx,aligny center");
		
		Chooser chooser1 = Chooser.getInstance();
		sDate = new JTextField(getFirstDayOfThisMonth().toString());
		chooser1.register(sDate);
		panel.add(sDate, "cell 5 0,growx,aligny center");
		sDate.setColumns(4);
		
		lblNewLabel_1 = new JLabel("--");
		lblNewLabel_1.setFont(new Font("仿宋", Font.PLAIN, 14));
		panel.add(lblNewLabel_1, "cell 6 0,growx,aligny center");
		
		Chooser chooser2 = Chooser.getInstance();
		eDate = new JTextField(getMaxDayOfThisMonth().toString());
		chooser2.register(eDate);
		
		panel.add(eDate, "cell 7 0,growx,aligny center");
		eDate.setColumns(4);
		
		label_1 = new JLabel("");
		label_1.setToolTipText("刷新");
		label_1.setIcon(TopPanel.getIcon("refresh1.png",this));
		panel.add(label_1, "cell 9 0,grow");
		
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
	
	/**
	 * 获取本月第一天
     * @return
     */
	public String getFirstDayOfThisMonth() {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return myFormatter.format(cal.getTime());
	}
	
	 /**
     * 获取本月最后一天
     * @return
     */
	public String getMaxDayOfThisMonth() {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		//主要就是这个roll方法
		cal.roll(Calendar.DATE, -1);
		return myFormatter.format(cal.getTime());
	}

	public JTextField getSDate() {
		return sDate;
	}
	public JTextField getEDate() {
		return eDate;
	}
	public JLabel getLabel_1() {
		return label_1;
	}
}
