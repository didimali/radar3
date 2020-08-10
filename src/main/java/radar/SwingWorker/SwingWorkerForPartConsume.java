package radar.SwingWorker;

import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import radar.SpringUtil;
import radar.Entity.FaultRecord;
import radar.Entity.PartConsume;
import radar.ServiceImpl.FaultRecordServiceImpl;
import radar.ServiceImpl.PartsServiceImpl;

public class SwingWorkerForPartConsume extends SwingWorker<Boolean,Void>{
	private PartConsume partConsume;
	@SuppressWarnings("static-access")
	protected Boolean doInBackground() throws Exception {
		Boolean result = false;
		SpringUtil s = new SpringUtil();
		PartsServiceImpl partsServiceImpl = (PartsServiceImpl) s.getBean("PartsServiceImpl");
		result = partsServiceImpl.add(getPartConsume());
		return result;
	}
	
	protected void done() {
		try {
			Boolean addResult = get();
			if(addResult) {
				JOptionPane.showMessageDialog(null,"新建备件消耗记录成功","提示",JOptionPane.PLAIN_MESSAGE);	

			}					
			else {
				JOptionPane.showMessageDialog(null,"新建备件消耗记录失败","提示",JOptionPane.PLAIN_MESSAGE);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public PartConsume getPartConsume() {
		return this.partConsume;
	}
	public void setPartConsume(PartConsume partConsume) {
		this.partConsume = partConsume;
	}
}
