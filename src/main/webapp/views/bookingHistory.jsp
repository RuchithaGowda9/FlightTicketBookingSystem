<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking History</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        html, body {
            height: 100%;
            margin: 0;
            background-color: #ffffff;
            background-image: url('https://assets.newatlas.com/dims4/default/2a36987/2147483647/strip/true/crop/1578x1052+0+14/resize/1200x800!/quality/90/?url=http%3A%2F%2Fnewatlas-brightspot.s3.amazonaws.com%2Farchive%2Fcloud-streamer-2.jpg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            display: flex;
            flex-direction: column;
        }
        .card {
            background-color: rgba(255, 255, 255, 0.95);
            border-radius: 12px;
            padding: 30px;
            margin: 20px auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .navbar {
            background-color: #2a52be;
            height: 70px;
            line-height: 70px;
            padding: 0 15px;
            display: flex;
            align-items: center;
            position: fixed;
            width: 100%;
            top: 0;
            left: 0;
            z-index: 1000;
        }
        .navbar-brand, .nav-link {
            color: #ffffff;
            transition: color 0.3s ease;
        }
        .btn-primary {
            background-color: #2a52be;
            border: none;
            font-size: 16px;
        }
        .btn-primary:hover {
            background-color: #1a3e7a;
            color: #ffffff;
        }
        .footer {
            background-color: #4a535e;
            color: #ffffff;
            padding: 20px 0;
            text-align: center;
            margin-top: auto;
        }
        .table thead th {
            background-color: #2a52be;
            color: white;
        }
        .status-delayed {
        color: red;
        font-weight: bold;
    }
        .status-cancelled {
        color: red;
        font-weight: bold;
    }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/user/userdashboard">Dashboard</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/user/userdashboard"><i class="fas fa-home"></i> Home</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="profileDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-user"></i> Profile
                </a>
                <div class="dropdown-menu" aria-labelledby="profileDropdown">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/user/edituser/${user.userId}">Update Profile</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/bookings/history">Booking History</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/user/logout"><i class="fas fa-sign-out-alt"></i> Log Out</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container body-content" style="margin-top: 70px;">
    <div class="card">
        <h2 class="text-center">Booking History</h2>
        
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                ${successMessage}
            </div>
        </c:if>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </c:if>

        <table class="table table-bordered">
    <thead>
        <tr>
            <th>Booking ID</th>
            <th>Booking Date</th>
            <th>Flight Number</th>
            <th>Arrival Airport</th>
            <th>Departure Airport</th>
            <th>Flight Status</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="booking" items="${bookings}">
            <tr>
                <td>${booking.bookingId}</td>
                <td>${booking.bookingDate}</td>
                <td>${booking.flight.flightNumber}</td>
                <td>${booking.flight.arrivalAirport.airportCode} - ${booking.flight.arrivalAirport.city}</td>
                <td>${booking.flight.departureAirport.airportCode} - ${booking.flight.departureAirport.city}</td>
                <td class="${booking.flight.status == 'Delayed' ? 'status-delayed' : booking.flight.status == 'Cancelled' ? 'status-cancelled' : ''}">
    					${booking.flight.status}
				</td>

                <td>
    <c:choose>                    
        <c:when test="${booking.status == 'Booked'}">
            <c:set var="departureTime" value="${booking.flight.departureTime.time}" />
            <c:if test="${departureTime > currentTime && booking.flight.status != 'Cancelled'}">
					<button type="button" class="btn btn-danger" onclick="showCancelModal('${booking.bookingId}')">Cancel</button>
            </c:if>
            <c:if test="${departureTime <= currentTime || booking.flight.status == 'Cancelled' }">
                <button type="button" class="btn btn-danger" disabled>No Action</button>
            </c:if>
        </c:when>
        <c:when test="${booking.status != 'Booked'}">
            <button type="button" class="btn btn-danger" disabled>Cancelled</button>
        </c:when>
    </c:choose>
</td>

            </tr>
        </c:forEach>
    </tbody>
</table>

<div class="modal fade" id="cancelModal" tabindex="-1" role="dialog" aria-labelledby="cancelModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="cancelModalLabel">Confirm Cancellation</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure you want to cancel this booking?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-danger" id="confirmCancelBtn">Cancel Booking</button>
            </div>
        </div>
    </div>
</div>

<div class="d-flex justify-content-end">
    <a href="${pageContext.request.contextPath}/user/userdashboard" class="btn btn-primary">Back to Dashboard</a>
</div>

    </div>
</div>

<footer class="footer">
    <div class="container">
        <p>&copy; 2024 AeroBook. All rights reserved.</p>
    </div>
</footer>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    let currentBookingId = null;

    function showCancelModal(bookingId) {
        currentBookingId = bookingId;
        $('#cancelModal').modal('show');
    }

    $('#confirmCancelBtn').click(function() {
        if (currentBookingId) {
            // Create a form and submit it
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = '${pageContext.request.contextPath}/bookings/cancel/' + currentBookingId;
            document.body.appendChild(form);
            form.submit();
        }
    });
</script>
</body>
</html>
