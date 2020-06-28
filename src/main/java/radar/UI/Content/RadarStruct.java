package radar.UI.Content;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import radar.UI.Components.ComboBox;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Components.Table1;

import java.awt.BorderLayout;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * 雷达组织结构展示面板
 * @author madi
 *
 */
public class RadarStruct extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JLabel title;
	private JLabel label1;
	private JComboBox radarType;
	private JPanel panel_1;
	private JSeparator separator;
	private JScrollPane scrollPane;
	private Table1 table;
	
	private ContentPanel2 panel2;
	
	public RadarStruct() {
		setLayout(new MigLayout("", "[8px][100%][8px]", "[8px][100%][8px]"));		
		
		panel2 = new ContentPanel2();
		add(panel2, "cell 1 1,grow");
		setHead();
		setBody();
	}

	private void setHead() {
		
		panel2.contentTop.setLayout(new MigLayout("", "[grow]", "[grow][]"));
		
		panel = new JPanelTransparent();
		panel2.contentTop.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][20%][][][grow][]", "[grow]"));
		
		title = new JLabel("雷达组织结构");
		title.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(title, "cell 0 0,growx,aligny center");
		
		label1 = new JLabel("雷达型号");
		label1.setFont(new Font("仿宋", Font.PLAIN, 18));
		panel.add(label1, "cell 2 0,growx,aligny center");
		
		radarType = new ComboBox("AcuteForecastServiceImpl", "getRadarType", null);
		radarType.setFont(new Font("仿宋", Font.PLAIN, 14));
		radarType.setMaximumRowCount(4);	
		panel.add(radarType, "cell 3 0,growx,aligny center");
		
		radarType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					String value = (String) e.getItem();
					if(value.equals("All"))
						table.selectDataByColumnIndexAndValue(-1,value);
					else
						table.selectDataByColumnIndexAndValue(1,value);
				}
			}
		});
		
		panel_1 = new JPanel();
		panel2.contentTop.add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		panel_1.add(separator, BorderLayout.CENTER);
		
	}

	private void setBody() {		
		
		
		
		String[] header = {"序号","雷达型号","雷达子系统","子系统下属部件"};
		Object[] params = null;
		table = new Table1("AcuteForecastServiceImpl", "getDataForRadarStructTable", params, header, false, 0);
		
		scrollPane = new JScrollPane(table);
		
		panel2.contentBody.add(scrollPane, BorderLayout.CENTER);
		
	}

}
