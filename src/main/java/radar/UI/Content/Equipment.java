package radar.UI.Content;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.miginfocom.swing.MigLayout;
import radar.Tools.Init;
import radar.UI.Components.TableWithScrollBar;
import radar.UI.Components.XiTongComBox;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class Equipment extends ContentPanel implements Init{
	
	
	private JLabel equipInfo;
	private TableWithScrollBar table;
	private JScrollPane panel_2;	
	private JComboBox XcomboBox;
	private static JFileChooser fileChooser;
	public static File chooseFile;  
	//弹出文件选择框
	Integer returnValue =null;
	private JLabel systemLable;
	
	public Equipment() {
		initUI();
		Action();
}
	@Override
	public void initUI() {
		initContentTop();
		contentTop.add(equipInfo, "flowx,cell 0 0,alignx left,aligny center");
		
		systemLable = new JLabel("子系统：");
		systemLable.setFont(new Font("仿宋", Font.PLAIN, 14));
		contentTop.add(systemLable, "cell 0 0,alignx left");
		

		XcomboBox = new XiTongComBox("XiTongServiceImpl", "getDataForXiTongComboBox");
		XcomboBox.setFont(new Font("仿宋", Font.PLAIN, 13));
		contentTop.add(XcomboBox, "cell 1 0,growx");

		initContentBody();
		contentBody.add(panel_2, "cell 0 0,grow");
		//添加底部按钮
		initContentFoot();
	}
	/**
	 * 添加内容面板头部
	 */
	public void initContentTop() {
		contentTop.setLayout(new MigLayout("", "[25%][grow][25%]", "[grow][grow][grow]"));
		equipInfo = new JLabel("部件信息：");
		equipInfo.setHorizontalAlignment(SwingConstants.LEFT);
		equipInfo.setFont(new Font("仿宋", Font.BOLD, 24));
	}

	/**
	 * 添加内容面板躯干
	 */
	public void initContentBody() {		
		contentBody.setLayout(new MigLayout("", "[grow]", "[grow]"));
		String[] header = { "序号", "部件名称", "对应子系统"};
		Object[] params= {};
		table = new TableWithScrollBar("XiTongServiceImpl", "getEquipmentInfo",params,header,false,0);
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

	private int importExcel() {
		 fileChooser = new JFileChooser();             
	     //过滤Excel文件，只寻找以xls结尾的Excel文件，如果想过滤word文档也可以写上doc
	     FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "xls");
	     fileChooser.setFileFilter(filter);      
	     returnValue = fileChooser.showOpenDialog(null);    
	     return returnValue;
	}
	public void Action() {
		//录入系统信息
//				addEquipInfo.addMouseListener(new MouseAdapter() {
//					@Override
//					public void mouseClicked(MouseEvent e) {
//						importExcel();
//						boolean flagk =false;
//
//				        //弹出一个文件选择提示框
//				        if (returnValue == JFileChooser.APPROVE_OPTION) {
//				        //当用户选择文件后获取文件路径
//				        chooseFile = fileChooser.getSelectedFile();
//				        //根据文件路径初始化Excel工作簿
//				        Workbook workBook=null;
//				         try {
//				                 workBook = Workbook.getWorkbook(chooseFile);
//				         } catch (Exception event) {	
//				        	 event.printStackTrace();
//				         } 
//				          //获取该工作表中的第一个工作表   
//				          Sheet sheet=workBook.getSheet(0);  
//				          //获取该工作表的行数，以供下面循环使用   
//				          int rowSize=sheet.getRows();  
//				        if(rowSize>1) {
//				   		 for(int i=1;i<rowSize;i++) {
//				   			 Equip equip = new Equip();
//				        	  String equipName = sheet.getCell(1,i).getContents();
//				        	  String xiTong = sheet.getCell(2,i).getContents();
//				        	  equip.setEquipName(equipName);;
//				        	  equip.setEquipStatus(0);
//					          SpringUtil s = new SpringUtil();
//					          XiTongServiceImpl xiTongServiceImpl = (XiTongServiceImpl) SpringUtil.getBean("XiTongServiceImpl"); 
//				    		  List<System> xT = xiTongServiceImpl.getXiTonglist();
//				    		  if(xT!=null||xT.size()>0) {
//				        		  for(int j=0;j<xT.size();j++) {
//				        			  
//				        				  if(xT.get(j).getSystemName().toString().equals(xiTong)||xT.get(j).getSystemName().toString()==xiTong) {
//				        					  equip.setSystemId(xT.get(j));
//				    			  }
//				    		  
//				        		  
//				        	  }
//
//				        	 //添加record 
//				    		 flagk = xiTongServiceImpl.add(equip);
//				    		 
//				          }
//				        }
//				          if(flagk) {
//					            JOptionPane.showMessageDialog(null, "部件信息成功导入");
//					            flagk = true;
//				    		 }
//				        }
//				        }
//					}
//				});
		//下拉框筛选事件
		XcomboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					String value = (String) e.getItem();
					if(value.equals("全部"))
						table.selectDataByColumnIndexAndValue(-1,value);
					else
						table.selectDataByColumnIndexAndValue(2,value);
				}
			}
		});
	}
			
			
}
