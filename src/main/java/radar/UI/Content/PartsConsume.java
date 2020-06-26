package radar.UI.Content;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.PartConsume;
import radar.Entity.Parts;
import radar.Entity.RadarType;
import radar.ServiceImpl.RadarServiceImpl;
import radar.Tools.Init;
import radar.UI.Components.Button;
import radar.UI.Components.Table;

public class PartsConsume extends ContentPanel implements Init{
	private JLabel partsConsumeInfo;
	private Table table;
	private JScrollPane panel_2;
	
	private JButton firstPage;
	private JButton previousPage;
	private JButton nextPage;
	private JButton lastPage;
	private JButton addPartsConsume;
    private static JFileChooser fileChooser;
	public static File chooseFile;  
	public PartsConsume() {
		initUI();
		 Action();

	}
	@Override
	public void initUI() {
		initContentTop();
		contentTop.add(partsConsumeInfo, "flowx,cell 0 1,alignx left,aligny center");
		contentTop.add(addPartsConsume, "cell 1 1,alignx right,aligny center");
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
		contentTop.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow][][grow]"));
		partsConsumeInfo = new JLabel("备件消耗信息");
		partsConsumeInfo.setHorizontalAlignment(SwingConstants.LEFT);
		partsConsumeInfo.setFont(new Font("宋体", Font.PLAIN, 14));
		addPartsConsume = new JButton("导入信息");
		addPartsConsume.setFont(new Font("宋体", Font.PLAIN, 12));



	}

	/**
	 * 添加内容面板躯干
	 */
	public void initContentBody() {		
		ContentBody.setLayout(new MigLayout("", "[grow]", "[grow]"));
		String[] header = { "序号", "备件名称", "备件消耗数量","消耗时间"};
		Object[] params= {};
		table = new Table("PartsServiceImpl", "getPartsConsume",params,header);
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
			    		  PartConsume partConsume = new PartConsume();
			    		  
			        	  String partsName = sheet.getCell(1,i).getContents();
			        	  String partCount = sheet.getCell(2,i).getContents();
			        	  Cell consumeDate = sheet.getCell(3, i);
			        	  int a = 0;
			        	  try {

			        		     a = Integer.parseInt(partCount);

			        		} catch (NumberFormatException e1) {

			        		    e1.printStackTrace();

			        		}
			        	  partConsume.setpConsumeCount(a);
				          SpringUtil s = new SpringUtil();
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
