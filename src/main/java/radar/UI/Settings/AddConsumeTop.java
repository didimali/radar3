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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.Manager;
import radar.Entity.PartConsume;
import radar.Entity.Parts;
import radar.ServiceImpl.ManagerServiceImpl;
import radar.ServiceImpl.PartsServiceImpl;
import radar.ServiceImpl.RadarServiceImpl;
import radar.UI.Components.ComboBox;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Top.TopPanel;

public class AddConsumeTop extends JPanelTransparent{
	private JPanel panel;
	private JPanel panel1;
	private JLabel goBackConsumeList;
	private JLabel jt;
	private JLabel addConsumes;
	private JButton inputConsume;
	private JSeparator consumeJs;
    private static JFileChooser fileChooser;
	public static File chooseFile;  
	public AddConsumeTop() {
		setLayout(new MigLayout("", "[100%]", "[grow][]"));
		initUI();
		Action();
	}
	private void initUI() {
		panel = new JPanelTransparent();
		add(panel,"cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][][][grow][]", "[100%]"));
		goBackConsumeList = new JLabel("返回");
		goBackConsumeList.setToolTipText("返回备件消耗列表");
		goBackConsumeList.setFont(new Font("仿宋", Font.BOLD, 24));
		goBackConsumeList.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(goBackConsumeList, "cell 0 0,alignx center,aligny center");

		
		jt = new JLabel(">>");
		jt.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(jt, "cell 1 0,grow");
		
		addConsumes = new JLabel("添加备件消耗");
		addConsumes.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(addConsumes, "cell 2 0,grow");
		
		inputConsume = new JButton("导入备件信息");
		inputConsume.setIcon(TopPanel.getIcon("plus.png",this));	
		inputConsume.setFont(new Font("仿宋", Font.PLAIN, 15));
		panel.add(inputConsume, "cell 4 0,alignx right,aligny center");
		
		panel1 = new JPanelTransparent();
		panel1.setBackground(Color.WHITE);
		
		add(panel1,"cell 0 1,grow");
		panel1.setLayout(new BorderLayout(0, 0));
		consumeJs = new JSeparator();
		consumeJs.setForeground(Color.GRAY);		
		panel1.add(consumeJs,BorderLayout.CENTER);
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
	private void Action() {
		inputConsume.addMouseListener(new MouseAdapter() {
				@SuppressWarnings("unlikely-arg-type")
				@Override
				public void mouseClicked(MouseEvent e) {
					importExcel();
					boolean flagk =false;
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
			        if(rowSize>1) {
			   		 for(int i=1;i<rowSize;i++) {
				          SpringUtil s = new SpringUtil();
				          ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) s.getBean("ManagerServiceImpl"); 
				          PartsServiceImpl partsServiceImpl = (PartsServiceImpl) s.getBean("PartsServiceImpl"); 
			    		  PartConsume partConsume = new PartConsume();
			        	  String partsName = sheet.getCell(1,i).getContents();
			        	  String partCount = sheet.getCell(2,i).getContents();
			        	  Cell consumeDate = sheet.getCell(3, i);
			        	  String managerName=sheet.getCell(4,i).getContents();
			        	  List<Manager> m = managerServiceImpl.selectManager(managerName);
			        	  //判断重复数据
			        	 List<PartConsume> pConsumes=partsServiceImpl.getPartsConsume();
		        		  //dd
		        		  java.util.Date fd = null;
			        	  if(consumeDate.getType()==CellType.DATE) {
			        		  DateCell dcs = (DateCell) consumeDate;
			                     java.util.Date fDate = dcs.getDate();
			                     TimeZone gmt = TimeZone.getTimeZone("GMT");

			                     SimpleDateFormat sdf = new SimpleDateFormat(
			                             "yyyy-MM-dd",Locale.getDefault());
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
			        	  }
			        	  //dd
			        	  for(int j=0;j<pConsumes.size();j++) {
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
			        		  String afterDate= sdf1.format(pConsumes.get(j).getConsumeDate());
			        		  if((pConsumes.get(j).getManagerId().getManagerName()==managerName||pConsumes.get(j).getManagerId().getManagerName().equals(managerName))&&
			        				(pConsumes.get(j).getPartsId().getPartsName()==partsName||pConsumes.get(j).getPartsId().getPartsName().equals(partsName))&&
			        				(pConsumes.get(j).getpConsumeCount()==Integer.valueOf(partCount)||pConsumes.get(j).getpConsumeCount().equals(Integer.valueOf(partCount)))&&
			        			(formatStr==afterDate||afterDate.equals(formatStr))	) {
			        			  JOptionPane.showMessageDialog(null,"文件中包含重复的备件消耗记录");
						            return;
			        		  }
			        	  }
			        	  
			        	  if(m !=null||m.size()>0) {
			        		  for(int j=0;j<m.size();j++) {
			        			  
			        				  if(m.get(j).getManagerName().toString().equals(managerName)||m.get(j).getManagerName().toString()==managerName) {
			        					  partConsume.setManagerId(m.get(j));;
			    			  }
			    		  
			        		  
			        	  }
			        	  }
			        	  int a = 0;
			        	  try {

			        		     a = Integer.parseInt(partCount);

			        		} catch (NumberFormatException e1) {

			        		    e1.printStackTrace();

			        		}
			        	  partConsume.setpConsumeCount(a);
			        	  RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
			    		  List<Parts> p = radarServiceImpl.getParts();
			    		  if(p !=null||p.size()>0) {
			        		  for(int j=0;j<p.size();j++) {
			        			  
			        				  if(p.get(j).getPartsName().toString().equals(partsName)||p.get(j).getPartsName().toString()==partsName) {
			        					  partConsume.setPartsId(p.get(j));
			    			  }
			    		  
			        		  
			        	  }

						        	  partConsume.setConsumeDate(fd);
			    		  
			        	 //添加record 
			    		 flagk = radarServiceImpl.add(partConsume);
			    		 
			          }
			        }
			          if(flagk) {
				            JOptionPane.showMessageDialog(null, "备件消耗记录成功导入");
			    		 }
			        }
			        }
				}
			});
		 
	}
	
	public JLabel getGoBackConsumeList() {
		return goBackConsumeList;
	}
}
