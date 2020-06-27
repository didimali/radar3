package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.EquipDao;
import radar.Entity.Equip;

@Repository("EquipDaoImpl")
public class EquipDaoImpl implements EquipDao {

	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Equip> getAllEquip() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from equip";
		Query query = em.createNativeQuery(sql,Equip.class);
		List<Equip> list = query.getResultList();
		em.close();
		return list;
	}

}
