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
import net.miginfocom.swing.MigLayout;
import radar.Tools.SwingWorkerForLineChart;


public class LineChart extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8119283721450606737L;

	/**
	 * 折线图控件
	 * @author madi
	 */
	
	private String title;
	private String xTitle;
	private String yTitle;
	private String className;
	private String methodName;
	private Object[] params;
	
	
	private ChartPanel chartPanel;
	private JFreeChart lineChart;
	
	/**
	 * 
	 * @param title 标题
	 * @param xTitle x轴标题
	 * @param yTitle y轴标题
	 * @param className 获取下拉框数据方法所在类的名字
	 * @param methodName 获取下拉框数据方法的方法名
	 * @param params 获取下拉框数据方法所需的参数，统一放在Object[]中，在方法中挨个读取
	 */
	public LineChart(String title,String xTitle,String yTitle,String className,String methodName, Object[] params) {
		this.title = title;
		this.xTitle = xTitle;
		this.yTitle = yTitle;
		this.className = className;
		this.methodName = methodName;
		this.params = params;
	}

	public void init() {
		
		//容器初始化
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		//创建图表
		createLineChart();
		add(chartPanel, "grow");
		
		//查询加载数据		
		SwingWorkerForLineChart swl = new SwingWorkerForLineChart(this, className, methodName,params);
		swl.execute();
		
	}

	private void createLineChart() {
		//创建数据集
		DefaultCategoryDataset data = new DefaultCategoryDataset();
//		data.addValue( 15 , "schools" , "1970" );
//		data.addValue( 30 , "schools" , "1980" );
//		data.addValue( 60 , "schools" , "1990" );
//		data.addValue( 120 , "schools" , "2000" );
//		data.addValue( 240 , "schools" , "2010" ); 
//		data.addValue( 300 , "schools" , "2014" );

		//创建JFreechart对象
		lineChart = ChartFactory.createLineChart(title,xTitle, yTitle, data,
				PlotOrientation.VERTICAL,true,true,false);
		//修改JFreeChart对象高级属性
		lineChart.setBorderVisible(false);
		
		LegendTitle legend = lineChart.getLegend(0);
        legend.setFrame(new BlockBorder(Color.white));
        legend.setItemFont(new Font("宋体",Font.PLAIN,12));//修改图例的字体
		
		//修改高级属性
		CategoryPlot plot = lineChart.getCategoryPlot();
		
		plot.setNoDataMessage("暂无数据，请稍候......"); // 没有数据的时候显示的内容 
		
		// 透明度
		plot.setForegroundAlpha(0.5f);
		// 背景全透明
		plot.setBackgroundAlpha(0.0f);
		
		//设置标题文字
		lineChart.setTitle(new TextTitle(title,new Font("宋体", Font.BOLD, 24)));	
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
		chartPanel = new ChartPanel(lineChart);
	}

	public JFreeChart getJFreeChart() {
		return lineChart;
	}
}
