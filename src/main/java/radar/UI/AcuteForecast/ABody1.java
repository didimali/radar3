package radar.UI.AcuteForecast;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import radar.SpringUtil;
import radar.ServiceImpl.AnalysisServiceImpl;
import radar.Tools.TableStyleUI;
import radar.UI.Components.Table1;
import radar.UI.Components.TableWithScrollBar;

import java.awt.Font;

/**
 * 统计分析-内容一
 *
 */
public class ABody1 extends JScrollPane {

	private static final long serialVersionUID = 1L;
	
	private TableWithScrollBar table;
	AnalysisServiceImpl analysisServiceImpl = (AnalysisServiceImpl) SpringUtil.getBean("AnalysisServiceImpl");
	public ABody1() {
		
		String[] header = {"编号", "型号","驻防地形","总数量","绿(台)","黄(台)","红(台)"};
        table = new TableWithScrollBar("AnalysisServiceImpl", "countRadarType", null, header, false, 0);
		setViewportView(table);

	}

	public TableWithScrollBar getTable() {
		return table;
	}
}
