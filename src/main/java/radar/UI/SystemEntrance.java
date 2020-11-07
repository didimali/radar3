package radar.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import radar.UI.Content.NewUser;
import radar.UI.Top.TopPanel;


/**
 * 整个窗体框架类
 */
public class SystemEntrance extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public SystemEntrance() {
		getContentPane().setLayout(new BorderLayout(0, 0));
	}
	
	public void initUI() {
		//调用Swing皮肤psg
    	try{
    		UIManager.setLookAndFeel(new NimbusLookAndFeel());  
	   	  	SwingUtilities.updateComponentTreeUI(this);
   	    }
   	    catch(Exception e){
   	    	System.out.println(e);
   	    }
    	
    	InputStream inputStream=this.getClass().getResourceAsStream("/images/logo2.png") ;
		try {
			BufferedImage bi=ImageIO.read(inputStream);
			Image im=(Image)bi;
			//设置右上角图标
	       	setIconImage(im);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dimension = kit.getScreenSize();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //设置窗口全屏
        setBounds(0, 0, dimension.width, dimension.height);
		
       	setTitle(null);
       	//设置窗口屏幕居中
       	setLocationRelativeTo(null);
       	//关闭窗口即退出程序
       	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	//窗口不可以拉伸放缩
//       	setResizable(false);
              	
       	Home home = new Home();
       	home.initUI();
    
        // 添加面板
        getContentPane().add(home);
        getContentPane().setBackground(Color.RED); //正确显示黑色
        // 设置界面可见
        setVisible(true);
	    }
}

