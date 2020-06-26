package radar.Dao;

import java.util.List;

import radar.Entity.DynamicData;
import radar.Entity.Manager;
import radar.Entity.Radar;
import radar.Entity.RadarForecast;
import radar.Entity.RadarHealth;
import radar.Entity.RepairPlan;

public interface RadarDao {

	List<RepairPlan> getRepairPlan(Integer id);

	List<RadarForecast> getFaultForecast(Integer id);

	List<RadarHealth> getRadarState(Integer id);

	List<Radar> getRadarsByType(String id);

	Integer countRadar(Integer id);

	Integer countHealth(Integer typeid, Integer health);

	
}
