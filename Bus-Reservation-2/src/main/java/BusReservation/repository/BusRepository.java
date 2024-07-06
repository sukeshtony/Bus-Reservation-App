package BusReservation.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import BusReservation.model.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer>{

	@Query("select b from Bus b where b.admin.id=?1")
	List<Bus> findByAdminId(int id);
	
	@Query("Select b from Bus b where b.from_location=?1 and b.to_location=?2 and b.date_of_departure=?3")
	List<Bus> findBus(String from_loc,String to_loc,LocalDate date_of_departure);
}

