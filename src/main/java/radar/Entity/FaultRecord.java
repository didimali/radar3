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
@Table(name="faultRecord")
public class FaultRecord {
	private Integer faultId;//故障id

	private Radar radarId;//	对应雷达id，外键

	private FaultType  faultTypeId;//	故障类型id，外键

	private String faultReason;//故障原因（描述）

	private Date faultDate;//	故障时刻
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "faultId",unique=true,nullable=false, length = 11)
	public Integer getFaultId() {
		return faultId;
	}

	public void setFaultId(Integer faultId) {
		this.faultId = faultId;
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
	@JoinColumn(name="radarId",nullable=false)
	public Radar getRadarId() {
		return radarId;
	}

	public void setRadarId(Radar radarId) {
		this.radarId = radarId;
	}
	@Column(name="faultReason",length=255)
	public String getFaultReason() {
		return faultReason;
	}

	public void setFaultReason(String faultReason) {
		this.faultReason = faultReason;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="faultDate")
	public Date getFaultDate() {
		return faultDate;
	}

	public void setFaultDate(Date faultDate) {
		this.faultDate = faultDate;
	}
}
