<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type;" content="text/html; charset=ISO-8859-1">
<title>Login</title>
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
<body style="background-image: url('image/loginWall.jpg');">

	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"><font style="color: black">Vehicle
					Reservation System</font> </a>
		</div>
	</div>
	</nav>
	<div class="container">

		<!-- <form class="form-signin" method="post" action="Login.do"> -->
		<form class="form-signin" name="" method="post" action="uAction.do?choice=1">
		
			<h2 class="form-signin-heading">Log In</h2>
			<label for="inputEmail" class="sr-only">User Id</label> <input
				type="text" id="inputEmail" name="userName" class="form-control"
				placeholder="User Id" onkeyup="validateInputEmail1()" onfocusout="validateInputEmail1()" required maxlength="6">
				<div id="inputEmailValid" style="color:red!important;"></div>
				<label
				for="inputPassword" class="sr-only">Password</label> <input
				type="password" id="inputPassword" name="password"
				class="form-control" placeholder="Password" onfocusout="validatePassword()" maxlength="20" minlength="8" required>
			<div id="inputPasswordValid" style="color: red !important;"></div>
				<p><font color="Red"><c:if test="${requestScope.message != null}"><c:out value="${requestScope.message}"></c:out></c:if></font></p>
			<button class="btn btn-lg btn-primary btn-block" id="login" type="submit">Log
				In</button>
			<p>
				Not a User? <a href="registration.jsp"> Sign Up for Free...!</a>
			</p>
		</form>
	</div>
	<div class="panel-footer">&copy; Vehicle Reservation System</div>
</body>
</html>