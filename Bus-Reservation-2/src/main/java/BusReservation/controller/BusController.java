package BusReservation.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BusReservation.dto.BusRequest;
import BusReservation.dto.BusResponse;
import BusReservation.dto.ResponseStructure;
import BusReservation.model.Bus;
import BusReservation.service.BusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin
@RestController
@RequestMapping("api/bus")
public class BusController {

	@Autowired
	private BusService busService;
	
	@PostMapping("/{admin_id}")
	public ResponseEntity<ResponseStructure<BusResponse>> saveBus(@RequestBody BusRequest busRequest,@PathVariable(name = "admin_id") int admin_id){
		return busService.saveBus(busRequest,admin_id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<BusResponse>> updateBus(@RequestBody BusRequest busRequest,@PathVariable(name = "id") int id){
		return busService.updateBus(busRequest, id);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> delete(@PathVariable int id){
		return busService.delete(id);
	}
	
	@GetMapping("/findByAdmin/{admin_id}")
	public ResponseEntity<ResponseStructure<List<Bus>>> findByAdminId(@PathVariable int admin_id){
		return busService.findByAdminId(admin_id);
	}
	
	@GetMapping("/findByBusId/{bus_id}")
	public ResponseEntity<ResponseStructure<Bus>> findByBusId(@PathVariable int bus_id){
		return busService.findBusById(bus_id);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<ResponseStructure<List<Bus>>> findAll() {
		return busService.findAll();
	}
	@GetMapping("/find")
	public ResponseEntity<ResponseStructure<List<Bus>>> findAvailableBus(@RequestParam String from,@RequestParam String to,@RequestParam LocalDate	 date){
		return busService.findAvailableBus(from, to, date);
	}
	
}
