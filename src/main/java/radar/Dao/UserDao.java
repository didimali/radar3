package radar.Dao;

import java.util.List;

import radar.Entity.User;

public interface UserDao {

	List<User> getUsers();

	boolean deleteUser(String userAccount);

	List<User> selectPassWordByUserName(String choosenUserName);

	boolean updateUser(String currentUserAccount, String currentPsd, String originalUserAccount);

	List<User> selectUserDaoByUserName(String userName);



}
