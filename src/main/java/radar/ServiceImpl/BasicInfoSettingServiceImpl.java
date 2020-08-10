package radar.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.BasicInfoSettingDao;
import radar.Entity.FaultRecord;
import radar.Entity.FaultType;

@Service("BasicInfoSettingServiceImpl")
public class BasicInfoSettingServiceImpl {
	@Autowired 
	BasicInfoSettingDao basicInfoSettingDao;
	public Object[][] getFaultRecord(Object[] params) {
		List<FaultRecord> list = new ArrayList<FaultRecord>();
		list = basicInfoSettingDao.getFaultRecord();
		Object[][] data  = new Object[list.size()][];
		for(int i=0;i<list.size();i++) {
			FaultRecord fr = list.get(i);
			String radar="";
			if(fr.getRadarId()!=null) {
				radar=fr.getRadarId().getRadarName();
			}
			String faultType="";
			if(fr.getFaultTypeId()!=null) {
				faultType=fr.getFaultTypeId().getFaultName();
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Object[] o = {fr.getFaultId(),i+1,radar,faultType,fr.getFaultReason(),sdf1.format(fr.getFaultDate())};
			data[i] = o;
		}
		return data;
	}
	/**
	 * 获取雷达故障类型
	 * @return
	 */
		public Object[] getFaultType(Object[] params) {
			Object[] result;
			List<FaultType> list = basicInfoSettingDao.getFaultType();
			result = new Object[list.size()+1];
			result[0] = "All";
			for(int i=1;i<result.length;i++)
				result[i] = list.get(i-1).getFaultName();
			return result;
		}
	public List<FaultType> getAllFaultType() {
		// TODO Auto-generated method stub
		return basicInfoSettingDao.getFaultType();
	}
	public List<FaultRecord> getFaultRecord() {
		// TODO Auto-generated method stub
		return basicInfoSettingDao.getFaultRecord();
	}
		
}
