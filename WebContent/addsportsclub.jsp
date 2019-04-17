<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sports Club ADD</title>

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
body  {
  background-image: url("images/bg2f.jpg");
  background-color: #cccccc;
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
				<li><a href="admin.html">Home</a></li>
				<li class="active"><a href="admin.html">Back</a></li>
				<li><a href="addnewclub.html">Add New Club &nbsp; <span
						class="glyphicon glyphicon-plus"></span>
				</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout">Logout &nbsp;<span
						class="glyphicon glyphicon-off"></span></a></li>
			</ul>
		</div>
	</nav>

	<h1>Available Sports Clubs</h1>
	<table id="sports" align="center">
		<tr>
			<th>CLUB ID</th>
			<th>CLUB Name</th>
			<th>Location</th>
			<th>Contact Number</th>
			<th>Add Sport</th>
		</tr>
		<c:forEach items="${sportsclubs}" var="club">
			<tr>
				<td>${club.scid}</td>
				<td>${club.scname}</td>
				<td>${club.location}</td>
				<td>${club.contactnumber}</td>
				<td><a href="add?scid=${club.scid}"> <button>Add New Sport</button></a></td>
			</tr>
		</c:forEach>
	</table>




</body>
</html>