package radar.UI.Content;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.ServiceImpl.AnalysisServiceImpl;
import radar.UI.AcuteForecast.CBody1;
import radar.UI.AcuteForecast.CBody2;
import radar.UI.AcuteForecast.CBody3;
import radar.UI.AcuteForecast.CBody4;
import radar.UI.AcuteForecast.CBody5;
import radar.UI.AcuteForecast.CTop1;
import radar.UI.AcuteForecast.CTop2;
import radar.UI.AcuteForecast.CTop3;
import radar.UI.AcuteForecast.CTop4;
import radar.UI.AcuteForecast.CTop5;
import javax.swing.JLabel;
import java.awt.Color;

/**
 * 精准预测跳转简单版本
 * @author madi
 *
 */
public class PrecisePrediction extends JPanel {

	private static final long serialVersionUID = 1L;
	JPanel panel;
	private ContentPanel2 c1 = new ContentPanel2(); 
	private ContentPanel2 c2 = new ContentPanel2();
	private ContentPanel2 c3 = new ContentPanel2();
	private ContentPanel2 c4 = new ContentPanel2();
	private ContentPanel2 c5 = new ContentPanel2();
	
	private CTop1 top1;
	private CTop2 top2;
	private CTop3 top3;
	private CTop4 top4;
	private CTop5 top5;
	private CBody1 body1;
	private CBody2 body2;
	private CBody3 body3;
	private CBody4 body4;
	private CBody5 body5;
	
	boolean m2 = true;
	boolean m3 = true;
	boolean m4 = true;
	boolean m5 = true;
	AnalysisServiceImpl analysisServiceImpl = (AnalysisServiceImpl) SpringUtil.getBean("AnalysisServiceImpl");
	private CardLayout cardLayout = new CardLayout(0,0);

	public PrecisePrediction() {
		setLayout(new MigLayout("", "[8px][100%][8px]", "[8px][100%][8px]"));
		
		panel = new JPanel();
		panel.setLayout(cardLayout);
		add(panel, "cell 1 1,grow");
		panel.add(c1,"c1");
		panel.add(c2,"c2");
		panel.add(c3,"c3");
		panel.add(c4,"c4");
		panel.add(c5,"c5");
		
		set1();
	}
	
	private void set1() {
		top1 = new CTop1();	
		body1 = new CBody1();
		
		//body1表格点击事件实现页面跳转
		body1.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int c = body1.getTable().getSelectedColumn();  //列数
				int r = body1.getTable().getSelectedRow();		//行数
				if(body1.getTable().getValueAt(r, 0) != null) {
					int managerId = (int) body1.getTable().getValueAt(r, 0);
					String managerName = (String) body1.getTable().getValueAt(r, 2);
					String radarType = (String) body1.getTable().getValueAt(r, 4);				
					if(c == 9) {
						set3(managerId,managerName,radarType);					
					}
					else if(c >=5 && c<=8){
						set2(managerId,managerName,radarType);					
					}
				}				
			}
		});
		
		//下拉框筛选事件
		top1.getLocationT().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					String value1 = (String) e.getItem();
					String value2 = (String) top1.getRadarT().getSelectedItem();
					int columnIndex1 = 3;
					int columnIndex2 = 4;
					if(value1.equals("All"))
							columnIndex1 = -1;
					if(value2.equals("All"))
						columnIndex2 = -1;
					body1.getTable().selectDataByColumnIndexsAndValues(columnIndex1,value1,columnIndex2,value2);
				}
			}
		});
		//下拉框筛选事件
		top1.getRadarT().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					String value1 = (String) top1.getLocationT().getSelectedItem();
					String value2 = (String) e.getItem();
					int columnIndex1 = 3;
					int columnIndex2 = 4;
					if(value1.equals("All"))
							columnIndex1 = -1;
					if(value2.equals("All"))
						columnIndex2 = -1;
					body1.getTable().selectDataByColumnIndexsAndValues(columnIndex1,value1,columnIndex2,value2);
				}
			}
		});
		
		c1.contentTop.add(top1);
		c1.contentBody.add(body1);
		
	}
	
	private void set2(int managerId, String managerName, String radarType) {
		if(!m2) {
			c2.contentTop.remove(top2);
			c2.contentBody.remove(body2);
		}
		top2 = new CTop2(managerName, radarType);	
		body2 = new CBody2(managerId,managerName,radarType);
		
		//页面切换
		top2.getTitle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(panel,"c1");
			}
		});
		body2.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = body2.getTable().getSelectedRow();		//行数
				if(body2.getTable().getValueAt(r, 0) != null) {
					int radarId  = (int) body2.getTable().getValueAt(r, 0);
					String radarName = (String) body2.getTable().getValueAt(r, 2);
					String managerName = top2.getManagerName();
					if(top2.getRadioButton().isSelected()) {
						set4(managerName,radarId,radarName);					
					}
					else{		
						set5(managerName,radarId,radarName);					
					};	
				}				
			}
		});
		if(m2) {
			c2.contentTop.add(top2);
			c2.contentBody.add(body2);
			m2 = false;
		}
		else {			
			c2.contentTop.add(top2);
			c2.contentBody.add(body2);
			validate();
			c2.contentTop.repaint();
			c2.contentBody.repaint();
		}
		cardLayout.show(panel,"c2");
	}
	
	private void set3(int managerId, String managerName, String radarType) {
		if(!m3) {
			c3.contentTop.remove(top3);
			c3.contentBody.remove(body3);
		}
		top3 = new CTop3(managerName, radarType);	
		body3 = new CBody3(managerId, managerName, radarType, top3.getSDate().getText(), top3.getEDate().getText());
		
		top3.getLabel_1().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(body3 != null) {
					c3.contentBody.remove(body3);
					body3 = new CBody3(managerId,managerName,radarType, top3.getSDate().getText(), top3.getEDate().getText());
					c3.contentBody.add(body3);
					c3.validate();
					c3.repaint();
				}
			}
		});
		
		//页面切换
		top3.getTitle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(panel,"c1");
			}
		});
		
		if(m3) {
			c3.contentTop.add(top3);
			c3.contentBody.add(body3);
			m3 = false;
		}
		else {			
			c3.contentTop.add(top3);
			c3.contentBody.add(body3);
			validate();
			c3.contentTop.repaint();
			c3.contentBody.repaint();
		}
		
		cardLayout.show(panel,"c3");
	}
	
	private void set4(String managerName, int radarId, String radarName) {
		if(!m4) {
			c4.contentTop.remove(top4);
			c4.contentBody.remove(body4);
		}
		top4 = new CTop4(managerName, radarName);	
		body4 = new CBody4(radarName, radarId);
		
		top4.getTitle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(panel,"c1");
			}
		});
		top4.getSubTitle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(panel,"c2");
			}
		});	
		top4.getButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				analysisServiceImpl.health(radarId);
			}
		});
		System.out.println(c4.contentTop.getComponents());
		if(m4) {
			c4.contentTop.add(top4);
			c4.contentBody.add(body4);
			m4 = false;
		}
		else {			
			c4.contentTop.add(top4);
			c4.contentBody.add(body4);
			c4.validate();
			c4.repaint();
		}
		
		cardLayout.show(panel,"c4");
	}
	
	private void set5(String managerName, int radarId, String radarName) {
		if(!m5) {
			c5.contentTop.remove(top5);
			c5.contentBody.remove(body5);
		}
		top5 = new CTop5(managerName, radarName);	
		body5 = new CBody5(radarName, radarId);
		
		top5.getTitle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(panel,"c1");
			}
		});
		top5.getSubTitle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(panel,"c2");
			}
		});
		
		if(m5) {
			c5.contentTop.add(top5);
			c5.contentBody.add(body5);
			m5 = false;
		}
		else {			
			c5.contentTop.add(top5);
			c5.contentBody.add(body5);
			c5.validate();
			c5.repaint();
		}
		
		cardLayout.show(panel,"c5");
	}	
}
