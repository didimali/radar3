package radar.UI;

import javax.swing.JPanel;

import radar.Tools.Init;
import radar.UI.Content.Equipment;
import radar.UI.Content.Parts;
import radar.UI.Content.PartsConsume;
import radar.UI.Content.XiTong;
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
	private TestLeftPanel left;
//	private Example center;
//	private NewManager center1;
	private  Equipment center1;

	public Home() {
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new BorderLayout(0, 0));	
	};

	@Override
	public void initUI() {
		top = new TestTopPanel();
		add(top, BorderLayout.NORTH);
		
		left = new TestLeftPanel();
		add(left, BorderLayout.WEST);
		
		//注意了，现在可以往后台传参查询获取数据了，穿的参数数量类型不限
		center1 = new Equipment();
		add(center1, BorderLayout.CENTER);
	}

	@Override
	public void Action() {
		
	}

}
