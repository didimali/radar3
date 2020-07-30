package radar.UI.AcuteForecast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.ServiceImpl.AnalysisServiceImpl;
import radar.Tools.Init;
import radar.UI.Components.Chooser;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Top.TopPanel;

import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * 统计分析-顶部栏三
 */
public class ATop3 extends JPanelTransparent implements Init{
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
	private JLabel label;
	private JTextField sDate;
	private JTextField eDate;
	private JLabel lblNewLabel_1;
	private JButton radioButton;
	private JButton radioButton_1;
	private JLabel timeButton;
	public ATop3(int typeid, int location) {
        String[] data=analysisServiceImpl.titleName(typeid,location);		
		this.typeName = data[0];
		this.locationName = data[1];
		this.text = typeName+"-"+locationName+"故障分析";
		setLayout(new MigLayout("", "[grow]", "[grow][4px]"));	
		initUI();
		Action(); 
	}

	@Override
	public void initUI() {
		panel = new JPanelTransparent();	
		add(panel,"cell 0 0,grow");
			
		panel.setLayout(new MigLayout("", "[][][][grow][][40px][][]", "[grow][grow]"));
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
		
		Chooser chooser1 = Chooser.getInstance();
		
		Chooser chooser2 = Chooser.getInstance();
		
		radioButton = new JButton("适应性分析");
		radioButton.setBackground(new Color(192,192,192));
		radioButton.setFont(new Font("仿宋", Font.PLAIN, 18));
		panel.add(radioButton, "cell 6 0,growx,aligny center");
		
		radioButton_1 = new JButton("故障分析 ");
		radioButton_1.setBackground(new Color(0, 204, 255));
		radioButton_1.setFont(new Font("仿宋", Font.PLAIN, 18));
		panel.add(radioButton_1, "cell 8 0,growx,aligny center");
		
		label = new JLabel("起始-截止时间");
		label.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(label, "cell 5 1,growx,aligny center");
		sDate = new JTextField(getFirstDayOfThisMonth().toString());
		sDate.setFont(new Font("仿宋", Font.PLAIN, 15));
		chooser1.register(sDate);
		panel.add(sDate, "cell 6 1,growx,aligny center");
		sDate.setColumns(4);
		
		lblNewLabel_1 = new JLabel("--");
		lblNewLabel_1.setFont(new Font("仿宋", Font.PLAIN, 16));
		panel.add(lblNewLabel_1, "cell 7 1,growx,aligny center");
		eDate = new JTextField(getMaxDayOfThisMonth().toString());
		eDate.setFont(new Font("仿宋", Font.PLAIN, 15));
		chooser2.register(eDate);
		
		panel.add(eDate, "cell 8 1,growx,aligny center");
		eDate.setColumns(4);
		
		
		
		timeButton = new JLabel();
		timeButton.setToolTipText("刷新");
		timeButton.setIcon(TopPanel.getIcon("refresh1.png",this));
		timeButton.setFont(new Font("仿宋", Font.PLAIN, 18));
		panel.add(timeButton, "cell 9 1");
		
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
	public JLabel getTimeButton() {
		return timeButton;
	}
	public JButton getRadioButton() {
		return radioButton;
	}
}
