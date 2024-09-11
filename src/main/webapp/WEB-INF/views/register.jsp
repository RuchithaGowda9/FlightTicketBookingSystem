<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register User</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6">
                <h2 class="text-center mb-4">Register User</h2>
                <form action="${pageContext.request.contextPath}/user/register" method="post" onsubmit="return validateForm()">
                    <div class="form-group">
                        <label for="firstName">First Name:<span class="text-danger">*</span></label>
                        <input type="text" id="firstName" name="firstName" class="form-control" oninput="validateFirstName()">
                        <div id="firstNameError" class="text-danger"></div>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last Name:</label>
                        <input type="text" id="lastName" name="lastName" class="form-control" oninput="validateLastName()">
                        <div id="lastNameError" class="text-danger"></div>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:<span class="text-danger">*</span></label>
                        <input type="text" id="email" name="email" class="form-control" oninput="validateEmail()">
                        <div id="emailError" class="text-danger"></div>
                    </div>
                    <div class="form-group">
                        <label for="phoneNumber">Phone Number:<span class="text-danger">*</span></label>
                        <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" oninput="validatePhoneNumber()">
                        <div id="phoneNumberError" class="text-danger"></div>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:<span class="text-danger">*</span></label>
                        <input type="password" id="password" name="password" class="form-control" oninput="validatePassword()">
                        <div class="form-check mt-2">
                            <input type="checkbox" id="showPassword" class="form-check-input" onclick="togglePasswordVisibility()">
                            <label for="showPassword" class="form-check-label">Show Password</label>
                        </div>
                        <div id="passwordError" class="text-danger"></div>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Register</button>
                </form>

                <c:if test="${param.success}">
                    <div class="alert alert-success mt-3" role="alert">
                        Registration successful!
                    </div>
                </c:if>
            </div>
        </div>
    </div>

    <!-- Bootstrap JavaScript and dependencies -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script>
        function togglePasswordVisibility() {
            var passwordField = document.getElementById('password');
            var showPasswordCheckbox = document.getElementById('showPassword');
            passwordField.type = showPasswordCheckbox.checked ? 'text' : 'password';
        }

        function validateFirstName() {
            var firstName = document.getElementById('firstName').value;
            var errorElement = document.getElementById('firstNameError');
            if (!firstName) {
                errorElement.textContent = 'First name is required.';
            } else if (firstName.length < 3) {
                errorElement.textContent = 'First name must be at least 3 characters long.';
            } else {
                errorElement.textContent = '';
            }
        }

        function validateLastName() {
            // Currently no specific validation for lastName
            document.getElementById('lastNameError').textContent = '';
        }

        function validateEmail() {
            var email = document.getElementById('email').value;
            var errorElement = document.getElementById('emailError');
            // Regular expression for email validation
            var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!email) {
                errorElement.textContent = 'Email is required.';
            } else if (!emailRegex.test(email)) {
                errorElement.textContent = 'Please enter a valid email address.';
            } else {
                errorElement.textContent = '';
            }
        }

        function validatePhoneNumber() {
            var phoneNumber = document.getElementById('phoneNumber').value;
            var errorElement = document.getElementById('phoneNumberError');
            if (!phoneNumber) {
                errorElement.textContent = 'Phone number is required.';
            } else if (!/^[6789]\d{9}$/.test(phoneNumber)) {
                errorElement.textContent = 'Phone number must be 10 digits long and start with 6, 7, 8, or 9.';
            } else {
                errorElement.textContent = '';
            }
        }

        function validatePassword() {
            var password = document.getElementById('password').value;
            var errorElement = document.getElementById('passwordError');
            if (!password) {
                errorElement.textContent = 'Password is required.';
            } else if (!/(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}/.test(password)) {
                errorElement.textContent = 'Password must be at least 8 characters long and include a mix of uppercase letters, lowercase letters, numbers, and special characters.';
            } else {
                errorElement.textContent = '';
            }
        }

        function validateForm() {
            validateFirstName();
            validateLastName();
            validateEmail();
            validatePhoneNumber();
            validatePassword();

            // Check if there are any errors in the error messages
            var hasErrors = false;
            if (document.getElementById('firstNameError').textContent) hasErrors = true;
            if (document.getElementById('emailError').textContent) hasErrors = true;
            if (document.getElementById('passwordError').textContent) hasErrors = true;
            if (document.getElementById('phoneNumberError').textContent) hasErrors = true;

            return !hasErrors; // Return false if there are errors, true otherwise
        }

        // Add event listeners to validate fields on input
        document.getElementById('firstName').addEventListener('input', validateFirstName);
        document.getElementById('lastName').addEventListener('input', validateLastName);
        document.getElementById('email').addEventListener('input', validateEmail);
        document.getElementById('phoneNumber').addEventListener('input', validatePhoneNumber);
        document.getElementById('password').addEventListener('input', validatePassword);

    </script>
</body>
</html>
