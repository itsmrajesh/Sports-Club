<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Booking</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
<style>
h1 {
	text-align: center;
	color: green;
}
body {
	background-image: url("images/allsports2.png");
	background-color: #cccccc;
	background-repeat: no-repeat;
}
</style>

</head>

<body>
<%
		response.setHeader("Cache-Control", "no-cache , no-store , must-revalidate");
		response.setHeader("Progma", "no-cache");
		response.setHeader("Expries", "0");

		if (session.getAttribute("uid") == null) {
			response.sendRedirect("timeout.jsp");
		}
	%>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Sports Club System</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#">Home</a></li>
				<li class="active"><a href="dashboard">Back</a></li>

			</ul>

		<ul class="nav navbar-nav navbar-right">
				<li><a href="logout">Logout &nbsp;<span
						class="glyphicon glyphicon-off"></span></a></li>
			</ul>
		</div>
	</nav>

	
	<h1>Viewing Bookings</h1>
	
	<table id="sports" align="center">
			<tr>
				<th>SID</th>
				<th>USER ID</th>
				<th>BOOKING ID</th>
				<th>BOOKING DATE</th>
				<th>BOOKING TIME</th>
				<th>BOOKING PRICE</th>
			</tr>
			<c:forEach items="${booking}" var="allSport">
				<tr>
					<td>${allSport.sid}</td>
					<td>${allSport.userid}</td>
					<td>${allSport.bookingid}</td>
					<td>${allSport.bookingdate}</td>
					<td>${allSport.bookingtime}</td>
					<td>${allSport.bookingprice}</td>

				</tr>
			</c:forEach>
		</table>
	
<body>

</body>
</html>