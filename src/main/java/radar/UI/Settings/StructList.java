package radar.UI.Settings;

import javax.swing.JScrollPane;
import radar.UI.Components.TableWithScrollBar;
/**
 * 备件列表
 * @author sgh
 *
 */
public class StructList extends JScrollPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TableWithScrollBar table;
	public StructList() {
		String[] header = {"序号","雷达型号","雷达子系统","子系统下属部件"};
		table = new TableWithScrollBar("AcuteForecastServiceImpl", "getDataForRadarStructTable",null, header, false, 0);
		table.setToolTipText("查看详情");
		setViewportView(table);
	}
	public TableWithScrollBar getTable() {
		return table;
	}
}
