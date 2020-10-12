package radar.UI.Components;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import radar.Tools.LoadingData;
import radar.Tools.LoadingDataClass;
import java.awt.BorderLayout;

public class PieChart extends JPanelTransparent implements LoadingData{
	
	/**
	 * 饼图控件
	 * @author madi
	 */
	private static final long serialVersionUID = 1898284017545564731L;
	
	private String title;
	private String className;
	private String methodName;
	private Object[] params;
	
	private ChartPanel chartPanel;
	private JFreeChart pieChart;
	private DefaultPieDataset data;
		
	/**
	 * 
	 * @param title 标题
	 * @param className 获取下拉框数据方法所在类的名字
	 * @param methodName 获取下拉框数据方法的方法名
	 * @param params 获取下拉框数据方法所需的参数，统一放在Object[]中，在方法中挨个读取
	 */
	public PieChart(String title,String className,String methodName, Object[] params) {
		this.title = title;
		this.className = className;
		this.methodName = methodName;
		this.params = params;		
	}

	public void init() {
		//容器样式初始化
		setBorder(null);
		
		//图表初始化
		setPieChart();
		setChartPanel();
		setLayout(new BorderLayout(0, 0));
		add(chartPanel);
		
		//查询加载数据
		LoadingDataClass loading = new LoadingDataClass(this, className, methodName,params);
		loading.execute();
	}
	
	public void refreshPieChart() {
		LoadingDataClass loading = new LoadingDataClass(this, className, methodName,params);
		loading.execute();
	}
	private void setPieChart() {
		//创建数据集对象
		data = new DefaultPieDataset();
		
		//创建JFreeChart对象
		pieChart = ChartFactory.createPieChart(title, data, true, true, true);
		pieChart.setBorderVisible(false);
		pieChart.setBackgroundPaint(null);
		pieChart.setBackgroundImageAlpha(0.0f);
		
		pieChart.setTitle(new TextTitle(title,new Font("宋体", Font.BOLD, 24)));		

        LegendTitle legend = pieChart.getLegend(0);
        legend.setFrame(new BlockBorder(Color.white));
        legend.setItemFont(new Font("宋体",Font.PLAIN,14));//修改图例的字体
        PiePlot p = (PiePlot) pieChart.getPlot();
        
        p.setLabelFont(new Font("宋体", Font.PLAIN,14));     //水平底部标题
        p.setNoDataMessage("数据加载中，请稍候......"); // 没有数据的时候显示的内容 
        p.setLabelGenerator(new StandardPieSectionLabelGenerator(("{0}:{1}"), NumberFormat.getNumberInstance(),new DecimalFormat("0.00"))); 
        
        p.setOutlinePaint(Color.WHITE); // 设置绘图面板外边的填充颜色
        p.setShadowPaint(Color.WHITE); // 设置绘图面板阴影的填充颜色
		// 饼图的透明度
		p.setForegroundAlpha(0.5f);
		// 饼图的背景全透明
		p.setBackgroundAlpha(0.0f);
		
		// 设置标题颜色
		pieChart.getTitle().setPaint(ChartColor.black);
	}
	
	private void setChartPanel() {
		chartPanel = new ChartPanel(pieChart);
		chartPanel.setBorder(null);
		chartPanel.setOpaque(true);
		chartPanel.setBackground(Color.WHITE);
	}

	@Override
	public void loadingData(Object data) {
		PiePlot p = (PiePlot) pieChart.getPlot();
		p.setDataset((DefaultPieDataset) data);
		if(((DefaultPieDataset)data).getKeys().contains("绿")) {
			p.setSectionPaint("绿",Color.GREEN);
			p.setSectionPaint("黄",Color.YELLOW);
			p.setSectionPaint("红",Color.RED);
		}
	}
}
