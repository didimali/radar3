package radar.UI.Left;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 左侧栏
 */
public class Left extends JPanel {

	private static final long serialVersionUID = 1L;

	private Button b1;
	private Button b2;
	private Button b3;
	
	public Left() {
		
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new MigLayout("", "[150px]", "[40px][40px][40px]"));
		
		b1 = new Button("精 准 预 测");
		b1.changeColor(true);
		add(b1, "cell 0 0,grow");
		
		b2 = new Button("宏 观 评 控");
		add(b2, "cell 0 1,grow");
		
		b3 = new Button("系 统 设 置");
		add(b3, "cell 0 2,grow");
		
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

}
