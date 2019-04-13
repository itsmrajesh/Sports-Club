<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Sports Club</title>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body {
	background-color: #C8D7DB;
}

.mrc {
	color: green;
	size: 5px;
	font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
	border: 0px;
	padding-top: 25px;
	padding-left: 35%;
}

.grid {
	padding-top: 20%;
}

.mrc1 {
	color: green;
	size: 5px;
	font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
	border: 0px;
	padding-top: 25px;
	padding-left: 25%;
}
</style>
</head>

<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Sports Club System</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="dashboard">Home</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout"><span
						class="glyphicon glyphicon-sign-up"></span> Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="mrc1">
		<br> <br>
		<h1>
			<%
				out.println("Update Status :- " + request.getAttribute("status"));
			%>
		</h1>
	</div>

</body>

</html>