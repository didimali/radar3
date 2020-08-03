package radar.UI.Settings;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ch.qos.logback.core.joran.action.Action;
import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.FaultRecord;
import radar.Entity.FaultType;
import radar.Entity.Manager;
import radar.Entity.PartConsume;
import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.ServiceImpl.BasicInfoSettingServiceImpl;
import radar.ServiceImpl.ManagerServiceImpl;
import radar.ServiceImpl.PartsServiceImpl;
import radar.ServiceImpl.RadarServiceImpl;
import radar.SwingWorker.SwingWorkerForFaultRecord;
import radar.SwingWorker.SwingWorkerForPartConsume;
import radar.UI.Components.BackupPartsBox;
import radar.UI.Components.Chooser;
import radar.UI.Components.ComboBox;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Components.ManagerCombox;

public class AddConsumeBody extends JPanelTransparent{
	private JPanel JPanel;
	private JPanel jPanel1;
	private JLabel partName;
	private JLabel consumeCount;
	private JLabel consumeTime;
	private JLabel manager;
	private BackupPartsBox partBox;
	private ManagerCombox managerName;
	private JTextField partNum;
	private JTextField partConsumeTime;
	private JButton submit;
	public AddConsumeBody() {
		setLayout(new MigLayout("", "[grow]", "[80%][20%]"));
		initUI();
		Action();
	}

	private void initUI() {
		JPanel=new JPanelTransparent();
		JPanel.setLayout(new MigLayout("", "[10%][grow][10px][grow][grow][10%]", "[10%][grow][grow][][grow][grow][grow][10%]"));
		add(JPanel, "cell 0 0,grow");
		partName = new JLabel("备件名称：");
		partName.setToolTipText("");
		partName.setFont(new Font("仿宋", Font.PLAIN, 16));
		JPanel.add(partName, "cell 1 1,alignx center");

	
		partBox = new BackupPartsBox("PartsServiceImpl", "getDataForPartsComboBox");
		partBox.setToolTipText("选择备件");
		partBox.setFont(new Font("仿宋", Font.PLAIN, 16));
		
		JPanel.add(partBox, "cell 3 1,growx");

		consumeCount = new JLabel("备件消耗数量：");
		consumeCount.setToolTipText("");
		consumeCount.setFont(new Font("仿宋", Font.PLAIN, 16));
		JPanel.add(consumeCount, "cell 1 2,alignx center,aligny baseline");

	
		
		partNum = new JTextField();
		partNum.setToolTipText("输入备件消耗数量");
		partNum.setFont(new Font("仿宋", Font.PLAIN, 15));
		JPanel.add(partNum, "cell 3 2,growx");

		consumeTime = new JLabel("消耗时间：");
		consumeTime.setFont(new Font("仿宋", Font.PLAIN, 16));
		JPanel.add(consumeTime, "cell 1 3,alignx center");

		Chooser chooser1 = Chooser.getInstance();
		partConsumeTime = new JTextField();
		partConsumeTime.setToolTipText("输入备件消耗时间");
		partConsumeTime.setFont(new Font("仿宋", Font.PLAIN, 16));
		partConsumeTime.setHorizontalAlignment(SwingConstants.CENTER);
	    chooser1.register(partConsumeTime);
		JPanel.add(partConsumeTime, "cell 3 3,growx");
		
		
		manager = new JLabel("部队：");
		manager.setFont(new Font("仿宋", Font.PLAIN, 16));
		JPanel.add(manager, "cell 1 4,alignx center");
		

		managerName = new ManagerCombox("ManagerServiceImpl", "getDataForManagerComboBox");
		managerName.setToolTipText("选择部队");
		managerName.setFont(new Font("仿宋", Font.PLAIN, 16));
		JPanel.add(managerName, "cell 3 4,growx");

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
					PartConsume p = new PartConsume();
					String partName =partBox.getSelectedItem().toString();
					if(partName=="All"||partName.equals("All")) {
						JOptionPane.showMessageDialog(null, "请选择备件名称", "提示",JOptionPane.WARNING_MESSAGE);  
						return;
					}else {
					PartsServiceImpl partsServiceImpl = (PartsServiceImpl) SpringUtil.getBean("PartsServiceImpl");
					List<Parts> parts =partsServiceImpl.getPartsType();
					for(int i=0;i<parts.size();i++) {
						if(parts.get(i).getPartsName()==partName||parts.get(i).getPartsName().equals(partName)) {
							p.setPartsId(parts.get(i));
						}
					}
					}
					String partCount = partNum.getText();
					if(partCount==""||partCount.equals("")) {
						JOptionPane.showMessageDialog(null, "请输入备件数量", "提示",JOptionPane.WARNING_MESSAGE);  
						return;
					}
					p.setpConsumeCount(Integer.valueOf(partCount));
					
					String consumeTime=partConsumeTime.getText();
					if(consumeTime==""||consumeTime.equals("")) {
						JOptionPane.showMessageDialog(null, "请输入时间", "提示",JOptionPane.WARNING_MESSAGE);  
						return;
					}
					 SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd");
					 Date cDate = s.parse(consumeTime);
			         p.setConsumeDate(cDate);
			         
			        String manager = managerName.getSelectedItem().toString();
			        if(manager=="All"||manager.equals("All")) {
			        	JOptionPane.showMessageDialog(null, "请选择部队名称", "提示",JOptionPane.WARNING_MESSAGE);  
						return;
			        }else {
			        	ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) SpringUtil.getBean("ManagerServiceImpl");
                        List<Manager> m = managerServiceImpl.getManagers();
                        for(int i=0;i<m.size();i++) {
                        	if(m.get(i).getManagerName()==manager||m.get(i).getManagerName().equals(manager)) {
                        		p.setManagerId(m.get(i));
                        	}
                        }
			        } 
			         
			   
					
					SwingWorkerForPartConsume s1 = new SwingWorkerForPartConsume();
					s1.setPartConsume(p);
					s1.execute();
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});		
	}
}
