package radar.UI.Content;

import java.awt.Color;
import java.awt.Font;
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
import radar.UI.Components.ManagerCombox;
import radar.UI.Components.RadarCombox;

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
public NewRadar() {
	initUI();
	Action();
}


@Override
public void initUI() {
	initContentTop();
	contentTop.add(radarInfo,"cell 0 1");
	contentTop.add(radarSeparator, "cell 0 2,growx");
	
	initContentBody();
	ContentBody.add(radarName, "cell 1 1,alignx trailing");
	ContentBody.add(radarNameComboBox, "cell 2 1,growx");
	ContentBody.add(radarType, "cell 1 2,alignx trailing");
	ContentBody.add(radarTypeComboBox, "cell 2 2,growx");
	ContentBody.add(radarInManager, "cell 1 3,alignx trailing");
	ContentBody.add(managerNameComboBox, "cell 2 3,growx");
	ContentBody.add(radarHealth, "cell 1 4,alignx trailing");
	ContentBody.add(radarHealthComboBox, "cell 2 4,growx");
	
	initContentFoot();
	contentFoot.add(add, "cell 1 1,alignx center,aligny center");
	contentFoot.add(update, "cell 3 1,alignx center,aligny center");
	contentFoot.add(delete, "cell 5 1,alignx center,aligny center");
}
/**
 * 添加内容面板头部
 */
public void initContentTop() {
	contentTop.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
	radarInfo = new JLabel("雷达信息设置");
	radarInfo.setFont(new Font("宋体", Font.PLAIN, 14));
	radarInfo.setHorizontalAlignment(SwingConstants.CENTER);
	radarSeparator = new JSeparator();
	radarSeparator.setForeground(Color.GRAY);
}

/**
 * 添加内容面板躯干
 */
public void initContentBody() {		
	ContentBody.setLayout(new MigLayout("", "[25%][10%][40%,grow][25%]", "[grow][grow][][grow][grow]"));
	radarName = new JLabel("雷达编号：");
	radarName.setFont(new Font("宋体", Font.PLAIN, 14));
	radarNameComboBox = new RadarCombox("RadarServiceImpl", "getDataForRadarComboBox");
	radarNameComboBox.setEditable(true);
	
	radarType = new JLabel("雷达型号：");
	radarType.setFont(new Font("宋体", Font.PLAIN, 14));
	String[] radarTypes = { "", "I型雷达","II型雷达"};
	radarTypeComboBox = new JComboBox();
	radarTypeComboBox.setForeground(Color.WHITE);
	radarTypeComboBox.setModel(new DefaultComboBoxModel(radarTypes));
	
	radarInManager = new JLabel("部队编号：");
	radarInManager.setFont(new Font("宋体", Font.PLAIN, 14));
	managerNameComboBox = new ManagerCombox("ManagerServiceImpl", "getDataForManagerComboBox");
	
	radarHealth = new JLabel("健康状态：");
	radarHealth.setFont(new Font("宋体", Font.PLAIN, 14));
	String[] radarHealth = { "", "绿","黄","蓝"};
	radarHealthComboBox = new JComboBox();
	radarHealthComboBox.setForeground(Color.WHITE);
	radarHealthComboBox.setModel(new DefaultComboBoxModel(radarHealth));
}

	/**
	 * 添加内容面板底部
	 */
		public void initContentFoot() {
		contentFoot.setLayout(new MigLayout("", "[10%][grow][10%][grow][10%][grow][10%]", "[10%][grow][10%]"));
		
		add = new JButton("新建");
		
		add.setFont(new Font("宋体", Font.PLAIN, 12));
		
		update = new JButton("修改");
		
		update.setFont(new Font("宋体", Font.PLAIN, 12));
		
		delete = new JButton("删除");
		
		delete.setFont(new Font("宋体", Font.PLAIN, 12));
		
			
		}
  public void Action() {
	  add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SpringUtil s = new SpringUtil();
				RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl");
				Object[] obj = radarServiceImpl.getDataForRadarComboBox();
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
					SpringUtil s1 = new SpringUtil();
					RadarServiceImpl radarServiceImpl1 = (RadarServiceImpl) s1.getBean("RadarServiceImpl");
					List<RadarType> RadarType = radarServiceImpl1.selectRadarType(name1);
					RadarType radarType = new RadarType();
					radarType = RadarType.get(0);
					r.setRadarTypeId(radarType);
					
					r.setRadarStatus(0);
					
					ManagerServiceImpl managerServiceImpl =(ManagerServiceImpl) s1.getBean("ManagerServiceImpl");
		            List <Manager> manager =managerServiceImpl.selectManager(name2);
		            Manager m = new Manager();
		            m = manager.get(0);
		            r.setManagerId(m);
		            
		            String health = radarHealthComboBox.getSelectedItem().toString();
		            String healthResult="";
		            if(health.equals("绿")) {
		            	healthResult="0";
		            }else if(health.equals("黄")) {
		            	healthResult="1";

		            }else if(health.equals("蓝")){
		            	healthResult="2";

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
			SpringUtil s = new SpringUtil();
			RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl");
			boolean  result =	radarServiceImpl.deleteRadar(radarName);
			if(result) {
				JOptionPane.showMessageDialog(null, "已成功删除雷达", "删除雷达", JOptionPane.INFORMATION_MESSAGE);

			}
			}
		});	
}
}
