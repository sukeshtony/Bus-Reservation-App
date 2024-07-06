package BusReservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponse {

	private int id;
	private String name;
	private String email;
	private long phone;
	private String gst_no;
	private String travels_name;
	
}
