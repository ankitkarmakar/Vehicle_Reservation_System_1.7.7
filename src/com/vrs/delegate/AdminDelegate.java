package com.vrs.delegate;

import java.util.List;

import org.apache.log4j.Logger;

import com.vrs.dao.AdminDAO;
import com.vrs.dto.UserDTO;
import com.vrs.dto.VehicleDTO;
import com.vrs.exceptions.VRSDaoException;

public class AdminDelegate {
	private static final Logger LOG = Logger.getLogger(AdminDelegate.class);

	/**
	 * @ desc This method will be used to add newly launched vehicle in main
	 * stock by admin
	 * 
	 * @param vehicle
	 * @return String returned by the method of addNewVehicle() in AdminDAO
	 * @throws VRSDaoException
	 */

	public String addNewVehicle(VehicleDTO vehicle) throws VRSDaoException {
		LOG.info("INSIDE:: addNewVehicle method of Admin");
		AdminDAO aDao = new AdminDAO();
		LOG.info("CALLING:: addNewVehicle() method of AdminDAO");
		return aDao.addNewVehicle(vehicle);
	}

	/**
	 * @desc This method is used to view the requested vehicle list
	 * @param first
	 * @param last
	 * @return
	 * @throws VRSDaoException
	 */
	public List<VehicleDTO> viewVehicleRequestList(int first, int last)
			throws VRSDaoException {
		LOG.info("INSIDE:: viewVehicleRequestList method of UserDelegate");
		List<VehicleDTO> vehicleList = new AdminDAO().getVehicleRequestList(
				first, last);
		LOG.info("INSIDE:: viewVehicleRequestList method:: vehicleList size = "
				+ vehicleList.size());
		return vehicleList;
	}

	/**
	 * desc This method is use to approve or reject vehicle request
	 * 
	 * @param approveList
	 * @param setStatus
	 * @param noOfVehicles
	 * @return
	 * @throws VRSDaoException
	 */
	public String approveOrRejectVehicleRequest(List<String> approveList,
			String setStatus, int noOfVehicles) throws VRSDaoException {
		LOG.info("INSIDE:: approveOrRejectVehicleRequest method of UserDelegate");
		String status = new AdminDAO().approveOrRejectVehicleRequest(
				approveList, setStatus, noOfVehicles);
		LOG.info("INSIDE:: approveOrRejectVehicleRequest method:: status = "
				+ status);
		return status;
	}

	/**
	 * @desc This method is used to view registration request
	 * @param choice
	 * @return
	 * @throws VRSDaoException
	 */
	public List<UserDTO> viewUserRegistrationList(int choice)
			throws VRSDaoException {
		LOG.info("INSIDE:: viewUserRegistrationList method of UserDelegate");
		List<UserDTO> registrationList = new AdminDAO()
				.getUserRegistrationList(choice);
		return registrationList;
	}

	/**
	 * @desc this method is used to approve registration request
	 * @param approveList
	 * @param setStatus
	 * @return
	 * @throws VRSDaoException
	 */
	public Boolean approveRegistrationRequest(List<Integer> approveList,
			String setStatus) throws VRSDaoException {
		LOG.info("INSIDE:: approveRegistrationRequest method of UserDelegate");
		Boolean status = new AdminDAO().approveOrRejectRegistration(
				approveList, setStatus);
		LOG.info("INSIDE:: approveRegistrationRequest method:: returnLoginStatus = "
				+ status);

		return status;
	}

	/**
	 * @desc this method is used to view registration request by selecting role
	 * @param approveList
	 * @param setStatus
	 * @return
	 * @throws VRSDaoException
	 */
	public List<UserDTO> viewPendingUserRegistrationListByRole(String role)
			throws VRSDaoException {
		LOG.info("INSIDE:: viewPendingUserRegistrationListByRole method of UserDelegate");
		List<UserDTO> registrationList = new AdminDAO()
				.getPendingUserRegistrationListByRole(role);
		return registrationList;
	}

	/**
	 * @desc this method is used to view registration request by selecting status
	 * @param approveList
	 * @param setStatus
	 * @return
	 * @throws VRSDaoException
	 */
	public List<UserDTO> viewPendingUserRegistrationListByStatus(String status)
			throws VRSDaoException {
		LOG.info("INSIDE:: viewPendingUserRegistrationListByStatus method of UserDelegate");
		List<UserDTO> registrationList = new AdminDAO()
				.getPendingUserRegistrationListByStatus(status);
		return registrationList;
	}
}
