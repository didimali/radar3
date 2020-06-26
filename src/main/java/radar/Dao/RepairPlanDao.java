package radar.Dao;

import java.util.List;

import radar.Entity.RepairPlan;

public interface RepairPlanDao {

	List<RepairPlan> getRepairPlanResult(String managerName, String radarTypeName);

}
