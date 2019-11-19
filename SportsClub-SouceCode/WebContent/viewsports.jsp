<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
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
h1 {
	text-align: center;
	color: green;
}

body {
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

			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout">Logout &nbsp;<span
						class="glyphicon glyphicon-off"></span></a></li>
			</ul>
		</div>
	</nav>

	<h1>Viewing All Sports</h1>
	<table id="sports" align="center">
		<tr>
			<th>Sports Club</th>
			<th>SID</th>
			<th>SportsName</th>
			<th>No.of.Players</th>
			<th>Sport Type</th>
			<th>Fees</th>
		</tr>
		<c:forEach items="${allSports}" var="allSport">
			<tr>
				<td>${allSport.sclub}</td>
				<td>${allSport.sid}</td>
				<td>${allSport.sname}</td>
				<td>${allSport.players}</td>
				<td>${allSport.stype}</td>
				<td>${allSport.sprice}</td>

			</tr>
		</c:forEach>
	</table>
</body>

</html>