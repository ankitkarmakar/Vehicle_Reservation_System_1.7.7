<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>
<link rel="stylesheet" href="css/s.css" />
<link rel="icon" href="image/ico.jpg" />

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
<div id="admin1" style="display: inline">
	<jsp:include page="header.jsp"></jsp:include>
	<div class="jumbotron">
		<img src="image/back1.jpg" class="img-responsive"
			alt="Responsive image">
	</div>
	<div class="container">
		<ul class="nav nav-tabs nav-justified">
			<li class="active"><a href="#menu1">Vehicle Request Approval</a>
			</li>
			<li><a href="#menu2">Add New Vehicle</a>
			</li>
			<li><a href="#menu3">Account Request Approval</a>
			</li>
		</ul>
		<div class="tab-content">
			<div id="menu1" class="tab-pane fade in active">
				<h3>Vehicle Request Approval</h3>
				<button class="btn btn-sm btn-info" id="amenu3">Show
					vehicle requests</button>
				<br> <br>
				<div id="navigation" style="display: none;">
					<input type="button" class="btn btn-sm btn-info" id="previous"
						value="Previous"> <input type="button"
						class="btn btn-sm btn-info" id="next" value="Next" /> <br> <br>

					<div id="showReq"></div>
				</div>
				
			<div id="showReq"></div>

			</div>




			<!-- menu 2 content starts Here -->

			<div id="menu2" class="tab-pane fade">

				<br /> <br />
				<form class="form-horizontal" action="aAction?choice=3"
					method="post" name="frm">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Vehicle
							ID</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="inputEmail3"
								name="Vehicle ID" id="Vehicle ID" placeholder="Vehicle ID"
								required
								oninvalid="this.setCustomValidity('vehicle Id is a must')"
								onchange="this.setCustomValidity('')" maxlength="8">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Manufacture
							Name</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="inputEmail3"
								id="Manufac" name="Manufac" placeholder="Manufacture Name"
								required
								oninvalid="this.setCustomValidity('Manufacture Name is a must')"
								onchange="this.setCustomValidity('')" maxlength="20">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Ex.
							showroom Price</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="Price"
								name="ExShowRoomPrice" placeholder="Ex. showroom Price" required
								oninvalid="this.setCustomValidity('ExShowRoomPrice is a must')"
								onchange="this.setCustomValidity('')" onkeyup="validatePrice()"
								onfocusout="validatePrice()" maxlength="9"> <label
								id="PriceError" style="color: red !important;"></label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Rent</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="rent" name="rent"
								placeholder="Rent" required
								oninvalid="this.setCustomValidity('rent is a must')"
								onchange="this.setCustomValidity('')" onkeyup="validateRent()"
								onfocusout="validateRent()" maxlength="8"> <label
								id="RentError" style="color: red !important;"></label>
						</div>
					</div>
					<div class="form-group">
						<label for="sel1" class="col-sm-2 control-label">Vehicle
							Type</label>
						<div class="col-sm-5">
							<select class="form-control" id="sel1" name="Vehicle Type">
								<option>Hatchback</option>
								<option>Sedans</option>
								<option>SUV/MUV</option>
								<option>Coupe</option>
								<option>Convertible</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="sel1" class="col-sm-2 control-label">Color</label>
						<div class="col-sm-5">
							<select class="form-control" id="sel1" name="color">
								<option>Red</option>
								<option>Green</option>
								<option>Blue</option>
								<option>White</option>
								<option>Black</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="sel1" class="col-sm-2 control-label">Seat
							Capacity</label>
						<div class="col-sm-5">
							<select class="form-control" id="sel1" name="seat capacity">
								<option>2</option>
								<option>4</option>
								<option>5</option>
								<option>7</option>
								<option>9</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">No.
							of Vehicle(s)</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="vehicleNo"
								name="No. of Vehicle(s)" placeholder="No. of Vehicle(s)"
								required
								oninvalid="this.setCustomValidity('No. of Vehicle(s) is a must')"
								onchange="this.setCustomValidity('')"
								onkeyup="validateVehicleno()" onfocusout="validateVehicleno()"
								maxlength="5"> <label id="VehiclenoError"
								style="color: red !important;"></label>
						</div>
					</div>


					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Vehicle
							Name</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="inputEmail3"
								name="Vehicle Name" placeholder="Vehicle Name" required
								oninvalid="this.setCustomValidity('Vehicle Name is a must')"
								onchange="this.setCustomValidity('')" maxlength="10">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-success" id="addVehicle"
								onclick="validate();">Add Vehicle</button>
						</div>
					</div>
				</form>
			</div>
			<!-- menu 2 ends Here -->

			<!-- menu 3 Starts Here -->
			<div id="menu3" class="tab-pane fade">
				<br>
				<div class="col-md-3">
					<ul class="nav nav-pills nav-stacked">
						<br>
						<br>
						<li class="active"><a href="#tab_a" data-toggle="pill"
							onclick="hide()">All Pending Account Request</a>
						</li>
						<li><a href="#tab_b" data-toggle="pill" onclick="hide()">View
								All Account Request</a>
						</li>
						<li><a href="#tab_c" data-toggle="pill" onclick="hide()">Role
								by Account Request</a>
						</li>
						<li><a href="#tab_d" data-toggle="pill" onclick="hide()">Status
								by Account Request</a>
						</li>
					</ul>
				</div>
				<div class="tab-content col-md-9">
					<div class="tab-pane active" id="tab_a">
						<h4>Click Below To View All Pending Account Request</h4>
						<form method="post" action="aAction?choice=4"
							id="viewUserRegistrationList">
							<input type="submit" name="viewPendingAccountRequest"
								class="btn btn-sm btn-info" value="View" onclick="vurl()"><br />
						</form>
					</div>
					<div class="tab-pane" id="tab_b">
						<h4>Click Below To View All Account Request Made So Far</h4>
						<form method="post" action="aAction?choice=5"
							id="viewUserRegistrationList2">
							<input type="submit" name="viewAllAccountRequest"
								class="btn btn-sm btn-info" value="View" onclick="vurl2()"><br />
						</form>
					</div>
					<div class="tab-pane" id="tab_c">
						<h5>View All Pending Account Request By Selecting The Role Of
							The User</h5>
						<form method="post" action="aAction?choice=7"
							id="viewUserRegistrationList3">
							<div class="col-md-3">
								<select name="role" class="form-control ">
									<option value="branchAdmin">Branch Admin</option>
									<option value="customer">Customer</option>
								</select>
							</div>
							<input type="submit" name="viewAccountRequestByRole"
								class="btn btn-sm btn-info" value="Select" onclick="vurl3()"><br />
						</form>
					</div>
					<div class="tab-pane" id="tab_d">
						<h5>View All Pending Account Request By Selecting The Status
							Of The Registration</h5>

						<form method="post" action="aAction?choice=8"
							id="viewUserRegistrationList4">
							<div class="col-md-3">
								<select name="status" class="form-control">
									<option value="pending">Pending</option>
									<option value="approved">Approved</option>
									<option value="rejected">Rejected</option>
								</select>
							</div>
							<input type="submit" name="viewAccountRequestByRole"
								value="Select" class="btn btn-sm btn-info" onclick="vurl4()"><br />
						</form>
					</div>
					<br>
					<div id="showRegistrationList" style="display: inline"></div>
				</div>
				<!-- End of tab content -->
			</div>

		</div>
	</div>

	<br>
	<br>
	<div class="panel-footer ">&copy; Vehicle Reservation System</div>
	<%-- <c:if test="${sessionScope.vehicles.size() le 1}">
	<script type="text/javascript">
	window.alert("hi");
	document.getElementById("next").disabled=true;
	</script>
	</c:if> --%>

	<c:if test="${sessionScope.vehicles.size() eq 0}">
		<script>
			document.getElementById("navigation").style.display = "none";
		</script>

	</c:if>
	<script type="text/javascript">
		function hide() {
			document.getElementById("showRegistrationList").style.display = 'none';
		}
		$(document).ready(function() {
			$(".nav-tabs a").click(function() {
				$(this).tab('show');
			});
			$('.nav-tabs a').on('shown.bs.tab', function(event) {
				var x = $(event.target).text(); // active tab
				var y = $(event.relatedTarget).text(); // previous tab
				$(".act span").text(x);
				$(".prev span").text(y);
			});
		});

		function vurl() {
			var form = $("#viewUserRegistrationList");
			form
					.submit(function() {

						$
								.ajax({
									type : form.attr('method'),
									url : form.attr('action'),
									data : form.serialize(),
									success : function(data) {
										var result = data;
										$("#showRegistrationList").html(result);
										document.getElementById("showRegistrationList").style.display = "inline";
										/* $("#showbooking").load("section.jsp #bookingDetails"); */
									}
								});

						return false;
					});
		};

		function vurl2() {
			var form = $("#viewUserRegistrationList2");
			form
					.submit(function() {

						$
								.ajax({
									type : form.attr('method'),
									url : form.attr('action'),
									data : form.serialize(),
									success : function(data) {
										var result = data;
										$("#showRegistrationList").html(result);
										document.getElementById("showRegistrationList").style.display = "inline";
										/* $("#showbooking").load("section.jsp #bookingDetails"); */
									}
								});

						return false;
					});
		};

		function vurl3() {
			var form = $("#viewUserRegistrationList3");
			form
					.submit(function() {

						$
								.ajax({
									type : form.attr('method'),
									url : form.attr('action'),
									data : form.serialize(),
									success : function(data) {
										var result = data;
										$("#showRegistrationList").html(result);
										document.getElementById("showRegistrationList").style.display = "inline";
										/* $("#showbooking").load("section.jsp #bookingDetails"); */
									}
								});

						return false;
					});
		};

		function vurl4() {
			var form = $("#viewUserRegistrationList4");
			form
					.submit(function() {

						$
								.ajax({
									type : form.attr('method'),
									url : form.attr('action'),
									data : form.serialize(),
									success : function(data) {
										var result = data;
										$("#showRegistrationList").html(result);
										document.getElementById("showRegistrationList").style.display = "inline";
										/* $("#showbooking").load("section.jsp #bookingDetails"); */
									}
								});

						return false;
					});
		};

		$(document)
				.ready(
						function() {
							$("#amenu3")
									.click(
											function() {
												$
														.get(
																"aAction?choice=1&page=0",
																function(data,
																		status) {
																	document
																			.getElementById("showReq").style.display = "inline";
																	document
																			.getElementById("navigation").style.display = "inline";
																	document
																			.getElementById("showReq").innerHTML = data;
																	if (data
																			.search("No More Result Found") != -1) {
																		/* alert("aaaa"); */
																		document
																				.getElementById("next").style.display = "none";
																		document
																				.getElementById("previous").style.display = "none";

																	} else {
																		/* alert("hghbf"); */
																		document
																				.getElementById("next").disabled = false;
																		document
																				.getElementById("previous").disabled = false;
																	}
																});
											});
						});
		$(document)
				.ready(
						function() {
							$("#next")
									.click(
											function() {
												$
														.get(
																"aAction?choice=1&page=2",
																function(data,
																		status) {
																	document
																			.getElementById("showReq").style.display = "inline";
																	document
																			.getElementById("showReq").innerHTML = data;
																	if (data
																			.search("No More Result Found") != -1) {
																		/* alert("aaaa"); */
																		document
																				.getElementById("previous").disabled = false;
																		document
																				.getElementById("next").disabled = true;
																	} else {
																		/* alert("hghbf"); */
																		document
																				.getElementById("next").disabled = false;
																		document
																				.getElementById("previous").disabled = false;
																	}
																});
											});
						});
		$(document)
				.ready(
						function() {
							$("#previous")
									.click(
											function() {
												$
														.get(
																"aAction?choice=1&page=-2",
																function(data,
																		status) {
																	document
																			.getElementById("showReq").style.display = "inline";
																	document
																			.getElementById("showReq").innerHTML = data;
																	if (data
																			.search("No More Result Found") != -1) {
																		/* alert("aaaa"); */
																		document
																				.getElementById("previous").disabled = true;
																		document
																				.getElementById("next").disabled = false;
																	} else {
																		/* alert("hghbf"); */
																		document
																				.getElementById("next").disabled = false;
																		document
																				.getElementById("previous").disabled = false;
																	}
																});
											});
						});
		
	</script>
	</div>
	<c:if test="${sessionScope.ROLE ne 'admin'}">
		<script>
			document.getElementById("admin1").style.display = "none";
			window.location.href = "404error.jsp";
		</script>

	</c:if>
</body>
</html>