package BusReservation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BusReservation.dao.BusDao;
import BusReservation.dao.TicketDao;
import BusReservation.dao.UserDao;
import BusReservation.dto.TicketResponse;
import BusReservation.exception.BusNotFoundException;
import BusReservation.exception.UserNotFoundException;
import BusReservation.model.Bus;
import BusReservation.model.Ticket;
import BusReservation.model.User;
import BusReservation.util.AccountStatus;
import BusReservation.util.TicketStatus;

@Service
public class TicketService {

	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private BusDao busDao;
	@Autowired
	private UserDao userDao;
	
	public TicketResponse bookTicket(int userId,int busId,int noOfSeats) {
		Optional<Bus> recBus=busDao.findById(busId);
		Optional<User> recUser=userDao.findById(userId);
		if(recBus.isEmpty()) 
			throw new BusNotFoundException("Cant find bus for the given id");
		if(recUser.isEmpty())
			throw new UserNotFoundException("Cant find user for the given id");
		User dbUser=recUser.get();
		if(dbUser.getStatus().equals(AccountStatus.INACTIVE.toString()))
			throw new IllegalStateException("Please activate your account,Then book ticket");
		Bus dbbus=recBus.get();
		
		if(dbbus.getAvailable_seats() < noOfSeats)
			throw new IllegalStateException("Insufficient seats");
		Ticket ticket=new Ticket();
		ticket.setCost(noOfSeats*dbbus.getCost_per_seat());
		ticket.setStatus(TicketStatus.BOOKED.toString());
		ticket.setBus(dbbus);
		ticket.setUser(dbUser);
		ticket.setNoOfSeatsBooked(noOfSeats);
		dbbus.getBookedTickets().add(ticket);
		dbUser.getTickets().add(ticket);
		dbbus.setAvailable_seats(dbbus.getAvailable_seats()-noOfSeats);
		userDao.SaveUser(dbUser);
		busDao.saveBus(dbbus);
		ticket=ticketDao.saveTicket(ticket);
		
		return mapToTicketResponse(ticket, dbbus, dbUser);
			
	}
	
	public TicketResponse mapToTicketResponse(Ticket ticket,Bus bus,User user) {
		TicketResponse ticketResponse=new TicketResponse();
		ticketResponse.setGender(user.getGender());
		ticketResponse.setPhone(user.getPhone());
		ticketResponse.setUsername(user.getName());
		ticketResponse.setAge(user.getAge());
		ticketResponse.setBusName(bus.getBus_name());
		ticketResponse.setBusNumber(bus.getBus_no());
		ticketResponse.setDateOfDeparture(bus.getDate_of_departure());
		ticketResponse.setCost(ticket.getCost());
		ticketResponse.setSource(bus.getFrom_location());
		ticketResponse.setDestination(bus.getTo_location());
		ticketResponse.setNumberOfSeatsBooked(ticket.getNoOfSeatsBooked());
		ticketResponse.setDateOfBooking(ticket.getDateOfBooking());
		ticketResponse.setStatus(ticket.getStatus());
		ticketResponse.setId(ticket.getId());
		return ticketResponse;
	}

}
