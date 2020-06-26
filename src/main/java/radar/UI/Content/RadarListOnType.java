package radar.UI.Content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.RadarHealth;
import radar.ServiceImpl.BigDataServiceImpl;
import radar.ServiceImpl.RadarServiceImpl;
import radar.Tools.TableStyleUI;

import javax.swing.JScrollPane;

public class RadarListOnType extends ContentPanel{

	private static final long serialVersionUID = -6029898662509471669L;
	RadarServiceImpl radarServiceImpl = (RadarServiceImpl) SpringUtil.getBean("RadarServiceImpl");
	public RadarListOnType(String id) {		
		initContentTop(id);
		initContentBody(id);
	}	
	
	public void initContentTop(String id) {
		contentTop.setBackground(Color.WHITE);
		contentTop.setLayout(new MigLayout("", "[grow]", "[grow][4px]"));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		String type=radarServiceImpl.getType(id);
		JLabel title = new JLabel("宏观评控>>"+type+"列表");
		title.setFont(new Font("仿宋", Font.BOLD, 24));			
		contentTop.add(title, "cell 0 0,grow");	
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.WHITE);
		contentTop.add(separator, "cell 0 1,growx,aligny bottom");
	}

	
	public void initContentBody(String id) {
		ContentBody.setBackground(Color.WHITE);
		ContentBody.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		Object[][] list=radarServiceImpl.getRadarList(id);
		JTable radarTable = new JTable();
		//radarTable.setEnabled(false);
		//radarTable.setRowSelectionAllowed(false);
		radarTable.setModel(new DefaultTableModel(list,new String[] {"编号", "名称","所属部队","健康状态","故障预测","维修计划"}));
		radarTable.setFont(new Font("宋体", Font.PLAIN, 14));
		TableStyleUI ui = new TableStyleUI();
        ui.makeFace(radarTable);
		JScrollPane JSP= new JScrollPane(radarTable);		
		ContentBody.add(JSP, "cell 0 0,grow");
	}

	public void initContentFoot() {
		
	}

}
