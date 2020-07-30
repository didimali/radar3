package radar.UI.AcuteForecast;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import radar.UI.Components.Table;
import radar.UI.Components.Table1;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 精准测评-内容一
 * @author madi
 *
 */
public class CBody1 extends JScrollPane {

	private static final long serialVersionUID = 1L;
	
	private Table1 table;

	public CBody1() {
		
		String[] header = {"managerId","序号","部队","驻地类型","雷达型号","列装数量（台）","红（台）","黄（台）","绿（台）","备件消耗（件）"};
		table = new Table1("AcuteForecastServiceImpl", "getAcuteForecastTable1Data", null, header,true,0);
		table.setToolTipText("查看详情");
		setViewportView(table);

	}

	public Table1 getTable() {
		return table;
	}
	
	public void Action() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
}
