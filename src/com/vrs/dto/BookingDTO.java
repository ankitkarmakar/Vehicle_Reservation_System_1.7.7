package com.vrs.dto;

import java.util.Date;

public class BookingDTO {

	private int customerID;
	private int branchAdminID;
	private String bookingID;
	private String vehicleName;
	private String vehicleID;
	private String manufacturerName;
	private String bookingApprovedFlag;
	private String branchID;
	private Date bookingDate;
	private Date bookingApprovedDate;
	private String color;
	private int rent;
	public int getRent() {
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	private int exShowroomPrice;
	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}
	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	/**
	 * @return the branchAdminID
	 */
	public int getBranchAdminID() {
		return branchAdminID;
	}
	/**
	 * @param branchAdminID the branchAdminID to set
	 */
	public void setBranchAdminID(int branchAdminID) {
		this.branchAdminID = branchAdminID;
	}
	/**
	 * @return the bookingID
	 */
	public String getBookingID() {
		return bookingID;
	}
	/**
	 * @param bookingID the bookingID to set
	 */
	public void setBookingID(String bookingID) {
		this.bookingID = bookingID;
	}
	/**
	 * @return the vehicleName
	 */
	public String getVehicleName() {
		return vehicleName;
	}
	/**
	 * @param vehicleName the vehicleName to set
	 */
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
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
	 * @return the manufacturerName
	 */
	public String getManufacturerName() {
		return manufacturerName;
	}
	/**
	 * @param manufacturerName the manufacturerName to set
	 */
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	/**
	 * @return the bookingApprovedFlag
	 */
	public String getBookingApprovedFlag() {
		return bookingApprovedFlag;
	}
	/**
	 * @param bookingApprovedFlag the bookingApprovedFlag to set
	 */
	public void setBookingApprovedFlag(String bookingApprovedFlag) {
		this.bookingApprovedFlag = bookingApprovedFlag;
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
	 * @return the bookingDate
	 */
	public Date getBookingDate() {
		return bookingDate;
	}
	/**
	 * @param bookingDate the bookingDate to set
	 */
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	/**
	 * @return the bookingApprovedDate
	 */
	public Date getBookingApprovedDate() {
		return bookingApprovedDate;
	}
	/**
	 * @param bookingApprovedDate the bookingApprovedDate to set
	 */
	public void setBookingApprovedDate(Date bookingApprovedDate) {
		this.bookingApprovedDate = bookingApprovedDate;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the exShowroomPrice
	 */
	public int getExShowroomPrice() {
		return exShowroomPrice;
	}
	/**
	 * @param exShowroomPrice the exShowroomPrice to set
	 */
	public void setExShowroomPrice(int exShowroomPrice) {
		this.exShowroomPrice = exShowroomPrice;
	}
	
	
}
