package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.ActivityDao;
import radar.Entity.Activity;


@Repository("ActivityDaoImpl")
public class ActivityDaoImpl implements ActivityDao{
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	//获取所有record数据
		@SuppressWarnings("unchecked")
		public List<Activity> getAllRecords() {
			EntityManager em = emf.createEntityManager();
			String selectSql = "select * from record";
			Query query = em.createNativeQuery(selectSql,Activity.class);
			List<Activity> list = query.getResultList();
			em.close();
			return list;
		}
}
