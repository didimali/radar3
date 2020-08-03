package radar.SwingWorker;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import radar.SpringUtil;
import radar.UI.Components.BackupPartsBox;
import radar.UI.Components.ManagerCombox;

public class SwingWorkerForPartsBox extends SwingWorker<Object[],Void>{
	private BackupPartsBox box;
	private String className;
	private String methodName;
	private Object[] resultData = {};

	
	/**
	 * 为下拉框提供数据的工具类
	 * @param box:需要数据的下拉框
	 * @param className:下拉框服务实现层的名字
	 * @param method:下拉框服务实现层获取数据的方法名字
	 */
	
	public SwingWorkerForPartsBox(BackupPartsBox box,String className,String methodName) {		
		this.box = box;
		this.className = className;
		this.methodName = methodName;	

	}
	
	@Override
	protected Object[] doInBackground() throws Exception {
		Object o = SpringUtil.getBean(className);
		Method method = o.getClass().getMethod(methodName);
		Object[] result = (Object[]) method.invoke(o);
		return result;
	}
	
	@Override
	protected void done() {
		
		try {
			Object[] data = get();
			if(data!= null||data.length!=0) {
				resultData = new String[1+data.length];
				resultData[0] = "全部";
				for(int i=0;i<data.length;i++) {
					resultData[i+1] =data[i];				
				}
			}
			box.init(resultData);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		
	}

}
