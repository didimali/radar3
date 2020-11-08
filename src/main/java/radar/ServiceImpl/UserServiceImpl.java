package radar.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.SpringUtil;
import radar.Dao.UserDao;
import radar.Entity.User;
import radar.Repository.UserRepository;
import radar.Service.UserService;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserRepository userRepository;
	
	public Boolean addUser(User user) {
		try {
			userRepository.save(user);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//获取用户拉列表
	@Override
	public Object[] getUsersCombox(Object[] params) {
		List<User> list = new ArrayList<User>();
		list = userDao.getUsers();
		Object[] result = new Object[list.size()];
		for(int i=0;i<list.size();i++) {
			User m = list.get(i);
			result[i] = m.getUserName();
		}
		return result;
	}
	
	//获取密码
	@Override
	public Object[] getUsersPassWordCombox(Object[] params) {
		List<User> list = new ArrayList<User>();
		list = userDao.getUsers();
		Object[] result = new Object[list.size()];
		for(int i=0;i<list.size();i++) {
			User m = list.get(i);
			result[i] = m.getPassWord();
		}
		return result;
	}
	
	public boolean deleteUser(String userAccount) {
		return userDao.deleteUser(userAccount);
	}
	
	public Object[] selectPassWordByUserName(String choosenUserName) {
		List<User> list =userDao.selectPassWordByUserName(choosenUserName);
		Object[] o=null;
		if(list!=null&&list.size()>0) {
			User m = list.get(0);
			o = new Object[1];
			String passWord="";
			passWord = m.getPassWord();
			o[0]=passWord;
		}else {
			o = new Object[1];
			o[0]="";
		}
		return o;
	}
	
	public List<User> slectUsers(String name) {
		return userDao.selectPassWordByUserName(name);
	}
	
	public boolean updateUser(String currentUserAccount, String currentPsd, String originalUserAccount) {			
		return userDao.updateUser(currentUserAccount,currentPsd,originalUserAccount);
	}

	/**
	 * 根据用户账号查询用户信息
	 */
	@Override
	public Object[] selectUserByUserName(Object[] params) {
		boolean result = false;
		String userName = (String)params[0];
		String psd = (String)params[1];
		List<User> user = userDao.selectUserDaoByUserName(userName);
		if(user != null && user.size()==1 ) {
			result = user.get(0).getPassWord().equals(SpringUtil.MD5Encode(psd))? true:false;
		}
			
		Object[] r = {result};
		return r;
	}
}