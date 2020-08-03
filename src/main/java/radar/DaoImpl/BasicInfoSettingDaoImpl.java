package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.BasicInfoSettingDao;
import radar.Entity.FaultRecord;
import radar.Entity.FaultType;
import radar.Entity.Parts;

@Repository("BasicInfoSettingDaoImpl")
@SuppressWarnings("unchecked")
public class BasicInfoSettingDaoImpl implements BasicInfoSettingDao{
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	@Override
	public List<FaultRecord> getFaultRecord(){
		EntityManager em = emf.createEntityManager();
		String sql = "select * from fault_record";
		Query query = em.createNativeQuery(sql,FaultRecord.class);
		List<FaultRecord> list = query.getResultList();
		em.close();
		return list;
	};
	public List<FaultType> getFaultType(){
		EntityManager em = emf.createEntityManager();
		String sql = "select * from fault_type";
		Query query = em.createNativeQuery(sql,FaultType.class);
		List<FaultType> list = query.getResultList();
		em.close();
		return list;
	};
}
