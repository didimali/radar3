package radar.Dao;

import java.util.List;

import radar.Entity.RadarHealth;

public interface RadarHealthDao {

	List<RadarHealth> getTotalRadarHealthResult(int radarId);

	List<RadarHealth> getDataForRadarHiLine(int radarId);

}
