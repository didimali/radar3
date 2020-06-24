package radar.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="parts")
public class Parts {

	private Integer partsId; //主键
	private String  partsName; //备件名称
	private RadarType radarTypeId; //外键
	private Integer partsCount;//备件库存数量
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "partsId",unique=true,nullable=false, length = 11)
	public Integer getPartsId() {
		return partsId;
	}
	public void setPartsId(Integer partsId) {
		this.partsId = partsId;
	}
	
	@Column(name ="managerName",length = 32)
	public String getPartsName() {
		return partsName;
	}
	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}
	
	@ManyToOne
	@JoinColumn(name="radarTypeId",columnDefinition = "INT not null")
	public RadarType getRadarTypeId() {
		return radarTypeId;
	}
	public void setRadarTypeId(RadarType radarTypeId) {
		this.radarTypeId = radarTypeId;
	}
	
	@Column(name = "partsCount", nullable=false, length = 11 ,columnDefinition = "INT not null default 0")
	public Integer getPartsCount() {
		return partsCount;
	}
	public void setPartsCount(Integer partsCount) {
		this.partsCount = partsCount;
	}
	
	
}
