package radar.UI.AcuteForecast;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import radar.UI.Components.PieChart;
import radar.UI.Components.Table;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;

/**
 * 精准测评-内容二
 */
public class CBody2 extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JScrollPane jTable;
	private JPanel jChart;
	
	private Table table;
	private PieChart pie1;
	private PieChart pie2;
	private Object[] params = {null,null};
	
	public CBody2(String managerName, String radarType) {
		params[0] = managerName;
		params[1] = radarType;
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[60%][grow]", "[grow]"));	
		
		setJTable();		
		setJChart();
		
	}

	private void setJChart() {
		
		pie1 = new PieChart(params[0]+"不同型号雷达统计", "AcuteForecastServiceImpl", "getDataForPie", params);
		pie1.setBackground(Color.WHITE);
		pie1.init();
		pie2 = new PieChart(params[1]+"不同状态雷达统计", "AcuteForecastServiceImpl", "getDataForPie1", params);
		pie2.setBackground(Color.WHITE);
		pie2.init();

		jChart = new JPanel();
		jChart.setBackground(Color.WHITE);
		add(jChart, "cell 1 0,grow");
		jChart.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		jChart.add(pie1, "cell 0 0,grow");
		jChart.add(pie2, "cell 0 1,grow");
	}

	private void setJTable() {
		String[] header = {"序号","雷达","健康评估结果","故障预测","维修计划"};
		table = new Table("AcuteForecastServiceImpl", "getAcuteForecastTable2Data", params, header);
		table.setToolTipText("查看详情");
		
		jTable = new JScrollPane(table);
		add(jTable, "cell 0 0,grow");
	}
	public Table getTable() {
		return table;
	}
}
