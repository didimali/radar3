package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.TestDao;
import radar.Entity.DynamicData;
import radar.Entity.Manager;
import radar.Entity.Radar;

@Repository("TestDaoImpl")
@SuppressWarnings("unchecked")
public class TestDaoImpl implements TestDao {
	
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	@Override
	public List<Manager> getManagers() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from manager where manager_status = '0'";
		Query query = em.createNativeQuery(sql,Manager.class);
		List<Manager> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public List<Radar> getRadars() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar where radar_status = '0'";
		Query query = em.createNativeQuery(sql,Radar.class);
		List<Radar> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public List<Object> getPieData() {
		EntityManager em = emf.createEntityManager();
		String sql = "select manager.manager_name,COUNT(radar.radar_id) as count from manager"
					+" left join radar on manager.manager_id = radar.manager_id where manager.manager_status = '0'"
					+" and radar.radar_status ='0' group by manager.manager_id";
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public List<DynamicData> getLineData() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from dynamic_data where radar_id = '1'";
		Query query = em.createNativeQuery(sql,DynamicData.class);
		List<DynamicData> list = query.getResultList();
		em.close();
		return list;
	}
}


