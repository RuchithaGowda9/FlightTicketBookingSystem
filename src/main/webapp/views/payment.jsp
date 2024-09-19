<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
        .payment-card {
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
        .body-content {
            margin-top: 70px;
            flex: 1;
        }
        .error-message {
            color: red;
            font-size: 0.875em;
            margin-top: 5px;
        }
        .form-group label {
            font-weight: bold;
        }
        .required-field::after {
            content: "*";
            color: red;
            margin-left: 5px;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/user/home">
            AeroBook
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact Us</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container body-content d-flex justify-content-center align-items-center">
        <div class="payment-card">
            <h2 class="text-center">Payment Form</h2>
            <form action="${pageContext.request.contextPath}/payments/processpayment" method="post">
            	
                <input type="hidden" name="bookingId" value="${bookingId}">
                <input type="hidden" name="flightId" value="${flightId}">
                <input type="hidden" name="seatsBooked" value="${seatsBooked}">
                <input type="hidden" name="noOfPassengers" value="${noOfPassengers}">

                <div class="form-group">
                    <label for="amount">Amount</label>
                    <input type="text" id="amount" name="amount" class="form-control" value="${amount}" readonly>
                </div>

                <div class="form-group">
                    <label for="paymentMethod" class="required-field">Payment Method:</label>
                    <select id="paymentMethod" name="paymentMethod" class="form-control" required>
                        <option value="">Select Payment Method</option>
                        <option value="Credit Card">Credit Card</option>
                        <option value="Debit Card">Debit Card</option>
                        <option value="Paypal">Paypal</option>
                    </select>
                </div>

                <div id="cardDetails" style="display:none;">
                    <div class="form-group">
                        <label for="cardNumber" class="required-field">Card Number:</label>
                        <input type="text" id="cardNumber" name="cardNumber" class="form-control" placeholder="Card Number">
                        <span id="cardNumberError" class="error-message"></span>
                    </div>
                    <div class="form-group">
                        <label for="expiryDate" class="required-field">Expiry Date:</label>
                        <input type="text" id="expiryDate" name="expiryDate" class="form-control" placeholder="MM/YY">
                        <span id="expiryDateError" class="error-message"></span>
                    </div>
                    <div class="form-group">
                        <label for="cvv" class="required-field">CVV:</label>
                        <input type="password" id="cvv" name="cvv" class="form-control" placeholder="CVV">
                        <span id="cvvError" class="error-message"></span>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary btn-block">Pay Now</button>
            </form>
        </div>
    </div>
<!-- Payment Success Modal -->
<div class="modal fade" id="paymentSuccessModal" tabindex="-1" role="dialog" aria-labelledby="paymentSuccessModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="paymentSuccessModalLabel">Payment Successful</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Your payment was successful!
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="downloadTicketsBtn">Download Tickets</button>
                <button type="button" class="btn btn-secondary" id="backToHomeBtn">Back to Home</button>
            </div>
        </div>
    </div>
</div>

    <!-- Footer -->
    <footer class="footer">
        <div class="container">
            <p>&copy; 2024 AeroBook. All rights reserved.</p>
        </div>
    </footer>

    <!-- Bootstrap JavaScript and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        document.getElementById('paymentMethod').addEventListener('change', function() {
            var cardDetails = document.getElementById('cardDetails');
            if (this.value === 'Credit Card' || this.value === 'Debit Card') {
                cardDetails.style.display = 'block';
            } else {
                cardDetails.style.display = 'none';
            }
        });

        function validateCardNumber() {
            var cardNumber = document.getElementById('cardNumber').value;
            var errorElement = document.getElementById('cardNumberError');
            if (cardNumber && !/^\d{16}$/.test(cardNumber)) {
                errorElement.textContent = 'Card number must be 16 digits.';
                return false;
            } else {
                errorElement.textContent = '';
                return true;
            }
        }

        function validateExpiryDate() {
            var expiryDate = document.getElementById('expiryDate').value;
            var errorElement = document.getElementById('expiryDateError');
            
            // Regex for MM/YY format
            var expiryDateRegex = /^\d{2}\/\d{2}$/;
            
            if (!expiryDateRegex.test(expiryDate)) {
                errorElement.textContent = 'Expiry date must be in MM/YY format.';
                return false;
            }
            
            var parts = expiryDate.split('/');
            var month = parseInt(parts[0], 10);
            var year = parseInt(parts[1], 10);
            if (month < 1 || month > 12) {
                errorElement.textContent = 'Month must be between 01 and 12.';
                return false;
            }
            var currentYear = new Date().getFullYear();
            var currentYearShort = currentYear % 100;
            if (year < currentYearShort) {
                errorElement.textContent = 'Year must be in the future.';
                return false;
            }
            errorElement.textContent = '';
            return true;
        }


        function validateCVV() {
            var cvv = document.getElementById('cvv').value;
            var errorElement = document.getElementById('cvvError');
            if (cvv && !/^\d{3}$/.test(cvv)) {
                errorElement.textContent = 'CVV must be 3 digits.';
                return false;
            } else {
                errorElement.textContent = '';
                return true;
            }
        }

        document.getElementById('cardNumber').addEventListener('input', validateCardNumber);
        document.getElementById('expiryDate').addEventListener('input', validateExpiryDate);
        document.getElementById('cvv').addEventListener('input', validateCVV);

        document.querySelector('form').addEventListener('submit', function(event) {
            var isCardNumberValid = validateCardNumber();
            var isExpiryDateValid = validateExpiryDate();
            var isCVVValid = validateCVV();

            if (!isCardNumberValid || !isExpiryDateValid || !isCVVValid) {
                event.preventDefault();
            }
        });
    </script>
    <script>
    let currentBookingId = null;

    document.getElementById('paymentForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent default form submission

        // Prepare data to be sent
        const formData = new FormData(this);
        currentBookingId = formData.get('bookingId'); // Capture the booking ID

        fetch('${pageContext.request.contextPath}/payments/processpayment', {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (!response.ok) throw new Error('Network response was not ok');
            return response.text();
        })
        .then(data => {
            // Show the success modal
            $('#paymentSuccessModal').modal('show');
        })
        .catch(error => {
            console.error('Error:', error);
            // Optionally handle errors (e.g., show an error message)
        });
    });

    // Handle the Download Tickets button click
    document.getElementById('downloadTicketsBtn').addEventListener('click', function() {
        if (currentBookingId) {
            window.location.href = '${pageContext.request.contextPath}/tickets/generateticket?bookingId=' + currentBookingId;
        }
    });

    // Handle the Back to Home button click
    document.getElementById('backToHomeBtn').addEventListener('click', function() {
        window.location.href = '${pageContext.request.contextPath}/user/userdashboard';
    });
</script>
    
</body>
</html>
