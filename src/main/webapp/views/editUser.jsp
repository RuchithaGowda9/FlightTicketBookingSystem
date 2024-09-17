<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored = "false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Edit User Details</h2>

        <c:if test="${param.updated}">
            <div class="alert alert-success" role="alert">
                User details updated successfully!
            </div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </c:if>

        <form:form method="post" modelAttribute="user" action="${pageContext.request.contextPath}/user/updateuser/${user.userId}">
            <div class="form-group">
                <label for="firstName">First Name:</label>
                <form:input path="firstName" id="firstName" class="form-control"/>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name:</label>
                <form:input path="lastName" id="lastName" class="form-control"/>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <form:input path="email" id="email" class="form-control"/>
            </div>
            <div class="form-group">
                <label for="phoneNumber">Phone Number:</label>
                <form:input path="phoneNumber" id="phoneNumber" class="form-control" readonly="true"/>
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
            <a href="${pageContext.request.contextPath}/user/showuser/${user.userId}" class="btn btn-secondary">Cancel</a>
        </form:form>
    </div>
<!-- Footer -->
	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<h5>About Us</h5>
					<p>We are AeroBook, dedicated to providing seamless flight
						booking experiences. Discover the best flights at competitive
						prices with ease.</p>
				</div>
				<div class="col-md-3">
					<h5>Quick Links</h5>
					<ul class="list-unstyled">
						<li><a href="${pageContext.request.contextPath}/user/home"
							class="text-white">Home</a></li>
						<li><a href="${pageContext.request.contextPath}/user/contact"
							class="text-white">Contact Us</a></li>
						<li><a href="${pageContext.request.contextPath}/user/about"
							class="text-white">About Us</a></li>
						<li><a href="${pageContext.request.contextPath}/user/faq"
							class="text-white">FAQ</a></li>
					</ul>
				</div>
				<div class="col-md-3">
					<h5>Contact Us</h5>
					<ul class="list-unstyled">
						<li><i class="fas fa-envelope"></i> <a
							href="mailto:support@aerobook.com" class="text-white">support@aerobook.com</a></li>
						<li><i class="fas fa-phone"></i> <a href="tel:+1234567890"
							class="text-white">+91 83100 82277</a></li>
					</ul>
				</div>
				<div class="col-md-3">
					<h5>Follow Us</h5>
					<ul class="list-unstyled">
						<li><a href="https://www.facebook.com/" class="text-white"><i
								class="fab fa-facebook-f"></i> Facebook</a></li>
						<li><a href="https://x.com/?lang=en" class="text-white"><i
								class="fab fa-twitter"></i> Twitter</a></li>
						<li><a href="https://www.instagram.com/" class="text-white"><i
								class="fab fa-instagram"></i> Instagram</a></li>
						<li><a href="https://in.linkedin.com/" class="text-white"><i
								class="fab fa-linkedin-in"></i> LinkedIn</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="footer-bottom">
			<div class="container text-center">
				<p>&copy; 2024 AeroBook. All rights reserved.</p>
			</div>
		</div>
	</footer>
    <!-- Include Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
