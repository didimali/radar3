package radar.Dao;

import java.util.List;

import radar.Entity.User;

public interface UserDao {

	List<User> getUsers();

	boolean deleteUser(String receiveName);

	List<User> selectPassWordByUserName(String choosenUserName);

	boolean updateUser(String inputNameModify, String inputPasswordModify, String userName3);



}
