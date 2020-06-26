package radar.Dao;

import java.util.List;

import radar.Entity.Radar;

public interface RadarDao {

	List<Radar> getRadarCountByHealth();

	List<Object> getRadarHCount(int managerId);

	List<Object> getRadarCountByRadarHeath(String managerName, String radarTypeName);

	List<Radar> getRadarDetails(String managerName, String radarTypeName);

	List<Object> getRadarCountByRadarType(String managerName);

}
