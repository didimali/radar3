package radar.UI.Content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
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
import radar.Entity.Manager;
import radar.Entity.PartConsume;
import radar.Entity.Parts;
import radar.ServiceImpl.RadarServiceImpl;
import radar.Tools.Init;
import radar.UI.Components.BackupPartsBox;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Components.ManagerCombox;
import radar.UI.Components.Table1;
import radar.UI.Top.TopPanel;
import radar.ServiceImpl.ManagerServiceImpl;
import javax.swing.JComboBox;


public class PartsConsume extends ContentPanel implements Init{
	
	private static final long serialVersionUID = 1L;
	private JLabel partsConsumeInfo;
	private Table1 table;
	private JScrollPane panel_2;

	private JButton addPartsConsume;
    private static JFileChooser fileChooser;
	public static File chooseFile;  
	private JLabel backup;
	private JComboBox partsConsumeBox;
	private JLabel managerL;
	private JComboBox ManagerBox;
	
	private JSeparator separator;
	private JPanelTransparent title;
	private JPanelTransparent subtitle;
	
	public PartsConsume() {
		initUI();
		 Action();

	}
	@Override
	public void initUI() {
		initContentTop();
		
		contentTop.add(title, "cell 0 0,grow");		
		contentTop.add(subtitle, "cell 0 1,grow");
		initContentBody();
		contentBody.add(panel_2, "cell 0 0,grow");
		//添加底部按钮
		initContentFoot();
		

		
	}
	/**
	 * 添加内容面板头部
	 */
	public void initContentTop() {
		
		contentTop.setLayout(new MigLayout("", "[100%]", "[100%][]"));
		
		title = new JPanelTransparent();	
		title.setLayout(new MigLayout("", "[][20%][][80px][20%][][10%]", "[100%][]"));		
		subtitle = new JPanelTransparent();
		subtitle.setLayout(new BorderLayout(0, 0));		
		
		partsConsumeInfo = new JLabel("备件消耗信息");
		partsConsumeInfo.setHorizontalAlignment(SwingConstants.LEFT);
		partsConsumeInfo.setFont(new Font("仿宋", Font.BOLD, 24));
		managerL = new JLabel("部队：");
		managerL.setFont(new Font("仿宋", Font.PLAIN, 16));
		ManagerBox = new ManagerCombox("ManagerServiceImpl", "getDataForManagerComboBox");
		ManagerBox.setFont(new Font("仿宋", Font.PLAIN, 15));

		backup = new JLabel("备件：");
		backup.setFont(new Font("仿宋", Font.PLAIN, 16));
		partsConsumeBox = new BackupPartsBox("PartsServiceImpl", "getDataForPartsComboBox");
		partsConsumeBox.setFont(new Font("仿宋", Font.PLAIN, 15));
		addPartsConsume = new JButton("导入备件消耗记录");
		addPartsConsume.setIcon(TopPanel.getIcon("new4.png",this));	
		addPartsConsume.setFont(new Font("仿宋", Font.PLAIN, 16));
	
		title.setLayout(new MigLayout("", "[10%][10%][grow][10%][grow][10%][grow]", "[100%]"));
		title.add(partsConsumeInfo, "flowx,cell 0 0,alignx left,aligny center");
		title.add(managerL, "cell 1 0,alignx trailing");		
		title.add(ManagerBox, "cell 2 0,growx");
		title.add(backup, "cell 3 0,alignx trailing");
		title.add(partsConsumeBox, "cell 4 0,growx");
		title.add(addPartsConsume, "cell 6 0,alignx center,aligny center");
		
		separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		subtitle.add(separator,BorderLayout.CENTER);
		

	}

	/**
	 * 添加内容面板躯干
	 */
	public void initContentBody() {		
		contentBody.setLayout(new MigLayout("", "[100%]", "[100%]"));
		String[] header = { "序号", "备件名称", "备件消耗数量","消耗时间","部队名称"};
		Object[] params= {};
		table = new Table1("PartsServiceImpl", "getPartsConsume",params,header,false,0);
		panel_2 = new JScrollPane(table);		
		panel_2.setBackground(Color.WHITE);
		panel_2.setOpaque(true);

	}

	/**
	 * 添加内容面板底部
	 */
	public void initContentFoot() {
		contentFoot.setLayout(new MigLayout("", "[10%][grow][10][grow][10][grow][10][grow][10][grow][10%]", "[10%][80%][10%]"));
		
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
  public void Action() {
	  addPartsConsume.addMouseListener(new MouseAdapter() {
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

		    		  PartConsume partConsume = new PartConsume();
		        	  String partsName = sheet.getCell(1,i).getContents();
		        	  String partCount = sheet.getCell(2,i).getContents();
		        	  Cell consumeDate = sheet.getCell(3, i);
		        	  String managerName=sheet.getCell(4,i).getContents();
		        	  List<Manager> m = managerServiceImpl.selectManager(managerName);
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
					        	  partConsume.setConsumeDate(fd);;
			        	  }
		    		  
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
	 
			
			
		ManagerBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					String value1 = (String) e.getItem();
					String value2 = (String) partsConsumeBox.getSelectedItem();
					int columnIndex1 = 4;
					int columnIndex2 = 1;
					if(value1.equals("All"))
							columnIndex1 = -1;
					if(value2.equals("All"))
						columnIndex2 = -1;
					table.selectDataByColumnIndexsAndValues(columnIndex1,value1,columnIndex2,value2);
				}
			}
		});
		
		
			
		partsConsumeBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					String value1 = (String) ManagerBox.getSelectedItem();
					String value2 = (String) e.getItem();
					int columnIndex1 = 4;
					int columnIndex2 = 1;
					if(value1.equals("All"))
							columnIndex1 = -1;
					if(value2.equals("All"))
						columnIndex2 = -1;
					table.selectDataByColumnIndexsAndValues(columnIndex1,value1,columnIndex2,value2);
				}
			}
		});
  }
}
