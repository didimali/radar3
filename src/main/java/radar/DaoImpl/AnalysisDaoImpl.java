package radar.DaoImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.AnalysisDao;
import radar.Entity.DynamicData;
import radar.Entity.Equip;
import radar.Entity.Manager;
import radar.Entity.PartConsume;
import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.Entity.RadarHealth;
import radar.Entity.RepairPlan;
import radar.Entity.System;

@Repository("AnalysisDaoImpl")
@SuppressWarnings("unchecked")
public class AnalysisDaoImpl implements AnalysisDao {
	
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	@Override
	public Integer countRadarByTL(Integer typeid,Integer location,Integer health) {
		EntityManager em = emf.createEntityManager();
		String sql;
		if(health==null) {
		sql = "select count(*) from radar left join manager on "
				+ "radar.manager_id = manager.manager_id where radar_type_id="+typeid+" and "
				+ "manager_location="+location+" and radar_status=0 and manager_status=0";	
		}else {
		sql = "select count(*) from radar left join manager on "
				+ "radar.manager_id = manager.manager_id where radar_type_id="+typeid+" and "
				+ "manager_location="+location+" and radar_health="+health+" and radar_status=0 and manager_status=0";
		}
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return Integer.parseInt(list.get(0).toString());
	}
	
	@Override
	public List<Object> getRadarListByTL(Integer typeid,Integer location) {
		EntityManager em = emf.createEntityManager();
		String sql = "select radar_name,manager_name,radar_health from radar left join manager on "
				+ "radar.manager_id = manager.manager_id where radar_type_id="+typeid+" and "
				+ "manager_location="+location+" and radar_status=0 and manager_status=0";	
		
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<Object> getFaultLocationType(Integer typeid,Integer location,String startDate,String endDate) {
		EntityManager em = emf.createEntityManager();
		String sql;
		if(startDate==null||endDate==null) {
			sql = "select DISTINCT(fault_localtion) from radar left join manager on "
					+ "radar.manager_id = manager.manager_id right join fault_record on "
					+ "radar.radar_id=fault_record.radar_id left join fault_type on "
					+ "fault_record.fault_type_id=fault_type.fault_type_id "  
					+"where radar.radar_type_id="+typeid+" and manager_location="+location+" and radar_status=0 ";
		}else {
		sql = "select DISTINCT(fault_localtion) from radar left join manager on "
				+ "radar.manager_id = manager.manager_id right join fault_record on "
				+ "radar.radar_id=fault_record.radar_id left join fault_type on "
				+ "fault_record.fault_type_id=fault_type.fault_type_id "  
				+"where radar.radar_type_id="+typeid+" and manager_location="+location+" and radar_status=0 "
				+ "and fault_date between "+"'"+startDate+"'"+" and "+"'"+endDate+"'";
		}
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public int countFaultLocationType(Integer typeid,Integer location,String startDate,String endDate,String SearchKey) {
		EntityManager em = emf.createEntityManager();
		String sql;
		if(startDate==null||endDate==null) {
			sql = "select count(fault_localtion) from radar left join manager on "
					+ "radar.manager_id = manager.manager_id right join fault_record on "
					+ "radar.radar_id=fault_record.radar_id left join fault_type on "
					+ "fault_record.fault_type_id=fault_type.fault_type_id "  
					+"where radar.radar_type_id="+typeid+" and manager_location="+location+" and radar_status=0 "
					+ "and fault_localtion = '"+ SearchKey+"'";
		}else {
		sql = "select count(fault_localtion) from radar left join manager on "
				+ "radar.manager_id = manager.manager_id right join fault_record on "
				+ "radar.radar_id=fault_record.radar_id left join fault_type on "
				+ "fault_record.fault_type_id=fault_type.fault_type_id "  
				+"where radar.radar_type_id="+typeid+" and manager_location="+location+" and radar_status=0 "
				+ "and fault_date between "+"'"+startDate+"'"+" and "+"'"+endDate+"'"+" and fault_localtion= '"+ SearchKey+"'";
		}
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return Integer.parseInt(list.get(0).toString());
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
	public void save1(String id, int result) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(new Date());
		try {
			String selectSql = "insert into radar_health(ass_result_effective,assess_date,assess_result,radar_id) values"
					+ " ('0','"+currentTime+"','"+result+"','"+id+"')";
			Query query = em.createNativeQuery(selectSql);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}	
	}
	
	@Override
	public void save2(String id, int result) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = "update radar set radar_health="+result+" where radar_id="+id;
			Query query = em.createNativeQuery(selectSql);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}	
	}
	
	@Override
	public void change1(String id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = "update radar_health set ass_result_effective = 1 where radar_id="+id+" ";
			Query query = em.createNativeQuery(selectSql);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}		
	}
	
	@Override
	public void change2(String id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = "update radar_forecast set fore_result_effective = 1 where radar_id="+id+" ";
			Query query = em.createNativeQuery(selectSql);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}		
	}
	
	@Override
	public void change3(String id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = "update repair_plan set plan_effective = 1 where radar_id="+id+" ";
			Query query = em.createNativeQuery(selectSql);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}		
	}
	@Override
	public void faultForecast(String id, int i) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(new Date());
			Calendar ca = Calendar.getInstance();
			ca.setTime(new Date());
			Random random = new Random();			
			ca.add(Calendar.DATE, random.nextInt(60));
			Date d = ca.getTime();
	        String nextTime = sdf.format(d);
			try {
				String selectSql = "insert into radar_forecast(fore_result_effective,forecast_date,forecast_result_date,fault_type_id,radar_id) values"
						+ " ('0','"+nextTime+"','"+currentTime+"','"+i+"','"+id+"')";
				Query query = em.createNativeQuery(selectSql);
				query.executeUpdate();
				em.flush();
				em.getTransaction().commit();
			} finally {
				em.close();
			}		
	}
	
	@Override
	public String countNum(int health,int typeid) {
		EntityManager em = emf.createEntityManager();
		String sql = "select count(*) from radar where radar_health="+health+" and radar_type_id="+typeid+" and radar_status=0";
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return list.get(0).toString();
	}

	@Override
	public List<Parts> getParts(String id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from parts where radar_type_id="+id +" " ;
		Query query = em.createNativeQuery(sql,Parts.class);
		List<Parts> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<PartConsume> getPartsConsume(int id,String startDate,String endDate) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from part_consume where parts_id="+id
				+" and consume_date between "+"'"+startDate+"'"+" and "+"'"+endDate+"'"
				+ " and p_consume_count >0";
		Query query = em.createNativeQuery(sql,PartConsume.class);
		List<PartConsume> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<Radar> getRadar(String typeid) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar where radar_type_id="+typeid+" and radar_status=0";
		Query query = em.createNativeQuery(sql,Radar.class);
		List<Radar> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<RadarHealth> gethealthID(String radarid) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar_health where radar_id="+radarid+" ORDER BY health_result_id DESC LIMIT 0,1 ";
		Query query = em.createNativeQuery(sql,RadarHealth.class);
		List<RadarHealth> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public void save3(int result,int hid, int sysid) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = "insert into sys_or_equip_health(ass_result,health_result_id,system_id) values"
					+ " ('"+result+"','"+hid+"','"+sysid+"')";
			Query query = em.createNativeQuery(selectSql);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}	
	}	
	@Override
	public void save4(String radarid) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(new Date());
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		Random random = new Random();			
		ca.add(Calendar.DATE, random.nextInt(60));
		Date d = ca.getTime();
        String nextTime = sdf.format(d);
		try {
			String selectSql = "insert into repair_plan(repair_plan_date,radar_id,plan_effective) values"
					+ " ('"+nextTime+"','"+radarid+"','0')";
			Query query = em.createNativeQuery(selectSql);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}	
	}	
	@Override
	public List<RepairPlan> getRepairID(String radarid) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from repair_plan where radar_id="+radarid+" ORDER BY repair_plan_id DESC LIMIT 0,1 ";
		Query query = em.createNativeQuery(sql,RepairPlan.class);
		List<RepairPlan> list = query.getResultList();
		em.close();
		return list;
	}
	@Override
	public List<Object> getPartsForecast(String radarid) {
		EntityManager em = emf.createEntityManager();
		String sql = "select fault_parts.number,parts.parts_id from "
				+ "radar_forecast right join fault_parts on "
				+ "radar_forecast.fault_type_id=fault_parts.fault_type_id "
				+ "left join parts on fault_parts.parts_id=parts.parts_id "
				+ "where radar_id="+radarid+" and fore_result_effective=0";			
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}
	@Override
	public void saveRepairContent(int num,int partid, int planid) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = "insert into repair_content(parts_count,parts_id,repair_plan_id) values"
					+ " ('"+num+"','"+partid+"','"+planid+"')";
			Query query = em.createNativeQuery(selectSql);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}	
	}
	
	
	@Override
	public int countFaultNum(int radarid) {
		EntityManager em = emf.createEntityManager();
		String sql = "select count(*) from fault_type left join radar on fault_type.radar_type_id = radar.radar_type_id where radar.radar_id="+radarid;
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return Integer.parseInt(list.get(0).toString());
	}
	
	@Override
	public List<Radar> getRadarID() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar where radar_status=0";
		Query query = em.createNativeQuery(sql,Radar.class);
		List<Radar> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public int countDynamicData(int id) {
		EntityManager em = emf.createEntityManager();
		String sql = "select count(*) from dynamic_data where radar_id="+id;
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return Integer.parseInt(list.get(0).toString());
	}
}


