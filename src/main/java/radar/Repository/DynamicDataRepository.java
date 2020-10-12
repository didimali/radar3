package radar.Repository;

import org.springframework.data.repository.Repository;

import radar.Entity.DynamicData;

public interface DynamicDataRepository extends Repository<DynamicData,Integer>{
	
	void save(DynamicData dynamicData);

}
