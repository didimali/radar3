package radar.UI.Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import radar.Tools.LoadingData;
import radar.Tools.LoadingDataClass;


@SuppressWarnings({ "serial", "rawtypes","unused","unchecked" })
public class XiTongComBox extends JComboBox implements LoadingData{
	private String ServiceImplName;
	private String methodName;
	/*
	 * 构造一个包含数据的下拉框
	 * @param ServiceImplName:下拉框获取数据将要调用的ServiceImpl类的名字
     * @param methodName:下拉框获取数据将要调用的ServiceImpl类中方法的名字
	 */
	
	public XiTongComBox(String ServiceImplName,String methodName) {
		
		this.ServiceImplName = ServiceImplName;
		this.methodName = methodName;
		
		//样式设置
		setMaximumRowCount(20);
		setBackground(Color.WHITE);
		setRenderer(new TwoDecimalRenderer(getRenderer()));
		//加载数据
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
		if(data!= null|| data.length!=0) {
			resultData = new String[1+data.length];
			resultData[0] = "All";
			for(int i=0;i<data.length;i++) {
				resultData[i+1] =data[i];				
			}
		}
		init(resultData);
	}
}
