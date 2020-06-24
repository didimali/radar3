package radar.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "partConsume")
public class RepairPlan {

	private Integer repairPlanId; // 维修计划ID
	private Radar radarId; // 对应雷达ID
	private Date repairPlanDate; // 计划维修时间

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "repairPlanId",unique=true,nullable=false, length = 11)
	public Integer getRepairPlanId() {
		return repairPlanId;
	}

	public void setRepairPlanId(Integer repairPlanId) {
		this.repairPlanId = repairPlanId;
	}

	@ManyToOne
	@JoinColumn(name="radarId",columnDefinition = "INT not null default 0")
	public Radar getRadarId() {
		return radarId;
	}

	public void setRadarId(Radar radarId) {
		this.radarId = radarId;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "repairPlanDate")
	public Date getRepairPlanDate() {
		return repairPlanDate;
	}

	public void setRepairPlanDate(Date repairPlanDate) {
		this.repairPlanDate = repairPlanDate;
	}

}
