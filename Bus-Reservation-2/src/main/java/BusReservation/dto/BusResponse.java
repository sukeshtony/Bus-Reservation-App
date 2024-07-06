package BusReservation.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusResponse {

	private int id;
	private String bus_name;
	private String bus_no;
	private LocalDate date_of_departure;
	private String from_location;
	private String to_location;
	private int no_of_seats;
	private double cost_per_seats;
}
