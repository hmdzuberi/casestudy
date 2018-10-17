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

        $(function() {
            let url = "http://localhost:8080/AutomobileService/rest/customer/new";
            $.get(url, function(data) {
                $("#customerNo").val(data);
            });

            $("#btn").on('click', function() {
                var customer = {
                    "customerNo": $("#customerNo").val(),
                    "customerName": $("#customerName").val(),
                    "phoneNo": $("#phoneNo").val(),
                    "address": $("#address").val(),
                    "car": {
                        "carNo": $("#carNo").val(),
                        "carModel": $("#carModel").val()
                    }
                };

                $.ajax({
                    url: "http://localhost:8080/AutomobileService/rest/customer/add",
                    method: "POST",
                    contentType:"application/json",
                    data: JSON.stringify(customer),
                    success: function(data) {
                        $(":input", "#form").not(":button").val("");
                        $("#info").empty().append(data);
                        // document.location = "main.jsp";
                    },
                    error: function(err) {
                        $("#info").empty().append("Error");
                    }
                });
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
        <div style="margin-left: 10px" class="col-md-4">
            <h2>Add Customer</h2>
            <form class="form-group" id="form">
                <h4>Customer Details</h4>
                <div>
                    <label for="customerNo">Customer No:</label>
                    <input type="text" id="customerNo" name="customerNo" class="form-control" disabled>
                </div>
                <br>
                <div>
                    <label for="customerName">Customer Name:</label>
                    <input type="text" id="customerName" name="customerName" class="form-control">
                </div>
                <br>
                <div>
                    <label for="phoneNo">Phone No:</label>
                    <input type="text" id="phoneNo" name="phoneNo" class="form-control">
                </div>
                <br>
                <div>
                    <label for="address">Address:</label>
                    <input type="text" id="address" name="address" class="form-control">
                </div>
                <br>
                <h4>Car Details</h4>
                <div>
                    <label for="carNo">Car No:</label>
                    <input type="text" id="carNo" name="carNo" class="form-control">
                </div>
                <br>
                <div>
                    <label for="carModel">Car Model:</label>
                    <input type="text" id="carModel" name="carModel" class="form-control">
                </div>
                <br>
                <input type="button" class="btn btn-primary" id="btn" value="Add"><span id="info" style="margin-left: 10px"></span>
            </form>
            <br>
            <a href="/AutomobileService/">Back</a>
        </div>
    </c:if>
</body>

</html>