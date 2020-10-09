package radar.UI.AcuteForecast;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Components.LineChart;
import radar.UI.Components.PieChart;
import radar.UI.Components.Table;
import radar.UI.Components.Table1;
import radar.UI.Components.TableWithScrollBar;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import java.awt.Font;
/**
 * 精准预测-内容三
 */
public class BBody1 extends JPanelTransparent {

	private static final long serialVersionUID = 1L;
	
	private JScrollPane jTable;
	private JPanel jChart;
	
	private TableWithScrollBar table;
	private PieChart pie;
	private LineChart line;
	private Object[] params = {null,null,null,null,null};
	
	private JTabbedPane tabbedPane;
	
	public BBody1(String radarType, String sDate, String eDate) {
		params[0] = radarType;
		params[1] = sDate;
		params[2] = eDate;
		setBackground(null);
		setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("宋体", Font.PLAIN, 14));
		tabbedPane.setBackground(null);
		tabbedPane.setBorder(null);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		add(tabbedPane, BorderLayout.CENTER);
				
		setJTable();		
		setJChart(params[0]);
	}

	private void setJChart(Object obj) {
		String name;
		if(obj=="1") {
			name="I型雷达";
		}else {
			name="II型雷达";
		}	
		pie = new PieChart(name+"备件消耗统计", "AnalysisServiceImpl", "getPartConsumePie", params);
		pie.setBackground(Color.WHITE);
		pie.init();
		line = new LineChart(name+"备件消耗",null,null, "AnalysisServiceImpl", "getPartConsumeLine", params);
		line.setBackground(Color.WHITE);
		line.init();

		jChart = new JPanelTransparent();
		jChart.setLayout(new MigLayout("", "[50%][50%]", "[100%,grow]"));
		
		jChart.add(pie, "cell 0 0,grow");
		jChart.add(line, "cell 1 0,grow");
		
		
		tabbedPane.addTab("统计图表", null, jChart, null);
	}

	private void setJTable() {
				
		String[] header = {"序号","备件","消耗数量"};
		table = new TableWithScrollBar("AnalysisServiceImpl", "getPartsConsumeData", params, header,false,0);		
//		jTable = new JScrollPane(table);
//		add(jTable, "cell 0 0,grow");		
		jTable = new JScrollPane(table);
		tabbedPane.addTab("统计表格", null, jTable, null);
	}
		
	public TableWithScrollBar getTable() {
		return table;
	}
}
