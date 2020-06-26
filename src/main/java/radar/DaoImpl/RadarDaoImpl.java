package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.RadarDao;
import radar.Entity.Radar;

@SuppressWarnings("unchecked")
@Repository("RadarDaoImpl")
public class RadarDaoImpl implements RadarDao {

	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	
	@Override
	public List<Radar> getRadarCountByHealth() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar where radar_status = '0'";
		Query query = em.createNativeQuery(sql,Radar.class);
		List<Radar> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public List<Object> getRadarHCount(int managerId) {
		int id = managerId;
		EntityManager em = emf.createEntityManager();
		String sql = "select manager.manager_location,radar.radar_type_id,radar.radar_health,"
					+"COUNT(radar.radar_id) from manager left join radar on manager.manager_id ="
					+" radar.manager_id where radar.radar_status = '0' and "
					+"manager.manager_id = '"+id+"' group by radar.radar_health,radar.radar_type_id";
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public List<Object> getRadarCountByRadarHeath(String managerName, String radarTypeName) {
		EntityManager em = emf.createEntityManager();
		String sql = "select COUNT(*),radar_health from radar where radar_status = '0' "
				+ "and manager_id = (select manager_id from manager where manager_name = '"+managerName+"')"				
				+ " and radar_type_id = "
				+ "(select radar_type_id from radar_type where radar_type_name = '"+radarTypeName+"')"
				+ "  group by radar_health";
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public List<Radar> getRadarDetails(String managerName, String radarTypeName) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar where manager_id = "
				+ "(select manager_id from manager where manager_name = '"+managerName+"')"
				+" and radar_status = '0' and radar_type_id = "
				+ "(select radar_type_id from radar_type where radar_type_name = '"+radarTypeName+"')";
		Query query = em.createNativeQuery(sql,Radar.class);
		List<Radar> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public List<Object> getRadarCountByRadarType(String managerName) {
		EntityManager em = emf.createEntityManager();
		String sql = "select COUNT(radar.radar_id),radar_type.radar_type_name from radar left join radar_type "
					+" on radar.radar_type_id = radar_type.radar_type_id where radar.manager_id = "
					+ "(select manager_id from manager where manager_name = '"+managerName+"')"
					+ " and radar.radar_status = '0' group by radar_type.radar_type_name;";
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}

}
