package radar.UI.Settings;

import javax.swing.JScrollPane;

import radar.UI.Components.TableWithScrollBar;

public class UserList extends JScrollPane{
	private static final long serialVersionUID = 1L;
	private TableWithScrollBar table;
	public UserList() {
		String[] header = { "序号", "用户名", "密码"};
		table = new TableWithScrollBar("UserServiceImpl", "getUsers",null,header,false,0);
		table.setToolTipText("查看详情");
		setViewportView(table);
	}
	public TableWithScrollBar getTable() {
		return table;
	}
}
