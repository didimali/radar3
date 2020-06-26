package radar.UI.Content;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.Manager;
import radar.ServiceImpl.ManagerServiceImpl;
import radar.ServiceImpl.RadarServiceImpl;
import radar.SwingWorker.SwingWorkerForNewManager;
import radar.Tools.Init;
import radar.UI.Components.BarChart;
import radar.UI.Components.Button;
import radar.UI.Components.ComboBox;
import radar.UI.Components.LineChart;
import radar.UI.Components.ManagerCombox;
import radar.UI.Components.PieChart;
import radar.UI.Components.Table;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewManager extends ContentPanel implements Init{
	private JLabel ManagerInfo;
	private JLabel managerName;
	private JComboBox managerNameComboBox;
	private JLabel locationType;
	private JComboBox locationTypeComboBox;
	private JButton add;
	private JButton update;
	private JButton delete;
	private JSeparator managerSeparator;

	public NewManager() {
		initUI();
		Action();
		
	}
	
	@Override
	public void initUI() {
		initContentTop();
		contentTop.add(ManagerInfo, "cell 0 1");
		contentTop.add(managerSeparator, "cell 0 2,growx");
		
		initContentBody();
		ContentBody.add(managerName, "cell 1 1,alignx trailing");
		ContentBody.add(managerNameComboBox, "cell 2 1,growx");
		ContentBody.add(locationType, "cell 1 2,alignx trailing");
		ContentBody.add(locationTypeComboBox, "cell 2 2,growx");
		initContentFoot();
		contentFoot.add(add, "cell 1 1,alignx center,aligny center");
		contentFoot.add(update, "cell 3 1,alignx center,aligny center");
		contentFoot.add(delete, "cell 5 1,alignx center,aligny center");




	}	
	/**
	 * 添加内容面板头部
	 */
	public void initContentTop() {
//		contentTop.setLayout(new MigLayout("", "[grow]", "[grow]"));	
		//将需要传递的参数按顺序放到Object[]中，然后在ServiceImpl方法中挨个读出来就可以了
//		Object[] params = {"pigan","皮干",1};
//		comboBox = new ComboBox("TestServiceImpl", "getRadars",params);
		contentTop.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
		ManagerInfo = new JLabel("部队信息设置");
		ManagerInfo.setFont(new Font("宋体", Font.PLAIN, 14));
		ManagerInfo.setHorizontalAlignment(SwingConstants.CENTER);
		managerSeparator = new JSeparator();
		managerSeparator.setForeground(Color.GRAY);
	}

	/**
	 * 添加内容面板躯干
	 */
	public void initContentBody() {		
//		ContentBody.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow]"));
//		
//		pie = new PieChart("饼图标题","TestServiceImpl", "getPieData",new Object[] {"qq"});
//		pie.init();		
//		line = new LineChart("折线图标题","x轴","y轴","TestServiceImpl", "getLineData",new Object[] {"qq",1});
//		line.init();		
//		bar = new BarChart("柱状图标题","x轴","y轴","TestServiceImpl", "getBarData",new Object[] {1,2,3});
//		bar.init();	
//		String[] header = { "序号", "部队编号", "所在位置"};
//		table = new Table("TestServiceImpl", "getManagers",new Object[] {"asd",1},header);
//		panel_2 = new JScrollPane(table);		
//		panel_2.setBackground(Color.WHITE);
//		panel_2.setOpaque(true);
		ContentBody.setLayout(new MigLayout("", "[25%][10%][40%,grow][25%]", "[grow][grow][][grow][grow]"));
		managerName = new JLabel("部队编号：");
		managerName.setFont(new Font("宋体", Font.PLAIN, 14));
		managerNameComboBox = new ManagerCombox("ManagerServiceImpl", "getDataForManagerComboBox");
		managerNameComboBox.setEditable(true);
		
		locationType = new JLabel("驻地类型：");
		locationType.setFont(new Font("宋体", Font.PLAIN, 14));
		String[] locationTypes = { "", "高原","山地","平原","沿海","沙漠"};
		locationTypeComboBox = new JComboBox();
		locationTypeComboBox.setModel(new DefaultComboBoxModel(locationTypes));
	}
	
	/**
	 * 添加内容面板底部
	 */
	public void initContentFoot() {
//		contentFoot.setLayout(new MigLayout("", "[10%][grow][10][grow][10][grow][10][grow][10%]", "[10%][80%][10%]"));
//		firstPage = new Button("首 页");
//		previousPage = new Button("上 一 页");
//		nextPage = new Button("下 一 页");
//		lastPage = new Button("尾 页");
	contentFoot.setLayout(new MigLayout("", "[10%][grow][10%][grow][10%][grow][10%]", "[10%][grow][10%]"));
	
	add = new JButton("新建");
	
	add.setFont(new Font("宋体", Font.PLAIN, 12));
	
	update = new JButton("修改");

	update.setFont(new Font("宋体", Font.PLAIN, 12));
	
	delete = new JButton("删除");
	
	delete.setFont(new Font("宋体", Font.PLAIN, 12));

		
	}
	/**
	 * 添加页面组件事件
	 */
	public void Action() {
		//部队下拉框事件（更新驻地类型下拉框数据）
//		managerNameComboBox.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//				locationTypeComboBox.initManagerLoaction(managerNameComboBox.getSelectedItem().toString());
//				}
//				catch(Exception execption) {
//					execption.printStackTrace();
				
//				}
//				if(managerNameComboBox.getSelectedItem().toString().equals("")) {
//					String[] locationTypes = { "", "高原","山地","平原","沿海","沙漠"};
//					locationTypeComboBox.setModel(new DefaultComboBoxModel(locationTypes));
//				}else if(!managerNameComboBox.getSelectedItem().toString().equals("")) {
//					SpringUtil s = new SpringUtil();
//					ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) s.getBean("ManagerServiceImpl"); 
//				
//				}
//			}
//		});
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SpringUtil s = new SpringUtil();
				ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) s.getBean("ManagerServiceImpl");
				Object[] obj = managerServiceImpl.getDataForManagerComboBox();
				Boolean flag = true;
				for(int i = 0; i < obj.length; i++){
					if(managerNameComboBox.getSelectedItem().toString().equals(obj[i].toString())) {
						flag= false;
						JOptionPane.showMessageDialog(null,"该部队已经存在","提示",JOptionPane.WARNING_MESSAGE);					
						break;
					}
					}
				if(flag) {
				try {
					Manager m = new Manager();
		            m.setManagerStatus(0);
					m.setManagerName(managerNameComboBox.getSelectedItem().toString());
					String locationName ="";
					if(locationTypeComboBox.getSelectedItem().toString().equals("高原")) {
						locationName="0";
					}else if(locationTypeComboBox.getSelectedItem().toString().equals("山地")) {
						locationName="1";

					}else if(locationTypeComboBox.getSelectedItem().toString().equals("平原")) {
						locationName="2";

					}else if(locationTypeComboBox.getSelectedItem().toString().equals("沿海")) {
						locationName="3";

					}else if(locationTypeComboBox.getSelectedItem().toString().equals("沙漠")) {
						locationName="4";

					}
					m.setManagerLocation(locationName);
					SwingWorkerForNewManager s1 = new SwingWorkerForNewManager();
					s1.setManager(m);
					s1.execute();
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				}
			}
		});
		update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			String managerName =managerNameComboBox.getSelectedItem().toString();
			SpringUtil s = new SpringUtil();
			ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) s.getBean("ManagerServiceImpl");
			boolean  result =	managerServiceImpl.deleteManager(managerName);
			if(result) {
				JOptionPane.showMessageDialog(null, "已成功删除", "删除部队", JOptionPane.INFORMATION_MESSAGE);
				initUI();

			}
			}
		});
		
		}

	
}
