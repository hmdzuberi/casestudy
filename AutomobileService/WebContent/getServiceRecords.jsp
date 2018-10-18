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
            let url = "http://localhost:8080/AutomobileService/rest/services/getlogs";
            $.get(url, function (data) {
                $("#tbody").empty();
                for (record in data) {
                    $("#tbody").append("<tr><td>" + data[record].serviceLogId + "</td><td>" + data[record].carNo + "</td><td>" + data[record].serviceName + "</td><td>" + data[record].dateGiven + "</td><td>" + data[record].deliveryDate + "</td><td>" + data[record].insuranceCoverage + "</td><td>" + data[record].netAmount + "</td></tr>");
                }
            });

            $("#getBtn").on('click', function () {
                $.ajax({
                    url: "http://localhost:8080/AutomobileService/rest/services/getlog?carNo=" + $("#carNo").val(),
                    method: "GET",
                    success: function (data) {
                        $("#getinfo").empty();
                        $("#tbody").empty();
                        for (record in data) {
                            $("#tbody").append("<tr><td>" + data[record].serviceLogId + "</td><td>" + data[record].carNo + "</td><td>" + data[record].serviceName + "</td><td>" + data[record].dateGiven + "</td><td>" + data[record].deliveryDate + "</td><td>" + data[record].insuranceCoverage + "</td><td>" + data[record].netAmount + "</td></tr>");
                        }
                    },
                    error: function (err) {
                        $("#getinfo").empty().append("Error");
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
        <div style="margin-left: 10px">
            <h2>Service Logs</h2>
            <div class="col-md-3">
                <form class="form-group">
                    <div>
                        <label>Car No:</label>
                        <input type="text" id="carNo" name="carNo" class="form-control">
                    </div>
                    <br>
                    <input type="button" id="getBtn" value="Get Records" class="btn btn-primary">
                    <span id="getinfo"></span>
                </form>
                <br>
                <a href="login">Back</a>
            </div>
            <br>
            <div class="col-md-9">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Service Log ID</th>
                            <th>Car No.</th>
                            <th>Service</th>
                            <th>Date Given</th>
                            <th>Delivery Date</th>
                            <th>Insurance Coverage</th>
                            <th>Net Amount</th>
                        </tr>
                    </thead>
                    <tbody id="tbody">

                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
</body>

</html>