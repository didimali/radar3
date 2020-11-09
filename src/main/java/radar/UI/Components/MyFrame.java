package radar.UI.Components;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = -4556242278694993655L;

	public MyFrame() {
				
		//调用Swing皮肤psg
    	try{
    		UIManager.setLookAndFeel(new NimbusLookAndFeel());  
	   	  	SwingUtilities.updateComponentTreeUI(this);
   	    }
   	    catch(Exception e){
   	    	System.out.println(e);
   	    }
    	
    	InputStream inputStream=this.getClass().getResourceAsStream("/images/logo.png") ;
		try {
			BufferedImage bi=ImageIO.read(inputStream);
			Image im=(Image)bi;
			//设置右上角图标
	       	setIconImage(im);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
