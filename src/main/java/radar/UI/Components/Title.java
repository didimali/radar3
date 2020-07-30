package radar.UI.Components;

import net.miginfocom.swing.MigLayout;
import radar.Tools.LoadingData;
import radar.Tools.LoadingDataClass;
import java.awt.Color;
import javax.swing.JLabel;

import java.awt.Font;

/**
 * 标题
 * @author madi
 *
 */
public class Title extends JPanelTransparent implements LoadingData{
	
	private static final long serialVersionUID = 1L;
	//表格将要调用的ServiceImpl类的名字
    private String className;
    private String methodName;
    private Object[] params;
    
    private JLabel repairPlanDate;

	public Title(String className,String methodName,Object[] params) {
		setBackground(Color.WHITE);
		this.className = className;
		this.methodName = methodName;
		this.params = params;
		setLayout(new MigLayout("", "[][]", "[100%]"));
		
		init();		
	}

	private void init() {
		JLabel lblNewLabel = new JLabel("建议维修时间：");
		lblNewLabel.setFont(new Font("仿宋", Font.PLAIN, 16));
		add(lblNewLabel, "cell 0 0,growx,aligny center");
		
		repairPlanDate = new JLabel("");
		
		LoadingDataClass loading = new LoadingDataClass(this, className, methodName,params);
		loading.execute();
		
		repairPlanDate.setFont(new Font("仿宋", Font.PLAIN, 16));
		add(repairPlanDate, "cell 1 0,growx,aligny center");
	}
	

	public JLabel getRepairPlanDate() {
		return repairPlanDate;
	}
	
	@Override
	public void loadingData(Object data) {
		repairPlanDate.setText((String) data);
	}
}
