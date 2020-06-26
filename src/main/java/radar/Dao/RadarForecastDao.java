package radar.Dao;

import java.util.List;

import radar.Entity.RadarForecast;

public interface RadarForecastDao {

	List<RadarForecast> getRadarForecastResult(String managerName, String radarTypeName);

}
