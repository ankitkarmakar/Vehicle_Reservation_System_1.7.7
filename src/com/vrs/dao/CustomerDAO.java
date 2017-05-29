package com.vrs.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.vrs.constants.Query;
import com.vrs.constants.ResourceClosing;
import com.vrs.dto.BookingDTO;
import com.vrs.dto.CustomerDTO;
import com.vrs.dto.UserDTO;
import com.vrs.dto.VehicleDTO;
import com.vrs.exceptions.VRSDaoException;
import com.vrs.utils.DbUtil;

/**
 * @author 502263
 *
 */
public class CustomerDAO {
	private static final Logger LOG = Logger.getLogger(CustomerDAO.class);

	/**
	 * @param customerDTO
	 * @return
	 * @throws VRSDaoException
	 */
	private Connection connection;
	PreparedStatement ps = null;
	ResultSet rs = null;
	int count = 0;
	boolean present = false;
	int numberOfRows;
	PreparedStatement prepareStatement = null;
	String returnMessage = null;

	public String updateCustomerDetails(CustomerDTO customerDTO)
			throws VRSDaoException {
		int updatedRows = 0;
		try {

			connection = DbUtil.getConnection();

			prepareStatement = connection
					.prepareStatement(Query.COUNT_UPDATION_QUERY);
			prepareStatement.setInt(1, customerDTO.getUserId());

			rs = prepareStatement.executeQuery();

			if (rs.next()) {
				numberOfRows = rs.getInt(1);
			} else {
				returnMessage = "Details are Successfully Updated";
			}
			if (numberOfRows == 0) {

				ps = connection.prepareStatement(Query.INSERT_CUSTOMER_DETAILS);
				ps.setInt(1, customerDTO.getUserId());
				ps.setString(2, customerDTO.getCustomerName());
				ps.setDate(3, new java.sql.Date(customerDTO.getDateOfBirth()
						.getTime()));
				ps.setString(4, customerDTO.getAddress());
				ps.setString(5, customerDTO.getPhoneNo());
				ps.setString(6, customerDTO.getEmail());
				ps.setString(7, customerDTO.getOccupation());
				count = ps.executeUpdate();

				if (count > 0) {
					returnMessage = "The Details Are  Successfully registered";
				}

			} else {
				LOG.debug("executing the query::"+Query.UPDATE_CUSTOMER_DETAILS);
				ps = connection.prepareStatement(Query.UPDATE_CUSTOMER_DETAILS);

				ps.setString(1, customerDTO.getCustomerName());
				ps.setDate(2, new java.sql.Date(customerDTO.getDateOfBirth()
						.getTime()));
				ps.setString(3, customerDTO.getAddress());
				ps.setString(4, customerDTO.getPhoneNo());
				ps.setString(5, customerDTO.getEmail());
				ps.setString(6, customerDTO.getOccupation());
				ps.setInt(7, customerDTO.getUserId());

				updatedRows = ps.executeUpdate();

				if (updatedRows != 0) {
					returnMessage = "Details are Successfully Updated";

				}
			}
		} catch (SQLException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("SQLExeption exception occured");
		} catch (ClassNotFoundException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("SQLExeption exception occured");
		} catch (IOException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("SQLExeption exception occured");
		}  finally {
			ResourceClosing.closeResources(rs, connection, prepareStatement);
		}
		return returnMessage;
	}

	/**
	 * @desc This method will Search the available Vehicle According to criteria
	 * @param vehicle
	 * @param firstVehicle
	 * @param lastVehicle
	 * @return
	 * @throws VRSDaoException
	 */
	public List<VehicleDTO> vehicleSearch(VehicleDTO vehicle, int firstVehicle,
			int lastVehicle) throws VRSDaoException {

		List<VehicleDTO> vehicleList = new ArrayList<VehicleDTO>();
		LOG.info("INSIDE:: vehicleSearch method of VehicleDAO");
		try {
			try {
				LOG.info("BEFORE:: Connection to the database");
				connection = DbUtil.getConnection();
			} catch (IOException e) {
				LOG.error("Exception occured . " + e.getCause());
				throw new VRSDaoException("IOExeption exception occured");
			}

			int addMore = 0;
			int flagForLocation = 0;
			StringBuffer sb = null;
			int queryIndex = 1;
			int flagForColor = 0;
			int flagForManufactureName = 0;
			int flagForPrice = 0;
			int flagForSeat = 0;
			LOG.debug("Executing the query::"+Query.SEARCH_CONSTANT);
			String query = Query.SEARCH_CONSTANT;
			sb = new StringBuffer(query);
			if (!(vehicle.getBranchLocation().equalsIgnoreCase("location"))) {
				sb.append("  lower(branch.branch_location)=lower(?) ");
				addMore++;
				flagForLocation = 1;
			}

			if (!(vehicle.getManufactureName().equalsIgnoreCase("mfname"))) {
				if (addMore > 0) {
					sb.append(" and ");
				}
				sb.append(" lower(vehicle.Manufacture_name)=lower(?)");
				addMore++;
				flagForManufactureName = 1;
			}
			if (vehicle.getSeat() != 100) {
				if (addMore > 0) {
					sb.append(" and ");
				}
				sb.append(" vehicle.seat=?");
				addMore++;
				flagForSeat = 1;
			}
			if (!(vehicle.getColor().equalsIgnoreCase("nothing"))) {
				if (addMore > 0) {
					sb.append(" and ");
				}
				sb.append(" lower(vehicle.color)=lower(?)");
				addMore++;
				flagForColor = 1;
			}
			if (vehicle.getMaxPrice() != 0 && vehicle.getMinPrice() != 0) {
				if (addMore > 0) {
					sb.append(" and ");
				}
				sb.append(" rent between ? and ?");
				addMore++;
				flagForPrice = 1;

			}

			sb.append(") where rn between ? and ?");
			query = sb.toString();

			ps = connection.prepareStatement(query);
			if (flagForLocation == 1) {
				ps.setString(queryIndex, vehicle.getBranchLocation());
				queryIndex++;
			}
			if (flagForManufactureName == 1) {
				ps.setString(queryIndex, vehicle.getManufactureName());
				queryIndex++;
			}
			if (flagForSeat == 1) {
				ps.setInt(queryIndex, vehicle.getSeat());
				queryIndex++;
			}
			if (flagForColor == 1) {
				ps.setString(queryIndex, vehicle.getColor());
				queryIndex++;

			}

			if (flagForPrice == 1) {
				ps.setInt(queryIndex, vehicle.getMinPrice());
				queryIndex++;
				ps.setInt(queryIndex, vehicle.getMaxPrice());
				queryIndex++;
			}
			ps.setInt(queryIndex, firstVehicle);
			queryIndex++;
			ps.setInt(queryIndex, lastVehicle);

			@SuppressWarnings("unused")
			String searchQuery = sb.toString();

			rs = ps.executeQuery();
			while (rs.next()) {

				VehicleDTO v = new VehicleDTO();
				v.setRent(rs.getInt("r"));
				v.setBranchID(rs.getString("b_id"));
				v.setVehicleName(rs.getString("v_name"));
				v.setVehicleId(rs.getString("v_id"));
				v.setManufactureName(rs.getString("m_name"));
				vehicleList.add(v);
			}

		} catch (ClassNotFoundException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Class not found exception occured");
		} catch (SQLException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("SQL exception occured");
		} finally {
			ResourceClosing.closeResources(rs, connection, prepareStatement);
		}

		return vehicleList;

	}

	/**
	 * @param vdto
	 * @param first
	 * @param last
	 * @return
	 * @throws VRSDaoException
	 */
	public List<BookingDTO> searchVehicleBooking(BookingDTO vdto, int first,
			int last) throws VRSDaoException {
		List<BookingDTO> bookingList = new ArrayList<BookingDTO>();
		try {
			connection = DbUtil.getConnection();
			LOG.debug("executing the query::"+Query.VEHICLE_SEARCH_QUERY);
			ps = connection.prepareStatement(Query.VEHICLE_SEARCH_QUERY);
			ps.setInt(1, vdto.getCustomerID());
			ps.setInt(2, first);
			ps.setInt(3, last);
			rs = ps.executeQuery();
			while (rs.next()) {
				String a = rs.getString("b_id");
				BookingDTO v = new BookingDTO();
				v.setBookingID(a);
				v.setBookingDate(new java.util.Date(rs.getDate("b_time").getTime()));
				v.setCustomerID(vdto.getCustomerID());
				v.setVehicleID(rs.getString("v_id"));
				v.setVehicleName(rs.getString("v_name"));
				v.setBookingApprovedFlag(rs.getString("flag"));
				v.setRent(rs.getInt("RENT"));
				bookingList.add(v);

			}

		} catch (ClassNotFoundException e) {

			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Class not found exception occured");
		} catch (IOException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("IOExeption exception occured");
		} catch (SQLException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("SQLException exception occured");
		} finally {
			ResourceClosing.closeResources(rs, connection, prepareStatement);
		}
		return bookingList;
	}

	/**
	 * @param bookingDTO
	 * @return
	 * @throws VRSDaoException
	 */
	public String bookVehicle(BookingDTO bookingDTO) throws VRSDaoException {
		String newBookingID = null;
		try {
			connection = DbUtil.getConnection();
			LOG.debug("executing the query::"+Query.MAXIMUM_BOOKINGID_SEARCH_QUERY);
			
			ps = connection
					.prepareStatement(Query.MAXIMUM_BOOKINGID_SEARCH_QUERY);

			rs = ps.executeQuery();
			rs.next();
			int bookingNumber = rs.getInt("max_booking_id");
			bookingNumber++;
			newBookingID = "book" + bookingNumber;
			Date date = new Date();
			LOG.debug("executing the query::"+Query.INSERT_INTO_BOOKING_QUERY);
			ps = connection.prepareStatement(Query.INSERT_INTO_BOOKING_QUERY);
			ps.setString(1, newBookingID);
			ps.setDate(2, new java.sql.Date(date.getTime()));
			ps.setInt(3, bookingDTO.getCustomerID());
			ps.setString(4, bookingDTO.getVehicleID());
			ps.setString(5, bookingDTO.getBranchID());
			ps.setString(6, "pending");
			ps.execute();

		} catch (ClassNotFoundException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Class not found exception occured");
		} catch (IOException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("IO exception occured");
		} catch (SQLException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("SQL exception occured");
		} finally {
			ResourceClosing.closeResources(rs, connection, prepareStatement);
		}
		return newBookingID;

	}

	/**
	 * @param userDTO
	 * @return
	 * @throws VRSDaoException
	 */
	public CustomerDTO getUserInformation(UserDTO userDTO)
			throws VRSDaoException {

		CustomerDTO customerDTO = new CustomerDTO();
		java.sql.Date date = null;
		java.util.Date dateOfBirth = null;
		try {
			connection = DbUtil.getConnection();
			LOG.debug("executing the query::"+Query.GET_CUSTOMER_DETAILS);
			ps = connection.prepareStatement(Query.GET_CUSTOMER_DETAILS);
			ps.setInt(1, userDTO.getUserId());
			rs = ps.executeQuery();

			if (rs.next()) {
				customerDTO.setCustomerName(rs.getString("CUSTOMER_NAME"));
				date = rs.getDate("DOB");
				dateOfBirth = new java.util.Date(date.getTime());
				customerDTO.setDateOfBirth(dateOfBirth);
				customerDTO.setAddress(rs.getString("ADDRESS").trim());
				customerDTO.setPhoneNo(rs.getString("PHONE_NUMBER"));
				customerDTO.setEmail(rs.getString("EMAIL"));
				customerDTO.setOccupation(rs.getString("OCCUPATION"));
			}
		} catch (ClassNotFoundException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("Class not found exception occured");
		} catch (IOException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("IO exception occured");
		} catch (SQLException e) {
			LOG.error("Exception occured . " + e.getCause());
			throw new VRSDaoException("SQL exception occured");
		}finally {
			ResourceClosing.closeResources(rs, connection, prepareStatement);
		}
		return customerDTO;
	}
	
}
