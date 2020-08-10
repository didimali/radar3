package radar.Tools;

import java.util.List;

import javax.swing.SwingWorker;
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
	}

	@Override
	protected Boolean doInBackground() throws Exception {
		ConnectSqliteDataBase csd = new ConnectSqliteDataBase(url);
		List<Records> list1 = csd.selectAllRecords();
		List<Faults> list2 = csd.selectAllFaults();
		return null;
	}
	
	@Override
	protected void done() {
		
	}

}
