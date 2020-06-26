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
import radar.Tools.Init;
import radar.UI.Components.Button;
import radar.UI.Components.Table;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class Parts extends ContentPanel implements Init{
	private JLabel partsInfo;
	private Table table;
	private JScrollPane panel_2;
	
	private JButton firstPage;
	private JButton previousPage;
	private JButton nextPage;
	private JButton lastPage;
	private JButton addParts;
    private static JFileChooser fileChooser;
	public static File chooseFile;  


	public Parts() {
		
		 initUI();
		 Action();
	}
	@Override
	public void initUI() {
		initContentTop();
		contentTop.add(partsInfo, "flowx,cell 0 2,alignx left,aligny center");
		contentTop.add(addParts, "cell 2 3,alignx right,aligny center");
		
		comboBox = new JComboBox();
		String[] radarTypes = { "", "I型雷达","II型雷达"};
		comboBox.setModel(new DefaultComboBoxModel(radarTypes));
		comboBox.setFont(new Font("宋体", Font.PLAIN, 12));
		contentTop.add(comboBox, "cell 0 3,growx");
		
		lookUp = new JButton("搜索");
		lookUp.setFont(new Font("宋体", Font.PLAIN, 12));
		contentTop.add(lookUp, "cell 1 3,alignx left");
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
		contentTop.setLayout(new MigLayout("", "[grow][grow][grow]", "[][grow][grow][][grow]"));
		partsInfo = new JLabel("备件种类信息：");
		partsInfo.setHorizontalAlignment(SwingConstants.LEFT);
		partsInfo.setFont(new Font("宋体", Font.PLAIN, 14));
		addParts = new JButton("导入信息");
		
		addParts.setFont(new Font("宋体", Font.PLAIN, 12));



	}

	/**
	 * 添加内容面板躯干
	 */
	public void initContentBody() {		
		ContentBody.setLayout(new MigLayout("", "[grow]", "[grow]"));
		String[] header = { "序号", "备件名称", "雷达型号"};
		Object[] params= {};
		table = new Table("PartsServiceImpl", "getPartsType",params,header);
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
		  //录入备件种类信息
			addParts.addMouseListener(new MouseAdapter() {
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
			    		  radar.Entity.Parts part = new radar.Entity.Parts();
			        	  String partsName = sheet.getCell(1,i).getContents();
			        	  String radarType = sheet.getCell(2,i).getContents();
			        	  part.setPartsName(partsName);
			        	  part.setPartsCount(0);
				          SpringUtil s = new SpringUtil();
			        	  RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
			    		  List<RadarType> r = radarServiceImpl.getRadarTypes();
			    		  if(r!=null||r.size()>0) {
			        		  for(int j=0;j<r.size();j++) {
			        			  
			        				  if(r.get(j).getRadarTypeName().toString().equals(radarType)||r.get(j).getRadarTypeName().toString()==radarType) {
			    				  part.setRadarTypeId(r.get(j));
			    			  }
			    		  
			        		  
			        	  }
			    		  
			        	 //添加record 
			    		 flagk = radarServiceImpl.add(part);
			    		 
			          }
			        }
			          if(flagk) {
				            JOptionPane.showMessageDialog(null, "备件种类成功导入");
				            flagk = true;
			    			 System.out.println("备件种类成功导入");
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
