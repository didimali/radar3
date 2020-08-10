package radar.UI.Components;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import radar.Tools.LoadingData;
import radar.Tools.LoadingDataClass;

@SuppressWarnings({"rawtypes","unchecked"})
public class BackupPartsBox extends JComboBox implements LoadingData{
	
	private static final long serialVersionUID = 1L;
	private String ServiceImplName;
	private String methodName;
	/*
	 * 构造一个包含数据的下拉框
	 * @param ServiceImplName:下拉框获取数据将要调用的ServiceImpl类的名字
     * @param methodName:下拉框获取数据将要调用的ServiceImpl类中方法的名字
	 */
	
	public BackupPartsBox(String ServiceImplName,String methodName) {
		
		this.ServiceImplName = ServiceImplName;
		this.methodName = methodName;
		
		//样式设置
		setMaximumRowCount(20);
		setBackground(Color.WHITE);
		setRenderer(new TwoDecimalRenderer(getRenderer()));
		//加载数据
//		SwingWorkerForPartsBox swb = new SwingWorkerForPartsBox(this,ServiceImplName,methodName);
//		swb.execute();
		
		LoadingDataClass loading = new LoadingDataClass(this, ServiceImplName, methodName,null);
		loading.execute();
		String[] data = {};
		init(data);
	}

	//数据加载
	public void init(Object[] data) {
		DefaultComboBoxModel mode  = new DefaultComboBoxModel(data);
		setModel(mode);
	}

	@Override
	public void loadingData(Object d) {
		Object[] data = (Object[]) d;
		Object[] resultData = {};
		if(data!= null||data.length!=0) {
			resultData = new String[1+data.length];
			resultData[0] = "All";
			for(int i=0;i<data.length;i++) {
				resultData[i+1] =data[i];				
			}
		}
		init(resultData);
	}
}
