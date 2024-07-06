package BusReservation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import BusReservation.model.Ticket;
import BusReservation.repository.TicketRepository;

@Repository
public class TicketDao {

	@Autowired
	private TicketRepository ticketRepository;
	
	public Ticket saveTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}
	
}

