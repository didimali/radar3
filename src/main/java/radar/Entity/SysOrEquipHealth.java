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
@Table(name="sysOrEquipHealth")
public class SysOrEquipHealth {
private Integer hResultContentId;//评估结果内容id
private RadarHealth healthResultId;//对应评估结果id
private System systemId;//子系统id
private String assResult;	//评估结果
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "hResultContentId",unique=true,nullable=false, length = 11)
public Integer gethResultContentId() {
	return hResultContentId;
}
public void sethResultContentId(Integer hResultContentId) {
	this.hResultContentId = hResultContentId;
}
@ManyToOne
@JoinColumn(name="healthResultId",nullable=false)
public RadarHealth getHealthResultId() {
	return healthResultId;
}
public void setHealthResultId(RadarHealth healthResultId) {
	this.healthResultId = healthResultId;
}
@ManyToOne
@JoinColumn(name="systemId",nullable=false)
public System getSystemId() {
	return systemId;
}
public void setSystemId(System systemId) {
	this.systemId = systemId;
}
@Column(name="assResult",length=8,nullable=false)
public String getAssResult() {
	return assResult;
}
public void setAssResult(String assResult) {
	this.assResult = assResult;
}
}
