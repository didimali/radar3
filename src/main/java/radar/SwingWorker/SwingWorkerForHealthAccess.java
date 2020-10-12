package radar.SwingWorker;

import java.util.concurrent.ExecutionException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import radar.SpringUtil;
import radar.Entity.Manager;
import radar.Entity.Radar;
import radar.ServiceImpl.AnalysisServiceImpl;
import radar.UI.AcuteForecast.CBody2;


public class SwingWorkerForHealthAccess extends SwingWorker<Boolean,Void>{
	private int radar;		
	@SuppressWarnings({ "unused", "rawtypes" })
	private JComboBox managerNameComboBox;
	private CBody2 body2;
	@SuppressWarnings("static-access")
	protected Boolean doInBackground() throws Exception {
		Boolean result = false;
		SpringUtil s = new SpringUtil();
		AnalysisServiceImpl analysisServiceImpl = (AnalysisServiceImpl) s.getBean("AnalysisServiceImpl");
		//result = analysisServiceImpl.health(getRadarid());
		result = analysisServiceImpl.doBigDataAnalysis();
		return result;
	}
	
	protected void done() {
		try {
			Boolean addResult = get();
			if(addResult) {
				JOptionPane.showMessageDialog(null,"评估完毕","提示",JOptionPane.PLAIN_MESSAGE);	
			}					
			else {
				JOptionPane.showMessageDialog(null,"评估失败","提示",JOptionPane.PLAIN_MESSAGE);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public int getRadarid() {
		return this.radar;
	}
	public void setRadarid(int radarid) {
		this.radar = radarid;
	}
}
