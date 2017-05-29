<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.vrs.dto.BookingDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div id="vehicleBookDiv">

		<form action="baAction?choice=4" method="post">
			<c:if test="${requestScope.list.size() gt 0}">

				<h4>Search Results</h4>
				<table class="table text-center table-bordered table-striped">
					<thead>
					<tr>
						<td><b>Booking ID: </b></td>
						<td><b>Vehicle Name: </b></td>
						<td><b>Ex-Showroom Price: </b></td>
						<td><b>Manufacturer Name: </b></td>
						<td><b>Enter the comment</b></td>
						<td><b>Select your option</b></td>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.list}" var="booking">
							<!-- <form action="approveBooking" method="post"> -->
							<tr>
								<td><c:out value="${booking.bookingID}" />
								</td>
								<td><c:out value="${booking.vehicleName}" />
								</td>
								<td><c:out value="${booking.exShowroomPrice}" />
								</td>
								<td> <c:out
										value="${booking.manufacturerName}" />
								</td>
								<td><input type="text" name="comment" class="form-control"
								placeholder="Comment" 
								value="<c:out value="${param.comment}" />">
								</td>
								<td><input type="radio" name="selIds"
									value='${booking.bookingID}:${booking.vehicleID}' required/>
								</td>
								<!-- <td><input type="submit" value="submit values"></td> -->
							</tr>
							<!-- </form> -->
						</c:forEach>
					</tbody>
				</table>
				<input type="submit" class="btn btn-sm btn-success" value="submit values">
			</c:if>
		</form>
		<c:if test="${requestScope.list.size() eq 0}">
		<br>
			<label>
				<font color="red">Sorry..! No result Found</font>
			</label>
		</c:if>
	</div>
</body>
</html>