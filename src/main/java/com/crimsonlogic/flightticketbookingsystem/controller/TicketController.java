package com.crimsonlogic.flightticketbookingsystem.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.flightticketbookingsystem.service.TicketService;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@GetMapping("/generateticket")
	public ResponseEntity<InputStreamResource> generateTicket(@RequestParam("bookingId") Long bookingId)
			throws IOException, DocumentException {
		ByteArrayInputStream bis = ticketService.generateTicket(bookingId);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=ticket.pdf");

		return ResponseEntity.ok().headers(headers).contentLength(bis.available())
				.contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
	}
}
