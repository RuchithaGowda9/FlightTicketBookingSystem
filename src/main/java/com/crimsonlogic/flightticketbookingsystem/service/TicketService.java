package com.crimsonlogic.flightticketbookingsystem.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.flightticketbookingsystem.entity.Booking;
import com.crimsonlogic.flightticketbookingsystem.entity.Flight;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class TicketService {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private FlightService flightService;

	public ByteArrayInputStream generateTicket(Long bookingId) throws IOException, DocumentException {
		Booking booking = bookingService.getBookingById(bookingId);
		if (booking == null) {
			throw new IllegalArgumentException("Booking not found");
		}

		Flight flight = booking.getFlight();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, baos);

		document.open();

		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
		Paragraph header = new Paragraph("Your Flight Ticket", headerFont);
		header.setAlignment(Element.ALIGN_CENTER);
		document.add(header);
		document.add(new Paragraph("-------------------------------------------------",
				FontFactory.getFont(FontFactory.HELVETICA, 12)));

		Font detailFontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
		Font detailFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

		document.add(new Paragraph("Booking ID: " + booking.getBookingId(), detailFontBold));
		document.add(new Paragraph("Flight Number: " + flight.getFlightNumber(), detailFont));
		document.add(new Paragraph("Departure Airport: " + flight.getDepartureAirport().getAirportName() + " ("
				+ flight.getDepartureAirport().getAirportCode() + ")", detailFont));
		document.add(new Paragraph("Arrival Airport: " + flight.getArrivalAirport().getAirportName() + " ("
				+ flight.getArrivalAirport().getAirportCode() + ")", detailFont));
		document.add(new Paragraph(
				"Departure Time: " + new SimpleDateFormat("dd MMM yyyy HH:mm").format(flight.getDepartureTime()),
				detailFont));
		document.add(new Paragraph(
				"Arrival Time: " + new SimpleDateFormat("dd MMM yyyy HH:mm").format(flight.getArrivalTime()),
				detailFont));
		document.add(new Paragraph("Seat Number: " + booking.getSeatId(), detailFont));

		document.add(new Paragraph("-------------------------------------------------",
				FontFactory.getFont(FontFactory.HELVETICA, 12)));
		Paragraph footer = new Paragraph("Thank you for booking with us! Have a pleasant journey!",
				FontFactory.getFont(FontFactory.HELVETICA, 10));
		footer.setAlignment(Element.ALIGN_CENTER);
		document.add(footer);

		document.close();

		return new ByteArrayInputStream(baos.toByteArray());
	}
}
