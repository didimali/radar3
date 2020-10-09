package radar.UI.Settings;

import javax.swing.JScrollPane;
import radar.UI.Components.TableWithScrollBar;

/**
 * 故障列表
 * @author sgh
 *
 */
public class FaultList extends JScrollPane{

	private static final long serialVersionUID = -6393699527743778258L;
	private TableWithScrollBar table;
	public FaultList() {
		String[] header = {"faultId","序号","雷达","故障类型","故障原因","故障时间"};
		table = new TableWithScrollBar("BasicInfoSettingServiceImpl", "getFaultRecord", null, header,true,0);
		table.setToolTipText("查看详情");
		setViewportView(table);
	}
	public TableWithScrollBar getTable() {
		return table;
	}
}
