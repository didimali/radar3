package radar.Repository;

import org.springframework.data.repository.Repository;

import radar.Entity.Manager;


public interface ManagerRepository extends Repository<Manager,Integer> {
	void save(Manager m);
}
