package com.vrs.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.vrs.constants.Query;
import com.vrs.constants.ResourceClosing;
import com.vrs.dto.UserDTO;
import com.vrs.exceptions.VRSDaoException;
import com.vrs.utils.DbUtil;

public class UserDAO {
	private static final Logger LOG = Logger.getLogger(UserDAO.class);

	/**
	 * @DESC this method is use to return current date
	 * @return
	 */

	private static java.sql.Date getCurrentDate() {
		LOG.info("INSIDE:: getCurrentDate method of UserDAO");
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	/**
	 * @desc This method is used for registration
	 * @param user
	 * @return registrationStatus
	 * @throws VRSDaoException
	 */
	@SuppressWarnings("finally")
	public int registerUserDetails(UserDTO user) throws VRSDaoException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		int registrationStatus = 0;
		ResultSet resultSet = null;
		int rowsReturned = 0;
		

		try {
			dbConnection = DbUtil.getConnection();
			LOG.debug("executing the query::"+Query.CHECK_EXIST_USER_ID_QUERY);
			preparedStatement = dbConnection
					.prepareStatement(Query.CHECK_EXIST_USER_ID_QUERY);
			preparedStatement.setInt(1, user.getUserId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				rowsReturned = resultSet.getInt("count_existing_user");
			}
			if (rowsReturned == 0) {

				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
				sdf.setLenient(false);
				LOG.debug("executing the query::"+Query.REGISTRATION_QUERY);
				preparedStatement = dbConnection
						.prepareStatement(Query.REGISTRATION_QUERY);
				preparedStatement.setInt(1, user.getUserId());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getRole());
				preparedStatement.setString(4, "pending");

				preparedStatement.setDate(5, getCurrentDate());

				rowsReturned = preparedStatement.executeUpdate();
				if (rowsReturned > 0) {
					registrationStatus = 1;
				}
				
			} else {
				registrationStatus = 2;
			}

		} catch (SQLException e) {
			LOG.error("Exception occured . "+e.getCause() );
			registrationStatus = 0;
			throw new VRSDaoException(
					"User already exists!! Try with another one");

		} finally {
			ResourceClosing.closeResources(resultSet, dbConnection, preparedStatement);
			return registrationStatus;
		}
			
		
		
	}

	/**
	 * @desc This method is use to login into the system
	 * @param user
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws VRSDaoException
	 */
	public String loginUser(UserDTO user) throws  VRSDaoException {
		LOG.info("INSIDE:: loginUser method of UserDAO");
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		int userid = user.getUserId();
		String password = user.getPassword();
		String roleName = null;
		ResultSet rs = null;
		try {

			LOG.info("BEFORE:: Connection to the database");
			dbConnection = DbUtil.getConnection();
			LOG.info("BEFORE::login");
			preparedStatement = dbConnection
					.prepareStatement(Query.LOGIN_QUERY);
			preparedStatement.setInt(1, userid);
			preparedStatement.setString(2, password);

			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				if (rs.getString("flag").equals("approved")) {
					roleName = rs.getString("role_name");
				}
			}

			LOG.info("AFTER::login");
		} catch (SQLException e) {
			LOG.error("Exception occured . "+e.getCause() );
			throw new VRSDaoException("Error: Login Failed");

		} catch (Exception e) {
			LOG.error("Exception occured . "+e.getCause() );
			throw new VRSDaoException("Error: Login Failed");
		}  finally {
			ResourceClosing.closeResources(rs, dbConnection, preparedStatement);
			
		}
		return roleName;
	}

	
	

	/**
	 * @Desc This method checks whether a particlar user is registered or not
	 * @param user
	 * @return returnFlag
	 * @throws VRSDaoException 
	 */
	public boolean isExist(UserDTO user) throws VRSDaoException {
		Connection connection = null;
		String query;
		String name = null;
		boolean returnFlag = true;
		LOG.info("INSIDE:: isExist method of UserDAO");
		LOG.debug("before executing this query");
		if (user.getRole().equalsIgnoreCase("customer")) {
			
			query = "select CUSTOMER.CUSTOMER_NAME from CUSTOMER where user_id=?";
		} else {
			query = "select BRANCH_ADMIN.BRANCH_LOCATION from BRANCH_ADMIN where user_id=?";
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		try {
			LOG.info("BEFORE:: Connection to the database");
			connection = DbUtil.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, user.getUserId());
			 resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				name = resultSet.getString(1);
			}

			if (name == null) {
				returnFlag = false;
			}

		} catch (ClassNotFoundException e) {
			LOG.error("Exception occured . "+e.getCause() );
			throw new VRSDaoException("Error: Login Failed");
		} catch (IOException e) {
			LOG.error("Exception occured . "+e.getCause() );
			throw new VRSDaoException("Error: Login Failed");
		} catch (SQLException e) {
			LOG.error("Exception occured . "+e.getCause() );
			throw new VRSDaoException("Error: Login Failed");
		}  finally {
			ResourceClosing.closeResources(resultSet, connection, preparedStatement);
			
		}
		return returnFlag;
	}
	

}
