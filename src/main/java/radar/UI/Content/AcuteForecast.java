package radar.UI.Content;

import radar.Tools.Init;
import radar.UI.AcuteForecast.CBody1;
import radar.UI.AcuteForecast.CBody2;
import radar.UI.AcuteForecast.CBody3;
import radar.UI.AcuteForecast.CTop1;
import radar.UI.AcuteForecast.CTop2;
import radar.UI.AcuteForecast.CTop3;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
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
	private CBody1 c1;
	private CBody2 c2;
	private CBody3 c3;
	private JPanel foot1;
		
	
	public AcuteForecast() {
		initUI();
		Action();		
	}

	@Override
	public void initUI() {
		contentTop.setLayout(new BorderLayout(0, 0));
		ContentBody.setLayout(new BorderLayout(0, 0));
		contentFoot.setLayout(new BorderLayout(0, 0));
	
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
				
				String managerName = (String) c1.getTable().getValueAt(r, 1);
				String radarType = (String) c1.getTable().getValueAt(r, 3);
				System.out.println(managerName+" "+radarType);
				if(c == 8) {
					contentTop.remove(top1);
					ContentBody.remove(c1);
					set3(managerName,radarType);
					validate();
					repaint();
				}
				else if(c >=4 && c<=7){
					contentTop.remove(top1);
					ContentBody.remove(c1);
					set2(managerName,radarType);
					validate();
					repaint();
				}
				
			}
		});
		
		contentTop.add(top1);
		ContentBody.add(c1);
		
	}

	private void set2(String managerName, String radarType) {
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
		c2 = new CBody2(managerName,radarType);
		
		contentTop.add(top2);
		ContentBody.add(c2);
		
	}

	private void set3(String managerName, String radarType) {
		top3 = new CTop3(managerName, radarType);
		
		top3.getLabel_1().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(c3 != null) {
					ContentBody.remove(c3);
					c3 = new CBody3(managerName,radarType, top3.getSDate().getText(), top3.getEDate().getText());
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
		c3 = new CBody3(managerName,radarType, top3.getSDate().getText(), top3.getEDate().getText());
		
		contentTop.add(top3);
		ContentBody.add(c3);
	}



	@Override
	public void Action() {
	}

}
