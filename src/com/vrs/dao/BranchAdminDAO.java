package com.vrs.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.vrs.constants.Query;
import com.vrs.constants.ResourceClosing;
import com.vrs.dto.BookingDTO;
import com.vrs.dto.BranchAdminDTO;
import com.vrs.dto.UserDTO;
import com.vrs.dto.VehicleDTO;
import com.vrs.exceptions.VRSDaoException;
import com.vrs.utils.DbUtil;

public class BranchAdminDAO {
	Connection connection = null;
	PreparedStatement ps = null;

	ResultSet rs = null;
	int count = 0;
	boolean present = false;
	int numberOfRows;
	PreparedStatement prepareStatement = null;
	String returnMessage = null;
	private static final Logger LOG = Logger.getLogger(BranchAdminDAO.class);

	/**
	 * @desc This method is used to insert the branch admin details
	 * @param branchAdminDTO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */

	public String updateBranchAdminDetails(BranchAdminDTO branchAdminDTO)
			throws VRSDaoException {
		int updatedRows = 0;
		int numberOfUpdatedRows = 0;
		String branchId = null;
		try {

			Connection connection = DbUtil.getConnection();
			LOG.debug("BEFORE::Executing this query::"
					+ Query.COUNT_BRANCHADMIN_QUERY);

			prepareStatement = connection
					.prepareStatement(Query.COUNT_BRANCHADMIN_QUERY);

			prepareStatement.setInt(1, branchAdminDTO.getUserId());

			rs = prepareStatement.executeQuery();
			LOG.debug("AFTER::Executing this query::"
					+ Query.COUNT_BRANCHADMIN_QUERY);

			if (rs.next()) {
				numberOfRows = rs.getInt(1);
			}

			if (numberOfRows == 0) {

				LOG.debug("BEFORE:: EXECUTING THE QUERY::"
						+ Query.GET_BRANCH_ID_FROM_BRANCH);
				ps = connection
						.prepareStatement(Query.GET_BRANCH_ID_FROM_BRANCH);
				ps.setString(1, branchAdminDTO.getBranchLocation());
				ResultSet rs = ps.executeQuery();
				LOG.debug("AFTER:: EXECUTING THE QUERY::"
						+ Query.GET_BRANCH_ID_FROM_BRANCH);
				while (rs.next()) {
					branchId = rs.getString("branch_id");
				}

				ps.close();
				LOG.info("AFTER:: The preparedstatement is closed");

				LOG.debug("BEFORE::Executing this query::"
						+ Query.INSERT_BRANCH_ADMIN_DETAILS);
				ps = connection
						.prepareStatement(Query.INSERT_BRANCH_ADMIN_DETAILS);
				ps.setInt(1, branchAdminDTO.getUserId());
				ps.setString(2, branchAdminDTO.getAddress());
				ps.setString(3, branchAdminDTO.getBranchLocation());

				ps.setString(4, branchAdminDTO.getPhoneNo());
				ps.setString(5, branchAdminDTO.getMailid());
				ps.setString(6, branchId);
				count = ps.executeUpdate();
				LOG.debug("AFTER::Executing this query::"
						+ Query.INSERT_BRANCH_ADMIN_DETAILS);
				if (count > 0) {
					returnMessage = "The Details Are  Successfully registered";
				}

			} else {
				LOG.debug("BEFORE::Executing this query::"
						+ Query.UPDATE_BRANCH_ADMIN_DETAILS);
				ps = connection
						.prepareStatement(Query.UPDATE_BRANCH_ADMIN_DETAILS);
				ps.setString(1, branchAdminDTO.getAddress());
				ps.setString(2, branchAdminDTO.getBranchLocation());

				ps.setString(3, branchAdminDTO.getPhoneNo());
				ps.setString(4, branchAdminDTO.getMailid());
				ps.setInt(5, branchAdminDTO.getUserId());

				numberOfUpdatedRows = ps.executeUpdate();
				LOG.debug("AFTER::Executing this query::"
						+ Query.UPDATE_BRANCH_ADMIN_DETAILS);

				LOG.debug("BEFORE::Executing this query::"
						+ Query.GET_BRANCH_ADMIN_BRANCH_ID);
				ps = connection
						.prepareStatement(Query.GET_BRANCH_ADMIN_BRANCH_ID);
				ps.setInt(1, branchAdminDTO.getUserId());
				ResultSet rs = ps.executeQuery();
				LOG.debug("AFTER:: EXECUTING THE QUERY::"
						+ Query.GET_BRANCH_ADMIN_BRANCH_ID);

				while (rs.next()) {
					branchId = rs.getString("branch_id");
				}

				ps.close();
				LOG.info("AFTER:: The preparedstatement is closed");

				LOG.debug("BEFORE:: EXECUTING THE QUERY::"
						+ Query.BRANCH_UPDATE);
				ps = connection.prepareStatement(Query.BRANCH_UPDATE);

				ps.setString(1, branchAdminDTO.getBranchLocation());
				ps.setString(2, branchId);
				updatedRows = ps.executeUpdate();
				LOG.debug("AFTER:: EXECUTING THE QUERY::" + Query.BRANCH_UPDATE);
			}

			if (updatedRows != 0 || numberOfUpdatedRows != 0) {
				returnMessage = "Details are Successfully Updated";

			}
		} catch (SQLException se) {
			LOG.error("Exception occured . " + se.getCause());
			throw new VRSDaoException("SQLExeption exception occured");
		} catch (Exception e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Exeption exception occured");
		} finally {
			ResourceClosing.closeResources(rs, connection, prepareStatement);
		}
		return returnMessage;

	}

	
	/**
	 * @param vdto
	 * @return
	 * @throws VRSDaoException
	 */
	public int vehicleRequest(VehicleDTO vdto) throws VRSDaoException {
		int incrementId = 0;
		StringBuffer vehicleRequestId;
		try {
			connection = DbUtil.getConnection();
			LOG.info("INSIDE:: vehicleRequest method of BranchAdminDAO");
			LOG.debug("BEFORE::executing this query::"
					+ Query.VEHICLE_ID_FETCH_QUERY);
			ps = connection.prepareStatement(Query.VEHICLE_ID_FETCH_QUERY); // for
																			// fetching
																			// last
																			// vehicle
																			// id
																			// from
																			// database
			rs = ps.executeQuery();
			LOG.debug("AFTER::executing this query::"
					+ Query.VEHICLE_ID_FETCH_QUERY);
			rs.next();
			vehicleRequestId = new StringBuffer();
			incrementId = (rs.getInt("max_vehicle_id")) + 1;
			vehicleRequestId.append("vrq").append(incrementId);
			vdto.setVehicleRequestId(vehicleRequestId.toString());

			LOG.debug("BEFORE::executing this query::"
					+ Query.VEHICLE_REQUEST_QUERY);
			ps = connection.prepareStatement(Query.VEHICLE_REQUEST_QUERY); // for
																			// inserting
																			// new
																			// request
																			// into
																			// database
			ps.setString(1, vdto.getVehicleRequestId());
			ps.setString(2, vdto.getBranchID());
			Date date = new Date();
			ps.setDate(3, new java.sql.Date(date.getTime()));
			ps.setString(4, vdto.getVehicleId());
			ps.setInt(5, vdto.getNumberOfVehicle());
			ps.setString(6, "pending");
			count = ps.executeUpdate();
			LOG.debug("BEFORE::executing this query::"
					+ Query.VEHICLE_REQUEST_QUERY);

			if (count > 0) {
				connection.commit();
			}
		} catch (ClassNotFoundException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Class not found exception occured");
		} catch (IOException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("IOExeption exception occured");
		} catch (SQLException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("SQLException exception occured");
		} finally {
			ResourceClosing.closeResources(rs, connection, prepareStatement);
		}
		return count;
	}

	/**
	 * @param bookingDTO
	 * @param first
	 * @param last
	 * @return
	 * @throws VRSDaoException
	 */
	public List<BookingDTO> searchAllVehicleBooking(BookingDTO bookingDTO,
			int first, int last) throws VRSDaoException {
		List<BookingDTO> bookingList = new ArrayList<BookingDTO>();
		LOG.info("INSIDE:: searchAllVehicleBooking method of BranchAdminDAO");
		try {
			connection = DbUtil.getConnection();
			LOG.debug("BEFORE::executing the query::"
					+ Query.ALL_VEHICLE_SEARCH_QUERY);
			ps = connection.prepareStatement(Query.ALL_VEHICLE_SEARCH_QUERY);
			ps.setInt(1, bookingDTO.getBranchAdminID());
			ps.setString(2, bookingDTO.getVehicleID());
			ps.setInt(3, first);
			ps.setInt(4, last);
			rs = ps.executeQuery();
			LOG.debug("AFTER::executing the query::"
					+ Query.ALL_VEHICLE_SEARCH_QUERY);

			while (rs.next()) {
				String bookingID = rs.getString("b_id");
				BookingDTO bdto = new BookingDTO();
				bdto.setBookingID(bookingID);
				bdto.setBookingDate(rs.getDate("b_time"));
				bdto.setCustomerID(rs.getInt("c_id"));
				bdto.setVehicleName(rs.getString("v_name"));
				bdto.setManufacturerName(rs.getString("m_name"));
				bdto.setExShowroomPrice(rs.getInt("e_s_price"));
				bdto.setColor(rs.getString("col"));
				bookingList.add(bdto);
			}

		} catch (ClassNotFoundException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Error: Class Not Found");
		} catch (IOException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Error: Class Not Found");
		} catch (SQLException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Error: Class Not Found");
		}finally {
			ResourceClosing.closeResources(rs, connection, prepareStatement);
		}
		return bookingList;
	}

	/**
	 * @param bookingDTO
	 * @return
	 * @throws VRSDaoException
	 */
	public int approveBooking(BookingDTO bookingDTO) throws VRSDaoException {
		int status = 0;
		int branchVehicleStock = 0;
		int newBranchVehicleStock = 0;
		String branchID = null;
		LOG.info("INSIDE:: approveBooking method of BranchAdminDAO");
		try {
			connection = DbUtil.getConnection();
			LOG.debug("BEFORE::executing the query::"
					+ Query.GET_VEHICLE_STOCK_QUERY);
			ps = connection.prepareStatement(Query.GET_VEHICLE_STOCK_QUERY);
			ps.setString(1, bookingDTO.getVehicleID());
			ps.setInt(2, bookingDTO.getBranchAdminID());

			rs = ps.executeQuery();
			LOG.debug("AFTER::executing the query::"
					+ Query.GET_VEHICLE_STOCK_QUERY);
			while (rs.next()) {
				branchVehicleStock = rs.getInt("BRANCH_VEHICLE_STOCK");
				branchID = rs.getString("b_id");
			}
			
			ps.close();
			LOG.info("prepareStatement is closed");
			if (branchVehicleStock >= 1) {
				newBranchVehicleStock = branchVehicleStock - 1;
				LOG.debug("BEFORE::executing the query::update vehicle_location set vehicle_location.branch_vehicle_stock=? where vehicle_location.vehicle_id=? and vehicle_location.branch_id=?");
				ps = connection
						.prepareStatement(Query.UPDATE_VEHICLE_LOCATION_FOR_BOOKING_QUERY);
				ps.setInt(1, newBranchVehicleStock);
				ps.setString(2, bookingDTO.getVehicleID());
				ps.setString(3, branchID);
				int s = ps.executeUpdate();
				LOG.debug("AFTER::executing the query::update vehicle_location set vehicle_location.branch_vehicle_stock=? where vehicle_location.vehicle_id=? and vehicle_location.branch_id=?");
				ps.close();
				LOG.info("AFTER::preparedStatement is closed");
				if (s > 0) {
					LOG.debug("BEFORE::executing the query::update booking set booking.booking_confirmed_date=? , booking.booking_approve_flag=? where booking.booking_id=?");
					ps = connection
							.prepareStatement(Query.UPDATE_BOOKING_QUERY);
					ps.setDate(1, new java.sql.Date(bookingDTO
							.getBookingApprovedDate().getTime()));
					ps.setString(2, bookingDTO.getBookingApprovedFlag());
					ps.setString(3, bookingDTO.getBookingID());
					ps.executeUpdate();
					LOG.debug("AFTER::executing the query::update booking set booking.booking_confirmed_date=? , booking.booking_approve_flag=? where booking.booking_id=?");
					ps.close();

					LOG.info("AFTER::preparedStatement is closed");
					LOG.debug("BEFORE::executing the query::"
							+ Query.GET_NEW_VEHICLE_STOCK_QUERY);
					ps = connection
							.prepareStatement(Query.GET_NEW_VEHICLE_STOCK_QUERY);
					ps.setString(1, bookingDTO.getVehicleID());
					rs = ps.executeQuery();
					while (rs.next()) {
						status = rs.getInt("BRANCH_VEHICLE_STOCK");
					}
					LOG.debug("executing the query::"
							+ Query.GET_NEW_VEHICLE_STOCK_QUERY + "status="
							+ status);
					LOG.debug("AFTER::executing the query::"
							+ Query.GET_NEW_VEHICLE_STOCK_QUERY);
					status=newBranchVehicleStock;
				}
			} else {
				status = -9999;
			}

		} catch (ClassNotFoundException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Error: Class Not Found");
		} catch (IOException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Error: Input & Output Problem");
		} catch (SQLException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Error: Problem Executing SQL");
		} finally {
			ResourceClosing.closeResources(rs, connection, prepareStatement);
		}
		return status;
	}

	/**@desc this method is used to fetch the branch Admin info
	 * @param userDTO
	 * @return
	 * @throws VRSDaoException
	 */
	public BranchAdminDTO getUserInformation(UserDTO userDTO)
			throws VRSDaoException {

		BranchAdminDTO branchAdminDTO = new BranchAdminDTO();
		try {
			connection = DbUtil.getConnection();
			LOG.debug("Execution::"+Query.GET_BRANCHADMIN_DETAILS);
			ps = connection.prepareStatement(Query.GET_BRANCHADMIN_DETAILS);
			ps.setInt(1, userDTO.getUserId());
			rs = ps.executeQuery();

			if (rs.next()) {
				branchAdminDTO.setBranchLocation(rs
						.getString("BRANCH_LOCATION"));
				branchAdminDTO.setAddress(rs.getString("BRANCH_ADDRESS"));
				branchAdminDTO.setPhoneNo(rs.getString("BRANCH_PHONE_NUMBER"));
				branchAdminDTO.setMailid(rs.getString("BRANCH_EMAIL"));
				branchAdminDTO.setBranchID(rs.getString("BRANCH_ID"));
			}
		} catch (SQLException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("SQLException exception occured");
		} catch (ClassNotFoundException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("SQLException exception occured");
		} catch (IOException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("SQLException exception occured");
		} finally {
			ResourceClosing.closeResources(rs, connection, prepareStatement);
		}
		return branchAdminDTO;
	}

}
