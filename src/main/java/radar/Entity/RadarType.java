package radar.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="radarType")
public class RadarType {

	private Integer radarTypeId;  //雷达型号id
	private String radarTypeName; //雷达型号名称
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "radarTypeId",unique=true,nullable=false, length = 11)
	public Integer getRadarTypeId() {
		return radarTypeId;
	}
	public void setRadarTypeId(Integer radarTypeId) {
		this.radarTypeId = radarTypeId;
	}
	
	@Column(name ="radarTypeName",length = 32, nullable=false)
	public String getRadarTypeName() {
		return radarTypeName;
	}
	public void setRadarTypeName(String radarTypeName) {
		this.radarTypeName = radarTypeName;
	}
}
