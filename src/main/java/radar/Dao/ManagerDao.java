package radar.Dao;

import java.util.List;

import radar.Entity.Manager;

public interface ManagerDao {

	List<Manager> getManagers();

	boolean deleteManager(String managerName);

	List<Manager> selectManager(String name2);

}
