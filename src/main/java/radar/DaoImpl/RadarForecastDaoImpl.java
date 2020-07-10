package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.RadarForecastDao;
import radar.Entity.RadarForecast;

@Repository("RadarForecastDaoImpl")
public class RadarForecastDaoImpl implements RadarForecastDao {
	
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	@Override
	public List<RadarForecast> getRadarForecastResult(int managerId,String radarTypeName) {
		EntityManager em = emf.createEntityManager();
		String sql = "select radar_forecast.* from radar left join radar_forecast"
						+" on radar.radar_id = radar_forecast.radar_id where radar.manager_id = '"+managerId+"'"
						+" and radar.radar_status = '0' and radar_forecast.fore_result_effective = '0'"
						+" and radar.radar_type_id = "
						+ "(select radar_type_id from radar_type where radar_type_name = '"+radarTypeName+"')"
						+" group by radar_forecast.radar_id";
		Query query = em.createNativeQuery(sql,RadarForecast.class);
		List<RadarForecast> list = query.getResultList();
		em.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RadarForecast> getRadarForecastResult(int radarId) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar_forecast where radar_id = '"+radarId+"' and fore_result_effective = '0';";
		Query query = em.createNativeQuery(sql,RadarForecast.class);
		List<RadarForecast> list = query.getResultList();
		em.close();
		return list;
	}

}
