package radar.UI.Content;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.Manager;
import radar.Entity.Radar;
import radar.Entity.RadarType;
import radar.ServiceImpl.ManagerServiceImpl;
import radar.ServiceImpl.RadarServiceImpl;
import radar.SwingWorker.SwingWorkerForNewRadar;
import radar.Tools.Init;
import radar.UI.Components.ComboBox;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Top.TopPanel;

import java.awt.BorderLayout;

@SuppressWarnings({"serial","rawtypes"})
public class NewRadar extends ContentPanel implements Init{
	private JLabel radarInfo;
	
	private JLabel radarName;
	private JComboBox radarNameComboBox;
	private JLabel radarType;
	private JComboBox radarTypeComboBox;
	private JLabel radarInManager;
	private JComboBox managerNameComboBox;
	private JLabel radarHealth;
	private JComboBox radarHealthComboBox;
	

	private JButton add;
	private JButton update;
	private JButton delete;
	private JSeparator radarSeparator;
	private JPanelTransparent title;
	private JPanelTransparent subtitle;
	public NewRadar() {
		initUI();
		Action();
	}


@Override
public void initUI() {
	initContentTop();
	contentTop.add(title, "cell 0 0,grow");
	contentTop.add(subtitle, "cell 0 1,grow");
		
	initContentBody();
	contentBody.add(radarName, "cell 1 0,alignx trailing,aligny bottom");
	contentBody.add(radarNameComboBox, "cell 2 0,growx,aligny bottom");
	contentBody.add(radarType, "cell 1 1,alignx trailing");
	contentBody.add(radarTypeComboBox, "cell 2 1,growx");
	contentBody.add(radarInManager, "cell 1 2,alignx trailing");
	contentBody.add(managerNameComboBox, "cell 2 2,growx");
	contentBody.add(radarHealth, "cell 1 3,alignx trailing");
	contentBody.add(radarHealthComboBox, "cell 2 3,growx");
	
	initContentFoot();
	contentFoot.add(add, "cell 1 1,grow");
	contentFoot.add(update, "cell 3 1,grow");
	contentFoot.add(delete, "cell 5 1,grow");
}
/**
 * 添加内容面板头部
 */
public void initContentTop() {
	contentTop.setLayout(new MigLayout("", "[100%]", "[100%][]"));
	
	title = new JPanelTransparent();	
	title.setLayout(new MigLayout("", "[]", "[100%]"));
	
	subtitle = new JPanelTransparent();
	subtitle.setLayout(new BorderLayout(0, 0));
	
	radarInfo = new JLabel("雷达信息设置");
	radarInfo.setFont(new Font("仿宋", Font.BOLD, 24));
	radarInfo.setHorizontalAlignment(SwingConstants.CENTER);	
	title.add(radarInfo, "cell 0 0,growx,aligny center");
	
	
	radarSeparator = new JSeparator();
	radarSeparator.setForeground(Color.BLACK);
	subtitle.add(radarSeparator,BorderLayout.CENTER);
}

/**
 * 添加内容面板躯干
 */
public void initContentBody() {		
	contentBody.setLayout(new MigLayout("", "[25%][10%][40%,grow][25%]", "[grow][grow][][grow][grow]"));
	radarName = new JLabel("雷达编号：");
	radarName.setFont(new Font("仿宋", Font.PLAIN, 16));
	radarNameComboBox = new ComboBox("RadarServiceImpl", "getDataForRadarComboBox",null);
	radarNameComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
	radarNameComboBox.setEditable(true);
	
	radarType = new JLabel("雷达型号：");
	radarType.setFont(new Font("仿宋", Font.PLAIN, 16));
	String[] radarTypes = { "", "I型雷达","II型雷达"};
	radarTypeComboBox = new JComboBox();
	radarTypeComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
	radarTypeComboBox.setForeground(Color.WHITE);
	radarTypeComboBox.setModel(new DefaultComboBoxModel(radarTypes));
	
	radarInManager = new JLabel("部队编号：");
	radarInManager.setFont(new Font("仿宋", Font.PLAIN, 16));
	managerNameComboBox = new ComboBox("ManagerServiceImpl", "getDataForManagerComboBox",null);
	managerNameComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
	
	radarHealth = new JLabel("健康状态：");
	radarHealth.setFont(new Font("仿宋", Font.PLAIN, 16));
	String[] radarHealth = { "", "绿","黄","红"};
	radarHealthComboBox = new JComboBox();
	radarHealthComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
	radarHealthComboBox.setForeground(Color.WHITE);
	radarHealthComboBox.setModel(new DefaultComboBoxModel(radarHealth));
}

	/**
	 * 添加内容面板底部
	 */
		public void initContentFoot() {
		contentFoot.setLayout(new MigLayout("", "[10%][grow][10%][grow][10%][grow][10%]", "[10%][grow][10%]"));
		
		add = new JButton("新建");
		add.setIcon(TopPanel.getIcon("plus.png",this));	
		add.setFont(new Font("仿宋", Font.PLAIN, 15));
		
		update = new JButton("修改");
		update.setIcon(TopPanel.getIcon("edit1.png",this));
		update.setFont(new Font("仿宋", Font.PLAIN, 15));
		
		delete = new JButton("删除");
		delete.setIcon(TopPanel.getIcon("delete.png",this));	
		delete.setFont(new Font("仿宋", Font.PLAIN, 15));
		
			
		}
  public void Action() {
	  //雷达编号下拉框事件(雷达型号、部队编号、健康状态）
	  radarNameComboBox.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked", "static-access" })
			public void actionPerformed(ActionEvent e) {
				if(radarNameComboBox.getSelectedItem().toString().equals("")) {
					//雷达型号
					String[] radarTypes = { "", "I型雷达","II型雷达"};
					radarTypeComboBox.setModel(new DefaultComboBoxModel(radarTypes));
					//部队编号
					managerNameComboBox = new ComboBox("ManagerServiceImpl", "getDataForManagerComboBox",null);
					//健康状态
					String[] radarHealth = { "", "绿","黄","红"};
					radarHealthComboBox.setModel(new DefaultComboBoxModel(radarHealth));
				}else if(!radarNameComboBox.getSelectedItem().toString().equals("")) {
					SpringUtil s = new SpringUtil();
					RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl");
					String choosenRadarName =radarNameComboBox.getSelectedItem().toString();
					//雷达型号
					Object[]  concreteRadarType = radarServiceImpl.selectLocationByRadarName(choosenRadarName);
					Object[] resultdataRadarType =new String[concreteRadarType.length];
					resultdataRadarType=concreteRadarType;
					radarTypeComboBox.setModel(new DefaultComboBoxModel(resultdataRadarType));
					//部队编号
					Object[]  managerName = radarServiceImpl.selectManagerNameByRadarName(choosenRadarName);
					Object[] resultdataManagerName =new String[managerName.length];
					resultdataManagerName=managerName;
					managerNameComboBox.setModel(new DefaultComboBoxModel(resultdataManagerName));
					//健康状态
					Object[]  concreteH = radarServiceImpl.selectHealthByRadarName(choosenRadarName);
					Object[] resultdataH =new String[concreteH.length];
					resultdataH=concreteH;
					radarHealthComboBox.setModel(new DefaultComboBoxModel(resultdataH));
				}
			}
		});
	  add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RadarServiceImpl radarServiceImpl = (RadarServiceImpl) SpringUtil.getBean("RadarServiceImpl");
				Object[] obj = radarServiceImpl.getDataForRadarComboBox(null);
				Boolean flag = true;
				System.out.println("obj.length");
				System.out.println(obj.length);

				for(int i = 0; i < obj.length; i++){
					System.out.println(obj[i]);

						if(radarNameComboBox.getSelectedItem().toString().equals(obj[i])) {
							flag= false;
							JOptionPane.showMessageDialog(null,"该雷达已经存在","提示",JOptionPane.WARNING_MESSAGE);					
							break;
						}
					
					
					}
				if(flag) {
				try {
					Radar r = new Radar();
					r.setRadarName(radarNameComboBox.getSelectedItem().toString());
					String name1=radarTypeComboBox.getSelectedItem().toString();
					String name2 = managerNameComboBox.getSelectedItem().toString();

					if(name1.equals("")||name2.equals("")) {
						JOptionPane.showMessageDialog(null,"请选择雷达类型、所属部队","提示",JOptionPane.WARNING_MESSAGE);					
					}
					RadarServiceImpl radarServiceImpl1 = (RadarServiceImpl) SpringUtil.getBean("RadarServiceImpl");
					List<RadarType> RadarType = radarServiceImpl1.selectRadarType(name1);
					RadarType radarType = new RadarType();
					radarType = RadarType.get(0);
					r.setRadarTypeId(radarType);
					
					r.setRadarStatus(0);
					
					ManagerServiceImpl managerServiceImpl =(ManagerServiceImpl) SpringUtil.getBean("ManagerServiceImpl");
		            List <Manager> manager =managerServiceImpl.selectManager(name2);
		            Manager m = new Manager();
		            m = manager.get(0);
		            r.setManagerId(m);
		            
		            String health = radarHealthComboBox.getSelectedItem().toString();
		            Integer healthResult=null;
		            if(health.equals("绿")) {
		            	healthResult=0;
		            }else if(health.equals("黄")) {
		            	healthResult=1;

		            }else if(health.equals("红")){
		            	healthResult=2;

		            }
		            r.setRadarHealth(healthResult);
					SwingWorkerForNewRadar s2 = new SwingWorkerForNewRadar();
					s2.setRadar(r);
					s2.execute();
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
				
			String radarName =radarNameComboBox.getSelectedItem().toString();
			RadarServiceImpl radarServiceImpl = (RadarServiceImpl) SpringUtil.getBean("RadarServiceImpl");
			boolean  result =	radarServiceImpl.deleteRadar(radarName);
			if(result) {
				JOptionPane.showMessageDialog(null, "已成功删除雷达", "删除雷达", JOptionPane.INFORMATION_MESSAGE);

			}
			}
		});	
}
}
