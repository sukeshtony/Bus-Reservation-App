package BusReservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BusReservation.dto.ResponseStructure;
import BusReservation.dto.TicketResponse;
import BusReservation.model.Ticket;
import BusReservation.service.TicketService;

@CrossOrigin
@RestController
@RequestMapping("api/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@PostMapping("/{busId}/{userId}/{noOfSeats}")
	public TicketResponse saveTicket(@PathVariable int busId,
			@PathVariable int userId,@PathVariable int noOfSeats){
		
		return ticketService.bookTicket(userId, busId, noOfSeats);
	}
}
