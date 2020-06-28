package radar.UI.AcuteForecast;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import radar.Tools.Init;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JSeparator;

/**
 * 统计分析-顶部栏一
 */
public class ATop1 extends JPanel implements Init{

	private static final long serialVersionUID = 1L;

	private JLabel title;
	private JPanel panel;
	private JPanel panel1;
	
	public ATop1() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[grow][4px]"));		
		initUI();
	}

	@Override
	public void initUI() {
		panel = new JPanel();
		panel.setBackground(Color.WHITE);		
		add(panel,"cell 0 0,grow");
					
		panel.setLayout(new MigLayout("", "[][][][grow][][40px][][]", "[grow]"));
		title = new JLabel("统计分析");
		title.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title, "cell 0 0,growx,aligny center");						
		
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

}
