package com.vrs.dto;

import java.util.Date;

public class UserDTO {
public int userId;
public String password;
public String role;
public String flag;
public Date accounDate;

public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getFlag() {
	return flag;
}
public void setFlag(String flag) {
	this.flag = flag;
}
public Date getAccounDate() {
	return accounDate;
}
public void setAccounDate(Date accounDate) {
	this.accounDate = accounDate;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}


}
