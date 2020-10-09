package radar.UI.Settings;

import javax.swing.JScrollPane;
import radar.UI.Components.TableWithScrollBar;

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
	private TableWithScrollBar table;
	public ConsumeList() {
		String[] header = { "序号", "备件名称", "备件消耗数量","消耗时间","部队名称"};
		table = new TableWithScrollBar("PartsServiceImpl", "getPartsConsume",null,header,false,0);
		table.setToolTipText("查看详情");
		setViewportView(table);
	}
	public TableWithScrollBar getTable() {
		return table;
	}
}
