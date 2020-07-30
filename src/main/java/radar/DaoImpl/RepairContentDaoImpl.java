package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.RepairContentDao;
import radar.Entity.RepairContent;

@Repository("RepairContentDaoImpl")
public class RepairContentDaoImpl implements RepairContentDao {

	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RepairContent> getRadarRepairContent(int radarId) {
		EntityManager em = emf.createEntityManager();
		String sql = "select repair_content.* from repair_plan left join repair_content"
					+ " on repair_plan.repair_plan_id = repair_content.repair_plan_id"
					+ " where repair_plan.radar_id = '"+radarId+"' and repair_plan.plan_effective = '0'";
		Query query = em.createNativeQuery(sql,RepairContent.class);
		List<RepairContent> list = query.getResultList();
		em.close();
		return list;
	}

}
