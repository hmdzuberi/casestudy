<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
        crossorigin="anonymous"></script>
    <script type="text/javascript">

        $(function () {
            $("#getBtn").on('click', function () {
                $("#info").empty();
                $(":input", "#form").not(":button, #customerNo").val("");
                $.ajax({
                    url: "http://localhost:8080/AutomobileService/rest/customer/get?customerNo=" + $("#customerNo").val(),
                    method: "GET",
                    success: function (customer) {
                        $("#customerName").val(customer.customerName);
                        $("#phoneNo").val(customer.phoneNo);
                        $("#address").val(customer.address);
                        $("#carNo").val(customer.car.carNo);
                        $("#carModel").val(customer.car.carModel);
                    },
                    error: function (err) {
                        $("#info").empty().append("Error");
                    }
                })
            });
        });

    </script>
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
        <div style="margin-left: 10px" class="col-md-8">
            <h2>Get Customer</h2>
            <form class="form-group" id="form">
                <div class="col-md-6" style="float: left">
                    <h4>Customer Details</h4>
                    <div>
                        <label for="customerNo">Customer No:</label>
                        <input type="text" id="customerNo" name="customerNo" class="form-control">
                        <br>
                        <input type="button" value="Get Customer" class="btn btn-primary" id="getBtn">
                        <span id="info" style="margin-left: 10px"></span>
                    </div>
                    <br>
                    <div>
                        <label for="customerName">Customer Name:</label>
                        <input type="text" id="customerName" name="customerName" class="form-control" disabled>
                    </div>
                    <br>
                    <div>
                        <label for="phoneNo">Phone No:</label>
                        <input type="text" id="phoneNo" name="phoneNo" class="form-control" disabled>
                    </div>
                    <br>
                    <div>
                        <label for="address">Address:</label>
                        <input type="text" id="address" name="address" class="form-control" disabled>
                    </div>
                    <br>
                    <a href="login">Back</a>
                </div>
                <div class="col-md-6" style="float: right">
                    <h4>Car Details</h4>
                    <div>
                        <label for="carNo">Car No:</label>
                        <input type="text" id="carNo" name="carNo" class="form-control" disabled>
                    </div>
                    <br>
                    <div>
                        <label for="carModel">Car Model:</label>
                        <input type="text" id="carModel" name="carModel" class="form-control" disabled>
                    </div>
                </div>
            </form>
        </div>
    </c:if>
</body>

</html>