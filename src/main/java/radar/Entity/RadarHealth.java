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
@Table(name="radarHealth")
public class RadarHealth {
	private Integer healthResultId;//健康评估结果id
	private Radar radarId;//	所属雷达id，外键
	private String assessResult;//	评估结果	
	private Date assessDate;//	评估时间
	private Integer assResultEffective;//结果是否有效 0:有效；1:无效   Default：0
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "healthResultId",unique=true,nullable=false, length = 11)
	public Integer getHealthResultId() {
		return healthResultId;
	}
	public void setHealthResultId(Integer healthResultId) {
		this.healthResultId = healthResultId;
	}
	@ManyToOne
	@JoinColumn(name="radarId")
	public Radar getRadarId() {
		return radarId;
	}
	public void setRadarId(Radar radarId) {
		this.radarId = radarId;
	}
	@Column(name="assessResult",length=8)
	public String getAssessResult() {
		return assessResult;
	}
	public void setAssessResult(String assessResult) {
		this.assessResult = assessResult;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="assessDate",nullable=false)
	public Date getAssessDate() {
		return assessDate;
	}
	public void setAssessDate(Date assessDate) {
		this.assessDate = assessDate;
	}
	@Column(columnDefinition = "INT not null default 0")
	public Integer getAssResultEffective() {
		return assResultEffective;
	}
	public void setAssResultEffective(Integer assResultEffective) {
		this.assResultEffective = assResultEffective;
	}

}