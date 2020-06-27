package radar.Tools;

import java.awt.Color;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import radar.SpringUtil;
import radar.UI.Components.PieChart;

/**
 * 获取饼图数据的SwingWorker类
 * @author madi
 */
public class SwingWorkerForPieChart extends SwingWorker<DefaultPieDataset,Void>{

	private PieChart panel;
	private String className;
	private String methodName;
	private Object[] params;
	
	/**
	 * @param params 获取数据所需方法的参数
	 * @param panel:饼图对象实例
	 * @param className:获取数据所需方法所在ServiceImpl的名字
	 * @param methodName:获取数据所需方法的名字
	 */
	public SwingWorkerForPieChart(PieChart panel,String className,String methodName, Object[] params) {
		this.panel = panel;
		this.className = className;
		this.methodName = methodName;
		this.params = params;
	}
	
	@Override
	protected DefaultPieDataset doInBackground() throws Exception {
		Object o = SpringUtil.getBean(className);
		Method method = o.getClass().getMethod(methodName,Object[].class);
		DefaultPieDataset data = (DefaultPieDataset) method.invoke(o,(Object)params);
		return data;
	}
	
	@Override
	protected void done() {		
		try {
			DefaultPieDataset data;
			data = get();
			PiePlot p = (PiePlot) panel.getJFreeChart().getPlot();
			p.setDataset(data);
			if(data.getKeys().contains("绿")) {
				p.setSectionPaint("绿",Color.GREEN);
				p.setSectionPaint("黄",Color.YELLOW);
				p.setSectionPaint("红",Color.RED);
			}
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		
	}

}
