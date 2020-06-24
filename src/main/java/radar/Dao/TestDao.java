package radar.Dao;

import java.util.List;

public interface TestDao {

	List<Manager> getManagers();

	List<Radar> getRadars();

	List<Object> getPieData();

	List<DynamicData> getLineData();

}
