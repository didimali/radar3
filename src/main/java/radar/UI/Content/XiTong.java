package radar.UI.Content;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import jxl.Sheet;
import jxl.Workbook;
import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.RadarType;
import radar.ServiceImpl.RadarServiceImpl;
import radar.ServiceImpl.XiTongServiceImpl;
import radar.Tools.Init;
import radar.UI.Components.Button;
import radar.UI.Components.Table;
import radar.Entity.System;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class XiTong extends ContentPanel implements Init{
	private JLabel xiTongInfo;
	private Table table;
	private JScrollPane panel_2;
	
	private JButton firstPage;
	private JButton previousPage;
	private JButton nextPage;
	private JButton lastPage;
	
	private JButton addXiTongInfo;

	  private static JFileChooser fileChooser;
		public static File chooseFile;  
	public XiTong() {
		initUI();
		Action();
}
	@Override
	public void initUI() {
		initContentTop();
		contentTop.add(xiTongInfo, "flowx,cell 0 1,alignx left,aligny center");
		contentTop.add(addXiTongInfo, "cell 2 2,alignx right,aligny center");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"","I型雷达","II型雷达"}));
		contentTop.add(comboBox, "cell 0 2,growx");
		
		lookUp = new JButton("搜索");
		contentTop.add(lookUp, "cell 1 2");

		initContentBody();
		ContentBody.add(panel_2, "cell 0 0,grow");
		//添加底部按钮
		initContentFoot();
		contentFoot.add(firstPage, "cell 1 1,grow");			
		contentFoot.add(previousPage, "cell 3 1,grow");			
		contentFoot.add(nextPage, "cell 5 1,grow");			
		contentFoot.add(lastPage, "cell 7 1,grow");	
		
	}
	/**
	 * 添加内容面板头部
	 */
	public void initContentTop() {
		contentTop.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][][][grow]"));
		xiTongInfo = new JLabel("雷达系统信息：");
		xiTongInfo.setHorizontalAlignment(SwingConstants.LEFT);
		xiTongInfo.setFont(new Font("宋体", Font.PLAIN, 14));
		addXiTongInfo = new JButton("导入信息");
		addXiTongInfo.setFont(new Font("宋体", Font.PLAIN, 12));
		



	}

	/**
	 * 添加内容面板躯干
	 */
	public void initContentBody() {		
		ContentBody.setLayout(new MigLayout("", "[grow]", "[grow]"));
		String[] header = { "序号", "系统名称", "雷达型号"};
		Object[] params= {};
		table = new Table("XiTongServiceImpl", "getXiTongInfo",params,header);
		panel_2 = new JScrollPane(table);		
		panel_2.setBackground(Color.WHITE);
		panel_2.setOpaque(true);

	}

		/**
		 * 添加内容面板底部
		 */
			public void initContentFoot() {
				contentFoot.setLayout(new MigLayout("", "[10%][grow][10][grow][10][grow][10][grow][10%]", "[10%][80%][10%]"));
				firstPage = new Button("首 页");
				previousPage = new Button("上 一 页");
				nextPage = new Button("下 一 页");
				lastPage = new Button("尾 页");
			
				
			}
			private void FillTable() {
				DefaultTableModel model = new DefaultTableModel(table.getPageData(),
		                table.header);
		        table.setModel(model);
		        table.setStyle();
			}
			//弹出文件选择框
			Integer returnValue =null;
			private JComboBox comboBox;
			private JButton lookUp;
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
				addXiTongInfo.addMouseListener(new MouseAdapter() {
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
				   			 System xiTong = new System();
				        	  String xiTongName = sheet.getCell(1,i).getContents();
				        	  String radarType = sheet.getCell(2,i).getContents();
				        	  xiTong.setSystemName(xiTongName);;
				        	  xiTong.setSystemStatus(0);;
					          SpringUtil s = new SpringUtil();
				        	  RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
				    		  List<RadarType> r = radarServiceImpl.getRadarTypes();
				    		  if(r!=null||r.size()>0) {
				        		  for(int j=0;j<r.size();j++) {
				        			  
				        				  if(r.get(j).getRadarTypeName().toString().equals(radarType)||r.get(j).getRadarTypeName().toString()==radarType) {
				        					  xiTong.setRadarTypeId(r.get(j));
				    			  }
				    		  
				        		  
				        	  }
				        XiTongServiceImpl xiTongServiceImpl = (XiTongServiceImpl) s.getBean("XiTongServiceImpl"); 

				        	 //添加record 
				    		 flagk = xiTongServiceImpl.add(xiTong);
				    		 
				          }
				        }
				          if(flagk) {
					            JOptionPane.showMessageDialog(null, "系统信息成功导入");
					            flagk = true;
				    		 }
				        }
				        }
					}
				});
				//底部按钮事件
				//首页
				firstPage.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						table.getFirstPage();
						//返回当前页
						FillTable();
						
					}

					
				});
				//底部按钮事件
					//上一页
				previousPage.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						//页数-1
						table.getPreviousPage();
						//返回当前页
						FillTable();
					}
				});
				//底部按钮事件
					//下一页
				nextPage.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						//页数+1
						table.getNextPage();
						//返回当前页
						FillTable();
					}
				});
				//底部按钮事件
					//末页
				lastPage.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						table.getLastPage();
						//返回当前页
						FillTable();
					}
				});
			}
}
