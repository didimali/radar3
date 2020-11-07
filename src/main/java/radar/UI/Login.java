package radar.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import java.awt.Color;

/**
 * 用户登录界面
 * @author madi
 *
 */
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField userAccount;
	private JTextField passWord;
	
	private JButton login;
	private JButton cancle;

	public Login() {
		init();
		setUndecorated(true); 
		getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		setTitle(null);		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//设置窗口屏幕居中
       	setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
		
		JPanel panel1 = new JPanel();
		panel1.setOpaque(false);
		contentPane.add(panel1, "cell 0 0,growx,aligny center");
		
		JLabel title = new JLabel("基于大数据的现役雷达装备预测性维修保障平台");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("仿宋", Font.BOLD, 18));
		panel1.add(title);
		
		JPanel panel2 = new JPanel();
		panel2.setOpaque(false);
		contentPane.add(panel2, "cell 0 1,grow");
		panel2.setLayout(new MigLayout("", "[60px][][grow][60px]", "[60px][]"));
		
		JLabel label1 = new JLabel("账号：");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("仿宋", Font.BOLD, 16));
		panel2.add(label1, "cell 1 0,alignx trailing");
		
		userAccount = new JTextField();
		panel2.add(userAccount, "cell 2 0,growx");
		userAccount.setColumns(10);
		
		JLabel label2 = new JLabel("密码：");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("仿宋", Font.BOLD, 16));
		panel2.add(label2, "cell 1 1");
		
		passWord = new JTextField();
		panel2.add(passWord, "cell 2 1,growx");
		userAccount.setColumns(10);
		
		JPanel panel3 = new JPanel();
		panel3.setOpaque(false);
		contentPane.add(panel3, "cell 0 2,grow");
		panel3.setLayout(new MigLayout("", "[grow][][grow][][grow]", "[grow]"));
		
		login = new JButton("登录");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
				SystemEntrance systemEntrance = new SystemEntrance();
	     	    systemEntrance.initUI();
			}
		});
		login.setFont(new Font("仿宋", Font.BOLD, 14));
		panel3.add(login,"cell 1 0,growx,aligny center");
		cancle = new JButton("注销");
		cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit1();
			}
		});
		cancle.setFont(new Font("仿宋", Font.BOLD, 14));
		panel3.add(cancle,"cell 3 0,growx,aligny center");
	}
	
	/**
	 * 退出系统
	 */
	private void exit() {
		this.dispose();
	}
	
	private void exit1() {
		System.exit(0);
	}
	
	private void init() {
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
		
		ImageIcon image = new ImageIcon(this.getClass().getResource("/images/123.gif"));
		JLabel label = new JLabel(image);
		label.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
		
		//获取窗口的第二层，将label放入
		getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
	}

}
