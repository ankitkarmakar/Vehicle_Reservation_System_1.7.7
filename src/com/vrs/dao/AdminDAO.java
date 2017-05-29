package com.vrs.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.vrs.constants.Query;
import com.vrs.constants.ResourceClosing;

import com.vrs.dto.UserDTO;
import com.vrs.dto.VehicleDTO;
import com.vrs.exceptions.VRSDaoException;
import com.vrs.utils.DbUtil;

public class AdminDAO {
	private static final Logger LOG = Logger.getLogger(AdminDAO.class);

	/**
	 * @ desc This method will be used to add newly launched vehicle in main
	 * stock by admin
	 * 
	 * @param vehicle
	 * @return
	 * @throws VRSDaoException 
	 */
	public String addNewVehicle(VehicleDTO vehicle) throws VRSDaoException
			 {
		Connection connection = null;
		PreparedStatement prepareStatment = null;
		ResultSet rs = null;
		int count = 0;
		int numberOfRows = 0;
		int existingStock = 0;
		int updatedRows;
		String returnMessage = null;
		LOG.info("INSIDE:: addNewVehicle method of Admin");
		try {

			LOG.info("BEFORE:: Connection to the database");
			connection = DbUtil.getConnection();

			LOG.debug("BEFORE::executing this query::"
					+ Query.COUNT_VEHICLE_QUERY);
			prepareStatment = connection
					.prepareStatement(Query.COUNT_VEHICLE_QUERY);
			prepareStatment.setString(1, vehicle.getVehicleId());
			rs = prepareStatment.executeQuery();
			LOG.debug("AFTER::executing this query::"
					+ Query.COUNT_VEHICLE_QUERY);
			if (rs.next()) {
				numberOfRows = rs.getInt("count_vehicle");
			} 

			LOG.info("BEFORE::   addition of vehicle to the main stock");
			if (numberOfRows == 0) {

				LOG.debug("BEFORE::executing this query::"
						+ Query.ADD_NEW_VEHICLE_QUERY);
				prepareStatment = connection
						.prepareStatement(Query.ADD_NEW_VEHICLE_QUERY);
				prepareStatment.setString(1, vehicle.getVehicleId());
				prepareStatment.setString(2, vehicle.getManufactureName());
				prepareStatment.setInt(3, vehicle.getExShowroomPrice());
				prepareStatment.setInt(4, vehicle.getRent());
				prepareStatment.setString(5, vehicle.getVehicleType());
				prepareStatment.setString(6, vehicle.getColor());
				prepareStatment.setInt(7, vehicle.getSeat());
				prepareStatment.setInt(8, vehicle.getNumberOfVehicle());
				prepareStatment.setString(9, vehicle.getVehicleName());
				count = prepareStatment.executeUpdate();
				LOG.debug("AFTER::executing this query::"
						+ Query.ADD_NEW_VEHICLE_QUERY);
				if (count > 0) {
					returnMessage = "The vehicle is succesfully added to our main stock";
					LOG.info("AFTER::   addition of vehicle to the main stock");
				}
			} else {
				LOG.debug("BEFORE::executing this query::"
						+ Query.SELECT_EXISTING_VEHICLE_MAIN_STOCK_QUERY);
				prepareStatment = connection
						.prepareStatement(Query.SELECT_EXISTING_VEHICLE_MAIN_STOCK_QUERY);
				prepareStatment.setString(1, vehicle.getVehicleId());
				rs = prepareStatment.executeQuery();
				LOG.debug("AFTER::executing this query::"
						+ Query.SELECT_EXISTING_VEHICLE_MAIN_STOCK_QUERY);
				if (rs.next()) {
					existingStock = rs.getInt("total_stock");
				}
				LOG.info("BEFORE:: UPDATION of vehicle to the main stock");
				LOG.debug("BEFORE::executing this query::"
						+ Query.ADD_VEHICLE_MAIN_STOCK_QUERY);
				prepareStatment = connection
						.prepareStatement(Query.ADD_VEHICLE_MAIN_STOCK_QUERY);

				prepareStatment.setInt(1, vehicle.getNumberOfVehicle()
						+ existingStock);
				prepareStatment.setString(2, vehicle.getVehicleId());
				prepareStatment.setString(3, vehicle.getManufactureName());
				prepareStatment.setInt(4, vehicle.getExShowroomPrice());
				prepareStatment.setInt(5, vehicle.getRent());
				prepareStatment.setString(6, vehicle.getVehicleType());
				prepareStatment.setString(7, vehicle.getColor());
				prepareStatment.setInt(8, vehicle.getSeat());
				prepareStatment.setString(9, vehicle.getVehicleName());
				updatedRows = prepareStatment.executeUpdate();

				LOG.debug("AFTER::executing this query::"
						+ Query.ADD_VEHICLE_MAIN_STOCK_QUERY);

				if (updatedRows == 0) {
					returnMessage = "The given vehicle id already exists try another vehicle id";
					LOG.info("The given vehicle id already exists try another vehicle id");
				} else {
					returnMessage = "The stock of the given vehicle id is succesfully updated in our main stock";
					LOG.info("AFTER:: UPDATION of vehicle to the main stock");
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Exeption exception occured");
		} finally {
			ResourceClosing.closeResources(rs, connection, prepareStatment);
			

		}
		return returnMessage;

	}

	/**
	 * This method is used to fetch the vehicle requested list
	 * 
	 * @param first
	 * @param last
	 * @return
	 * @throws VRSDaoException
	 */
	public List<VehicleDTO> getVehicleRequestList(int first, int last)
			throws VRSDaoException {

		Connection dbConnection = null;
		LOG.info("INSIDE:: getVehicleRequestList method of UserDAO");
		PreparedStatement preparedStatement = null;
		String vehicleId;
		String vehicleRequestId;
		ResultSet rs=null;

		List<VehicleDTO> vehicleDetailsList = new ArrayList<VehicleDTO>();

		try {

			LOG.info("BEFORE:: Connection to the database");
			dbConnection = DbUtil.getConnection();
			LOG.info("BEFORE::  getVehicleRequestList");
			LOG.debug("BEFORE::executing this query::"
					+ Query.GET_VEHICLE_LIST_QUERY);
			preparedStatement = dbConnection
					.prepareStatement(Query.GET_VEHICLE_LIST_QUERY);

			preparedStatement.setInt(1, first);
			preparedStatement.setInt(2, last);

			 rs = preparedStatement.executeQuery();
			LOG.debug("AFTER::executing this query::"
					+ Query.GET_VEHICLE_LIST_QUERY);
			while (rs.next()) {
				VehicleDTO vehicle = new VehicleDTO();
				vehicleId = rs.getString("v_id");

				vehicleRequestId = rs.getString("v_r_id");
				vehicle.setVehicleId(vehicleId);
				vehicle.setVehicleRequestId(vehicleRequestId);
				vehicle.setRequestDate(new java.util.Date(rs.getDate("r_date")
						.getTime()));
				vehicle.setBranchID(rs.getString("b_id"));
				vehicle.setNumberOfVehicle(rs.getInt("n_o_v"));
				vehicleDetailsList.add(vehicle);
			}

			LOG.info("AFTER::  getVehicleRequestList");
		} catch (Exception e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Error: Failed to Get vehicle List");

		} finally {
			ResourceClosing.closeResources(rs, dbConnection, preparedStatement);
		}
		return vehicleDetailsList;
	}

	/**
	 * desc This method is used to approve or reject the vehicle request
	 * 
	 * @param approveList
	 * @param setStatus
	 * @param noOfVehicles
	 * @return
	 * @throws VRSDaoException
	 */
	public String approveOrRejectVehicleRequest(List<String> approveList,
			String setStatus, int noOfVehicles) throws  VRSDaoException {
		LOG.info("INSIDE:: approveOrRejectVehicleRequest method of UserDAO");
		Connection dbConnection = null;
		@SuppressWarnings("unused")
		Boolean status = false;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String vehicleRequestId;
		int numberOfRecordsAffected;
		String vehicleId = null;
		String branchId = null;
		@SuppressWarnings("unused")
		int numberOfVehicleRequested = 0;
		int rowsReturned = 0;
		String returnMessage = null;
		int vehicleMainStock = 0;
		int branchVehicleStock = 0;
		int countRecords = 0;
		try {
			LOG.info("BEFORE:: Connection to the database");
			dbConnection = DbUtil.getConnection();

			LOG.info("BEFORE:: approve the vehicle list");
			for (int index = 0; index < approveList.size(); index++) {
				vehicleRequestId = approveList.get(index);
				if (setStatus == "approved") {
					LOG.debug("BEFORE::executing this query::"
							+ Query.FETCH_VEHICLE_REQUEST_QUERY);
					preparedStatement = dbConnection
							.prepareStatement(Query.FETCH_VEHICLE_REQUEST_QUERY);
					preparedStatement.setString(1, vehicleRequestId);
					resultSet = preparedStatement.executeQuery();
					LOG.debug("AFTER::executing this query::"
							+ Query.FETCH_VEHICLE_REQUEST_QUERY);
					if (resultSet.next()) {
						numberOfVehicleRequested = resultSet
								.getInt("number_of_vehicle");

						branchId = resultSet.getString("branch_id");
						vehicleId = resultSet.getString("vehicle_id");

					}
					LOG.debug("BEFORE::executing this query::"
							+ Query.COUNT_VEHICLE_QUERY);
					preparedStatement = dbConnection
							.prepareStatement(Query.COUNT_VEHICLE_QUERY);
					preparedStatement.setString(1, vehicleId);
					resultSet = preparedStatement.executeQuery();
					LOG.debug("AFTER::executing this query::"
							+ Query.COUNT_VEHICLE_QUERY);
					if (resultSet.next()) {
						rowsReturned = resultSet.getInt("count_vehicle");
					}
					LOG.debug("BEFORE::executing this query::"
							+ Query.GET_VEHICLE_MAIN_STOCK_QUERY);
					preparedStatement = dbConnection
							.prepareStatement(Query.GET_VEHICLE_MAIN_STOCK_QUERY);
					preparedStatement.setString(1, vehicleId);
					resultSet = preparedStatement.executeQuery();
					LOG.debug("AFTER::executing this query::"
							+ Query.GET_VEHICLE_MAIN_STOCK_QUERY);
					if (resultSet.next()) {
						vehicleMainStock = resultSet.getInt("total_stock");
					}
					if (rowsReturned > 0) {
						if (vehicleMainStock >= noOfVehicles) {
							LOG.debug("BEFORE::executing this query::"
									+ Query.APPROVE_OR_REJECT_VEHICLE_REQUEST_QUERY);
							preparedStatement = dbConnection
									.prepareStatement(Query.APPROVE_OR_REJECT_VEHICLE_REQUEST_QUERY);
							preparedStatement.setString(1, setStatus);
							preparedStatement.setString(2, vehicleRequestId);
							numberOfRecordsAffected = preparedStatement
									.executeUpdate();
							LOG.debug("AFTER::executing this query::"
									+ Query.APPROVE_OR_REJECT_VEHICLE_REQUEST_QUERY);
							if (numberOfRecordsAffected > 0) {
								returnMessage = "vehicle request with vehicle request id "
										+ vehicleRequestId
										+ " is approved succesfully. "
										+ noOfVehicles
										+ " vehicles are approved";
								vehicleMainStock = vehicleMainStock
										- noOfVehicles;
								LOG.debug("BEFORE::executing this query::"
										+ Query.UPDATE_VEHICLE_MAIN_STOCK_QUERY);
								preparedStatement = dbConnection
										.prepareStatement(Query.UPDATE_VEHICLE_MAIN_STOCK_QUERY);
								preparedStatement.setInt(1, vehicleMainStock);
								preparedStatement.setString(2, vehicleId);
								preparedStatement.executeUpdate();
								LOG.debug("AFTER::executing this query::"
										+ Query.UPDATE_VEHICLE_MAIN_STOCK_QUERY);
								LOG.debug("BEFORE::executing this query::"
										+ Query.COUNT_BRANCH_LOCATION_RECORDS_QUERY);
								preparedStatement = dbConnection
										.prepareStatement(Query.COUNT_BRANCH_LOCATION_RECORDS_QUERY);
								preparedStatement.setString(1, vehicleId);
								preparedStatement.setString(2, branchId);
								resultSet = preparedStatement.executeQuery();
								LOG.debug("AFTER::executing this query::"
										+ Query.COUNT_BRANCH_LOCATION_RECORDS_QUERY);
								if (resultSet.next()) {
									countRecords = resultSet
											.getInt("count_branch_location_records");
								}

								if (countRecords > 0) {
									LOG.debug("BEFORE::executing this query::"
											+ Query.FETCH_BRANCH_EXISTING_VEHICLE_STOCK_QUERY);
									preparedStatement = dbConnection
											.prepareStatement(Query.FETCH_BRANCH_EXISTING_VEHICLE_STOCK_QUERY);
									preparedStatement.setString(1, vehicleId);
									preparedStatement.setString(2, branchId);
									resultSet = preparedStatement
											.executeQuery();
									LOG.debug("AFTER::executing this query::"
											+ Query.FETCH_BRANCH_EXISTING_VEHICLE_STOCK_QUERY);
									if (resultSet.next()) {
										branchVehicleStock = resultSet
												.getInt("branch_vehicle_stock");
									}
									branchVehicleStock = branchVehicleStock
											+ noOfVehicles;
									LOG.debug("BEFORE::executing this query::"
											+ Query.BRANCH_VEHICLE_UPDATE_QUERY);
									preparedStatement = dbConnection
											.prepareStatement(Query.BRANCH_VEHICLE_UPDATE_QUERY);
									preparedStatement.setInt(1,
											branchVehicleStock);
									preparedStatement.setString(2, vehicleId);
									preparedStatement.setString(3, branchId);
									preparedStatement.executeUpdate();
									LOG.debug("AFTER::executing this query::"
											+ Query.BRANCH_VEHICLE_UPDATE_QUERY);
								} else if (countRecords == 0) {
									LOG.debug("BEFORE::executing this query::"
											+ Query.INSERT_RECORDS_INTO_VEHICLE_LOCATION);
									preparedStatement = dbConnection
											.prepareStatement(Query.INSERT_RECORDS_INTO_VEHICLE_LOCATION);
									preparedStatement.setString(1, vehicleId);
									preparedStatement.setString(2, branchId);
									preparedStatement.setInt(3, noOfVehicles);

									preparedStatement.executeUpdate();
									LOG.debug("AFTER::executing this query::"
											+ Query.INSERT_RECORDS_INTO_VEHICLE_LOCATION);

								}

							}
						} else {
							returnMessage = "The number of vehicle you want to approved  for Vehicle request id "
									+ vehicleRequestId
									+ " is not available in main stock";
						}
					} else {
						returnMessage = "The vehicle requested by Branch Admin for request id "
								+ vehicleRequestId
								+ " is not available in main stock";
					}
					LOG.info("AFTER:: approve  the vehicle list");
				} else if (setStatus == "rejected") {

					LOG.info("BEFORE::  Reject the vehicle list");
					LOG.debug("BEFORE::executing this query::"
							+ Query.APPROVE_OR_REJECT_VEHICLE_REQUEST_QUERY);
					preparedStatement = dbConnection
							.prepareStatement(Query.APPROVE_OR_REJECT_VEHICLE_REQUEST_QUERY);
					preparedStatement.setString(1, setStatus);
					preparedStatement.setString(2, vehicleRequestId);
					numberOfRecordsAffected = preparedStatement.executeUpdate();
					LOG.debug("AFTER::executing this query::"
							+ Query.GET_VEHICLE_LIST_QUERY);
					if (numberOfRecordsAffected > 0) {
						returnMessage = "vehicle request with vehicle request id "
								+ vehicleRequestId + " is rejected succesfully";
					}
					LOG.info("AFTER::  Reject the vehicle list");
				}

			}

		} catch (SQLException e) {
			LOG.error("Exception occured . " + e.getCause());
			status = false;
			throw new VRSDaoException("SQLException  occured");

		} catch (ClassNotFoundException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("ClassNotFoundException  occured");
		} catch (IOException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("IOException  occured");
		} finally {
			ResourceClosing.closeResources(resultSet, dbConnection, preparedStatement);
		}

		return returnMessage;

	}

	/**
	 * @desc This method is used to get the list of pending registration
	 * @param choice
	 * @return
	 * @throws VRSDaoException 
	 */
	public List<UserDTO> getUserRegistrationList(int choice) throws VRSDaoException {

		Connection dbConnection = null;

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;

		LOG.info("INSIDE:: getUserRegistrationList method of UserDAO");

		List<UserDTO> userRegistrationRequestList = new ArrayList<UserDTO>();

		try {

			LOG.info("BEFORE:: Connection to the database");
			dbConnection = DbUtil.getConnection();
			LOG.info("BEFORE::fetching the Registration List");
			if (choice == 1) {
				LOG.debug("BEFORE::executing this query::"
						+ Query.GET_PENDING_REGISTRATION_REQUEST_LIST_QUERY);
				preparedStatement = dbConnection
						.prepareStatement(Query.GET_PENDING_REGISTRATION_REQUEST_LIST_QUERY);
				LOG.debug("AFTER::executing this query::"
						+ Query.GET_PENDING_REGISTRATION_REQUEST_LIST_QUERY);
			} else if (choice == 2) {
				LOG.debug("BEFORE::executing this query::"
						+ Query.GET_ALL_REGISTRATION_REQUEST_LIST_QUERY);
				preparedStatement = dbConnection
						.prepareStatement(Query.GET_ALL_REGISTRATION_REQUEST_LIST_QUERY);
				LOG.debug("AFTER::executing this query::"
						+ Query.GET_ALL_REGISTRATION_REQUEST_LIST_QUERY);

			}

			 rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UserDTO user = new UserDTO();
				user.setUserId(rs.getInt("user_id"));

				user.setRole(rs.getString("role_name"));
				user.setAccounDate(rs.getDate("account_date"));
				user.setFlag(rs.getString("flag"));
				userRegistrationRequestList.add(user);

			}

			LOG.info("AFTER::fetching the Registration List");

		} catch (Exception e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Exception  occured");
		} finally {
			ResourceClosing.closeResources(rs, dbConnection, preparedStatement);
		}
		return userRegistrationRequestList;
	}

	/**
	 * desc this method is used to approve or reject registration request
	 * 
	 * @param RegistrationRequestList
	 * @param setStatus
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws VRSDaoException
	 */
	public Boolean approveOrRejectRegistration(
			List<Integer> RegistrationRequestList, String setStatus)
			throws  VRSDaoException {
		LOG.info("INSIDE:: approveOrRejectRegistration method of UserDAO");
		PreparedStatement prepareStatement = null;
		String query;
		int returnRows;
		int j = 2;
		Boolean returnStatus = false;
		Connection dbConnection = null;
		ResultSet resultStatement = null;
		try {

			LOG.info("BEFORE:: Connection to the database");
			dbConnection = DbUtil.getConnection();
			LOG.info("BEFORE:: approve Or Reject the Registration list");
			query = "update user_table set flag=? where user_id in (";
			StringBuilder queryBuilder = new StringBuilder(query);
			for (int i = 0; i < RegistrationRequestList.size(); i++) {
				queryBuilder.append(" ?");
				if (i != RegistrationRequestList.size() - 1)
					queryBuilder.append(",");
			}
			query = queryBuilder.append(")").toString();

			prepareStatement = dbConnection.prepareStatement(query);

			prepareStatement.setString(1, setStatus);
			for (int i = 0; i < RegistrationRequestList.size(); i++) {
				prepareStatement.setInt(j, RegistrationRequestList.get(i));
				j++;
			}
			LOG.debug("BEFORE::executing this query::");
			returnRows = prepareStatement.executeUpdate();
			LOG.debug("AFTER::executing this query::");
			if (returnRows > 0) {
				returnStatus = true;
				LOG.info("AFTER:: approve Or Reject the Registration list");
			} else {
				returnStatus = false;
			}

		} catch (Exception e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Exeption exception occured");
		} finally {
			ResourceClosing.closeResources(resultStatement, dbConnection, prepareStatement);
		}

		return returnStatus;

	}

	/**
	 * @desc This method is used to get the list of pending registration by
	 *       selecting the role of the user
	 * @param choice
	 * @return
	 * @throws VRSDaoException 
	 */
	public List<UserDTO> getPendingUserRegistrationListByRole(String role) throws VRSDaoException {

		Connection dbConnection = null;
		ResultSet rs=null;

		PreparedStatement preparedStatement = null;

		LOG.info("INSIDE:: getPendingUserRegistrationListByRole method of UserDAO");

		List<UserDTO> userRegistrationRequestList = new ArrayList<UserDTO>();

		try {

			LOG.info("BEFORE:: Connection to the database");
			dbConnection = DbUtil.getConnection();
			LOG.info("BEFORE::fetching the Pending the Registration List");
			preparedStatement = dbConnection
					.prepareStatement(Query.GET_PENDING_REGISTRATION_REQUEST_LIST_BY_ROLE_QUERY);

			preparedStatement.setString(1, role);

			LOG.debug("BEFORE::executing this query::"
					+ Query.GET_PENDING_REGISTRATION_REQUEST_LIST_BY_ROLE_QUERY);
			rs = preparedStatement.executeQuery();
			LOG.debug("AFTER::executing this query::"
					+ Query.GET_PENDING_REGISTRATION_REQUEST_LIST_BY_ROLE_QUERY);
			LOG.debug("AFTER::executing this query::");
			while (rs.next()) {
				UserDTO user = new UserDTO();
				user.setUserId(rs.getInt("user_id"));

				user.setRole(rs.getString("role_name"));
				user.setAccounDate(rs.getDate("account_date"));
				user.setFlag(rs.getString("flag"));
				userRegistrationRequestList.add(user);

			}

			LOG.info("AFTER::fetching the Pending the Registration List");

		} catch (Exception e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Exeption exception occured");
		} finally {
			ResourceClosing.closeResources(rs, dbConnection, preparedStatement);
		}
		return userRegistrationRequestList;
	}

	/**
	 * @desc This method is used to get the list of pending registration by
	 *       selecting the status of user
	 * @param choice
	 * @return
	 * @throws VRSDaoException 
	 */
	public List<UserDTO> getPendingUserRegistrationListByStatus(String status) throws VRSDaoException {

		Connection dbConnection = null;

		PreparedStatement preparedStatement = null;
		ResultSet rs =null;

		LOG.info("INSIDE:: getPendingUserRegistrationListByStatus method of UserDAO");

		List<UserDTO> userRegistrationRequestList = new ArrayList<UserDTO>();

		try {

			LOG.info("BEFORE:: Connection to the database");
			dbConnection = DbUtil.getConnection();
			LOG.info("BEFORE::fetching the Pending the Registration List BY status");
			LOG.debug("BEFORE::executing this query::"
					+ Query.GET_PENDING_REGISTRATION_REQUEST_LIST_BY_STATUS_QUERY);
			preparedStatement = dbConnection
					.prepareStatement(Query.GET_PENDING_REGISTRATION_REQUEST_LIST_BY_STATUS_QUERY);

			preparedStatement.setString(1, status);

			rs = preparedStatement.executeQuery();
			LOG.debug("AFTER::executing this query::"
					+ Query.GET_PENDING_REGISTRATION_REQUEST_LIST_BY_STATUS_QUERY);
			while (rs.next()) {
				UserDTO user = new UserDTO();
				user.setUserId(rs.getInt("user_id"));

				user.setRole(rs.getString("role_name"));
				user.setAccounDate(rs.getDate("account_date"));
				user.setFlag(rs.getString("flag"));
				userRegistrationRequestList.add(user);

			}

			LOG.info("AFTER::fetching the Pending the Registration List BY status");

		} catch (Exception e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Exeption exception occured");
		} finally {
			ResourceClosing.closeResources(rs, dbConnection, preparedStatement);
		}
		return userRegistrationRequestList;
	}
}
