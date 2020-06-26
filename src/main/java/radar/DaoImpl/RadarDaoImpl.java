package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.RadarDao;
import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.Entity.RadarType;

@Repository("RadarDaoImpl")

public class RadarDaoImpl implements RadarDao{
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

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
	public	List<RadarType> selectRadarType(String name1) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar_type where radar_type_name = '"+name1+"'";
		Query query = em.createNativeQuery(sql,RadarType.class);
		List<RadarType> list = query.getResultList();
		em.close();
		return list;
	};
	@Override
	public	List<Parts> getParts() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from parts";
		Query query = em.createNativeQuery(sql,Parts.class);
		List<Parts> list = query.getResultList();
		em.close();
		return list;
	};
	@Override
	public	List<RadarType> getRadarTypes() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar_type ";
		Query query = em.createNativeQuery(sql,RadarType.class);
		List<RadarType> list = query.getResultList();
		em.close();
		return list;
	};
	public boolean deleteRadar(String radarName) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = " update radar set radar_status=1 where radar_name=:radarName";
			Query query = em.createNativeQuery(selectSql);
			query.setParameter("radarName", radarName);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return true;

	}

}
