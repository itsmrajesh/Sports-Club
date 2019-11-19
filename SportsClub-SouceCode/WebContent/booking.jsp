<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cc"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="b"%>
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
<link rel="stylesheet" type="text/css" href="css/clubtable.css" />
<style>
h1 {
	text-align: center;
	color: green;
}

body {
	background-image: url("images/bg2f.jpg");
	background-color: #cccccc;
}

#bckright {
	background-image: url(images/allsports.png);
	background-position: right, left top;
	background-repeat: no-repeat;
	background-size: 450px, 450px;
}

#bck {
	background-image: url(images/indiacricket.png);
	background-position: left, left top;
	background-repeat: no-repeat;
	background-size: 450px, 450px;
}
</style>
<title>|Booking|</title>
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
				<li><a href="dashboard">Home</a></li>
				<li class="active"><a href="dashboard">Back</a></li>

			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout">Logout &nbsp;<span
						class="glyphicon glyphicon-off"></span></a></li>
			</ul>
		</div>
	</nav>
	<div id="bck">
		<div id="bckright">
			<h1>Sports Club Details</h1>
			<table id="club" align="center">
				<b:forEach items="${clubdetails}" var="club">
					<tr>
						<th>Sports Club ID</th>
						<td>${club.scid}</td>
					</tr>
					<tr>
						<th>Club Name</th>
						<td>${club.scname}</td>
					</tr>
					<tr>
						<th>Club Location</th>
						<td>${club.location}</td>
					</tr>
					<tr>
						<th>Contact Number</th>
						<td>${club.contactnumber}</td>
					</tr>
				</b:forEach>
			</table>
			<h1>InDoor Sports</h1>
			<table id="sports" align="center">
				<tr>
					<th>SID</th>
					<th>SportsName</th>
					<th>Sports Club</th>
					<th>Fees</th>
					<th>No.of.Players</th>
					<th>Sport Type</th>
					<th>Book Now</th>
				</tr>
				<c:forEach items="${allSportsindoor}" var="allSport">
					<tr>
						<td>${allSport.sid}</td>
						<td>${allSport.sname}</td>
						<td>${allSport.sclub}</td>
						<td>${allSport.sprice}</td>
						<td>${allSport.players}</td>
						<td>${allSport.stype}</td>
						<td><a href="booksport?sid=${allSport.sid}"><button>Book
									Now</button></a></td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<h1>OutDoor Sports</h1>
			<table id="sports" align="center">
				<tr>
					<th>SID</th>
					<th>SportsName</th>
					<th>Sports Club</th>
					<th>Fees</th>
					<th>No.of.Players</th>
					<th>Sport Type</th>
					<th>Book Now</th>
				</tr>
				<cc:forEach items="${allSportsoutdoor}" var="allSport">
					<tr>
						<td>${allSport.sid}</td>
						<td>${allSport.sname}</td>
						<td>${allSport.sclub}</td>
						<td>${allSport.sprice}</td>
						<td>${allSport.players}</td>
						<td>${allSport.stype}</td>
						<td><a href="booksport?sid=${allSport.sid}"><button>Book
									Now</button></a></td>
					</tr>
				</cc:forEach>
			</table>
		</div>
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