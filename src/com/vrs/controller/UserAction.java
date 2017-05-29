package com.vrs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vrs.delegate.BranchAdminDelegate;
import com.vrs.delegate.CustomerDelegate;
import com.vrs.delegate.UserDelegate;
import com.vrs.dto.BranchAdminDTO;
import com.vrs.dto.CustomerDTO;
import com.vrs.dto.UserDTO;
import com.vrs.exceptions.VRSDaoException;
import org.apache.log4j.Logger;

/**
 * @author 502263
 *
 */
public class UserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UserAction.class);

	public UserAction() {
		super();
	}

	/**
	 * @desc this method will describe the action chosen by users such as
	 *       whether to login or to register or to logout and navigate to the
	 *       corresponding methods
	 * @param request
	 * @param response
	 * @throws Exception
	 */

	protected void doUserAction(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOG.info("INSIDE:: doUserAction method");
		int choice = 0;
		choice = Integer.parseInt(request.getParameter("choice"));
		LOG.debug("BEFORE:: Switch case in doUserAction method with choice "
				+ choice);
		switch (choice) {
		case 1:
			doLogin(request, response);
			break;
		case 2:
			doRegistration(request, response);
			break;
		case 3:
			doLogout(request, response);
			break;
		case 4:
			doCustomerDetailsRegistration(request, response);
			break;
		case 5:
			checkSession(request, response);
			break;
		default:
			break;
		}
	}

	/**
	 * @desc this method will be used to log in by user based on valid login
	 *       details otherwise show an error message and on successful login
	 *       redirect to the corresponding home page based on the roles
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws VRSDaoException
	 */

	protected void doLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ClassNotFoundException, VRSDaoException {

		LOG.info("INSIDE:: doLogin method:: ");
		String userID = request.getParameter("userName");
		String password = request.getParameter("password");
		LOG.info("INSIDE:: doLogin method:: user with userID = " + userID);
		int userId = Integer.parseInt(userID);
		HttpSession session = request.getSession();
		UserDTO user = new UserDTO();
		String returnRole = null;
		user.setUserId(userId);
		user.setPassword(password);
		returnRole = new UserDelegate().loginDelegateAction(user);
		if (returnRole == null) {
			LOG.info("INSIDE:: doLogin method:: invalid user with userID = "
					+ userId);
			request.setAttribute("message", "Login Not Successful");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else {
			user.setRole(returnRole);
			session.setAttribute("ROLE", returnRole);
			if (returnRole.equalsIgnoreCase("branchAdmin")) {
				session.setAttribute("userID", userID);
				LOG.info("INSIDE:: doLogin method:: valid Branch Admin with userID = "
						+ userId);
				if (new UserDelegate().isExist(user)) {
					BranchAdminDTO branchAdminDTO = new BranchAdminDTO();
					branchAdminDTO = getBranchAdminInformation(user);
					request.setAttribute("branchAdminDTO", branchAdminDTO);
					request.getRequestDispatcher("branchAdminHome.jsp")
							.forward(request, response);
				} else {
					request.getRequestDispatcher("branchAdminRegister.jsp");
					response.sendRedirect("branchAdminRegister.jsp");
				}
			} else if (returnRole.equalsIgnoreCase("admin")) {
				session.setAttribute("userID", userID);
				LOG.info("INSIDE:: doLogin method:: valid Admin with userID = "
						+ userId);
				response.sendRedirect("adminHome.jsp");
			} else if (returnRole.equalsIgnoreCase("customer")) {
				session.setAttribute("userID", userID);
				LOG.info("INSIDE:: doLogin method:: valid customer with userID = "
						+ userId);
				if (new UserDelegate().isExist(user)) {
					CustomerDTO customerDTO = new CustomerDTO();
					customerDTO = getCustomerInformation(user);
					request.setAttribute("customerDTO", customerDTO);
					request.getRequestDispatcher("userHome.jsp").forward(
							request, response);
				} else {
					request.getRequestDispatcher("customerRegister.jsp");
					response.sendRedirect("customerRegister.jsp");
				}
			}
		}
	}

	/**
	 * @desc this method will be used to do the registration of new user,on
	 *       successful registration shows the success message other wise
	 *       redirect to the error page
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 * @throws VRSDaoException
	 */

	protected void doRegistration(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, VRSDaoException {

		LOG.info("INSIDE:: doRegistration method:: ");
		int userId = Integer
				.parseInt((String) request.getParameter("userName"));
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		int returnValue = 0;
		if (role.equalsIgnoreCase("branchAdmin")) {
			LOG.info("INSIDE:: doRegistration method:: userId = " + userId
					+ " Requests for registration as a Branch Admin");
			BranchAdminDTO branchAdmin = new BranchAdminDTO();
			branchAdmin.setUserId(userId);
			branchAdmin.setPassword(password);
			branchAdmin.setRole(role);
			returnValue = new UserDelegate().registerAction(branchAdmin);

		}
		if (role.equalsIgnoreCase("customer")) {
			LOG.info("INSIDE:: doRegistration method:: userId = " + userId
					+ " Requests for registration as a Customer");
			CustomerDTO customer = new CustomerDTO();
			customer.setUserId(userId);
			customer.setPassword(password);
			customer.setRole(role);
			returnValue = new UserDelegate().registerAction(customer);

		}
		if (returnValue == 1) {

			response.sendRedirect("login.jsp");
		} else if (returnValue == 2) {

			request.setAttribute("message",
					"Given user Id Already exist try another");
			request.getRequestDispatcher("registration.jsp").forward(request,
					response);

		} else {
			response.sendRedirect("errorPage.jsp");
		}

	}

	/**
	 * @desc this method will be used to successfully logout from the session
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	protected void doLogout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		LOG.info("INSIDE:: doLogout method:: ");
		HttpSession session = request.getSession(false);
		LOG.info("INSIDE:: doRegistration method:: Ending Session of UserID = "
				+ session.getAttribute("userID"));
		session.removeAttribute("userID");
		session.invalidate();
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @desc this method will be used to register the details of the customer
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	protected void doCustomerDetailsRegistration(HttpServletRequest request,
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		String dateOfBirth = request.getParameter("dob");
		LOG.info("INSIDE:: doCustomerDetailsUpdation method:: customer with date of birth = "
				+ dateOfBirth);

		Date date = null;
		try {
			date = sdf.parse(dateOfBirth);
		} catch (ParseException e) {
			request.setAttribute("errorMessage",
					"Sorry..! An Internal Error Occured");
			response.sendError(504);
		}

		String address = request.getParameter("address");
		LOG.debug("INSIDE:: doCustomerDetailsUpdation method:: customer with address = "
				+ address);

		String mailId = request.getParameter("mailId");
		LOG.debug("INSIDE:: doCustomerDetailsUpdation method:: customer with mailId = "
				+ mailId);

		String mobile = request.getParameter("mobile");
		LOG.debug("INSIDE:: doCustomerDetailsUpdation method:: customer with mobile = "
				+ mobile);

		String occupation = request.getParameter("occupation");
		LOG.debug("INSIDE:: doCustomerDetailsUpdation method:: customer with occupation = "
				+ occupation);

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setUserId(userId);
		customerDTO.setCustomerName(customerName);
		customerDTO.setDateOfBirth(date);
		customerDTO.setAddress(address);
		customerDTO.setEmail(mailId);
		customerDTO.setPhoneNo(mobile);
		customerDTO.setOccupation(occupation);
		CustomerDelegate customerDelegate = new CustomerDelegate();
		customerDelegate.updateCustomerDetailValidation(customerDTO);

		request.setAttribute("successMessage",
				"Your Profile Has Been Successfully Updated..");
		request.getRequestDispatcher("success.jsp").forward(request, response);

	}

	/**@desc this method is used fetch BranchAdmin Information
	 * @param userDTO
	 * @return
	 * @throws VRSDaoException
	 */
	protected BranchAdminDTO getBranchAdminInformation(UserDTO userDTO)
			throws VRSDaoException {

		BranchAdminDTO branchAdminDTO = new BranchAdminDTO();
		BranchAdminDelegate branchAdminDelegate = new BranchAdminDelegate();
		branchAdminDTO = branchAdminDelegate.getUserInformation(userDTO);
		return branchAdminDTO;

	}

	/**@desc this method is used fetch BranchAdmin Information
	 * @param userDTO
	 * @return
	 * @throws VRSDaoException
	 */
	protected CustomerDTO getCustomerInformation(UserDTO userDTO)
			throws VRSDaoException {

		CustomerDTO customerDTO = new CustomerDTO();
		CustomerDelegate customerDelegate = new CustomerDelegate();
		customerDTO = customerDelegate.getUserInformation(userDTO);
		return customerDTO;

	}

	/**@desc this method is used to checkSession
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void checkSession(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("userID") == null) {
			String text = "login.jsp";
			response.setContentType("text/plain"); 
													
													
			response.setCharacterEncoding("UTF-8"); 
													
			response.getWriter().write(text);
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			doUserAction(request, response);
		} catch (Exception e) {
			LOG.error("INSIDE:: doGet method:: Exception occured ", e);
			request.setAttribute("errorMessage",
					"Sorry..! An Internal Error Occured");
			response.sendError(504);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			doUserAction(request, response);
		} catch (Exception e) {
			LOG.error("INSIDE:: doPost method:: Exception occured ", e);
			request.setAttribute("errorMessage",
					"Sorry..! An Internal Error Occured");
			response.sendError(504);
		}
	}

}
