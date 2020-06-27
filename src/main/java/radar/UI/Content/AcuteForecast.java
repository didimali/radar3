package radar.UI.Content;

import radar.Tools.Init;
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

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 精准预测
 */
public class AcuteForecast extends ContentPanel implements Init{
		
	private static final long serialVersionUID = 1L;
	
	private CTop1 top1;
	private CTop2 top2;
	private CTop3 top3;
	private CTop4 top4;
	private CTop5 top5;
	private CBody1 c1;
	private CBody2 c2;
	private CBody3 c3;
	private CBody4 c4;
	private CBody5 c5;
	private JPanel foot1;
		
	
	public AcuteForecast() {
		remove(contentFoot);
		initUI();
		Action();		
	}

	@Override
	public void initUI() {
		contentTop.setLayout(new BorderLayout(0, 0));
		ContentBody.setLayout(new BorderLayout(0, 0));
		set1();
	}

	private void set1() {		
		top1 = new CTop1();	
		c1 = new CBody1();
		
		//c1表格点击事件实现页面跳转
		c1.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int c = c1.getTable().getSelectedColumn();  //列数
				int r = c1.getTable().getSelectedRow();		//行数
				int managerId = (int) c1.getTable().getValueAt(r, 0);
				String managerName = (String) c1.getTable().getValueAt(r, 2);
				String radarType = (String) c1.getTable().getValueAt(r, 4);				
				if(c == 9) {
					contentTop.remove(top1);
					ContentBody.remove(c1);
					set3(managerId,managerName,radarType);
					validate();
					repaint();
				}
				else if(c >=5 && c<=8){
					contentTop.remove(top1);
					ContentBody.remove(c1);
					set2(managerId,managerName,radarType);
					validate();
					repaint();
				}
				
			}
		});
		//下拉框筛选事件
		top1.getLocationT().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					String value = (String) e.getItem();
					if(value.equals("All"))
						c1.getTable().selectDataByColumnIndexAndValue(-1,value);
					else
					c1.getTable().selectDataByColumnIndexAndValue(3,value);
				}
			}
		});
		//下拉框筛选事件
		top1.getRadarT().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					String value = (String) e.getItem();
					if(value.equals("All"))
						c1.getTable().selectDataByColumnIndexAndValue(-1,value);
					else
					c1.getTable().selectDataByColumnIndexAndValue(4,value);
				}
			}
		});
		contentTop.add(top1);
		ContentBody.add(c1);
		
	}

	private void set2(int managerId, String managerName, String radarType) {
		top2 = new CTop2(managerName,radarType);		
		//页面切换
		top2.getTitle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentTop.remove(top2);
				ContentBody.remove(c2);
				contentTop.add(top1);
				ContentBody.add(c1);
				validate();
				repaint();
			}
		});
		c2 = new CBody2(managerId,managerName,radarType);		
		c2.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = c2.getTable().getSelectedRow();		//行数
				int radarId  = (int) c2.getTable().getValueAt(r, 0);
				String radarName = (String) c2.getTable().getValueAt(r, 2);
				String managerName = top2.getManagerName();
				if(top2.getRadioButton().isSelected()) {
					contentTop.remove(top2);
					ContentBody.remove(c2);					
					set4(managerName,radarId,radarName);
					validate();
					repaint();
				}
				else{
					contentTop.remove(top2);
					ContentBody.remove(c2);					
					set5(managerName,radarId,radarName);
					validate();
					repaint();
				};	
			}
		});
		contentTop.add(top2);
		ContentBody.add(c2);
		
	}

	private void set3(int managerId, String managerName, String radarType) {
		top3 = new CTop3(managerName, radarType);
		
		top3.getLabel_1().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(c3 != null) {
					ContentBody.remove(c3);
					c3 = new CBody3(managerId,managerName,radarType, top3.getSDate().getText(), top3.getEDate().getText());
					ContentBody.add(c3);
					validate();
					repaint();
				}
			}
		});
		//页面切换
		top3.getTitle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentTop.remove(top3);
				ContentBody.remove(c3);
				contentTop.add(top1);
				ContentBody.add(c1);
				validate();
				repaint();
			}
		});
		c3 = new CBody3(managerId,managerName,radarType, top3.getSDate().getText(), top3.getEDate().getText());
		
		contentTop.add(top3);
		ContentBody.add(c3);
	}
	
	private void set4(String managerName, int radarId, String radarName) {
		
		c4 = new CBody4(radarName, radarId);
		top4 = new CTop4(managerName, radarName);
		top4.getTitle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentTop.remove(top4);
				ContentBody.remove(c4);
				contentTop.add(top1);
				ContentBody.add(c1);
				validate();
				repaint();
			}
		});
		top4.getSubTitle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentTop.remove(top4);
				ContentBody.remove(c4);
				contentTop.add(top2);
				ContentBody.add(c2);
				validate();
				repaint();
			}
		});
		contentTop.add(top4);
		ContentBody.add(c4);
	}
	
	private void set5(String managerName, int radarId, String radarName) {
		
		c5 = new CBody5(radarName,radarId);
		top5 = new CTop5(managerName,radarName);
		top5.getTitle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentTop.remove(top5);
				ContentBody.remove(c5);
				contentTop.add(top1);
				ContentBody.add(c1);
				validate();
				repaint();
			}
		});
		top5.getSubTitle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentTop.remove(top5);
				ContentBody.remove(c5);
				contentTop.add(top2);
				ContentBody.add(c2);
				validate();
				repaint();
			}
		});
		contentTop.add(top5);
		ContentBody.add(c5);
	}



	@Override
	public void Action() {
	}

}
