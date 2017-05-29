package com.vrs.junit.testcase;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.vrs.dao.UserDAO;
import com.vrs.dto.UserDTO;
import com.vrs.exceptions.VRSDaoException;

import junit.framework.TestCase;

public class LoginTest extends TestCase {

	@Test
	public void testLoginPass() throws ClassNotFoundException, SQLException,
			IOException, VRSDaoException {
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(123456);
		userDTO.setPassword("123456789");

		String role = "";

		role = userDAO.loginUser(userDTO);
		System.out.println(role);

		assertEquals("customer", role);

	}

	/*@Test
	public void testLoginFail() throws ClassNotFoundException, SQLException,
			IOException, VRSDaoException {
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(123456);
		userDTO.setPassword("wrongpwd");

		String role = "";

		role = userDAO.loginUser(userDTO);
		//System.out.println(role);

		assertNotSame("customer", role);

	}
*/
}
