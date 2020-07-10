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
@Table(name="equip")
public class Equip {
private Integer	equipId	;//部件id
private String	equipName;//	部件名字
private System	systemId;//	对应子系统id
private Integer	equipStatus	;//部件状态0:存在；1：已删除    Default：0
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "equipId",unique=true,nullable=false, length = 11)
public Integer getEquipId() {
	return equipId;
}
public void setEquipId(Integer equipId) {
	this.equipId = equipId;
}
@Column(name="equipName",length=32,unique=true)

public String getEquipName() {
	return equipName;
}
public void setEquipName(String equipName) {
	this.equipName = equipName;
}
@ManyToOne
@JoinColumn(name="systemId",nullable=false)
public System getSystemId() {
	return systemId;
}
public void setSystemId(System systemId) {
	this.systemId = systemId;
}
@Column(columnDefinition = "INT not null default 0")

public Integer getEquipStatus() {
	return equipStatus;
}
public void setEquipStatus(Integer equipStatus) {
	this.equipStatus = equipStatus;
}


}
