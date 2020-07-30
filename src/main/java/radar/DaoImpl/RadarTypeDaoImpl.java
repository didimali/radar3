package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.RadarTypeDao;
import radar.Entity.RadarType;

@SuppressWarnings("unchecked")
@Repository("RadarTypeDaoImpl")
public class RadarTypeDaoImpl implements RadarTypeDao{

	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	

	@Override
	public List<RadarType> getRadarType() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar_type";
		Query query = em.createNativeQuery(sql,RadarType.class);
		List<RadarType> list = query.getResultList();
		em.close();
		return list;
	}

}
