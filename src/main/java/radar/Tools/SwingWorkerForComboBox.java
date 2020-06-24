package radar.Tools;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import radar.SpringUtil;
import radar.UI.Components.ComboBox;

public class SwingWorkerForComboBox extends SwingWorker<Object[],Void>{

	private ComboBox box;
	private String className;
	private String methodName;
	private Object[] params;
	
	/**
	 * 为下拉框提供数据的工具类
	 * @param params 
	 * @param box:需要数据的下拉框
	 * @param className:下拉框服务实现层的名字
	 * @param method:下拉框服务实现层获取数据的方法名字
	 * @param params 获取下拉框数据方法所需的参数，统一放在Object[]中，在方法中挨个读取
	 */
	
	public SwingWorkerForComboBox(ComboBox box,String className,String methodName, Object[] params) {		
		this.box = box;
		this.className = className;
		this.methodName = methodName;	
		this.params = params;
	}
	
	@Override
	protected Object[] doInBackground() throws Exception {
		Object o = SpringUtil.getBean(className);
		Method method = o.getClass().getMethod(methodName,Object[].class);
		Object[] result = (Object[]) method.invoke(o,(Object)params);
		return result;
	}
	
	@Override
	protected void done() {		
		try {
			Object[] data = get();
			box.init(data);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}		
	}
}
