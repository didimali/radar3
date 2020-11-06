package radar.SwingWorker;

import java.util.concurrent.ExecutionException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import radar.SpringUtil;
import radar.Entity.Manager;
import radar.Entity.User;
import radar.ServiceImpl.ManagerServiceImpl;
import radar.ServiceImpl.UserServiceImpl;

public class SwingWorkerForUser extends SwingWorker<Boolean,Void>{
	private User user;		


	@SuppressWarnings("static-access")
	protected Boolean doInBackground() throws Exception {
		Boolean result = false;
		SpringUtil s = new SpringUtil();
		UserServiceImpl userServiceImpl = (UserServiceImpl) s.getBean("UserServiceImpl");
		result = userServiceImpl.addUser(getUser());
		return result;
	}
	
	protected void done() {
		try {
			Boolean addResult = get();
			if(addResult) {
				JOptionPane.showMessageDialog(null,"新建用户成功","提示",JOptionPane.PLAIN_MESSAGE);	

			}					
			else {
				JOptionPane.showMessageDialog(null,"新建用户失败","提示",JOptionPane.PLAIN_MESSAGE);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
