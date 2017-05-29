<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
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
<div id="branchAdmin" style="display: inline">
	 <jsp:include page="header.jsp"></jsp:include> 
	<div class="jumbotron">
		<img src="image/back1.jpg" class="img-responsive"
			alt="Responsive image">
	</div>
	<div class="container">



		<h3>Profile</h3>
		<br /> <br />
		<form name="branch_Admin_home_form" class="form-horizontal"
			action="baAction?choice=1" method="post">

			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">Login
					Id</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="inputusername"
						name="userName" placeholder="Login Id" value="${sessionScope.userID}"
						onkeyup="validateLoginId()" disabled>
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">Branch
					Location</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="location"
						placeholder="Branch Location" name="BranchLocation"
						onblur="validatebranchLocation()" required>
						<label
								id="inputBranchLocationValid" style="color: red !important;"></label>
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">Address</label>
				<div class="col-sm-5">
					<textarea class="form-control" placeholder="Address" rows="3"
								name="Address" id="address" required></textarea>
				</div>
			</div>

			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">e-mail</label>
				<div class="col-sm-5">
					<input type="email" class="form-control" id="email"
						placeholder="e-mail" name="Mailid" onfocusout="validateEmail()" required>
						<label id="emailError"
								style="color: red !important;"></label>
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">Mobile</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="inputPhone"
						placeholder="Mobile" name="PhoneNo" onkeyup="validatePhoneNo()" required>
						<label
								id="inputPhoneValid" style="color: red !important;"></label>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" id="Submit" class="btn btn-success">Update</button>
					<button type="reset" class="btn btn-danger">Reset</button>
				</div>
			</div>
		</form>
	</div>
	</div>
	<c:if test="${sessionScope.ROLE ne 'branchAdmin'}">
	<script>

document.getElementById("branchAdmin").style.display="none";
window.location.href="404error.jsp";
</script>
	
	</c:if>
</body>
</html>