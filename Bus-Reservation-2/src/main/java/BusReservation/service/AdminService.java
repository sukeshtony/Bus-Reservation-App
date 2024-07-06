package BusReservation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import BusReservation.dao.AdminDao;
import BusReservation.dto.AdminRequest;
import BusReservation.dto.AdminResponse;
import BusReservation.dto.EmailConfiguration;
import BusReservation.dto.ResponseStructure;
import BusReservation.exception.AdminNotFoundException;
import BusReservation.model.Admin;
import BusReservation.model.Bus;
import jakarta.servlet.http.HttpServletRequest;
import BusReservation.util.AccountStatus;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private ReservationApiMailService mailService;
	
	@Autowired
	private LinkGenerationService linkGenerationService;
	
	@Autowired
	EmailConfiguration emailConfiguration;
	
	public ResponseEntity<ResponseStructure<AdminResponse>> saveAdmin(AdminRequest adminRequest,HttpServletRequest request){
		
		ResponseStructure<AdminResponse> structure=new ResponseStructure<>();
		Admin dbAdmin=mapToAdmin(adminRequest);
		String activation_Link=linkGenerationService.getAdminActivationLink(dbAdmin, request);
		emailConfiguration.setText("Click here to activate your account"+activation_Link);
		emailConfiguration.setToAddress(dbAdmin.getEmail());
		emailConfiguration.setSubject("Activate Your Account");	
		structure.setMessage(mailService.sendMail(emailConfiguration));	
		structure.setData(mapToAdminResponse(dbAdmin));
		structure.setStatusCode(HttpStatus.CREATED.value());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest,int id){		
		ResponseStructure<AdminResponse> structure=new ResponseStructure<>();
		Optional<Admin> recAdmin=adminDao.findById(id);
		if(recAdmin.isPresent()) {	
			
			Admin dbadmin=recAdmin.get();
			
			dbadmin.setId(id);
			dbadmin.setName(adminRequest.getName());
			dbadmin.setEmail(adminRequest.getEmail());
			dbadmin.setGst_no(adminRequest.getGst_no());
			dbadmin.setPassword(adminRequest.getPassword());
			dbadmin.setPhone(adminRequest.getPhone());
			dbadmin.setTravels_name(adminRequest.getTravels_name());
		
			adminDao.saveAdmin(dbadmin);
			structure.setMessage("Admin got updated");
			structure.setData(mapToAdminResponse(dbadmin));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);		
		}
		throw new AdminNotFoundException("Can't find the admin with for the given id");
		
	}
	
	public ResponseEntity<ResponseStructure<AdminResponse>> findById(int id)  {
		ResponseStructure<AdminResponse> structure=new ResponseStructure<>();
		
		Optional<Admin> option=adminDao.findById(id);
		if(option.isPresent()) {
			structure.setMessage("Admin has been found");
			structure.setData(mapToAdminResponse(option.get()));
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);			
		}	
		throw new AdminNotFoundException("Invalid ID");
	}
	
	
	public ResponseEntity<ResponseStructure<AdminResponse>> verifyByPhoneAndPassword(long phone,String password){
		ResponseStructure<AdminResponse> structure=new ResponseStructure<>();
		Optional<Admin> option=adminDao.verifyByPhoneAndPassword(phone, password);
		if(option.isPresent()) {
			Admin admin=option.get();
			if(admin.getStatus().equals(AccountStatus.INACTIVE.toString())) {
				throw new IllegalStateException("Please Activate Your Account Before You Sign In");
			}
			structure.setMessage("Admin has been verified for the above phone number");
			structure.setData(mapToAdminResponse(option.get()));
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Invalid Phone no or Password");
	}
	
	public ResponseEntity<ResponseStructure<AdminResponse>> verifyByEmailAndPassword(String email,String password){
		ResponseStructure<AdminResponse> structure=new ResponseStructure<>();
		Optional<Admin> option=adminDao.verifyByEmailAndPassword(email, password);
		if(option.isPresent()) {
			Admin admin=option.get();
			if(admin.getStatus().equals(AccountStatus.INACTIVE.toString())) {
				throw new IllegalStateException("Please Activate Your Account Before You Sign In");
			}
			structure.setMessage("Admin has been verified for the above email");
			structure.setData(mapToAdminResponse(option.get()));
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Invalid Email or Password");
	}
	public String forgotPassword(String email,HttpServletRequest request) {
		Optional<Admin> recAdmin=adminDao.findByEmail(email);
		if(recAdmin.isEmpty()) {
			throw new AdminNotFoundException("Invaild Email Id");
		}
		Admin admin=recAdmin.get();
		String resetPasswordLink=linkGenerationService.getAdminResetPasswordLink(admin, request);
		emailConfiguration.setToAddress(admin.getEmail());
		emailConfiguration.setSubject("Reset Your Password");
		emailConfiguration.setText("Please click the following link to reset password "+resetPasswordLink);
		mailService.sendMail(emailConfiguration);
		return "Reset password link has been send to entered email id";
		
	}
	
	public ResponseEntity<ResponseStructure<AdminResponse>>findByEmail(String email){
		ResponseStructure<AdminResponse> structure=new ResponseStructure<>();
		Optional<Admin> recAdmin=adminDao.findByEmail(email);
		if(recAdmin.isEmpty()) {
			throw new AdminNotFoundException("Invaild Email id");
		}
		structure.setMessage("Admin has been found");
		structure.setData(mapToAdminResponse(recAdmin.get()));
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);	
		
	}
	
	public AdminResponse verifyLink(String token) {
		Optional<Admin> recAdmin=adminDao.findByToken(token);
		if(recAdmin.isEmpty()) {
			throw new AdminNotFoundException("Link has been expired or invalid");
		}
		Admin dbAdmin=recAdmin.get();
		dbAdmin.setToken(null);
		adminDao.saveAdmin(dbAdmin);
		return mapToAdminResponse(dbAdmin);
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteAdmin(int id){
		Optional<Admin> recAdmin=adminDao.findById(id);
		ResponseStructure<String> structure=new ResponseStructure<>();
		if(recAdmin.isEmpty())
			throw new AdminNotFoundException("There is no admin present with the given id");
		 adminDao.deleteAdmin(id);
		 structure.setMessage("Admin deleted");
		 structure.setData("Data has been removed");
		 structure.setStatusCode(HttpStatus.OK.value());	 
		 return ResponseEntity.status(HttpStatus.OK).body(structure);	 
	}
	
	private Admin mapToAdmin(AdminRequest adminRequest) {
		
		Admin admin=Admin.builder().name(adminRequest.getName()).email(adminRequest.getEmail()).phone(adminRequest.getPhone())
				.travels_name(adminRequest.getTravels_name()).gst_no(adminRequest.getGst_no()).password(adminRequest.getPassword()).
				build();
		
		
		return admin;
		
	}
	
	private AdminResponse mapToAdminResponse(Admin admin) {
		return AdminResponse.builder().id(admin.getId()).name(admin.getName()).phone(admin.getPhone())
				.email(admin.getEmail()).travels_name(admin.getTravels_name()).build();
	}
	
	public String activate(String token) {
		Optional<Admin> recAdmin=adminDao.findByToken(token);
		if(recAdmin.isEmpty()) {
			throw new AdminNotFoundException("Invalid Token");
		}	
			Admin dbAdmin=recAdmin.get();
			dbAdmin.setStatus(AccountStatus.ACTIVE.toString());
			dbAdmin.setToken(null);
			adminDao.saveAdmin(dbAdmin);
			return "Your Account has been activated";
		
		
	}
	
		
	
}
