<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cc"%>
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
<link rel="stylesheet" type="text/css" href="css/table.css" />

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: #FD4AC1;
}

* {
	box-sizing: border-box;
}

/* Add padding to containers */
.container {
	padding: 16px;
	background-color: white;
}

/* Full-width input fields */
input[type=text], input[type=number] {
	width: 35%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}

input[type=text]:focus, input[type=number]:focus {
	background-color: #ddd;
	outline: none;
}

/* Overwrite default styles of hr */
hr {
	border: 1px solid #f1f1f1;
	margin-bottom: 25px;
}

/* Set a style for the submit button */
.registerbtn {
	background-color: #F00A0A;
	color: white;
	padding: 10px 15px;
	margin: 8px 4px;
	border: none;
	cursor: pointer;
	width: 8%;
	opacity: 0.9;
}

.registerbtn:hover {
	opacity: 1;
}

.registerbtn {
	border-radius: 8px;
}
</style>
<title>|Booking|</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Sports Club System</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="dashboard">Home</a></li>
				<li class="active"><a href="dashboard">Back</a></li>

			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout"><span
						class="glyphicon glyphicon-sign-up"></span> Logout</a></li>
			</ul>
		</div>
	</nav>
	<div>
<h1 align="center"> InDoor Sports</h1>
		<table id="sports" align="center">
			<tr>
				<th>SID</th>
				<th>SportsName</th>
				<th>Sports Club</th>
				<th>Fees</th>
				<th>No.of.Players</th>
				<th>Sport Type</th>
			</tr>
			<c:forEach items="${allSportsindoor}" var="allSport">
				<tr>
					<td>${allSport.sid}</td>
					<td>${allSport.sname}</td>
					<td>${allSport.sclub}</td>
					<td>${allSport.sprice}</td>
					<td>${allSport.players}</td>
					<td>${allSport.stype}</td>
				</tr>
			</c:forEach>
		</table>
		<br>
<h1 align="center"> OutDoor Sports</h1>
		<table id="sports" align="center">
			<tr>
				<th>SID</th>
				<th>SportsName</th>
				<th>Sports Club</th>
				<th>Fees</th>
				<th>No.of.Players</th>
				<th>Sport Type</th>
			</tr>
			<cc:forEach items="${allSportsoutdoor}" var="allSport">
				<tr>
					<td>${allSport.sid}</td>
					<td>${allSport.sname}</td>
					<td>${allSport.sclub}</td>
					<td>${allSport.sprice}</td>
					<td>${allSport.players}</td>
					<td>${allSport.stype}</td>
				</tr>
			</cc:forEach>
		</table>
	</div>
	<!-- <form action="booksport">
        <div class="container">
          <label for="name"><b>Enter Date</b></label>
          <input type="date" placeholder="Enter Date for Booking" name="date" required><br>
          <label for="club"><b>Enter Time</b></label>
          <input type="time" placeholder="Enter time for Booking" name="time" required><br>      
          <button type="submit" class="registerbtn">Submit</button>
        </div>
      </form> -->
</body>
</html>