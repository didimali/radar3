package radar.UI.Components;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.util.ShapeUtilities;

import radar.Tools.Init;
import radar.Tools.SwingWorkerForHistoryLineForHI;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;

/**
 * 雷达健康状况变化趋势图实现类
 * @author madi
 *
 */
public class HistoryLineForHI extends JPanel implements Init{
	
	private static final long serialVersionUID = 1L;
	
	private JFreeChart line;
	private ChartPanel chart;
	private DefaultCategoryDataset data;
	
	private String className;
	private String methodName;
	private Object[] params = {null};
	private String titleText;
	
	/**
	 * 
	 * @param titleText 图标题
	 * @param className 类名
	 * @param methodName 方法名
	 * @param radarId 雷达ID
	 */
	public HistoryLineForHI(String titleText,String className,String methodName,int radarId) {
		this.titleText = titleText;
		this.className = className;
		this.methodName = methodName;
		params[0] = radarId;
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		initUI();
	}

	@Override
	public void initUI() {
		getData();
		createLine();
		chart = new ChartPanel(line);
		add(chart, BorderLayout.CENTER);
	}
	
	private void getData() {
		SwingWorkerForHistoryLineForHI swHi = new SwingWorkerForHistoryLineForHI(this, className, methodName, params);
		swHi.execute();
	}

	@Override
	public void Action() {
		
	}

	private void createLine() {
		data = new DefaultCategoryDataset();
		
		line = ChartFactory.createLineChart(titleText,null, null, data, PlotOrientation.VERTICAL,false, true, false);
		line.setBorderVisible(false);
		line.setTitle(new TextTitle(titleText,new Font("宋体", Font.BOLD, 16)));	
		
		CategoryPlot categoryPlot = (CategoryPlot)line.getPlot();
		
	    categoryPlot.setBackgroundAlpha(0.0f); //背景透明
	    categoryPlot.setNoDataMessage("暂无数据，请稍候......"); // 没有数据的时候显示的内容 
	    
	    SymbolAxis symbolAxis = new SymbolAxis(null, new String[] { "红", "黄", "绿"});
	    symbolAxis.setTickLabelFont(new Font("仿宋",Font.BOLD, 12));
	    
	    categoryPlot.setRangeAxis((ValueAxis)symbolAxis);	
	    CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
	    categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	    LineAndShapeRenderer xylinerenderer = (LineAndShapeRenderer)categoryPlot.getRenderer(); 
   		xylinerenderer.setBaseShapesVisible(true); //显示点
   		xylinerenderer.setSeriesLinesVisible(0,false);//不显示线
   		xylinerenderer.setSeriesShape(0, ShapeUtilities.createDiamond(8.0F));   		
   		
	}

	public JFreeChart getJFreeChart() {
		return line;
	}

	

}
