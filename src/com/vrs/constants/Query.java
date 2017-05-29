package com.vrs.constants;

public class Query {

	public static final String VEHICLE_SEARCH_QUERY = "select rent, b_id,b_time,c_id,v_id,v_name,flag from (select RENT as rent, booking.BOOKING_ID as b_id,booking.BOOKING_TIME as b_time,booking.CUSTOMER_ID as c_id,booking.VEHICLE_ID as v_id,VEHICLE_NAME as v_name,booking.BOOKING_APPROVE_FLAG as flag,booking.BOOKING_CONFIRMED_DATE,rownum as rn from booking inner join vehicle on vehicle.VEHICLE_ID=booking.VEHICLE_ID where customer_id=?)where rn between ? and ? order by booking_confirmed_date";

	public static final String MAXIMUM_BOOKINGID_SEARCH_QUERY = "select max(to_number(substr(booking_id,5))) as max_booking_id from booking";

	public static final String INSERT_INTO_BOOKING_QUERY = "INSERT INTO BOOKING (booking_id,booking_time,customer_id,vehicle_id,branch_id,booking_approve_flag) VALUES (?,?,?,?,?,?)";

	public static final String VEHICLE_BOOKING_SEARCH_QUERY = "select r,b_id,v_name,v_id from(select rent as r,vehicle_location.branch_id as b_id,vehicle_name as v_name,vehicle.vehicle_id as v_id,rownum as rn from vehicle join vehicle_location on vehicle.vehicle_id=vehicle_location.vehicle_id join branch on vehicle_location.branch_id=branch.branch_id where branch.branch_location=? and vehicle.Manufacture_name=? and vehicle.seat=? and vehicle.color=? and rent between ? and ?) where rn between ? and ?";

	public static final String REGISTRATION_QUERY = "INSERT INTO user_table"
			+ "(user_id, pass, role_name, flag,ACCOUNT_DATE) VALUES"
			+ "(?,?,?,?,?)";

	public static final String LOGIN_QUERY = "SELECT USER_ID,pass,flag,role_name FROM user_table where user_id=? and pass=?";

	public static final String APPROVED_ACCOUNT_QUERY = "UPDATE user_table"
			+ " SET flag = 'Approved' " + " WHERE USER_ID=?";

	public static final String REJECT_ACCOUNT_QUERY = "UPDATE user_table"
			+ " SET flag = 'Rejected' " + " WHERE USER_ID=?";

	public static final String ADD_NEW_VEHICLE_QUERY = "insert into vehicle values (?,?,?,?,?,?,?,?,?)";

	public static final String VEHICLE_ID_FETCH_QUERY = "select max(to_number(substr(VEHICLE_REQUEST_ID,4))) as max_vehicle_id from VEHICLE_REQUEST";

	public static final String VEHICLE_REQUEST_QUERY = "insert into VEHICLE_REQUEST (VEHICLE_REQUEST_ID,BRANCH_ID,REQUEST_DATE,VEHICLE_ID,NUMBER_OF_VEHICLE,APPROVE_REQUEST_FLAG) values (?,?,?,?,?,?)";

	public static final String ALL_VEHICLE_SEARCH_QUERY = "select b_id,b_time,c_id,v_name,m_name,e_s_price,col from(select BOOKING_ID as b_id,BOOKING_TIME as b_time,CUSTOMER_ID as c_id,VEHICLE_NAME as v_name, MANUFACTURE_NAME as m_name,EX_SHOWROOM_PRICE as e_s_price,color as col,rownum as rn from booking inner join BRANCH_ADMIN on booking.branch_ID=BRANCH_ADMIN.branch_ID inner join vehicle on vehicle.vehicle_id=booking.vehicle_id where BOOKING_APPROVE_FLAG='pending' and BRANCH_ADMIN.USER_ID=? and VEHICLE_ID=?) where rn between ? and ?";
	
	public static final String GET_VEHICLE_LIST_QUERY = "select v_id,v_r_id,r_date,b_id,n_o_v from(select VEHICLE_ID as v_id,vehicle_request_id as v_r_id,request_date as r_date,branch_id as b_id,number_of_vehicle as n_o_v,rownum as rn from vehicle_request where approve_request_flag='pending') where rn between ? and ?";
	
	/*public static final String UPDATE_CUSTOMER_DETAILS = "insert into customer values(?,?,?,?,?,?,?)";*/
	
	public static final String BRANCH_ADMIN_ADD = "insert into branch_admin values(?,?,?,?,?,?)";
	public static final String GET_VEHICLE_STOCK_QUERY = "select BRANCH_VEHICLE_STOCK,vehicle_location.branch_id b_id from vehicle_location inner join branch_admin on vehicle_location.branch_id=branch_admin.branch_id where vehicle_location.vehicle_id=? and branch_admin.USER_ID=?";

	public static final String FETCH_VEHICLE_REQUEST_QUERY=" select number_of_vehicle,branch_id,vehicle_id  from vehicle_request where vehicle_request_id=?";
	
	public static final String COUNT_VEHICLE_QUERY="select count(vehicle_id) as count_vehicle from vehicle where vehicle_id=?";
	
	public static final String GET_VEHICLE_MAIN_STOCK_QUERY="select total_stock  from vehicle where vehicle_id=?";
	
	public static final String APPROVE_OR_REJECT_VEHICLE_REQUEST_QUERY="UPDATE vehicle_request SET approve_request_flag =?,approve_request_date=(select sysdate from dual) WHERE vehicle_request_id=?";
	
	public static final String UPDATE_VEHICLE_MAIN_STOCK_QUERY="update vehicle set total_stock=? where vehicle_id=?";
	
	public static final String COUNT_BRANCH_LOCATION_RECORDS_QUERY="select count(*) as count_branch_location_records from vehicle_location where vehicle_id=? and branch_id=?";
	
	public static final String FETCH_BRANCH_EXISTING_VEHICLE_STOCK_QUERY="select branch_vehicle_stock  from vehicle_location where vehicle_id=? and branch_id=?";
	
	public static final String BRANCH_VEHICLE_UPDATE_QUERY="update vehicle_location set branch_vehicle_stock=? where vehicle_id=? and branch_id=?";
	
	public static final String INSERT_RECORDS_INTO_VEHICLE_LOCATION="insert into vehicle_location values(?,?,?)";
	
	
	public static final String SELECT_EXISTING_VEHICLE_MAIN_STOCK_QUERY="select total_stock from vehicle where vehicle_id=?";
	
	public static final String ADD_VEHICLE_MAIN_STOCK_QUERY="update vehicle set total_stock=? where vehicle_id=? and manufacture_name=? and ex_showroom_price=? and rent=? and type=? and color=? and seat=? and vehicle_name=?";
	
	public static final String GET_PENDING_REGISTRATION_REQUEST_LIST_QUERY="SELECT USER_ID,role_name,account_date,flag FROM user_table where flag='pending' ";
	
	public static final String GET_ALL_REGISTRATION_REQUEST_LIST_QUERY="SELECT USER_ID,role_name,account_date,flag FROM user_table order by account_date,flag,role_name";
	
	public static final String GET_PENDING_REGISTRATION_REQUEST_LIST_BY_ROLE_QUERY="SELECT USER_ID,role_name,account_date,flag FROM user_table where flag='pending' and role_name=? ";
	
	public static final String GET_PENDING_REGISTRATION_REQUEST_LIST_BY_STATUS_QUERY="SELECT USER_ID,role_name,account_date,flag FROM user_table where flag=? order by account_date,flag,role_name";
	public static final String UPDATE_CUSTOMER_DETAILS="update customer set CUSTOMER_NAME=?,DOB=?,ADDRESS=?,PHONE_NUMBER=?,EMAIL=?,OCCUPATION=? where USER_ID=?";

	public static final String BRANCH_ADMIN_UPDATE = "update branch_admin set BRANCH_ADDRESS=?,BRANCH_LOCATION=?,BRANCH_PHONE_NUMBER=?,BRANCH_EMAIL=? where user_id=?";

	public static final String BRANCH_UPDATE = "update branch set branch_location=? where branch_id=?";

	public static final String GET_BRANCH_ADMIN_BRANCH_ID = "select branch_id from branch_admin where user_id=?";

	public static final String GET_BRANCH_ID_FROM_BRANCH = "select branch_id from branch where branch_location=?";
	
	public static final String CHECK_EXIST_USER_ID_QUERY="select count(user_id) as count_existing_user from user_table where user_id=?";
	public static final String INSERT_CUSTOMER_DETAILS = "insert into customer values(?,?,?,?,?,?,?)";
	public static final String COUNT_UPDATION_QUERY="select count(USER_ID) from customer where user_id=?"; 

	public static final String GET_BRANCHADMIN_DETAILS = "select BRANCH_ADDRESS, BRANCH_LOCATION, BRANCH_PHONE_NUMBER, BRANCH_EMAIL , BRANCH_ID from BRANCH_ADMIN where USER_ID = ?";

	public static final String GET_CUSTOMER_DETAILS = "select CUSTOMER_NAME, DOB, ADDRESS, PHONE_NUMBER, EMAIL, OCCUPATION from CUSTOMER where USER_ID = ?";

	public static final String GET_NEW_VEHICLE_STOCK_QUERY = "select BRANCH_VEHICLE_STOCK from vehicle_location where vehicle_id=?";

	public static final String UPDATE_BOOKING_QUERY = "update booking set booking.booking_confirmed_date=? , booking.booking_approve_flag=? where booking.booking_id=?";

	public static final String UPDATE_VEHICLE_LOCATION_FOR_BOOKING_QUERY = "update vehicle_location set vehicle_location.branch_vehicle_stock=? where vehicle_location.vehicle_id=? and vehicle_location.branch_id=?";
    public static final String COUNT_BRANCHADMIN_QUERY="select count(USER_ID) from branch_admin where user_id=?";
	public static final String INSERT_BRANCH_ADMIN_DETAILS = "insert into branch_admin values(?,?,?,?,?,?)";
	public static final String UPDATE_BRANCH_ADMIN_DETAILS = "update branch_admin set BRANCH_ADDRESS=?,BRANCH_LOCATION=?,BRANCH_PHONE_NUMBER=?,BRANCH_EMAIL=? where user_id=?";

	public static final String SEARCH_CONSTANT = "select r,b_id,v_name,v_id,m_name from(select rent as r,vehicle_location.branch_id as b_id,vehicle_name as v_name, MANUFACTURE_NAME as m_name,vehicle.vehicle_id as v_id,rownum as rn from vehicle join vehicle_location on vehicle.vehicle_id=vehicle_location.vehicle_id join branch on vehicle_location.branch_id=branch.branch_id where";


}
