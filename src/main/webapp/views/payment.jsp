<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored = "false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
    <div class="container mt-4">
        <h2>Payment Form</h2>
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
                <label for="paymentMethod">Payment Method</label>
                <select id="paymentMethod" name="paymentMethod" class="form-control" required>
                    <option value="">Select Payment Method</option>
                    <option value="Credit Card">Credit Card</option>
                    <option value="Debit Card">Debit Card</option>
                    <option value="Paypal">Paypal</option>
                </select>
            </div>

            <div id="cardDetails" style="display:none;">
                <div class="form-group">
                    <label for="cardNumber">Card Number</label>
                    <input type="text" id="cardNumber" name="cardNumber" class="form-control" placeholder="Card Number">
                    <span id="cardNumberError" class="text-danger"></span> <!-- Error message -->
                </div>
                <div class="form-group">
                    <label for="expiryDate">Expiry Date</label>
                    <input type="text" id="expiryDate" name="expiryDate" class="form-control" placeholder="MM/YY">
                    <span id="expiryDateError" class="text-danger"></span> <!-- Error message -->
                </div>
                <div class="form-group">
                    <label for="cvv">CVV</label>
                    <input type="text" id="cvv" name="cvv" class="form-control" placeholder="CVV">
                    <span id="cvvError" class="text-danger"></span> <!-- Error message -->
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Pay Now</button>
        </form>
    </div>

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
            if (expiryDate && !/^\d{2}\/\d{2}$/.test(expiryDate)) {
                errorElement.textContent = 'Expiry date must be in MM/YY format.';
                return false;
            } else {
                errorElement.textContent = '';
                return true;
            }
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
                event.preventDefault(); // Prevent form submission if validation fails
            }
        });
    </script>
</body>
</html>
