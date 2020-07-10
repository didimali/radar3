package radar.UI.AcuteForecast;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import radar.SpringUtil;
import radar.ServiceImpl.AnalysisServiceImpl;
import radar.Tools.TableStyleUI;
import radar.UI.Components.Table1;

import java.awt.Font;

/**
 * 统计分析-内容一
 *
 */
public class ABody1 extends JScrollPane {

	private static final long serialVersionUID = 1L;
	
//	private JTable table;
	private Table1 table;
	AnalysisServiceImpl analysisServiceImpl = (AnalysisServiceImpl) SpringUtil.getBean("AnalysisServiceImpl");
	public ABody1() {
		
//		Object[][] list=analysisServiceImpl.countRadarType();
//		table = new JTable();
//		table.setModel(new DefaultTableModel(list,new String[] {"编号", "型号","驻防地形","总数量","绿(台)","黄(台)","红(台)"}));
//		table.setFont(new Font("仿宋", Font.PLAIN, 14));
//		table.setToolTipText("查看详情");
//		TableStyleUI ui = new TableStyleUI();
//      ui.makeFace(table);
		String[] header = {"编号", "型号","驻防地形","总数量","绿(台)","黄(台)","红(台)"};
        table = new Table1("AnalysisServiceImpl", "countRadarType", null, header, false, 0);
		setViewportView(table);

	}

	public Table1 getTable() {
		return table;
	}
}
