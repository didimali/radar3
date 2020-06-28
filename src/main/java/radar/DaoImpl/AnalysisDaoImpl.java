package radar.DaoImpl;

import java.util.List;

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
import radar.Entity.Radar;
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
	
	
	
}


