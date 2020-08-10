package radar.UI.AcuteForecast;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JSplitPane;
import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.UI.Components.ComboBox;
import radar.UI.Components.HistoryLineForHI;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Components.Table;
import radar.UI.Components.Table1;

import java.awt.FlowLayout;
import javax.swing.JTable;

import org.jfree.chart.JFreeChart;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * 单个部队详细分析结果-
 * @author madi
 *
 */
public class CBody4 extends JPanelTransparent {
	
	private static final long serialVersionUID = 1L;
	
	private Object[] params = {null};
	private String text;
	
	private JPanel health;
	private JScrollPane hTable;
	private HistoryLineForHI hLine;
	private JTable healthTable;
	
	private JPanel fault;
	private JScrollPane fTable;
	private JTable faultTable;
	private JLabel hTitle;
	private JLabel fTitle;

	public CBody4(String radarName,int radarId) {
		text = radarName;
		params[0] = radarId;
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[60%][40%]", "[grow]"));
		
		setHealthPanel();
		setFaultPanel();
	}

	private void setHealthPanel() {
		health = new JPanelTransparent();
		add(health, "cell 0 0,grow");
		health.setLayout(new MigLayout("", "[100%]", "[5%][52%][43%]"));
		
		String[] header = {"序号","系统名称","健康状态","评估时间"};
		
		hTitle = new JLabel("健康评估结果");
		hTitle.setFont(new Font("仿宋", Font.PLAIN, 18));
		health.add(hTitle, "cell 0 0,growx,aligny center");
		healthTable = new Table1("AcuteForecastServiceImpl", "getDataForRadarHealthResultTable", params, header, false, 1);
		
		hTable = new JScrollPane(healthTable);
		health.add(hTable, "cell 0 1,grow");
		
		hLine = new HistoryLineForHI(text+"健康状态变化趋势", "AcuteForecastServiceImpl", "getDataForRadarHiLine", (int) params[0]);
		hLine.setBackground(Color.WHITE);
		health.add(hLine, "cell 0 2,grow");
	}

	private void setFaultPanel() {
		fault = new JPanelTransparent();
		add(fault, "cell 1 0,grow");
		fault.setLayout(new MigLayout("", "[100%]", "[5%][95%]"));
		
		String[] header = {"序号","故障部位","预计发生时间"};
		
		fTitle = new JLabel("故障预测结果");
		fTitle.setFont(new Font("仿宋", Font.PLAIN, 18));
		fault.add(fTitle, "cell 0 0,growx,aligny center");
		faultTable = new Table1("AcuteForecastServiceImpl", "getDataForRadarForecastTable", params, header, false, 1);
		fTable = new JScrollPane(faultTable);
		
		fault.add(fTable, "cell 0 1,grow");
	}
	
	public void refresh() {
		((Table1) healthTable).refreshTable();
		((Table1) faultTable).refreshTable();  
		hLine.refreshLine();	
	}
	

}
