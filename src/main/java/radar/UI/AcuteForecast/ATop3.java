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

import javax.swing.JTextField;

import javax.swing.JButton;

/**
 * 统计分析-顶部栏三
 */
public class ATop3 extends JPanel implements Init{
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
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JButton timeButton;
	public ATop3(int typeid, int location) {
        String[] data=analysisServiceImpl.titleName(typeid,location);		
		this.typeName = data[0];
		this.locationName = data[1];
		this.text = typeName+"-"+locationName+"故障分析";
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
		
		radioButton = new JRadioButton("适应性分析");
		radioButton.setBackground(Color.WHITE);
		radioButton.setFont(new Font("仿宋", Font.PLAIN, 20));
		panel.add(radioButton, "cell 6 0,growx,aligny center");
		
		radioButton_1 = new JRadioButton("故障分析 ",true);
		radioButton_1.setBackground(Color.WHITE);
		radioButton_1.setFont(new Font("仿宋", Font.PLAIN, 20));
		panel.add(radioButton_1, "cell 8 0,growx,aligny center");
		
		label = new JLabel("起始-截止时间");
		label.setFont(new Font("仿宋", Font.PLAIN, 14));
		panel.add(label, "cell 5 1,growx,aligny center");
		sDate = new JTextField(getFirstDayOfThisMonth().toString());
		chooser1.register(sDate);
		panel.add(sDate, "cell 6 1,growx,aligny center");
		sDate.setColumns(4);
		
		lblNewLabel_1 = new JLabel("--");
		lblNewLabel_1.setFont(new Font("仿宋", Font.PLAIN, 14));
		panel.add(lblNewLabel_1, "cell 7 1,growx,aligny center");
		eDate = new JTextField(getMaxDayOfThisMonth().toString());
		chooser2.register(eDate);
		
		panel.add(eDate, "cell 8 1,growx,aligny center");
		eDate.setColumns(4);
		
		
		
		timeButton = new JButton("查询");
		panel.add(timeButton, "cell 9 1");
		
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
	public JButton getTimeButton() {
		return timeButton;
	}
	public JRadioButton getRadioButton() {
		return radioButton;
	}
}
