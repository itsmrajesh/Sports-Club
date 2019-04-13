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
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: #5DF85D;
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

/* Add a blue text color to links */
/* Set a grey background color and center the text of the "sign in" section */
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
				<li class="active"><a href="admin.html">Back</a></li>

			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout"><span
						class="glyphicon glyphicon-sign-up"></span> Logout</a></li>
			</ul>
		</div>
	</nav>

	<h1>Available Sports Clubs</h1>
	<table id="sports" align="left">
		<tr>
			<th>CLUB ID</th>
			<th>CLUB Name</th>
			<th>Add Sport</th>
		</tr>
		<c:forEach items="${sportsclubs}" var="club">
			<tr>
				<td>${club.scid}</td>
				<td>${club.scname}</td>
				<td><button>
						<a href="add?scid=${club.scid}"> Add New Sport</a>
					</button></td>
			</tr>
		</c:forEach>
	</table>


		<div align="right">
	<div class="container">
			<form action="addnewsportclub">
				<label for="scname">Enter Sport Club Name </label> <br><input
					type="text" placeholder="Enter Club name " required name="scname"><br>
				<input type="submit" value="Add New Club">
			</form>
		</div>
	</div>

</body>
</html>