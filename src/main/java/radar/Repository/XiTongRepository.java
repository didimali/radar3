package radar.Repository;

import org.springframework.data.repository.Repository;

import radar.Entity.Equip;
import radar.Entity.System;

public interface XiTongRepository extends Repository<System,Integer>{

	void save(System xiTong);

	void save(Equip equip);

}
