package radar.Dao;

import java.util.List;

import radar.Entity.PartConsume;
import radar.Entity.Parts;

public interface PartsDao {

	List<Parts> getPartsType();

	List<PartConsume> getPartsConsume();

}
