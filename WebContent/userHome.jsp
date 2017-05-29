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
<title>User Home</title>
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
<script type="text/javascript" src="js/validate.js"></script>
</head>

<body>
	<div id="customer" style="display: inline">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="jumbotron">
			<img src="image/back1.jpg" class="img-responsive"
				alt="Responsive image">
		</div>
		<div class="container">
			<ul class="nav nav-tabs nav-justified">
				<li class="active"><a href="#menu1">My Details</a>
				</li>
				<li><a href="#menu2">Search Vehicle</a>
				</li>
				<li><a id="amenu3" href="#menu3">Booking Status</a>
				</li>
			</ul>
			<div class="tab-content">

				<!-- ***** menu 1 Starts Here ***** -->
				<div id="menu1" class="tab-pane fade in active">

					<h3>Profile</h3>
					<br />
					<form name="userHome Form" class="form-horizontal"
						action="cAction?choice=1" method="post">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">User
								ID</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="inputusername"
									placeholder="User Name" name="userid"
									value="${sessionScope.userID}" disabled>
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Customer
								Name</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="customerName"
									placeholder="Customer Name" name="Customer Name"
									onblur="validateCustomerName()"
									value="<c:out value="${requestScope.customerDTO.customerName}"/>"
									onkeyup="validateCustomerName()" maxlength="15"
									onfocusout="validateCustomerName()" required disabled>
								<label id="NameError" style="color: red !important;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Date
								of Birth</label>
							<div class="col-sm-5">
								<div class='input-group date' id='datetimepicker'
									date-format="dd/mm/yyyy">
									<input type="text" class="form-control" id="Dob"
										placeholder="(DD/MM/YYYY)" name="dob" required="required" /> <span
										class="input-group-addon"> <i
										class="glyphicon glyphicon-calendar"></i> </span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Address</label>
							<div class="col-sm-5">
								<textarea class="form-control" placeholder="Address" rows="3"
									name="Address" id="address" maxlength="150" required disabled>
									<c:out value="${requestScope.customerDTO.address}" />
								</textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">e-mail</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="email"
									placeholder="xxx@xxxxx.com" name="Mailid" maxlength="30"
									value="<c:out value="${requestScope.customerDTO.email}"></c:out>"
									onblur="validateUserEmail()" required disabled> <label
									id="EmailError" style="color: red !important;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Mobile</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="Mobile"
									placeholder="xxx-xxxxxxx" name="PhoneNo"
									value="<c:out value="${requestScope.customerDTO.phoneNo}"></c:out>"
									onkeyup="validateUserPhoneNo()" onblur="validateUserPhoneNo()"
									required disabled maxlength="11"> <label
									id="MobileError" style="color: red !important;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Occupation</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="Occupation"
									maxlength="25"
									value="<c:out value="${requestScope.customerDTO.occupation}"></c:out>"
									placeholder="Occupation" name="occupation" disabled required>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="button" id="Edit" class="btn btn-info"
									onclick="edit()">Edit</button>
								<button type="submit" id="Save" onclick="submit()"
									class="btn btn-success" style="display: none;">Save</button>
								<input type="Reset" id="reset" class="btn btn-danger"
									style="display: none;" />
							</div>
						</div>
					</form>
				</div>

				<!-- ***** menu 2 Starts Here ***** -->

				<div id="menu2" class="tab-pane fade">

					<br /> <br />
					<form class="form-horizontal"
						action="cAction?choice=2&vehiclePage=0" id="searchvehicle"
						method="post">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Manufacture
								Name</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="inputEmail3"
									placeholder="Manufacture Name" name="manufacturerName">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Enter
								Location</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="Location"
									placeholder="Location" name="location">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Min
								Price</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="MinPrice"
									placeholder="Min Price" name="minPrice"
									onkeyup="validateMinPrice()" onblur="validateMinPrice()">
								<label id="MinPriceError" style="color: red !important;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Max
								Price</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="MaxPrice"
									placeholder="Max Price" name="maxPrice"
									onkeyup="validateMaxPrice()" onblur="validateMaxPrice()">
								<label id="MaxPriceError" style="color: red !important;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="sel1" class="col-sm-2 control-label">Seat
								Capacity</label>
							<div class="col-sm-5">
								<select class="form-control" id="sel1" name="seat">
									<option>Choose Number of seats</option>
									<option value="2">2</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="sel1" class="col-sm-2 control-label">Color</label>
							<div class="col-sm-5">
								<select class="form-control" id="sel1" name="color">
									<option>Choose a Color</option>
									<option>Red</option>
									<option>Green</option>
									<option>Blue</option>
									<option>White</option>
									<option>black</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-success" id="showVehicle">Search
									Vehicle</button>
							</div>
						</div>
					</form>

					<div class="col-sm-10" id="getVehicles" style="display: none;">
						<input type="button" id="previousVehicle"
							class="btn btn-sm btn-info" value="Previous"> <input
							type="button" id="nextVehicle" class="btn btn-sm btn-info"
							value="next"> <br>
						<h3>
							<font style="color: green;">Search Result</font>
						</h3>
					</div>

					<div class="col-sm-10" id="showresult" style="display: none">
						<div id="vehicleList"></div>

					</div>



				</div>


				<!-- Menu 3 Starts Here -->

				<div id="menu3" class="tab-pane fade">

					<!-- <button id="b" type="button">show my bookings</button> -->
					<div id="results" style="display: ;"></div>

					<input type="button" class="btn btn-sm btn-info" id="previous"
						value="Previous"> <input type="button"
						class="btn btn-sm btn-info" id="next" value="Next">
				</div>

				<!-- Menu 3 Ends Here -->
			</div>

		</div>
		<br> <br>
		<div class="panel-footer ">&copy; Vehicle Reservation System</div>
		<script type="text/javascript">
			/*  $(document).ready(function() {
			$("#showVehicle").click(
				function() {
					document.getElementById("showbooking").style.display = "inline";
					// $("#vehicleList").load("loop.jsp #vehicleBookDiv");

					});
			}); */

			var form = $('#searchvehicle');
			form
					.submit(function() {

						$
								.ajax({
									type : form.attr('method'),
									url : form.attr('action'),
									data : form.serialize(),
									success : function(data) {
										var result = data;
										$('#showresult').html(result);
										document.getElementById("showresult").style.display = "table";
										document.getElementById("getVehicles").style.display = "table";
										/* $("#showbooking").load("section.jsp #bookingDetails"); */
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
																	"cAction?choice=2&vehiclePage=-1",
																	function(
																			data,
																			status) {
																		document
																				.getElementById("showresult").style.display = "inline";
																		document
																				.getElementById("showresult").innerHTML = data;
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
																	"cAction?choice=2&vehiclePage=1",
																	function(
																			data,
																			status) {
																		document
																				.getElementById("showresult").style.display = "inline";
																		document
																				.getElementById("showresult").innerHTML = data;
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
			$(document)
					.ready(
							function() {
								$("#amenu3")
										.click(
												function() {
													$
															.get(
																	"cAction?choice=3&page=0",
																	function(
																			data,
																			status) {
																		document
																				.getElementById("results").style.display = "inline";
																		document
																				.getElementById("results").innerHTML = data;
																		if (data
																				.search("No Booking found") != -1) {
																			/* alert("aaaa"); */
																			document
																					.getElementById("previous").disabled = true;
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
								$("#next")
										.click(
												function() {
													$
															.get(
																	"cAction?choice=3&page=5",
																	function(
																			data,
																			status) {
																		document
																				.getElementById("results").style.display = "inline";
																		document
																				.getElementById("results").innerHTML = data;
																		if (data
																				.search("No Booking found") != -1) {
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
																	"cAction?choice=3&page=-5",
																	function(
																			data,
																			status) {
																		document
																				.getElementById("results").style.display = "inline";
																		document
																				.getElementById("results").innerHTML = data;
																		if (data
																				.search("No Booking found") != -1) {
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
		<script src="js/tabpanel.js" type="text/javascript"></script>
		<!-- <script src="js/javascript.js" type="text/javascript"></script> -->
		<script src="js/validate.js" type="text/javascript"></script>
	</div>
	<c:if test="${sessionScope.ROLE ne 'customer'}">
		<script>
			document.getElementById("customer").style.display = "none";
			window.location.href = "404error.jsp";
		</script>

	</c:if>
</body>
</html>