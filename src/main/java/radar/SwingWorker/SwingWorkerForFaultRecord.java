package radar.SwingWorker;

import java.util.concurrent.ExecutionException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import radar.SpringUtil;
import radar.Entity.FaultRecord;
import radar.Entity.Manager;
import radar.ServiceImpl.FaultRecordServiceImpl;
import radar.ServiceImpl.ManagerServiceImpl;

public class SwingWorkerForFaultRecord extends SwingWorker<Boolean,Void>{
	private FaultRecord faultRecord;		

	@SuppressWarnings("static-access")
	protected Boolean doInBackground() throws Exception {
		Boolean result = false;
		SpringUtil s = new SpringUtil();
		FaultRecordServiceImpl faultRecordServiceImpl = (FaultRecordServiceImpl) s.getBean("FaultRecordServiceImpl");
		result = faultRecordServiceImpl.add(getFaultRecord());
		return result;
	}
	
	protected void done() {
		try {
			Boolean addResult = get();
			if(addResult) {
				JOptionPane.showMessageDialog(null,"新建故障记录成功","提示",JOptionPane.PLAIN_MESSAGE);	

			}					
			else {
				JOptionPane.showMessageDialog(null,"新建故障记录失败","提示",JOptionPane.PLAIN_MESSAGE);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public FaultRecord getFaultRecord() {
		return this.faultRecord;
	}
	public void setFaultRecord(FaultRecord faultRecord) {
		this.faultRecord = faultRecord;
	}
}
