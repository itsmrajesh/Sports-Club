<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
h1 {
	text-align: center;
	color: green;
}

body {
	background-image: url("images/bg2f.jpg");
	background-color: #cccccc;
}

#bck{
 background-image: url(images/runner.png);
  background-position: right bottom, left top;
  background-repeat: no-repeat;
 background-size: 300px, 350px;
}
</style>
<title>Booking Sport</title>
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
				<li><a href="logout">Logout &nbsp;<span
						class="glyphicon glyphicon-off"></span></a></li>
			</ul>
		</div>
	</nav>
	<h1>Sport Details</h1>
	<table id="sports" align="center">
		<tr>
			<th>SID</th>
			<th>SportsName</th>
			<th>Sports Club</th>
			<th>Fees</th>
			<th>No.of.Players</th>
			<th>Sport Type</th>
		</tr>
		<c:forEach items="${viewsport}" var="sport">
			<tr>
				<td>${sport.sid}</td>
				<td>${sport.sname}</td>
				<td>${sport.sclub}</td>
				<td>${sport.sprice}</td>
				<td>${sport.players}</td>
				<td>${sport.stype}</td>

			</tr>
		</c:forEach>
	</table>
	<br>
	<div id="bck">
		<h1>Provide Booking Details</h1>
		<div class="container">
			<form action="dobooking" method="post">
				<div class="input-group">
					<span class="input-group-addon">Date &nbsp; &nbsp;<i
						class=" glyphicon glyphicon-calendar"></i></span> <input id="date"
						type="date" class="form-control" name="date" placeholder="date"
						required>
				</div>
				<div class="input-group">
					<span class="input-group-addon">Start time &nbsp;<i
						class="glyphicon glyphicon-time"></i></span> <input id="time" type="time"
						class="form-control" name="time" placeholder="time" required>
				</div>
				<div class="input-group">
					<span class="input-group-addon">End time &nbsp;<i
						class="glyphicon glyphicon-time"></i></span> <input id="time" type="time"
						class="form-control" name="time" placeholder="time" required>
				</div>
				<input type="checkbox" required>Agree to T&C <br> <br>
				<div>
					<input type="submit" name="submit" value="Confirm Booking"
						class="btn btn-success">&nbsp; <input type="reset"
						name="reset" value=" Clear " class="btn btn-info">
				</div>
				<br> <a href="dashboard" class="btn btn-danger" role="button">Cancel</a>
			</form>
		</div>
	</div>
</body>
</html>