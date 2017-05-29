package com.vrs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.vrs.delegate.BranchAdminDelegate;
import com.vrs.dto.BookingDTO;
import com.vrs.dto.BranchAdminDTO;
import com.vrs.dto.VehicleDTO;
import com.vrs.exceptions.VRSDaoException;

public class BranchAdminAction extends HttpServlet {
	static int first = 1;
	static int last = 5;
	static String oldVehicleID;
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(BranchAdminAction.class);

	public BranchAdminAction() {
		super();
	}

	/**
	 * @desc this method will navigate to the corresponding actions by branch
	 *       admin such as updating details,requesting new vehicles,approving
	 *       the request by customer, viewing the booking details based on
	 *       choice
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws VRSDaoException
	 */

	protected void doAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ClassNotFoundException, SQLException, VRSDaoException {
		LOG.info("INSIDE:: doAction method of Customer");
		int choice = 0;
		choice = Integer.parseInt(request.getParameter("choice"));
		LOG.debug("BEFORE:: Switch case in doAction method with choice "
				+ choice);
		HttpSession session = request.getSession(false);
		if (session.getAttribute("userID") == null) {
			response.sendRedirect("login.jsp");
		} else {

			switch (choice) {
			case 1:
				doUpdateBranchAdminDetails(request, response);
				break;
			case 2:
				dovehicleRequest(request, response);
				break;
			case 3:
				doGetBookingDetails(request, response);
				break;
			case 4:
				approveBooking(request, response);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @desc this method will get the information required to update the details
	 *       of branch Admin
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	protected void doUpdateBranchAdminDetails(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ClassNotFoundException, SQLException {

		LOG.info("INSIDE:: doUpdateBranchAdminDetails method:: ");

		HttpSession session = request.getSession();

		int username = Integer.parseInt(session.getAttribute("userID")
				.toString());

		LOG.info("INSIDE:: doUpdateBranchAdminDetails method:: user with userID = "
				+ username);
		String branchLocation = request.getParameter("BranchLocation");
		LOG.info("INSIDE:: doCustomerDetailsUpdation method:: user with BranchLocation = "
				+ branchLocation);
		String address = request.getParameter("Address");
		LOG.info("INSIDE:: doCustomerDetailsUpdation method:: user with Address = "
				+ address);
		String mailid = request.getParameter("Mailid");
		LOG.info("INSIDE:: doCustomerDetailsUpdation method:: user with Mailid = "
				+ mailid);
		String phoneNo = request.getParameter("PhoneNo");
		LOG.info("INSIDE:: doCustomerDetailsUpdation method:: user with PhoneNo = "
				+ phoneNo);

		BranchAdminDTO branchAdminDTO = new BranchAdminDTO();
		branchAdminDTO.setUserId(username);
		branchAdminDTO.setBranchLocation(branchLocation);
		branchAdminDTO.setAddress(address);
		branchAdminDTO.setMailid(mailid);
		branchAdminDTO.setPhoneNo(phoneNo);

		BranchAdminDelegate branchAdminDelegate = new BranchAdminDelegate();

		String returnMessage = null;
		try {

			returnMessage = branchAdminDelegate
					.updateBranchAdmin(branchAdminDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (returnMessage != null) {
			request.setAttribute("successMessage",
					"Your Profile Has Been Successfully Updated..");
			request.getRequestDispatcher("success.jsp").forward(request,
					response);
		} else {
			request.setAttribute("successMessage",
					"Problem Occurred in Updation..");
			request.getRequestDispatcher("success.jsp").forward(request,
					response);
		}

	}

	/**
	 * @desc this method will be used to request for new vehicles to admin by
	 *       branch admin based on the requirements and specifications
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws VRSDaoException
	 * @throws SQLException
	 */

	protected void dovehicleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			VRSDaoException, SQLException {
		LOG.info("INSIDE:: dovehicleRequest method:: ");
		VehicleDTO vehicle = new VehicleDTO();
		vehicle.setBranchID(request.getParameter("branchID"));
		LOG.info("INSIDE:: dovehicleRequest method:: Requested BranchId = "
				+ vehicle.getBranchID());
		vehicle.setVehicleId(request.getParameter("vehicleID"));
		LOG.info("INSIDE:: dovehicleRequest method:: Requested VehicleId = "
				+ vehicle.getVehicleId());
		vehicle.setNumberOfVehicle(Integer.parseInt(request
				.getParameter("noOfVehicle")));
		LOG.info("INSIDE:: dovehicleRequest method:: Requested No. of Vehicle = "
				+ vehicle.getNumberOfVehicle());

		if (new BranchAdminDelegate().vehicleRequest(vehicle) > 0) {
			LOG.info("AFTER:: dovehicleRequest method:: Updated Succesfully");
			request.setAttribute("successMessage",
					"Your Request Has Been recorded Successfully..!!");
			request.getRequestDispatcher("success.jsp").forward(request,
					response);
		}
	}

	/**
	 * @desc this method will be used by branch Admin to get the booking details
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws VRSDaoException
	 */

	protected void doGetBookingDetails(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			VRSDaoException {
		LOG.info("INSIDE:: doGetBookingDetails method:: ");
		int page = Integer.parseInt(request.getParameter("page").toString());

		first = first + page;
		last = last + page;
		BookingDTO bookingDTO = new BookingDTO();

		String vehicleID = null;
		if (page == 0) {

			vehicleID = request.getParameter("vehicleID");
			oldVehicleID = vehicleID;
			first = 1;
			last = 5;
		}
		LOG.info("INSIDE:: dovehicleRequest method:: Requested VehicleId = "
				+ vehicleID);

		bookingDTO.setVehicleID(oldVehicleID);

		int branchAdminID = 0;

		HttpSession session = request.getSession(false);
		if (session.getAttribute("userID") != null) {
			branchAdminID = Integer.parseInt(session.getAttribute("userID")
					.toString());
			LOG.info("INSIDE:: dovehicleRequest method:: Requested VehicleId = "
					+ vehicleID + " By Branch Admin UserID = " + branchAdminID);
		}
		bookingDTO.setBranchAdminID(branchAdminID);
		List<BookingDTO> bookingList = new ArrayList<BookingDTO>();
		BranchAdminDelegate branchAdminDelegate = new BranchAdminDelegate();
		bookingList = branchAdminDelegate.searchVehicleBooking(bookingDTO,
				first, last);
		for (int i = 0; i < bookingList.size(); i++) {
			bookingList.get(i).setVehicleID(oldVehicleID);

		}
		LOG.info("INSIDE:: doGetBookingDetails method:: bookingList with size = "
				+ bookingList.size());

		request.setAttribute("list", bookingList);
		RequestDispatcher rd = request
				.getRequestDispatcher("approveBooking.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}

	}

	/**
	 * @desc this method will be used to approve booking and on approval
	 *       redirects to a page showing a successful booking message
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws VRSDaoException
	 */

	protected void approveBooking(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			VRSDaoException {
		LOG.info("INSIDE:: approveBooking method:: ");
		String bookingID;
		String vehicleID;
		int bookingApproveStatus = 0;
		String str = request.getParameter("selIds");
		// System.out.println(str);
		String vehicleAndBranch[] = str.split(":");
		bookingID = vehicleAndBranch[0];
		vehicleID = vehicleAndBranch[1];
		String bookingApprovedFlag = request.getParameter("comment");

		LOG.info("INSIDE:: approveBooking method:: With BookingID = "
				+ bookingID + " & VehicleID = " + vehicleID + " & flag = "
				+ bookingApprovedFlag);
		BranchAdminDelegate branchAdminDelegate = new BranchAdminDelegate();
		BookingDTO bookingDTO = new BookingDTO();
		Date bookingApprovedDate = new Date();
		bookingDTO.setBookingID(bookingID);
		bookingDTO.setBookingApprovedDate(bookingApprovedDate);
		bookingDTO.setVehicleID(vehicleID);
		int branchAdminID = 0;

		HttpSession session = request.getSession(false);
		if (session.getAttribute("userID") != null) {
			branchAdminID = Integer.parseInt(session.getAttribute("userID")
					.toString());
		}

		bookingDTO.setBranchAdminID(branchAdminID);
		bookingDTO.setBookingApprovedFlag(bookingApprovedFlag);
		bookingApproveStatus = branchAdminDelegate.approveBooking(bookingDTO);
		if (bookingApproveStatus == -9999) {
			LOG.info("INSIDE:: approveBooking method:: Out of Stock!!");
			request.setAttribute("successMessage",
					"You are running Out of Stock!!");
		} else {
			LOG.info("INSIDE:: approveBooking method:: Booking Approved remaining vehicle:"
					+ bookingApproveStatus);
			request.setAttribute("successMessage",
					"Booking Approved!! you have " + bookingApproveStatus
							+ " vehicles left!!");
		}

		request.getRequestDispatcher("success.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			doAction(request, response);
		} catch (Exception e) {
			request.setAttribute("errorMessage",
					"Sorry..! An Internal Error Occured");
			LOG.error(request.getAttribute("errorMessage"), e);
			response.sendError(504);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			doAction(request, response);
		} catch (Exception e) {
			request.setAttribute("errorMessage",
					"Sorry..! An Internal Error Occured");
			LOG.error(request.getAttribute("errorMessage"), e);
			response.sendError(504);
		}

	}

}
