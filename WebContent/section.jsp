<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="bookingDetails">
			<h3>
				<small>Booking 1</small>
			</h3>
			<table class="table table-striped">

				<tbody>
				<%-- <c:forEach items="${requestScope.bookingList}" var="booking"> --%> 
					<tr>
						<td><b>Name: </b><c:out value="${booking.customerID}" /></td>
						<td><b>Vehicle: </b> Tata Indigo</td>
						<td><b>Booking Id: </b> jd659</td>
						<td><button type="submit" class="btn btn-success">Approve</button>
							<button type="submit" class="btn btn-danger">Reject</button></td>
					</tr>
					<tr>
						<td><b>User Id: </b> dj10002</td>
						<td><b>Rent: </b> 660 &#8360;</td>
						<td><b>Booking Date: </b> 12 Aug 2015</td>
						<td></td>
					</tr>
					<tr>
						<td><b>e-mail: </b> doe.john@rediff.com</td>
						<td><b>Vehicle Id: </b> ti321</td>
						<td><b>Booking Time: </b> 04:16 pm</td>
						<td></td>
					</tr>
					<%-- </c:forEach> --%>
				</tbody>
			</table>
	</div>

</body>
</html>