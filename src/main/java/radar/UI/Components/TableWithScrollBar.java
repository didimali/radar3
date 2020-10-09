package radar.UI.Components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import radar.Tools.LoadingData;
import radar.Tools.LoadingDataClass;
import radar.Tools.TableStyleUI;

/**
 * 带滚动条的表格控件
 * @author madi
 */

public class TableWithScrollBar extends JTable implements LoadingData{

	private static final long serialVersionUID = 1L;
	
	//表格加载的总的数据集
    private Object[][] resultData;
    private DefaultTableModel model = null;
    //空数据集（10行）
    private Object[][] nothing = { {}, {}, {}, {}, {}, {}, {}, {}, {}, {}};
	
    //表格将要调用的ServiceImpl类的名字
    private String className;
    private String methodName;
    private Object[] params;
    private boolean noFirstColumn = false;
    private String[] header;
    private int level = 0;
    
    /**
     * @param className :表格数据获取方法所在类的类名
     * @param methodName ：表格数据获取方法的方法名
     * @param params ：表格数据获取方法的参数
     * @param header ：表格表头
     * @param noFirstColumn 不显示第一列
     * @param level : 表格行高等级
     */
 	public TableWithScrollBar(String className,String methodName,Object[] params, String[] header,boolean noFirstColumn,int level) {
     	setBackground(Color.WHITE);
 		this.className = className;
 		this.methodName = methodName;
 		this.params = params;
 		this.header = header;
 		this.noFirstColumn = noFirstColumn;
 		this.level = 0;
 		initTable();
 	}

 	/**
 	 * 刷新表格数据 
 	 */
 	public void refreshTable(){
 		getTableDate();
 	}
 	
 	/**
 	 * 向数据库查询表格数据
 	 */
 	private void getTableDate() {
 		LoadingDataClass loading = new LoadingDataClass(this, className, methodName,params);
		loading.execute(); 		
 	}
 	
 	/**
 	 * 表格数据初始化加载
 	 */
	private void initTable() {
		getTableDate();
		// 如果结果集中没有数据，那么就用空来代替数据集中的每一行
        model = new DefaultTableModel(nothing, header);
        this.setModel(model);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, r);
        setStyle();
	}
	
	/**
	 * 表格样式设定
	 */
	public void setStyle() {
        TableStyleUI ui = new TableStyleUI();
        ui.makeFace(this,level);
        //隐藏第一列
        if(noFirstColumn)
        	hideLastColumn();
	}

	/**
	 * 设置表格第一列不可见（该列可用来存储各种查询条件）
	 */
	 private void hideLastColumn() {
		this.getColumnModel().getColumn(0).setMinWidth(0);
		this.getColumnModel().getColumn(0).setWidth(0);
		this.getColumnModel().getColumn(0).setMaxWidth(0);
		this.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

	public void init(Object[][] data,boolean mark) {
		if (data != null && data.length != 0 && mark) {
			resultData = initResultData(data);
		    model = new DefaultTableModel(getData(), header);
        } 
		else if(!mark && data.length != 0) {
			data = initResultData(data);
			model = new DefaultTableModel(data, header);
		}
		else             
            model = new DefaultTableModel(nothing, header);// 如果结果集中没有数据，那么就用空来代替数据集中的每一行
        setModel(model);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, r);
        setStyle();
	}
	
	/**
	 * 根据筛选条件筛选满足条件的数据集
	 * @param columnIndex 筛选列的列数
	 * @param value	筛选条件值
	 * @return
	 */
	private Object[][] getSelectedData(Object[][] data,int columnIndex,String value){
		if(columnIndex<0 || columnIndex >data[0].length)
			return data;
		List<Object[]> list = new ArrayList<Object[]>();
		for(int i=0;i<data.length;i++) {
			Object[] o = data[i];
			if(o == null) 
				break;
			if(o[columnIndex].equals(value)) 
				list.add(o);
		}
		int N = list.size();
		Object[][] result = new Object[N][];
		for(int i=0;i<list.size();i++) {
			Object[] o = list.get(i);
			if(o == null)
				break;
			if(noFirstColumn)
				o[1] = i+1;
			else
				o[0] = i+1;
			result[i] = o;
		}
		return result;		
	}
	
	/**
	 * 根据表格的列数及其相应的值筛选相应的表格——单一条件筛选
	 * @param columnIndex	筛选列的列数
	 * @param value		筛选条件值
	 */
	public void selectDataByColumnIndexAndValue(int columnIndex,String value) {
		Object[][] result = getSelectedData(resultData,columnIndex,value);		
		init(result,false);		
	}
	
	/**
	 * 根据表格的列数及其相应的值筛选相应的表格——双条件筛选
	 * @param columnIndex1	筛选列的列数1
	 * @param value1		筛选条件值1
	 * @param columnIndex2	筛选列的列数2
	 * @param value2		筛选条件值2
	 */
	public void selectDataByColumnIndexsAndValues(int columnIndex1,String value1,int columnIndex2,String value2) {
		Object[][] result = null;	
		result = getSelectedData(resultData,columnIndex1,value1);
		result = getSelectedData(result,columnIndex2,value2);
		init(result,false);
	}

	//返回表格数据集
	private Object[][] getData() {
		return resultData;
	}

	//将数据库查询结果装载入表格数据集
	private Object[][] initResultData(Object[][] data) {
		int N = data.length;
		N = N%10 >0 ? 10*(N/10+1):N;
		Object[][] result = new Object[N][];
		for(int i=0;i<data.length;i++) {
			if(data[i] == null)
				break;
			if(noFirstColumn)
				data[i][1] = i+1;
			else
				data[i][0] = i+1;
			result[i] = data[i];
		}
		return result;
	}

	@Override
	public void loadingData(Object data) {
		init((Object[][])data,true);
	}
}
