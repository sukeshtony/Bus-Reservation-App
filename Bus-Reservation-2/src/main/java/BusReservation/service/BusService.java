package BusReservation.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import BusReservation.dao.AdminDao;
import BusReservation.dao.BusDao;
import BusReservation.dto.BusRequest;
import BusReservation.dto.BusResponse;
import BusReservation.dto.ResponseStructure;
import BusReservation.exception.AdminNotFoundException;
import BusReservation.model.Bus;

@Service
public class BusService {

	@Autowired
	AdminDao adminDao;
	@Autowired
	private BusDao busDao;
	
	public ResponseEntity<ResponseStructure<BusResponse>> saveBus(BusRequest busRequest,int admin_id){
		ResponseStructure<BusResponse> structure=new ResponseStructure<>();
		
		Bus saveBus=mapToBus(busRequest,admin_id);
		saveBus.setCost_per_seat(busRequest.getCost_per_seat());
		busDao.saveBus(saveBus);
		
		structure.setMessage("Bus Saved");
		structure.setData(mapToBusResponse(saveBus));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<BusResponse>> updateBus(BusRequest busRequest,int id){
		ResponseStructure<BusResponse> structure=new ResponseStructure<>();
		Optional<Bus> recBus=busDao.findById(id);
		
		if(recBus.isPresent()) {
			Bus updateBus=recBus.get();
			updateBus.setBus_name(busRequest.getBus_name());
			updateBus.setBus_no(busRequest.getBus_no());
			updateBus.setDate_of_departure(busRequest.getDate_of_departure());
			updateBus.setFrom_location(busRequest.getFrom_location());
			updateBus.setTo_location(busRequest.getTo_location());
			updateBus.setNo_of_seats(busRequest.getNo_of_seats());
			busDao.saveBus(updateBus);
			structure.setMessage("Bus data has been updated");
			structure.setData(mapToBusResponse(updateBus));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
			
		}
		throw new AdminNotFoundException("Cant find bus with the given id");
	}
	
	public ResponseEntity<ResponseStructure<List<Bus>>> findAvailableBus(String from,String to,LocalDate dateOfDeparture){
		ResponseStructure<List<Bus>> structure=new ResponseStructure<>();
		List<Bus> buses=busDao.findAvailableBuses(from, to, dateOfDeparture);
		if(buses.isEmpty())
			throw new AdminNotFoundException("No available bus form the given date and place");
		structure.setData(buses);
		structure.setMessage("available buses for the given time");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<Bus>> findBusById(int id){
		ResponseStructure<Bus> structure=new ResponseStructure<>();
		Optional<Bus> recbus=busDao.findById(id);
		if(recbus.isEmpty())
			throw new AdminNotFoundException("Cant find bus for the given id");
		Bus dbBus=recbus.get();
		structure.setData(dbBus);
		structure.setMessage("Bus has been found for the given id");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
		
	}
	
	public ResponseEntity<ResponseStructure<List<Bus>>> findByAdminId(int admin_id){
		ResponseStructure<List<Bus>> structure=new ResponseStructure<>();
		List<Bus> buses=busDao.findByAdmin_Id(admin_id);
		if(buses.isEmpty()) {
			throw new AdminNotFoundException("Admin id does not found");
		}
		structure.setMessage("Admin's buses has been found");
		structure.setData(buses);
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<List<Bus>>> findAll(){
		ResponseStructure<List<Bus>> structure=new ResponseStructure<>();
		structure.setData(busDao.findAll());
		structure.setMessage("List of all buses");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<String>> delete(int id){
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<Bus> dbBus=busDao.findById(id);
		
		if(dbBus.isPresent()) {
			busDao.delete(id);
			structure.setData("Bus Deleted");
			structure.setMessage("Bus Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
			
		}
		 throw new AdminNotFoundException("Bus id is invalid, Cant delete the bus");
	}
	
	
	
	
	private Bus mapToBus(BusRequest busRequest,int admin_id) {
		
		return Bus.builder().bus_name(busRequest.getBus_name()).bus_no(busRequest.getBus_no()).date_of_departure(busRequest.getDate_of_departure())
				.from_location(busRequest.getFrom_location()).to_location(busRequest.getTo_location()).no_of_seats(busRequest.getNo_of_seats()).available_seats(busRequest.getAvailable_seats()).admin(adminDao.findById(admin_id).get()).build();
	}
	
	private BusResponse mapToBusResponse(Bus bus) {
		
		return BusResponse.builder().id(bus.getId()).bus_name(bus.getBus_name()).bus_no(bus.getBus_no())
				.date_of_departure(bus.getDate_of_departure()).from_location(bus.getFrom_location())
				.to_location(bus.getTo_location()).no_of_seats(bus.getNo_of_seats()).cost_per_seats(bus.getCost_per_seat()).build();
	}
}
