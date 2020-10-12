package radar.Dao;

import java.util.List;
import radar.Entity.Equip;
import radar.Entity.System;

public interface XiTongDao {

	List<System> getXiTongInfo();

	List<Equip> getEquipmentInfo();

}
