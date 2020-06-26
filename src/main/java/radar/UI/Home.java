package radar.UI;

import javax.swing.JPanel;

import radar.Tools.Init;
import radar.UI.Content.AcuteForecast;
import radar.UI.Content.Example;
import radar.UI.Left.Left;
import radar.UI.Left.TestLeftPanel;
import radar.UI.Top.TestTopPanel;

import java.awt.BorderLayout;
import java.awt.Color;

/**
 * 窗口内容
 */
public class Home extends JPanel implements Init {


	private static final long serialVersionUID = 1L;
	
	private TestTopPanel top;
	private Left left;
	private JPanel center;
	public Home() {
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new BorderLayout(0, 0));	
	};

	@Override
	public void initUI() {
		top = new TestTopPanel();
		add(top, BorderLayout.NORTH);
		
		left = new Left();
		add(left, BorderLayout.WEST);
		
		//注意了，现在可以往后台传参查询获取数据了，穿的参数数量类型不限
		center = new AcuteForecast();
		add(center, BorderLayout.CENTER);
	}

	@Override
	public void Action() {
		
	}

}
