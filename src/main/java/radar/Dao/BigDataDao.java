package radar.Dao;

import java.util.List;

import radar.Entity.Equip;
import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.Entity.RadarForecast;
import radar.Entity.RadarHealth;
import radar.Entity.SysOrEquipHealth;
import radar.Entity.System;


public interface BigDataDao {

	List<Radar> getRadarByID(String id);

	List<RadarHealth> getRadarHealth(String id);

	List<SysOrEquipHealth> getSEHealth(String id);

	List<System> getSystemName(String id);

	List<RadarHealth> getRadarHistory(String id);

	List<RadarForecast> getFaultForecast(String id);

	List<Equip> getEquipName(String id);

	List<Parts> getPartsName(String id);
	
	Integer getPartsNum(String radarid, String partid);

	List<Object> getPartsID(String id);



}
