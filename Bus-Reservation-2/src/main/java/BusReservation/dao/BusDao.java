package BusReservation.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import BusReservation.model.Bus;
import BusReservation.repository.BusRepository;

@Repository
public class BusDao {

	@Autowired
	private BusRepository busRepository;
	
	public Bus saveBus(Bus bus){
		return busRepository.save(bus);
	}
	
	public List<Bus> findAll(){
		return busRepository.findAll();
	}
	
	public void delete(int id) {
		busRepository.deleteById(id);;
	}
	
	public List<Bus> findAvailableBuses(String from,String to,LocalDate dateOfDeparture){
		return busRepository.findBus(from, to, dateOfDeparture);
	}
	
	public Optional<Bus> findById(int id) {
		return busRepository.findById(id);
	}
	
	public List<Bus> findByAdmin_Id(int admin_id){
		return busRepository.findByAdminId(admin_id);
	}
	
	
	
}
