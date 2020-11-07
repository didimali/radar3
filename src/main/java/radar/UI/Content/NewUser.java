package radar.UI.Content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.Manager;
import radar.Entity.User;
import radar.ServiceImpl.ManagerServiceImpl;
import radar.ServiceImpl.UserServiceImpl;
import radar.SwingWorker.SwingWorkerForNewManager;
import radar.SwingWorker.SwingWorkerForUser;
import radar.Tools.Init;
import radar.UI.Components.ComboBox;
import radar.UI.Components.JPanelTransparent;
import radar.UI.Top.TopPanel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class NewUser extends ContentPanel implements Init{
	private JLabel UserInfo;
	private JLabel userName;
	private JComboBox inputName;
	private JLabel passWord;
	private JComboBox inputPassword;
	private String userName2;


	private JButton add;
	private JButton update;
	private JButton delete;
	private JSeparator userSeparator;
	private JPanelTransparent title;
	private JPanelTransparent subtitle;
   public NewUser() {
	   initUI();
	   Action();
  }
   public void initUI() {
	   initContentTop();
		contentTop.add(title, "cell 0 0");
		contentTop.add(subtitle, "cell 0 1,growx");
		
		initContentBody();
		
		contentBody.add(userName, "cell 1 1,alignx trailing");
		contentBody.add(inputName, "cell 2 1,growx");
		contentBody.add(passWord, "cell 1 2,alignx trailing");
		contentBody.add(inputPassword, "cell 2 2,growx");
		initContentFoot();
		contentFoot.add(add, "cell 1 1,grow");
		contentFoot.add(update, "cell 3 1,grow");
		contentFoot.add(delete, "cell 5 1,grow");
	   
   }
   /**
	 * 添加内容面板头部
	 */
	public void initContentTop() {
		contentTop.setLayout(new MigLayout("", "[100%]", "[100%][]"));
		
		title = new JPanelTransparent();	
		title.setLayout(new MigLayout("", "[]", "[100%]"));
		
		subtitle = new JPanelTransparent();
		subtitle.setLayout(new BorderLayout(0, 0));
		
		UserInfo = new JLabel("用户管理");
		UserInfo.setFont(new Font("仿宋", Font.BOLD, 24));
		UserInfo.setHorizontalAlignment(SwingConstants.CENTER);		
		title.add(UserInfo, "cell 0 0,growx,aligny center");
		
		userSeparator = new JSeparator();
		userSeparator.setForeground(Color.GRAY);		
		subtitle.add(userSeparator,BorderLayout.CENTER);
	}

	/**
	 * 添加内容面板躯干
	 */
	public void initContentBody() {	
		contentBody.setLayout(new MigLayout("", "[25%,grow][10%][40%,grow][25%]", "[grow][grow][][grow][grow]"));
		userName = new JLabel("用户名：");
		userName.setFont(new Font("仿宋", Font.PLAIN, 16));
		inputName = new ComboBox("UserServiceImpl", "getUsersCombox",null);
		inputName.setFont(new Font("仿宋", Font.PLAIN, 16));
		inputName.setEditable(true);
			
		
		passWord = new JLabel("密码：");
		passWord.setFont(new Font("仿宋", Font.PLAIN, 16));
		inputPassword = new ComboBox("UserServiceImpl", "getUsersPassWordCombox", null);
		inputPassword.setFont(new Font("仿宋", Font.PLAIN, 16));
		inputPassword.setEditable(true);

	}
	
	/**
	 * 添加内容面板底部
	 */
	public void initContentFoot() {
	contentFoot.setLayout(new MigLayout("", "[10%][grow][10%][grow][10%][grow][10%]", "[10%][80%][10%]"));
	
	add = new JButton("新建");
	
	add.setIcon(TopPanel.getIcon("plus.png",this));	
	add.setFont(new Font("仿宋", Font.PLAIN, 15));
	
	update = new JButton("修改");
	update.setIcon(TopPanel.getIcon("edit1.png",this));
	update.setFont(new Font("仿宋", Font.PLAIN, 15));
	
	delete = new JButton("删除");
	
	delete.setIcon(TopPanel.getIcon("delete.png",this));	
	delete.setFont(new Font("仿宋", Font.PLAIN, 15));

		
	}
	@Override
	public void Action() {
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			String name =	inputName.getSelectedItem().toString();
		    UserServiceImpl userServiceImpl = (UserServiceImpl) SpringUtil.getBean("UserServiceImpl");
		    List<User>  list =userServiceImpl.slectUsers(name);
				if(list!=null&&list.size()>0) {
					JOptionPane.showMessageDialog(null,"该用户已存在","提示",JOptionPane.WARNING_MESSAGE);
					return;
				}else {
					try {
						if(inputPassword.getSelectedItem().toString().equals("")) {
							JOptionPane.showMessageDialog(null,"请重置密码","提示",JOptionPane.WARNING_MESSAGE);
							return;	
						}else {
							User u = new User();
							u.setUserStatus(0);
							u.setUserName(inputName.getSelectedItem().toString());
							u.setPassWord(inputPassword.getSelectedItem().toString());
							SwingWorkerForUser s1 = new SwingWorkerForUser();
							s1.setUser(u);
							s1.execute();
						}
					}
					catch(Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		inputName.addItemListener(new ItemListener() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void itemStateChanged(ItemEvent e) {	
				if(inputName.getSelectedItem().toString().equals("")) {
					String[] pw = {""};
					inputPassword.setModel(new DefaultComboBoxModel(pw));
				}else if(!inputName.getSelectedItem().toString().equals("")) {
				    UserServiceImpl userServiceImpl = (UserServiceImpl) SpringUtil.getBean("UserServiceImpl");
					String choosenUserName =inputName.getSelectedItem().toString();
					Object[]  concreteUserPassWord = userServiceImpl.selectPassWordByUserName(choosenUserName);
					Object[] resultdata =new String[concreteUserPassWord.length];
					resultdata=concreteUserPassWord;
					inputPassword.setModel(new DefaultComboBoxModel(resultdata));
				}
					if(e.getStateChange() ==ItemEvent.DESELECTED) {
						userName2 = (String) e.getItem();
					}
			}
			
		});

			update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    UserServiceImpl userServiceImpl = (UserServiceImpl) SpringUtil.getBean("UserServiceImpl");
				String inputNameModify=inputName.getSelectedItem().toString();
				String inputPasswordModify=inputPassword.getSelectedItem().toString();
				Object[] obj = userServiceImpl.getUsersCombox(null);
				Boolean flag=true;
				//获取修改前的值
				String userName3 =userName2;
				if(userName3==null) {
					JOptionPane.showMessageDialog(null, "请更新用户名", "提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}else {
					int num = JOptionPane.showConfirmDialog(null, "是否修改"+userName3+"的信息？", "提示",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				    switch(num) {
				    case JOptionPane.YES_OPTION:
						boolean result = userServiceImpl.updateUser(inputNameModify,inputPasswordModify,userName3);
						if(result) {
							JOptionPane.showMessageDialog(null, "更新成功", "提示", JOptionPane.INFORMATION_MESSAGE);

						}
				    case JOptionPane.NO_OPTION:
				    	break;
				    case JOptionPane.CANCEL_OPTION:
				    	break;
				    }
				}
			}
		});
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String receiveName =inputName.getSelectedItem().toString();
				boolean flag=false;
				if(receiveName.equals("")||inputPassword.getSelectedItem().toString().equals("")) {
						JOptionPane.showMessageDialog(null,"请选择要删除的用户","提示",JOptionPane.WARNING_MESSAGE);
						return;
					}else {
						flag=true;
					}
				if(flag) {
					int num = JOptionPane.showConfirmDialog(null, "是否删除？", "提示",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				    switch(num) {
				    case JOptionPane.YES_OPTION:
				    	UserServiceImpl userServiceImpl = (UserServiceImpl) SpringUtil.getBean("UserServiceImpl");
						boolean  result =	userServiceImpl.deleteUser(receiveName);
						if(result) {
							contentBody.remove(inputName);
							inputName = new ComboBox("UserServiceImpl", "getUsersCombox",null);
							contentBody.add(inputName, "cell 2 1,growx");
							inputName.validate();
							inputName.repaint();
							JOptionPane.showMessageDialog(null, "已成功删除", "提示", JOptionPane.INFORMATION_MESSAGE);
						}
				    case JOptionPane.NO_OPTION:
				    	break;
				    case JOptionPane.CANCEL_OPTION:
				    	break;
				    }
				
				}
			}
		});
	}
}
