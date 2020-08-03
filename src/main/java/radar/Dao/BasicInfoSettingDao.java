package radar.Dao;

import java.util.List;

import radar.Entity.FaultRecord;
import radar.Entity.FaultType;

public interface BasicInfoSettingDao {

	List<FaultRecord> getFaultRecord();

	List<FaultType> getFaultType();

}
