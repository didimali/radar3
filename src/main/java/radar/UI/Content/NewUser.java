package radar.UI.Content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.User;
import radar.ServiceImpl.UserServiceImpl;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewUser extends ContentPanel implements Init{
	
	private static final long serialVersionUID = 1L;
	private JLabel UserInfo;
	private JLabel userName;
	private JComboBox inputName;
	private JLabel passWord;
	private JPasswordField inputPassword;
	
	private String dUserAccount;
	private String sUserAccount;
	private String userName2;


	private JButton add;
	private JButton update;
	private JButton delete;
	private JSeparator userSeparator;
	private JPanelTransparent title;
	private JPanelTransparent subtitle;
	private JLabel lblNewLabel;
	private JPasswordField newPassword;
	
	UserServiceImpl userServiceImpl;
	
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
		contentBody.add(lblNewLabel, "cell 1 3,alignx trailing");
		
		newPassword = new JPasswordField();
		newPassword.setFont(new Font("仿宋", Font.PLAIN, 16));
		newPassword.setHorizontalAlignment(SwingConstants.CENTER);
		contentBody.add(newPassword, "cell 2 3,growx");
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
		contentBody.setLayout(new MigLayout("", "[25%,grow][10%][40%,grow][25%]", "[grow][grow][][][grow][grow]"));
		userName = new JLabel("账号：");
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		userName.setFont(new Font("仿宋", Font.PLAIN, 16));
		inputName = new ComboBox("UserServiceImpl", "getUsersCombox",null);
		inputName.setFont(new Font("仿宋", Font.PLAIN, 16));
		inputName.setEditable(true);
			
		
		passWord = new JLabel("密码：");
		passWord.setHorizontalAlignment(SwingConstants.CENTER);
		passWord.setFont(new Font("仿宋", Font.PLAIN, 16));
		inputPassword = new JPasswordField();		
		inputPassword.setHorizontalAlignment(SwingConstants.CENTER);
		inputPassword.setFont(new Font("仿宋", Font.PLAIN, 16));
		
		lblNewLabel = new JLabel("新密码：");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("仿宋", Font.PLAIN, 16));

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
		
		//用户添加事件
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addUser();
			}
		});
		
		inputName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
			}
		});

		//用户下拉框点击事件
		inputName.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {	
				if(e.getStateChange() ==ItemEvent.SELECTED) {
					sUserAccount = (String) e.getItem();
				}
			}
			
		});

		//用户修改事件
		update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateUser();			    
			}			
		});
		
		//用户删除事件
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deleteUser();				
			}			
		});
	}
	
	/**
	 * 新建用户账号
	 */
	private void addUser() {
		
		String userAccount = inputName.getSelectedItem().toString();
		String psd = new String(inputPassword.getPassword());
	    userServiceImpl = (UserServiceImpl) SpringUtil.getBean("UserServiceImpl");
	    
	    //检查该账号是否存在
	    List<User>  list =userServiceImpl.slectUsers(userAccount);
		if(list!=null&&list.size()>0) {
			JOptionPane.showMessageDialog(null,"该账号已存在","提示",JOptionPane.WARNING_MESSAGE);
			return;
		}
		else {
			try {
				if(psd.length() == 0) {
					JOptionPane.showMessageDialog(null,"密码不能为空，请输入密码","提示",JOptionPane.WARNING_MESSAGE);
					return;	
				}else {
					User u = new User();
					u.setUserName(inputName.getSelectedItem().toString());
					u.setPassWord(SpringUtil.MD5Encode(psd));
					SwingWorkerForUser s1 = new SwingWorkerForUser();
					s1.setUser(u);
					s1.execute();
					refreshPasswordFields();
				}
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		}		
	}
	/**
	 * 更新用户账号信息
	 */
	private void updateUser() {
		userServiceImpl = (UserServiceImpl) SpringUtil.getBean("UserServiceImpl");		
		//旧用户账号
		String originalUserAccount = sUserAccount;
		//新用户账号
		String currentUserAccount = inputName.getSelectedItem().toString();
		//旧账号密码
		String originalPsd = new String(inputPassword.getPassword());
		//新账号密码
		String currentPsd = new String(newPassword.getPassword());
		//新账号名为空，提示用户输入账号
		if(currentUserAccount.length() ==0) {
			JOptionPane.showMessageDialog(null, "请输入用户账号", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		//验证旧账号的密码是否正确
		if(originalPsd.length() ==0) {
			JOptionPane.showMessageDialog(null, "请输入用户旧密码", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		Object[] params = {originalUserAccount,originalPsd};
		boolean conformed = (boolean) userServiceImpl.selectUserByUserName(params)[0];
		if(!conformed) {
			JOptionPane.showMessageDialog(null, "旧密码错误，请重新输入！", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(currentPsd.length() ==0) {
			JOptionPane.showMessageDialog(null, "请输入用户新密码", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		int num = JOptionPane.showConfirmDialog(null, "是否修改"+originalUserAccount+"的信息？",
				"提示",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	    switch(num) {
	    case JOptionPane.YES_OPTION:
			boolean result = userServiceImpl.updateUser(currentUserAccount,SpringUtil.MD5Encode(currentPsd),originalUserAccount);
			if(result) {
				JOptionPane.showMessageDialog(null, "账号信息更新成功", "提示", JOptionPane.INFORMATION_MESSAGE);
				refreshPasswordFields();

			}
	    case JOptionPane.NO_OPTION:
	    	break;
	    case JOptionPane.CANCEL_OPTION:
	    	break;
	    }
	}

	/**
	 * 删除用户账号
	 */
	private void deleteUser() {
		//用户账号
		String userAccount =inputName.getSelectedItem().toString();
		//账号密码
		String psd = new String(inputPassword.getPassword());
		Object[] params = {userAccount,psd};
		boolean conformed = (boolean) userServiceImpl.selectUserByUserName(params)[0];
		if(!conformed) {
			JOptionPane.showMessageDialog(null, "账号密码错误，请重新输入！", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int num = JOptionPane.showConfirmDialog(null, "是否删除账号"+userAccount+"？", "提示",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	    switch(num) {
	    case JOptionPane.YES_OPTION:
			boolean  result =	userServiceImpl.deleteUser(userAccount);
			if(result) {
				contentBody.remove(inputName);
				inputName = new ComboBox("UserServiceImpl", "getUsersCombox",null);
				contentBody.add(inputName, "cell 2 1,growx");
				inputName.validate();
				inputName.repaint();
				JOptionPane.showMessageDialog(null, "已成功删除", "提示", JOptionPane.INFORMATION_MESSAGE);
				refreshPasswordFields();
			}
	    case JOptionPane.NO_OPTION:
	    	break;
	    case JOptionPane.CANCEL_OPTION:
	    	break;
	    }	
	}
	
	/**
	 * 清空密码框内容
	 */
	private void refreshPasswordFields() {
		inputPassword.setText(null);
		newPassword.setText(null);
	}
}
