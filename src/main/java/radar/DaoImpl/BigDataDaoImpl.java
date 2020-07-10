package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.BigDataDao;
import radar.Entity.Equip;
import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.Entity.RadarForecast;
import radar.Entity.RadarHealth;
import radar.Entity.SysOrEquipHealth;
import radar.Entity.System;

@Repository("BigDataDaoImpl")
@SuppressWarnings("unchecked")
public class BigDataDaoImpl implements BigDataDao {
	
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	@Override
	public List<Radar> getRadarByID(String id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar where radar_id="+id+" and radar_status = 0";
		Query query = em.createNativeQuery(sql,Radar.class);
		List<Radar> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<RadarHealth> getRadarHealth(String id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar_health where radar_id= "+id+" and ass_result_effective = 0";
		Query query = em.createNativeQuery(sql,RadarHealth.class);
		List<RadarHealth> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<SysOrEquipHealth> getSEHealth(String id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from sys_or_equip_health where health_result_id="+id;
		Query query = em.createNativeQuery(sql,SysOrEquipHealth.class);
		List<SysOrEquipHealth> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<System> getSystemName(String id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from system where system_id="+id;
		Query query = em.createNativeQuery(sql,System.class);
		List<System> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<Equip> getEquipName(String id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from equip where equip_id="+id;
		Query query = em.createNativeQuery(sql,Equip.class);
		List<Equip> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<RadarHealth> getRadarHistory(String id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar_health where radar_id= " + id + " order by assess_date desc limit 6";
		Query query = em.createNativeQuery(sql,RadarHealth.class);
		List<RadarHealth> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<RadarForecast> getFaultForecast(String id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar_forecast where radar_id= "+id+" and fore_result_effective = 0";
		Query query = em.createNativeQuery(sql,RadarForecast.class);
		List<RadarForecast> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<Parts> getPartsName(String id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from parts where parts_id= "+id;
		Query query = em.createNativeQuery(sql,Parts.class);
		List<Parts> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public Integer getPartsNum(String radarid,String partid) {
		EntityManager em = emf.createEntityManager();
		String sql = "select sum(number) from fault_parts right join radar_forecast on "
				+ "fault_parts.fault_type_id= radar_forecast.fault_type_id where radar_id="+radarid+" and fore_result_effective = 0 and parts_id= "+partid;
		Query query = em.createNativeQuery(sql);
		List<Object> num = query.getResultList();
		em.close();
		return Integer.parseInt(num.get(0).toString());
	}
	
	@Override
	public List<Object> getPartsID(String id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select DISTINCT(parts_id) from fault_parts right join "
				+ "radar_forecast on fault_parts.fault_type_id= radar_forecast.fault_type_id where radar_id="+id+" and fore_result_effective = 0";
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}
	
}

