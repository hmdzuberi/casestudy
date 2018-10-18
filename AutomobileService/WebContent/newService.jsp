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
            let url = "http://localhost:8080/AutomobileService/rest/services/get";
            $.get(url, function (data) {
                $("#serviceName").empty();
                for (service in data) {
                    $("#serviceName").append("<option>" + data[service] + "</option>");
                }
            });

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
                });
            });

            $("#serviceName").on('change', function () {
                let url = "http://localhost:8080/AutomobileService/rest/services/getService?serviceName=" + $("#serviceName").val();
                $.get(url, function (data) {
                    $("#amount").val(data.amount);
                    $("#netAmount").val($("#amount").val());
                    $("#insuranceCoverage").val(0);
                });
            });

            $("#insurance").on('change', function () {
                if ($("#insurance").prop("checked") == true) {
                    $.ajax({
                        url: "http://localhost:8080/Insurance/insurance/get?carNo=" + $("#carNo").val(),
                        method: "GET",
                        success: function (data) {
                            let amount = parseFloat($("#amount").val());
                            let InsuranceCoverage = amount * data.maxCoverage;
                            let netAmount = amount - InsuranceCoverage;

                            $("#insuranceCoverage").val(InsuranceCoverage);
                            $("#netAmount").val(netAmount);
                        },
                        error: function (err) {
                            $("#insuranceinfo").empty().append("Error");
                        }
                    });
                } else {
                    $("#netAmount").val($("#amount").val());
                }
            });

            $("#newBtn").on('click', function () {
                var serviceLogEntry = {
                    "serviceLogId": 0,
                    "carNo": $("#carNo").val(),
                    "serviceName": $("#serviceName").val(),
                    "dateGiven": $("#dateGiven").val(),
                    "deliveryDate": $("#deliveryDate").val(),
                    "insuranceCoverage": $("#insuranceCoverage").val(),
                    "netAmount": $("#netAmount").val()
                };
                $.ajax({
                    url: "http://localhost:8080/AutomobileService/rest/services/new",
                    method: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(serviceLogEntry),
                    success: function (data) {
                        $(':input', 'form').not(':button').val('').prop('checked', false);
                        $("#addinfo").empty().append(data);
                    },
                    error: function (err) {
                        $("#addinfo").empty().append("Error")
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
        <div style="margin-left: 10px" class="col-md-9">
            <h2>New Service</h2>
            <form class="form-group" id="form">
                <div style="float: left" class="col-md-6">
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
                    <br>
                    <a href="login">Back</a>
                </div>
                <div style="float: right" class="col-md-6">
                    <h4>Service</h4>
                    <div>
                        <label for="serviceName">Service:</label>
                        <select id="serviceName" class="form-control" name="serviceName"></select>
                    </div>
                    <br>
                    <div>
                        <label for="amount">Amount:</label>
                        <input type="text" id="amount" name="amount" class="form-control" disabled>
                    </div>
                    <br>
                    <div>
                        <label for="dateGiven">Date Given:</label>
                        <input type="date" id="dateGiven" name="dateGiven" class="form-control">
                    </div>
                    <br>
                    <div>
                        <label for="deliveryDate">Delivery Date:</label>
                        <input type="date" id="deliveryDate" name="deliveryDate" class="form-control">
                    </div>
                    <br>
                    <div class="checkbox">
                        <label><input type="checkbox" id="insurance">Insurance</label>
                    </div>
                    <div id="insuranceinfo"></div>
                    <br>
                    <div>
                        <label for="insuranceCoverage">Insurance Coverage:</label>
                        <input type="text" id="insuranceCoverage" name="insuranceCoverage" class="form-control"
                            disabled>
                    </div>
                    <br>
                    <div>
                        <label for="netAmount">Net Amount:</label>
                        <input type="text" id="netAmount" name="netAmount" class="form-control" disabled>
                    </div>
                    <br>
                    <input type="button" id="newBtn" value="New service" class="btn btn-primary">
                    <span id="addinfo" style="margin-left: 10px"></span>
                </div>
            </form>
        </div>
    </c:if>
</body>

</html>