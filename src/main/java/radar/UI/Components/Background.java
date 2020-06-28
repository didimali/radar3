package radar.UI.Components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 背景图片实现类
 * @author madi
 *
 */
public class Background extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image image;
	public Background() {

	}
	
	@Override
	 protected void paintComponent(Graphics g) {  
//		super.paintComponent(g);	    
		getPicture();		
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
	 }
	
	private void getPicture() {

		InputStream inputStream=this.getClass().getResourceAsStream("/images/background4.jpg") ;
		try {
			BufferedImage bi=ImageIO.read(inputStream);
			image =(Image)bi;
			//设置右上角图标
		} catch (IOException e) {
			e.printStackTrace();
		}		             
	}

}
