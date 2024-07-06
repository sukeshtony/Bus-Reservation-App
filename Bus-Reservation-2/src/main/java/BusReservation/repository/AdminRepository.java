package BusReservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import BusReservation.model.Admin;


public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Optional<Admin> findById(int id);
	
	Optional<Admin> findByToken(String token);
	Optional<Admin> findByEmail(String email);
	
	@Query("select a from Admin a where a.email=?1 and a.password=?2")
	Optional<Admin> verifyAdminByEmailAndPassword(String email,String password);
	
	@Query("select a from Admin a where a.phone=?1 and a.password=?2")
	Optional<Admin> verifyAdminByPhoneAndPassword(long phone,String password);
	
}
