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
public class PartConsume {
	private Integer consumeId;// 消耗记录id 主键
	private Parts partsId; // 备件ID 外键
	private Integer pConsumeCount;// 备件消耗数量 非空
	private Date consumeDate; // 消耗时间

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "consumeId",unique=true,nullable=false, length = 11)
	public Integer getConsumeId() {
		return consumeId;
	}

	public void setConsumeId(Integer consumeId) {
		this.consumeId = consumeId;
	}

	
	@ManyToOne
	@JoinColumn(name="partsId",columnDefinition = "INT not null")
	public Parts getPartsId() {
		return partsId;
	}

	public void setPartsId(Parts partsId) {
		this.partsId = partsId;
	}

	@JoinColumn(name="pConsumeCount",nullable=false)
	public Integer getpConsumeCount() {
		return pConsumeCount;
	}

	public void setpConsumeCount(Integer pConsumeCount) {
		this.pConsumeCount = pConsumeCount;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "consumeDate")
	public Date getConsumeDate() {
		return consumeDate;
	}

	public void setConsumeDate(Date consumeDate) {
		this.consumeDate = consumeDate;
	}

}
