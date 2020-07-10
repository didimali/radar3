package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.SystemDao;

@Repository("SystemDaoImpl")
public class SystemDaoImpl implements SystemDao {

	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<radar.Entity.System> getAllSystem() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from system";
		Query query = em.createNativeQuery(sql,radar.Entity.System.class);
		List<radar.Entity.System> list = query.getResultList();
		em.close();
		return list;
	}

}
