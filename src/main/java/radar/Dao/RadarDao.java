package radar.Dao;

import java.util.List;

import radar.Entity.DynamicData;
import radar.Entity.Manager;
import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.Entity.RadarForecast;
import radar.Entity.RadarHealth;
import radar.Entity.RadarType;
import radar.Entity.RepairPlan;

public interface RadarDao {

	List<RepairPlan> getRepairPlan(Integer id);

	List<RadarForecast> getFaultForecast(Integer id);

	List<RadarHealth> getRadarState(Integer id);

	List<Radar> getRadarsByType(String id);

	Integer countRadar(Integer id);

	Integer countHealth(Integer typeid, Integer health);

	List<Radar> getRadarCountByHealth();

	List<Object> getRadarHCount(int managerId);

	List<Object> getRadarCountByRadarHeath(int managerId, String radarTypeName);

	List<Radar> getRadarDetails(int managerId, String radarTypeName);

	List<Object> getRadarCountByRadarType(int managerId);
	List<Radar> getRadars();

	List<RadarType> selectRadarType(String name1);

	boolean deleteRadar(String radarName);

	List<RadarType> getRadarTypes();

	List<Parts> getParts();

	List<Radar> selectRadar(String choosenRadarName);

	List<Radar> getRadarsByManagerId(int managerId);


}
