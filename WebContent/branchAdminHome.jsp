<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.vrs.dto.BookingDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Branch Admin Home</title>
<link rel="stylesheet" href="css/s.css" />
<link rel="icon" href="image/ico.jpg" />
<link href="bootstrap-3.3.4-dist/css/bootstrap.css" rel='stylesheet'
	type='text/css' />
<link href="bootstrap-3.3.4-dist/css/bootstrap.min.css" rel='stylesheet'
	type='text/css' />
<link href="css/datepicker.css" rel='stylesheet' type='text/css' />
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/datepicker.js"></script>
<script src="bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="js/validate.js"></script>
</head>
<body>
<c:out value="${sessionScope.ROLE}"></c:out>


<div id="branchAdmin" style="display: inline">
	<script type="text/javascript" src="./js/tabpanel.js"></script>
	<jsp:include page="header.jsp" flush="true"></jsp:include>
	<div class="jumbotron">
		<img src="image/back1.jpg" class="img-responsive"
			alt="Responsive image">
	</div>
	<div class="container">
		<ul class="nav nav-tabs nav-justified">
			<li class="active"><a href="#menu1">Branch Admin Details</a></li>
			<li><a href="#menu2">Request for Vehicle</a></li>
			<li><a href="#menu3">Approve/Pending Booking</a></li>
		</ul>
		<div class="tab-content">
			<div id="menu1" class="tab-pane fade in active">

				<!-- ***** menu 1 Starts Here ***** -->

				<h3>Profile</h3>
				<br /> <br />
				<form name="branch_Admin_home_form" class="form-horizontal"
					action="baAction?choice=1" method="post">

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Login
							Id</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="inputusername"
								name="userName" value="${sessionScope.userID}"
								placeholder="Login Id" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Branch
							Location</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="location"
								placeholder="Branch Location" name="BranchLocation"
								onblur="validatebranchLocation()"
								value="<c:out value="${requestScope.branchAdminDTO.branchLocation}"></c:out>"
								onkeyup="validatebranchLocation()" required disabled> <label
								id="inputBranchLocationValid" style="color: red !important;"></label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Address</label>
						<div class="col-sm-5">
							<textarea class="form-control" placeholder="Address" rows="3"
								name="Address" id="address" required disabled><c:out value="${requestScope.branchAdminDTO.address}" /></textarea>
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">e-mail</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="email"
								placeholder="e-mail" name="Mailid" onblur="validateEmail()"
								onkeyup="validateEmail()"
								value="<c:out value="${requestScope.branchAdminDTO.mailid}"></c:out>"
								required disabled> <label id="emailError"
								style="color: red !important;"></label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Mobile</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="inputPhone"
								placeholder="Mobile" name="PhoneNo" onblur="validatePhoneNo()"
								maxlength="11"
								value="<c:out value="${requestScope.branchAdminDTO.phoneNo}"></c:out>"
								onkeyup="validatePhoneNo()" required disabled> <label
								id="inputPhoneValid" style="color: red !important;"></label>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" id="Edit" class="btn btn-info"
								onclick="editable()">Edit</button>
							<button type="submit" id="Submit" style="display: none"
								class="btn btn-success" disabled>Save</button>
							<button type="reset" id="reset" style="display: none"
								class="btn btn-danger">Reset</button>
						</div>
					</div>
				</form>
			</div>
			<div id="menu2" class="tab-pane fade">

				<!-- ***** menu 2 Starts Here ***** -->


				<br /> <br />
				<form class="form-horizontal" action="baAction?choice=2"
					method="post">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Branch
							Id</label>
						<div class="col-sm-5">
							<input type="text" name="branchID" class="form-control"
								id="inputEmail3" placeholder="Branch Id" required
								value="<c:out value="${requestScope.branchAdminDTO.branchID}"></c:out>"
								oninvalid="this.setCustomValidity('Branch ID is a must')"
								onchange="this.setCustomValidity('')" readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Vehicle
							Id</label>
						<div class="col-sm-5">
							<input type="text" name="vehicleID" class="form-control"
								id="inputEmail3" placeholder="Vehicle Id" required
								oninvalid="this.setCustomValidity('Vehicle ID is a must')"
								onchange="this.setCustomValidity('')" maxlength="5">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">No.
							of Vehicle(s)</label>
						<div class="col-sm-5">
							<input type="text" name="noOfVehicle" class="form-control"
								id="vehicleNo" placeholder="No. of Vehicle(s)" required
								oninvalid="this.setCustomValidity('Atleast 1 is required')"
								onchange="this.setCustomValidity('')" maxlength="5"
								onblur="validateVehicleNo()"> <label id="vehicleNoError"
								style="color: red !important;"></label>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" id="Request" class="btn btn-success">Request</button>
						</div>
					</div>

				</form>
			</div>
			<div id="menu3" class="tab-pane fade">
				<br>
				<h4>Enter the Vehicle ID for which you want to see Pending
					Bookings</h4>
				<br>
				<form class="form-horizontal" id="Form"
					action="baAction?choice=3&page=0" method="post">
					<div class="col-sm-4 input-group">
						<input type="text" class="form-control" placeholder="Vehicle Id"
							id="vehicleid" name="vehicleID" maxlength="6"> <span
							class="input-group-btn">
							<button class="btn btn-default" id="show" type="submit"
								onclick="demoDisplay()">Search</button>
						</span>
					</div>
				</form>
				<br />
				<div class="col-sm-13" id="booking" style="display: none">
				<input type="button" id="previousVehicle"
					class="btn btn-sm btn-info" value="Previous"> <input
					type="button" id="nextVehicle" class="btn btn-sm btn-info"
					value="Next">
				<div id="showbooking" style="display: none"></div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<div class="panel-footer ">&copy; Vehicle Reservation System</div>

	<script>
		$(document)
				.ready(
						function() {
							$('#show')
									.click(
											function(event) {
												var username = $('#vehicleid')
														.val();
												$
														.get(
																'Bookings',
																{
																	user : username
																},
																function(
																		responseText,
																		status) {
																	// $('#welcometext').text(responseText);  
																	// document.getElementById("welcometext").innerHTML=responseText;

																	//
																	//window.alert(responseText);
																	document
																			.getElementById("showbooking").style.display = "inline";
																	document
																			.getElementById("showbooking").innerHTML = responseText;
																});
											});
						});

		var form = $('#Form');
		form
				.submit(function() {

					$
							.ajax({
								type : form.attr('method'),
								url : form.attr('action'),
								data : form.serialize(),
								success : function(data) {
									var result = data;
									$('#showbooking').html(result);
									document.getElementById("showbooking").style.display = "table";
									document.getElementById("booking").style.display = "table";
									if (data
											.search("Sorry..! No result Found") != -1) {
										/* alert("aaaa"); */
										document
												.getElementById("previousVehicle").disabled = true;
										document
												.getElementById("nextVehicle").disabled = true;
									} else {
										/* alert("hghbf"); */
										document
												.getElementById("nextVehicle").disabled = false;
										document
												.getElementById("previousVehicle").disabled = false;
									}
								}
							});

					return false;
				});

		$(document)
				.ready(
						function() {
							$("#previousVehicle")
									.click(
											function() {
												$
														.get(
																"baAction?choice=3&page=-5",
																function(data,
																		status) {
																	document
																			.getElementById("showbooking").style.display = "inline";
																	document
																			.getElementById("showbooking").innerHTML = data;
																	if (data
																			.search("Sorry..! No result Found") != -1) {
																		/* alert("aaaa"); */
																		document
																				.getElementById("previousVehicle").disabled = true;
																		document
																				.getElementById("nextVehicle").disabled = false;
																	} else {
																		/* alert("hghbf"); */
																		document
																				.getElementById("nextVehicle").disabled = false;
																		document
																				.getElementById("previousVehicle").disabled = false;
																	}
																});
											});
						});

		$(document)
				.ready(
						function() {
							$("#nextVehicle")
									.click(
											function() {
												$
														.get(
																"baAction?choice=3&page=5",
																function(data,
																		status) {
																	document
																			.getElementById("showbooking").style.display = "inline";
																	document
																			.getElementById("showbooking").innerHTML = data;
																	if (data
																			.search("Sorry..! No result Found") != -1) {
																		/* alert("aaaa"); */
																		document
																				.getElementById("previousVehicle").disabled = false;
																		document
																				.getElementById("nextVehicle").disabled = true;
																	} else {
																		/* alert("hghbf"); */
																		document
																				.getElementById("nextVehicle").disabled = false;
																		document
																				.getElementById("previousVehicle").disabled = false;
																	}

																});
											});
						});
	</script>

	<script src="js/validate.js" type="text/javascript"></script>
	</div>
	<c:if test="${sessionScope.ROLE ne 'branchAdmin'}">
	<script>

document.getElementById("branchAdmin").style.display="none";
window.location.href="404error.jsp";
</script>
	
	</c:if>
</body>
</html>