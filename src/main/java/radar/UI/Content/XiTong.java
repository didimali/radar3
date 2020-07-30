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
import radar.UI.Components.Table1;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class XiTong extends ContentPanel implements Init{
	private JLabel xiTongInfo;
	private Table1 table;
	private JScrollPane panel_2;

	private JComboBox comboBox;
	private JLabel radarTypeLable;

	  private static JFileChooser fileChooser;
		public static File chooseFile;  
	public XiTong() {
		initUI();
		Action();
}
	@Override
	public void initUI() {
		initContentTop();
		contentTop.add(xiTongInfo, "flowx,cell 0 0,alignx left,aligny center");		
		
		contentTop.add(radarTypeLable, "cell 1 0,alignx left");		
		
		contentTop.add(comboBox, "cell 1 0,growx");

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
		xiTongInfo = new JLabel("雷达系统信息：");
		xiTongInfo.setHorizontalAlignment(SwingConstants.LEFT);
		xiTongInfo.setFont(new Font("仿宋", Font.BOLD, 24));
		radarTypeLable = new JLabel("雷达型号：");
		radarTypeLable.setFont(new Font("仿宋", Font.PLAIN, 14));

		comboBox = new JComboBox();
		comboBox.setFont(new Font("仿宋", Font.PLAIN, 13));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"All","I型雷达","II型雷达"}));

	}

	/**
	 * 添加内容面板躯干
	 */
	public void initContentBody() {		
		contentBody.setLayout(new MigLayout("", "[grow]", "[grow]"));
		String[] header = { "序号", "系统名称", "雷达型号"};
		Object[] params= {};
		table = new Table1("XiTongServiceImpl", "getXiTongInfo",params,header,false,0);
		panel_2 = new JScrollPane(table);		
		panel_2.setBackground(Color.WHITE);
		panel_2.setOpaque(true);

	}

		/**
		 * 添加内容面板底部
		 */
			public void initContentFoot() {
				contentFoot.setLayout(new MigLayout("", "[10%][grow][10][grow][10][grow][10][grow][10%]", "[10%][80%][10%]"));
				
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
				//录入系统信息
//				addXiTongInfo.addMouseListener(new MouseAdapter() {
//					@Override
//					public void mouseClicked(MouseEvent e) {
//						importExcel();
//						boolean flagk =false;
//
//				        //弹出一个文件选择提示框
//				        if (returnValue == fileChooser.APPROVE_OPTION) {
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
//				   			 System xiTong = new System();
//				        	  String xiTongName = sheet.getCell(1,i).getContents();
//				        	  String radarType = sheet.getCell(2,i).getContents();
//				        	  xiTong.setSystemName(xiTongName);;
//				        	  xiTong.setSystemStatus(0);;
//					          SpringUtil s = new SpringUtil();
//				        	  RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
//				    		  List<RadarType> r = radarServiceImpl.getRadarTypes();
//				    		  if(r!=null||r.size()>0) {
//				        		  for(int j=0;j<r.size();j++) {
//				        			  
//				        				  if(r.get(j).getRadarTypeName().toString().equals(radarType)||r.get(j).getRadarTypeName().toString()==radarType) {
//				        					  xiTong.setRadarTypeId(r.get(j));
//				    			  }
//				    		  
//				        		  
//				        	  }
//				        XiTongServiceImpl xiTongServiceImpl = (XiTongServiceImpl) s.getBean("XiTongServiceImpl"); 
//
//				        	 //添加record 
//				    		 flagk = xiTongServiceImpl.add(xiTong);
//				    		 
//				          }
//				        }
//				          if(flagk) {
//					            JOptionPane.showMessageDialog(null, "系统信息成功导入");
//					            flagk = true;
//				    		 }
//				        }
//				        }
//					}
//				});
				
				//下拉框筛选事件
				
				comboBox.addItemListener(new ItemListener() {
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
