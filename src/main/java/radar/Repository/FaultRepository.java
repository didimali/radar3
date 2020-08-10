package radar.Repository;

import org.springframework.data.repository.Repository;

import radar.Entity.FaultRecord;


public interface FaultRepository extends Repository<FaultRecord,Integer>{

	void save(FaultRecord fault);

}
