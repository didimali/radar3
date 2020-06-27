package radar.UI.Components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import radar.Tools.SwingWorkerForTable1;
import radar.Tools.TableStyleUI;

/**
 * 不分页的表格
 * @author madi
 *
 */
public class Table1 extends JTable{

	private static final long serialVersionUID = 1L;
	
	//表格加载的数据
    private Object[][] resultData;
    private DefaultTableModel model = null;
	
    //表格将要调用的ServiceImpl类的名字
    private String className;
    private String methodName;
    private Object[] params;
    private boolean noFirstColumn = false;
    private String[] header;
    private int level = 0;
    
    /**
     * 
     * @param className :表格数据获取方法所在类的类名
     * @param methodName ：表格数据获取方法的方法名
     * @param params ：表格数据获取方法的参数
     * @param header ：表格表头
     * @param noFirstColumn 不显示第一列
     * @param level : 表格行高等级
     */
 	public Table1(String className,String methodName,Object[] params, String[] header,boolean noFirstColumn,int level) {
     	setBackground(Color.WHITE);
 		this.className = className;
 		this.methodName = methodName;
 		this.params = params;
 		this.header = header;
 		this.noFirstColumn = noFirstColumn;
 		this.level = 0;
 		initTable();
 	}

	private void initTable() {
		SwingWorkerForTable1 swt = new SwingWorkerForTable1(this, className,methodName,params);
		swt.execute();
		// 如果结果集中没有数据，那么就用空来代替数据集中的每一行
        Object[][] nothing = { {}, {}, {}, {}, {}, {}, {}, {}, {}, {}};
        model = new DefaultTableModel(nothing, header);
        this.setModel(model);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, r);
        setStyle();
	}
	
	public void setStyle() {
		//设置表格样式
        TableStyleUI ui = new TableStyleUI();
        ui.makeFace(this,level);
        if(noFirstColumn)
        	hideLastColumn();
	}

	 private void hideLastColumn() {
	    	//设置表格第一列不可见（该列存储雷达的radarId）
			this.getColumnModel().getColumn(0).setMinWidth(0);
			this.getColumnModel().getColumn(0).setWidth(0);
			this.getColumnModel().getColumn(0).setMaxWidth(0);
			this.getColumnModel().getColumn(0).setPreferredWidth(0);
	    }

	public void init(Object[][] data) {
		if (data != null && data.length != 0 ) {
			initResultData(data);
		     model = new DefaultTableModel(getData(), header);
        } 
		else {
            // 如果结果集中没有数据，那么就用空来代替数据集中的每一行
            Object[][] nothing = { {}, {}, {}, {}, {}, {}, {}, {}, {}, {}};
            model = new DefaultTableModel(nothing, header);
        }
        setModel(model);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, r);
        setStyle();
	}
	/**
	 * 根据表格的列数及其相应的值筛选相应的表格
	 * @param columnIndex	筛选列的列数
	 * @param value		筛选条件值
	 */
	public void selectDataByColumnIndexAndValue(int columnIndex,String value) {
		//恢复至初始表格（或不筛选）
		if(columnIndex == -1) {
			model = new DefaultTableModel(resultData, header);
			setModel(model);
	        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
	        r.setHorizontalAlignment(JLabel.CENTER);
	        setDefaultRenderer(Object.class, r);
	        setStyle();
	        return;
		}
		List<Object[]> tmp = new ArrayList<Object[]>();
		for(int i=0;i<resultData.length;i++) {
			Object[] o = resultData[i];
			if(o == null) 
				break;
			if(o[columnIndex].equals(value))
				tmp.add(o);
		}
		
		int N = tmp.size();
		int l = N%10;
		int k = N/10;
		if(l>0)
			N = 10*(k+1);
		Object[][] rData = new Object[N+2][];
		for(int j=0;j<tmp.size();j++) {
			Object[] o = tmp.get(j);
			if(o == null)
				break;
			o[1] = j+1;
			rData[j] = o;
		}
		if(tmp.size() == 0) {
			Object[][] nothing = { {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}};
            model = new DefaultTableModel(nothing, header);
		}
		else
			model = new DefaultTableModel(rData, header);
		setModel(model);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, r);
        setStyle();
		
	}

	private Object[][] getData() {
		return resultData;
	}

	private void initResultData(Object[][] data) {
		int N = data.length;
		int l = N%10;
		int k = N/10;
		if(l>0)
			N = 10*(k+1);
		resultData = new Object[N+2][];
		for(int i=0;i<data.length;i++) {
			resultData[i] = data[i];
		}
	}

}
