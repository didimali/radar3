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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.RadarHealth;
import radar.ServiceImpl.BigDataServiceImpl;
import radar.ServiceImpl.RadarServiceImpl;
import radar.Tools.BigDataTableUI;
import radar.Tools.TableStyleUI;

import javax.swing.JScrollPane;

public class RadarTypeList extends ContentPanel{

	private static final long serialVersionUID = -6029898662509471669L;
	RadarServiceImpl radarServiceImpl = (RadarServiceImpl) SpringUtil.getBean("RadarServiceImpl");
	public RadarTypeList() {		
		initContentTop();
		initContentBody();
	}	
	
	public void initContentTop() {
		contentTop.setBackground(Color.WHITE);
		contentTop.setLayout(new MigLayout("", "[grow]", "[grow][4px]"));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);

		JLabel title = new JLabel("宏观评控");
		title.setFont(new Font("仿宋", Font.BOLD, 24));			
		contentTop.add(title, "cell 0 0,grow");	
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.WHITE);
		contentTop.add(separator, "cell 0 1,growx,aligny bottom");
	}

	
	public void initContentBody() {
		ContentBody.setBackground(Color.WHITE);
		//ContentBody.setLayout(new MigLayout("", "[grow]", "[168px][grow]"));
		ContentBody.setLayout(new MigLayout("", "[grow]", "[45%][grow]"));
		Object[][] list=radarServiceImpl.countRadarList();
		JTable RadarTable = new JTable();
		//RadarTable.setEnabled(false);
		//RadarTable.setRowSelectionAllowed(false);
		RadarTable.setModel(new DefaultTableModel(list,new String[] {"编号", "雷达型号","总数量(台)","较差(台)","不良(台)","健康(台)"}			
		));
		RadarTable.setFont(new Font("宋体", Font.PLAIN, 14));
		BigDataTableUI ui = new BigDataTableUI();
        ui.makeFace(RadarTable);
		JScrollPane JSP= new JScrollPane(RadarTable);		
		ContentBody.add(JSP, "cell 0 0,grow");
	
		JPanel panel = new JPanel(new MigLayout("", "[grow][grow]", "[grow]"));
		JFreeChart jFreeChart1 = createPieChart1();
		JPanel radarPanel1 = new ChartPanel(jFreeChart1);
		panel.add(radarPanel1, "cell 0 0,grow");
		JFreeChart jFreeChart2 = createPieChart2();
		JPanel radarPanel2 = new ChartPanel(jFreeChart2);
		panel.add(radarPanel2, "cell 1 0,grow");
		ContentBody.add(panel, "cell 0 1,grow");
		
		
	}

	public void initContentFoot() {
		
	}
	  
	  private JFreeChart createPieChart1() {
		PieDataset paramPieDataset=radarServiceImpl.createDataset1();
	    JFreeChart jFreeChart = ChartFactory.createPieChart(null, paramPieDataset, true, true, false);
	    jFreeChart.addSubtitle((Title)new TextTitle("I型雷达宏观评控统计", new Font("宋体", Font.BOLD, 15)));
	    jFreeChart.getLegend().setItemFont(new Font("宋体",Font.PLAIN,14));
	    PiePlot piePlot = (PiePlot)jFreeChart.getPlot();
	    piePlot.setSectionPaint("健康",Color.GREEN);
	    piePlot.setSectionPaint("不良",Color.YELLOW);
	    piePlot.setSectionPaint("较差",Color.RED);
	    piePlot.setBackgroundAlpha(0.0f);
	    piePlot.setLabelFont(new Font("宋体",Font.PLAIN,14));
	    piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0},{1}"));
	    return jFreeChart;
	  }

	  private JFreeChart createPieChart2() {
		PieDataset paramPieDataset=radarServiceImpl.createDataset2();
	    JFreeChart jFreeChart = ChartFactory.createPieChart(null, paramPieDataset, true, true, false);
	    jFreeChart.addSubtitle((Title)new TextTitle("II型雷达宏观评控统计", new Font("宋体", Font.BOLD, 15)));
	    jFreeChart.getLegend().setItemFont(new Font("宋体",Font.PLAIN,14));
	    PiePlot piePlot = (PiePlot)jFreeChart.getPlot();
	    piePlot.setSectionPaint("健康",Color.GREEN);
	    piePlot.setSectionPaint("不良",Color.YELLOW);
	    piePlot.setSectionPaint("较差",Color.RED);
	    piePlot.setBackgroundAlpha(0.0f);
	    piePlot.setLabelFont(new Font("宋体",Font.PLAIN,14));
	    piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0},{1}"));
	    return jFreeChart;
	  }
}
