package radar.Tools;

import java.util.Date;

/**
 * 雷达运行时自动记录的故障记录对应实体
 * @author madi
 *
 */
public class Faults{
	
	/**
	 * 故障记录id
	 */
	private Integer id;
	/**
	 * 故障记录时间
	 */
	private Date time;
	/**
	 * 故障发生位置编号（10进制）
	 */
	private int dev;
	/**
	 * 故障内容
	 */
	private String info;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getDev() {
		return dev;
	}
	public void setDev(int dev) {
		this.dev = dev;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

}
