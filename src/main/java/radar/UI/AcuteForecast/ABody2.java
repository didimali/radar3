package radar.UI.AcuteForecast;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.general.PieDataset;

import radar.SpringUtil;
import radar.ServiceImpl.AnalysisServiceImpl;
import radar.Tools.TableStyleUI;
import radar.UI.Components.JPanelTransparent;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Font;

/**
 * 统计分析-内容二
 */
public class ABody2 extends JPanelTransparent{
	
	private static final long serialVersionUID = 1L;
	AnalysisServiceImpl analysisServiceImpl = (AnalysisServiceImpl) SpringUtil.getBean("AnalysisServiceImpl");
	private JTable table;

	public ABody2(int typeid, int location) {
        
		setLayout(new MigLayout("", "[60%][grow]", "[grow]"));	
		
		setJTable(typeid,location);		
		setJChart(typeid,location);
		
	}

	private void setJChart(int typeid,int location) {
		
		JFreeChart jFreeChart = createPieChart(typeid,location);
		JPanel radarPanel = new ChartPanel(jFreeChart);
		add(radarPanel, "cell 1 0,grow");

	}

	private void setJTable(int typeid,int location) {
		Object[][] list=analysisServiceImpl.getRadar(typeid,location);	
		table = new JTable();
		if (list != null && list.length != 0 ) {
			Object[][] newdata=initResultData(list);
			table.setModel(new DefaultTableModel(newdata,new String[] {"编号", "雷达编号","所属部队","健康状态"}));
        } 
		else {
            // 如果结果集中没有数据，那么就用空来代替数据集中的每一行
            Object[][] nothing = { {}, {}, {}, {}, {}, {}, {}, {}, {}, {}};
            table.setModel(new DefaultTableModel(nothing,new String[] {"编号", "雷达编号","所属部队","健康状态"}));
        }
		table.setFont(new Font("仿宋", Font.PLAIN, 14));
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		TableStyleUI ui = new TableStyleUI();
        ui.makeFace(table);
        JScrollPane JSP=new JScrollPane(table);
		add(JSP, "cell 0 0,grow");
	}
	private Object[][] initResultData(Object[][] data) {
		int N = data.length;
		int l = N%10;
		int k = N/10;
		if(l>0)
			N = 10*(k+1);
		Object[][] resultData = new Object[N+2][];
		for(int i=0;i<data.length;i++) {
			resultData[i] = data[i];
		}
		return resultData;
	}
	
	private JFreeChart createPieChart(int typeid, int location) {
		PieDataset paramPieDataset=analysisServiceImpl.createPieData(typeid,location);
		String[] data=analysisServiceImpl.titleName(typeid,location);
	    JFreeChart jFreeChart = ChartFactory.createPieChart(null, paramPieDataset, true, true, false);
	    jFreeChart.addSubtitle((Title)new TextTitle(data[0]+"-"+data[1]+"健康状态统计", new Font("仿宋", Font.BOLD, 24)));
	    jFreeChart.getLegend().setItemFont(new Font("仿宋",Font.PLAIN,14));
	    PiePlot piePlot = (PiePlot)jFreeChart.getPlot();
	    piePlot.setSectionPaint("绿",Color.GREEN);
	    piePlot.setSectionPaint("黄",Color.YELLOW);
	    piePlot.setSectionPaint("红",Color.RED);
	    piePlot.setBackgroundAlpha(0.0f);
	    piePlot.setOutlinePaint(Color.WHITE);
	    piePlot.setShadowPaint(Color.WHITE);
	    piePlot.setLabelFont(new Font("仿宋",Font.PLAIN,14));
	    piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0},{1}"));
	    return jFreeChart;
	  }

	public JTable getTable() {
		return table;
	}
	
}
