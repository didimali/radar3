package radar.Dao;

import java.util.List;

import radar.Entity.Equip;
import radar.Entity.PartConsume;
import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.Entity.RadarHealth;
import radar.Entity.System;

public interface AnalysisDao {

	Integer countRadarByTL(Integer typeid, Integer location, Integer health);

	List<Object> getRadarListByTL(Integer typeid, Integer location);

	List<Object> getFaultLocationType(Integer typeid, Integer location, String startDate, String endDate);

	List<System> getSystemName(String id);

	List<Equip> getEquipName(String id);

	int countFaultLocationType(Integer typeid, Integer location, String startDate, String endDate, String SearchKey);

	void save1(String id, int result);

	void change1(String id);

	void change2(String id);

	void save2(String id, int result);

	void faultForecast(String id, int i);

	String countNum(int health, int typeid);

	List<PartConsume> getPartsConsume(int integer, String startDate, String endDate);

	List<Parts> getParts(String id);

	List<Radar> getRadar(String typeid);

	List<RadarHealth> gethealthID();

	void save3(int result, int hid, int sysid);

	int countFaultNum(int radarid);
}
