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
@Table(name = "repairContent")
public class RepairContent {

	private Integer repairContentId; // 维修计划内容ID 主键
	private RepairPlan repairPlanId; // 外键 维修计划ID
	private Parts partsId; // 外键 对应备件ID
	private Integer partsCount;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "repairContentId",unique=true,nullable=false, length = 11)
	public Integer getRepairContentId() {
		return repairContentId;
	}

	public void setRepairContentId(Integer repairContentId) {
		this.repairContentId = repairContentId;
	}

	@ManyToOne
	@JoinColumn(name="repairPlanId",columnDefinition = "INT not null default 0")
	public RepairPlan getRepairPlanId() {
		return repairPlanId;
	}

	public void setRepairPlanId(RepairPlan repairPlanId) {
		this.repairPlanId = repairPlanId;
	}

	@ManyToOne
	@JoinColumn(name="partsId",columnDefinition = "INT not null default 0")
	public Parts getPartsId() {
		return partsId;
	}

	public void setPartsId(Parts partsId) {
		this.partsId = partsId;
	}
	@Column(name = "partsCount",nullable=false, length = 11)
	public Integer getPartsCount() {
		return partsCount;
	}

	public void setPartsCount(Integer partsCount) {
		this.partsCount = partsCount;
	}

}
