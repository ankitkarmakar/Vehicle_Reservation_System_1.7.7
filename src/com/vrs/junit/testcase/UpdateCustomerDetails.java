package com.vrs.junit.testcase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.vrs.dao.CustomerDAO;
import com.vrs.delegate.CustomerDelegate;
import com.vrs.dto.CustomerDTO;

import junit.framework.TestCase;

public class UpdateCustomerDetails extends TestCase {

	@Test //public boolean updateCustomerDetails(CustomerDTO)
	public void testUpdateCustomerDetails(){
		CustomerDAO customerDAO = new CustomerDAO();
		CustomerDTO customerDTO = new CustomerDTO();
		
		
		
		
		
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			date = sdf.parse("01-JAN-1990");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		DateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
	    Date date=new Date();
	    Date date1 = null;
	    sdf.setLenient(false);
	    try {
			 date1=sdf.parse("01-JAN-1990");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		customerDTO.setUserId(324231);customerDTO.setCustomerName("aaaa");customerDTO.setDateOfBirth(date1);customerDTO.setAddress("address");customerDTO.setEmail("gcvdv@fghjvg.com");customerDTO.setPhoneNo("033-6363636");customerDTO.setOccupation("bvfbvkb");
		
		customerDTO.setAccounDate(date);
		boolean insertFlag=false;

		try{
			/*insertFlag = customerDAO.updateCustomerDetails(customerDTO);*/
			//TODO Based on your need, provide necessary assertion condition
	//		System.out.println(insertFlag);
		assertEquals(true,insertFlag);
		}catch(Exception e){
		//	System.out.println(e.getMessage());
			fail();
		}
	}
	

}
