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
@Table(name="fault_parts")
public class FaultPartsRelation {
	private Integer relationId;//记录id
	private FaultType faultTypeId;//	雷达id，外键
	private Parts partsId;//	记录类型id，外键
	private Integer number;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "relationId",unique=true,nullable=false, length = 11)
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	@ManyToOne
	@JoinColumn(name="faultTypeId",nullable=false)
	public FaultType getFaultTypeId() {
		return faultTypeId;
	}
	public void setFaultTypeId(FaultType faultTypeId) {
		this.faultTypeId = faultTypeId;
	}
	@ManyToOne
	@JoinColumn(name="partsId",nullable=false)
	public Parts getPartsId() {
		return partsId;
	}
	public void setPartsId(Parts partsId) {
		this.partsId = partsId;
	}
	@Column(name="number",length = 11)
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
	
}
