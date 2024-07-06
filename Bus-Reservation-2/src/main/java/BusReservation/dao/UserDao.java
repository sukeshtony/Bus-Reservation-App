package BusReservation.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import BusReservation.model.User;
import BusReservation.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository userRepository;
	
	public User SaveUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> findById(int id){
		return userRepository.findById(id);
	}
	
	public Optional<User> verifyByEmailAndPassword(String email,String password){
		return userRepository.verifyUserByEmailAndPassword(email, password);
	}
	
	public Optional<User> verifyByPhoneAndPassword(long phone,String password){
		return userRepository.verifyUserByPhoneAndPassword(phone, password);
	}
	
	public Optional<User>findByToken(String token){
		return userRepository.findByToken(token);
	}
	
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
	
}
