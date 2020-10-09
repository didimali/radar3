package radar.UI.Settings;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.Parts;
import radar.Entity.RadarType;
import radar.ServiceImpl.RadarServiceImpl;
import radar.SwingWorker.SwingWorkerForParts;
import radar.UI.Components.ComboBox;
import radar.UI.Components.JPanelTransparent;

public class AddPartsBody extends JPanelTransparent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel JPanel;
	private JPanel jPanel1;
	private JLabel partsName;
	private JLabel partsNum;
	private JLabel radarType;
	private ComboBox rType;
	private JTextField pNum;
	private JTextField pName;
	private JButton submit;
	public AddPartsBody() {
		setLayout(new MigLayout("", "[grow]", "[80%][20%]"));
		initUI();
		Action();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		JPanel=new JPanelTransparent();
		JPanel.setLayout(new MigLayout("", "[10%][grow][10px][grow][grow][10%]", "[10%][grow][grow][][grow][grow][grow][10%]"));
		add(JPanel, "cell 0 0,grow");
		partsName = new JLabel("备件名称：");
		partsName.setToolTipText("");
		partsName.setFont(new Font("仿宋", Font.PLAIN, 16));
		JPanel.add(partsName, "cell 1 1,alignx center");

		
		pName = new JTextField();
		pName.setToolTipText("输入备件名称");
		pName.setFont(new Font("仿宋", Font.PLAIN, 16));
		pName.setColumns(10);
		JPanel.add(pName, "cell 3 1,growx");
	

		partsNum = new JLabel("备件数量：");
		partsNum.setToolTipText("");
		partsNum.setFont(new Font("仿宋", Font.PLAIN, 16));
		JPanel.add(partsNum, "cell 1 2,alignx center");
		
		pNum = new JTextField();
		pNum.setToolTipText("输入备件数量");
		pNum.setFont(new Font("仿宋", Font.PLAIN, 16));
		pNum.setColumns(10);
		JPanel.add(pNum, "cell 3 2,growx");
		
		
		radarType = new JLabel("雷达型号：");
		radarType.setToolTipText("");
		radarType.setFont(new Font("仿宋", Font.PLAIN, 16));
		JPanel.add(radarType, "cell 1 3,alignx center");

		rType = new ComboBox("AcuteForecastServiceImpl", "getRadarType", null);
		rType.setFont(new Font("仿宋", Font.PLAIN, 15));
		JPanel.add(rType, "cell 3 3,growx");
	
		
		jPanel1=new JPanelTransparent();
		jPanel1.setLayout(new MigLayout("", "[8px][grow][8px]", "[8px][][grow][8px]"));
		add(jPanel1, "cell 0 1,grow");
		submit = new JButton("添加");
		submit.setFont(new Font("仿宋", Font.PLAIN, 16));
		jPanel1.add(submit, "cell 1 1,alignx center");

	}
	private void Action() {
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					Parts p = new Parts();
					String partName = pName.getText();
					if(partName==""||partName.equals("")) {
						JOptionPane.showMessageDialog(null, "请输入备件名称", "提示",JOptionPane.WARNING_MESSAGE);  
						return;
					}
					p.setPartsName(partName);
					
					String partCount = pNum.getText();
					Integer.valueOf(partCount);
					if(partCount==""||partCount.equals("")) {
						JOptionPane.showMessageDialog(null, "请输入备件数量", "提示",JOptionPane.WARNING_MESSAGE);  
						return;
					}
					p.setPartsCount(Integer.valueOf(partCount));
					
					String radarType = rType.getSelectedItem().toString();
					if(radarType=="All"||radarType.equals("All")) {
						JOptionPane.showMessageDialog(null, "请选择雷达型号", "提示",JOptionPane.WARNING_MESSAGE);  
						return;
					}else{
						RadarServiceImpl radarServiceImpl = (RadarServiceImpl) SpringUtil.getBean("RadarServiceImpl");

						List<RadarType> rType =radarServiceImpl.selectRadarType(radarType);
						
						p.setRadarTypeId(rType.get(0));
					}
					
					SwingWorkerForParts s = new SwingWorkerForParts();
					s.setParts(p);
					s.execute();
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});		
	}
}
