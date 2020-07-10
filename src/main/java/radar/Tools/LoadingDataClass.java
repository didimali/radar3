package radar.Tools;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import radar.SpringUtil;

/**
 * 数据加载类
 * @author Administrator
 *
 */
public class LoadingDataClass extends SwingWorker<Object,Void>{

	private LoadingData jComponent;
	private String className;
	private String methodName;
	private Object[] params;
	
	public LoadingDataClass(LoadingData jComponent, String className, String methodName, Object[] params) {
		this.jComponent = jComponent;
		this.className = className;
		this.methodName = methodName;		
		this.params = params;
	}

	@Override
	protected Object doInBackground() throws Exception {
		Object o = SpringUtil.getBean(className);
		Method method = o.getClass().getMethod(methodName,Object[].class);
		Object result = method.invoke(o,(Object)params);
		return result;
	}
	
	@Override 
	protected void done() {
		try {
			jComponent.loadingData(get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}

}
