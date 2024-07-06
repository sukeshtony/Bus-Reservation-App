package BusReservation.dto;

import java.time.LocalDate;

import BusReservation.model.Admin;
import lombok.Data;

@Data
public class BusRequest {

	
	private String bus_name;
	private String bus_no;
	private LocalDate date_of_departure;
	private String from_location;
	private String to_location;
	private int no_of_seats;	
	private double cost_per_seat;
	private int available_seats;
	private Admin admin;
	
	
}
