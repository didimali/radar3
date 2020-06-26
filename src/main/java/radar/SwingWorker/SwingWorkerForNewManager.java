package radar.SwingWorker;

import java.util.concurrent.ExecutionException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import radar.SpringUtil;
import radar.Entity.Manager;
import radar.ServiceImpl.ManagerServiceImpl;
import radar.UI.Components.ManagerCombox;


public class SwingWorkerForNewManager extends SwingWorker<Boolean,Void>{
	private Manager manager;		
	@SuppressWarnings({ "unused", "rawtypes" })
	private JComboBox managerNameComboBox;

	@SuppressWarnings("static-access")
	protected Boolean doInBackground() throws Exception {
		Boolean result = false;
		SpringUtil s = new SpringUtil();
		ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) s.getBean("ManagerServiceImpl");
		result = managerServiceImpl.addManager(getManager());
		return result;
	}
	
	protected void done() {
		try {
			Boolean addResult = get();
			if(addResult) {
				JOptionPane.showMessageDialog(null,"新建部队成功","提示",JOptionPane.PLAIN_MESSAGE);	

			}					
			else {
				JOptionPane.showMessageDialog(null,"新建部队失败","提示",JOptionPane.PLAIN_MESSAGE);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public Manager getManager() {
		return this.manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
