package radar.UI.Components;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import radar.Tools.SwingWorkerForComboBox;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

@SuppressWarnings({"rawtypes","unchecked"})
public class ComboBox extends JComboBox{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6294605107896516557L;
	private String className;
	private String methodName;
	private Object[] params;
	
	/**
	 * 生成一个装载数据的下拉框
	 * @param className 获取下拉框数据方法所在类的名字
	 * @param methodName 获取下拉框数据方法的方法名
	 * @param params 获取下拉框数据方法所需的参数，统一放在Object[]中，在方法中挨个读取
	 */
	
	public ComboBox(String className,String methodName, Object[] params) {
		
		this.className = className;
		this.methodName = methodName;
		this.params = params;
		
		//样式设置
		setMaximumRowCount(20);
		setBackground(Color.WHITE);
		setRenderer(new TwoDecimalRenderer(getRenderer()));
		//加载数据
		SwingWorkerForComboBox swb = new SwingWorkerForComboBox(this,className,methodName,params);
		swb.execute();
		String[] data = {};
		init(data);
	}

	//数据加载
	public void init(Object[] data) {
		DefaultComboBoxModel mode  = new DefaultComboBoxModel(data);
		setModel(mode);
	}
}
@SuppressWarnings({"rawtypes","unchecked"})
class TwoDecimalRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = -6518120547224228417L;
	private ListCellRenderer defaultRenderer;

	  public TwoDecimalRenderer(ListCellRenderer defaultRenderer) {
	    this.defaultRenderer = defaultRenderer;
	  }

	  @Override
	  public Component getListCellRendererComponent(JList list, Object value,
	      int index, boolean isSelected, boolean cellHasFocus) {
	    Component c = defaultRenderer.getListCellRendererComponent(list, value,
	        index, isSelected, cellHasFocus);
	    c.setFont(new Font("仿宋", Font.PLAIN, 13));
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
