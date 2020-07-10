package radar.Repository;

import org.springframework.data.repository.Repository;

import radar.Entity.PartConsume;

public interface PartConsumeRepository extends Repository<PartConsume,Integer>{

	void save(PartConsume p);

}
