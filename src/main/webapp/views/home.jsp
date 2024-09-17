<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AeroBook - Flight Ticket Booking</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
html, body {
	height: 100%;
	margin: 0;
	background-color: #ffffff; /* Fallback background color */
	background-image:
		url('https://assets.newatlas.com/dims4/default/2a36987/2147483647/strip/true/crop/1578x1052+0+14/resize/1200x800!/quality/90/?url=http%3A%2F%2Fnewatlas-brightspot.s3.amazonaws.com%2Farchive%2Fcloud-streamer-2.jpg');
	background-size: cover;
	/* Make sure the background image covers the entire body */
	background-position: center; /* Center the background image */
	background-attachment: fixed;
	/* Keep the background image fixed during scrolling */
	display: flex;
	flex-direction: column;
}

body {
	display: flex;
	flex-direction: column;
}

.navbar {
	background-color: #2a52be; /* Match navbar color */
	height: 70px; /* Set navbar height to 70px */
	display: flex;
	align-items: center; /* Center items vertically */
}

.navbar-brand {
	display: flex;
	align-items: center; /* Center items vertically */
}

.navbar-brand img {
	width: 60px; /* Set the logo width */
	height: 60px; /* Set the logo height */
	margin-right: 10px; /* Space between logo and text */
}

.navbar-brand, .nav-link {
	color: #ffffff;
	transition: color 0.3s ease; /* Smooth color transition */
}

.navbar-brand:hover, .nav-link:hover {
	color: #0e1f52; /* Change to a darker color on hover */
}

.hero-section {
	background-color: rgba(255, 255, 255, 0.95);
	/* Slightly transparent background */
	color: #000000; /* Set text color to black */
	padding: 40px 20px; /* Padding for the hero section */
	text-align: center;
	border-radius: 8px; /* Optional: Adds rounded corners */
	max-height: 200px;
	max-width: 800px; /* Increase max width for a wider card */
	margin: 110px auto;
	/* Center the section horizontally and add some top margin */
	flex: 1;
	/* Allows the hero section to expand and take available space */
}

.hero-section h1 {
	font-size: 2rem; /* Font size for heading */
	color: #000000; /* Ensure heading color is black */
}

.hero-section p {
	font-size: 1rem; /* Font size for paragraph */
	color: #000000; /* Ensure paragraph color is black */
}

.btn-primary {
	background-color: #2a52be; /* Match button color with navbar */
	border-color: #2a52be;
	font-size: 0.875rem; /* Font size for button */
	padding: 0.5rem 1rem; /* Padding for button */
	transition: background-color 0.3s ease, border-color 0.3s ease;
	/* Smooth color transition */
}

.btn-primary:hover {
	background-color: #0e1f52;
	border-color: #0e1f52;
}

.footer {
    background-color: #4a535e;
    color: #ffffff;
    padding: 40px 0;
    margin-top: auto;
}

.footer h5 {
    font-size: 1.25rem;
    margin-bottom: 15px;
}

.footer p {
    font-size: 0.875rem;
}

.footer a {
    color: #ffffff;
    text-decoration: none;
    transition: color 0.3s ease;
}

.footer a:hover {
    color: #cccccc;
}

.footer-bottom {
    border-top: 1px solid #333333;
    padding: 10px 0;
}

.footer .list-unstyled {
    padding-left: 0;
    list-style: none;
}

.footer .list-unstyled li {
    margin-bottom: 10px;
}

.footer .list-unstyled li a {
    color: #ffffff;
    transition: color 0.3s ease;
}

.footer .list-unstyled li a:hover {
    color: #cccccc;
}

</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark">
		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/user/home">  
			<svg width="200" height="20" viewBox="0 0 369.66666666666663 59.60033587831631" class="looka-1j8o68f"><defs id="SvgjsDefs1176"></defs><g id="SvgjsG1177" featurekey="S6ay6y-0" transform="matrix(1.0101751843782425,0,0,1.0101751843782425,-10.654502568874754,-22.728941648510457)" fill="#ffffff"><switch xmlns="http://www.w3.org/2000/svg"><foreignObject requiredExtensions="http://ns.adobe.com/AdobeIllustrator/10.0/" x="0" y="0" width="1" height="1"></foreignObject><g xmlns:i="http://ns.adobe.com/AdobeIllustrator/10.0/" i:extraneous="self"><g><g><path d="M50,81.5c-16.3,0-29.5-13.2-29.5-29.5S33.7,22.5,50,22.5c6.8,0,13.5,2.4,18.8,6.7c3.3,2.7,6,6.2,7.8,10      c0.4,0.7,0,1.6-0.7,2c-0.7,0.4-1.6,0-2-0.7c-1.7-3.4-4.1-6.5-7-9c-4.7-3.9-10.7-6.1-16.9-6.1c-14.6,0-26.5,11.9-26.5,26.5      S35.4,78.5,50,78.5c10.3,0,19.7-6,24.1-15.4c0.3-0.8,1.2-1.1,2-0.7c0.8,0.3,1.1,1.2,0.7,2C72,74.8,61.5,81.5,50,81.5z"></path></g><g><path d="M27.7,71.1c-8.8,0-15-2.4-16.7-6.9C9.2,59.3,12.8,53,21.2,46.7c0.7-0.5,1.6-0.4,2.1,0.3c0.5,0.7,0.4,1.6-0.3,2.1      c-7,5.3-10.4,10.6-9.2,14.1c2,5.4,15.8,7.3,35.6,1.2c0.8-0.2,1.6,0.2,1.9,1c0.2,0.8-0.2,1.6-1,1.9      C41.9,69.9,34.1,71.1,27.7,71.1z"></path></g><g><path d="M87.6,37.8c-0.6,0-1.2-0.4-1.4-1c-1.3-3.5-7.3-5.3-16.1-4.9l-2.3,0c-0.8,0-1.5-0.7-1.5-1.5s0.7-1.5,1.5-1.5H70      c10.4-0.5,17.2,1.9,19,6.9c0.3,0.8-0.1,1.6-0.9,1.9C87.9,37.8,87.8,37.8,87.6,37.8z"></path></g><g><g><path d="M50,67.1c-0.2,0-0.4,0-0.6-0.1l-7.3-3.4c-0.8-0.3-1.1-1.2-0.7-2c0.4-0.8,1.2-1.1,2-0.7l7.3,3.4c0.8,0.3,1.1,1.2,0.7,2       C51.1,66.8,50.6,67.1,50,67.1z"></path></g><g><path d="M46.6,74.4c-0.2,0-0.4,0-0.6-0.1c-0.8-0.3-1.1-1.2-0.7-2l3.4-7.3c0.4-0.8,1.2-1.1,2-0.7c0.8,0.3,1.1,1.2,0.7,2L48,73.5       C47.7,74.1,47.2,74.4,46.6,74.4z"></path></g></g></g><g><path d="M58.6,64c-0.2,0-0.4,0-0.6-0.1l-12-5.5c-0.6-0.3-1-1-0.8-1.7s0.7-1.2,1.4-1.2l11.9-0.4l4.3-2.6l-6.7-7.2     c-0.3-0.3-0.4-0.7-0.4-1.1c0-0.4,0.2-0.8,0.5-1.1c0.2-0.1,1.7-1.4,4.7-1.4c3.7,0,8,1.9,12.8,5.5l10.2-4.7     c0.6-0.3,1.5-0.4,2.5-0.4c1.9,0,5.4,0.5,6.3,2.6c0.4,0.9,0.8,2.5-1,4c-0.9,0.7-2.3,1.4-4,2.2L59.2,63.9C59,64,58.8,64,58.6,64z      M53,58.3l5.6,2.5l27.7-12.6c1.5-0.7,3.4-1.5,3.6-2c0,0,0-0.1-0.1-0.2c-0.2-0.3-1.7-0.8-3.6-0.8c-0.7,0-1.1,0.1-1.3,0.2l-11,5     c-0.5,0.2-1.1,0.2-1.6-0.2c-5.6-4.5-9.4-5.4-11.6-5.4c-0.4,0-0.8,0-1.2,0.1l6.5,6.9c0.3,0.3,0.5,0.8,0.4,1.2     c-0.1,0.4-0.3,0.8-0.7,1.1l-6.2,3.8c-0.2,0.1-0.5,0.2-0.7,0.2L53,58.3z"></path></g></g></switch></g><g id="SvgjsG1178" featurekey="j5pGhi-0" transform="matrix(2.7861038204266895,0,0,2.7861038204266895,101.66712994740371,-2.787021294838837)" fill="#ffffff"><path d="M7.6758 4.901999999999999 l6.748 14.482 l-1.543 0 l-2.2754 -4.7754 l-6.25 0 l-2.2559 4.7754 l-1.6211 0 l6.8457 -14.482 l0.35156 0 z M7.5 7.968999999999999 l-2.5 5.2539 l4.9609 0 z M25.459109375 15.6348 l1.1523 0.61523 q-0.556640625 1.123046875 -1.30859375 1.806640625 t-1.6895 1.04 t-2.1191 0.35645 q-2.6171875 0 -4.091796875 -1.713867188 t-1.4746 -3.8721 q0 -2.041015625 1.25 -3.6328125 q1.58203125 -2.03125 4.248046875 -2.03125 q2.724609375 0 4.365234375 2.080078125 q1.162109375 1.46484375 1.171875 3.65234375 l-9.6191 0 q0.0390625 1.875 1.19140625 3.071289063 t2.8418 1.1963 q0.8203125 0 1.591796875 -0.2880859375 t1.3135 -0.75684 t1.1768 -1.5234 z M25.459109375 12.7539 q-0.2734375 -1.103515625 -0.80078125 -1.762695313 t-1.3965 -1.0645 t-1.8262 -0.40527 q-1.572265625 0 -2.705078125 1.015625 q-0.8203125 0.7421875 -1.240234375 2.216796875 l7.9688 0 z M29.003946875 14.541 l0 -1.2891 q0.15625 -0.966796875 0.44921875 -1.572265625 q0.68359375 -1.6796875 1.8359375 -2.4609375 t1.9141 -0.78125 q0.56640625 0 1.2109375 0.37109375 l-0.71289 1.1523 q-0.908203125 -0.33203125 -1.801757813 0.537109375 t-1.2354 1.8945 q-0.25390625 0.908203125 -0.25390625 3.408203125 l0 3.623 l-1.4063 0 l0 -4.8828 z M40.28320625 8.203 q2.470703125 0 4.1015625 1.787109375 q1.474609375 1.640625 1.474609375 3.876953125 t-1.5625 3.9111 t-4.0137 1.6748 q-2.4609375 0 -4.018554688 -1.674804688 t-1.5576 -3.9111 q0 -2.2265625 1.474609375 -3.8671875 q1.630859375 -1.796875 4.1015625 -1.796875 z M40.28320625 9.551 q-1.708984375 0 -2.939453125 1.26953125 t-1.2305 3.0664 q0 1.162109375 0.5615234375 2.172851563 t1.5186 1.5527 t2.0898 0.54199 q1.15234375 0 2.104492188 -0.5419921875 t1.5088 -1.5527 t0.55664 -2.1729 q0 -1.796875 -1.23046875 -3.06640625 t-2.9395 -1.2695 z M48.359334375 4.901999999999999 l4.5996 0 q1.71875 0 2.646484375 0.41015625 t1.46 1.2598 t0.53223 1.875 q0 0.966796875 -0.4736328125 1.7578125 t-1.3916 1.2793 q1.142578125 0.390625 1.748046875 0.9033203125 t0.94727 1.2451 t0.3418 1.5918 q0 1.748046875 -1.279296875 2.954101563 t-3.418 1.2061 l-5.7129 0 l0 -14.482 z M49.775434375 6.318 l0 4.6387 l2.5684 0 q1.50390625 0 2.211914063 -0.283203125 t1.123 -0.88379 t0.41504 -1.3428 q0 -0.99609375 -0.693359375 -1.5625 t-2.207 -0.56641 l-3.418 0 z M49.775434375 12.412099999999999 l0 5.5566 l3.5352 0 q1.58203125 0 2.319335938 -0.3125 t1.1865 -0.9668 t0.44922 -1.4355 q0 -0.95703125 -0.6298828125 -1.6796875 t-1.7334 -0.98633 q-0.7421875 -0.17578125 -2.568359375 -0.17578125 l-2.5586 0 z M66.210940625 8.203 q2.470703125 0 4.1015625 1.787109375 q1.474609375 1.640625 1.474609375 3.876953125 t-1.5625 3.9111 t-4.0137 1.6748 q-2.4609375 0 -4.018554688 -1.674804688 t-1.5576 -3.9111 q0 -2.2265625 1.474609375 -3.8671875 q1.630859375 -1.796875 4.1015625 -1.796875 z M66.210940625 9.551 q-1.708984375 0 -2.939453125 1.26953125 t-1.2305 3.0664 q0 1.162109375 0.5615234375 2.172851563 t1.5186 1.5527 t2.0898 0.54199 q1.15234375 0 2.104492188 -0.5419921875 t1.5088 -1.5527 t0.55664 -2.1729 q0 -1.796875 -1.23046875 -3.06640625 t-2.9395 -1.2695 z M79.38476875 8.203 q2.470703125 0 4.1015625 1.787109375 q1.474609375 1.640625 1.474609375 3.876953125 t-1.5625 3.9111 t-4.0137 1.6748 q-2.4609375 0 -4.018554688 -1.674804688 t-1.5576 -3.9111 q0 -2.2265625 1.474609375 -3.8671875 q1.630859375 -1.796875 4.1015625 -1.796875 z M79.38476875 9.551 q-1.708984375 0 -2.939453125 1.26953125 t-1.2305 3.0664 q0 1.162109375 0.5615234375 2.172851563 t1.5186 1.5527 t2.0898 0.54199 q1.15234375 0 2.104492188 -0.5419921875 t1.5088 -1.5527 t0.55664 -2.1729 q0 -1.796875 -1.23046875 -3.06640625 t-2.9395 -1.2695 z M87.480496875 4.59 l1.3867 0 l0 8.457 l4.9512 -4.3164 l2.0313 0 l-4.4824 4.0723 l4.8242 6.6406 l-1.9531 0 l-4.2188 -5.7617 l-1.1523 0.94727 l0 4.8145 l-1.3867 0 l0 -14.854 z"></path></g></svg>
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/user/home"><i class="fas fa-home"></i> Home</a></li>
				<li class="nav-item"><a class="nav-link" href="#"><i class="fas fa-envelope"></i> Contact Us</a></li>
			</ul>
		</div>
	</nav>

	<!-- Hero Section -->
	<section class="hero-section">
		<div class="container">
			<h1>Welcome to AeroBook</h1>
			<p>Your gateway to effortless flight booking.</p>
			<a href="${pageContext.request.contextPath}/user/login"
				class="btn btn-primary">Login</a>
		</div>
	</section>

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
							class="text-white"><i class="fas fa-home"></i> Home</a></li>
						<li><a href="${pageContext.request.contextPath}/user/contact"
							class="text-white"><i class="fas fa-envelope"></i> Contact Us</a></li>
						<li><a href="${pageContext.request.contextPath}/user/about"
							class="text-white"><i class="fas fa-info-circle"></i> About Us</a></li>
						<li><a href="${pageContext.request.contextPath}/user/faq"
							class="text-white"><i class="fas fa-question-circle"></i> FAQ</a></li>
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

	<!-- Bootstrap JavaScript and dependencies -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
