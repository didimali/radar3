package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import radar.Entity.Equip;
import radar.Entity.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.XiTongDao;

@Repository("XiTongDaoImpl")

public class XiTongDaoImpl implements XiTongDao{
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	public List<System> getXiTongInfo(){
		EntityManager em = emf.createEntityManager();
		String sql = "select * from system where system_status = '0'";
		Query query = em.createNativeQuery(sql,System.class);
		@SuppressWarnings("unchecked")
		List<System> list = query.getResultList();
		em.close();
		return list;
		
	};
	public List<Equip> getEquipmentInfo(){
		EntityManager em = emf.createEntityManager();
		String sql = "select * from equip where equip_status = '0'";
		Query query = em.createNativeQuery(sql,Equip.class);
		@SuppressWarnings("unchecked")
		List<Equip> list = query.getResultList();
		em.close();
		return list;
	}

}
