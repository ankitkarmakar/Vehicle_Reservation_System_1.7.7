package com.vrs.delegate;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.vrs.dao.UserDAO;
import com.vrs.dto.UserDTO;
import com.vrs.exceptions.VRSDaoException;

public class UserDelegate {

	private static final Logger LOG = Logger.getLogger(UserDelegate.class);

	/**
	 * @desc this method will be used FOR registration
	 * @param approveList
	 * @param setStatus
	 * @return boolean status
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws VRSDaoException
	 */

	@SuppressWarnings("unused")
	public int registerAction(UserDTO user) throws SQLException,
			VRSDaoException {
		String userId = String.valueOf(user.getUserId());
		int temp = 0;
		boolean returnValue = false;
		int countDigits = 0;
		int countSpecialChar = 0;
		int returnRegistrationStatus = 0;

		String userIdpattern = "[0-9]{6}";
		String passwordPattern = "[a-zA-Z0-9]{1,}[0-9]{1,}[!@#$%^&*]{1,}";

		if (userId.matches(userIdpattern)) {
			if (user.getPassword().matches(passwordPattern)) {
				returnValue = true;

			}
		} else
			returnValue = false;
		if (user.getRole().equalsIgnoreCase("customer")) {

			returnRegistrationStatus = new UserDAO().registerUserDetails(user);

		} else if (user.getRole().equalsIgnoreCase("BranchAdmin")) {

			returnRegistrationStatus = new UserDAO().registerUserDetails(user);

		}
		LOG.info("INSIDE:: registerAction method:: returnLoginStatus = "
				+ returnRegistrationStatus);

		return returnRegistrationStatus;
	}

	/**
	 * @desc this method will be used to login
	 * @param user
	 * @return String returnLoginStatus
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws VRSDaoException
	 * @throws SQLException
	 */
	public String loginDelegateAction(UserDTO user)
			throws ClassNotFoundException, IOException, VRSDaoException,
			SQLException {
		LOG.info("INSIDE:: loginDelegateAction method of UserDelegate");
		String returnLoginStatus = null;
		returnLoginStatus = new UserDAO().loginUser(user);
		LOG.info("INSIDE:: loginDelegateAction method:: returnLoginStatus = "
				+ returnLoginStatus);
		return returnLoginStatus;
	}

	/**
	 * @desc this method checks whether a particular user is registered or not
	 * @param user
	 * @return boolean
	 * @throws VRSDaoException
	 */
	public boolean isExist(UserDTO user) throws VRSDaoException {
		LOG.info("INSIDE:: isExist method of UserDelegate");
		if (new UserDAO().isExist(user))
			return true;
		else
			return false;
	}
}
