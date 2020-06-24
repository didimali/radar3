package radar.Tools;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import radar.SpringUtil;
import radar.UI.Components.BarChart;

/**
 * 柱状图的数据获取类
 * @author madi
 *
 */
public class SwingWorkerForBarChart extends SwingWorker<DefaultCategoryDataset,Void>{

	private BarChart barChart;
	private String className;
	private String methodName;
	private Object[] params;
	
	public SwingWorkerForBarChart(BarChart barChart, String className, String methodName, Object[] params) {
		this.barChart = barChart;
		this.className = className;
		this.methodName = methodName;		
		this.params = params;
	}

	@Override
	protected DefaultCategoryDataset doInBackground() throws Exception {
		Object o = SpringUtil.getBean(className);
		Method method = o.getClass().getMethod(methodName,Object[].class);
		DefaultCategoryDataset result = (DefaultCategoryDataset) method.invoke(o,(Object)params);
		return result;
	}
	
	@Override 
	protected void done() {
		DefaultCategoryDataset data;
		try {
			data = get();
			CategoryPlot plot = barChart.getJFreeChart().getCategoryPlot();
			plot.setDataset(data);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}

}
