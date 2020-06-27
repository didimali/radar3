package radar.Dao;

import java.util.List;

import radar.Entity.RepairContent;

public interface RepairContentDao {

	List<RepairContent> getRadarRepairContent(int radarId);

}
