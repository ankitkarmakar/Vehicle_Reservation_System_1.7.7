<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>VRS</title>
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

</head>
<body>
	<script type="text/javascript" src="./js/tabpanel.js"></script>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="jumbotron">
		<img src="image/back1.jpg" class="img-responsive"
			alt="Responsive image">
	</div>
	<div class="container ">
		<div class="row">
			<div class="col-md-5 col-centered">
				<label class="col-lg-10 control-label"> <strong> </strong> </label>
				<div class="page-header">
					<h1><small><c:out value="${requestScope.successMessage}"></c:out>
					</small></h1>
					<a onclick="window.history.back();"
							class="btn btn-primary btn-sm"><span
							class="glyphicon glyphicon-circle-arrow-left"></span> Go Back </a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>