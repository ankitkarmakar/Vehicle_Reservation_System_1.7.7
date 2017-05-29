package com.vrs.dto;

public class BranchAdminDTO extends UserDTO {
	
	private String branchLocation,address,mailid,phoneNo,branchID;

	/**
	 * @return the branchLocation
	 */
	public String getBranchLocation() {
		return branchLocation;
	}

	/**
	 * @param branchLocation the branchLocation to set
	 */
	public void setBranchLocation(String branchLocation) {
		this.branchLocation = branchLocation;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the mailid
	 */
	public String getMailid() {
		return mailid;
	}

	/**
	 * @param mailid the mailid to set
	 */
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return the branchID
	 */
	public String getBranchID() {
		return branchID;
	}

	/**
	 * @param branchID the branchID to set
	 */
	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}
	
}
