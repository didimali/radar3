package radar.UI.Left;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Background;
import radar.UI.Components.Button;
import radar.UI.Components.JPanelTransparent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

/**
 * 左侧栏
 */
public class Left extends JPanel {

	private static final long serialVersionUID = 1L;

	private Button b1;
	private Button b2;
	private Button b3;
	private JButton b4;
	private JButton b5;
	private JButton b6;
	private JButton b7;
	
	private ButtonGroup group;
	
	public Left() {
		
		setBackground(new Color(248,248,255));
		setLayout(new MigLayout("", "[150px]", "[40px][40px][40px][40px][40px][40px][40px]"));
		
		b1 = new Button("精 准 预 测");
		b1.changeColor(true);
		add(b1, "cell 0 0,grow");
		
		b2 = new Button("统 计 分 析");
		add(b2, "cell 0 1,grow");
		
		b3 = new Button("系 统 设 置");
		add(b3, "cell 0 2,grow");
		
		b4 = new Button("部 队 设 置");
		b4.setFont(new Font("仿宋", Font.BOLD, 16));
		add(b4, "cell 0 3,grow");
		
		b5 = new Button("雷 达 设 置");
		b5.setFont(new Font("仿宋", Font.BOLD, 16));
		add(b5, "cell 0 4,grow");
		
		b6 = new Button("备 件 管 理");
		b6.setFont(new Font("仿宋", Font.BOLD, 16));
		add(b6, "cell 0 5,grow");
		
		b7 = new Button("结 构 信 息");
		b7.setFont(new Font("仿宋", Font.BOLD, 16));
		add(b7, "cell 0 6,grow");
		
		group.add(b4);
		group.add(b5);
		group.add(b6);
		group.add(b7);
		b1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b1.changeColor(true);
				b2.changeColor(false);
				b3.changeColor(false);
				}
		});
		
		b2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b1.changeColor(false);
				b2.changeColor(true);
				b3.changeColor(false);
				}
		});
		
		b3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b1.changeColor(false);
				b2.changeColor(false);
				b3.changeColor(true);
				}
		});
		
	}

	public Button getB1() {
		return b1;
	}
	public Button getB2() {
		return b2;
	}
}
