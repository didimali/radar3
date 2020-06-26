package radar.UI;

import javax.swing.JPanel;

import radar.Tools.Init;
import radar.UI.Content.MacroEvaluation;
import radar.UI.Left.Left;
import radar.UI.Top.TopPanel;

import java.awt.BorderLayout;
import java.awt.Color;

/**
 * 窗口内容
 */
public class Home extends JPanel implements Init {


	private static final long serialVersionUID = 1L;
	
	private TopPanel top;
	private Left left;
	private JPanel center;
	public Home() {
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new BorderLayout(0, 0));	
	};

	@Override
	public void initUI() {
		top = new TopPanel();
		add(top, BorderLayout.NORTH);
		
		left = new Left();
		add(left, BorderLayout.WEST);
		
//		center = new AcuteForecast();
		center = new MacroEvaluation();
		add(center, BorderLayout.CENTER);
	}

	@Override
	public void Action() {
		
	}

}
