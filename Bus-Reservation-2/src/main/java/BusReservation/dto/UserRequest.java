package BusReservation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	@Email(message = "Invalid email format")
	@Size(message = "Size must be more than 8",min = 8)
	private String email;
	@NotBlank(message = "Gender is madatory")
	private String gender;
	private int age;
	private long phone;
	@NotBlank(message = "password is mandatory")
	private String password;
}
