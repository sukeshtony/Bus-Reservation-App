package BusReservation.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDate dateOfBooking;
	@Column(nullable = false)
	private double cost;
	@Column(nullable = false)
	private String status;
	@Column(nullable = false)
	private int noOfSeatsBooked;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "bus_id")
	private Bus bus;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id")
	private User user;
}
