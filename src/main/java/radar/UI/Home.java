package radar.UI;

import javax.swing.JPanel;

import radar.Tools.Init;
import radar.UI.Content.Example;
import radar.UI.Content.RadarAnalysis;
import radar.UI.Content.RadarListOnType;
import radar.UI.Content.RadarTypeList;
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
	private Example center;
	private RadarAnalysis test;
	private RadarListOnType test2;
	private RadarTypeList test3;
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
//		center = new Example();
//		add(center, BorderLayout.CENTER);
		
		test = new RadarAnalysis("1");
		add(test, BorderLayout.CENTER);
		
//		test2 = new RadarListOnType("1");
//		add(test2, BorderLayout.CENTER);
		
//		test3 = new RadarTypeList();
//		add(test3, BorderLayout.CENTER);
	}

	@Override
	public void Action() {
		
	}

}
