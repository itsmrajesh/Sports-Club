<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Sofia'
	rel='stylesheet'>
</head>
<style>
.fortext {
	font-family: 'Sofia';
	font-size: 40px;
	color: green;
}

body {
	background-color: #cccccc;
}

.bgimg {
	background-image: url("images/timeout.png");
	background-position: center;
	background-color: #cccccc;
	background-repeat: no-repeat;
}
</style>
<body>

	<div align="center" class="fortext">
		<h1>
			Hey user its Time out <br> Please Login again to continue..
		</h1>

		<h3>
			Don't worry this page automatically redirect you to login page :-) or
			else <a href="login.html">Click here</a>
		</h3>
	</div>
	<div class="bgimg">

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br>

	</div>
	<%
		response.setHeader("Refresh", "5;url=login.html");
	%>
</body>
</html>