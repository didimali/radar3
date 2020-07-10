package radar.Dao;

import java.util.List;

import radar.Entity.Equip;
import radar.Entity.System;

public interface AnalysisDao {

	Integer countRadarByTL(Integer typeid, Integer location, Integer health);

	List<Object> getRadarListByTL(Integer typeid, Integer location);

	List<Object> getFaultLocationType(Integer typeid, Integer location, String startDate, String endDate);

	List<System> getSystemName(String id);

	List<Equip> getEquipName(String id);

	int countFaultLocationType(Integer typeid, Integer location, String startDate, String endDate, String SearchKey);

}
