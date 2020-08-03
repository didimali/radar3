package radar.SwingWorker;

import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import radar.SpringUtil;
import radar.Entity.FaultRecord;
import radar.Entity.Parts;
import radar.ServiceImpl.FaultRecordServiceImpl;
import radar.ServiceImpl.PartsServiceImpl;

public class SwingWorkerForParts extends SwingWorker<Boolean,Void>{
	private Parts parts;
	@SuppressWarnings("static-access")
	protected Boolean doInBackground() throws Exception {
		Boolean result = false;
		SpringUtil s = new SpringUtil();
		PartsServiceImpl partsServiceImpl = (PartsServiceImpl) s.getBean("PartsServiceImpl");
		result = partsServiceImpl.add(getParts());
		return result;
	}
	
	protected void done() {
		try {
			Boolean addResult = get();
			if(addResult) {
				JOptionPane.showMessageDialog(null,"新建备件成功","提示",JOptionPane.PLAIN_MESSAGE);	

			}					
			else {
				JOptionPane.showMessageDialog(null,"新建备件失败","提示",JOptionPane.PLAIN_MESSAGE);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public Parts getParts() {
		return this.parts;
	}
	public void setParts(Parts parts) {
		this.parts = parts;
	}
}
