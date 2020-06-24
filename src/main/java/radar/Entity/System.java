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
@Table(name="system")
public class System {
private Integer	systemId;//子系统id
private String systemName;//子系统名字
private RadarType	radarTypeId;//所属雷达型号id
private Integer	systemStatus;  //子系统状态0:存在；1：已删除 Default：0
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "systemId",unique=true,nullable=false, length = 11)
public Integer getSystemId() {
	return systemId;
}
public void setSystemId(Integer systemId) {
	this.systemId = systemId;
}
@Column(name="systemName",length=32,unique=true)
public String getSystemName() {
	return systemName;
}
public void setSystemName(String systemName) {
	this.systemName = systemName;
}
@ManyToOne
@JoinColumn(name="radarTypeId",nullable=false)
public RadarType getRadarTypeId() {
	return radarTypeId;
}
public void setRadarTypeId(RadarType radarTypeId) {
	this.radarTypeId = radarTypeId;
}
@Column(columnDefinition = "INT not null default 0")
public Integer getSystemStatus() {
	return systemStatus;
}
public void setSystemStatus(Integer systemStatus) {
	this.systemStatus = systemStatus;
}


}
