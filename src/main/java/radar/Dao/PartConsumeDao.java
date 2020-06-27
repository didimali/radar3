package radar.Dao;

import java.util.List;

import radar.Entity.PartConsume;

public interface PartConsumeDao {

	List<PartConsume> getPartConsume();

	List<PartConsume> getPartsConsumeDetails(int managerId, String radarType, String sDate, String eDate);

	List<PartConsume> geRadarPartsConsume(int radarId);

}
