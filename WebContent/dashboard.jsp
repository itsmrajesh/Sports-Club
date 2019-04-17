<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>SportsClub</title>
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
.container {
	overflow: hidden
}

.tab {
	float: left;
}

.dropbtn {
	background-color: #0A0C0A;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f1f1f1;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #ddd;
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown:hover .dropbtn {
	background-color: #060706;
}
</style>

</head>
<body background="main.jpg">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Sports Club System</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<div class="dropdown">
					<button class="dropbtn">MY Account</button>
					<div class="dropdown-content">
						<a href="password.html">Change Password</a> <a href="email.html">Update
							E-mail Address</a> <a href="mobnumb.html">Update Contact Number</a>
					</div>
				</div>
				<li><a href="viewbooking">MY Booking </a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout">Logout &nbsp;<span
						class="glyphicon glyphicon-off"></span></a></li>
			</ul>
		</div>
	</nav>

	<!-- Nav Bar Ended -->
	<!-- Main body Starts -->
	
	<div align="center">
		<font Color="white" size="8px" face="arial"><h1 color:"white">
			<%
				out.println("Welcome " + session.getAttribute("name").toString().toUpperCase());
			%>
			</h1><h1>
			<%
				out.println("ID \t:- " + session.getAttribute("uid"));
			%>
		</h1></font>
	</div> <br><br>
<table id="sports" align="center">
		<tr>
			<tr>
			<th>CLUB ID</th>
			<th>CLUB Name</th>
			<th>Location</th>
			<th>Contact Number</th>
			<th>Book Sport</th>
		</tr>
		</tr>
		<c:forEach items="${allSportsclubs}" var="club">
			<tr>
				<td>${club.scid}</td>
				<td>${club.scname}</td>
				<td>${club.location}</td>
				<td>${club.contactnumber}</td>
				<td><a href="viewsportsforbooking?scid=${club.scid}"><button>Book Now</button></a>
					</td>
			</tr>
		</c:forEach>
	</table>

</body>

</html>