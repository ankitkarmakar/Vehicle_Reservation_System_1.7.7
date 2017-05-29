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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link href="bootstrap-3.3.4-dist/css/bootstrap.css" rel='stylesheet'
	type='text/css' />
<link href="bootstrap-3.3.4-dist/css/bootstrap.min.css" rel='stylesheet'
	type='text/css' />
<link href="css/datepicker.css" rel='stylesheet' type='text/css' />
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/datepicker.js"></script>
<script type="text/javascript" src="js/validate.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="js/validate.js"></script>
</head>
<body>
	<!-- <div id="vehicleBookDiv"> -->
	<form action="cAction?choice=4" method="post">
		<c:if test="${requestScope.searchList.size() gt 0}">
			
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<td><b>Manufacture Name: </b></td>
						<td><b>Vehicle Id: </b></td>
						<td><b>Vehicle Name: </b></td>
						<td><b>Vehicle Rent: </b></td>
						<td><b>Select:</b></td>
					</tr>				
				</thead>
				<tbody>
					<c:forEach items="${requestScope.searchList}" var="vehicle">
						<tr>
							<td> <c:out	value="${vehicle.manufactureName}" /></td>
							<td> <c:out value="${vehicle.vehicleId}" /></td>
							<td> <c:out	value="${vehicle.vehicleName}" /></td>
							<td> <c:out value="${vehicle.rent}" /></td>
							<td><input type="radio" name="selIds"
								value='${vehicle.branchID}:${vehicle.vehicleId}' required/></td>
						</tr>
					</c:forEach>


				</tbody>
			</table>


			<input type="submit" class="btn btn-sm btn-default" value="Book">
		</c:if>
	</form>

	<c:if test="${requestScope.searchList.size() eq 0}">
		<label> <font color="red">Sorry..! No result Found</font></label>
	</c:if>
	<!-- </div> -->
</body>
</html>