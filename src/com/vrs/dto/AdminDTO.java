package com.vrs.dto;

public class AdminDTO extends UserDTO {
private int userid;
private String password;
/**
 * @return the userid
 */
public int getUserid() {
	return userid;
}
/**
 * @param userid the userid to set
 */
public void setUserid(int userid) {
	this.userid = userid;
}
/**
 * @return the password
 */
public String getPassword() {
	return password;
}
/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}


}
