package com.vrs.dto;

import java.util.Date;

public class VehicleRequestDTO {
	private String vehicleRequestID;
	private String branchID;
	private Date requestDate;
	private String vehicleID;
	private String numberOfVehicle;
	private String approveRequestFlag;
	private Date approveRequestDate;
	/**
	 * @return the vehicleRequestID
	 */
	public String getVehicleRequestID() {
		return vehicleRequestID;
	}
	/**
	 * @param vehicleRequestID the vehicleRequestID to set
	 */
	public void setVehicleRequestID(String vehicleRequestID) {
		this.vehicleRequestID = vehicleRequestID;
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
	/**
	 * @return the requestDate
	 */
	public Date getRequestDate() {
		return requestDate;
	}
	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	/**
	 * @return the vehicleID
	 */
	public String getVehicleID() {
		return vehicleID;
	}
	/**
	 * @param vehicleID the vehicleID to set
	 */
	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}
	/**
	 * @return the numberOfVehicle
	 */
	public String getNumberOfVehicle() {
		return numberOfVehicle;
	}
	/**
	 * @param numberOfVehicle the numberOfVehicle to set
	 */
	public void setNumberOfVehicle(String numberOfVehicle) {
		this.numberOfVehicle = numberOfVehicle;
	}
	/**
	 * @return the approveRequestFlag
	 */
	public String getApproveRequestFlag() {
		return approveRequestFlag;
	}
	/**
	 * @param approveRequestFlag the approveRequestFlag to set
	 */
	public void setApproveRequestFlag(String approveRequestFlag) {
		this.approveRequestFlag = approveRequestFlag;
	}
	/**
	 * @return the approveRequestDate
	 */
	public Date getApproveRequestDate() {
		return approveRequestDate;
	}
	/**
	 * @param approveRequestDate the approveRequestDate to set
	 */
	public void setApproveRequestDate(Date approveRequestDate) {
		this.approveRequestDate = approveRequestDate;
	}
	
	
}
