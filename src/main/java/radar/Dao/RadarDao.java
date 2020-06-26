package radar.Dao;

import java.util.List;

import radar.Entity.Parts;
import radar.Entity.Radar;
import radar.Entity.RadarType;

public interface RadarDao {

	List<Radar> getRadars();

	List<RadarType> selectRadarType(String name1);

	boolean deleteRadar(String radarName);

	List<RadarType> getRadarTypes();

	List<Parts> getParts();


}
