package radar.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
private Integer userId;
private String userName;
private String passWord;
private Integer userStatus; //状态 0:存在；1：已删除 Default：0

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "userId",unique=true,nullable=false, length = 11)
public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassWord() {
	return passWord;
}
public void setPassWord(String passWord) {
	this.passWord = passWord;
}
@Column(columnDefinition = "INT not null default 0")
public Integer getUserStatus() {
	return userStatus;
}
public void setUserStatus(Integer userStatus) {
	this.userStatus = userStatus;
}

}
