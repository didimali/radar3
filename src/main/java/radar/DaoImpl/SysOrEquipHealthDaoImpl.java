package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.SysOrEquipHealthDao;
import radar.Entity.SysOrEquipHealth;

@Repository("SysOrEquipHealthDaoImpl")
public class SysOrEquipHealthDaoImpl implements SysOrEquipHealthDao {

	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SysOrEquipHealth> getRadarHealthResultContent(int healthResultId) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from sys_or_equip_health where health_result_id = '"+healthResultId+"'";
		Query query = em.createNativeQuery(sql,SysOrEquipHealth.class);
		List<SysOrEquipHealth> list = query.getResultList();
		em.close();
		return list;
	}

}
