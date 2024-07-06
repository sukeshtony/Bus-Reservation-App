package BusReservation.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import BusReservation.model.Admin;
import BusReservation.repository.AdminRepository;

@Repository
public class AdminDao {

	@Autowired
	private AdminRepository adminRepository;
	
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	public Optional<Admin> findById(int id){
		return adminRepository.findById(id);
	}
	
	public Optional<Admin> findByToken(String token){
		return adminRepository.findByToken(token);
	}
	
	public Optional<Admin> verifyByEmailAndPassword(String email,String password){
		return adminRepository.verifyAdminByEmailAndPassword(email, password);
	}
	
	public Optional<Admin> verifyByPhoneAndPassword(long phone,String password){
		return adminRepository.verifyAdminByPhoneAndPassword(phone, password);
		
	}
	
	public Optional<Admin>findByEmail(String email){
		return adminRepository.findByEmail(email);
	}
	
	public void deleteAdmin(int id) {
		adminRepository.deleteById(id);
	}
	
	
	
	
}
