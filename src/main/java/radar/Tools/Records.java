package radar.Tools;

import java.util.Date;

public class Records {
	
	private Integer id;
	private Date time;
	private String timeb;
	private int dev;
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
	public String getTimeb() {
		return timeb;
	}
	public void setTimeb(String timeb) {
		this.timeb = timeb;
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
