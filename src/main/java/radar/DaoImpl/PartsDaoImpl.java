package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.PartsDao;
import radar.Entity.PartConsume;
import radar.Entity.Parts;

@Repository("PartsDaoImpl")
public class PartsDaoImpl implements PartsDao{
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	@Override
	public List<Parts> getPartsType() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from parts";
		Query query = em.createNativeQuery(sql,Parts.class);
		List<Parts> list = query.getResultList();
		em.close();
		return list;
	}
	@Override
	public List<PartConsume> getPartsConsume() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from part_consume";
		Query query = em.createNativeQuery(sql,PartConsume.class);
		List<PartConsume> list = query.getResultList();
		em.close();
		return list;
	}
}
