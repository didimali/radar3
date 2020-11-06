package radar.Repository;

import org.springframework.data.repository.Repository;

import radar.Entity.User;

public interface UserRepository extends Repository<User,Integer>{

	void save(User u);


}
