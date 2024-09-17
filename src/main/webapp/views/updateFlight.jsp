<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Update Flight</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
html, body {
    height: 100%;
    margin: 0;
    background-image: linear-gradient(to right, #87CEEB, #336699, #6699CC);
    display: flex;
    flex-direction: column;
}

.admin-dashboard-card {
    background-color: rgba(255, 255, 255, 0.95); /* Slightly transparent background */
    border-radius: 12px;
    padding: 30px;
    width: 550px; /* Reduced width */
    margin: 20px auto;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.15);
}

.navbar, .footer {
    background-color: #2a52be;
    color: #ffffff;
}

.footer {
    background-color: #4a535e;
    padding: 20px 0;
    text-align: center;
    margin-top: auto;
}

.body-content {
    margin-top: 70px;
    flex: 1;
    position: relative;
}

.navbar-brand, .nav-link {
    color: #ffffff;
    transition: color 0.3s ease;
}

.navbar-brand:hover, .nav-link:hover {
    color: #0e1f52;
}

.nav-link {
    padding: 10px 15px;
    border-radius: 4px;
    transition: background-color 0.3s ease, color 0.3s ease;
}

.nav-link:hover {
    background-color: #1a3e7a;
    color: #ffffff;
}

.btn-primary {
    background-color: #2a52be;
    border: none;
}

.btn-primary:hover {
    background-color: #1a3e7a;
    color: #ffffff;
}

.sidebar {
    height: 100%;
    width: 250px;
    position: fixed;
    top: 0;
    left: -250px;
    background-color: #343a40;
    color: #ffffff;
    transition: left 0.3s ease;
    padding-top: 60px;
    z-index: 1000;
}

.sidebar a {
    color: #ffffff;
    padding: 15px 20px;
    display: block;
    text-decoration: none;
}

.sidebar a:hover {
    background-color: #495057;
}

.sidebar-toggle {
    font-size: 24px;
    cursor: pointer;
    color: #ffffff;
}

.navbar {
    z-index: 1002;
}

.error-message {
    color: red;
    margin-top: 10px;
}
</style>
</head>
<body>
    <!-- Sidebar -->
    <div id="sidebar" class="sidebar">
        <a href="${pageContext.request.contextPath}/flight/add">Schedule Flight</a>
        <a href="${pageContext.request.contextPath}/flight/viewflights">Manage Flight Schedule</a>
        <a href="${pageContext.request.contextPath}/user/viewBooking">View Booking</a>
    </div>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/user/admindashboard">AeroBook</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <span id="sidebarToggle" class="sidebar-toggle fas fa-bars"></span>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user/admindashboard">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user/logout">Log Out</a></li>
            </ul>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container body-content d-flex justify-content-center align-items-center">
        <div class="admin-dashboard-card">
            <h2 class="text-center">Update Flight</h2>

            <form action="${pageContext.request.contextPath}/flight/update" method="post">
                <input type="hidden" name="id" value="${flight.flightId}">

                <div class="form-group">
                    <label for="flightNumber">Flight Number</label>
                    <input type="text" class="form-control" id="flightNumber" name="flightNumber" value="${flight.flightNumber}" readonly>
                </div>

                <div class="form-group">
                    <label for="departureAirport">Departure Airport</label>
                    <input type="text" class="form-control" id="departureAirport" name="departureAirport"
                           value="${flight.departureAirport.airportCode} - ${flight.departureAirport.airportName}" readonly>
                </div>

                <div class="form-group">
                    <label for="arrivalAirport">Arrival Airport</label>
                    <input type="text" class="form-control" id="arrivalAirport" name="arrivalAirport"
                           value="${flight.arrivalAirport.airportCode} - ${flight.arrivalAirport.airportName}" readonly>
                </div>

                <div class="form-group">
                    <label for="departureTime">Departure Time</label>
                    <input type="datetime-local" class="form-control" id="departureTime" name="departureTime"
                           value="${formattedDepartureTime}">
                </div>

                <div class="form-group">
                    <label for="arrivalTime">Arrival Time</label>
                    <input type="datetime-local" class="form-control" id="arrivalTime" name="arrivalTime"
                           value="${formattedArrivalTime}">
                </div>
                <div class = "form-group">
                	<label for = "tripPrice">Trip Price</label>
                	<input type = "text" class = "form-control" id = "tripPrice" name = "tripPrice"
                	value = "${flight.tripPrice }">
                </div>

                <div class="form-group">
                    <label for="status">Status</label>
                    <select class="form-control" id="status" name="status">
                        <option value="On Time" ${flight.status == 'On Time' ? 'selected' : ''}>On Time</option>
                        <option value="Delayed" ${flight.status == 'Delayed' ? 'selected' : ''}>Delayed</option>
                        <option value="Cancelled" ${flight.status == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Update Schedule</button>
            </form>
        </div>
    </div>

    <!-- Footer -->
    <footer class="footer">
        <div class="container">
            <p>&copy; 2024 AeroBook. All rights reserved.</p>
        </div>
    </footer>

    <!-- Bootstrap JavaScript and dependencies -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <!-- Custom JavaScript for Sidebar Toggle -->
    <script>
        document.getElementById('sidebarToggle').addEventListener('click', function() {
            var sidebar = document.getElementById('sidebar');
            if (sidebar.style.left === '0px') {
                sidebar.style.left = '-250px'; // Hide the sidebar
            } else {
                sidebar.style.left = '0px'; // Show the sidebar
            }
        });
    </script>
</body>
</html>
