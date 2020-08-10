package radar.UI.Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingWorker;

import radar.Tools.LoadingData;
import radar.Tools.LoadingDataClass;

/**
 * 下拉框1
 * @author madi
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class ComboBox1 extends JComboBox implements LoadingData{

	private static final long serialVersionUID = 1L;
	
	private String className;
	private String methodName;
	private Object[] params;
	
	public DefaultComboBoxModel mode;
	public String[] data = {};
	public Object[][] resultData;
	
	/**
	 * 下拉框
	 * @param className 	下拉框数据来源方法所在类的类名
	 * @param methodName 	下拉框数据来源方法方法名 
	 * @param params		数据源方法形参
	 */
	public ComboBox1(String className,String methodName,Object[] params) {
		this.className = className;
		this.methodName = methodName;
		this.params = params;
	}
	
	public void init() {
		initComboBox(data);
	}	
	
	private void initComboBox(String[] data) {		
		LoadingDataClass s = new LoadingDataClass(this, className, methodName,params);
		s.execute();
		mode = new DefaultComboBoxModel(data);
		setModel(mode);
		//样式设置
		setRenderer(new TwoDecimalRenderer1(getRenderer()));
	}


	@Override
	public void loadingData(Object d) {
		Object[][] data = (Object[][]) d;
		resultData = data;
		this.data = new String[data.length];
		for(int i=0;i<data.length;i++) {
			this.data[i] = (String) data[i][1];
		}
		mode = new DefaultComboBoxModel(this.data);
		setModel(mode);
		//样式设置
		setRenderer(new TwoDecimalRenderer1(getRenderer()));		
	}
}

@SuppressWarnings({"rawtypes","unchecked"})
class TwoDecimalRenderer1 extends DefaultListCellRenderer {
	private static final long serialVersionUID = -6518120547224228417L;
	private ListCellRenderer defaultRenderer;

	  public TwoDecimalRenderer1(ListCellRenderer defaultRenderer) {
	    this.defaultRenderer = defaultRenderer;
	  }

	  @Override
	  public Component getListCellRendererComponent(JList list, Object value,
	      int index, boolean isSelected, boolean cellHasFocus) {
	    Component c = defaultRenderer.getListCellRendererComponent(list, value,
	        index, isSelected, cellHasFocus);
	    c.setFont(new Font("仿宋", Font.PLAIN, 15));
	    if (c instanceof JLabel) {
	    	
	      if (isSelected) {
	        c.setBackground(new Color(135,206,250));
	        c.setForeground(new Color(255,0,0));
	      } else {
	        c.setBackground(Color.WHITE);
	      }
	    } else {
	      c.setBackground(Color.red);
	      c = super.getListCellRendererComponent(list, value, index, isSelected,
	          cellHasFocus);
	    }
	    return c;
	  }
}


