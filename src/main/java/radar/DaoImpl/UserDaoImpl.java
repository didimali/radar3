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
	public boolean deleteUser(String receiveName) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = " update user set user_status=1 where user_name=:receiveName";
			Query query = em.createNativeQuery(selectSql);
			query.setParameter("receiveName", receiveName);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return true;

	}
	public List<User> selectPassWordByUserName(String choosenUserName){
		EntityManager em = emf.createEntityManager();
		String sql = "select * from user where user_name='"+choosenUserName+"' and user_status='0'";
		Query query = em.createNativeQuery(sql,User.class);
		@SuppressWarnings("unchecked")
		List<User> list = query.getResultList();
		em.close();
		return list;
	};
	
	public boolean updateUser(String inputNameModify, String inputPasswordModify, String userName3) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = " update user set user_name=:inputNameModify,pass_word=:inputPasswordModify where user_name=:userName3";
			Query query = em.createNativeQuery(selectSql);
			query.setParameter("inputNameModify", inputNameModify);
			query.setParameter("inputPasswordModify", inputPasswordModify);
			query.setParameter("userName3", userName3);

			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return true;
	}
}
