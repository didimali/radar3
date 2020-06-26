package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.RadarDao;
import radar.Entity.Equip;
import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.Entity.RadarForecast;
import radar.Entity.RadarHealth;
import radar.Entity.RepairPlan;
import radar.Entity.SysOrEquipHealth;
import radar.Entity.System;

@Repository("RadarDaoImpl")
@SuppressWarnings("unchecked")
public class RadarDaoImpl implements RadarDao {
	
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	@Override
	public List<Radar> getRadarsByType(String id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from Radar where radar_type_id="+id+" and radar_status = 0";
		Query query = em.createNativeQuery(sql,Radar.class);
		List<Radar> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public List<RepairPlan> getRepairPlan(Integer id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from repair_plan where radar_id="+id+" and plan_effective = 0";
		Query query = em.createNativeQuery(sql,RepairPlan.class);
		List<RepairPlan> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public List<RadarForecast> getFaultForecast(Integer id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar_forecast where radar_id="+id+" and fore_result_effective = 0";
		Query query = em.createNativeQuery(sql,RadarForecast.class);
		List<RadarForecast> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<RadarHealth> getRadarState(Integer id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar_health where radar_id="+id+" and ass_result_effective = 0";
		Query query = em.createNativeQuery(sql,RadarHealth.class);
		List<RadarHealth> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public Integer countRadar(Integer id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select count(*) from radar where radar_type_id="+id+" and radar_status = 0";
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return Integer.parseInt(list.get(0).toString());
	}
	
	@Override
	public Integer countHealth(Integer typeid,Integer health) {
		EntityManager em = emf.createEntityManager();
		String sql = "select count(*) from radar where radar_type_id="+typeid+" and radar_status = 0 and radar_health= "+health;
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return Integer.parseInt(list.get(0).toString());
	}
	
	
}

