package radar;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import radar.Radar3Application;
import radar.UI.Home;
import radar.UI.SystemEntrance;

public class ImageFrame extends JFrame implements Runnable{
	
	private static final long serialVersionUID = 1L;

	private Thread t;
	Toolkit kit = Toolkit.getDefaultToolkit();
	
	Dimension dimension = kit.getScreenSize();
	Dimension frameSize = new Dimension(dimension.width/2, dimension.height/2);
	ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/images/loading.png"));
	
	public static void main(String[] args) {
	    ImageFrame imageFrame = new ImageFrame();
	    imageFrame.addImageByRepaint();
	}
	
	@Override
	public void run(){
		try{
			Thread.sleep(1);//启动画面时间
//			Radar3Application Radar3Application=new Radar3Application();//启动程序主窗体
			String[] args = {""};
			Radar3Application.main(args,this,t);
			// 启动完成,调用自定义方法,在EDT启动主页面
//			this.setVisible(false);
//			t.interrupt();
		}catch(InterruptedException e){
			System.out.println("Some unexcepted mistake has happened! \n Been caught in Welcom.java. ");
		}
	}
	
	public ImageFrame() {
	    // 设置窗体属性
	    setSize(frameSize);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setIconImage(imageIcon.getImage());
	    setUndecorated(true);
	    t=new Thread(this);
		t.start();
	  }
	
	public void addImageByRepaint() {
	    ImagePanel imagePanel = new ImagePanel(frameSize, imageIcon.getImage());
	    setContentPane(imagePanel);
//	    addComponents();
	    setVisible(true);
	  }

	
	class ImagePanel extends JPanel {
	    
		private static final long serialVersionUID = 1L;
		Dimension d;
	    Image image;
	    public ImagePanel(Dimension d, Image image) {
	      super();
	      this.d = d;
	      this.image = image;
	    }
	    @Override
	    public void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      g.drawImage(image, 0, 0, d.width, d.height, this);
	    }
	  }	  	 
	
}