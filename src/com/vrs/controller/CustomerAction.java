package com.vrs.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

import com.vrs.delegate.CustomerDelegate;
import com.vrs.dto.BookingDTO;
import com.vrs.dto.CustomerDTO;
import com.vrs.dto.VehicleDTO;
import com.vrs.exceptions.VRSDaoException;

public class CustomerAction extends HttpServlet {
	static int first = 1;
	static int last = 5;
	static int firstVehicle = 1;
	static int lastVehicle = 1;
	static String oldManufacturerName;
	static String oldLocation;
	static int oldMinPrice;
	static int oldMaxPrice;
	static int oldSeat;
	static String oldColor;
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CustomerAction.class);

	public CustomerAction() {
		super();
	}

	/**
	 * @desc this method will navigate to the corresponding actions by customer
	 *       such as updating details,searching vehicle,showing the booking list
	 *       against the particular customer, booking vehicle based on choice
	 * @param request
	 * @param response
	 * @throws Exception
	 */

	protected void doAction(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

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
				doCustomerDetailsUpdation(request, response);
				break;
			case 2:
				searchVehicle(request, response);
				break;
			case 3:
				showBooking(request, response);
				break;
			case 4:
				doBookVehicle(request, response);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @desc this method will get the information required to update the details
	 *       of customer and on successful updation gives a success message to
	 *       the customer
	 * @param request
	 * @param response
	 * @throws Exception
	 */

	protected void doCustomerDetailsUpdation(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOG.info("INSIDE:: doCustomerDetailsUpdation method:: ");

		HttpSession session = request.getSession(false);
		int userId = Integer
				.parseInt(session.getAttribute("userID").toString());
		LOG.info("INSIDE:: doCustomerDetailsUpdation method:: user with userID = "
				+ userId);
		String customerName = request.getParameter("Customer Name");
		LOG.info("INSIDE:: doCustomerDetailsUpdation method:: customer with name = "
				+ customerName);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dateOfBirth = request.getParameter("dob");
		LOG.info("INSIDE:: doCustomerDetailsUpdation method:: customer with date of birth = "
				+ dateOfBirth);

		Date dob = new Date();

		dob = sdf.parse(dateOfBirth);
		sdf.applyPattern("dd-MMM-yy");

		String address = request.getParameter("Address").trim();
		LOG.debug("INSIDE:: doCustomerDetailsUpdation method:: customer with address = "
				+ address);

		String mailId = request.getParameter("Mailid").trim();
		LOG.debug("INSIDE:: doCustomerDetailsUpdation method:: customer with mailId = "
				+ mailId);

		String mobile = request.getParameter("PhoneNo").trim();
		LOG.debug("INSIDE:: doCustomerDetailsUpdation method:: customer with mobile = "
				+ mobile);

		String occupation = request.getParameter("occupation").trim();
		LOG.debug("INSIDE:: doCustomerDetailsUpdation method:: customer with occupation = "
				+ occupation);

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setUserId(userId);
		customerDTO.setCustomerName(customerName);
		customerDTO.setDateOfBirth(sdf.parse(sdf.format(dob)));
		customerDTO.setAddress(address);
		customerDTO.setEmail(mailId);
		customerDTO.setPhoneNo(mobile);
		customerDTO.setOccupation(occupation);
		CustomerDelegate customerDelegate = new CustomerDelegate();
		String returnMessage = null;
		returnMessage = customerDelegate.updateCustomerDetails(customerDTO);
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
	 * @desc this method will be used by customer to search desired vehicle
	 *       based on specifications
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws VRSDaoException
	 */

	protected void searchVehicle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			VRSDaoException {
		LOG.info("INSIDE:: searchVehicle method:: ");

		String manufacturerName;
		String location;
		int minPrice = 0;
		int maxPrice = 0;
		int seat = 0;
		String color;
		int vehiclePage = Integer.parseInt(request.getParameter("vehiclePage")
				.toString());
		firstVehicle = firstVehicle + vehiclePage;
		lastVehicle = lastVehicle + vehiclePage;

		if (vehiclePage == 0) {
			firstVehicle = 1;
			lastVehicle = 1;
			manufacturerName = request.getParameter("manufacturerName");
			if (manufacturerName.equalsIgnoreCase("")) {
				manufacturerName = "mfname";
			}
			oldManufacturerName = manufacturerName;
			LOG.info("INSIDE:: searchVehicle method:: customer with manufacturerName = "
					+ manufacturerName);

			location = request.getParameter("location");
			if (location.equalsIgnoreCase("")) {
				location = "location";
			}
			LOG.info("INSIDE:: searchVehicle method:: customer with location = "
					+ location);

			oldLocation = location;
			String minimumPrice = request.getParameter("minPrice").toString();
			if (minimumPrice.equalsIgnoreCase("")) {
				minPrice = 0;

			} else {
				minPrice = Integer.parseInt(minimumPrice);
			}
			LOG.info("INSIDE:: searchVehicle method:: customer with minPrice = "
					+ minPrice);
			oldMinPrice = minPrice;

			String maximumPrice = request.getParameter("maxPrice").toString();
			if (maximumPrice.equalsIgnoreCase("")) {
				maxPrice = 0;

			} else {
				maxPrice = Integer.parseInt(maximumPrice);
			}
			LOG.info("INSIDE:: searchVehicle method:: customer with maxPrice = "
					+ maxPrice);
			oldMaxPrice = maxPrice;

			String numberOfSeat = request.getParameter("seat").toString();
			if (numberOfSeat.equalsIgnoreCase("Choose Number of seats")) {
				numberOfSeat = "100";

			}
			seat = Integer.parseInt(numberOfSeat);
			LOG.info("INSIDE:: searchVehicle method:: customer with seat = "
					+ seat);
			oldSeat = seat;

			color = request.getParameter("color");
			if (color.equalsIgnoreCase("Choose a Color")) {
				color = "nothing";
			}
			LOG.info("INSIDE:: searchVehicle method:: customer with color = "
					+ color);
			oldColor = color;
		}

		VehicleDTO vehicleDTO = new VehicleDTO();
		vehicleDTO.setManufactureName(oldManufacturerName);
		vehicleDTO.setMinPrice(oldMinPrice);
		vehicleDTO.setMaxPrice(oldMaxPrice);
		vehicleDTO.setColor(oldColor);
		vehicleDTO.setSeat(oldSeat);
		vehicleDTO.setBranchLocation(oldLocation);

		CustomerDelegate customerDelegate = new CustomerDelegate();
		List<VehicleDTO> vehicleList = new ArrayList<VehicleDTO>();

		vehicleList = customerDelegate.vehicleSearch(vehicleDTO, firstVehicle,
				lastVehicle);
		request.setAttribute("searchList", vehicleList);
		RequestDispatcher rd = request.getRequestDispatcher("loop.jsp");

		if (rd != null) {
			rd.forward(request, response);
		}
	}

	/**
	 * @desc this method will be used to book desired vehicle based on
	 *       specifications
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws VRSDaoException
	 */

	protected void doBookVehicle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			VRSDaoException {
		LOG.info("INSIDE:: doBookVehicle method:: ");

		String branchID;
		String vehicleID;
		String str = request.getParameter("selIds");
		String vehicleAndBranch[] = str.split(":");
		int customerID = 0;
		HttpSession session = request.getSession(false);
		if (session.getAttribute("userID") != null) {
			customerID = Integer.parseInt(session.getAttribute("userID")
					.toString());
			LOG.info("INSIDE:: doBookVehicle method:: user with customerID = "
					+ customerID);

		}

		branchID = vehicleAndBranch[0];
		LOG.info("INSIDE:: doBookVehicle method::  branchID = " + branchID);

		vehicleID = vehicleAndBranch[1];
		LOG.info("INSIDE:: doBookVehicle method::  vehicleID = " + vehicleID);

		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setBranchID(branchID);
		bookingDTO.setVehicleID(vehicleID);
		bookingDTO.setCustomerID(customerID);
		CustomerDelegate customerDelegate = new CustomerDelegate();
		String bookingID = customerDelegate.bookVehicle(bookingDTO);
		LOG.info("INSIDE:: doBookVehicle method::  bookingID = " + bookingID);

		request.setAttribute("successMessage",
				"Thankyou, for booking. Your Booking id is " + bookingID);
		request.getRequestDispatcher("success.jsp").forward(request, response);

	}

	/**
	 * @desc this method will be used to show all booking list against the
	 *       particular customer
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws VRSDaoException
	 */

	protected void showBooking(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			VRSDaoException {
		LOG.info("INSIDE:: showBooking method:: ");

		int page = Integer.parseInt(request.getParameter("page").toString());

		first = first + page;
		last = last + page;

		response.setContentType("text/html");
		BookingDTO vdt = new BookingDTO();
		int customerID = 0;
		HttpSession session = request.getSession(false);
		if (session.getAttribute("userID") != null) {
			customerID = Integer.parseInt(session.getAttribute("userID")
					.toString());
		}
		LOG.info("INSIDE:: showBooking method:: user with customerID = "
				+ customerID);

		vdt.setCustomerID(customerID);
		CustomerDelegate customerDelegate = new CustomerDelegate();
		List<BookingDTO> bookingList = new ArrayList<BookingDTO>();
		bookingList = customerDelegate.searchVehicleBooking(vdt, first, last);
		request.setAttribute("bookingList", bookingList);
		LOG.info("INSIDE:: showBooking method:: bookingList with size = "
				+ bookingList.size());

		request.getRequestDispatcher("showAllBooking.jsp").forward(request,
				response);
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

}
