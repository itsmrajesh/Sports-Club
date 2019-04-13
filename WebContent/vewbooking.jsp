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
<style>
.container {
	overflow: hidden
}

.tab {
	float: left;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 15px;
}

#customers {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#customers td, #customers th {
	border: 1px solid #ddd;
	padding: 8px;
}

#customers tr:nth-child(even) {
	background-color: #f2f2f2;
}

#customers tr:hover {
	background-color: #ddd;
}

#customers th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #4CAF50;
	color: #070101;
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
				<li><a href="#">Home</a></li>
				<li class="active"><a href="dashboard">Back</a></li>

			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout"><span
						class="glyphicon glyphicon-sign-up"></span> Logout</a></li>
			</ul>
		</div>
	</nav>

	
	<center>
		<h2>
			<font Color="red" size="8px" face="arial">VIEWING BOOKING 
		</h2>
	</center>
	</font>
	
	<table id="customers">
			</center>
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