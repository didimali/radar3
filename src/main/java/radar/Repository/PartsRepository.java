package radar.Repository;

import org.springframework.data.repository.Repository;

import radar.Entity.Parts;

public interface PartsRepository extends Repository<Parts,Integer>{
	void save(Parts p);

}
