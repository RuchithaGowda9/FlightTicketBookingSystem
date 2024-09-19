<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
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
        .edit-user-card {
            background-color: rgba(255, 255, 255, 0.95);
            border-radius: 12px;
            padding: 30px;
            width: 100%;
            max-width: 500px;
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
        .form-group label {
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
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/user/bookinghistory">Booking History</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/user/logout"><i class="fas fa-sign-out-alt"></i> Log Out</a>
            </li>
        </ul>
    </div>
</nav>

    <!-- Main Content -->
    <div class="container body-content d-flex justify-content-center align-items-center" style="margin-top: 70px;">
        <div class="edit-user-card">
            <h2 class="text-center">Edit User Details</h2>

            <c:if test="${not empty successMessage}">
    			<div class="alert alert-success" role="alert">
        			${successMessage}
    			</div>
			</c:if>


        <!-- Error Message -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </c:if>

<!-- Form Section -->
<form:form method="post" modelAttribute="user" action="${pageContext.request.contextPath}/user/updateuser/${user.userId}">
    <div class="form-group">
        <label for="firstName" class="required-field">First Name:</label>
        <form:input path="firstName" id="firstName" class="form-control" required="true"/>
    </div>
    <div class="form-group">
        <label for="lastName" class="required-field">Last Name:</label>
        <form:input path="lastName" id="lastName" class="form-control" required="true"/>
    </div>
    <div class="form-group">
        <label for="email" class="required-field">Email:</label>
        <form:input path="email" id="email" class="form-control" required="true"/>
    </div>
    <div class="form-group">
        <label for="phoneNumber" class="required-field">Phone Number:</label>
        <form:input path="phoneNumber" id="phoneNumber" class="form-control" readonly="true"/>
    </div>
    <div class="form-group d-flex justify-content-between">
        <button type="submit" class="btn btn-primary">Update</button>
        <a href="${pageContext.request.contextPath}/user/showuser/${user.userId}" class="btn btn-secondary">Cancel</a>
    </div>
</form:form>

        </div>
    </div>

    <!-- Footer -->
    <footer class="footer">
        <div class="container">
            <p>&copy; 2024 AeroBook. All rights reserved.</p>
        </div>
    </footer>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
