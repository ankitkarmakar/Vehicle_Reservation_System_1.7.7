<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>


	<div>
		<c:if test="${not empty vehicleRequestList}">


			<form method="post" action="aAction?choice=2">

				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Vehicle Request Id</th>
							<th>Vehicle Id</th>
							<th>Branch Id</th>
							<th>Vehicle Request Date</th>
							<th>Number Of Vehicle</th>
							<th>Select</th>
						</tr>
					</thead>
					<c:forEach items="${vehicleRequestList}" var="customer">
						<tbody>
							<tr>
								<td><c:out value="${customer.vehicleRequestId}" />
								</td>
								<td><c:out value="${customer.vehicleId}" />
								</td>
								<td><c:out value="${customer.branchID}" />
								</td>
								<td><c:out value="${customer.requestDate}" />
								</td>
								<td><c:out value="${customer.numberOfVehicle}" />
								</td>

								<td><input type="radio" name="vehicle"
									value=<c:out value="${customer.vehicleRequestId}" /> required><br>
								</td>

							</tr>
						</tbody>
					</c:forEach>
				</table>
				<h4>Enter The No Of Vehicles you want to approve for the
					selected vehicle request</h4>
				<input type="text" name="numberOfVehicle" id="inputField"
					value="${param.numberOfVehicle}" required
					oninvalid="this.setCustomValidity('Enter the number of vehicles you want to approved')"
					onchange="this.setCustomValidity('')"
					onkeyup="validateInputField()" maxlength="6"> <input type="submit"
					name="for approval" class="btn btn-success btn-sm" id="Approve" value="approval">
				<input type="submit" name="for rejection" id="Reject"
					class="btn btn-danger btn-sm" value="rejection">
				<label id="NoOfVehiclefield" style="color: red !important;"></label>
			</form>
		</c:if>
		<c:if test="${requestScope.vehicleRequestList.size() eq 0}">
			<label>
				<font color="red">No More Result Found</font>
			</label>
		</c:if>
	</div>
</body>
</html>