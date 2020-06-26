package radar.UI.Top;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class TestTopPanel extends TopPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TestTopPanel() {
	
	
	}
	
	/**
	 * @author :madi
	 * @param: image name
	 * @return: Image
	 */
	public  static  ImageIcon getIcon(String imageName,Object c){		
		
		try {	
			InputStream inputStream=c.getClass().getResourceAsStream("/images/"+imageName+".png");
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
