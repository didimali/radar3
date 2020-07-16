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

import radar.SwingWorker.SwingWorkerForComboBox;
import radar.SwingWorker.SwingWorkerForPartsBox;

public class BackupPartsBox extends JComboBox{
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
		setRenderer(new TwoDecimalRenderer1(getRenderer()));
		//加载数据
		SwingWorkerForPartsBox swb = new SwingWorkerForPartsBox(this,ServiceImplName,methodName);
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
class TwoDecimalRenderer4 extends DefaultListCellRenderer {
	private static final long serialVersionUID = -6518120547224228417L;
	private ListCellRenderer defaultRenderer;

	  public TwoDecimalRenderer4(ListCellRenderer defaultRenderer) {
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
