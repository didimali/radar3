package radar.UI.Components;

import javax.swing.JButton;

import radar.Tools.Init;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class Button extends JButton implements Init{
	
	private static final long serialVersionUID = 1L;
	
	private String text;
	
	public Button(String text) {
		this.text = text;
		initUI();
		Action();
	}

	@Override
	public void initUI() {
		setOpaque(false);
		setBackground(new Color(192,192,192));
		setForeground(Color.BLACK);
		setFont(new Font("仿宋", Font.BOLD, 16));
		setText(text);
		setBorder(new EmptyBorder(0, 0, 0, 0)); 
	}
	
	public void changeColor(boolean change) {
		if(change) {
			setBackground(new Color(0, 204, 255));
			repaint();
		}
		else {
			setBackground(new Color(192,192,192));
			repaint();
		}
		
	}
	
	public void defaultColor() {
			setBackground(new Color(0, 204, 255));
			repaint();		
	}

	@Override
	public void Action() {
		
		this.addMouseListener(new MouseAdapter() {
//			//鼠标悬停按钮变色
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				setBackground(Color.GRAY);
//				setForeground(Color.BLACK);
//				repaint();
//			}
//			//鼠标离开按钮颜色恢复
//			@Override
//			public void mouseExited(MouseEvent e) {
//				setBackground(Color.WHITE);
//				setForeground(Color.BLACK);
//			}
//			@Override
//			public void mousePressed(MouseEvent e) {
//				setBackground(new Color(21,114,232));
//				setForeground(Color.WHITE);
//				repaint();
//			}			
		});
	}
	
}
