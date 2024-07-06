package BusReservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import BusReservation.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findById(int id);
	
	Optional<User> findByToken(String token);
	
	@Query("select u from User u where u.email=?1 and u.password=?2")
	Optional<User> verifyUserByEmailAndPassword(String email,String password);
	
	@Query("select u from User u where u.phone=?1 and u.password=?2")
	Optional<User> verifyUserByPhoneAndPassword(long phone,String password);
}
