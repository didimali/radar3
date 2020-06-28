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
/**
 * 精准预测-内容三
 */
public class CBody3 extends JPanelTransparent {

	private static final long serialVersionUID = 1L;
	
	private JScrollPane jTable;
	private JPanel jChart;
	
	private Table1 table;
	private PieChart pie;
	private LineChart line;
	private Object[] params = {null,null,null,null,null};
	
	
	public CBody3(int managerId, String managerName, String radarType, String sDate, String eDate) {
		params[0] = managerName;
		params[1] = radarType;
		params[2] = sDate;
		params[3] = eDate;
		params[4] = managerId;
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[60%][grow]", "[grow]"));	
		
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

		jChart = new JPanelTransparent();
		add(jChart, "cell 1 0,grow");
		jChart.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		jChart.add(pie, "cell 0 0,grow");
		jChart.add(this.line, "cell 0 1,grow");
	}

	private void setJTable() {
		String[] header = {"序号","备件","消耗数量"};
		table = new Table1("AcuteForecastServiceImpl", "getAcuteForecastTable3Data", params, header,false,0);
		
		jTable = new JScrollPane(table);
		add(jTable, "cell 0 0,grow");
	}
		
	public Table1 getTable() {
		return table;
	}
}
