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
@Table(name="faultType")
public class FaultType {
	private Integer faultTypeId;//故障id
	private String faultName;//	故障名称
	private String faultPrinciple;//	故障机理
	private RadarType radarTypeId;//对应雷达型号id
	private String faultLocaltion; //故障部位，格式：s+systemId 或者 e+equipiId
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "faultTypeId",unique=true,nullable=false, length = 11)
	public Integer getFaultTypeId() {
		return faultTypeId;
	}
	public void setFaultTypeId(Integer faultTypeId) {
		this.faultTypeId = faultTypeId;
	}
	
	@Column(name="faultName",length=64,nullable=false)
	public String getFaultName() {
		return faultName;
	}
	public void setFaultName(String faultName) {
		this.faultName = faultName;
	}
	@Column(name="faultPrinciple",length=255)
	public String getFaultPrinciple() {
		return faultPrinciple;
	}
	public void setFaultPrinciple(String faultPrinciple) {
		this.faultPrinciple = faultPrinciple;
	}
	@ManyToOne
	@JoinColumn(name="radarTypeId",nullable=false)
	public RadarType getRadarTypeId() {
		return radarTypeId;
	}
	public void setRadarTypeId(RadarType radarTypeId) {
		this.radarTypeId = radarTypeId;
	}
	@Column(name="faultLocaltion",length=32)
	public String getFaultLocaltion() {
		return faultLocaltion;
	}
	public void setFaultLocaltion(String faultLocaltion) {
		this.faultLocaltion = faultLocaltion;
	}

}
