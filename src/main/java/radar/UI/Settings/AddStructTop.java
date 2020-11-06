package radar.UI.Settings;

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
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import jxl.Sheet;
import jxl.Workbook;
import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.Parts;
import radar.Entity.RadarType;
import radar.ServiceImpl.RadarServiceImpl;
import radar.Tools.Init;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Top.TopPanel;

public class AddStructTop extends JPanelTransparent implements Init{
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

	public AddStructTop() {
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
		
		addParts = new JLabel("编辑产品结构");
		addParts.setFont(new Font("仿宋", Font.PLAIN, 24));
		panel.add(addParts, "cell 2 0,grow");
		
		panel1 = new JPanelTransparent();
		panel1.setBackground(Color.WHITE);
		
		add(panel1,"cell 0 1,grow");
		panel1.setLayout(new BorderLayout(0, 0));
		partsJs = new JSeparator();
		partsJs.setForeground(Color.GRAY);		
		panel1.add(partsJs,BorderLayout.CENTER);
	}

	
	public JLabel getGoBack() {
		return goBack;
	}
	@Override
	public void Action() {
		// TODO Auto-generated method stub
		
	}
}
