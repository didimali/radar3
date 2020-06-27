package radar.Dao;

import java.util.List;

import radar.Entity.SysOrEquipHealth;

public interface SysOrEquipHealthDao {

	List<SysOrEquipHealth> getRadarHealthResultContent(int healthResultId);

}
