package com.vrs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.vrs.delegate.AdminDelegate;
import com.vrs.dto.UserDTO;
import com.vrs.dto.VehicleDTO;
import com.vrs.exceptions.VRSDaoException;

public class AdminAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AdminAction.class);
	static int first = 1;
	static int last = 2;

	public AdminAction() {
		super();

	}

	/**
	 * @desc method to get the choice of admin to view all users' registration
	 *       list,approve or reject the pending request,viewing the vehicle
	 *       request list, adding new vehicle to the stock
	 * @variables choice
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws VRSDaoException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	protected void doAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			VRSDaoException, ClassNotFoundException, SQLException {

		LOG.info("INSIDE:: doAction method of Admin");

		HttpSession session = request.getSession(false);
		if (session.getAttribute("userID") == null) {
			response.sendRedirect("login.jsp");
		} else {
			int choice = 0;
			choice = Integer.parseInt(request.getParameter("choice"));

			LOG.info("BEFORE:: Switch case in doAction method with choice "
					+ choice);

			switch (choice) {
			case 1:
				getRequestVehicleList(request, response);
				break;
			case 2:
				approveVehicleRequest(request, response);
				break;
			case 3:
				addNewVehicle(request, response);
				break;
			case 4:
				getPendingUserRegistration(request, response);
				break;
			case 5:
				getAllUserRegistration(request, response);
				break;
			case 6:
				approveRegistrationRequest(request, response);
				break;
			case 7:
				getPendingUserRegistrationByRole(request, response);
				break;
			case 8:
				getPendingUserRegistrationByStatus(request, response);
				break;
			default:
				break;
			}
		}

	}

	/**
	 * @desc this method will be used to get the List of requested vehicle
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws VRSDaoException
	 */

	protected void getRequestVehicleList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			VRSDaoException {

		List<VehicleDTO> vehicleList;
		LOG.info("INSIDE:: getVehicleList method of Admin");

		int page = Integer.parseInt(request.getParameter("page").toString());

		if (page == 0) {
			first = 1;
			last = 2;
		}
		first = first + page;
		last = last + page;
		LOG.info("CALLING::viewVehicleRequestList of AdminDelegate");
		vehicleList = new AdminDelegate().viewVehicleRequestList(first, last);

		request.setAttribute("vehicleRequestList", vehicleList);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("showVehicleRequestList.jsp");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @desc this method will be used to approve or reject the request of
	 *       vehicles done by branch Admin to admin
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws VRSDaoException
	 * @throws ClassNotFoundException
	 */

	protected void approveVehicleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			VRSDaoException, ClassNotFoundException {
		LOG.info("INSIDE:: approveVehicleRequest method of Admin");

		if (request.getParameter("for approval") != null) {

			LOG.info("INSIDE:: approveVehicleRequest method of Admin :: For approve vehicle Requests");
			String[] checkboxNamesList = request.getParameterValues("vehicle");
			int noOfVehicles = Integer.parseInt(request
					.getParameter("numberOfVehicle"));
			List<String> vehicleRequestList = new ArrayList<String>();
			for (int i = 0; i < checkboxNamesList.length; i++) {
				vehicleRequestList.add(checkboxNamesList[i]);
			}

			String status = new AdminDelegate().approveOrRejectVehicleRequest(
					vehicleRequestList, "approved", noOfVehicles);
			LOG.info("INSIDE:: approveVehicleRequest method of Admin :: For approve vehicle Requests :: "
					+ status);
			request.setAttribute("successMessage", status);

			request.getRequestDispatcher("success.jsp").forward(request,
					response);

		} else if (request.getParameter("for rejection") != null) {

			LOG.info("INSIDE:: approveVehicleRequest method of Admin :: For reject vehicle Requests");
			String[] checkboxNamesList = request.getParameterValues("vehicle");
			List<String> vehicleRequestList = new ArrayList<String>();

			for (int i = 0; i < checkboxNamesList.length; i++) {
				vehicleRequestList.add(checkboxNamesList[i]);
			}

			String status = new AdminDelegate().approveOrRejectVehicleRequest(
					vehicleRequestList, "rejected", 0);
			LOG.info("INSIDE:: approveVehicleRequest method of Admin :: For reject vehicle Requests :: "
					+ status);
			request.setAttribute("successMessage", status);
			request.getRequestDispatcher("success.jsp").forward(request,
					response);
		}
	}

	/**
	 * @desc method to add new vehicles to the stock by taking the specifications
	 *       needed
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */

	protected void addNewVehicle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {

		LOG.info("INSIDE:: addNewVehicle method of Admin");
		String returnMessage;
		VehicleDTO vehicle = new VehicleDTO();
		if (request.getParameter("Vehicle ID").length() > 0
				&& request.getParameter("Manufac").length() > 0
				&& request.getParameter("ExShowRoomPrice").length() > 0
				&& request.getParameter("rent").length() > 0
				&& request.getParameter("Vehicle Type").length() > 0
				&& request.getParameter("color").length() > 0
				&& request.getParameter("seat capacity").length() > 0
				&& request.getParameter("No. of Vehicle(s)").length() > 0
				&& request.getParameter("Vehicle Name").length() > 0) {
			String vehicleID = request.getParameter("Vehicle ID");
			String manufacture = request.getParameter("Manufac");
			int exShowRoomPrice = Integer.parseInt(request
					.getParameter("ExShowRoomPrice"));
			int rent = Integer.parseInt(request.getParameter("rent"));
			String vehicleType = request.getParameter("Vehicle Type");
			String color = request.getParameter("color");
			int seatCapacity = Integer.parseInt(request
					.getParameter("seat capacity"));
			int noOfVehicle = Integer.parseInt(request
					.getParameter("No. of Vehicle(s)"));
			String vehicleName = request.getParameter("Vehicle Name");

			vehicle.setVehicleId(vehicleID);
			vehicle.setManufactureName(manufacture);
			vehicle.setExShowroomPrice(exShowRoomPrice);
			vehicle.setRent(rent);
			vehicle.setVehicleType(vehicleType);
			vehicle.setColor(color);
			vehicle.setSeat(seatCapacity);
			vehicle.setNumberOfVehicle(noOfVehicle);
			vehicle.setVehicleName(vehicleName);

			try {
				LOG.info("CALLING::calling addNewVehicle of AdminDelegate");
				returnMessage = new AdminDelegate().addNewVehicle(vehicle);
				request.setAttribute("successMessage", returnMessage);
				LOG.info("INSIDE:: addNewVehicle method of Admin :: After add new Vehicle :: "
						+ returnMessage);
				request.getRequestDispatcher("success.jsp").forward(request,
						response);

			} catch (Exception e) {
				LOG.error(
						"INSIDE:: addNewVehicle method of Admin :: Exception ",
						e);
				request.setAttribute("errorMessage",
						"Sorry..! An Internal Error Occured");
				response.sendError(504);
			}
		} else {
			request.setAttribute("errorMessage",
					"Sorry..! An Internal Error Occured");
			LOG.debug("INSIDE:: addNewVehicle method of Admin :: DEBUG :: Null field was present in passed Parameters");
			response.sendError(504);
		}
	}

	/**
	 * @desc method will be used to get the pending list of user registration
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws VRSDaoException
	 */

	protected void getPendingUserRegistration(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			VRSDaoException {
		LOG.info("INSIDE:: getPendingUserRegistration method of Admin");

		List<UserDTO> userRegistrationRequestList = new ArrayList<UserDTO>();
		RequestDispatcher dispatcher = null;

		userRegistrationRequestList = new AdminDelegate()
				.viewUserRegistrationList(1);
		if (userRegistrationRequestList.size() == 0) {
			request.setAttribute("successMessage",
					"No Registration Request Found");
			request.getRequestDispatcher("userRegistrationSuccess.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("registrationRequestList",
					userRegistrationRequestList);
			dispatcher = request
					.getRequestDispatcher("getUserRegistrationList.jsp");
			LOG.info("INSIDE:: getPendingUserRegistration method of Admin ::  Size of Registration List = "
					+ userRegistrationRequestList.size());
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}
		}

	}

	/**
	 * @desc this method will be used to get the registration request
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws VRSDaoException
	 */
	protected void getAllUserRegistration(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			VRSDaoException {
		LOG.info("INSIDE:: getAllUserRegistration method of Admin");

		List<UserDTO> userRegistrationRequestList = new ArrayList<UserDTO>();
		RequestDispatcher dispatcher = null;

		userRegistrationRequestList = new AdminDelegate()
				.viewUserRegistrationList(2);
		if (userRegistrationRequestList.size() == 0) {
			request.setAttribute("successMessage",
					"No Registration Request Found");
			request.getRequestDispatcher("userRegistrationSuccess.jsp")
					.forward(request, response);
		}
		request.setAttribute("registrationRequestList",
				userRegistrationRequestList);
		dispatcher = request
				.getRequestDispatcher("getUserRegistrationList.jsp");
		LOG.info("INSIDE:: getAllUserRegistration method of Admin :: Size of Registration List = "
				+ userRegistrationRequestList.size());
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @desc the method will be used to approve the registration request of users
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws VRSDaoException
	 */

	protected void approveRegistrationRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ClassNotFoundException, VRSDaoException {
		LOG.info("INSIDE:: approveRegistrationRequest method of Admin");

		boolean status = false;
		if (request.getParameter("for approval") != null) {
			LOG.info("INSIDE:: approveRegistrationRequest method of Admin :: for Approval");
			String[] checkboxNamesList = request.getParameterValues("user");
			LOG.info("INSIDE:: approveRegistrationRequest method of Admin :: checkboxNamesList size="
					+ checkboxNamesList.length);

			List<Integer> registrationRequestList = new ArrayList<Integer>();

			for (int i = 0; i < checkboxNamesList.length; i++) {

				registrationRequestList.add(Integer
						.parseInt(checkboxNamesList[i]));

			}

			status = new AdminDelegate().approveRegistrationRequest(
					registrationRequestList, "approved");
			if (status) {
				request.setAttribute("successMessage",
						"Registration request status updated succesfully");
				LOG.info("INSIDE:: approveRegistrationRequest method of Admin :: Successfully Accepted ");
				request.getRequestDispatcher("success.jsp").forward(request,
						response);
			} else {

				request.setAttribute("errorMessage",
						"Sorry..! An Internal Error Occured");
				LOG.debug("INSIDE:: approveRegistrationRequest method of Admin :: Approval not Successful ");
				response.sendError(504);
			}

		} else if (request.getParameter("for rejection") != null) {
			LOG.info("INSIDE:: approveRegistrationRequest method of Admin :: for Rejection");
			String[] checkboxNamesList = request.getParameterValues("user");
			List<Integer> registrationRequestList = new ArrayList<Integer>();

			for (int i = 0; i < checkboxNamesList.length; i++) {

				registrationRequestList.add(Integer
						.parseInt(checkboxNamesList[i]));

			}

			status = new AdminDelegate().approveRegistrationRequest(
					registrationRequestList, "rejected");

			if (status) {
				request.setAttribute("successMessage",
						"Registration request status updated succesfully");
				LOG.info("INSIDE:: approveRegistrationRequest method of Admin :: Successfully rejected ");
				request.getRequestDispatcher("success.jsp").forward(request,
						response);
			} else {

				request.setAttribute("errorMessage",
						"Sorry..! An Internal Error Occured");
				LOG.debug("INSIDE:: approveRegistrationRequest method of Admin :: Rejection not Successful ");
				response.sendError(504);
			}

		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			doAction(request, response);
		} catch (Exception e) {
			request.setAttribute("errorMessage",
					"Sorry..! An Internal Error Occured");
			LOG.error("INSIDE:: doGet method:: Exception occured ", e);
			response.sendError(504);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			doAction(request, response);
		} catch (Exception e) {
			request.setAttribute("errorMessage",
					"Sorry..! An Internal Error Occured");
			LOG.error("INSIDE:: doPost method:: Exception occured ", e);
			response.sendError(504);
		}

	}

	/**
	 * @desc view pending registration list based on role
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws VRSDaoException
	 */

	protected void getPendingUserRegistrationByRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			VRSDaoException {
		LOG.info("INSIDE:: getPendingUserRegistrationByRole method of Admin");
		String role = request.getParameter("role");
		List<UserDTO> userRegistrationRequestList = new ArrayList<UserDTO>();
		RequestDispatcher dispatcher = null;

		userRegistrationRequestList = new AdminDelegate()
				.viewPendingUserRegistrationListByRole(role);
		if (userRegistrationRequestList.size() == 0) {
			request.setAttribute("successMessage",
					"No Registration Request Found");
			request.getRequestDispatcher("userRegistrationSuccess.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("registrationRequestList",
					userRegistrationRequestList);
			dispatcher = request
					.getRequestDispatcher("getUserRegistrationList.jsp");
			LOG.info("INSIDE:: getPendingUserRegistrationByRole method of Admin ::  Size of Registration List = "
					+ userRegistrationRequestList.size());
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @desc view pending registration list based on status
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws VRSDaoException
	 */

	protected void getPendingUserRegistrationByStatus(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, VRSDaoException {
		LOG.info("INSIDE:: getPendingUserRegistrationByStatus method of Admin");
		String status = request.getParameter("status");
		List<UserDTO> userRegistrationRequestList = new ArrayList<UserDTO>();
		RequestDispatcher dispatcher = null;

		userRegistrationRequestList = new AdminDelegate()
				.viewPendingUserRegistrationListByStatus(status);
		if (userRegistrationRequestList.size() == 0) {
			request.setAttribute("successMessage",
					"No Registration Request Found");
			request.getRequestDispatcher("userRegistrationSuccess.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("registrationRequestList",
					userRegistrationRequestList);
			dispatcher = request
					.getRequestDispatcher("getUserRegistrationList.jsp");
			LOG.info("INSIDE:: getPendingUserRegistrationByStatus method of Admin ::  Size of Registration List = "
					+ userRegistrationRequestList.size());
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}
		}
	}

}
