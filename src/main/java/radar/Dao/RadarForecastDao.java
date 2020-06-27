package radar.Dao;

import java.util.List;

import radar.Entity.RadarForecast;

public interface RadarForecastDao {

	List<RadarForecast> getRadarForecastResult(int managerId, String radarTypeName);

	List<RadarForecast> getRadarForecastResult(int radarId);

}
