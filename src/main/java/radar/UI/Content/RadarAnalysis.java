package radar.UI.Content;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import radar.SpringUtil;
import radar.ServiceImpl.BigDataServiceImpl;
import radar.Tools.BigDataTableUI;
import radar.Tools.TableStyleUI;
import net.miginfocom.swing.MigLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.util.ShapeUtilities;

/**
 * 雷达管理-大数据分析-健康评估
 */
public class RadarAnalysis extends ContentPanel{
	
	private static final long serialVersionUID = 2288694751645396402L;
	BigDataServiceImpl bigDataServiceImpl = (BigDataServiceImpl) SpringUtil.getBean("BigDataServiceImpl");
	
	private String text2;
	private String text3;
	private JLabel title1;
	private JLabel title2_1;
	public RadarAnalysis(String id,String title2,String title3) {
		text2 = title2;
		text3 = title3+"分析结果";
		int num=bigDataServiceImpl.dataVerify(id);
		if(num==0) {
			initContentTop();	
			JOptionPane.showMessageDialog(panel, "暂无数据！","警告信息",JOptionPane.WARNING_MESSAGE); 	
		}else{
			initContentTop();
			initContentBody(id);
		}
	}

	public void initContentTop() {
		contentTop.setBackground(Color.WHITE);
		contentTop.setLayout(new MigLayout("", "[grow]", "[grow][4px]"));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentTop.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][][][][]", "[grow]"));
		
		title1 = new JLabel("宏观评控");
		title1.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title1, "cell 0 0,growx,aligny center");
		
		JLabel s1 = new JLabel(">>");
		s1.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(s1, "cell 1 0,growx,aligny center");
		
		title2_1 = new JLabel(text2);
		title2_1.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title2_1, "cell 2 0,growx,aligny center");
		
		JLabel s2 = new JLabel(">>");
		s2.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(s2, "cell 3 0,growx,aligny center");
		
		JLabel title3 = new JLabel(text3);
		title3.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(title3, "cell 4 0,growx,aligny center");
		
//		JLabel title = new JLabel("精准预测>>部队雷达型号>>部队雷达列表>>详细分析结果");
//		title.setFont(new Font("仿宋", Font.BOLD, 24));			
//		contentTop.add(title, "cell 0 0,grow");	
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.WHITE);
		contentTop.add(separator, "cell 0 1,growx,aligny bottom");
	}

	 
	public void initContentBody(String id) {
		ContentBody.setLayout(new MigLayout("", "[grow][grow]", "[5%][40%][5%][50%]"));			
		String name=bigDataServiceImpl.getRadarName(id);
		
		JLabel healthLabel = new JLabel(name+"当前健康状态");
		healthLabel.setFont(new Font("宋体", Font.BOLD, 14));
		ContentBody.add(healthLabel, "cell 0 0,alignx center");
		
		JLabel faultLable = new JLabel(name+"故障预测结果");
		faultLable.setFont(new Font("宋体", Font.BOLD, 14));
		ContentBody.add(faultLable, "cell 1 0,alignx center");	    
		//健康评估结果
		Object[][] Healthlist=bigDataServiceImpl.getHealth(id);
		JTable healthTable = new JTable();
		healthTable.setEnabled(false);
		healthTable.setRowSelectionAllowed(false);
		healthTable.setModel(new DefaultTableModel(Healthlist,new String[] {"编号", "系统名称","健康状态","评估时间"}));       
		healthTable.setFont(new Font("宋体", Font.PLAIN, 14));
		BigDataTableUI ui = new BigDataTableUI();
        ui.makeFace(healthTable);
		JScrollPane JSP= new JScrollPane(healthTable);
		JSP.setEnabled(false);
		ContentBody.add(JSP, "cell 0 1,growx");
		//故障预测结果
		Object[][] Faultlist=bigDataServiceImpl.getFault(id);
		JTable faultTable = new JTable();
		faultTable.setEnabled(false);
		faultTable.setRowSelectionAllowed(false);
		faultTable.setModel(new DefaultTableModel(Faultlist,new String[] {"故障名称","故障部位","故障机理","预计发生时间"}
		));
		faultTable.setFont(new Font("宋体", Font.PLAIN, 14));	
		BigDataTableUI ui2 = new BigDataTableUI();
        ui2.makeFace(faultTable);
		JScrollPane JSP2= new JScrollPane(faultTable);		
		JSP2.setEnabled(false);
		ContentBody.add(JSP2, "cell 1 1,growx");
	    //健康评估历史趋势图	
		JLabel historyLabel = new JLabel(name+"总系统历史健康状态图");
		historyLabel.setFont(new Font("宋体", Font.BOLD, 14));	
		ContentBody.add(historyLabel, "cell 0 2,alignx center");
		
		JFreeChart healthChart = historyChart(historyData(id));
		JPanel healthPanel = new ChartPanel(healthChart);
		ContentBody.add(healthPanel, "cell 0 3,growx");		
		//备件资源定制
		JLabel maintainLable = new JLabel(name+"所需维修备件资源");
		maintainLable.setFont(new Font("仿宋", Font.BOLD, 14));	
		ContentBody.add(maintainLable, "cell 1 2,alignx center");
		
		JFreeChart partsChart = PartsChart(partsData(id));
		JPanel partsPanel = new ChartPanel(partsChart);
		ContentBody.add(partsPanel, "cell 1 3,growx");	
		
	}

	 private CategoryDataset historyData(String id) {
		    DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
		    Object[][] state=bigDataServiceImpl.getHistory(id);
		    for(int i=0;i<state.length;i++) {
		    	defaultCategoryDataset.addValue(Integer.parseInt(state[i][0].toString()), "HI",state[i][1].toString());
		    }
		    return (CategoryDataset)defaultCategoryDataset;
		  }	
		private static JFreeChart historyChart(CategoryDataset paramCategoryDataset) {
		    JFreeChart jFreeChart = ChartFactory.createLineChart(null,null, null, paramCategoryDataset, PlotOrientation.VERTICAL,false, true, false);
		    CategoryPlot categoryPlot = (CategoryPlot)jFreeChart.getPlot();
		    categoryPlot.setBackgroundAlpha(0.0f); //背景透明
		    SymbolAxis symbolAxis = new SymbolAxis(null, new String[] { "红", "黄", "绿"});
		    symbolAxis.setTickLabelFont(new Font("仿宋",Font.BOLD, 14));
		    categoryPlot.setRangeAxis((ValueAxis)symbolAxis);	
		    CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
			categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
		    LineAndShapeRenderer xylinerenderer = (LineAndShapeRenderer)categoryPlot.getRenderer(); 
	   		xylinerenderer.setBaseShapesVisible(true); //显示点
	   		xylinerenderer.setSeriesLinesVisible(0,false);//不显示线
	   		xylinerenderer.setSeriesShape(0, ShapeUtilities.createDiamond(8.0F));
		    return jFreeChart;
		  }
	
	private CategoryDataset  partsData(String id) {
	    DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
	    String str = "备件需求";
	    Object[][] data=bigDataServiceImpl.getPartsNum(id);
	    for(int i=0;i<data.length;i++) {
	    	defaultCategoryDataset.addValue(Integer.parseInt(data[i][1].toString()), str,data[i][0].toString());
	    }
	    return (CategoryDataset)defaultCategoryDataset;
	  }	
	 public static JFreeChart PartsChart(CategoryDataset paramArrayOfCategoryDataset) {
		    JFreeChart jFreeChart = ChartFactory.createBarChart(null, null,null, paramArrayOfCategoryDataset,PlotOrientation.VERTICAL, false,true, false);
			CategoryPlot categoryPlot = (CategoryPlot)jFreeChart.getPlot(); 
			categoryPlot.getDomainAxis().setTickLabelFont(new Font("宋体",Font.PLAIN,12));//设置x轴坐标上的字体
			categoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT); //坐标在下方		
			categoryPlot.getRangeAxis().setRange(0, 10); //x轴区间
			categoryPlot.setBackgroundAlpha(0.0f); //背景透明
			CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
			categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
			BarRenderer barRenderer = (BarRenderer)categoryPlot.getRenderer();
		    barRenderer.setItemLabelAnchorOffset(9.0D);    //上下间距
		    barRenderer.setMaximumBarWidth(0.05);
		    barRenderer.setBaseItemLabelsVisible(true);    //显示字 
		    barRenderer.setBaseItemLabelGenerator((CategoryItemLabelGenerator)new StandardCategoryItemLabelGenerator());   //显示字
		    return jFreeChart;
		  }
	public JLabel getTitle1() {
		return title1;
	}
	public JLabel getTitle2() {
		return title2_1;
	}
}
