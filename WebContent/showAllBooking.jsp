<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.vrs.dto.VehicleDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- <div id="vehicleBookDiv"> -->

	<c:if test="${requestScope.bookingList.size() gt 0}">
		<br>
		<h3>My Bookings</h3>
		<table class="table table-bordered table-striped">

			<thead>
				<tr>
					<td><b>Booking ID: </b></td>
					<td><b>Vehicle Id: </b></td>
					<td><b>Vehicle Name: </b></td>
					<td><b>Rent: </b></td>
					<td><b>Booking Date: </b></td>
					<td><b>Booking Status: </b></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.bookingList}" var="booking">
					<tr>
						<td><c:out value="${booking.bookingID}" /></td>
						<td><c:out value="${booking.vehicleID}" /></td>
						<td><c:out value="${booking.vehicleName}" /></td>
						<td><c:out value="${booking.rent}" /></td>
						<td><c:out value="${booking.bookingDate}" /></td>
						<td><c:out value="${booking.bookingApprovedFlag}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- <input type="submit" value="submit values"> -->
	</c:if>

	<c:if test="${requestScope.bookingList.size() eq 0}">
		<br>
		<h4>
			<font color="red">No Booking found</font>
		</h4>
	</c:if>
	<!-- </div> -->
</body>
</html>