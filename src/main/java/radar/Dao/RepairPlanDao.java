package radar.Dao;

import java.util.List;

import radar.Entity.RepairPlan;

public interface RepairPlanDao {

	List<RepairPlan> getRepairPlanResult(int managerId, String radarTypeName);

	List<RepairPlan> geRadarRepairPlanDate(int radarId);

}
