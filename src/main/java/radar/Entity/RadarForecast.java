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
@Table(name="radarForecast")
public class RadarForecast {
	private Integer forecastResultId;//故障预测记录id
	private Radar radarId;//	所属雷达id,外键
	private FaultType faultTypeId;//故障类型
	private Date  forecastDate;//故障预测时间
	private Date forecastResultDate;//故障预测结果生成时间
	private Integer foreResultEffective;//结果是否有效 0:有效；1:无效 Default：0
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name = "forecastResultId",unique=true,nullable=false, length = 11)
	public Integer getForecastResultId() {
		return forecastResultId;
	}
	public void setForecastResultId(Integer forecastResultId) {
		this.forecastResultId = forecastResultId;
	}
	@ManyToOne
	@JoinColumn(name="radarId")
	public Radar getRadarId() {
		return radarId;
	}
	public void setRadarId(Radar radarId) {
		this.radarId = radarId;
	}
	@Column(columnDefinition = "INT not null default 0")
	public Integer getForeResultEffective() {
		return foreResultEffective;
	}
	public void setForeResultEffective(Integer foreResultEffective) {
		this.foreResultEffective = foreResultEffective;
	}
	@ManyToOne
	@JoinColumn(name="faultTypeId")
	public FaultType getFaultTypeId() {
		return faultTypeId;
	}
	public void setFaultTypeId(FaultType faultTypeId) {
		this.faultTypeId = faultTypeId;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="forecastDate",nullable=false)
	public Date getForecastDate() {
		return forecastDate;
	}
	public void setForecastDate(Date forecastDate) {
		this.forecastDate = forecastDate;
	}
	@DateTimeFormat(pattern="yy-mm-dd HH:mm:ss")
	@Column(name="forecastResultDate",nullable=false)
	public Date getForecastResultDate() {
		return forecastResultDate;
	}
	public void setForecastResultDate(Date forecastResultDate) {
		this.forecastResultDate = forecastResultDate;
	}


}
