package BusReservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BusReservation.model.Ticket;



public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
