package radar.UI.Settings;

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
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.Manager;
import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.Entity.RadarType;
import radar.ServiceImpl.ManagerServiceImpl;
import radar.ServiceImpl.RadarServiceImpl;
import radar.SwingWorker.SwingWorkerForNewRadar;
import radar.SwingWorker.SwingWorkerForParts;
import radar.UI.Components.ComboBox;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Top.TopPanel;

public class AddStructBody extends JPanelTransparent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel JPanel;
	private JPanel jPanel1;
	private JLabel partsName;
	private JLabel partsNum;

	private ComboBox rType;
	private JTextField pNum;
	private JTextField pName;
	private JButton submit;
	
	private JLabel radarType;
	private JComboBox typeComboBox;
	private JLabel radarSystem;
	private JComboBox systemComboBox;
	private JLabel radarEquip;
	private JComboBox equipComboBox;
	private String rName;
	
	private JButton add;
	private JButton update;
	private JButton delete;
	public AddStructBody() {
		setLayout(new MigLayout("", "[grow]", "[80%][20%]"));
		initUI();
		Action();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		JPanel=new JPanelTransparent();
		add(JPanel, "cell 0 0,grow");
		JPanel.setLayout(new MigLayout("", "[25%][10%][40%,grow][25%]", "[grow][grow][][grow][grow]"));
		
		radarType = new JLabel("雷达型号：");
		radarType.setFont(new Font("仿宋", Font.PLAIN, 16));
		typeComboBox = new ComboBox("RadarServiceImpl", "getDataForTypeComboBox",null);		
		typeComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
		typeComboBox.setEditable(true);
		
		radarSystem = new JLabel("雷达分系统：");
		radarSystem.setFont(new Font("仿宋", Font.PLAIN, 16));
		systemComboBox = new ComboBox("RadarServiceImpl", "getDataForSystemComboBox",null);
		systemComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
		systemComboBox.setEditable(true);
		
		radarEquip = new JLabel("分系统所属部件：");
		radarEquip.setFont(new Font("仿宋", Font.PLAIN, 16));
		equipComboBox = new ComboBox("RadarServiceImpl", "getDataForEquipComboBox",null);
		equipComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
		equipComboBox.setEditable(true);
		
		
		JPanel.add(radarType, "cell 1 0,alignx trailing,aligny bottom");
		JPanel.add(typeComboBox, "cell 2 0,growx,aligny bottom");
		JPanel.add(radarSystem, "cell 1 1,alignx trailing");
		JPanel.add(systemComboBox, "cell 2 1,growx");
		JPanel.add(radarEquip, "cell 1 2,alignx trailing");
		JPanel.add(equipComboBox, "cell 2 2,growx");
		
		jPanel1=new JPanelTransparent();
		jPanel1.setLayout(new MigLayout("", "[20%][grow][20%][grow][20%]", "[10%][grow][10%]"));
		add(jPanel1, "cell 0 1,grow");
		add = new JButton("新建");
		add.setIcon(TopPanel.getIcon("plus.png",this));	
		add.setFont(new Font("仿宋", Font.PLAIN, 15));

		delete = new JButton("删除");
		delete.setIcon(TopPanel.getIcon("delete.png",this));	
		delete.setFont(new Font("仿宋", Font.PLAIN, 15));
		jPanel1.add(add, "cell 1 1,grow");
		jPanel1.add(delete, "cell 3 1,grow");
		
	}

	  public void Action() {

		  add.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					RadarServiceImpl radarServiceImpl = (RadarServiceImpl) SpringUtil.getBean("RadarServiceImpl");										
						String type=typeComboBox.getSelectedItem().toString();
						String system=systemComboBox.getSelectedItem().toString();
						String equip=equipComboBox.getSelectedItem().toString();
						if(type.equals("")||system.equals("")||equip.equals("")) {
							JOptionPane.showMessageDialog(null,"请完善修改信息","提示",JOptionPane.WARNING_MESSAGE);
						}else {
							int num = JOptionPane.showConfirmDialog(null, "是否添加"+type+"的信息？", "提示",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						    switch(num) {
						    case JOptionPane.YES_OPTION:
								boolean result = radarServiceImpl.addStruct(type,system,equip);
								if(result) {
									JPanel.remove(typeComboBox);
									JPanel.remove(systemComboBox);
									JPanel.remove(equipComboBox);
									typeComboBox = new ComboBox("RadarServiceImpl", "getDataForTypeComboBox",null);
									typeComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
									typeComboBox.setEditable(true);
									systemComboBox = new ComboBox("RadarServiceImpl", "getDataForSystemComboBox",null);
									systemComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
									systemComboBox.setEditable(true);
									equipComboBox = new ComboBox("RadarServiceImpl", "getDataForEquipComboBox",null);
									equipComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
									equipComboBox.setEditable(true);
									JPanel.add(typeComboBox, "cell 2 0,growx,aligny bottom");
									JPanel.add(systemComboBox, "cell 2 1,growx");
									JPanel.add(equipComboBox, "cell 2 2,growx");					
									JPanel.validate();
									JPanel.repaint();									
									JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
								}else {
									JOptionPane.showMessageDialog(null,"所添加信息已存在","提示",JOptionPane.WARNING_MESSAGE);
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
					String type=typeComboBox.getSelectedItem().toString();
					String system=systemComboBox.getSelectedItem().toString();
					String equip=equipComboBox.getSelectedItem().toString();
					if(type.equals("")||(system.equals("")&&equip.equals(""))||(system.equals("")&&!equip.equals(""))){
						JOptionPane.showMessageDialog(null,"请完善删除信息","提示",JOptionPane.WARNING_MESSAGE);
					}else {			
					int num = JOptionPane.showConfirmDialog(null, "是否删除？", "提示",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				    switch(num) {
				    case JOptionPane.YES_OPTION:
				    	RadarServiceImpl radarServiceImpl = (RadarServiceImpl) SpringUtil.getBean("RadarServiceImpl");
						boolean  result =	radarServiceImpl.deleteStruct(system,equip);
						if(result) {
							JPanel.remove(typeComboBox);
							JPanel.remove(systemComboBox);
							JPanel.remove(equipComboBox);
							typeComboBox = new ComboBox("RadarServiceImpl", "getDataForTypeComboBox",null);
							typeComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
							typeComboBox.setEditable(true);
							systemComboBox = new ComboBox("RadarServiceImpl", "getDataForSystemComboBox",null);
							systemComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
							systemComboBox.setEditable(true);
							equipComboBox = new ComboBox("RadarServiceImpl", "getDataForEquipComboBox",null);
							equipComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
							equipComboBox.setEditable(true);
							JPanel.add(typeComboBox, "cell 2 0,growx,aligny bottom");
							JPanel.add(systemComboBox, "cell 2 1,growx");
							JPanel.add(equipComboBox, "cell 2 2,growx");					
							JPanel.validate();
							JPanel.repaint();	
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
