<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title><%
		out.println(session.getAttribute("name").toString().toUpperCase() + " Dashboard");
	%></title>
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
	color: white;
}

.imgbg1{
  background-image: url("images/cricket.png");
 background-position:left bottom,right top;
	background-repeat: no-repeat;
	background-size: 350px, 350px;
}
.imgbg2{
  background-image: url("images/trophy.png");
 background-position: right bottom, left top;
	background-repeat: no-repeat;
	background-size: 350px, 350px;
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
				<li><a href="viewprofile">Profile</a></li>
				
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
	
	<div class="imgbg1">
	<div class="imgbg2">
		<h1 color:"white">
			<%
				out.println("Welcome " + session.getAttribute("name").toString().toUpperCase());
			%>
			</h1><h1>
			<%
				out.println("ID \t:- " + session.getAttribute("uid"));
			%>
		</h1>
	 <br><br>
	<h1>Sports Clubs</h1>
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
</div>
</div>
</body>

</html>