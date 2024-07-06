package BusReservation.dto;

import java.util.List;

import BusReservation.model.Bus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class AdminRequest {

	@NotBlank(message = "Name is mandatory")
	private String name;
	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is mandatory")
	@Size(min = 8,message = "Size must be more than 8")
	private String email;
	@NotBlank(message = "Gst number is mandatory")
	@Size(min = 5,max=5, message = "Gst no is must be 5")
	private String gst_no;
	
	private long phone;
	@NotBlank(message = "travels name is mandatory")
	private String travels_name;
	@NotBlank(message = "Password is mandatory")
	private String password;
	private List<Bus> buses;
	
}
