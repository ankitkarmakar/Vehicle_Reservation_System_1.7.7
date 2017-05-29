<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.vrs.dto.VehicleDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="vehicleBookDiv">
		<form method="post" action="aAction?choice=6">
			
			<table class="table table-bordered table-striped">
				<thead>
				<tr>
					<th>UserId</th>
					<th>RoleName</th>
					<th>RegistrationDate</th>
					<th>Status</th>
					<th>Select</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${requestScope.registrationRequestList}"	var="user">
					<tr>
						<td><c:out value="${user.userId}" />
						</td>
						<td><c:out value="${user.role}" />
						</td>
						<td><c:out value="${user.accounDate}" />
						</td>
						<td><c:out value="${user.flag}" />
						</td>

						<td><input type="checkbox" name="user"
							value=<c:out value="${user.userId}" /> onClick="EnableSubmit(this)"><br>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<input type="submit" class="btn btn-sm btn-info" id="ac" name="for approval" value="Approve" disabled>
			<input type="submit" class="btn btn-sm btn-info" id="rj" name="for rejection" value="Reject" disabled>

		</form>
	</div>
	<script type="text/javascript">
	EnableSubmit = function(val)
	{
	    var sbmt = document.getElementById("ac");
	    var sbmt1 = document.getElementById("rj");

	    if (val.checked == true)
	    {
	        sbmt.disabled = false;
	        sbmt1.disabled = false;
	    }
	    else
	    {
	        sbmt.disabled = true;
	        sbmt1.disabled = true;
	    }
	} 
	</script>
</body>
</html>