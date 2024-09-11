<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Flight</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Update Flight</h2>
        <form action="${pageContext.request.contextPath}/flight/update/${flight.flightId}" method="post">
            <div class="form-group">
                <label for="flightNumber">Flight Number:</label>
                <input type="text" id="flightNumber" name="flightNumber" class="form-control" value="${flight.flightNumber}" required>
            </div>

            <div class="form-group">
                <label for="departureAirport">Departure Airport:</label>
                <input type="text" id="departureAirport" name="departureAirport" class="form-control" value="${flight.departureAirport}" required>
            </div>

            <div class="form-group">
                <label for="arrivalAirport">Arrival Airport:</label>
                <input type="text" id="arrivalAirport" name="arrivalAirport" class="form-control" value="${flight.arrivalAirport}" required>
            </div>

            <div class="form-group">
                <label for="departureTime">Departure Time:</label>
                <input type="datetime-local" id="departureTime" name="departureTime" class="form-control" value="${flight.departureTime}">
            </div>

            <div class="form-group">
                <label for="arrivalTime">Arrival Time:</label>
                <input type="datetime-local" id="arrivalTime" name="arrivalTime" class="form-control" value="${flight.arrivalTime}">
            </div>

            <div class="form-group">
                <label for="status">Status:</label>
                <input type="text" id="status" name="status" class="form-control" value="${flight.status}">
            </div>

            <button type="submit" class="btn btn-primary">Update Flight</button>
        </form>
        
        <c:if test="${param.updateSuccess}">
            <div class="alert alert-success mt-3" role="alert">
                Flight updated successfully!
            </div>
        </c:if>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
