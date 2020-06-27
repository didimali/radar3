package radar.UI.AcuteForecast;

import javax.swing.JPanel;

import radar.Tools.Init;
import radar.UI.Components.PieChart;
import radar.UI.Components.Table;
import radar.UI.Components.Table1;
import radar.UI.Components.Title;

import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * 精准预测-器材筹措结果内容页面
 * @author madi
 *
 */
public class CBody5 extends JPanel implements Init{
	
	private static final long serialVersionUID = 1L;
	private JTable planTable;
	private JScrollPane planPanel;
	
	private Object[] params = {null};
	private String titleText;
	private JPanel title;
	
	public CBody5(String radarName,int radarId) {
		titleText = radarName;
		params[0] = radarId;
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[8%,grow][92%]"));
		
		setPlan();
		setHistory();
		
	}

	private void setPlan() {
		
		String[] header = {"序号","备件名称","所需数量"};
		
		title = new Title("AcuteForecastServiceImpl", "getDataForRadarRepairPlanDate", params);
		add(title, "cell 0 0,grow");
		planTable = new Table1("AcuteForecastServiceImpl", "getDataForRadarRepairPlanContent", params, header, false, 0);
		
		planPanel = new JScrollPane(planTable);
		add(planPanel, "cell 0 1,grow");
		
	}

	private void setHistory() {
	}

	

	@Override
	public void initUI() {
		
	}

	@Override
	public void Action() {
		
	}

}
