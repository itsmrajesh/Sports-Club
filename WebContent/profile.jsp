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
				<li><a href="dashboard">Back</a></li>
				<div class="dropdown">
					<button class="dropbtn">MY Account</button>
					<div class="dropdown-content">
						<a href="password.html">Change Password</a> <a href="email.html">Update
							E-mail Address</a> <a href="mobnumb.html">Update Contact Number</a>
					</div>
				</div>
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