package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.UserDao;
import radar.Entity.User;

@Repository("UserDaoImpl")
public class UserDaoImpl implements UserDao{
	
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	
	@Override
	public List<User> getUsers() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from user where user_status='0'";
		Query query = em.createNativeQuery(sql,User.class);
		@SuppressWarnings("unchecked")
		List<User> list = query.getResultList();
		em.close();
		return list;
	}
	
	public boolean deleteUser(String userAccount) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = " update user set user_status=1 where user_name=:userAccount";
			Query query = em.createNativeQuery(selectSql);
			query.setParameter("userAccount", userAccount);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return true;

	}
	
	@SuppressWarnings("unchecked")
	public List<User> selectPassWordByUserName(String choosenUserName){
		EntityManager em = emf.createEntityManager();
		String sql = "select * from user where user_name='"+choosenUserName+"' and user_status='0'";
		Query query = em.createNativeQuery(sql,User.class);		
		List<User> list = query.getResultList();
		em.close();
		return list;
	};
	
	/**
	 * 
	 */
	public boolean updateUser(String currentUserAccount, String currentPsd, String originalUserAccount) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = " update user set user_name=:currentUserAccount,pass_word=:currentPsd where user_name=:originalUserAccount";
			Query query = em.createNativeQuery(selectSql);
			query.setParameter("currentUserAccount", currentUserAccount);
			query.setParameter("currentPsd", currentPsd);
			query.setParameter("originalUserAccount", originalUserAccount);

			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> selectUserDaoByUserName(String userName) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from user where user_name = '"+userName+"' and user_status = 0";
		Query query = em.createNativeQuery(sql,User.class);
		List<User> list = query.getResultList();
		em.close();
		return list;
	}
}
