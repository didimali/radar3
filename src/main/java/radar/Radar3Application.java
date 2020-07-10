package radar;

import javax.swing.SwingUtilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import radar.UI.SystemEntrance;

@SpringBootApplication
public class Radar3Application {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(Radar3Application.class, args);
		// 启动完成,调用自定义方法,在EDT启动主页面
	   	 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	           	 try {
	           		 SystemEntrance systemEntrance = new SystemEntrance();
		     	    	systemEntrance.initUI();
	           	 }
	           	 catch(Exception e) {
	           		 e.printStackTrace();
	           	 }
	            }
	        });
	}

}
