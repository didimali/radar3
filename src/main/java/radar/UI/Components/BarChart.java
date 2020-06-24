package radar.UI.Components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import radar.Tools.SwingWorkerForBarChart;
import java.awt.BorderLayout;

/**
 * 柱状图工具类
 */
public class BarChart extends JPanel{
	
	private static final long serialVersionUID = 7549911555945545120L;
	
	private String title;
	private String xTitle;
	private String yTitle;
	
	private String className;
	private String methodName;
	private Object[] params;
	
	private ChartPanel chartPanel;
	private JFreeChart barChart;
	
	/**
	 * 
	 * @param title 标题
	 * @param xTitle x轴标题
	 * @param yTitle y轴标题
	 * @param className 数据查询方法所在类的类名
	 * @param methodName 数据查询方法的方法名
	 * @param params 数据查询方法所需的参数
	 */
	public BarChart(String title,String xTitle,String yTitle,String className,String methodName, Object[] params) {
		
		this.title = title;
		this.xTitle = xTitle;
		this.yTitle = yTitle;
		this.className = className;
		this.methodName = methodName;
		this.params = params;
		setLayout(new BorderLayout(0, 0));

	}
	
	public void init() {
		//容器初始化
		setBackground(Color.WHITE);
		setOpaque(true);
		
		//创建图表
		createBarChart();
		add(chartPanel);
		
		
		//查询加载数据		
		SwingWorkerForBarChart swb = new SwingWorkerForBarChart(this, className, methodName,params);
		swb.execute();
		
	}

	private void createBarChart() {
		//创建数据集
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		data.addValue( 15 , "数据" , "类型1" );
		data.addValue( 30 , "数据" , "类型2" );
		data.addValue( 60 , "数据" , "类型3" );
		data.addValue( 120 , "数据" , "类型4" );
		data.addValue( 240 , "数据" , "类型5" ); 
		data.addValue( 300 , "数据" , "类型6" );

		//创建JFreechart对象
		barChart = ChartFactory.createBarChart(title,xTitle, yTitle, data,
				PlotOrientation.VERTICAL,true,true,false);
		//修改JFreeChart对象高级属性
		barChart.setBorderVisible(false);
		
		LegendTitle legend = barChart.getLegend(0);
        legend.setFrame(new BlockBorder(Color.white));
        legend.setItemFont(new Font("宋体",Font.PLAIN,12));//修改图例的字体
		
		//修改高级属性
		CategoryPlot plot = barChart.getCategoryPlot();
		
		plot.setNoDataMessage("暂时没有数据，请稍候"); // 没有数据的时候显示的内容 
		
		// 透明度
		plot.setForegroundAlpha(0.5f);
		// 背景全透明
		plot.setBackgroundAlpha(0.0f);
		
		//设置标题文字
		barChart.setTitle(new TextTitle(title,new Font("宋体", Font.BOLD, 24)));	
		//设置X轴坐标上的文字 
		plot.getDomainAxis().setTickLabelFont(new Font("宋体", Font.PLAIN, 12)); 
		//设置X轴的标题文字 
		plot.getDomainAxis().setLabelFont(new Font("宋体", Font.PLAIN, 14));
		//设置Y轴坐标上的文字 
		plot.getRangeAxis().setTickLabelFont(new Font("宋体", Font.PLAIN, 12)); 
		//设置Y轴的标题文字 
		plot.getRangeAxis().setLabelFont(new Font("黑体", Font.PLAIN, 14)); 
		
				
		//设置网格竖线 
		plot.setDomainGridlinesVisible(false); 
		//设置网格横线颜色 
		plot.setRangeGridlinesVisible(false); 
		//图片背景色 
		plot.setBackgroundPaint(Color.WHITE); 
		plot.setOutlineVisible(false); 
		//图边框颜色 
		plot.setOutlinePaint(Color.WHITE); 
		//设置柱的透明度 
		plot.setForegroundAlpha(1.0f); 
		
		//创建呈现媒介
		chartPanel = new ChartPanel(barChart);
	}

	public JFreeChart getJFreeChart() {
		return barChart;
	}

}
