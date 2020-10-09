package radar.UI.AcuteForecast;

import radar.Tools.Init;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Components.TableWithScrollBar;

import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;

/**
 * 精准预测-器材筹措结果内容页面
 * @author madi
 *
 */
public class BBody2 extends JPanelTransparent implements Init{
	
	private static final long serialVersionUID = 1L;
	private TableWithScrollBar planTable;
	private JScrollPane planPanel;
	
	private Object[] params = {null};
	
	public BBody2(String typeid) {
		params[0] = typeid;
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", ""));		
		setPlan();
		setHistory();
		
	}

	private void setPlan() {
		
		String[] header = {"序号","备件名称","所需数量"};
		
		planTable = new TableWithScrollBar("AnalysisServiceImpl", "getRepairPlanContent", params, header, false, 0);
		planPanel = new JScrollPane(planTable);
		add(planPanel, "cell 0 0,grow");
		
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
