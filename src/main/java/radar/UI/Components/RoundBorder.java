package radar.UI.Components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.Border;

public class RoundBorder implements Border{
	
	private Color color = new Color(255,255,255);
	
	public RoundBorder(Color color) {
		this.color = color;
	}
	
	public RoundBorder() {
		this.color = new Color(240,240,240);
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); 
		Color oldColor = g.getColor();
		Graphics2D g2 = (Graphics2D)g;
		int i;
		g2.setRenderingHints(rh);
		g2.setColor(color);
		for(i = 0; i < 8; i++)  {
			if (true) {
				g2.setStroke(new BasicStroke(8));
				g2.drawRoundRect(x, y, width-1, height-1, 20, 20);
			}
			else
				g2.drawRect(x, y, width, height);//实际中此循环语句需要修改
		}
		g2.setColor(oldColor);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(0,0,0,0);
	}

	@Override
	public boolean isBorderOpaque() {
		return false;
	}

}
