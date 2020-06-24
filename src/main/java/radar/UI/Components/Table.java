package radar.UI.Components;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import radar.Tools.SwingWorkerForTable;
import radar.Tools.TableStyleUI;
import java.awt.Color;

public class Table extends JTable {
	
	private static final long serialVersionUID = 1L;
	// JTable表分页信息相关变量
    public int currentPage = 1;
    private int pageCount = 10;
    private int totalPage = 0;
    private int totalRowCount = 0;
    private int column = 0;
    private int restCount;
    //表格加载的数据
    private Object[][] resultData;
    private DefaultTableModel model = null;
    
    //表格将要调用的ServiceImpl类的名字
    private String className;
    private String methodName;
    private Object[] params;
    public  String[] header;
	
   /**
    * 
    * @param className :表格数据获取方法所在类的类名
    * @param methodName ：表格数据获取方法的方法名
    * @param params ：表格数据获取方法的参数
    * @param header ：表格表头
    */
	public Table(String className,String methodName,Object[] params, String[] header) {
    	setBackground(Color.WHITE);
		this.className = className;
		this.methodName = methodName;
		this.params = params;
		this.header = header;
		initTable();
	}

	private void initTable() {
		SwingWorkerForTable swt = new SwingWorkerForTable(this, className,methodName,params);
		swt.execute();
		// 如果结果集中没有数据，那么就用空来代替数据集中的每一行
        Object[][] nothing = { {}, {}, {}, {}, {}, {}, {}, {}, {}, {},{}, {}, {}, {}, {}, {}};
        model = new DefaultTableModel(nothing, header);
        totalRowCount = 0;
        this.setModel(model);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, r);
        setStyle();
	}
	
	public void init(Object[][] data) {
		if (data != null && data.length != 0 ) {
            initResultData(data);
            model = new DefaultTableModel(getPageData(), header);
        } 
		else {
            // 如果结果集中没有数据，那么就用空来代替数据集中的每一行
            Object[][] nothing = { {}, {}, {}, {}, {}, {}, {}, {}, {}, {}};
            model = new DefaultTableModel(nothing, header);
            totalRowCount = 0;
        }
        setModel(model);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, r);
        setStyle();
	}
	
	public void setStyle() {
		//设置表格样式
        TableStyleUI ui = new TableStyleUI();
        ui.makeFace(this);
	}
	
	/**
     * 获取下一页
     */
    public int getNextPage() {
        if (this.currentPage != this.totalPage) {
            return ++currentPage;
        }
        return -1;
    }

    /**
     * 获取上一页
     */
    public int getPreviousPage() {
        if (this.currentPage != 1) {
            return --currentPage;
        }
        return -1;
    }

    /**
     * 获取最后一页
     */
    public int getLastPage() {
    	currentPage = totalPage;
        return this.totalPage;
    }

    /**
     * 获取第一页
     */
    public int getFirstPage() {
    	currentPage = 1;
        return 1;
    }

    /**
     * 获取总页数
     */
    public int getTotolPage() {
        return this.totalPage;
    }

    /**
     * 获取当前页
     */
    public int getCurrentPage() {
        return this.currentPage;
    }
    /**
     * 获取分页数据
     * 
     * @return
     */
    public Object[][] getPageData() {
        Object[][] currentPageData = new Object[pageCount][column];// 构造每页数据集
        if (this.getCurrentPage() < this.totalPage) {// 如果当前页数小于总页数，那么每页数目应该是规定的数pageCount
            for (int i = pageCount * (this.getCurrentPage() - 1); i < pageCount
                    * (this.getCurrentPage() - 1) + pageCount; i++) {
                for (int j = 0; j < column; j++) {
                    // 把结果集中对应每页的每一行数据全部赋值给当前页的每一行的每一列
                    currentPageData[i % pageCount][j] = resultData[i][j];
                }
            }
        } else {
            // 在动态改变数据结果集的时候，如果当前页没有数据了，则回到前一页（一般针对最后一页而言）
            if (pageCount * (this.getCurrentPage() - 1) >= totalRowCount)
                this.currentPage--;
            for (int i = pageCount * (this.getCurrentPage() - 1); i < pageCount
                    * (this.getCurrentPage() - 1) + restCount; i++) {
                for (int j = 0; j < column; j++) {
                    currentPageData[i % pageCount][j] = resultData[i][j];
                }
            }
        }
        return currentPageData;
    }
    public void initResultData(Object[][] data) {
        if (data != null) {
            resultData = data;// 总的结果集
            column = data[0].length;// 表的列数
            totalRowCount = data.length;// 表的长度
            currentPage = 1;
            totalPage = totalRowCount % pageCount == 0 ? totalRowCount
                    / pageCount : totalRowCount / pageCount + 1;// 结果集的总页数
            restCount = totalRowCount % pageCount == 0 ? 10 : totalRowCount
                    % pageCount;// 最后一页的数据数
        }
    }

}
