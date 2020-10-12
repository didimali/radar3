package radar.Tools;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import radar.SpringUtil;
import radar.ServiceImpl.XiTongServiceImpl;
/**
  * 从sqlite数据库提取数据存入mysql数据中
 * @author madi
 *
 */
public class ExtractDataFromSqlite extends SwingWorker<Boolean,Void>{
	
	String url;
	int radarId;
	
	public ExtractDataFromSqlite(int radarId, String url){
		this.url = url;
		this.radarId = radarId;
	}

	@Override
	protected Boolean doInBackground() throws Exception {
		try {
			XiTongServiceImpl xt = (XiTongServiceImpl) SpringUtil.getBean("XiTongServiceImpl");
			//数据尚未插入，解析，插入数据
			ConnectSqliteDataBase csd = new ConnectSqliteDataBase(url);
			HashMap<String,List> map = csd.selectAllData();
			if(map.containsKey("Records"))
				xt.AddDynamicData(radarId,map.get("Records"));
			if(map.containsKey("Faults"))
				xt.AddFaultRecord(radarId,map.get("Faults"));
			return true;
		}
		catch(Exception e) {
			return false;
		}
		
	}
	
	@Override
	protected void done() {
		try {
			boolean result = get();
			if(result)
				JOptionPane.showMessageDialog(null,"数据导入成功","提示",JOptionPane.PLAIN_MESSAGE);
			else
				JOptionPane.showMessageDialog(null,"数据导入失败","提示",JOptionPane.PLAIN_MESSAGE);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
