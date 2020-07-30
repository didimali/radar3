package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.RadarHealthDao;
import radar.Entity.RadarForecast;
import radar.Entity.RadarHealth;

@Repository("RadarHealthDaoimpl")
public class RadarHealthDaoimpl implements RadarHealthDao {
	
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RadarHealth> getTotalRadarHealthResult(int radarId) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar_health where radar_id = '"+radarId+"' and ass_result_effective = '0'";
		Query query = em.createNativeQuery(sql,RadarHealth.class);
		List<RadarHealth> list = query.getResultList();
		em.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RadarHealth> getDataForRadarHiLine(int radarId) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar_health where radar_id = '"+radarId+"' order by assess_date desc limit 7";
		Query query = em.createNativeQuery(sql,RadarHealth.class);
		List<RadarHealth> list = query.getResultList();
		em.close();
		return list;
	}

}
