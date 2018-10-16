<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title>Automobile Services</title>
</head>
<%!boolean isValidUser = false;%>
<%!String user = "";%>
<%
	if (session.getAttribute("isValidUser") != null) {
		isValidUser = (boolean) session.getAttribute("isValidUser");
		//user = session.getAttribute("user").toString();
	}
%>
<body>
	<c:if test="${isValidUser == false}">
		<%
			response.sendRedirect("/login");
		%>
	</c:if>
	<c:if test="${isValidUser == true}">
		<div style="margin-left: 10px">
			<h2>Services</h2>
			<div>
				<h4>Customer</h4>
				<ul>
					<li><a href="addCustomer.jsp">Add a new customer</a></li>
					<li><a href="updateCustomer.jsp">Update existing customer</a></li>
					<li><a href="getCustomer.jsp">Get customer details</a></li>
				</ul>
			</div>
			<div>
				<h4>Service Records</h4>
				<ul>
					<li><a href="getServiceRecords.jsp">Get Service Records</a></li>
				</ul>
			</div>
			<div>
				<h4>New Service</h4>
				<ul>
					<li><a href="newService.jsp">New Service</a></li>
				</ul>
			</div>
			<br>
			<div>
				<h4>Logout</h4>
				<ul>
					<li><a href="logout.jsp">Logout</a></li>
				</ul>
			</div>
		</div>
	</c:if>
</body>
</html>