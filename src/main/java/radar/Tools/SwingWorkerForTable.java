package radar.Tools;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import radar.SpringUtil;
import radar.UI.Components.Table;

public class SwingWorkerForTable extends SwingWorker<Object[][],Void>{

	private Table table;
	private String className;
	private String methodName;
	private Object[] params;
	
	public SwingWorkerForTable(Table table,String className,String methodName, Object[] params) {
		
		this.table = table;
		this.className = className;
		this.methodName = methodName;	
		this.params = params;
	}
	
	@Override
	protected Object[][] doInBackground()throws Exception {		
		Object t = SpringUtil.getBean(className);		
		Method method = t.getClass().getMethod(methodName,Object[].class);
		Object[][] result = (Object[][]) method.invoke(t,(Object)params);
		return result;
	}
	
	@Override
	protected void done() {
		try {
			Object[][] data = get();
			table.init(data);			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}

}
