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
<link rel="stylesheet" type="text/css" href="css/clubtable.css" />
<title>
	<%
		out.println(session.getAttribute("name").toString().toUpperCase() + " Profile");
	%>
</title>
<style type="text/css">
body {
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
				<li><a href="dashboard">Home</a></li>
				<li class="active"><a href="dashboard">Back</a></li>

			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout">Logout &nbsp;<span
						class="glyphicon glyphicon-off"></span></a></li>
			</ul>
		</div>
	</nav>
	<!--Main Body Starts here  -->
	<table id="club" align="center">
				<c:forEach items="${userdetails}" var="user">
					<tr>
						<th>Name</th>
						<td>${user.name}</td>
					</tr>
					<tr>
						<th>User ID</th>
						<td>${user.userid}</td>
					</tr>
					<tr>
						<th>Email</th>
						<td>${user.email}</td>
					</tr>
					<tr>
						<th>Contact Number</th>
						<td>${user.mobile}</td>
					</tr>
					<tr>
						<th>Address</th>
						<td>${user.address}</td>
					</tr>
					<tr>
						<th>Date Of Birth</th>
						<td>${user.dob}</td>
					</tr>
					
				</c:forEach>
			</table>
</body>
</html>