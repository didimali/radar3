package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Entity.PartConsume;
import radar.Entity.RepairPlan;

@Repository("RepairPlanDao")
public class RepairPlanDao implements radar.Dao.RepairPlanDao {

	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	@Override
	public List<RepairPlan> getRepairPlanResult(String managerName,String radarTypeName) {
		EntityManager em = emf.createEntityManager();
		String sql = "select repair_plan.* from radar left join repair_plan on radar.radar_id = repair_plan.radar_id"
					+" where radar.manager_id = "
					+ "(select manager_id from manager where manager_name = '"+managerName+"')"
					+ " and radar.radar_type_id = "
					+ "(select radar_type_id from radar_type where radar_type_name = '"+radarTypeName+"')"
					+ " and radar.radar_status = '0'"
					+" and repair_plan.plan_effective = '0' group by repair_plan.radar_id";
		Query query = em.createNativeQuery(sql,RepairPlan.class);
		List<RepairPlan> list = query.getResultList();
		em.close();
		return list;
	}
	
	
}
