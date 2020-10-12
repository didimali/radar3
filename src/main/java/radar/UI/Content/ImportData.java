package radar.UI.Content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;
import radar.Tools.ExtractDataFromSqlite;
import radar.Tools.Init;
import radar.Tools.LoadingDataClass;
import radar.UI.Components.ComboBox1;
import radar.UI.Components.JPanelTransparent;

public class ImportData extends ContentPanel implements Init{

	private static final long serialVersionUID = 1L;
	
	private JLabel titleLabel;
	private JSeparator radarSeparator;
	private JPanelTransparent title;
	private JPanelTransparent subtitle;
	
	private JTextField textField;
	
	private JPanelTransparent form;
	private JLabel managerLabel;
	private ComboBox1 managers;
	private JLabel radarLabel;
	private ComboBox1 radars;
	private JLabel fileUrl;
	private JPanelTransparent buttons;
	private JButton selectFiles;
	private JButton submitData;
	
	//文件选择器
	private JFileChooser fileChooser;	
	//选中的文件
	public File selectedFile;	
	private Integer returnValue =null;
	
	
	public ImportData() {		
		initUI();
	}
	
	public void initUI() {
		
		initContentTop();
		contentTop.add(title, "cell 0 0,grow");
		contentTop.add(subtitle, "cell 0 1,grow");
		
		contentBody.setLayout(new MigLayout("", "[100%]", "[70%][20%]"));
		initForm();
		initButtons();
		
		
		Action();
	}
	
	private void initContentTop() {
		contentTop.setLayout(new MigLayout("", "[100%]", "[100%][]"));
		
		title = new JPanelTransparent();	
		title.setLayout(new MigLayout("", "[]", "[100%]"));
		
		subtitle = new JPanelTransparent();
		subtitle.setLayout(new BorderLayout(0, 0));
		
		titleLabel = new JLabel("导入雷达数据");
		titleLabel.setFont(new Font("仿宋", Font.BOLD, 24));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);	
		title.add(titleLabel, "cell 0 0,growx,aligny center");
		
		
		radarSeparator = new JSeparator();
		radarSeparator.setForeground(Color.BLACK);
		subtitle.add(radarSeparator,BorderLayout.CENTER);
	}

	private void initButtons() {
		buttons = new JPanelTransparent();
		contentBody.add(buttons, "cell 0 1,grow");
		buttons.setLayout(new MigLayout("", "[grow][100px][60px][100px][grow]", "[100%]"));
		
		selectFiles = new JButton("选 择 文 件");
		selectFiles.setFont(new Font("仿宋", Font.BOLD, 16));
		buttons.add(selectFiles, "cell 1 0");
		
		submitData = new JButton("上 传 数 据");
		submitData.setFont(new Font("仿宋", Font.BOLD, 16));
		buttons.add(submitData, "cell 3 0");
	}

	private void initForm() {
		form = new JPanelTransparent();
		contentBody.add(form, "cell 0 0,grow");
		form.setLayout(new MigLayout("", "[grow][][350px,grow][grow]", "[40px][][20px][][20px][]"));
		
		managerLabel = new JLabel("部 队 编 号");
		managerLabel.setFont(new Font("仿宋", Font.BOLD, 16));
		form.add(managerLabel, "cell 1 1,alignx trailing");
		
		managers = new ComboBox1("ManagerServiceImpl", "getAllManger", null);
		managers.init();
		managers.setMaximumRowCount(6);
		form.add(managers, "cell 2 1,growx");
		
		radarLabel = new JLabel("雷 达 编 号");
		radarLabel.setFont(new Font("仿宋", Font.BOLD, 16));
		form.add(radarLabel, "cell 1 3,alignx trailing");
		
		radars = new ComboBox1("RadarServiceImpl", "getRadars", null);
		radars.init();
		form.add(radars, "cell 2 3,growx");
		
		fileUrl = new JLabel("文 件 地 址");
		fileUrl.setFont(new Font("仿宋", Font.BOLD, 16));
		form.add(fileUrl, "cell 1 5,alignx trailing");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("仿宋", Font.PLAIN, 16));
		form.add(textField, "cell 2 5,growx");
		textField.setColumns(6);
	}
	
	@Override
	public void Action() {
		//部队下拉框事件
		managers.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					int managerId = (int) managers.resultData[managers.getSelectedIndex()][0];
					Object[] params = {managerId};
					LoadingDataClass s = new LoadingDataClass(radars,"RadarServiceImpl", "getRadarsByManagerId", params);
					s.execute();
				}
			}
		});
		//选择按钮事件
		selectFiles.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(radars.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "请选择需要导入数据的雷达", 
							"警告",JOptionPane.WARNING_MESSAGE);
				}
				else {
					importExcel();
					 //弹出一个文件选择提示框
			        if (returnValue == fileChooser.APPROVE_OPTION) {
			        //当用户选择文件后获取文件路径
			        selectedFile = fileChooser.getSelectedFile();
			        textField.setText(selectedFile.getPath());
			        }
				}
			}
		});	
		//上传数据按钮事件
		submitData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(selectedFile == null) {
					JOptionPane.showMessageDialog(null, "请选择相应的数据文件", 
							"提示",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					int radarId = (int) radars.resultData[radars.getSelectedIndex()][0];
					ExtractDataFromSqlite edfs = new ExtractDataFromSqlite(radarId,selectedFile.getPath());
					edfs.execute();
				}
			}
		});
	}
	//弹出文件选择框
	private int importExcel() {
		 fileChooser = new JFileChooser();             
	     //过滤Excel文件，只寻找以xls结尾的Excel文件，如果想过滤word文档也可以写上doc
	     FileNameExtensionFilter filter = new FileNameExtensionFilter("Data Base File","db");
	     fileChooser.setFileFilter(filter);   	          
	     returnValue = fileChooser.showOpenDialog(setDialogIcon("folder.png"));    
	     return returnValue;
	}
	//设置会话框右上角图标
	private JFrame setDialogIcon(String iconName) {
		JFrame f = new JFrame();		
	     InputStream inputStream=this.getClass().getResourceAsStream("/images/"+iconName) ;
		 try {
			 BufferedImage bi=ImageIO.read(inputStream);
			 Image im=(Image)bi;
			 //设置右上角图标
	       	 f.setIconImage(im);
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 return f;
	}

}
