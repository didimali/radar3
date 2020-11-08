package radar.Tools;

import java.util.Date;

/**
 * sqlite数据库雷达运行数据记录对应实体类
 * @author madi
 *
 */
public class Records {
	
	/**
	 * 记录id
	 */
	private Integer id;
	/**
	 * 数据记录时间
	 */
	private Date time;
	/**
	 * 雷达运行数据对应的结构位置编号（10进制）
	 */
	private int dev;
	/**
	 * 运行数据内容
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
