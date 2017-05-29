package com.vrs.delegate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.vrs.dao.BranchAdminDAO;
import com.vrs.dto.BookingDTO;
import com.vrs.dto.BranchAdminDTO;
import com.vrs.dto.UserDTO;
import com.vrs.dto.VehicleDTO;
import com.vrs.exceptions.VRSDaoException;


public class BranchAdminDelegate {
	
	
	static int choice1 = 0;
	 private static final Logger LOG=Logger.getLogger(BranchAdminDelegate.class);


	
	 
	 
	 
	
	
	
	/**@throws Exception 
	 * @throws VRSDaoException 
	 * @desc updation of branchAdmin details entered by branch admin 
	  *param branchAdminDTO
		 */

	public String updateBranchAdmin(BranchAdminDTO branchAdminDTO)
			throws VRSDaoException, Exception {
		
		
		LOG.info("INSIDE:: updateBranchAdmin method of BranchAdminDelegate");
		
		BranchAdminDAO branchAdminDAO = new BranchAdminDAO();
		
		
		 LOG.info("CALLING:: updateBranchAdminDetails method of BranchAdminDAO Class");
		return branchAdminDAO.updateBranchAdminDetails(branchAdminDTO);

	}

	/**@desc this method will be used to search the vehicle booking list
	  *@param BookingDTO bookingDTO, int first, int last
	  *@return  List<BookingDTO>bookingList
	  *@throws VRSDaoException
	*/
	public List<BookingDTO> searchVehicleBooking(BookingDTO bookingDTO, int first, int last)
			throws VRSDaoException {
		
		 LOG.info("INSIDE::searchVehicleBooking()  method of BranchAdminDelegate");

		List<BookingDTO> bookingList = new ArrayList<BookingDTO>();
		
		
		 LOG.info("CALLING:: searchAllVehicleBooking method of vehicleDAO Class");
		 BranchAdminDAO branchAdminDAO=new BranchAdminDAO();
		bookingList = branchAdminDAO.searchAllVehicleBooking(bookingDTO,first,last);
		return bookingList;
	}

	
	
	/**@desc this method will be used to approve booking
	  *@param bookingDTO
	  *return boolean value returned by approveBooking() method of vehicleDAO class
	  *throws VRSDaoException
	  */

	public int approveBooking(BookingDTO bookingDTO) throws VRSDaoException {

		 LOG.info("INSIDE:: approveBooking() method of BranchAdminDelegate");
		
		
		LOG.info("CALLING:: approveBooking() method of vehicleDAO Class");
		BranchAdminDAO branchAdminDAO=new BranchAdminDAO();

		return branchAdminDAO.approveBooking(bookingDTO);
	}

	public int vehicleRequest(VehicleDTO vrt) throws VRSDaoException{
		//Check Null
		BranchAdminDAO branchAdminDAO=new BranchAdminDAO();
		
		return branchAdminDAO.vehicleRequest(vrt);
	}

	
	/**
	 * @param userDTO
	 * @return
	 * @throws VRSDaoException
	 */
	
	public BranchAdminDTO getUserInformation(UserDTO userDTO) throws VRSDaoException{
		BranchAdminDAO branchAdminDAO=new BranchAdminDAO();
		return branchAdminDAO.getUserInformation(userDTO);
		
	}
	
}
