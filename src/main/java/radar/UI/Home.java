
package radar.UI;

import javax.swing.JPanel;

import radar.Tools.Init;
import radar.UI.AcuteForecast.CBody3;
import radar.UI.Content.AcuteForecast;
import radar.UI.Content.Equipment;
import radar.UI.Content.MacroEvaluation;
import radar.UI.Content.NewManager;
import radar.UI.Content.NewRadar;
import radar.UI.Content.Parts;
import radar.UI.Content.PartsConsume;
import radar.UI.Content.Radartype;
import radar.UI.Content.XiTong;
import radar.UI.Left.Left;
import radar.UI.Top.TopPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 窗口内容
 */
public class Home extends JPanel implements Init {


	private static final long serialVersionUID = 1L;
	
	private TopPanel top;
	private Left left;
//	private JPanel center;
//	private Equipment equipment;
//	private NewManager newManager;
//	private NewRadar newRadar;
//	private Parts parts;
//	private PartsConsume partsConsume;
	private XiTong xiTong;
	private Radartype test;
	
	private AcuteForecast c1;
	private MacroEvaluation c2;
	public Home() {
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new BorderLayout(0, 0));	
		initUI();
	};

	@Override
	public void initUI() {
		top = new TopPanel();
		add(top, BorderLayout.NORTH);
		
//		set1();
//		equipment = new Equipment();
//		add(equipment, BorderLayout.CENTER);
//
//		newManager = new NewManager();
//		add(newManager, BorderLayout.CENTER);
		
//		newRadar=new NewRadar();
//		add(newRadar, BorderLayout.CENTER);
		
//		parts = new Parts();
//		add(parts, BorderLayout.CENTER);

//		partsConsume = new PartsConsume();
//		add(partsConsume, BorderLayout.CENTER);
		
//		xiTong = new XiTong();
//		add(xiTong, BorderLayout.CENTER);
		test = new Radartype();
		add(test, BorderLayout.CENTER);
		
		left = new Left();
		left.getB1().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(c2);
				if(c1 != null) {					
					add(c1, BorderLayout.CENTER);
				}
				else {
					set1();
				}
				validate();
				repaint();
			}
		});
		left.getB2().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(c1);
				if(c2 != null) {					
					add(c2, BorderLayout.CENTER);
				}
				else {
					set2();
				}
				validate();
				repaint();
			}
		});
		add(left, BorderLayout.WEST);		
		
		
	}

	private void set1() {
		c1 = new AcuteForecast();
		add(c1, BorderLayout.CENTER);
	}

	private void set2() {
		c2 = new MacroEvaluation();
		add(c2, BorderLayout.CENTER);
	}

	@Override
	public void Action() {
		
	}

}
package radar.UI;

import javax.swing.JPanel;

import radar.Tools.Init;
import radar.UI.AcuteForecast.CBody3;
import radar.UI.Content.AcuteForecast;
import radar.UI.Content.Equipment;
import radar.UI.Content.MacroEvaluation;
import radar.UI.Content.NewManager;
import radar.UI.Content.NewRadar;
import radar.UI.Content.Parts;
import radar.UI.Content.PartsConsume;
import radar.UI.Content.PrecisePrediction;
import radar.UI.Content.RadarStruct;
import radar.UI.Content.XiTong;
import radar.UI.Left.Left;
import radar.UI.Top.TopPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 窗口内容
 */
public class Home extends JPanel implements Init {


	private static final long serialVersionUID = 1L;
	
	private TopPanel top;
	private Left left;
//	private JPanel center;
//	private Equipment equipment;
//	private NewManager newManager;
//	private NewRadar newRadar;
//	private Parts parts;
//	private PartsConsume partsConsume;
	private XiTong xiTong;
	
	private AcuteForecast c1;
	private MacroEvaluation c2;
	
	private PrecisePrediction c3;
	
	private RadarStruct c4;
	public Home() {
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new BorderLayout(0, 0));	
		initUI();
	};

	@Override
	public void initUI() {
		top = new TopPanel();
		add(top, BorderLayout.NORTH);
		
//		set1();
		set4();
//		equipment = new Equipment();
//		add(equipment, BorderLayout.CENTER);
//
//		newManager = new NewManager();
//		add(newManager, BorderLayout.CENTER);
		
//		newRadar=new NewRadar();
//		add(newRadar, BorderLayout.CENTER);
		
//		parts = new Parts();
//		add(parts, BorderLayout.CENTER);

//		partsConsume = new PartsConsume();
//		add(partsConsume, BorderLayout.CENTER);
		
//		xiTong = new XiTong();
//		add(xiTong, BorderLayout.CENTER);

		left = new Left();
		left.getB1().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(c2);
				if(c1 != null) {					
					add(c1, BorderLayout.CENTER);
				}
				else {
					set1();
				}
				validate();
				repaint();
			}
		});
		left.getB2().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(c1);
				if(c2 != null) {					
					add(c2, BorderLayout.CENTER);
				}
				else {
					set2();
				}
				validate();
				repaint();
			}
		});
		add(left, BorderLayout.WEST);		
		
		
	}

	private void set3() {
		c3 = new PrecisePrediction();
		add(c3, BorderLayout.CENTER);
	}

	private void set1() {
		c1 = new AcuteForecast();
		add(c1, BorderLayout.CENTER);
	}

	private void set2() {
		c2 = new MacroEvaluation();
		add(c2, BorderLayout.CENTER);
	}
	
	private void set4() {
		c4 = new RadarStruct();
		add(c4, BorderLayout.CENTER);
	}

	@Override
	public void Action() {
		
	}

}
