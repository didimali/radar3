package radar.UI.Top;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Background;
import radar.UI.Components.Button;
import radar.UI.Components.JPanelTransparent;

public class TopPanel extends Background{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel logo;
	private JLabel title;
	
	public TopPanel() {
		setBackground(Color.WHITE);
//		setOpaque(true);
		
		setLayout(new BorderLayout(0, 0));
		
		logo = new JPanelTransparent();
		add(logo, BorderLayout.CENTER);
		logo.setLayout(new BorderLayout(0, 0));
		
		title = new JLabel("基于大数据的现役雷达装备预测性维修保障平台");
		title.setBackground(Color.WHITE);
		title.setForeground(Color.WHITE);
		title.setIcon(getIcon("radar1.png",this));
//		title.setBackground(new Color(0, 204, 255));
//		title.setBackground(new Color(30,144,255));
		title.setFont(new Font("仿宋", Font.BOLD, 26));
		title.setPreferredSize(new Dimension(164, 60));
		logo.add(title, BorderLayout.CENTER);
		
		
				
	}

		
	/**
	 * @author :madi
	 * @param: image name
	 * @return: Image
	 */
	public  static  ImageIcon getIcon(String imageName,Object c){		
		
		try {	
			InputStream inputStream=c.getClass().getResourceAsStream("/images/"+imageName);
			if(inputStream != null) {
				BufferedImage bi=ImageIO.read(inputStream);
				Image im=(Image)bi;
				ImageIcon icon = new ImageIcon(im);
				return icon;
			}
			else
				return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}		
	}
}
