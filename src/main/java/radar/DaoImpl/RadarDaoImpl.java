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
	
	@Override
	public List<Radar> getRadarCountByHealth() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar where radar_status = '0'";
		Query query = em.createNativeQuery(sql,Radar.class);
		List<Radar> list = query.getResultList();
		em.close();
		return list;
	}

	public List<RepairPlan> getRepairPlan(Integer id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from repair_plan where radar_id="+id+" and plan_effective = 0";
		Query query = em.createNativeQuery(sql,RepairPlan.class);
		List<RepairPlan> list = query.getResultList();
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
