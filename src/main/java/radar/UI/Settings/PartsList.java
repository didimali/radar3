package radar.UI.Settings;

import javax.swing.JScrollPane;
import radar.UI.Components.TableWithScrollBar;
/**
 * 备件列表
 * @author sgh
 *
 */
public class PartsList extends JScrollPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TableWithScrollBar table;
	public PartsList() {
		String[] header = { "序号", "备件名称", "雷达型号","备件数量"};
		table = new TableWithScrollBar("PartsServiceImpl", "getPartsType",null,header,false,0);
		table.setToolTipText("查看详情");
		setViewportView(table);
	}
	public TableWithScrollBar getTable() {
		return table;
	}
}
