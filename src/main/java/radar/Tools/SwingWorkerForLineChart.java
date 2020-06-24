package radar.Tools;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import radar.SpringUtil;
import radar.UI.Components.LineChart;

/**
 * 折线图的数据查询类
 */
public class SwingWorkerForLineChart extends SwingWorker<DefaultCategoryDataset,Void>{
	
	private LineChart lineChart;
	private String className;
	private String methodName;
	private Object[] params;
	
	public SwingWorkerForLineChart(LineChart lineChart,String className,String methodName, Object[] params) {
		this.lineChart = lineChart;
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
		try {
			DefaultCategoryDataset data = get();
			CategoryPlot plot = lineChart.getJFreeChart().getCategoryPlot();
			plot.setDataset(data);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
