package radar.UI.Settings;

import javax.swing.JScrollPane;

import radar.UI.Components.Table1;

/**
 * 备件消耗列表
 * @author sgh
 *
 */
public class ConsumeList extends JScrollPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Table1 table;
	public ConsumeList() {
		String[] header = { "序号", "备件名称", "备件消耗数量","消耗时间","部队名称"};
		table = new Table1("PartsServiceImpl", "getPartsConsume",null,header,false,0);
		table.setToolTipText("查看详情");
		setViewportView(table);
	}
public Table1 getTable() {
	return table;
}
}
