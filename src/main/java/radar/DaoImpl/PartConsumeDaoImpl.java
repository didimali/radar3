package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.PartConsumeDao;
import radar.Entity.PartConsume;

@SuppressWarnings("unchecked")
@Repository("PartConsumeDaoImpl")
public class PartConsumeDaoImpl implements PartConsumeDao {

	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	
	/**
	 * 有待改进，需要加入时间限制条件
	 */
	@Override
	public List<PartConsume> getPartConsume() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from part_consume";
		Query query = em.createNativeQuery(sql,PartConsume.class);
		List<PartConsume> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public List<PartConsume> getPartsConsumeDetails(String managerName,String radarType, String sDate, String eDate) {
		EntityManager em = emf.createEntityManager();
		String sql = "select part_consume.* from radar_type left join parts"
					+" on radar_type.radar_type_id = parts.radar_type_id left join part_consume " 
					+" on parts.parts_id = part_consume.parts_id where part_consume.manager_id = "
					+ "(select manager_id from manager where manager_name = '"+managerName+"')"
					+ " and parts.radar_type_id = (select radar_type_id from radar_type where radar_type_name = '"+radarType+"')"
					+ " and part_consume.consume_date between '"+sDate+"' and '"+eDate+"';";
		Query query = em.createNativeQuery(sql,PartConsume.class);
		List<PartConsume> list = query.getResultList();
		em.close();
		return list;
	}

}
