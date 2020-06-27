package radar.UI.Content;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import radar.Tools.Init;
/**
 * 宏观评控
 * @author madi
 *
 */
public class MacroEvaluation extends JPanel implements Init{

	private static final long serialVersionUID = 1L;
	
	private RadarTypeList c1;
	private RadarListOnType c2;
	private RadarAnalysis c3;
	
	private String radarTypeId;
	
	

	public MacroEvaluation() {
		setLayout(new BorderLayout(0, 0));
		initUI();
	}

	@Override
	public void initUI() {
		set1();
	}

	private void set1() {
		c1 = new RadarTypeList();
		c1.getRadarTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = c1.getRadarTable().getSelectedRow();		//行数
				String radarType = (String) c1.getRadarTable().getValueAt(r, 1);
				radarTypeId = "1";
				if(radarType.equals("II型雷达"))
					radarTypeId = "2";
				remove(c1);
				if(c2 == null)
					set2(radarTypeId);
				else
					add(c2, BorderLayout.CENTER);
				validate();
				repaint();					
			}
		});
		add(c1, BorderLayout.CENTER);
	}

	private void set2(String radarTypeId) {
		c2 = new RadarListOnType(radarTypeId);
		
		c2.getRadarTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = c2.getRadarTable().getSelectedRow();		//行数
				String radarName = (String) c2.getRadarTable().getValueAt(r, 2);				
				String title2 = c2.getText();
				
				String radarId = "1";
				if(!radarName.equals("部队1"))
					radarId = null;				
				remove(c2);				
				set3(radarId,title2,radarName);
				validate();
				repaint();					
			}
		});
		c2.getTitle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				remove(c2);
				add(c1, BorderLayout.CENTER);
				validate();
				repaint();					
			}
		});		
		add(c2, BorderLayout.CENTER);
	}

	private void set3(String radarId,String title2,String title3) {
		c3 = new RadarAnalysis(radarId,title2,title3);
		c3.getTitle1().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				remove(c3);
				if(c1 == null)
					set1();
				else
					add(c1, BorderLayout.CENTER);
				validate();
				repaint();					
			}
		});	
		c3.getTitle2().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				remove(c3);
				if(c2 == null)
					set2(radarTypeId);
				else
					add(c2, BorderLayout.CENTER);
				validate();
				repaint();					
			}
		});	
		add(c3, BorderLayout.CENTER);
	}

	@Override
	public void Action() {
		
	}

}
