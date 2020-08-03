package radar.UI.Settings;

import javax.swing.JScrollPane;

import radar.UI.Components.Table1;
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
	private Table1 table;
		public PartsList() {
			String[] header = { "序号", "备件名称", "雷达型号","备件数量"};
			table = new Table1("PartsServiceImpl", "getPartsType",null,header,false,0);
			table.setToolTipText("查看详情");
			setViewportView(table);
		}
	public Table1 getTable() {
		return table;
	}
}
