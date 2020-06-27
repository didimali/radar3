package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.ManagerDao;
import radar.Entity.Manager;

@SuppressWarnings("unchecked")
@Repository("ManagerDaoImpl")
public class ManagerDaoImpl implements ManagerDao {

	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	
	
	@Override
	public List<Manager> getManager() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from manager where manager_status = '0'";
		Query query = em.createNativeQuery(sql,Manager.class);
		List<Manager> list = query.getResultList();
		em.close();
		return list;
	}
	@Override
	public List<Manager> getManagers() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from manager where manager_status = '0'";
		Query query = em.createNativeQuery(sql,Manager.class);
		List<Manager> list = query.getResultList();
		em.close();
		return list;
	}
	public boolean deleteManager(String managerName) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = " update manager set manager_status=1 where manager_name=:managerName";
			Query query = em.createNativeQuery(selectSql);
			query.setParameter("managerName", managerName);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return true;

	}
	@Override
	public	List<Manager> selectManager(String name2) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from manager where manager_name = '"+name2+"'";
		Query query = em.createNativeQuery(sql,Manager.class);
		List<Manager> list = query.getResultList();
		em.close();
		return list;
	};

}
