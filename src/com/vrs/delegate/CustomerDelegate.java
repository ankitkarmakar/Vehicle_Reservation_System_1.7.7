package com.vrs.delegate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.vrs.controller.CustomerAction;
import com.vrs.dao.CustomerDAO;

import com.vrs.dto.BookingDTO;
import com.vrs.dto.CustomerDTO;
import com.vrs.dto.UserDTO;
import com.vrs.dto.VehicleDTO;
import com.vrs.exceptions.VRSDaoException;

public class CustomerDelegate {

	static int choice = 0;
	private static final Logger LOG=Logger.getLogger(CustomerAction.class);
	
	
	
	
	/**@desc this method will be used to validate the entered customer details against the busineess rules
	  *@param customerDTO
	 * @throws Exception 
	*/

	public void updateCustomerDetailValidation(CustomerDTO customerDTO)
			throws Exception {
		LOG.info("INSIDE:: updateCustomerDetailValidation method of CustomerDelegate");
		
		
		if (isUpdateCustomerDetailValidate(customerDTO)) {
			LOG.info("CALLING::isUpdateCustomerDetailValidate() method of CustomerDelegate");
		}
		updateCustomerDetails(customerDTO);
	}
	
	
	
	/**@desc this method will be used to validate the entered customer details against the busineess rules
	   *@param customerDTO
	   *@return boolean validationFlag
	   *@throws VRSDaoException
	*/


	private boolean isUpdateCustomerDetailValidate(CustomerDTO customerDTO) {
		LOG.info("INSIDE:: isUpdateCustomerDetailValidate method of CustomerDelegate");
		
		boolean validationFlag = false;
		LOG.info("INSIDE:: isUpdateCustomerDetailValidate ::validationFlag with value "+validationFlag);

		
		
		if ((customerDTO.getUserId() != 0)
				&& (customerDTO.getCustomerName() != null)
				&& (customerDTO.getEmail() != null)
				&& (customerDTO.getOccupation() != null)
				&& (customerDTO.getDateOfBirth() != null)
				&& (customerDTO.getPhoneNo() != null)
				&& (customerDTO.getAddress() != null)) {

			String customerValidatePattern = "[a-zA-Z]{1,}";
			String validateCustomerEmailPattern = "[a-zA-Z0-9]{3,}[@]{1}[a-zA-Z0-9]{5,}[.]{1}+com";
			String validateCustomerPhoneNoPattern = "[0-9]{3}[-]{1}[0-9]{7}";

			if (customerDTO.getCustomerName().matches(customerValidatePattern)) {
				if (customerDTO.getEmail()
						.matches(validateCustomerEmailPattern)) {
					if (customerDTO.getPhoneNo().matches(
							validateCustomerPhoneNoPattern)) {
						Calendar c1 = Calendar.getInstance();
						Calendar c2 = Calendar.getInstance();
						c2.setTime(customerDTO.getDateOfBirth());
						if (((c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000L * 3600L * 24 * 365)) > 20) {
							validationFlag = true;
							LOG.info("INSIDE:: isUpdateCustomerDetailValidate ::validationFlag with value "+validationFlag);
							
						}

					}
				}
			}

		} else {
			
		}
		return validationFlag;
	}

	
	
	
	/**@desc this method will be used to update the customer details for the very first time
	   *@param customerDTO
	 * @throws Exception 
	*/
	public String updateCustomerDetails(CustomerDTO customerDTO)
			throws Exception {
		LOG.info("INSIDE:: updateCustomerDetails method:: ");
		CustomerDAO customerUpdateDao = new CustomerDAO();

		
		LOG.info("INSIDE:: isUpdateCustomerDetailValidate ::calling updateCustomerDetails of CustomerDAO"  );
		return customerUpdateDao.updateCustomerDetails(customerDTO);

	}

	
	/**@desc this method will be used to search the vehicle List
	  *@param VehicleDTO vdto, int firstVehicle, int lastVehicle
	  *@return List<VehicleDTO>vehicleList
	  *@throws VRSDaoException
	*/

	public List<VehicleDTO> vehicleSearch(VehicleDTO vdto, int firstVehicle, int lastVehicle) throws VRSDaoException {
		choice = 1;
		LOG.info("INSIDE:: vehicleSearch method:: ");
		List<VehicleDTO> vehicleList = new ArrayList<VehicleDTO>();
		
		CustomerDAO customerDAO=new CustomerDAO();
		LOG.info("INSIDE:: vehicleSearch::calling vehicleSearch of VehicleDAO ");
		vehicleList = customerDAO.vehicleSearch(vdto,firstVehicle,lastVehicle);
		
		LOG.info("INSIDE:: vehicleSearch method:: vehicleList with size = "+vehicleList.size());

		return vehicleList;
	}
	
	
	/**@desc this method will be used to book the vehicle 
	  *@param List<VehicleDTO> vehicleList, int vehicleChoice,int customerID
	  *@return String bookingID
	  *@throws VRSDaoException
	*/

	

	
	
	/**@desc this method will be used to search the vehicleBooking list 
	  *@param BookingDTO vdto, int first, int last
	  *@return List<BookingDTO>bookingList
	  *@throws VRSDaoException
	*/

	public List<BookingDTO> searchVehicleBooking(BookingDTO vdto, int first, int last)
			throws VRSDaoException {
		LOG.info("INSIDE:: searchVehicleBooking method:: ");
		
		CustomerDAO customerDAO=new CustomerDAO();
		List<BookingDTO> bookingList = new ArrayList<BookingDTO>();
		try {
			bookingList = customerDAO.searchVehicleBooking(vdto,first,last);
		} catch (VRSDaoException e) {
			
			LOG.error("Exception occurred",e);
			throw new VRSDaoException(e.getMessage());
		}
		LOG.info("INSIDE:: searchVehicleBooking method:: bookingList with size = "+bookingList.size());

		return bookingList;

	}
	
	
	/**@desc this method will be used to book the vehicle 
	  *@param bookingDTO
	  *@return String bookingID
	  *@throws VRSDaoException
	*/


	public String bookVehicle(BookingDTO bookingDTO) throws VRSDaoException {

		LOG.info("INSIDE:: bookVehicle method:: ");
		String bookingID = null;
		
		CustomerDAO customerDAO=new CustomerDAO();

		try {
			bookingID = customerDAO.bookVehicle(bookingDTO);
		} catch (VRSDaoException e) {
			
			
			LOG.error("Exception occurred",e);
			throw new VRSDaoException(e.getMessage());
		}
		LOG.info("INSIDE:: bookVehicle method:: user with userID = "+bookingID);

		return bookingID;
	}
	

	public CustomerDTO getUserInformation(UserDTO userDTO)
			throws VRSDaoException{
		CustomerDAO customerDAO= new CustomerDAO();
		return customerDAO.getUserInformation(userDTO);
	}
}
