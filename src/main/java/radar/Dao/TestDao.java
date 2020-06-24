package radar.Dao;

import java.util.List;

import radar.Entity.DynamicData;
import radar.Entity.Manager;
import radar.Entity.Radar;

public interface TestDao {

	List<Manager> getManagers();

	List<Radar> getRadars();

	List<Object> getPieData();

	List<DynamicData> getLineData();

}
