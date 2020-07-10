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
@Table(name="radar")
public class Radar {
	private Integer radarId;//雷达id
	private String radarName; //雷达编号
	private RadarType  radarTypeId;//雷达型号id
	private Manager managerId;//雷达所属部队id，外键
	private int radarHealth;//雷达健康状态
	private Integer radarStatus;//雷达状态，0:存在；1：已删除，Default：0
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "radarId",unique=true,nullable=false, length = 11)
	public Integer getRadarId(){
		return radarId;
	}
	public void setRadarId(Integer radarId) {
		this.radarId = radarId;
	}
	@Column(name="radarName",length=32,unique=true)
	public String getRadarName() {
		return radarName;
	}
	public void setRadarName(String radarName) {
		this.radarName = radarName;
	}
	@ManyToOne
	@JoinColumn(name="radarTypeId",nullable=false)
	public RadarType getRadarTypeId() {
		return radarTypeId;
	}
	public void setRadarTypeId(RadarType radarTypeId) {
		this.radarTypeId = radarTypeId;
	}
	@ManyToOne
	@JoinColumn(name="managerId",nullable=false)
	public Manager getManagerId() {
		return managerId;
	}
	public void setManagerId(Manager managerId) {
		this.managerId = managerId;
	}
	@Column(columnDefinition = "INT not null default 0")
	public int getRadarHealth() {
		return radarHealth;
	}
	public void setRadarHealth(int radarHealth) {
		this.radarHealth = radarHealth;
	}
	@Column(columnDefinition = "INT not null default 0")
	public Integer getRadarStatus() {
		return radarStatus;
	}
	public void setRadarStatus(Integer radarStatus) {
		this.radarStatus = radarStatus;
	}
}
