package radar.UI.Settings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
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

import antlr.collections.impl.Vector;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.FaultRecord;
import radar.Entity.FaultType;
import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.Entity.RadarType;
import radar.ServiceImpl.BasicInfoSettingServiceImpl;
import radar.ServiceImpl.FaultRecordServiceImpl;
import radar.ServiceImpl.RadarServiceImpl;
import radar.Tools.Init;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Top.TopPanel;

public class AddPartsTop extends JPanelTransparent implements Init{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panel1;
	private JLabel goBack;
	private JLabel jt;
	private JLabel addParts;
	private JButton inputParts;
	private JSeparator partsJs;
    private static JFileChooser fileChooser;
	public static File chooseFile;  

	public AddPartsTop() {
		setLayout(new MigLayout("", "[100%]", "[grow][]"));
		initUI();
		Action();
	}
	@Override
	public void initUI() {
		panel = new JPanelTransparent();
		add(panel,"cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][][][grow][]", "[100%]"));
		goBack = new JLabel("返回");
		goBack.setToolTipText("返回备件列表");
		goBack.setFont(new Font("仿宋", Font.BOLD, 24));
		goBack.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(goBack, "cell 0 0,alignx center,aligny center");

		
		jt = new JLabel(">>");
		jt.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(jt, "cell 1 0,grow");
		
		addParts = new JLabel("添加备件");
		addParts.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(addParts, "cell 2 0,grow");
		
		inputParts = new JButton("导入备件信息");
		inputParts.setIcon(TopPanel.getIcon("plus.png",this));	
		inputParts.setFont(new Font("仿宋", Font.PLAIN, 15));
		panel.add(inputParts, "cell 4 0,alignx right,aligny center");
		
		panel1 = new JPanelTransparent();
		panel1.setBackground(Color.WHITE);
		
		add(panel1,"cell 0 1,grow");
		panel1.setLayout(new BorderLayout(0, 0));
		partsJs = new JSeparator();
		partsJs.setForeground(Color.GRAY);		
		panel1.add(partsJs,BorderLayout.CENTER);
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
		inputParts.addMouseListener(new MouseAdapter() {
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
			        	  String partCounts = sheet.getCell(3,i).getContents();
				          SpringUtil s = new SpringUtil();
			        	  RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 

			        	  List<Parts> parts =radarServiceImpl.getParts();
			        	  for(int j=0;j<parts.size();j++) {
			        		  
			        		  if((partsName==parts.get(j).getPartsName()||partsName.equals(parts.get(j).getPartsName()))
			        				  &&(radarType==parts.get(j).getRadarTypeId().getRadarTypeName()||radarType.equals(parts.get(j).getRadarTypeId().getRadarTypeName()))) {
						            JOptionPane.showMessageDialog(null,radarType+"的"+partsName+"已存在");
						            return;
			        		  }
			        	  }
			        	  
			        	  part.setPartsName(partsName);
			        	  part.setPartsCount(Integer.valueOf(partCounts));
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
			    		 }
			        }
			        }
				}
			});
	}
	
	public JLabel getGoBack() {
		return goBack;
	}
}
