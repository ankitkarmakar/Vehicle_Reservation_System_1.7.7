<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
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
<div id="customer" style="display: inline">
	<jsp:include page="header.jsp"></jsp:include>
	<div class="jumbotron">
		<img src="image/back1.jpg" class="img-responsive"
			alt="Responsive image">
	</div>
	<div class="container">


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
								onblur="validateCustomerNameR()"
								onkeyup="validateCustomerNameR()" maxlength="15"
								required> <label
								id="NameError" style="color: red !important;"></label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Date
							of Birth</label>
						<div class="col-sm-5">
							<div class='input-group date' id='datetimepicker'
								date-format="dd/mm/yyyy">
								<input type='text' class="form-control" id="Dob"
									placeholder="(DD/MM/YYYY)" name="dob" 
									readonly required/> <span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar" ></i>
								</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Address</label>
						<div class="col-sm-5">
							<textarea class="form-control" placeholder="Address" rows="3"
								name="Address" id="address" maxlength="150"
								 required></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">e-mail</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="email"
								placeholder="xxx@xxxxx.com" name="Mailid" maxlength="30"
								onblur="validateUserEmail()" required> <label
								id="EmailError" style="color: red !important;"></label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Mobile</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="Mobile"
								placeholder="xxx-xxxxxxx" name="PhoneNo"
								onkeyup="validateUserPhoneNo()" onblur="validateUserPhoneNo()"
								required maxlength="11"> <label
								id="MobileError" style="color: red !important;"></label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Occupation</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="Occupation" maxlength="40"
								placeholder="Occupation" name="occupation" onkeyup="validateOccupation()" onblur="validateOccupation()" required>
								<label id="inputOccupationValid" style="color:red!important;"></label>
						</div>
					</div>


				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" id="Save" class="btn btn-success" value="Save">
						<input type="reset" class="btn btn-danger" value="Reset">
					</div>
				</div>
			</form>
		</div>
	</div>
	</div>
	<c:if test="${sessionScope.ROLE ne 'customer'}">
		<script>
			document.getElementById("customer").style.display = "none";
			window.location.href = "404error.jsp";
		</script>

	</c:if>
</body>
</html>