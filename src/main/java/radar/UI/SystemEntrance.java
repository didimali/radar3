package radar.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import lombok.extern.slf4j.Slf4j;
import radar.UI.Components.MyFrame;


/**
 * 整个窗体框架类
 */
@Slf4j
public class SystemEntrance extends MyFrame{
	
	private static final long serialVersionUID = 1L;
	
	private String userAccount;
	
	public SystemEntrance(String userAccount) {
		getContentPane().setLayout(new BorderLayout(0, 0));
		this.userAccount = userAccount;
	}
	
	public void initUI() {
		
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
        
        //给右上角的关闭按钮添加日志记录事件
        this.addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		log.info(userAccount+" 于"+
        				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"退出系统！！！");
        	}
        	});
	    }
}

