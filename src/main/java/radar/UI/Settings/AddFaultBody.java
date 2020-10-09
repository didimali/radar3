package radar.UI.Settings;

import radar.SpringUtil;
import radar.Entity.FaultRecord;
import radar.Entity.FaultType;
import radar.Entity.Radar;
import radar.ServiceImpl.BasicInfoSettingServiceImpl;
import radar.ServiceImpl.RadarServiceImpl;
import radar.SwingWorker.SwingWorkerForFaultRecord;
import radar.UI.Components.Chooser;
import radar.UI.Components.ComboBox;
import radar.UI.Components.JPanelTransparent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddFaultBody extends JPanelTransparent{
	private JPanel JPanel;
	private JPanel jPanel1;
	private JLabel radarName;
	private JLabel faultType;
	private JLabel faultTime;
	private JLabel faultReason;
	private JComboBox radarNameComboBox;
	private ComboBox faultT;
	private JTextField textTime;
	private JTextArea textArea;
	private JButton submit;
	/**
	 * 
	 */
	private static final long serialVersionUID = -1963574552821054502L; 
		public AddFaultBody() {
			setLayout(new MigLayout("", "[grow]", "[80%][20%]"));
			initUI();
			Action();
		}
	
	
		private void initUI() {
			// TODO Auto-generated method stub
			JPanel=new JPanelTransparent();
			JPanel.setLayout(new MigLayout("", "[10%][grow][10px][grow][grow][10%]", "[10%][grow][grow][][grow][grow][grow][10%]"));
			add(JPanel, "cell 0 0,grow");
			radarName = new JLabel("雷达：");
			radarName.setToolTipText("");
			radarName.setFont(new Font("仿宋", Font.PLAIN, 16));
			JPanel.add(radarName, "cell 1 1,alignx center");

		
			radarNameComboBox = new ComboBox("RadarServiceImpl", "getDataForRadarComboBox",null);
			radarNameComboBox.setToolTipText("选择雷达");
			radarNameComboBox.setFont(new Font("仿宋", Font.PLAIN, 16));
			
			JPanel.add(radarNameComboBox, "cell 3 1,growx");

			faultType = new JLabel("故障类型：");
			faultType.setToolTipText("");
			faultType.setFont(new Font("仿宋", Font.PLAIN, 16));
			JPanel.add(faultType, "cell 1 2,alignx center,aligny baseline");

		
			faultT = new ComboBox("BasicInfoSettingServiceImpl", "getFaultType", null);
			faultT.setToolTipText("选择故障类型");
			faultT.setFont(new Font("仿宋", Font.PLAIN, 15));
			JPanel.add(faultT, "cell 3 2,growx");

			faultTime = new JLabel("故障时间：");
			faultTime.setFont(new Font("仿宋", Font.PLAIN, 16));
			JPanel.add(faultTime, "cell 1 3,alignx center");

			
			Chooser chooser1 = Chooser.getInstance();
			textTime = new JTextField();
			textTime.setToolTipText("选择故障时间");
			textTime.setHorizontalAlignment(SwingConstants.CENTER);
		    chooser1.register(textTime);
			JPanel.add(textTime, "cell 3 3,growx");


			faultReason = new JLabel("故障原因：");
			faultReason.setFont(new Font("仿宋", Font.PLAIN, 16));
			JPanel.add(faultReason, "cell 1 4,alignx center");

			textArea = new JTextArea();
			textArea.setBorder( BorderFactory.createEmptyBorder(10, 10, 10, 10));
			textArea.setToolTipText("填写故障原因");
			textArea.setFont(new Font("仿宋", Font.PLAIN, 16));
			textArea.setBackground(Color.WHITE);
			JPanel.add(textArea, "cell 3 4,grow");

			jPanel1=new JPanelTransparent();
			jPanel1.setLayout(new MigLayout("", "[8px][grow][8px]", "[8px][][grow][8px]"));
			add(jPanel1, "cell 0 1,grow");
			submit = new JButton("添加");
			submit.setFont(new Font("仿宋", Font.PLAIN, 16));
			jPanel1.add(submit, "cell 1 1,alignx center");

		}
		public void Action() {
			submit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						FaultRecord f = new FaultRecord();
						String radarName =radarNameComboBox.getSelectedItem().toString();
						RadarServiceImpl radarServiceImpl = (RadarServiceImpl) SpringUtil.getBean("RadarServiceImpl");
						List<Radar> radar = radarServiceImpl.getAllRadars();
			           for(int i=0;i<radar.size();i++) {
			        	   if(radar.get(i).getRadarName()==radarName||radar.get(i).getRadarName().equals(radarName)) {
			        		   f.setRadarId(radar.get(i));
			        	   }
			           }
			           
			           String faultType = faultT.getSelectedItem().toString();
			           if(faultType=="All"||faultType.equals("All")) {
							JOptionPane.showMessageDialog(null, "请选择故障类型", "提示",JOptionPane.WARNING_MESSAGE);  
							return;
			           }
			           BasicInfoSettingServiceImpl basicInfoSettingServiceImpl = (BasicInfoSettingServiceImpl) SpringUtil.getBean("BasicInfoSettingServiceImpl");
			           List<FaultType> ft =basicInfoSettingServiceImpl.getAllFaultType();
			           for(int i=0;i<ft.size();i++) {
			        	   if(ft.get(i).getFaultName()==faultType||ft.get(i).getFaultName().equals(faultType)) {
			        		   f.setFaultTypeId(ft.get(i));
			        	   }
			           }
			           
			           String faultTime=  textTime.getText();
			           if(faultTime==""||faultTime.equals("")) {
			        	   JOptionPane.showMessageDialog(null, "请选择故障时间", "提示",JOptionPane.WARNING_MESSAGE);  
							return;
			           }
			           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			           Date faultDate = sdf.parse(faultTime);
			           f.setFaultDate(faultDate);
			           
			           String faultReason="";
			           faultReason=textArea.getText();
			           f.setFaultReason(faultReason);
						
						SwingWorkerForFaultRecord s = new SwingWorkerForFaultRecord();
						s.setFaultRecord(f);
						s.execute();
					}
					catch(Exception e1) {
						e1.printStackTrace();
					}
					
					
				}
			});
		}
}
