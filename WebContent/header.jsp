<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" 
	content="text/html; charset=ISO-8859-1; max-age=0, must-revalidate, no-cache, no-store, private">
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

	<c:if test="${empty sessionScope.userID}">
		<jsp:forward page="login.jsp"></jsp:forward>
	</c:if>
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"><font style="color: black">Vehicle
					Reservation System</font> </a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"> <c:if
							test="${not empty sessionScope.userID}">
							<c:out value="${sessionScope.userID}"></c:out>
						</c:if> </a>
				</li>
				<!-- <li><a href="Redirect">Logout</a> -->
				<li><a href="uAction.do?choice=3">Logout</a>
				</li>
			</ul>
		</div>
	</div>
	</nav>

<script type="text/javascript">
     var pollInt = 5000;
     window.setInterval( check(), pollInt);
    	function check() {
			
    		 $.ajax({
             url: 'uAction.do?choice=5',
             type: 'post',
             success: function(response) {
				if(response){
					window.location.href=response;
				}
			}
         });
    		
     }		
</script>
</body>
</html>