package radar.UI;

import javax.swing.JPanel;

import radar.Tools.Init;
import radar.UI.Content.ContentPanel3;
import radar.UI.Content.FaultRecord;
import radar.UI.Content.ImportData;
import radar.UI.Content.NewManager;
import radar.UI.Content.NewRadar;
import radar.UI.Content.NewUser;
import radar.UI.Content.PartConsume;
import radar.UI.Content.PrecisePrediction;
import radar.UI.Content.RadarStruct;
import radar.UI.Content.Radartype;
import radar.UI.Content.PartsManage;
import radar.UI.Left.Left;
import radar.UI.Settings.UserList;
import radar.UI.Top.TopPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
	private JPanel panel;
	private CardLayout cardLayout = new CardLayout(0,0);
	
	private ContentPanel3 p1 = new ContentPanel3();
	private ContentPanel3 p2 = new ContentPanel3();
	private ContentPanel3 p3 = new ContentPanel3();
	private ContentPanel3 p4 = new ContentPanel3();
	private ContentPanel3 p5 = new ContentPanel3();
	private ContentPanel3 p6 = new ContentPanel3();
	private ContentPanel3 p7 = new ContentPanel3();
	private ContentPanel3 p8 = new ContentPanel3();
	private ContentPanel3 p9 = new ContentPanel3();
	private ContentPanel3 p10 = new ContentPanel3();

  
	private PrecisePrediction c1;
	private Radartype c2;	
	private NewManager c3;
	private RadarStruct c4;
	private NewRadar c5;
	private PartsManage c6;
	private PartConsume c7;
	private FaultRecord c8;
	private ImportData c9;
	private NewUser c10;


	
	
	public Home() {
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new BorderLayout(0, 0));	
		initUI();
	};

	@Override
	public void initUI() {
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(cardLayout);
		panel.add(p1,"p1");
		panel.add(p2,"p2");
		panel.add(p3,"p3");
		panel.add(p4,"p4");
		panel.add(p5,"p5");
		panel.add(p6,"p6");
		panel.add(p7,"p7");
		panel.add(p8,"p8");
		panel.add(p9,"p9");
		panel.add(p10,"p10");

		
		setTop();
		setLeft();		
		set1();
	}	

	private void setTop() {
		top = new TopPanel();
		add(top, BorderLayout.NORTH);
	}

	private void setLeft() {
		left = new Left();
		left.getB1().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				set1();
				cardLayout.show(panel,"p1");
				validate();
				repaint();
			}
		});
		left.getB2().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				set2();
				cardLayout.show(panel,"p2");
				validate();
				repaint();
			}
		});
		left.getB3().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				set3();
				cardLayout.show(panel,"p3");
				validate();
				repaint();
			}
		});
		left.getB4().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				set3();
				cardLayout.show(panel,"p3");
				validate();
				repaint();
			}
		});
		
		left.getB5().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				set5();
				cardLayout.show(panel,"p5");
				validate();
				repaint();
			}
		});
		left.getB6().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				set6();
				cardLayout.show(panel,"p6");
				validate();
				repaint();
			}
		});
		left.getB7().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				set4();
				cardLayout.show(panel,"p4");
				validate();
				repaint();
			}
		});
		left.getB8().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				set7();
				cardLayout.show(panel,"p7");
				validate();
				repaint();
			}
		});
		left.getB9().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				set8();
				cardLayout.show(panel,"p8");
				validate();
				repaint();
			}			
		});
		left.getB10().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				set9();
				cardLayout.show(panel,"p9");
				validate();
				repaint();
			}
		});	
		left.getB11().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				set10();
				cardLayout.show(panel,"p10");
				validate();
				repaint();
			}
		});
		add(left, BorderLayout.WEST);		
	}

	private void set1() {
		if(c1 != null)
			p1.remove(c1);
		c1 = new PrecisePrediction();
		p1.add(c1, BorderLayout.CENTER);
	}

	private void set2() {
		if(c2 != null)
			p2.remove(c2);
		c2 = new Radartype();
		p2.add(c2, BorderLayout.CENTER);
	}
	
	private void set3() {
		if(c3 != null)
			p3.remove(c3);
		c3 = new NewManager();
		p3.add(c3, BorderLayout.CENTER);
	}
	
	private void set4() {
		if(c4 != null)
			p4.remove(c4);
		c4 = new RadarStruct();
		p4.add(c4, BorderLayout.CENTER);
	}
	
	private void set5() {
		if(c5 != null)
			p5.remove(c5);
		c5 = new NewRadar();
		p5.add(c5, BorderLayout.CENTER);
	}
	private void set6() {
		if(c6 != null)
			p6.remove(c6);
		c6 = new PartsManage();
		p6.add(c6, BorderLayout.CENTER);
	}
	private void set7() {
		if(c7 != null)
			p7.remove(c7);
		c7 = new PartConsume();
		p7.add(c7, BorderLayout.CENTER);
	}
 	private void set8() {
 		if(c8 != null)
 			p8.remove(c8);
 		c8 = new FaultRecord();
 		p8.add(c8, BorderLayout.CENTER);
 	}
	
	private void set9() {
		if(c9 != null)
			p9.remove(c9);
		c9 = new ImportData();
		p9.add(c9, BorderLayout.CENTER);
	}
	private void set10() {
		if(c10 != null)
			p10.remove(c10);
		c10 = new NewUser();
		p10.add(c10, BorderLayout.CENTER);
	}

	@Override
	public void Action() {
		
	}

}
