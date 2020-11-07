package radar.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.UserDao;
import radar.Entity.Manager;
import radar.Entity.Parts;
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
		public boolean deleteUser(String receiveName) {
			return userDao.deleteUser(receiveName);
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
			// TODO Auto-generated method stub
			return userDao.selectPassWordByUserName(name);
		}
		public boolean updateUser(String inputNameModify, String inputPasswordModify, String userName3) {
			
			return userDao.updateUser(inputNameModify,inputPasswordModify,userName3);
		}
}