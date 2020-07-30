package radar.UI.Content;

import java.awt.BorderLayout;
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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import jxl.Sheet;
import jxl.Workbook;
import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.RadarType;
import radar.ServiceImpl.RadarServiceImpl;
import radar.Tools.Init;
import radar.UI.Components.ComboBox;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Components.Table1;
import radar.UI.Top.TopPanel;

import javax.swing.JComboBox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

@SuppressWarnings("serial")
public class Parts extends ContentPanel implements Init{
	private JLabel partsInfo;
	private Table1 table;
	private JScrollPane panel_2;
	

	private JButton addParts;
    private static JFileChooser fileChooser;
	public static File chooseFile;  
	private JComboBox radarComboxTypeBox;
	private JLabel radarTypeLable;
	
	private JSeparator separator;
	private JPanelTransparent title;
	private JPanelTransparent subtitle;

	public Parts() {
		
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
		title.setLayout(new MigLayout("", "[][20%][][120px][20%][][10%]", "[100%]"));
		
		subtitle = new JPanelTransparent();
		subtitle.setLayout(new BorderLayout(0, 0));
		
		partsInfo = new JLabel("备件种类信息");
		partsInfo.setHorizontalAlignment(SwingConstants.LEFT);
		partsInfo.setFont(new Font("仿宋", Font.BOLD, 24));
		title.add(partsInfo, "cell 0 0,growx,aligny center");
		
		radarTypeLable = new JLabel("雷达型号  ");
		radarTypeLable.setFont(new Font("仿宋", Font.PLAIN, 16));
		title.add(radarTypeLable, "cell 2 0,growx,aligny center");
		
		radarComboxTypeBox = new ComboBox("AcuteForecastServiceImpl", "getRadarType", null);
		radarComboxTypeBox.setMaximumRowCount(8);
		radarComboxTypeBox.setFont(new Font("仿宋", Font.PLAIN, 15));
		title.add(radarComboxTypeBox, "cell 3 0,growx,aligny center");
		
		addParts = new JButton("导入备件种类信息");	
		addParts.setIcon(TopPanel.getIcon("new4.png",this));	
		addParts.setFont(new Font("仿宋", Font.PLAIN, 16));		
		title.add(addParts, "cell 5 0,growx,aligny center");
				
		separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		subtitle.add(separator,BorderLayout.CENTER);
		
	}

	/**
	 * 添加内容面板躯干
	 */
	public void initContentBody() {		
		contentBody.setLayout(new MigLayout("", "[100%]", "[100%]"));
		String[] header = { "序号", "备件名称", "雷达型号"};
		Object[] params= {};
		table = new Table1("PartsServiceImpl", "getPartsType",params,header,false,0);
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
			private JLabel l1;
			private JLabel l2;
			private JLabel l3;
			private JLabel l4;
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
		
			//下拉框筛选事件
			
			radarComboxTypeBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == 1) {
						String value = (String) e.getItem();
						if(value.equals("All"))
							table.selectDataByColumnIndexAndValue(-1,value);
						else
							table.selectDataByColumnIndexAndValue(2,value);
					}
				}
			});
	  }
}
