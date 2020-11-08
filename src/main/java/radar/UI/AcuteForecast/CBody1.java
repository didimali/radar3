package radar.UI.AcuteForecast;

import javax.swing.JScrollPane;
import radar.UI.Components.TableWithScrollBar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 精准测评-内容一
 * @author madi
 *
 */
public class CBody1 extends JScrollPane {

	private static final long serialVersionUID = 1L;
	
//	private Table1 table;
	
	private TableWithScrollBar table;

	public CBody1() {
		
		String[] header = {"managerId","序号","部队","驻地类型","雷达型号","列装数量（台）","红（台）","黄（台）","绿（台）","备件消耗（件）"};
//		table = new Table1("AcuteForecastServiceImpl", "getAcuteForecastTable1Data", null, header,true,0);
		table = new TableWithScrollBar("AcuteForecastServiceImpl", "getAcuteForecastTable1Data", null, header,true,0);
		setViewportView(table);
	}

	public TableWithScrollBar getTable() {
		return table;
	}
	
	public void Action() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
	
	public void refresh() {
		table.refreshTable();
	}
	
}
