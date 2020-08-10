package radar.UI.Content;

import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.Manager;
import radar.ServiceImpl.ManagerServiceImpl;
import radar.SwingWorker.SwingWorkerForNewManager;
import radar.Tools.Init;
import radar.UI.Components.ComboBox;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Top.TopPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.springframework.validation.annotation.Validated;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSeparator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings({"serial","rawtypes","unchecked"})
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
	private String managerName2;
	private String managerName3;

	private JPanelTransparent title;
	private JPanelTransparent subtitle;


	public NewManager() {
		initUI();
		Action();
		
	}
	
	@Override
	public void initUI() {
		initContentTop();
		contentTop.add(title, "cell 0 0");
		contentTop.add(subtitle, "cell 0 1,growx");
		
		initContentBody();
		
		
		contentBody.add(managerName, "cell 1 1,alignx trailing");
		contentBody.add(managerNameComboBox, "cell 2 1,growx");
		contentBody.add(locationType, "cell 1 2,alignx trailing");
		contentBody.add(locationTypeComboBox, "cell 2 2,growx");
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
		
		ManagerInfo = new JLabel("部队信息设置");
		ManagerInfo.setFont(new Font("仿宋", Font.BOLD, 24));
		ManagerInfo.setHorizontalAlignment(SwingConstants.CENTER);		
		title.add(ManagerInfo, "cell 0 0,growx,aligny center");
		
		managerSeparator = new JSeparator();
		managerSeparator.setForeground(Color.GRAY);		
		subtitle.add(managerSeparator,BorderLayout.CENTER);
	}

	/**
	 * 添加内容面板躯干
	 */
	public void initContentBody() {	
		contentBody.setLayout(new MigLayout("", "[25%,grow][10%][40%,grow][25%]", "[grow][grow][][grow][grow]"));
		managerName = new JLabel("部队编号：");
		managerName.setFont(new Font("仿宋", Font.PLAIN, 16));
		managerNameComboBox = new ComboBox("ManagerServiceImpl", "getDataForManagerComboBox",null);
		managerNameComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
		managerNameComboBox.setEditable(true);
			
		
		locationType = new JLabel("驻地类型：");
		locationType.setFont(new Font("仿宋", Font.PLAIN, 16));
		locationTypeComboBox = new ComboBox("ManagerServiceImpl", "getLocationType", null);
		locationTypeComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
	}
	
	/**
	 * 添加内容面板底部
	 */
	public void initContentFoot() {
	contentFoot.setLayout(new MigLayout("", "[10%][grow][10%][grow][10%][grow][10%]", "[10%][80%][10%]"));
	
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
	/**
	 * 添加页面组件事件
	 */
	public void Action() {

		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) SpringUtil.getBean("ManagerServiceImpl");
				Object[] obj = managerServiceImpl.getDataForManagerComboBox(null);
				Boolean flag = true;
				for(int i = 0; i < obj.length; i++){
					if(managerNameComboBox.getSelectedItem().toString().equals(obj[i].toString())||managerNameComboBox.getSelectedItem().toString().equals("")
							||locationTypeComboBox.getSelectedItem().toString().equals("")) {
						flag= false;
						JOptionPane.showMessageDialog(null,"请添加一个新的部队","提示",JOptionPane.WARNING_MESSAGE);					
						break;
					}
					}
				if(flag) {
				try {
					Manager m = new Manager();
		            m.setManagerStatus(0);
					m.setManagerName(managerNameComboBox.getSelectedItem().toString());
					Integer locationName = null;
					if(locationTypeComboBox.getSelectedItem().toString().equals("高原")) {
						locationName=0;
					}else if(locationTypeComboBox.getSelectedItem().toString().equals("山地")) {
						locationName=1;

					}else if(locationTypeComboBox.getSelectedItem().toString().equals("平原")) {
						locationName=2;

					}else if(locationTypeComboBox.getSelectedItem().toString().equals("沿海")) {
						locationName=3;

					}else if(locationTypeComboBox.getSelectedItem().toString().equals("沙漠")) {
						locationName=4;

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
		managerNameComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {	
				if(managerNameComboBox.getSelectedItem().toString().equals("")) {
					String[] locationTypes = { "", "高原","山地","平原","沿海","沙漠"};
					locationTypeComboBox.setModel(new DefaultComboBoxModel(locationTypes));
				}else if(!managerNameComboBox.getSelectedItem().toString().equals("")) {
					ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) SpringUtil.getBean("ManagerServiceImpl");
					String choosenManagerName =managerNameComboBox.getSelectedItem().toString();
					Object[]  concreteLocation = managerServiceImpl.selectLocationByManagerName(choosenManagerName);
					Object[] resultdata =new String[concreteLocation.length];
					resultdata=concreteLocation;
					locationTypeComboBox.setModel(new DefaultComboBoxModel(resultdata));
				}
				
				if(e.getStateChange() ==ItemEvent.DESELECTED) {
					managerName2 = (String) e.getItem();
				}
			}
		});
			
		update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) SpringUtil.getBean("ManagerServiceImpl");
				String locationType=locationTypeComboBox.getSelectedItem().toString();
				String managerNameEditor=managerNameComboBox.getSelectedItem().toString();
				Object[] obj = managerServiceImpl.getDataForManagerComboBox(null);
				Boolean flag=true;
				//获取修改前的值
				String manager3 =managerName2;
				for(int i = 0; i < obj.length; i++){
				if(manager3.equals("")||locationType.equals("")) {
						JOptionPane.showMessageDialog(null,"请完善修改信息","提示",JOptionPane.WARNING_MESSAGE);
						flag=false;
						break;
					}
				}
				if(flag) {
					int num = JOptionPane.showConfirmDialog(null, "是否修改"+manager3+"的信息？", "提示",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				    switch(num) {
				    case JOptionPane.YES_OPTION:
						boolean result = managerServiceImpl.updateManager(managerNameEditor,locationType,manager3);
						if(result) {
							JOptionPane.showMessageDialog(null, "更新成功", "提示", JOptionPane.INFORMATION_MESSAGE);

						}
				    case JOptionPane.NO_OPTION:
				    	break;
				    case JOptionPane.CANCEL_OPTION:
				    	break;
				    }
				}
			}
		});
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				String managerName =managerNameComboBox.getSelectedItem().toString();
				boolean flag=false;
				if(managerName.equals("")||locationTypeComboBox.getSelectedItem().toString().equals("")) {
						JOptionPane.showMessageDialog(null,"请选择要删除的部队","提示",JOptionPane.WARNING_MESSAGE);
						return;
					}else {
						flag=true;
					}
				if(flag) {
					int num = JOptionPane.showConfirmDialog(null, "是否删除？", "提示",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				    switch(num) {
				    case JOptionPane.YES_OPTION:
				    	ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) SpringUtil.getBean("ManagerServiceImpl");
						boolean  result =	managerServiceImpl.deleteManager(managerName);
						if(result) {
							contentBody.remove(managerNameComboBox);
							managerNameComboBox = new ComboBox("ManagerServiceImpl", "getDataForManagerComboBox",null);
							contentBody.add(managerNameComboBox, "cell 2 1,growx");
							managerNameComboBox.validate();
							managerNameComboBox.repaint();
							JOptionPane.showMessageDialog(null, "已成功删除", "提示", JOptionPane.INFORMATION_MESSAGE);
						}
				    case JOptionPane.NO_OPTION:
				    	break;
				    case JOptionPane.CANCEL_OPTION:
				    	break;
				    }
				
				}
			}
			});		
		}	
}
