package radar.UI.Left;

import java.awt.Color;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;

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
	private Button b4;
	private Button b5;
	private Button b6;
	private Button b7;
		
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
		
		showSubTitles(false);
				
		b1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b1.changeColor(true);
				b2.changeColor(false);
				b3.changeColor(false);
				showSubTitles(false);
				}
		});
		
		b2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b1.changeColor(false);
				b2.changeColor(true);
				b3.changeColor(false);
				showSubTitles(false);
				}
		});
		
		b3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b1.changeColor(false);
				b2.changeColor(false);
				b3.changeColor(true);
				
				showSubTitles(true);
				}
		});
		
		b4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b4.changeColor(true);
				b5.changeColor(false);
				b6.changeColor(false);
				b7.changeColor(false);
				}
		});
		b5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b4.changeColor(false);
				b5.changeColor(true);
				b6.changeColor(false);
				b7.changeColor(false);
				}
		});
		b6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b4.changeColor(false);
				b5.changeColor(false);
				b6.changeColor(true);
				b7.changeColor(false);
				}
		});
		b7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b4.changeColor(false);
				b5.changeColor(false);
				b6.changeColor(false);
				b7.changeColor(true);
				}
		});
		
	}
	
	private void showSubTitles(boolean show) {
		b4.changeColor(true);
		b5.changeColor(false);
		b6.changeColor(false);
		b7.changeColor(false);
		
		b4.setVisible(show);
		b5.setVisible(show);
		b6.setVisible(show);
		b7.setVisible(show);
		
		this.validate();
		this.repaint();
	}

	public Button getB1() {
		return b1;
	}
	public Button getB2() {
		return b2;
	}
}
