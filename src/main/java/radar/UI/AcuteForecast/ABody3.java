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
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Font;

/**
 * 统计分析-内容三
 */
public class ABody3 extends JPanel{
	
	private static final long serialVersionUID = 1L;
	AnalysisServiceImpl analysisServiceImpl = (AnalysisServiceImpl) SpringUtil.getBean("AnalysisServiceImpl");
	
	public ABody3(int typeid, int location,String startDate,String endDate) {
        
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[60%][grow]", "[grow]"));	
		
		setJTable(typeid,location,startDate,endDate);		
		setJChart(typeid,location,startDate,endDate);
		
	}

	private void setJChart(int typeid,int location,String startDate,String endDate) {
		
		JFreeChart jFreeChart = createPieChart(typeid,location,startDate,endDate);
		JPanel radarPanel = new ChartPanel(jFreeChart);
		add(radarPanel, "cell 1 0,grow");

	}

	private void setJTable(int typeid,int location,String startDate,String endDate) {
		Object [][] list=analysisServiceImpl.getFaultList(typeid,location,startDate,endDate);		
		JTable table = new JTable();
		if (list != null && list.length != 0 ) {
			Object[][] newdata=initResultData(list);
			table.setModel(new DefaultTableModel(newdata,new String[] {"编号", "雷达结构","故障数量"}));
        } 
		else {
            // 如果结果集中没有数据，那么就用空来代替数据集中的每一行
            Object[][] nothing = { {}, {}, {}, {}, {}, {}, {}, {}, {}, {}};
            table.setModel(new DefaultTableModel(nothing,new String[] {"编号", "雷达结构","故障数量"}));
        }		
		table.setFont(new Font("宋体", Font.PLAIN, 14));
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		TableStyleUI ui = new TableStyleUI();
        ui.makeFace(table);
        JScrollPane JSP=new JScrollPane(table);
		add(JSP, "cell 0 0,grow");
	}

	private JFreeChart createPieChart(int typeid, int location,String startDate,String endDate) {
		PieDataset paramPieDataset=analysisServiceImpl.createPieData2(typeid,location,startDate,endDate);
		String[] data=analysisServiceImpl.titleName(typeid,location);
	    JFreeChart jFreeChart = ChartFactory.createPieChart(null, paramPieDataset, true, true, false);
	    jFreeChart.addSubtitle((Title)new TextTitle(data[0]+"-"+data[1]+"故障数量统计", new Font("宋体", Font.BOLD, 24)));
	    jFreeChart.getLegend().setItemFont(new Font("宋体",Font.PLAIN,14));
	    PiePlot piePlot = (PiePlot)jFreeChart.getPlot();
	    piePlot.setBackgroundAlpha(0.0f);
	    piePlot.setOutlinePaint(Color.WHITE);
	    piePlot.setShadowPaint(Color.WHITE);
	    piePlot.setLabelFont(new Font("宋体",Font.PLAIN,14));
	    piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0},{1}"));
	    return jFreeChart;
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
}
