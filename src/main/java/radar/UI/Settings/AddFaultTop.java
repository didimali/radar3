package radar.UI.Settings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;



import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.Activity;
import radar.Entity.FaultRecord;
import radar.Entity.FaultType;
import radar.Entity.Manager;
import radar.Entity.PartConsume;
import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.ServiceImpl.ActivityServiceImpl;
import radar.ServiceImpl.BasicInfoSettingServiceImpl;
import radar.ServiceImpl.FaultRecordServiceImpl;
import radar.ServiceImpl.ManagerServiceImpl;
import radar.ServiceImpl.RadarServiceImpl;
import radar.Tools.Init;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Top.TopPanel;

public class AddFaultTop extends JPanelTransparent implements Init{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panel1;
	private JLabel FaultList;
	private JLabel jt;
	private JLabel addRecord;
	private JButton inputRecord;
	private JSeparator recordJs;
    private static JFileChooser fileChooser;
	public static File chooseFile;  

	public AddFaultTop() {
		setLayout(new MigLayout("", "[100%]", "[grow][]"));
		initUI();
		Action();
	}
	@Override
	public void initUI() {
		panel = new JPanelTransparent();
		add(panel,"cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][][][grow][]", "[100%]"));
		FaultList = new JLabel("返回");
		FaultList.setToolTipText("返回故障列表");
		FaultList.setFont(new Font("仿宋", Font.BOLD, 24));
		FaultList.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(FaultList, "cell 0 0,alignx center,aligny center");

		
		jt = new JLabel(">>");
		jt.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(jt, "cell 1 0,grow");
		
		addRecord = new JLabel("添加故障");
		addRecord.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(addRecord, "cell 2 0,grow");
		
		inputRecord = new JButton("导入故障记录");
		inputRecord.setIcon(TopPanel.getIcon("plus.png",this));	
		inputRecord.setFont(new Font("仿宋", Font.PLAIN, 15));
		panel.add(inputRecord, "cell 4 0,alignx right,aligny center");
		
		panel1 = new JPanelTransparent();
		panel1.setBackground(Color.WHITE);
		
		add(panel1,"cell 0 1,grow");
		panel1.setLayout(new BorderLayout(0, 0));
		recordJs = new JSeparator();
		recordJs.setForeground(Color.GRAY);		
		panel1.add(recordJs,BorderLayout.CENTER);
	}
	//弹出文件选择框
		Integer returnValue =null;

		
		private int importExcel() {
			 fileChooser = new JFileChooser();             
		     //过滤Excel文件，只寻找以xls结尾的Excel文件，如果想过滤word文档也可以写上doc
		     FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "xls");
		     fileChooser.setFileFilter(filter);      
		     returnValue = fileChooser.showOpenDialog(null);    
		     return returnValue;
		}
	@Override
	public void Action() {
		inputRecord.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					importExcel();
					boolean flagg =false;

			        //弹出一个文件选择提示框
			        if (returnValue == fileChooser.APPROVE_OPTION) {
			        //当用户选择文件后获取文件路径
			        chooseFile = fileChooser.getSelectedFile();
			        
			        //根据文件路径初始化Excel工作簿
			        Workbook workBook=null;
			         try {
			                 workBook = Workbook.getWorkbook(chooseFile);
			         } catch (Exception event) {	
			        	 event.printStackTrace();
			 
			 
			         } 
			          //获取该工作表中的第一个工作表   
			          Sheet sheet=workBook.getSheet(0);  
			          //获取该工作表的行数，以供下面循环使用   
			          int rowSize=sheet.getRows();  
			          SpringUtil s = new SpringUtil();
			          RadarServiceImpl radarServiceImpl =(RadarServiceImpl)s.getBean("RadarServiceImpl");
			          FaultRecordServiceImpl faultRecordServiceImpl =(FaultRecordServiceImpl)s.getBean("FaultRecordServiceImpl");
			          List<Radar> radars =radarServiceImpl.getAllRadars();
			          BasicInfoSettingServiceImpl basicInfoSettingServiceImpl =(BasicInfoSettingServiceImpl)s.getBean("BasicInfoSettingServiceImpl");
			          List<FaultType> faultTypes = basicInfoSettingServiceImpl.getAllFaultType();
			          for(int i=1;i<rowSize;i++) {
			        	  FaultRecord fault = new FaultRecord();
			        	  String RName = sheet.getCell(0,i).getContents().toString();
			        	  String faultType = sheet.getCell(1,i).getContents().toString();
			        	  Cell faultDate = sheet.getCell(2, i);
			        	  String faultReason = sheet.getCell(3,i).getContents().toString();
			        	  List<FaultRecord> fr =basicInfoSettingServiceImpl.getFaultRecord();

			        	  java.util.Date fd = null;

			        	  if(faultDate.getType()==CellType.DATE) {
			        		  DateCell dcs = (DateCell) faultDate;
			                     java.util.Date fDate = dcs.getDate();
			                     TimeZone gmt = TimeZone.getTimeZone("GMT");

			                     SimpleDateFormat sdf = new SimpleDateFormat(
			                             "yyyy-MM-dd HH:mm:ss",Locale.getDefault());
			                     sdf.setTimeZone(gmt);


				                String faultTime = sdf.format(fDate);
				                TimeZone local = TimeZone.getDefault();
				                sdf.setTimeZone(local);
					        	  try {
					        		  fd = sdf.parse(faultTime);
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
//					  	  	fault.setFaultDate(fd);
			        	  }
			        	  
			        	  for(int j=0;j<fr.size();j++){
			        		  String fdS=fd.toString();
			        		  Date date1=null;
			        		  SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
			        		  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

			        		  try {
								 date1 = (Date) sdf.parse(fdS);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			        		  String formatStr = sdf1.format(date1);
			        		  String afterDate= sdf1.format(fr.get(j).getFaultDate());
			        		  if((fr.get(j).getRadarId().getRadarName()==RName||fr.get(j).getRadarId().getRadarName().equals(RName))&&
			        				  (fr.get(j).getFaultTypeId().getFaultName()==faultType||fr.get(j).getFaultTypeId().getFaultName().equals(faultType))&&
			        				  (fr.get(j).getFaultReason()==faultReason||fr.get(j).getFaultReason().equals(faultReason))&&
			        				  (formatStr==afterDate||afterDate.equals(formatStr))) {
			        			  JOptionPane.showMessageDialog(null,"文件中包含重复的故障记录");
						            return;
			        		  }
			        		  
			        	  }
					  	  	fault.setFaultDate(fd);

			        	  if(radars!=null||radars.size()>0) {
			        		  for(int j =0;j<radars.size();j++) {
					        	 
					        	  if(radars.get(j).getRadarName()==RName||radars.get(j).getRadarName().equals(RName)) {
					        		  fault.setRadarId(radars.get(j));
					        	  }
				 
					          }
			        		  
			        	  }
			        	if(faultTypes!=null||faultTypes.size()>0) {
			        		 for(int k =0;k<faultTypes.size();k++) {
			 		        	if(faultType.equals(faultTypes.get(k).getFaultName())) {
			 		        		fault.setFaultTypeId(faultTypes.get(k));;
			 		        	}
			 		        }
			        	}
			        	  
			       
			        fault.setFaultReason(faultReason);
			        flagg = faultRecordServiceImpl.add(fault);
			        
			          }
			          if(flagg) {
			                JOptionPane.showMessageDialog(null, "故障记录成功导入");

				        	System.out.println("故障记录成功导入");
				        }
			          
			        }
			        //
				}
			});
	}
	public JLabel getFaultList() {
		return FaultList;
	}
}
