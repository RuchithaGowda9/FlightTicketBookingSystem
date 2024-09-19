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
import com.itextpdf.text.pdf.PdfPTable;
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

		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
		Paragraph header = new Paragraph("Your Flight Ticket", headerFont);
		header.setAlignment(Element.ALIGN_CENTER);
		document.add(header);

		// Flight Details Table
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setSpacingBefore(10f);
		table.setSpacingAfter(10f);

		Font detailFontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
		Font detailFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

		table.addCell(new Paragraph("Booking ID:", detailFontBold));
		table.addCell(new Paragraph(String.valueOf(booking.getBookingId()), detailFont));
		table.addCell(new Paragraph("Flight Number:", detailFontBold));
		table.addCell(new Paragraph(flight.getFlightNumber(), detailFont));

		table.addCell(new Paragraph("Departure Airport:", detailFontBold));
		table.addCell(new Paragraph(flight.getDepartureAirport().getAirportName() + " ("
				+ flight.getDepartureAirport().getAirportCode() + ")", detailFont));

		table.addCell(new Paragraph("Arrival Airport:", detailFontBold));
		table.addCell(new Paragraph(
				flight.getArrivalAirport().getAirportName() + " (" + flight.getArrivalAirport().getAirportCode() + ")",
				detailFont));

		table.addCell(new Paragraph("Departure Time:", detailFontBold));
		table.addCell(
				new Paragraph(new SimpleDateFormat("dd MMM yyyy HH:mm").format(flight.getDepartureTime()), detailFont));

		table.addCell(new Paragraph("Arrival Time:", detailFontBold));
		table.addCell(
				new Paragraph(new SimpleDateFormat("dd MMM yyyy HH:mm").format(flight.getArrivalTime()), detailFont));

		table.addCell(new Paragraph("Seat Number:", detailFontBold));
		table.addCell(new Paragraph(booking.getSeatId(), detailFont));

		document.add(table);

		// Terms and Conditions Section
		Paragraph termsHeader = new Paragraph("Terms and Conditions",
				FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
		termsHeader.setSpacingBefore(20f);
		termsHeader.setSpacingAfter(10f);
		document.add(termsHeader);

		String[] terms = {
				"1. Cancellation Policy: If you decide to cancel your ticket, a cancellation fee will be applied: "
						+ "A 5% cancellation fee for domestic flights. A 6% cancellation fee for international flights. "
						+ "The remaining amount will be refunded to your original payment method.",

				"2. Flight Cancellation: In the event that any flights are canceled due to unforeseen circumstances, "
						+ "the total airfare will be fully refunded to your original payment method.",

				"3. Change of Itinerary: We reserve the right to modify flight schedules and routes as necessary. "
						+ "Customers will be notified of any changes as soon as possible, and alternatives will be provided when applicable.",

				"4. Baggage Policy: Each passenger is entitled to a specified baggage allowance. "
						+ "Additional charges may apply for excess baggage. It is advisable to review our baggage policy prior to travel.",

				"5. Travel Insurance: We recommend that passengers obtain travel insurance to cover any unforeseen circumstances, "
						+ "including but not limited to trip cancellations, medical emergencies, and loss of baggage." };

		for (String term : terms) {
			document.add(new Paragraph(term, detailFont));
			document.add(new Paragraph("")); // Add a blank line for spacing
		}

		Paragraph footer = new Paragraph("Thank you for booking with us! Have a pleasant journey!",
				FontFactory.getFont(FontFactory.HELVETICA, 10));
		footer.setAlignment(Element.ALIGN_CENTER);
		document.add(footer);

		document.close();

		return new ByteArrayInputStream(baos.toByteArray());
	}

}
