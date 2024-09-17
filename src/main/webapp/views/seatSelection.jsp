<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Seat Selection</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
body {
	background-color: #ffffff;
	background-image: url('https://assets.newatlas.com/dims4/default/2a36987/2147483647/strip/true/crop/1578x1052+0+14/resize/1200x800!/quality/90/?url=http%3A%2F%2Fnewatlas-brightspot.s3.amazonaws.com%2Farchive%2Fcloud-streamer-2.jpg');
	background-size: cover;
	background-position: center;
	background-attachment: fixed;
	margin-top: 70px;
	display: flex;
	flex-direction: column;
}

.seat {
	display: inline-block;
	width: 40px;
	height: 40px;
	margin: 4px;
	text-align: center;
	line-height: 40px;
	cursor: pointer;
	border: 1px solid #ddd;
	background-color: #f5f5f5;
	border-radius: 6px;
	position: relative;
}

.seat.booked {
	background-color: #b0b0b0;
	cursor: not-allowed;
}

.seat.selected {
	background-color: #2a52be;
	color: #ffffff;
	border: 1px solid #1a3e7a;
}

.error-message {
	color: red;
	font-weight: bold;
	margin-top: 10px;
}

.navbar {
	background-color: #2a52be;
	height: 70px;
	line-height: 70px;
	padding: 0 15px;
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

.seat-selection-card {
	background-color: rgba(255, 255, 255, 0.9);
	border-radius: 12px;
	padding: 40px;
	width: 100%;
	max-width: 700px;
	margin: 20px auto;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.aisle {
    display: inline-block;
    width: 20px;
    height: 40px;
    background-color: #d0e8f2; /* Light blue color for aisle */
    border: 1px solid #ddd;
    border-radius: 6px;
    margin: 0;
    text-align: center;
    line-height: 40px;
    position: relative;
}

.aisle::after {
    /* content: 'Aisle Side'; /* Indicate "Aisle Side" */ */
    font-size: 12px;
    color: #333; /* Dark color for readability */
    writing-mode: vertical-rl;
    text-orientation: upright;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}

/* Icon Styles */
.icon {
    font-size: 24px;
    color: #2a52be;
    margin: 0 10px;
}
.icon.start {
    margin-left: 0;
}
.icon.end {
    margin-right: 0;
}

/* Legend styles */
.legend {
	margin-bottom: 20px;
	padding: 10px;
	background-color: rgba(255, 255, 255, 0.8);
	border-radius: 6px;
	display: flex;
	justify-content: space-around;
	flex-wrap: wrap; /* Allows items to wrap onto multiple lines if needed */
}

.legend-item {
	display: flex;
	align-items: center;
	margin: 5px;
}

.legend-box {
	width: 30px;
	height: 30px;
	border: 1px solid #ddd;
	border-radius: 6px;
	margin-right: 10px;
}

.legend-box.available {
	background-color: #f5f5f5;
}

.legend-box.booked {
	background-color: #b0b0b0;
}

.legend-box.selected {
	background-color: #2a52be;
	color: #ffffff;
}

.legend-box.aisle {
	background-color: #d0e8f2;
}

.legend-box.window {
	font-size: 24px;
	color: #2a52be;
	display: flex;
	justify-content: center;
	align-items: center;
}

.legend-label {
	font-size: 14px;
}
</style>
</head>
<body>
	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/user/home">
		 <svg width="200" height="20" viewBox="0 0 369.66666666666663 59.60033587831631" class="looka-1j8o68f"> <defs id="SvgjsDefs1176"></defs> <g id="SvgjsG1177" fill="#ffffff"> <!-- SVG Content --> </g> </svg> </a>
	</nav>
<div class="container mt-4 d-flex justify-content-center">
    <div class="seat-selection-card">
        <h2 class="text-center">Seat Selection for Flight: ${flight.flightNumber}</h2>
        
        <!-- Legend -->
        <div class="legend">
            <div class="legend-item">
                <div class="legend-box available"></div>
                <span class="legend-label">Available</span>
            </div>
            <div class="legend-item">
                <div class="legend-box booked"></div>
                <span class="legend-label">Not Available</span>
            </div>
            <div class="legend-item">
                <div class="legend-box selected"></div>
                <span class="legend-label">Selected</span>
            </div>
            <div class="legend-item">
                <div class="legend-box aisle"></div>
                <span class="legend-label">Aisle</span>
            </div>
            <div class="legend-item">
                <div class="legend-box window">
                    <i class="fas fa-window-maximize"></i>
                </div>
                <span class="legend-label">Window Side</span>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/bookings/submitSeats" method="post">
            <input type="hidden" name="flightId" value="${flight.flightId}" />
            <input type="hidden" name="noOfPassengers" value="${noOfPassengers}" />
            <input type="hidden" name="selectedSeats" value="" />
            <input type="hidden" id="seatPrice" value="${flight.tripPrice}" />
            <input type="hidden" id="totalAmount" name="amount" value="0" />
            <div id="seatLayout" class="mb-3">
                <c:set var="bookedSeatsList" value="${fn:split(bookedSeats, ',')}" />
                <c:forEach var="row" begin="1" end="${noOfRows}">
                    <div class="row mb-2 align-items-center">
                        <!-- Start Icon -->
                        <div class="icon start">
                            <i class="fas fa-window-maximize"></i>
                        </div>

                        <c:forEach var="seatIndex" begin="1" end="${seatsPerRow}">
                            <c:set var="seatNumber" value="${(row - 1) * seatsPerRow + seatIndex}" />
                            <c:if test="${seatIndex % 4 == 0}">
                                <!-- Insert aisle div after every three seats -->
                                <div class="aisle"></div>
                            </c:if>
                            <c:if test="${seatNumber <= allSeats.size()}">
                                <c:set var="seat" value="${seatNumber}" />
                                <c:set var="isBooked" value="false" />
                                <c:forEach items="${bookedSeats}" var="bookedSeat">
                                    <c:if test="${bookedSeat == seat}">
                                        <c:set var="isBooked" value="true" />
                                    </c:if>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${isBooked}">
                                        <div class="seat booked col" data-seat="${seat}">
                                            ${seat} <input type="checkbox" name="seatId" value="${seat}" style="display: none;" disabled>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="seat available col" data-seat="${seat}">
                                            ${seat} <input type="checkbox" name="seatId" value="${seat}" data-price="${seatPrice}" style="display: none;">
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </c:forEach>

                        <!-- End Icon -->
                        <div class="icon end">
                            <i class="fas fa-window-maximize"></i>
                        </div>
                    </div>
                </c:forEach>
            </div>
             <h6>Total Price : Rs </h6>
            <div id="totalPrice" class="mb-3"></div>
            <div id="errorMessage" class="error-message"></div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>
	    document.addEventListener('DOMContentLoaded', function () {
	        let selectedSeats = new Set();
	        const maxSeats = parseInt(document.querySelector('input[name="noOfPassengers"]').value, 10);
	        const seatPriceElement = document.getElementById('seatPrice');
	        const seatPrice = parseFloat(seatPriceElement.value.trim());

	        console.log("Raw Seat Price Value:", seatPriceElement.value);
	        console.log("Parsed Seat Price:", seatPrice);

	        if (isNaN(seatPrice)) {
	            console.error("Invalid seatPrice value:", seatPriceElement.value);
	            return;
	        }

	        const errorMessageContainer = document.getElementById('errorMessage');

	        document.querySelectorAll('.seat.available').forEach(seat => {
	            seat.addEventListener('click', function () {
	                let seatCheckbox = seat.querySelector('input[type="checkbox"]');
	                let seatValue = seat.dataset.seat;

	                if (selectedSeats.has(seatValue)) {
	                    selectedSeats.delete(seatValue);
	                    seat.classList.remove('selected');
	                    seatCheckbox.checked = false;
	                    errorMessageContainer.textContent = '';
	                } else if (selectedSeats.size < maxSeats) {
	                    selectedSeats.add(seatValue);
	                    seat.classList.add('selected');
	                    seatCheckbox.checked = true;
	                    errorMessageContainer.textContent = '';
	                } else {
	                    //errorMessageContainer.textContent = `You can only select up to ${maxSeats} seat(s).`;
	                }

	                document.querySelector('input[name="selectedSeats"]').value = Array.from(selectedSeats).join(',');

	               let totalPrice = selectedSeats.size * seatPrice;

	                console.log("Total Price Calculation:", {
	                    numberOfSeats: selectedSeats.size,
	                    seatPrice: seatPrice,
	                    totalPrice: totalPrice
	                });

	                console.log("Calculated Total Price:", totalPrice);

	                const totalPriceContainer = document.getElementById('totalPrice');
	                const formattedPrice = totalPrice.toFixed(2);
	               // totalPriceContainer.textContent = 'Total Price: ${formattedPrice}';
	                /* if (totalPriceContainer) {
	                    const formattedPrice = totalPrice.toFixed(2);
	                    console.log("Formatted Total Price:", formattedPrice);
	                    totalPriceContainer.innerText = `Total Price: $${formattedPrice}`;
	                    console.log("Updated Total Price:", totalPriceContainer.innerText);
	                } else {
	                    console.error("Total Price Element not found.");
	                } */
	                document.getElementById('totalAmount').value = formattedPrice;
	                totalPriceContainer.textContent = totalPrice;
	            });
	        });
	    });
	</script>
</body>
</html>
