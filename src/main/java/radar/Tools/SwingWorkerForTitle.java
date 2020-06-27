package radar.Tools;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.jfree.data.general.DefaultPieDataset;

import radar.SpringUtil;
import radar.UI.Components.Title;

public class SwingWorkerForTitle extends SwingWorker<String,Void>{

	private Title title;
	private String className;
	private String methodName;
	private Object[] params;
	public SwingWorkerForTitle(Title title, String className, String methodName, Object[] params) {
		this.className = className;
		this.methodName = methodName;
		this.params = params;
		this.title = title;
	}

	@Override
	protected String doInBackground() throws Exception {
		Object o = SpringUtil.getBean(className);
		Method method = o.getClass().getMethod(methodName,Object[].class);
		String data = (String) method.invoke(o,(Object)params);
		return data;
	}
	
	@Override
	protected void done() {
		try {
			String text = get();
			title.getRepairPlanDate().setText(text);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}

}
