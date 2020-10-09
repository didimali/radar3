package radar.UI.AcuteForecast;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Components.LineChart;
import radar.UI.Components.PieChart;
import radar.UI.Components.TableWithScrollBar;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import java.awt.Font;
/**
 * 精准预测-内容三
 */
public class CBody3 extends JPanelTransparent {

	private static final long serialVersionUID = 1L;
	
	private TableWithScrollBar table;
	private PieChart pie;
	private LineChart line;
	private Object[] params = {null,null,null,null,null};
	
	JScrollPane scrollPane;
	JTabbedPane tabbedPane;
	JPanel panel;
	
	public CBody3(int managerId, String managerName, String radarType, String sDate, String eDate) {
		params[0] = managerName;
		params[1] = radarType;
		params[2] = sDate;
		params[3] = eDate;
		params[4] = managerId;
		setBackground(null);
		setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(null);
		tabbedPane.setBorder(null);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFont(new Font("宋体", Font.PLAIN, 14));
		add(tabbedPane, BorderLayout.CENTER);
		
		setJTable();		
		setJChart();
	}

	private void setJChart() {
		
		pie = new PieChart(params[1]+"备件消耗统计", "AcuteForecastServiceImpl", "getDataForPartConsumePie", params);
		pie.setBackground(Color.WHITE);
		pie.init();
		line = new LineChart(params[1]+"备件消耗",null,null, "AcuteForecastServiceImpl", "getDataForPartConsumeLine", params);
		line.setBackground(Color.WHITE);
		line.init();
		
		panel = new JPanelTransparent();
		panel.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		
		panel.add(pie, "cell 0 0,grow");
		panel.add(line, "cell 1 0,grow");		
		tabbedPane.addTab("统计图表", null, panel, null);
	}

	private void setJTable() {
		String[] header = {"序号","备件","消耗数量"};
		table = new TableWithScrollBar("AcuteForecastServiceImpl", "getAcuteForecastTable3Data", params, header,false,0);
		scrollPane = new JScrollPane(table);		
		tabbedPane.addTab("详细数据", null, scrollPane, null);		
	}
		
	public TableWithScrollBar getTable() {
		return table;
	}
}
